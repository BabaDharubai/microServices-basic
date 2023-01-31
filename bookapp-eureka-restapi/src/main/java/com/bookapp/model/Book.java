/**
 * 
 */
package com.bookapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 * @author BabaFakruddinDharuba
 *
 */
//jpa converts this class into table
@NamedQueries({@NamedQuery(name="findByTitleAuth",query="from Book b where b.title=?1 and b.author=?2"),
	@NamedQuery(name="findByCost",query="from Book b where b.price=?1")})
@NamedNativeQueries({
	@NamedNativeQuery(name="findTotalCount",query="select count(*) from book"),
	@NamedNativeQuery(name="findMinPriceBook",query="select * from book where cost=(select min(cost) from book)")
})
@Entity
public class Book {
	//informs hibernate that this is the primary key
	@Id
	private Integer bookId;
	private String title;
	private String author;
	private String category;
	@Column(name="cost")
	private double price;
	
	
	
	/**
	 * 
	 */
	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	/**
	 * @param bookId
	 * @param title
	 * @param author
	 * @param category
	 * @param price
	 */
	public Book(Integer bookId, String title, String author, String category, double price) {
		super();
		this.bookId = bookId;
		this.title = title;
		this.author = author;
		this.category = category;
		this.price = price;
	}



	/**
	 * @return the bookId
	 */
	public Integer getBookId() {
		return bookId;
	}
	/**
	 * @param bookId the bookId to set
	 */
	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the author
	 */
	public String getAuthor() {
		return author;
	}
	/**
	 * @param author the author to set
	 */
	public void setAuthor(String author) {
		this.author = author;
	}
	/**
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}
	/**
	 * @param category the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}
	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}
	
	@Override
	public String toString() {
		return "Book [bookId=" + bookId + ", title=" + title + ", author=" + author + ", category=" + category
				+ ", price=" + price + "]";
	}
	
}
