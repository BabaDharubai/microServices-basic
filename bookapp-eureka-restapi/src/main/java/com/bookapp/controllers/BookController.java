/**
 * 
 */
package com.bookapp.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bookapp.model.Book;
import com.bookapp.service.IBookService;

/**
 * @author BabaFakruddinDharuba
 *
 */
@RestController
@RequestMapping("book-api")
public class BookController {


	private Logger logger=LoggerFactory.getLogger(BookController.class);;

	IBookService bookService;

	/**
	 * @param bookService the bookService to set
	 */
	@Autowired
	public void setBookService(IBookService bookService) {
		this.bookService = bookService;
	}

	@GetMapping("/greet")
	public String greet() {
		logger.trace("A TRACE Message");
		logger.debug("A DEBUG Message");
		logger.info("An INFO Message");
		logger.warn("A WARN Message");
		logger.error("An ERROR Message");
		return "hello";
	}


	@PostMapping("/books")
	ResponseEntity<Void> addBook(@RequestBody Book book) {
		logger.info("one book is adding");
		bookService.addBook(book);
		logger.info("book added "+book);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@PutMapping("/books")
	ResponseEntity<Void> updateBook(@RequestBody Book book)//check passing parameter
	{
		bookService.updateBook(book);
		return ResponseEntity.status(HttpStatus.ACCEPTED).build();		
	}

	@DeleteMapping("/books/{bookId}")
	ResponseEntity<Void> deleteBook(@PathVariable("bookId") int bookId) {
		bookService.deleteBook(bookId);
		return ResponseEntity.status(HttpStatus.GONE).build();
	}


	@GetMapping("/books/{bookId}")
	ResponseEntity<Book> getById(@PathVariable("bookId") int bookid) {
		Book book=bookService.getById(bookid);
		HttpHeaders header=new HttpHeaders();
		header.add("desc", "get book by Id");
		return ResponseEntity.ok().headers(header).body(book);
	}

	@GetMapping("/books")
	ResponseEntity<List<Book>> getAll(){
		logger.info("Showing all books");
		List<Book> books=bookService.getAll();
		HttpHeaders header=new HttpHeaders();
		header.add("desc","Returns a list of books");
		header.add("info", "API-Get all books");
		ResponseEntity<List<Book>> responseEntity=new ResponseEntity<>(books,header,HttpStatus.OK);
		return responseEntity;
	}

	@GetMapping("/books/author/{author}")
	ResponseEntity<List<Book>> getByAuthor(@PathVariable("author") String author){
		logger.info("get Books by author " +author);
		HttpHeaders header=new HttpHeaders();
		header.add("desc", "Returns a list of books by author "+author);
		List<Book> books=bookService.getByAuthor(author);
		if(books.isEmpty())
			logger.warn("No books is available");
		return ResponseEntity.ok().headers(header).body(books);
	}

	@GetMapping("/books/category/{category}")
	ResponseEntity<List<Book>> getByCategory(@RequestParam("category") String category){
		logger.info("get Books by category " +category);
		HttpHeaders header=new HttpHeaders();
		header.add("desc", "Returns a list of books by category "+category);
		List<Book> books=bookService.getByCategory(category);
		return ResponseEntity.ok().headers(header).body(books);
	}

	@GetMapping("/books/price/{price}")
	ResponseEntity<List<Book>> getByPrice(@PathVariable("price") double price){
		HttpHeaders header=new HttpHeaders();
		header.add("desc", "Returns a list of books by price "+price);
		List<Book> books= bookService.getByPrice(price);
		return ResponseEntity.ok().headers(header).body(books);
	}

	//	List<Book> getByAuthorAndCategory(String author,String category){
	//		return bookService.getByAuthorAndCategory(author, category);
	//	}
	//	
	//	List<Book> getByTitleOrAuthor(String title,String author){
	//		
	//	}


	//custom query
	@GetMapping("books/price/{price}/author/{author}")
	ResponseEntity<List<Book>> getByPriceAuth(@PathVariable("price") double cost,@PathVariable("author") String author){
		HttpHeaders header=new HttpHeaders();
		header.add("desc", "Returns a list of books by price "+cost+" author "+author);
		List<Book> books= bookService.getByPriceAuth(cost, author);
		return ResponseEntity.ok().headers(header).body(books);
	}

	@GetMapping("books/category/{category}/author/{author}")
	ResponseEntity<List<Book>> getByCatAndAuth(@PathVariable("category") String category,@PathVariable("author")String author){
		HttpHeaders header=new HttpHeaders();
		header.add("desc", "Returns a list of books by category "+category+" author "+author);
		List<Book> books= bookService.getByCatAndAuth(category, author);
		return ResponseEntity.ok().headers(header).body(books); 
	}

	//	List<Book> getBookByAuth(String author){
	//		return bookService.getBookByAuth(author);
	//	}

}
