
public class TheArchitect {
    //Attributes
    static String archName;
    static String archTelNum;
    static String archEmail;
    static String archPhyAdd;
	


    /**
     * @param archName   - Architect Name
     * @param archTelNum - Architect Telephone Number
     * @param archEmail  - Architect Email
     * @param archPhyAdd - Architect Physical Address
     */
    public TheArchitect(String archName,
                        String archTelNum,
                        String archEmail,
                        String archPhyAdd) {
        TheArchitect.archName = archName;
        TheArchitect.archTelNum = archTelNum;
        TheArchitect.archEmail = archEmail;
        TheArchitect.archPhyAdd = archPhyAdd;
    }

    /**
     * Create String to Display Object.
     * @return - Object toString.
     */
    @Override
	public String toString() {
        String output =
                "______________________________________________________________________\n";
        output += "Project Architect:";
        output += "\n______________________________________________________________________";
        output += "\nArchitect Name:........................  " + archName;
        output += "\nArchitect Telephone Number:............  " + archTelNum;
        output += "\nArchitect Email Address:...............  " + archEmail;
        output += "\nArchitect Physical Address:............  " + archPhyAdd;
        output += "\n______________________________________________________________________";

        return output;
    }

    /**
     * Architect Details are sent here to convert data to String for writing
     * to ProjectFile.txt
     * @param architectDetails - String Architect Details - for Project
     * @return - Data for ProjectFile.txt
     */
    public static String toTxtFile(String architectDetails) { 


        // Architect Details: List:
        architectDetails += ("Architect Details, ");
        architectDetails += (archName + ", ");
        architectDetails += (archTelNum + ", ");
        architectDetails += (archEmail + ", ");
        architectDetails += (archPhyAdd);

        return architectDetails;

    }

}