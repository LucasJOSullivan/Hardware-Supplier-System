package old.hardwaresuppliersystem;


public class StockItem {
    
    private int stockID;
    private String title;
    private String referralCode;
    private String type;
	private double value;

	
	public StockItem(int stockID, String title, String referralCode, String type, double value){
  	    setStockID(stockID);
		setTitle(title);
		setReferralCode(referralCode);
		setType(type);
		setValue(value);
		
	}
	
	public void setStockID (int StockID){
		this.stockID = stockID;
	}

	public void setTitle(String title){
		this.title = title;
	}
	
	public void setReferralCode(String referralCode){
		this.referralCode = referralCode;
	}
	
	public void setValue(double value){
		this.value = value;
	}
	
	public void setType(String type){
		this.type = type;
	}
	

	
	
	public int getStockID(){
		return stockID;
	}
	public String getTitle(){
	  return title;	
	}
	
	public String getReferralCode(){
	  return referralCode;	
	}

	public String getType(){
	  return type;	
	}
	
	public double getValue(){
	  return value;	
	}
		
	public String toString() {
		return "ID: " + getStockID() + "Title: " + getTitle() + "Model: " + getReferralCode() + "Type: " + getType() + "Value: " + getValue();

    }
		
	

  }
