package in.co.rays.bean;

import java.util.Date;

public class CustomerBean {

	private int id;
	private String name;
	private String amount;
	private String accoountNumber;
	private Date dob;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getAccoountNumber() {
		return accoountNumber;
	}
	public void setAccoountNumber(String accoountNumber) {
		this.accoountNumber = accoountNumber;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	private String customerId;

}
