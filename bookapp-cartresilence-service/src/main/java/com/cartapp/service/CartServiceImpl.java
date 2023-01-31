/**
 * 
 */
package com.cartapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cartapp.exceptions.BookNotFoundException;
import com.cartapp.exceptions.IdNotFoundException;
import com.cartapp.model.Book;
import com.cartapp.model.Cart;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

/**
 * @author BabaFakruddinDharuba
 *
 */
@Service
public class CartServiceImpl implements ICartService {
	
	
	
	@Autowired
	private RestTemplate restTemplate;
	
//	private static final String BASEURL="http://fakruddin:8082/book-view/vbooks";
	
	private static final String BASEURL="http://BOOK-VIEW-SERVICE/book-view/vbooks";
	
	Cart cart=new Cart();
	private List<Book> bookList=new ArrayList<>();
	private double totalCost;

	@CircuitBreaker(name="CART_SERVICE",fallbackMethod = "fallbackGetByAuthor")
	@Override
	public List<Book> getByAuthor(String author) throws BookNotFoundException {
		// TODO Auto-generated method stub
		String url=BASEURL.concat("/author/").concat(author);
		ResponseEntity<List> responseEntity=restTemplate.getForEntity(url, List.class);
		List<Book> books=responseEntity.getBody();
		return books;
	}

	//fallback method should have the same method signature + exception as second parameter
	public List<Book> fallbackGetByAuthor(String author,Exception ex){
		return new ArrayList<>();
	}
	
	
	
	@CircuitBreaker(name = "CART_SERVICE",fallbackMethod = "fallbackGetById")
	@Override
	public Book getById(int bookid) throws IdNotFoundException {
		// TODO Auto-generated method stub
		String url=BASEURL.concat("/bookId/")+bookid;
		ResponseEntity<Book> responseEntity=restTemplate.getForEntity(url, Book.class);
		Book book=responseEntity.getBody();
		return book;
	}
	
	public Book fallbackGetById(int bookid,Exception ex) {
		return new Book();
	}

	@Override
	public void addToCart(int bookId) {
		// TODO Auto-generated method stub
		String url=BASEURL.concat("/bookId/")+bookId;
		ResponseEntity<Book> responseEntity=restTemplate.getForEntity(url, Book.class);
		Book book=responseEntity.getBody();
		cart.setCartId(1);
		bookList.add(book);
		cart.setBooks(bookList);
		totalCost+=book.getPrice();
		cart.setTotalcost(totalCost);
	}

	@Override
	public Cart showCart() {
		// TODO Auto-generated method stub
		return cart;
	}

}
