
public class TheCustomer { 
	
    
	//Customer Attributes:
    static String customerName;
    static String customerTelNum;
    static String customerEmail;
    static String customerPhyAdd;

    /**
     * @param customerName   - Customer Name
     * @param customerTelNum - Customer Telephone Number
     * @param customerEmail  - Customer Email
     * @param customerPhyAdd - Customer Physical Address
     */
    public TheCustomer(String customerName,
                       String customerTelNum,
                       String customerEmail,
                       String customerPhyAdd) {
    	TheCustomer.customerName = customerName;
    	TheCustomer.customerTelNum = customerTelNum;
    	TheCustomer.customerEmail = customerEmail;
        TheCustomer.customerPhyAdd = customerPhyAdd;    // Customer.customerPhyAdd = customerPhyAdd;
    }

    /**
     * Create String to Display Object.
     *
     * @return - Object toString
     */
    @Override
	public String toString() {
        String output =
                "______________________________________________________________________\n";
        output += "Customer Details:";
        output += "\n______________________________________________________________________";
        output += "\nCustomer Name:.........................  " + customerName;
        output += "\nCustomer Telephone Number:.............  " + customerTelNum;
        output += "\nCustomer Email Address:................  " + customerEmail;
        output += "\nCustomer Physical Address:.............  " + customerPhyAdd;
        output += "\n______________________________________________________________________";

        return output;
    }

    /**
     * Customer Details are sent here to convert data to String for writing
     * to ProjectFile.txt
     *
     * @param customerDetails - String Customer Details for Project.
     * @return - Data for ProjectFile.txt
     */
    public static String toTxtFile(String customerDetails) {   
        customerDetails += ("Customer Details, ");
        customerDetails += (customerName + ", ");
        customerDetails += (customerTelNum + ", ");
        customerDetails += (customerEmail + ", ");
        customerDetails += (customerPhyAdd);

        return customerDetails;
    }

}