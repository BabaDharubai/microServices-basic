/**
 * 
 */
package com.bookapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bookapp.model.Book;

/**
 * @author BabaFakruddinDharubai
 *
 */
@Repository
public interface IBookRepository extends JpaRepository<Book, Integer> {

	//derived queries
	List<Book> findByAuthor(String author);
	
	List<Book> readByCategory(String category);
	
	List<Book> getByPriceLessThan(double price);
	
	List<Book> findByAuthorAndCategory(String author,String category);
	
	List<Book> readByTitleOrAuthor(String title,String author);
	
	
	//custom queries
	//provide JPQL
	//this is the entity name not the table name
	@Query("from Book b where b.price=?1 and b.author=?2")
	List<Book> findByPriceAuth(double cost,String author);
	
	@Query("from Book b where b.category=?1 and b.author=?2")
	List<Book> findByCatAndAuth(String category,String author);
	
	@Query("from Book b where b.author=?1")
	List<Book> findByAuth(String author);
	
	
	//native query
	@Query(value="select * from book where category=?1 and cost=?2",nativeQuery=true)
	List<Book> findBookByCatPrice(String category,double price);
	
	//named native query
//	@Query(value="select count(*) from book",nativeQuery=true)
	@Query(name="findTotalCount")
	int getTotalCount();
	
	@Query(value="select min(cost) from book",nativeQuery=true)
	int minPrice();
	
//	@Query(value="select * from book where cost=(select min(cost) from book)",nativeQuery=true)
	@Query(name="findMinPriceBook")
	List<Book> findByMinPrice();
	
	
	//NamedQuery
	@Query(name="findByTitleAuth")
	List<Book> findBookByTitleAuth(String title,String author);
	
	@Modifying
	@Query(value="update book set cost=?2 where book_Id=?1",nativeQuery=true)
	void updateBook(int bookId,double price);
	
}
