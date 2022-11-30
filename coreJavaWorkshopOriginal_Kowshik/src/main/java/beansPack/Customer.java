package beansPack;

import java.math.BigDecimal;

public class Customer extends Address{

	private int customerId;
	private String customerName;
	private BigDecimal customerPhone;
	private String customerUsername;
	private String customerPassword;
	
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public BigDecimal getCustomerPhone() {
		return customerPhone;
	}
	public void setCustomerPhone(BigDecimal customerPhone) {
		this.customerPhone = customerPhone;
	}
	public String getCustomerUsername() {
		return customerUsername;
	}
	public void setCustomerUsername(String customerUsername) {
		this.customerUsername = customerUsername;
	}
	public String getCustomerPassword() {
		return customerPassword;
	}
	public void setCustomerPassword(String customerPassword) {
		this.customerPassword = customerPassword;
	}
}
