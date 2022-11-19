package old.hardwaresuppliersystem;

public class Customer {
    private int customerID;
    private String name;
    private String dateOfBirth;
    private String shippingAddressLine1;
    private String shippingAddressLine2;
    private String shippingAddressCity;
    private String shippingAddressCounty;
    private String shippingAddressEirCode;
    private String billingAddressLine1;
    private String billingAddressLine2;
    private String billingAddressCity;
    private String billingAddressCounty;
    private String billingAddressEirCode;
    private String eMailAddress;
    private long mobileNumber;
    private long landlineNumber;
    private long creditCardNumber;
    private String creditCardExpiryDate;
    private int creditCardSecurityCode;
    
    public Customer (int customerID, String name, String dateOfBirth, 
    	             String shippingAddressLine1, String shippingAddressLine2, String shippingAddressCity, String shippingAddressCounty, String shippingAddressEirCode,
                     String billingAddressLine1, String billingAddressLine2, String billingAddressCity, String billingAddressCounty, String billingAddressEirCode,
                     String eMailAddress, long mobileNumber, long landlineNumber, long creditCardNumber, String creditCardExpiryDate, int creditCardSecurityCode){
                    
                    customerID++;
                    setName(name);
                    setDateOfBirth(dateOfBirth);
                    
                    setShippingAddressLine1(shippingAddressLine1);
                    setShippingAddressLine2(shippingAddressLine2);
                    setShippingAddressCity(shippingAddressCity);
                    setShippingAddressCounty(shippingAddressCounty);
                    setShippingAddressEirCode(shippingAddressEirCode);
                    
                    setBillingAddressLine1(billingAddressLine1);
                    setBillingAddressLine2(billingAddressLine2);
                    setBillingAddressCity(billingAddressCity);
                    setBillingAddressCounty(billingAddressCounty);
                    setBillingAddressEirCode(billingAddressEirCode);
                    
                    setEMailAddress(eMailAddress);
                    setMobileNumber(mobileNumber);
                    setLandlineNumber(landlineNumber);
                    
                    setCreditCardNumber(creditCardNumber);
                    setCreditCardExpiryDate(creditCardExpiryDate);
                    setCreditCardSecurityCode(creditCardSecurityCode);
                    
                     }
                     
                    /*
                    customerID++;
                    setName();
                    setDateOfBirth();
                    
                    setShippingAddressLine1();
                    setShippingAddressLine2();
                    setShippingAddressCity();
                    setShippingAddressCounty();
                    setShippingAddressEirCode();
                    
                    setBillingAddressLine1();
                    setBillingAddressLine2();
                    setBillingAddressCity();
                    setBillingAddressCounty();
                    setBillingAddressEirCode();
                    
                    setEMailAddress();
                    setMobileNumber();
                    setLandlineNumber();
                    
                    setCreditCardNumber();
                    setCreditCardExpiryDate();
                    setCreditCardSecurityCode();

                    
                     }
                     */



