/**
 * 
 */
package com.bookapp.service;

import java.util.List;

import com.bookapp.exceptions.BookNotFoundException;
import com.bookapp.model.Book;

/**
 * @author BabaFakruddinDharuba
 *
 */
public interface IBookService {
	void addBook(Book book);

	void updateBook(Book book);//check passing parameter

	void deleteBook(int bookId);

	Book getById(int bookid);

	List<Book> getAll();

	List<Book> getByAuthor(String author) throws BookNotFoundException;

	List<Book> getByCategory(String category);

	List<Book> getByPrice(double price);
	
	List<Book> getByAuthorAndCategory(String author,String category);
	
	List<Book> getByTitleOrAuthor(String title,String author);
	
	
	//custom query
	List<Book> getByPriceAuth(double cost,String author);
	
	List<Book> getByCatAndAuth(String category,String author);
	
	List<Book> getBookByAuth(String author);
	
	//native
	List<Book> getBookByCatPrice(String category,double price);
	
	int getTotalCount();
	
	
	List<Book> getBookByMinPrice();
	
	
	//named
	List<Book> getBookByTitleAuth(String title,String author);
	
	//use of modifying
	void updateBook(int bookId,double price);
}
