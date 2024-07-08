package ecommerce.ecom.entity;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class OrderItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int orderItemId;

	@ManyToOne
	@JoinColumn(name = "order_id") // Specify the referenced column name
	private OrderDetails orderDetail;

	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;

	private int quantity;
	private BigDecimal discount;
	private BigDecimal total;

	public OrderItem(OrderDetails orderDetail, Product product, int quantity, BigDecimal discount, BigDecimal total) {
		super();
		this.orderDetail = orderDetail;
		this.product = product;
		this.quantity = quantity;
		this.discount = discount;
		this.total = total;
	}

	public OrderItem() {
		super();
	}

	public int getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(int orderItemId) {
		this.orderItemId = orderItemId;
	}

	public OrderDetails getOrderDetail() {
		return orderDetail;
	}

	public void setOrderDetail(OrderDetails orderDetail) {
		this.orderDetail = orderDetail;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getPrice() {
		return discount;
	}

	public void setPrice(BigDecimal price) {
		this.discount = price;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

}
