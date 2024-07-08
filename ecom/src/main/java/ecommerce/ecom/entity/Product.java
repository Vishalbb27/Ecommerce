package ecommerce.ecom.entity;

import java.math.BigDecimal;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int productId;
	private String name;
	private String description;
	private BigDecimal price;
	private BigDecimal rating;
	private BigDecimal discount;

	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;

	private int quantity;
	private int quantity_sold;
	public int getQuantity_sold() {
		return quantity_sold;
	}

	public void setQuantity_sold(int quantity_sold) {
		this.quantity_sold = quantity_sold;
	}

	@Column(columnDefinition = "BLOB(209715232)")
	private byte[] image;

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

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}



	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public Product(String name, String description, BigDecimal price, BigDecimal rating, BigDecimal discount,
			Category category, int quantity, byte[] image) {
		super();
		this.name = name;
		this.description = description;
		this.price = price;
		this.rating = rating;
		this.discount = discount;
		this.category = category;
		this.quantity = quantity;
		this.image = image;
	}

	public Product() {
		super();
	}

}
