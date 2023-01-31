/**
 * 
 */
package com.bookapp.controllers;

import java.util.ArrayList;
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

import com.bookapp.model.Book;
import com.bookapp.model.Cart;
import com.bookapp.service.ICartService;

/**
 * @author BabaFakruddinDharuba
 *
 */
@RestController
@RequestMapping("cart-api")
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
	
	
	
	Cart cart=new Cart();
	private List<Book> bookList=new ArrayList<>();
	private double totalCost;



	@GetMapping("/cart/bookId/{bookId}")
	ResponseEntity<Book> getById(@PathVariable("bookId") int bookid) {
		Book book=cartService.getById(bookid);
		HttpHeaders header=new HttpHeaders();
		header.add("desc", "get book by Id");
		return ResponseEntity.ok().headers(header).body(book);
	}

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

	
	@GetMapping("/cart/add-to-cart/bookId/{bookId}")
	ResponseEntity<String> addToCart(@PathVariable("bookId")int bookId){
//		cartService.addToCart(bookId);
		Book book=cartService.getById(bookId);
		cart.setCartId(1);
		bookList.add(book);
		cart.setBooks(bookList);
		totalCost+=book.getPrice();
		cart.setTotalcost(totalCost);
		System.out.println(cart);
		return ResponseEntity.ok().body("Added to cart");
	}
	
	@GetMapping("/cart/show-cart")
	ResponseEntity<Cart> showCart(){
		return ResponseEntity.ok().body(cart);
		
	}

}
