package hardwaresuppliersystem;


import java.io.Serializable;

/**
 * Description of a stock item
 * @author Lucas O'Sullivan
 */
public class Stock implements Serializable {
    
    private int stockID;
    private String title;
    private String referralCode;
    private String type;
	private double value;
	private static int count = 0;

	/**
	 * Stock no-argument constructor. Calls upon 5 mutators to initialise
	 * the attributes of a Stock object with default initial values;
	 * the Stock object is left in a consistent, initial state.
	 */
    public Stock (){
		setTitle("");
		setReferralCode("");
		setType("");
		setValue(0.0);
		setStockID(count);
		incrementCount();
	}

	/**
	 * Stock 4-argument constructor. Calls the 5 mutators to initialise the
	 * attributes of a Stock object with user-supplied values.
	 * Uses the Static Class variable "Count" to increment the "StockID" variable.
	 * @param title the title of a Stock object
	 * @param referralCode the referral code of a Stock object
	 * @param type the type (e.g. "Handtool," "Powertool") of a Stock object
	 * @param value the value (i.e. price) of the Stock object
	 */
	public Stock (String title, String referralCode, String type, double value){
		setTitle(title);
		setReferralCode(referralCode);
		setType(type);
		setValue(value);
		setStockID(count);
		incrementCount();

	}


	/**Method to increment the static count attribute of the class, in order
	 * to ensure that every Stock object will have a unique ID value, as it
	 * tracks the value of this attribute.
	 */
	private static void incrementCount() {
		count++;
	}

	/**
	 * Method to set the ID of a Stock object
	 * @param stockID the ID of the Stock object
	 */
	public void setStockID(int stockID){
		this.stockID = stockID;
	}

	/**
	 * Method to set the title of a Stock object
	 * @param title the title of the Stock object
	 */
	public void setTitle(String title){
		this.title = title;
	}

	/**
	 * Method to set the referral code of a Stock object
	 * @param referralCode the referral code of the Stock object
	 */
	public void setReferralCode(String referralCode){
		this.referralCode = referralCode;
	}

	/**
	 * Method to set the type of a Stock object
	 * @param type the type of the Stock object
	 */
	public void setType(String type){
		this.type = type;
	}

	/**
	 * Method to set the value of a Stock object
	 * @param value the value of the Stock object
	 */
	public void setValue(double value){
		this.value = value;
	}

	//--------------------------------------------------------

	/**
	 * Method to get the ID of a Stock object
	 * @return an integer entry specifying the ID of a Stock object
	 */
	public int getStockID(){
		return stockID;
	}

	/**
	 * Method to get the title of a Stock object
	 * @return a String entry specifying the title of a Stock object
	 */
	public String getTitle(){
	  return title;	
	}

	/**
	 * Method to get the referral code of a Stock object
	 * @return a String entry specifying the referral code of a Stock object
	 */
	public String getReferralCode(){
	  return referralCode;	
	}

	/**
	 * Method to get the type of a Stock object
	 * @return a String entry specifying the type of a Stock object
	 */
	public String getType(){
	  return type;	
	}

	/**
	 * Method to get the value of a Stock object
	 * @return a double entry specifying the value of a Stock object
	 */
	public double getValue(){
	  return value;	
	}

	//----------------------------------------

	/**
	 * Method to get the description of a Stock object using the above variables
	 * @return a String entry describing a Stock object
	 */
	public String toString() {
		return "ID: " + getStockID() + " Title: " + getTitle() + " Model: " + getReferralCode() + " Type: " + getType() + " Value: " + getValue();

    }

  }
