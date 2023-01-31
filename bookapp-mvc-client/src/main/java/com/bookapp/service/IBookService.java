/**
 * 
 */
package com.bookapp.service;

import java.util.List;

import com.bookapp.exceptions.BookNotFoundException;
import com.bookapp.model.Book;
import com.bookapp.model.Cart;

/**
 * @author BabaFakruddinDharuba
 *
 */
public interface IBookService {
	
	
	//book-view
	Book getById(int bookid);

	List<Book> getAll();

	List<Book> getByAuthor(String author) throws BookNotFoundException;
	
	//cart
	void addToCart(int bookId);
	
	Cart showCart();

}
