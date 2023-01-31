/**
 * 
 */
package com.bookapp.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bookapp.exceptions.BookNotFoundException;
import com.bookapp.exceptions.IdNotFoundException;
import com.bookapp.model.Book;
import com.bookapp.repository.IBookRepository;

/**
 * @author BabaFakruddinDharuba
 *
 */
@Service
public class BookServiceImpl implements IBookService{

	private Logger logger=LoggerFactory.getLogger(BookServiceImpl.class);
	
	private IBookRepository bookRepository;
	
	/**
	 * @param bookRepository the bookRepository to set
	 */
	@Autowired
	public void setBookRepository(IBookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	@Override
	public void addBook(Book book) {
		//if the current id is not there it will create a new row
		logger.info("inside add book");
		bookRepository.save(book);
		logger.info("book added");
		
	}

	@Override
	public void updateBook(Book book) {
		//if id is there it will update
		//if id is not there it will create a new row
		bookRepository.save(book);
	}

	@Override
	public void deleteBook(int bookId) {
		logger.info("inside delete book");
		bookRepository.deleteById(bookId);
		
		logger.error("Invalid id");
	}

	@Override
	public Book getById(int bookid) {
		// TODO Auto-generated method stub
		Book book= bookRepository
				.findById(bookid)
				.orElseThrow(()->new IdNotFoundException("Invalid Id"));
		return book;
	}

	@Override
	public List<Book> getAll() {
		// TODO Auto-generated method stub
		logger.info("inside get all method book");
		return bookRepository.findAll();
	}

	@Override
	public List<Book> getByAuthor(String author) {
		// TODO Auto-generated method stub
		logger.info("inside getByAuthor method");
		List<Book> books=bookRepository.findByAuthor(author);
		if(books.isEmpty()) {
			logger.warn("Books not available "+author);
			throw new BookNotFoundException("Book Not Found");
		}
		logger.info("got books by author "+author);
		return books;
	}

	@Override
	public List<Book> getByCategory(String category) {
		// TODO Auto-generated method stub
		List<Book> books=bookRepository.readByCategory(category);
		if(books.isEmpty()) {
			logger.warn("books not available "+category);
			throw new BookNotFoundException("Book Not Found");
		}
		logger.info("got books by category "+category);
		return books;
	}

	@Override
	public List<Book> getByPrice(double price) {
		// TODO Auto-generated method stub
		return bookRepository.getByPriceLessThan(price);
	}

	@Override
	public List<Book> getByAuthorAndCategory(String author, String category) {
		// TODO Auto-generated method stub
		return bookRepository.findByAuthorAndCategory(author, category);
	}

	@Override
	public List<Book> getByTitleOrAuthor(String title, String author) {
		// TODO Auto-generated method stub
		return bookRepository.readByTitleOrAuthor(title, author);
	}

	
	//custom queries
	@Override
	public List<Book> getByPriceAuth(double cost, String author) {
		// TODO Auto-generated method stub
		return bookRepository.findByPriceAuth(cost, author);
	}

	@Override
	public List<Book> getByCatAndAuth(String category, String author) {
		// TODO Auto-generated method stub
		return bookRepository.findByCatAndAuth(category, author);
	}

	@Override
	public List<Book> getBookByAuth(String author) {
		// TODO Auto-generated method stub
		return bookRepository.findByAuth(author);
	}

	//native
	@Override
	public List<Book> getBookByCatPrice(String category,double price) {
		// TODO Auto-generated method stub
		return bookRepository.findBookByCatPrice(category,price);
	}

	@Override
	public int getTotalCount() {
		// TODO Auto-generated method stub
		return bookRepository.getTotalCount();
	}

	@Override
	public List<Book> getBookByMinPrice() {
		// TODO Auto-generated method stub
		return bookRepository.findByMinPrice();
	}

	@Override
	public List<Book> getBookByTitleAuth(String title, String author) {
		// TODO Auto-generated method stub
		return bookRepository.findBookByTitleAuth(title, author);
	}

	@Override
	@Transactional
	public void updateBook(int bookId, double price) {
		// TODO Auto-generated method stub
		bookRepository.updateBook(bookId, price);
		
	}

}