    private void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public void setName(String name) {
        this.name = name; 
    }
    
    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth; 
    }
    
    public void setShippingAddressLine1(String shippingAddressLine1) {
        this.shippingAddressLine1 = shippingAddressLine1; 
    }
    public void setShippingAddressLine2(String shippingAddressLine2) {
        this.shippingAddressLine2 = shippingAddressLine2; 
    }
    public void setShippingAddressCity(String shippingAddressCity) {
        this.shippingAddressCity = shippingAddressCity; 
    }
    public void setShippingAddressCounty(String shippingAddressCounty) {
        this.shippingAddressCounty = shippingAddressCounty; 
    }
    public void setShippingAddressEirCode(String shippingAddressEirCode) {
        this.shippingAddressEirCode = shippingAddressEirCode; 
    }
    
    public void setBillingAddressLine1(String billingAddressLine1) {
        this.billingAddressLine1 = billingAddressLine1; 
    }
    public void setBillingAddressLine2(String billingAddressLine2) {
        this.billingAddressLine2 = billingAddressLine2; 
    }
    public void setBillingAddressCity(String billingAddressCity) {
        this.billingAddressCity = billingAddressCity; 
    }
    public void setBillingAddressCounty(String billingAddressCounty) {
        this.billingAddressCounty = billingAddressCounty;
    }
    public void setBillingAddressEirCode(String billingAddressEirCode) {
        this.billingAddressEirCode = billingAddressEirCode; 
    }
    
    public void setEMailAddress(String eMailAddress) {
    	this.eMailAddress = eMailAddress;
    }
    
    public void setMobileNumber(long mobileNumber) {
        this.mobileNumber = mobileNumber; 
    }
    
    public void setLandlineNumber(long landlineNumber) {
        this.landlineNumber = landlineNumber; 
    }
    
    public void setCreditCardNumber(long creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }
    
    public void setCreditCardExpiryDate(String creditCardExpiryDate) {
        this.creditCardExpiryDate = creditCardExpiryDate;
    }
    
    public void setCreditCardSecurityCode(int creditCardSecurityCode) {
        this.creditCardSecurityCode = creditCardSecurityCode;
    }
    
    
    private int getCustomerID(){
    	return customerID;
    }
    public String getName() {
        return name;
    }
    
    public String getDateOfBirth() {
        return dateOfBirth;
    }
    
    public String getShippingAddressLine1() {
        return shippingAddressLine1;
    }
    public String getShippingAddressLine2() {
        return shippingAddressLine2;
    }
    public String getShippingAddressCity() {
        return shippingAddressCity;
    }
    public String getShippingAddressCounty() {
        return shippingAddressCounty;
    }
    public String getShippingAddressEirCode() {
        return shippingAddressEirCode;
    }
    
    public String getBillingAddressLine1() {
        return billingAddressLine1;
    }
    public String getBillingAddressLine2() {
        return billingAddressLine2;
    }
    public String getBillingAddressCity() {
        return billingAddressCity;
    }
    public String getBillingAddressCounty() {
        return billingAddressCounty;
    }
    public String getBillingAddressEirCode() {
        return billingAddressEirCode;
    }
    
    public String getEMailAddress() {
    	return eMailAddress;
    }
    
    public long getMobileNumber() {
        return mobileNumber;
    }
    
    public long getLandlineNumber() {
        return landlineNumber;
    }
    
    public long getCreditCardNumber() {
        return creditCardNumber;
    }
    
    public String getCreditCardExpiryDate() {
        return creditCardExpiryDate;
    }
    
    public int getCreditCardSecurityCode() {
        return creditCardSecurityCode;
    }
    

    public String toString() {
        return "Customer Details" + "Customer ID: " + getCustomerID() + "Name: " + getName() + "Date of Birth: " + getDateOfBirth() + 
        	   
        	   "n/Shipping Address" + 
        	   "Line 1: " + getShippingAddressLine1() + "Line 2: " + getShippingAddressLine2() + 
        	   "City: " + getShippingAddressCity() + "County: " + getShippingAddressCounty() + 
        	   "EirCode: " + getShippingAddressEirCode() +
        	   	
        	   "n/Billing Address" + 
        	   "Line 1: " + getBillingAddressLine1() + "Line 2: " + getBillingAddressLine2() + 
        	   "City: " + getBillingAddressCity() + "County: " + getBillingAddressCounty() + 
        	   "EirCode: " + getBillingAddressEirCode() + "n/E-Mail Address: " + getEMailAddress() +
        	   
        	   "n/Contact Details" +	
        	   "Mobile Number: " + getMobileNumber() + "Landline Number: " + getLandlineNumber() + 
        	   	
        	   "n/Credit Card Information" + "n/E-Mail Address: " + getEMailAddress() + "Card Number: " + getCreditCardNumber() + "Expiry Date: " + getCreditCardExpiryDate() 
        	   	+ "Security Code: " + getCreditCardSecurityCode();
        	}
		
	

	
}
