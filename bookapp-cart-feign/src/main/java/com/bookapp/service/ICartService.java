package com.bookapp.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.bookapp.exceptions.BookNotFoundException;
import com.bookapp.exceptions.IdNotFoundException;
import com.bookapp.model.Book;

//this the client for bookview-service
//this behaves as a declarative client and the implementation
//is created by spring during runtime
//give the name of the service to connect with

@FeignClient(value = "BOOK-VIEW-SERVICE")
public interface ICartService {
	
	
	@GetMapping("/book-view/vbooks/author/{author}")
	List<Book> getByAuthor(@PathVariable("author") String author) throws BookNotFoundException;
	
	@GetMapping("/book-view/vbooks/bookId/{bookId}")
	Book getById(@PathVariable("bookId")int bookid) throws IdNotFoundException;
	
}
