package com.bookapp.service;

import java.util.List;

import com.bookapp.exceptions.BookNotFoundException;
import com.bookapp.exceptions.IdNotFoundException;
import com.bookapp.model.Book;
import com.bookapp.model.Cart;

public interface ICartService {
	
	List<Book> getByAuthor(String author) throws BookNotFoundException;
	Book getById(int bookid) throws IdNotFoundException;
	
	void addToCart(int bookId);
	Cart showCart();
}
