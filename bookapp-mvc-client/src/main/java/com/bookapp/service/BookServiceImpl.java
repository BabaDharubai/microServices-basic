/**
 * 
 */
package com.bookapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bookapp.exceptions.BookNotFoundException;
import com.bookapp.model.Book;
import com.bookapp.model.Cart;

/**
 * @author BabaFakruddinDharuba
 *
 */
@Service
public class BookServiceImpl implements IBookService{

	@Autowired
	private RestTemplate restTemplate;
	
	private static final String BASEURL="http://localhost:9000/";
	private static final String BOOKAPI="book-view/vbooks";
	private static final String CARTAPI="cart-api/cart";
	
	
	@Override
	public Book getById(int bookid) {
		String url=BASEURL.concat(BOOKAPI).concat("/bookId/")+bookid;
		ResponseEntity<Book> responseEntity=restTemplate.getForEntity(url, Book.class);
		Book book=responseEntity.getBody();
		return book;
	}

	@Override
	public List<Book> getAll() {
		// TODO Auto-generated method stub
		String url=BASEURL.concat(BOOKAPI);
		ResponseEntity<List> responseEntity=restTemplate.getForEntity(url, List.class);
		List<Book> books=responseEntity.getBody();
		return books;
		
	}

	@Override
	public List<Book> getByAuthor(String author) throws BookNotFoundException {
//		// TODO Auto-generated method stub
		String url=BASEURL.concat(CARTAPI).concat("/author/").concat(author);
		ResponseEntity<List> responseEntity=restTemplate.getForEntity(url, List.class);
		List<Book> books=responseEntity.getBody();
		return books;
	}

	@Override
	public void addToCart(int bookId) {
		// TODO Auto-generated method stub
		String url=BASEURL.concat(CARTAPI).concat("add-to-cart/bookId/")+bookId;
		restTemplate.optionsForAllow(url );
	}

	@Override
	public Cart showCart() {
		// TODO Auto-generated method stub
		String url=BASEURL.concat(CARTAPI).concat("/show-cart");
		ResponseEntity<Cart> responseEntity=restTemplate.getForEntity(url,Cart.class);
		Cart cart=responseEntity.getBody();
		return cart;
	}

}
