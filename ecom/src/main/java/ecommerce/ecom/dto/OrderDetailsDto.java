package ecommerce.ecom.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class OrderDetailsDto {
	private int orderId;

	private int user;

	private LocalDate orderDate;
	private LocalTime orderTime;
	private String orderStatus;
	private BigDecimal totalPrice;
	private BigDecimal totalDiscount;

	private List<OrderItemDto> orderItems;

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getUser() {
		return user;
	}

	public void setUser(int user) {
		this.user = user;
	}

	public LocalDate getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}

	public LocalTime getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(LocalTime orderTime) {
		this.orderTime = orderTime;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public BigDecimal getTotalDiscount() {
		return totalDiscount;
	}

	public void setTotalDiscount(BigDecimal totalDiscount) {
		this.totalDiscount = totalDiscount;
	}

	public List<OrderItemDto> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItemDto> orderItems) {
		this.orderItems = orderItems;
	}

	

	public OrderDetailsDto() {
		super();
	}

	public OrderDetailsDto(int orderId2, int userId, LocalTime orderTime2, LocalDate orderDate2,
			List<OrderItemDto> orderItemDtos, BigDecimal totalDiscount2, BigDecimal totalPrice2, String orderStatus2) {
		super();
		this.orderId = orderId2;
		this.user = userId;
		this.orderDate = orderDate2;
		this.orderTime = orderTime2;
		this.orderStatus = orderStatus2;
		this.totalPrice = totalPrice2;
		this.totalDiscount = totalDiscount2;
		this.orderItems = orderItemDtos;
	}

	
	
	
}
