/**
 * 
 */
package com.cartapp.model;

import java.util.List;

/**
 * @author BabaFakruddinDharuba
 *
 */
public class Cart {

	private Integer cartId;

	private List<Book> books;

	double totalcost;

	/**
	 * 
	 */
	public Cart() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cartId
	 * @param books
	 * @param totalcost
	 */
	public Cart(Integer cartId, List<Book> books, double totalcost) {
		super();
		this.cartId = cartId;
		this.books = books;
		this.totalcost = totalcost;
	}

	/**
	 * @return the cartId
	 */
	public Integer getCartId() {
		return cartId;
	}

	/**
	 * @param cartId the cartId to set
	 */
	public void setCartId(Integer cartId) {
		this.cartId = cartId;
	}

	/**
	 * @return the books
	 */
	public List<Book> getBooks() {
		return books;
	}

	/**
	 * @param books the books to set
	 */
	public void setBooks(List<Book> books) {
		this.books = books;
	}

	/**
	 * @return the totalcost
	 */
	public double getTotalcost() {
		return totalcost;
	}

	/**
	 * @param totalcost the totalcost to set
	 */
	public void setTotalcost(double totalcost) {
		this.totalcost = totalcost;
	}

	@Override
	public String toString() {
		return "Cart [cartId=" + cartId + ", books=" + books + ", totalcost=" + totalcost + "]";
	}

}
