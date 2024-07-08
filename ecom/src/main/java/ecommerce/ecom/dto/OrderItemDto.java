package ecommerce.ecom.dto;

import java.math.BigDecimal;


public class OrderItemDto {
	private int orderItemId;

	private int orderDetail;

	private ProductDto product;

	private int quantity;
	private BigDecimal discount;
	private BigDecimal total;
	public int getOrderItemId() {
		return orderItemId;
	}
	public void setOrderItemId(int orderItemId) {
		this.orderItemId = orderItemId;
	}
	public int getOrderDetail() {
		return orderDetail;
	}
	public void setOrderDetail(int orderDetail) {
		this.orderDetail = orderDetail;
	}
	public ProductDto getProduct() {
		return product;
	}
	public void setProduct(ProductDto product) {
		this.product = product;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public BigDecimal getDiscount() {
		return discount;
	}
	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	public OrderItemDto(int orderItemId, int orderDetail, ProductDto product, int quantity, BigDecimal discount,
			BigDecimal total) {
		super();
		this.orderItemId = orderItemId;
		this.orderDetail = orderDetail;
		this.product = product;
		this.quantity = quantity;
		this.discount = discount;
		this.total = total;
	}
	public OrderItemDto() {
		super();
	}
	
	
}
