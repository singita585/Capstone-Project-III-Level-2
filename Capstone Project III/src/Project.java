public class Project {
	  //Attributes:
	  //static
    static int proNumber;        
    static String proName;
    static String proType;
    static String proPhysAdd;
    static int proERF;
    static float proTotFee;
    static float proTotPaid;
    static String proDeadline;
	


    /**
     * @param proNumber - Project Number.
     * @param proName - Project Name.
     * @param proType - Building/ Project Type.
     * @param proPhysAdd - Project Physical Address.
     * @param proERF - Project ERF Number.
     * @param proTotFee - Project Total Fee.
     * @param proTotPaid - Project Total Fee Paid to Date.
     * @param proDeadline - Project Deadline.
     */
    public Project(int proNumber, String proName, String proType, String proPhysAdd, int proERF, float proTotFee, float proTotPaid, String proDeadline) {
        Project.proNumber = proNumber;        // CreateNewProject
        Project.proName = proName;
        Project.proType = proType;
        Project.proPhysAdd = proPhysAdd;
        Project.proERF = proERF;
        Project.proTotFee = proTotFee;
        Project.proTotPaid = proTotPaid;
        Project.proDeadline = proDeadline;
    }

    /**
     * Create String to Display Object.
     * @return - Object toString.
     */
    @Override
	public String toString() {
        String output =
                "______________________________________________________________________\n";
        output += "Project Details:";
        output += "\n______________________________________________________________________\n";

        output += "Project Number:........................  " + proNumber;

        if (proName.isEmpty()) {
            proName = proType + " " + TheCustomer.customerName;      
            output += "\nProject Name:..........................  " + proName;
        }
        output += "\nProject Name:..........................  " + proName;
        output += "\nProject Building Type:.................  " + proType;
        output += "\nProject Physical Address:..............  " + proPhysAdd;
        output += "\nProject ERF Number:....................  " + proERF;
        output += "\nProject Total Fee:.....................  " + proTotFee;
        output += "\nProject Total Amount Paid To Date:.....  " + proTotPaid;
        output += "\nProject Deadline:......................  " + proDeadline;
        output += "\n______________________________________________________________________";

        return output;
    }

    /**
     * Project Details are sent here to convert data to String for writing
     * to ProjectFile.txt
     * @param projectDetails - String Project Details - for Project
     * @return - Data for ProjectFile.txt
     */
    public static String toTxtFile(String projectDetails) {  
        projectDetails += ("Project Details, ");
        projectDetails += (proNumber + ", ");
        projectDetails += (proName + ", ");
        projectDetails += (proType + ", ");
        projectDetails += (proPhysAdd + ", ");
        projectDetails += (proERF + ", ");
        projectDetails += (proTotFee + ", ");
        projectDetails += (proTotPaid + ", ");
        projectDetails += (proDeadline);

        return projectDetails;
    }

}