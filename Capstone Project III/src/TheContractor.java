
public class TheContractor {   
    //Attributes:
    static String contractorName;
    static String contractorTelNum;
    static String contractorEmail;
    static String contractorPhyAdd;

    /**
     * @param contractorName - Builder Name
     * @param contractorTelNum - Builder Telephone Number
     * @param contractorEmail - Builder Email
     * @param contractorPhyAdd - Builder Physical Address
     */
    public TheContractor(String contractorName,
                              String contractorTelNum,
                              String contractorEmail,
                              String contractorPhyAdd) {
    	TheContractor.contractorName = contractorName;
    	TheContractor.contractorTelNum = contractorTelNum;           
        TheContractor.contractorEmail = contractorEmail;
        TheContractor.contractorPhyAdd = contractorPhyAdd;
    }

    /**
     * Create String to Display Object.
     * @return - Object toString.
     */
    @Override
	public String toString() {
        String output =
                "_________________________________________________________________________\n";
        output += "Contractor Details:";
        output += "\n______________________________________________________________________";
        output += "\nContractor Name:..........................  " + contractorName;
        output += "\nContractor Telephone Number:..............  " + contractorTelNum;
        output += "\nContractor Email Address:.................  " + contractorEmail;
        output += "\nContractor Physical Address:..............  " + contractorPhyAdd;
        output += "\n______________________________________________________________________";

        return output;
    }


    /**
     * Builder Details are sent here to convert data to String for writing
     * to ProjectFile.txt
     * @param contractorDetails - String Builder Details - for Project
     * @return - Data for PoisedProject.txt
     */
    public static String toTxtFile(String contractorDetails) {    
        contractorDetails += ("Contractor Details, ");
        contractorDetails += (contractorName + ", ");
        contractorDetails += (contractorTelNum + ", ");
        contractorDetails += (contractorEmail + ", ");
        contractorDetails += (contractorPhyAdd);

        return contractorDetails;
    }

}