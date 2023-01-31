/**
 * 
 */
package com.bookapp.service;

import java.util.List;

import com.bookapp.exceptions.BookNotFoundException;
import com.bookapp.exceptions.IdNotFoundException;
import com.bookapp.model.Book;

/**
 * @author BabaFakruddinDharuba
 *
 */
public interface IBookService {

	//derived queries
	List<Book> getByAuthor(String author) throws BookNotFoundException;

	List<Book> getByCategory(String category)throws BookNotFoundException;
	
	Book getById(int bookid) throws IdNotFoundException;

	List<Book> getAll();
}
