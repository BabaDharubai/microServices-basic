package com.cartapp.service;

import java.util.List;

import com.cartapp.exceptions.BookNotFoundException;
import com.cartapp.exceptions.IdNotFoundException;
import com.cartapp.model.Book;
import com.cartapp.model.Cart;

public interface ICartService {
	
	List<Book> getByAuthor(String author) throws BookNotFoundException;
	Book getById(int bookid) throws IdNotFoundException;
	
	void addToCart(int bookId);
	Cart showCart();
}
