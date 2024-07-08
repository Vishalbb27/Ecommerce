package ecommerce.ecom.dto;

import java.math.BigDecimal;


public class ProductDto {
	private int productId;
	private String name;
	private String description;
	private BigDecimal price;
	private BigDecimal rating;
	private BigDecimal discount;
	private int category;
	private int quantity;
	private int quantity_sold;

	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public BigDecimal getRating() {
		return rating;
	}
	public void setRating(BigDecimal rating) {
		this.rating = rating;
	}
	public BigDecimal getDiscount() {
		return discount;
	}
	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}
	public int getCategory() {
		return category;
	}
	public void setCategory(int category) {
		this.category = category;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getQuantity_sold() {
		return quantity_sold;
	}
	public void setQuantity_sold(int quantity_sold) {
		this.quantity_sold = quantity_sold;
	}

	public ProductDto( String name, String description, BigDecimal price, BigDecimal rating,
			BigDecimal discount, int category, int quantity) {
		super();

		this.name = name;
		this.description = description;
		this.price = price;
		this.rating = rating;
		this.discount = discount;
		this.category = category;
		this.quantity = quantity;
	}
	public ProductDto() {
		super();
	}
	
	
}
