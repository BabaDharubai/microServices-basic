/**
 * 
 */
package com.bookapp.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bookapp.model.Book;
import com.bookapp.model.Cart;
import com.bookapp.service.IBookService;

/**
 * @author BabaFakruddinDharuba
 *
 */
@Controller
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


	@RequestMapping("search-Id")
	public String getById(@RequestParam("param") int bookid,Model model) {
		Book book=bookService.getById(bookid);
		model.addAttribute("books", book);
		return "home";
	}

	@RequestMapping("/")
	public String getAll(Model model){
		System.out.println("Hi Baba");
		List<Book> books=bookService.getAll();
		model.addAttribute("books", books);
		return "home";
	}

	@RequestMapping("search-author")
	public String getByAuthor(@RequestParam("param")String author,Model model){
		List<Book> books=bookService.getByAuthor(author);
		model.addAttribute("books", books);
		return "home";
	}
	
	@RequestMapping("cart")
	public String showCart(Model model) {
		Cart cart=bookService.showCart();
		model.addAttribute("books",cart);
		return "home";
	}
	
//	@RequestMapping("add-to-cart")
//	public String addTocart(@RequestParam("choice"),int bookId) {
//		bookService.addToCart(int bookId)
//		model.addAttribute("books",cart);
//		return "home";
//	}
}
