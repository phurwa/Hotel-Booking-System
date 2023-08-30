package library1;

public class MyLibs {
	//declare variables
	int customerID;
	String fullName;
	String address;
	String phoneNo;
	String emailID;
	String customerType;
	String cardNo;
	String password;
	String companyName;
	//default constructor
	public MyLibs() {
		this.customerID=0;
		this.fullName = "";
		this.address = "";
		this.phoneNo = "";
		this.emailID = "";
		this.customerType = "";
		this.cardNo = "";
		this.password = "";
		this.companyName = "";
	}
	//parameterized constructor
	public MyLibs(int customerID, String fullName, String address, String phoneNo, String emailID, String customerType,
			String cardNo, String password, String companyName) {
		this.customerID = customerID;
		this.fullName = fullName;
		this.address = address;
		this.phoneNo = phoneNo;
		this.emailID = emailID;
		this.customerType = customerType;
		this.cardNo = cardNo;
		this.password = password;
		this.companyName = companyName;
	}
	//setters and getters
	public int getCustomerID() {
		return customerID;
	}
	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getEmailID() {
		return emailID;
	}
	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}
	public String getCustomerType() {
		return customerType;
	}
	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	//to String
	@Override
	public String toString() {
		return "MyLibs [customerID=" + customerID + ", fullName=" + fullName + ", address=" + address + ", phoneNo="
				+ phoneNo + ", emailID=" + emailID + ", customerType=" + customerType + ", cardNo=" + cardNo
				+ ", password=" + password + ", companyName=" + companyName + "]";
	}

}
