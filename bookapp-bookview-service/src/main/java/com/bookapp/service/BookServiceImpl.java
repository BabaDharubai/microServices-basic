/**
 * 
 */
package com.bookapp.service;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bookapp.exceptions.BookNotFoundException;
import com.bookapp.exceptions.IdNotFoundException;
import com.bookapp.model.Book;

/**
 * @author BabaFakruddinDharuba
 *
 */
//from this service make a api call
@Service
public class BookServiceImpl implements IBookService {

	@Autowired
	private RestTemplate restTemplate;
	
//	private static final String BASEURL="http://fakruddin:8081/book-api/books";
	
	private static final String BASEURL="http://BOOK-SERVICE/book-api/books";
	
	@Autowired
	private DiscoveryClient client;
	
	@Override
	public List<Book> getByAuthor(String author) throws BookNotFoundException {
		String url=BASEURL.concat("/author/").concat(author);
		ResponseEntity<List> responseEntity=restTemplate.getForEntity(url, List.class);
		List<ServiceInstance> instances=client.getInstances("BOOk-SERVICE");
		for(ServiceInstance serviceInstance:instances) {
			int pport=serviceInstance.getPort();
			String host=serviceInstance.getHost();
			String instanceId=serviceInstance.getInstanceId();
			String uri=serviceInstance.getUri().getPath();
			
			System.out.println("port..."+pport);
			System.out.println("host..."+host);
			System.out.println("isntanceId..."+instanceId);
			System.out.println("uri..."+uri);
		}
		List<Book> books=responseEntity.getBody();
		return books;
	}

	@Override
	public List<Book> getByCategory(String category) throws BookNotFoundException {
		String url=BASEURL.concat("/category/?category=").concat(category);
		ResponseEntity<List> responseEntity=restTemplate.getForEntity(url, List.class);
		List<Book> books=responseEntity.getBody();
		return books;
	}

	@Override
	public Book getById(int bookid) throws IdNotFoundException {
		String url=BASEURL.concat("/bookId/")+bookid;
		ResponseEntity<Book> responseEntity=restTemplate.getForEntity(url, Book.class);
		Book book=responseEntity.getBody();
		return book;
	}

	@Override
	public List<Book> getAll() {
		// TODO Auto-generated method stub
		ResponseEntity<List> responseEntity=restTemplate.getForEntity(BASEURL, List.class);
		List<Book> books=responseEntity.getBody();
		
		return books;
	}

}
