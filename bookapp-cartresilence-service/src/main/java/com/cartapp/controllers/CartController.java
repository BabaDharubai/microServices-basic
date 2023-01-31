/**
 * 
 */
package com.cartapp.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cartapp.model.Book;
import com.cartapp.model.Cart;
import com.cartapp.service.ICartService;

/**
 * @author BabaFakruddinDharuba
 *
 */
@RestController
@RequestMapping("cartresilence-api")
public class CartController {


	private Logger logger=LoggerFactory.getLogger(CartController.class);;

	ICartService cartService;

	/**
	 * @param bookService the bookService to set
	 */
	@Autowired
	public void setCartService(ICartService cartService) {
		this.cartService = cartService;
	}

//	@GetMapping("/greet")
//	public String greet() {
//		logger.trace("A TRACE Message");
//		logger.debug("A DEBUG Message");
//		logger.info("An INFO Message");
//		logger.warn("A WARN Message");
//		logger.error("An ERROR Message");
//		return "hello";
//	}


//	@PostMapping("/books")
//	ResponseEntity<Void> addBook(@RequestBody Book book) {
//		logger.info("one book is adding");
//		bookService.addBook(book);
//		logger.info("book added "+book);
//		return ResponseEntity.status(HttpStatus.CREATED).build();
//	}
//
//	@PutMapping("/books")
//	ResponseEntity<Void> updateBook(@RequestBody Book book)//check passing parameter
//	{
//		bookService.updateBook(book);
//		return ResponseEntity.status(HttpStatus.ACCEPTED).build();		
//	}
//
//	@DeleteMapping("/books/{bookId}")
//	ResponseEntity<Void> deleteBook(@PathVariable("bookId") int bookId) {
//		bookService.deleteBook(bookId);
//		return ResponseEntity.status(HttpStatus.GONE).build();
//	}


	@GetMapping("/cart/bookId/{bookId}")
	ResponseEntity<Book> getById(@PathVariable("bookId") int bookid) {
		Book book=cartService.getById(bookid);
		HttpHeaders header=new HttpHeaders();
		header.add("desc", "get book by Id");
		return ResponseEntity.ok().headers(header).body(book);
	}

//	@GetMapping("/vbooks")
//	ResponseEntity<List<Book>> getAll(){
//		logger.info("Showing all books");
//		List<Book> books=cartService.getAll();
//		HttpHeaders header=new HttpHeaders();
//		header.add("desc","Returns a list of books");
//		header.add("info", "API-Get all books");
//		ResponseEntity<List<Book>> responseEntity=new ResponseEntity<>(books,header,HttpStatus.OK);
//		return responseEntity;
//	}

	@GetMapping("/cart/author/{author}")
	ResponseEntity<List<Book>> getByAuthor(@PathVariable("author") String author){
		logger.info("get Books by author " +author);
		HttpHeaders header=new HttpHeaders();
		header.add("desc", "Returns a list of books by author "+author);
		List<Book> books=cartService.getByAuthor(author);
		if(books.isEmpty())
			logger.warn("No books is available");
		return ResponseEntity.ok().headers(header).body(books);
	}

//	@GetMapping("/vbooks/category/{category}")
//	ResponseEntity<List<Book>> getByCategory(@PathVariable("category") String category){
//		logger.info("get Books by category " +category);
//		HttpHeaders header=new HttpHeaders();
//		header.add("desc", "Returns a list of books by category "+category);
//		List<Book> books=bookService.getByCategory(category);
//		return ResponseEntity.ok().headers(header).body(books);
//	}

//	@GetMapping("/books/price/{price}")
//	ResponseEntity<List<Book>> getByPrice(@PathVariable("price") double price){
//		HttpHeaders header=new HttpHeaders();
//		header.add("desc", "Returns a list of books by price "+price);
//		List<Book> books= bookService.getByPrice(price);
//		return ResponseEntity.ok().headers(header).body(books);
//	}

	//	List<Book> getByAuthorAndCategory(String author,String category){
	//		return bookService.getByAuthorAndCategory(author, category);
	//	}
	//	
	//	List<Book> getByTitleOrAuthor(String title,String author){
	//		
	//	}


	//custom query
//	@GetMapping("books/price/{price}/author/{author}")
//	ResponseEntity<List<Book>> getByPriceAuth(@PathVariable("price") double cost,@PathVariable("author") String author){
//		HttpHeaders header=new HttpHeaders();
//		header.add("desc", "Returns a list of books by price "+cost+" author "+author);
//		List<Book> books= bookService.getByPriceAuth(cost, author);
//		return ResponseEntity.ok().headers(header).body(books);
//	}
//
//	@GetMapping("books/category/{category}/author/{author}")
//	ResponseEntity<List<Book>> getByCatAndAuth(@PathVariable("category") String category,@PathVariable("author")String author){
//		HttpHeaders header=new HttpHeaders();
//		header.add("desc", "Returns a list of books by category "+category+" author "+author);
//		List<Book> books= bookService.getByCatAndAuth(category, author);
//		return ResponseEntity.ok().headers(header).body(books); 
//	}

	//	List<Book> getBookByAuth(String author){
	//		return bookService.getBookByAuth(author);
	//	}
	
	@GetMapping("/cart/add-to-cart/bookId/{bookId}")
	ResponseEntity<String> addToCart(@PathVariable("bookId")int bookId){
		cartService.addToCart(bookId);
		return ResponseEntity.ok().body("Added to cart");
	}
	
	@GetMapping("/cart/show-cart")
	ResponseEntity<Cart> showCart(){
		return ResponseEntity.ok().body(cartService.showCart());
		
	}

}
