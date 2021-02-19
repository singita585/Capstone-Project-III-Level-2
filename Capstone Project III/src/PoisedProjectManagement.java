import java.io.*;
import java.text.*;
import java.time.LocalDate;
import java.util.*;

public class PoisedProjectManagement {
	
	/** Request Main menu option. */
    public static void getMenu() {
        // Title of Method:
        System.out.println("Main Menu:");
        System.out.println("_________________________________________\n");

        // Set Scanner:
        Scanner menuScanner = new Scanner(System.in);

        // Main Menu Options:
        System.out.print("Please select from the following options below:\n" +
        		 "1. Create New Project\n" +
                 "2. Update Existing Project\n" +
                 "3. Update Contractor Details\n" +
                 "4. Finalise Existing Project\n" +
                 "5. View All Complete Projects \n" +
                 "6. View All Incomplete Projects\n" +
                 "7. View All Overdue Projects\n" +
                 "8. Find Existing Project\n" +
                 "9. Exit\n" +
                 ": "
             );
        // Get User's menu choice:
        int menuChoice = Integer.parseInt(menuScanner.nextLine());

        System.out.println("______________________________________\n");

        // Return User's to Menu Execute with Scanner & menuChoice as parameters:
        menuExe(menuScanner, menuChoice);
    }

    /**
     * Executes Main menu choice and returns choice to method.
     *
     * @param menuProjectScanner - Scanner for menu.
     * @param userMenuChoice - int for user input on menu choice.
     */
    public static void menuExe(Scanner menuProjectScanner, int userMenuChoice) { 
        // try: Handle Input:
        try {
            switch (userMenuChoice) {
                // Create Project:
                case 1:
                    {
                        System.out.println("New Project Customer:\n");
                        newProjectCustomer(menuProjectScanner);
                        break;
                    }
                    // Update Existing Project:
                case 2:
                    {
                        System.out.println("Update Existing Project:\n");
                        updateProject(menuProjectScanner);
                        break;
                    }
                    // Update Contractor Details:
                case 3:
                    {
                        System.out.println("Update Contractor Contact Details:\n");
                        updateContractContacts(menuProjectScanner);
                        break;
                    }
                    // Finalise Existing Project:
                case 4:
                    {
                        projectFinaliseNumber(menuProjectScanner);
                        break;
                    }
                    // View ALl Completed Projects:
                case 5:
                    {
                        System.out.println("View All Completed Projects:\n");
                        viewCompletedProjects();
                        break;
                    }
                    // View All Incomplete Projects:
                case 6:
                    {
                        System.out.println("View Incomplete Projects:\n");
                        viewIncompleteProjects(menuProjectScanner);
                        break;
                    }
                    // View All Overdue Projects:
                case 7:
                    {
                        System.out.println("Overdue Projects:\n");
                        overdueProjects();
                        break;
                    }
                    // Find Existing Project:
                case 8:
                    {
                        findExistingProject(menuProjectScanner);
                        break;
                    }
                    // Exit:
                case 9:
                    {
                        // Close Scanner:
                        menuProjectScanner.close();

                        System.out.println("Thank you for using our System");
                        System.exit(0);
                    }
                    // else: throw Exception Error:
                default:
                    {
                        throw new InputMismatchException();
                    }
            }
        }
        // catch: Exception Error:
        catch (InputMismatchException exception) {
            System.out.println("Invalid Input...\n" +
                "Enter Option Number (1-8).\n" +
                "Please make sure enter the correct details & try again:\n");
            // return to getMenu:
            getMenu();
        }
    }

    /**
     * User input for customer details.
     * uses writeProject method to write data to txt file.
     *
     * @param customerScanner - Scanner for Customer
     */
    // New Customer: -- > Uses: WriteProject:
    public static void newProjectCustomer(Scanner customerScanner) {     
        // try: Request Information:
        try {
            // Request Customer Name:
            System.out.print("Customer Fullname\n" +
                ": ");
            String customerName = customerScanner.nextLine();
            // Handle Invalid Input:
            if (customerName.matches(".*\\d.*") || customerName.isEmpty()) {
                throw new InputMismatchException();
            }

            // Customer Telephone Number:
            System.out.print("Customer Telephone\n" +
                ": ");
            String customerTelNum;
            customerTelNum = customerScanner.nextLine();
            // Handle Invalid Input:
            if (!customerTelNum.matches("[0-9]+")) {
                throw new InputMismatchException();
            }

            // Customer Email Address:
            System.out.print("Customer Email Address\n" +
                ": ");
            String customerEmail;
            customerEmail = customerScanner.nextLine();
            // Handle Invalid Input:
            if (customerEmail.isEmpty()) {
                throw new InputMismatchException();
            }

            // Customer Physical Address:
            System.out.print("Customer Physical Address\n" +
                ": ");
            String customerPhyAdd = customerScanner.nextLine();
            // Handle Invalid Input:
            if (customerPhyAdd.isEmpty()) {
                throw new InputMismatchException();
            }

            System.out.println("______________________________________\n");

            // Confirm New Project Customer:
            System.out.print("Confirm New Project:\n" +
                "\n" +
                "1. Continue\n" +
                "2. Change input\n" +
                "3. Main Menu\n" +
                ": ");
            int newCustomerConfirmation = Integer.parseInt(customerScanner.nextLine());  ///NBNBNBNVN

            // If confirmed: Send Attribute Values to Class:
            // Return to New Customer method to get details:
            switch (newCustomerConfirmation) {
                case 1:
                    System.out.println("Customer Information Confirmed!");
                    // Create new Object of NewCustomer:
                    TheCustomer newCustomer = new TheCustomer(
                        customerName,
                        customerTelNum,
                        customerEmail,
                        customerPhyAdd
                        
                    );
                    // Print to Screen:
                    System.out.println(newCustomer);
                    // Customer Details:
                    String customerDetails = "";
                    customerDetails = TheCustomer.toTxtFile(customerDetails);
                    // Call method: write to PoisedProject.txt:
                    writeProject(customerDetails);
                    // Title of Next:
                    System.out.println("\n" +
                        "Create New Project:\n");
                    newProject(customerScanner);
                    break;
                case 2:
                    System.out.println("Canceled !!");
                    newProjectCustomer(customerScanner);
                    break;
                case 3:
                    getMenu();
                    break;
                default:
                    throw new InputMismatchException();
            }
        }
        // catch: Exception Error:
        catch (InputMismatchException exception) {
            System.out.println("Invalid input...\n" +
                "Please make sure enter the correct details & try again:\n");

            // Call Method Again:
            newProjectCustomer(customerScanner);
        }
    }

    /**
     * Requests input for project details.
     * uses writeProject method to write data to txt file.
     *
     * @param projectScanner - Scanner for Project.
     */
    // New Project: -- > Uses: WriteProject:
    public static void newProject(Scanner projectScanner) {
        // try: Request Information:
        try {
            // Request Project Number:
            System.out.print("Project Number\n" +
                ": ");
            String proNumberString = projectScanner.nextLine();

            // Handle Invalid Input:
            if (!proNumberString.matches("[0-9]+") || proNumberString.isEmpty()) {
                throw new InputMismatchException();
            }
            // Cast String number to Integer:
            int proNumber = Integer.parseInt(proNumberString);

            // Request Project Name:
            System.out.print("Project Name\n" +
                ": ");
            String proName = projectScanner.nextLine();
            // Handle Invalid Input:
            if (proName.matches(".*\\d.*")) {
                throw new InputMismatchException();
            }

            // User input type of building
            System.out.print("Building Type (e.g. House )\n" +
            		": ");
            
            String proType = projectScanner.nextLine();
           

            // User input Physical Address:
            System.out.print("Project Physical Address\n" +
                ": ");
            String proPhysAdd = projectScanner.nextLine();
            // Handle Invalid Input:
            if (proPhysAdd.isEmpty()) {
                throw new InputMismatchException();
            }

            // Request Project ERF Number:
            System.out.print("Project ERF Number\n" +
                ": ");
            String proERFString = projectScanner.nextLine();
            // Handle Invalid Input:
            if (!proERFString.matches("[0-9]+") || proERFString.isEmpty()) {
                throw new InputMismatchException();
            }
            // Cast String number to Integer:
            int proERF = Integer.parseInt(proERFString);

            // Request Project Total Fee
            System.out.print("Project Total Fee for e.g 12000\n" +
                ": ");
            String proTotFeeString = projectScanner.nextLine();
            // Handle Invalid Input:
            if (!proTotFeeString.matches("[0-9]+") || proTotFeeString.isEmpty()) {
                throw new InputMismatchException();
            }
            // Cast String number to Float:
            float proTotFee = Float.parseFloat(proTotFeeString);

            // User input - Project Total Fee Paid To Date:
            System.out.print("Project Total Fee Paid To Date e.g 750\n" +
                ": ");
            String proTotPaidString = projectScanner.nextLine();
            // Handle invali input:
            if (!proTotPaidString.matches("[0-9]+") || proTotPaidString.isEmpty()) {
                throw new InputMismatchException();
            }
            // Cast String number to Float:
            float proTotPaid = Float.parseFloat(proTotPaidString);

            // Request Project Deadline:
            // Day:
            System.out.print("Enter Deadline Day (DD) e.g 10 for Monday" +
                "\n: ");
            String day = projectScanner.nextLine();
            if (!day.matches("[0-9]+")) {
                throw new InputMismatchException();
            }

            // Month:
            System.out.print("Enter Deadline Month (MM) for 9 for August" +
                "\n: ");
            String month = projectScanner.nextLine();
            if (!month.matches("[0-9]+")) {
                throw new InputMismatchException();
            }

            // Year:
            System.out.print("Enter Deadline Year (YYYY) for e.g 2001" +
                "\n: ");
            String year = projectScanner.nextLine();
            if (!year.matches("[0-9]+")) {
                throw new InputMismatchException();
            }

            // Create Date:
            String proDeadline = (day + "-" + month + "-" + year);
            System.out.println("______________________________________\n");

            // Confirm New Project:
            System.out.print("Confirm New Project:\n" +
                "\n" +
                "1. Continue\n" +
                "2. Change Input\n" +
                "3. Main Menu\n" +
                ": ");
            int newProjectConfirm = Integer.parseInt(projectScanner.nextLine());     ///NBNBNBNBN

            // If confirmed: Send Attribute Values to Class:
            // Return to New Architect method to get details:
            if (newProjectConfirm == 1) {
                System.out.println("Project Information Confirmed!");
                Project newProject = new Project(
                    proNumber,
                    proName,
                    proType,
                    proPhysAdd,
                    proERF,
                    proTotFee,
                    proTotPaid,
                    proDeadline
                );
                
                System.out.println(newProject);

                // Project Details:
                String projectDetails = "";
                projectDetails = Project.toTxtFile(projectDetails);

                // Write to file:
                writeProject(projectDetails);

                // Title of next method:
                System.out.println("\n" +
                    "New Project Architect:\n");
                newProjectArchitect(projectScanner);
            }
            // Return back to top:
            else if (newProjectConfirm == 2) {
                System.out.println("Canceled !!");
                newProject(projectScanner);
            }
            // Return to MainMeu:
            else if (newProjectConfirm == 3) {
                getMenu();
            }
            // else: throw Exception:
            else {
                throw new InputMismatchException();
            }
        }
        // catch: Exception:
        catch (InputMismatchException exception) {
            System.out.println("Invalid input.\n" +
                "Please make sure enter the correct details & try again:\n");
            newProject(projectScanner);
        }

        // Close Scanner:
        projectScanner.close();
    }

    /**
     * Requests input for Architect details.
     * uses writeProject method to write data to txt file.
     *
     * @param architectScanner - Scanner for Architect.
     */
    // New Architect: -- > Uses: WriteProject:
    public static void newProjectArchitect(Scanner architectScanner) {
        // try: Request Information:
        try {
            // Architect Name:
            System.out.print("Architect Fullname\n" +
                ": ");
            String archName = architectScanner.nextLine();
            if (archName.matches(".*\\d.*") || archName.isEmpty()) {
                throw new InputMismatchException();
            }

            // Architect Telephone Number:
            System.out.print("Architect Telephone Number\n" +
                ": ");
            String archTelNum = architectScanner.nextLine();
            if (!archTelNum.matches("[0-9]+")) {
                throw new InputMismatchException();
            }

            // Architect Email Address:
            System.out.print("Architect Email Address\n" +
                ": ");
            String archEmail = architectScanner.nextLine();
            if (archEmail.isEmpty()) {
                throw new InputMismatchException();
            }

            // Architect Physical Address:
            System.out.print("Architect Physical Address\n" +
                ": ");
            String archPhyAdd = architectScanner.nextLine();
            if (archPhyAdd.isEmpty()) {
                throw new InputMismatchException();
            }

            System.out.println("______________________________________\n");

            // Confirm New Project Architect:
            System.out.print("Confirm New Project:\n" +
                "\n" +
                "1. Continue\n" +
                "2. Change Input\n" +
                "3. Main Menu\n" +
                ": ");
            int newArchitectConfirm = Integer.parseInt(architectScanner.nextLine());
            // If confirmed: Send Attribute Values to Class:
            // Return to New Builder method to get details:
            switch (newArchitectConfirm) {
                case 1:
                    System.out.println("Architect Information Confirmed!");
                    TheArchitect newArchitect = new TheArchitect(
                        archName,
                        archTelNum,
                        archEmail,
                        archPhyAdd
                    );
                    System.out.println(newArchitect);
                    // Architect Details:
                    String architectDetails = "";
                    architectDetails = newArchitect.toTxtFile(architectDetails);     //newArchitect/NewArchitect
                    // Write to file:
                    writeProject(architectDetails);
                    // Title of Next:
                    System.out.println("New Project Builder:\n");
                    newProjectContractor(architectScanner);
                    break;
                case 2:
                    System.out.println("Canceled !!");
                    newProjectArchitect(architectScanner);
                    break;
                case 3:
                    getMenu();
                    break;
                default:
                    throw new InputMismatchException();
            }
        }
        // catch: Handle Exception:
        catch (InputMismatchException exception) {
            System.out.println("Invalid input.\n" +
                "Please make sure enter the correct details & try again:\n");
            newProjectArchitect(architectScanner);
        }
        // Close Scanner:
        architectScanner.close();
        architectScanner.close();
    }

    /**
     * Requests input for Builder details.
     * uses writeProject method to write data to txt file.
     *
     * @param contractorScanner - Scanner for Builder.
     */
    // New Builder: -- > Uses: WriteProject:
    public static void newProjectContractor(Scanner contractorScanner) {
        // try: Request Information:
        try {
            // Builder Name:
            System.out.print("Contractor Name\n" +
                ": ");
            String contractorName = contractorScanner.nextLine();
            if (contractorName.matches(".*\\d.*") || contractorName.isEmpty()) {
                throw new InputMismatchException();
            }

            // Builder Telephone Number:
            System.out.print("Contractor Telephone\n" +
                ": ");
            String contractorTelNum = contractorScanner.nextLine();
            if (!contractorTelNum.matches("[0-9]+")) {
                throw new InputMismatchException();
            }

            // Builder Email Address:
            System.out.print("Contractor Email Address\n" +
                ": ");
            String contractorEmail = contractorScanner.nextLine();
            if (contractorEmail.isEmpty()) {
                throw new InputMismatchException();
            }

            // Builder Physical Address:
            System.out.print("Contractor Physical Address\n" +
                ": ");
            String contractorPhyAdd = contractorScanner.nextLine();
            if (contractorPhyAdd.isEmpty()) {
                throw new InputMismatchException();
            }

            System.out.println("______________________________________\n");

            // Confirm Contractor Information:
            System.out.print("Confirm New Project:\n" +
                "\n" +
                "1. Continue\n" +
                "2. Change Input\n" +
                "3. Main Menu\n" +
                ": ");
            int newContractorConfirmation = Integer.parseInt(contractorScanner.nextLine());
            // if: confirmed:
            switch (newContractorConfirmation) {
                case 1:
                    System.out.println("Confirmed !!");
                    TheContractor newContractor = new TheContractor(
                        contractorName,
                        contractorTelNum,
                        contractorEmail,
                        contractorPhyAdd
                    );
                    // Display Details:
                    System.out.println(newContractor);
                    // Builder Details:
                    String contractorDetails = "";
                    contractorDetails = newContractor.toTxtFile(contractorDetails); 
                    // Write to file:
                    writeProject(contractorDetails);
                    // return to main menu:
                    getMenu();
                    break;
                case 2:
                    System.out.println("Canceled !!");
                    newProjectContractor(contractorScanner);
                    break;
                case 3:
                    getMenu();
                    break;
                default:
                    throw new InputMismatchException();
            }
        }
        // catch: Handle Exception:
        catch (InputMismatchException exception) {
            System.out.println("Invalid input.\n" +
                "Please enter the correct details & try again:\n");
            newProjectContractor(contractorScanner);
        }

        // Close Scanner:
        contractorScanner.close();
    }

    /**
     * Writes input data to PoisedProject.txt.
     *
     * @param appendLine - append: String for Project Details.
     */
    // Write Project Details:
    public static void writeProject(String appendLine) {
        try {
            // Open File:
            File projectFile = new File("PoisedProject.txt");
            FileWriter fileWriter = new FileWriter(projectFile, true);
            PrintWriter printWriter = new PrintWriter(new FileWriter(projectFile, true));

            // Write String:
            printWriter.append(appendLine).append("\n");
            //Close:
            printWriter.close();
            fileWriter.close();
        } catch (Exception projectFileNotFound) {
            System.out.println("File Not Found.");
        }
    }

    /**
     * Retrieves all lines in PoisedProject.txt and stores
     * it in a StringBuilder. The StringBuilder is returned
     * to calling method.
     */

    // Read Project Details:
    public static StringBuilder readProject(Scanner updateScanner) {
        // Projects:
        StringBuilder projectLineString = new StringBuilder();

        try {
            // Open File for Read.
            File projectFile = new File("PoisedProject.txt");
            Scanner projectFilesScanner = new Scanner(projectFile);

            while (projectFilesScanner.hasNextLine()) {
                projectLineString.append(projectFilesScanner.nextLine()).append("\n");
            }

            //Close File Scanner:
            projectFilesScanner.close();
        } catch (Exception exception) {
            System.out.println("File Not Found.");
            updateProject(updateScanner);
        }
        // StringBuilder:
        return projectLineString;
    }

    /**
     * Requests input on update.
     * User can either update: Project Deadline or Project Total
     * Paid to Date.
     *
     * @param updateScanner - Scanner for Update Project.
     */
    // Update Existing Project:
    public static void updateProject(Scanner updateScanner) {
        // try: Request Update Information:
        try {
            // Request User's input of what they would like to update:11111
            System.out.print("Please select from the following options:\n" +
                "\n" +
                "1. Update Project Deadline\n" +
                "2. Update Total Paid Fee To Date\n" +
                "3. Main Menu\n" +
                ": ");
            int updateChoice = Integer.parseInt(updateScanner.nextLine());

            System.out.println("______________________________________\n");

            // Update Project Deadline:
            switch (updateChoice) {
                case 1:
                    // Display Error Message for Invalid Input:
                    String invalidInput = "Input Invalid.";
                    // try:
                    try {
                        // Request Project Number:
                        System.out.print("Enter Project Number\n" +
                            ": ");
                        int userProjectNumber = Integer.parseInt(updateScanner.nextLine());

                        // Request New Deadline Date:
                        System.out.println("Update Project Deadline:\n");

                        // Day:
                        System.out.print("Enter Deadline Day (DD) e.g 10 for Monday" +
                            "\n: ");
                        String day = updateScanner.nextLine();
                        if (!day.matches("[0-9]+")) {
                            System.out.println(invalidInput);
                            throw new InputMismatchException();
                        }
                        // Month:
                        System.out.print("Enter Deadline Month (MM) e.g 8 for August" +
                            "\n: ");
                        String month = updateScanner.nextLine();
                        if (!month.matches("[0-9]+")) {
                            System.out.println(invalidInput);
                            throw new InputMismatchException();
                        }
                        // Year:
                        System.out.print("Enter Deadline Year (YYYY) e.g 2001" +
                            "\n: ");
                        String year = updateScanner.nextLine();
                        if (!year.matches("[0-9]+")) {
                            System.out.println(invalidInput);
                            throw new InputMismatchException();
                        }

                        // Create Updated Deadline Date:
                        String newProjectDeadline = day + "-" + month + "-" + year;

                        // Call method: Read File: Get File Contents:
                        StringBuilder projectDetailsEdit = readProject(updateScanner);

                        // Set String Builder for updated project:
                        StringBuilder updatedProject = new StringBuilder();

                        // for line in Project File:
                        for (String detailLine: projectDetailsEdit.toString().split("[\n]")) {
                            ArrayList < String > projectList;
                            projectList = new ArrayList < > (Arrays.asList(detailLine.split(", ")));

                            // if project is found:
                            if (projectList.get(1).equals(String.valueOf(userProjectNumber))) {
                                // if Project Deadline is the equals previous Deadline:
                                if (projectList.get(8).equals(newProjectDeadline)) {
                                    System.out.println("Updated Project Deadline matches previous Project Deadline (" + projectList.get(8) + ")\n" +
                                        "Please enter a new Deadline & try again:" +
                                        "\n");
                                    // return to method : try again:
                                    updateProject(updateScanner);
                                }
                                // Update:
                                else {
                                    // Display Updated Date:
                                    System.out.println("\n" +
                                        "New Date: " + newProjectDeadline +
                                        "\n");

                                    projectList.set(8, newProjectDeadline);
                                    String changedProject =
                                        projectList.get(0) + ", " +
                                        projectList.get(1) + ", " +
                                        projectList.get(2) + ", " +
                                        projectList.get(3) + ", " +
                                        projectList.get(4) + ", " +
                                        projectList.get(5) + ", " +
                                        projectList.get(6) + ", " +
                                        projectList.get(7) + ", " +
                                        projectList.get(8);
                                    updatedProject.append(changedProject).append("\n");
                                }
                            }
                            // else: project not found.
                            else {
                                updatedProject.append(detailLine).append("\n");
                            }
                        }

                        // Confirm Update:
                        System.out.print("Confirm Update:\n" +
                            "1. Confirm\n" +
                            "2. Change Input\n" +
                            "3. Main Menu\n" +
                            ": ");
                        int confirmUpdate = Integer.parseInt(updateScanner.nextLine());

                        // Update Deadline:
                        switch (confirmUpdate) {
                            case 1:
                                System.out.println("Confirmed!!" +
                                    "\n");
                                // try: write update:
                                try {
                                    FileWriter projectFile = new FileWriter("PoisedProject.txt");
                                    PrintWriter updateProject = new PrintWriter(projectFile);
                                    // Write:
                                    updateProject.write(updatedProject.toString());
                                    // Close:
                                    updateProject.close();

                                    // return to Main Menu :
                                    getMenu();
                                }
                                // catch:
                                catch (Exception exception) {
                                    System.out.println("Update Failed.");

                                    // return to Main Menu :
                                    getMenu();
                                }
                                break;
                            case 2:
                                updateProject(updateScanner);
                                break;
                            case 3:
                                getMenu();
                                break;
                            default:
                                throw new InputMismatchException();
                        }
                    } catch (InputMismatchException inputMismatchException) {
                        System.out.println("Please enter the correct details & try again:\n");
                        updateProject(updateScanner);
                    }
                    break;
                case 2:
                    // Title:
                    System.out.println("Update Total Paid Fee To Date:" +
                        "\n");
                    // try:
                    try {
                        // Request Project Number:
                        System.out.print("Enter Project Number\n" +
                            ": ");
                        int userProjectNumber = Integer.parseInt(updateScanner.nextLine());

                        // Request New amount:
                        System.out.print("New Total Paid Fee To Date e.g 450:" +
                            "\n" +
                            "Enter Amount Paid Today\n" +
                            ": ");
                        float updatedTotalPaid = Float.parseFloat(updateScanner.nextLine());

                        // Call method: Read File: Get File Contents:
                        StringBuilder projectDetailsEdit = readProject(updateScanner);

                        // Set String Builder for updated project:
                        StringBuilder updatedProject = new StringBuilder();

                        // for line in Project File:
                        for (String detailLine: projectDetailsEdit.toString().split("[\n]")) {
                            ArrayList < String > projectList;
                            projectList = new ArrayList < > (Arrays.asList(detailLine.split(", ")));

                            // if project is found:
                            if (projectList.get(1).equals(String.valueOf(userProjectNumber))) {
                                // Previous Total Paid Amount:
                                float beforeTotalPaid = Float.parseFloat(projectList.get(7));
                                // New updated Total:
                                float newProjectAmount = beforeTotalPaid + updatedTotalPaid;
                                // Display new amount:

                                if (Float.parseFloat(projectList.get(6)) < newProjectAmount) {
                                    System.out.println("Amount paid exceeds Project Total Fee of " + projectList.get(6) +
                                        "\n Please enter the correct amount & try again:");
                                    // return to method to try again:
                                    updateProject(updateScanner);
                                } else {
                                    System.out.println("\n" +
                                        "New Total Paid: " + newProjectAmount);

                                    // Make change:
                                    projectList.set(7, String.valueOf(newProjectAmount));
                                    String changedProject =
                                        projectList.get(0) + ", " +
                                        projectList.get(1) + ", " +
                                        projectList.get(2) + ", " +
                                        projectList.get(3) + ", " +
                                        projectList.get(4) + ", " +
                                        projectList.get(5) + ", " +
                                        projectList.get(6) + ", " +
                                        projectList.get(7) + ", " +
                                        projectList.get(8);
                                    updatedProject.append(changedProject).append("\n");
                                }
                            } else {
                                updatedProject.append(detailLine).append("\n");
                            }
                        }

                        // Confirm Update:
                        System.out.print("Confirm Update:\n" +
                            "\n" +
                            "1. Confirm\n" +
                            "2. Change Input\n" +
                            "3. Main Menu\n" +
                            ": ");
                        int confirmUpdate = Integer.parseInt(updateScanner.nextLine());

                        switch (confirmUpdate) {
                            case 1:
                                System.out.println("Confirmed!!" +
                                    "\n");
                                // try: write update:
                                try {
                                    FileWriter projectFile = new FileWriter("PoisedProject.txt");
                                    PrintWriter updateProject = new PrintWriter(projectFile);
                                    // Write:
                                    updateProject.write(updatedProject.toString());
                                    // Close:
                                    updateProject.close();

                                    // Return to Main Menu:
                                    getMenu();
                                }
                                // catch:
                                catch (Exception exception) {
                                    System.out.println("Update Failed.");

                                    // return to Main Menu :
                                    getMenu();
                                }
                                break;
                            case 2:
                                updateProject(updateScanner);
                                break;
                            case 3:
                                getMenu();
                                break;
                        }
                    }
                    // catch:
                    catch (Exception exception) {
                        System.out.println("Update Failed.");
                    }
                    break;
                case 3:
                    getMenu();
                    break;
                default:
                    throw new InputMismatchException();
            }
        } catch (
            InputMismatchException exception) {
            System.out.println("Please enter the correct details & try again:\n");
            updateProject(updateScanner);
        }
        // Close Scanner:
        updateScanner.close();
    }

    /**
     * Requests input on update of Contact details.
     * User can either update: Architect Details or Builder contact
     * details.
     *
     * @param updateContactScanner - Scanner for Update Contact Details.
     */
    // Update Contract Contact Details:
    public static void updateContractContacts(Scanner updateContactScanner) {
        try {
            // Request User's input on what they would like to update:
            System.out.print("Please select from the following options:\n" +
                "\n" +
                "1. Update Architect Contact Details\n" +
                "2. Update Contractor' Contact Details\n" +
                "3. Main Menu\n" +
                ": ");
            int updateChoice = Integer.parseInt(updateContactScanner.nextLine());
            System.out.println("______________________________________\n");

            // Update Architect Contact Details:
            switch (updateChoice) {
                case 1:
                    try {
                        System.out.println("Update Architect Contact Details:\n");

                        // Request Project Number:
                        System.out.print("Enter Project Number\n" +
                            ": ");
                        int userProjectNumber = Integer.parseInt(updateContactScanner.nextLine());

                        // Update Telephone Number:
                        System.out.print("New Architect Telephone Number\n" +
                            ": ");
                        String updatedArchTelNum = updateContactScanner.nextLine();

                        if (!updatedArchTelNum.matches("[0-9]+") || updatedArchTelNum.isEmpty()) {
                            throw new InputMismatchException();
                        }
                        if (updatedArchTelNum.equals(TheArchitect.archTelNum)) {       //newArchitect/NewArchitect
                            System.out.println("Updated Telephone Number matches previous Telephone Number.");
                            throw new InputMismatchException();
                        }

                        // Update Email:
                        System.out.print("New Architect Email Address\n" +
                            ": ");
                        String updatedArchEmail = updateContactScanner.nextLine();

                        if (updatedArchEmail.isEmpty()) {
                            throw new InputMismatchException();
                        }

                        // Call method: Read File: Get File Contents:
                        StringBuilder projectDetailsEdit = readProject(updateContactScanner);

                        // Set String Builder for updated project:
                        StringBuilder updatedArchitect = new StringBuilder();

                        // Set Bool value to false: Architect is not next:
                        boolean archNext = false;

                        // for line in Project File:
                        for (String detailLine: projectDetailsEdit.toString().split("[\n]")) {
                            ArrayList < String > projectList;
                            projectList = new ArrayList < > (Arrays.asList(detailLine.split(", ")));

                            // if Architect line is found Details:
                            if (archNext) {
                                // change TelphoneNumber:
                                projectList.set(2, updatedArchTelNum);
                                // change Email:
                                projectList.set(3, updatedArchEmail);

                                // Check: if details match:
                                if (updatedArchTelNum.equals(projectList.get(2)) || updatedArchEmail.equals(projectList.get(3))) {
                                    System.out.println("Updated details match previous details.");
                                    throw new InputMismatchException();
                                }
                                String updateArchitect =
                                    projectList.get(0) + ", " +
                                    projectList.get(1) + ", " +
                                    projectList.get(2) + ", " +
                                    projectList.get(3) + ", " +
                                    projectList.get(4);
                                updatedArchitect.append(updateArchitect).append("\n");

                                // set back to false:
                                archNext = false;
                            } else {
                                if (projectList.get(1).equals(String.valueOf(userProjectNumber))) {
                                    // set to true as next line is Architect for the desire project:
                                    archNext = true;
                                }
                                updatedArchitect.append(detailLine).append("\n");
                            }
                        }
                        System.out.println("\n" +
                            "Updated Details:" +
                            "\n");
                        System.out.println("Telephone Number: " + updatedArchTelNum);
                        System.out.println("Email Address: " + updatedArchEmail);

                        System.out.println("______________________________________\n");

                        // Confirm Update:
                        System.out.print("Confirm Update:\n" +
                            "\n" +
                            "1. Confirm\n" +
                            "2. Change Input\n" +
                            "3. Main Menu\n" +
                            ": ");
                        int confirmUpdate = Integer.parseInt(updateContactScanner.nextLine());

                        // Write to File: Return to Main Menu:
                        switch (confirmUpdate) {
                            case 1:
                                // Write update to File:
                                try {
                                    FileWriter projectFile = new FileWriter("PoisedProject.txt");
                                    PrintWriter updateProject = new PrintWriter(projectFile);
                                    // Write:
                                    updateProject.write(updatedArchitect.toString());
                                    // Close:
                                    updateProject.close();

                                    // return to Main Menu :
                                    getMenu();
                                } catch (FileNotFoundException exception) {
                                    System.out.println("Update Failed.");

                                    // return to Main Menu :
                                    getMenu();
                                }
                                break;
                            case 2:
                                updateContractContacts(updateContactScanner);
                                break;
                            case 3:
                                getMenu();
                                break;
                            default:
                                throw new InputMismatchException();
                        }
                    } catch (Exception exception) {
                        System.out.println("Please make sure you enter the correct details & try again:");
                        updateContractContacts(updateContactScanner);
                    }
                    break;
                case 2:
                    try {
                        // Request Project Number:
                        System.out.print("Enter Project Number\n" +
                            ": ");
                        int userProjectNumber = Integer.parseInt(updateContactScanner.nextLine());

                        System.out.println("Update Contractor Contact Details:\n");

                        // Update Telephone Number:
                        System.out.print("New Contractor Telephone Number\n" +
                            ": ");
                        String updatedBuildTelNum = updateContactScanner.nextLine();

                        if (!updatedBuildTelNum.matches("[0-9]+") || updatedBuildTelNum.isEmpty()) {
                            throw new InputMismatchException();
                        }
                        if (updatedBuildTelNum.equals(TheContractor.contractorTelNum)) {    
                            System.out.println("Updated Telephone Number matches previous Telephone Number.");
                            throw new InputMismatchException();
                        }

                        // Update Email:
                        System.out.print("New Contractor Email Address\n" +
                            ": ");
                        String updatedBuildEmail = updateContactScanner.nextLine();

                        if (updatedBuildEmail.isEmpty()) {
                            throw new InputMismatchException();
                        }

                        // Call method: Read File: Get File Contents:
                        StringBuilder projectDetailsEdit = readProject(updateContactScanner);

                        // Set String Builder for updated project:
                        StringBuilder updatedBuilder = new StringBuilder();

                        // Set Bool value to false: Architect is not next:
                        boolean archNext = false;
                        boolean contractorNext = false;

                        // for line in Project File:
                        for (String detailLine: projectDetailsEdit.toString().split("[\n]")) {
                            ArrayList < String > projectList;
                            projectList = new ArrayList < > (Arrays.asList(detailLine.split(", ")));

                            // if Architect line is found Details:
                            if (contractorNext) {
                                // change Tel-Number:
                                projectList.set(2, updatedBuildTelNum);
                                // change Email:
                                projectList.set(3, updatedBuildEmail);

                                String updateArchitect =
                                    projectList.get(0) + ", " +
                                    projectList.get(1) + ", " +
                                    projectList.get(2) + ", " +
                                    projectList.get(3) + ", " +
                                    projectList.get(4);
                                updatedBuilder.append(updateArchitect).append("\n");

                                // set back to false:
                                archNext = false;
                                contractorNext = false;
                            } else {
                                if (archNext) {
                                    contractorNext = true;
                                }
                                // if project is found:
                                else if (projectList.get(1).equals(String.valueOf(userProjectNumber))) {
                                    // set to true as next line is Builder for the desire project:
                                    archNext = true;
                                }
                                updatedBuilder.append(detailLine).append("\n");
                            }
                        }

                        // Confirm Update:
                        System.out.println("______________________________________\n");
                        System.out.print("Confirm Update:\n" +
                            "\n" +
                            "1. Confirm\n" +
                            "2. Change Input\n" +
                            "3. Main Menu\n" +
                            ": ");
                        int confirmUpdate = Integer.parseInt(updateContactScanner.nextLine());
                        // Return to Main Menu:
                        switch (confirmUpdate) {
                            case 1:
                                try {
                                    FileWriter projectFile = new FileWriter("PoisedProject.txt");
                                    PrintWriter updateProject = new PrintWriter(projectFile);
                                    // Write:
                                    updateProject.write(updatedBuilder.toString());
                                    // Close:
                                    updateProject.close();

                                    // return to Main Menu :
                                    getMenu();
                                } catch (FileNotFoundException exception) {
                                    System.out.println("Update Failed.");
                                }
                                break;
                            case 2:
                                updateContractContacts(updateContactScanner);
                                break;
                            case 3:
                                getMenu();
                                break;
                            default:
                                throw new InputMismatchException();
                        }
                    } catch (Exception exception) {
                        System.out.println("failed...");
                    }
                    break;
                case 3:
                    getMenu();
                    break;
                default:
                    throw new InputMismatchException();
            }
        } catch (InputMismatchException exception) {
            System.out.println("Invalid input.\n" +
                "Please enter the correct details & try again:\n");
            updateContractContacts(updateContactScanner);
        }
        // Close Scanner:
        updateContactScanner.close();
    }

    /**
     * Requests project number,
     * Project is searched for in PoisedProject.txt
     * Project is stored in StringBuilder and returned to finaliseProject.
     *
     * @param finaliseScanner -  Scanner for Finalising Project.
     */
    // Finalise Project: Get Data: -----> 1
    public static void projectFinaliseNumber(Scanner finaliseScanner) {
        // Get Project Number
        System.out.print("Enter Project Number\n" +
            ": ");
        int userProjectNumber = Integer.parseInt(finaliseScanner.nextLine());

        // Build Project:
        StringBuilder completeProject = new StringBuilder();

        // Store Projects - completeProject:
        StringBuilder incompleteProjects = new StringBuilder();

        // try: Read File: Store Complete & Incomplete:
        try {
            // Read File:
            File projectFile = new File("PoisedProject.txt");
            Scanner projectScannerFile = new Scanner(projectFile);
            // 4 lines in PoisedProject.txt = One Project:
            while (projectScannerFile.hasNext()) {
                String customerLine = projectScannerFile.nextLine();
                String projectLine = projectScannerFile.nextLine();
                String architectLine = projectScannerFile.nextLine();
                String builderLine = projectScannerFile.nextLine();

                // Project Line to List:
                ArrayList < String > projectList;
                projectList = new ArrayList < > (Arrays.asList(projectLine.split(", ")));

                // if Project Number equals User Project Number
                if (projectList.get(1).equals(String.valueOf(userProjectNumber))) {
                    completeProject.append(customerLine).append("\n");
                    completeProject.append(projectLine).append("\n");
                    completeProject.append(architectLine).append("\n");
                    completeProject.append(builderLine).append("\n");
                }
                // else: Store Incomplete // Left Overs:
                else {
                    incompleteProjects.append(customerLine).append("\n");
                    incompleteProjects.append(projectLine).append("\n");
                    incompleteProjects.append(architectLine).append("\n");
                    incompleteProjects.append(builderLine).append("\n");
                }
            }
            // if Project is not found:
            if (completeProject.length() <= 0) {
                System.out.println("Project Number " + userProjectNumber + " does not exist.\n" +
                    "Please make sure you enter the correct Project Number." +
                    "\n");
                // return to method: try again:
                projectFinaliseNumber(finaliseScanner);
            }
            // Replace old projects with incomplete projects // left over:
            try {
                File projectFileIncomplete = new File("PoisedProject.txt");
                PrintWriter incompleteWriter = new PrintWriter(projectFileIncomplete);

                // Write:
                incompleteWriter.write(incompleteProjects.toString());
                // Close:
                incompleteWriter.close();
            }
            // catch: Exception:
            catch (FileNotFoundException incompleteEx) {
                System.out.println("Finalise Failed - File Not Found:");
                // return to Main Menu:
                getMenu();
            }
            // Call finaliseProject to Write Invoice:
            finaliseProject(completeProject);

            // Close File Scanner:
            projectScannerFile.close();
        }
        // catch: Exception
        catch (FileNotFoundException finaliseEx) {
            System.out.println("File Not Found.");
            // return Main Menu:
            getMenu();
        }
    }

    /**
     * Generates Project Invoice.
     * A Unique txt File is generated for each Project Invoice.
     *
     * @param completeProject - Scanner for Completing Project.
     */
    // Finalise Project: Invoice: -----> 2
    public static void finaliseProject(StringBuilder completeProject) {
        // Create Copy of String Builder:
        StringBuilder copyCompletedProject = new StringBuilder();
        copyCompletedProject.append(completeProject);
        
        
        // Store Project Lines as Array List:
        //ArrayList<String> listCustomer = new ArrayList<String>();
        ArrayList < String > listCustomer;
        ArrayList < String > listProject;
        ArrayList < String > listArchitect;
        ArrayList < String >listContractor;
        

        // for: Line in copy of CompletedProject: Set Details:
        for (String detailLine: copyCompletedProject.toString().split("\n")) {
            listCustomer = new ArrayList < > (Arrays.asList(detailLine.split(", ")));
            if ("Customer Details".equals(listCustomer.get(0))) {
                // Customer:
            	TheCustomer.customerName = listCustomer.get(1);
            	TheCustomer.customerTelNum = listCustomer.get(2);
            	TheCustomer.customerEmail = listCustomer.get(3);
            	TheCustomer.customerPhyAdd = listCustomer.get(4);
            }
            listProject = new ArrayList < > (Arrays.asList(detailLine.split(", ")));
            if ("Project Details".equals(listProject.get(0))) {
                // Project:
                Project.proNumber = Integer.parseInt(listProject.get(1));
                Project.proName = listProject.get(2);
                Project.proType = listProject.get(3);
                Project.proPhysAdd = listProject.get(4);
                Project.proERF = Integer.parseInt(listProject.get(5));
                Project.proTotFee = Float.parseFloat(listProject.get(6));
                Project.proTotPaid = Float.parseFloat(listProject.get(7));
                Project.proDeadline = listProject.get(8);
            }
            listArchitect = new ArrayList < > (Arrays.asList(detailLine.split(", ")));
            if ("Architect Details".equals(listArchitect.get(0))) {
                // Architect:
            	TheArchitect.archName = listArchitect.get(1);
            	TheArchitect.archTelNum = listArchitect.get(2);
                TheArchitect.archEmail = listArchitect.get(3);
                TheArchitect.archPhyAdd = listArchitect.get(4);
            }
           listContractor = new ArrayList < > (Arrays.asList(detailLine.split(", ")));
            if ("Builder Details".equals(detailLine)) {
                // Builder:
            	TheContractor.contractorName =listContractor.get(1);
                TheContractor.contractorTelNum =listContractor.get(2);
                TheContractor.contractorEmail =listContractor.get(3);
                TheContractor.contractorPhyAdd =listContractor.get(4);
            }
        }

        //File Location:
        String fileLocation;
        // try: Open File and Write Completed Project:
        try {
            // Create Time Stamp: DATE - (TIME - Hour.Min.Sec)
            String fileDateTime = new SimpleDateFormat("yyyy-MM-dd(HH.mm.ss)").format(new Date());

            // Create File: Write File:
            File finaliseFile = new File("Invoices\\" + fileDateTime + "--Invoice.txt");
            FileWriter fileWriter = new FileWriter(finaliseFile, true);
            PrintWriter finaliseWriter = new PrintWriter(new FileWriter(finaliseFile, true));

            // File Location/Path:
            fileLocation = finaliseFile.getAbsolutePath();

            // New Project:
            finaliseWriter.append("______________________________________________________________________\n");
            finaliseWriter.append("Invoice: " + "Project Number: ").append(String.valueOf(Project.proNumber));

            // Date Invoice was generated:
            LocalDate invoiceDate = LocalDate.now();
            finaliseWriter.append("\nDate: ").append(String.valueOf(invoiceDate));
            finaliseWriter.append("\n______________________________________________________________________\n");

            // Customer Contact Details:
            finaliseWriter.append("Customer Details:\n");
            finaliseWriter.append("______________________________________________________________________\n");
            finaliseWriter.append("Customer Name:.........................  ").append(TheCustomer.customerName);
            finaliseWriter.append("\nCustomer Telephone Number:.............  ").append(TheCustomer.customerTelNum);

            // Outstanding Fee Amount:
            finaliseWriter.append("\n" +
                "\nCustomer Outstanding Fees:");
            float outstandingProjectFee = Project.proTotFee - Project.proTotPaid;
            finaliseWriter.append("\nTotal Amount:..........................  ").append(String.valueOf(outstandingProjectFee));
            finaliseWriter.append("\n______________________________________________________________________\n");

            //Completed Project:
            finaliseWriter.append("Completed Project:\n");
            Project finalizedProject = new Project(
                Project.proNumber,
                Project.proName,
                Project.proType,
                Project.proPhysAdd,
                Project.proERF,
                Project.proTotFee,
                Project.proTotPaid,
                Project.proDeadline
            );

            // Print Final Completed Project:
            finaliseWriter.append(finalizedProject.toString());

            // Display Message & Invoice Path:
            System.out.println("Invoice Complete." +
                "\n");
            System.out.println("Invoice Location: " + fileLocation +
                "\n");

            // Close:
            finaliseWriter.close();
            fileWriter.close();
        }
        // catch: Exception:
        catch (Exception finalisedEx) {
            System.out.println("File Can't Be Generated.");
        }

        // Write to Project Complete File:
        completedProject(completeProject);

        // Return to Main Menu:
        getMenu();
    }

    /**
     * Writes completed project to CompletedProject.txt.
     * Removes Project From Incomplete Projects (PoisedProject.txt
     *
     * @param completeProject - Scanner for Completing Project.
     */
    // Finalise Project: Complete Project: ----- 3
    public static void completedProject(StringBuilder completeProject) {
        // try : Create Invoice:
        try {
            // Make String Builder List:
            StringBuilder completedProject = new StringBuilder();

            // Time Project was Finalized:
            String finalizedTime = new SimpleDateFormat("yyyy-MM-dd(HH.mm.ss)").format(new Date());
            completedProject.append("___________________________________________________________________________________________________________\n");
            completedProject.append("___________________________________________________________________________________________________________\n");
            completedProject.append("Finalised Date: ").append(finalizedTime).append("\n");

            // Create Array:
            ArrayList < String > completedList;

            for (String completedLine: completeProject.toString().split("\n")) {
                completedList = new ArrayList < > (Arrays.asList(completedLine.split(", ")));

                if ("Customer Details".equals(completedList.get(0))) {
                	TheCustomer customerDetails = new TheCustomer(
                        completedList.get(1),
                        completedList.get(2),
                        completedList.get(3),
                        completedList.get(4)
                    );
                    completedProject.append(customerDetails.toString()).append("\n");
                }
                if ("Project Details".equals(completedList.get(0))) {
                    Project projectDetails = new Project(
                        Integer.parseInt(completedList.get(1)),
                        completedList.get(2),
                        completedList.get(3),
                        completedList.get(4),
                        Integer.parseInt(completedList.get(5)),
                        Float.parseFloat(completedList.get(6)),
                        Float.parseFloat(completedList.get(7)),
                        completedList.get(8)
                    );
                    completedProject.append(projectDetails.toString()).append("\n");
                }

                if ("Architect Details".equals(completedList.get(0))) {
                	TheArchitect architectDetails = new TheArchitect(
                        completedList.get(1),
                        completedList.get(2),
                        completedList.get(3),
                        completedList.get(4)
                    );
                    completedProject.append(architectDetails.toString()).append("\n");
                }
                if ("Contractor Details".equals(completedList.get(0))) {
                	TheContractor contractorDetails = new TheContractor(
                        completedList.get(1),
                        completedList.get(2),
                        completedList.get(3),
                        completedList.get(4)
                    );
                    completedProject.append(contractorDetails.toString()).append("\n");
                }
            }

            // OpenFile:
            File completedFile = new File("CompletedProject.txt");
            PrintWriter completedWrite = new PrintWriter(new FileWriter(completedFile, true), true);

            // Write to file:
            completedWrite.append(completedProject.toString());

            //Close:
            completedWrite.close();

            // return getMenu:
            getMenu();
        } catch (Exception completedEx) {
            System.out.println("File Not Found");
        }
    }

    /** Reads all lines in CompletedProject.txt Displays lines/ completed projects. */
    // View All Completed Projects:
    public static void viewCompletedProjects() {
        // try: Read File:
        try {
            File completedFile = new File("CompletedProject.txt");
            @SuppressWarnings("resource")
			Scanner completedScanner = new Scanner(completedFile);

            // Display: Line in CompletedProject.txt:
            while (completedScanner.hasNextLine()) {
                System.out.println(completedScanner.nextLine());
            }

            // File is empty:
            if (completedFile.length() <= 0) {
                System.out.println("\n" +
                    "No projects have been finalised.\n" +
                    "To mark a project Complete: [Main Menu - Option - 4.]" +
                    "\n");
            }
            // return to getMenu:
            getMenu();
        }
        //catch: Exception:
        catch (FileNotFoundException viewCompletedEx) {
            System.out.println("File Not Found.");
            // return to Main Menu:
            getMenu();
        }
    }

    /**
     * Reads all line in PoisedProject.txt
     * Displays lines/ incomplete projects.
     *
     * @param incompleteProjectScanner - Scanner for Incomplete Projects
     */
    // View All Incomplete Projects:
    public static void viewIncompleteProjects(Scanner incompleteProjectScanner) {
        // String Builder: Projects:
        StringBuilder projectFile = readProject(incompleteProjectScanner);

        // String Builder: Incomplete Project:
        StringBuilder incompleteProject = new StringBuilder();

        // for : Line in ProjectFile:
        for (String incompleteLine: projectFile.toString().split("\n")) {
            // Control Line with ArrayList:
            ArrayList < String > incompleteList = new ArrayList < > (Arrays.asList(incompleteLine.split(", ")));

            // Customer Details:
            if ("Customer Details".equals(incompleteList.get(0))) {
                // Incomplete Project:
                incompleteProject.append(
                    "_____________________________________________________________________________________\n" +
                    "_____________________________________________________________________________________\n" +
                    "Incomplete Project:" +
                    "\n");
                TheCustomer customerDetails = new TheCustomer(
                    incompleteList.get(1),
                    incompleteList.get(2),
                    incompleteList.get(3),
                    incompleteList.get(4)
                );
                // append: Customer Object to String Builder:
                incompleteProject.append(customerDetails.toString()).append("\n");
            }
            // Project Details:
            if ("Project Details".equals(incompleteList.get(0))) {
                Project projectDetails = new Project(
                    Integer.parseInt(incompleteList.get(1)),
                    incompleteList.get(2),
                    incompleteList.get(3),
                    incompleteList.get(4),
                    Integer.parseInt(incompleteList.get(5)),
                    Float.parseFloat(incompleteList.get(6)),
                    Float.parseFloat(incompleteList.get(7)),
                    incompleteList.get(8)
                );
                // append: Project Object to String Builder:
                incompleteProject.append(projectDetails.toString()).append("\n");
            }
            // Architect Details:
            if ("Architect Details".equals(incompleteList.get(0))) {
            	TheArchitect architectDetails = new TheArchitect(
                    incompleteList.get(1),
                    incompleteList.get(2),
                    incompleteList.get(3),
                    incompleteList.get(4)
                );
                // append: Architect Object to String Builder:
                incompleteProject.append(architectDetails.toString()).append("\n");
            }
            // Contractor Details:
            if ("Contractor Details".equals(incompleteList.get(0))) {
            	TheContractor contractorDetails = new TheContractor(
                    incompleteList.get(1),
                    incompleteList.get(2),
                    incompleteList.get(3),
                    incompleteList.get(4)
                );
                // append: Builder Object to String Builder:
                incompleteProject.append(contractorDetails.toString()).append("\n");
            }
        }
        // Display Incomplete Projects:
        System.out.println(incompleteProject.toString());

        // return Main Menu:
        getMenu();
    }

    /**
     * Reads data in PoisedProject.txt
     * Changes String Date to type Date.
     * Displays all projects that have passed the Project Deadline.
     */
    // View All Overdue Projects:
    public static void overdueProjects() {
        // try: Get Overdue Projects by comparing dates:
        try {
            // Open File: Read File:
            File projectFile = new File("PoisedProject.txt");
            @SuppressWarnings("resource")
			Scanner projectFileScanner = new Scanner(projectFile);

            // Create String Builder for Incomplete Projects:
            StringBuilder overdueProjects = new StringBuilder();

            // Create Array to store projectLines:
            ArrayList < String > projectList;

            // Set Date Format:
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
            // Get Current Date:
            String currentDateString = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
            Date currentDate = simpleDateFormat.parse(currentDateString);

            // Store Lines in PoisedProject.txt:
            while (projectFileScanner.hasNext()) {
                String projectLine = projectFileScanner.nextLine();

                // Cast projectLine to ListArray:
                projectList = new ArrayList < > (Arrays.asList(projectLine.split(", ")));

                // if projectList is Project Details Line:
                if ("Project Details".equals(projectList.get(0))) {
                    // Project Date:
                    String projectDateString = projectList.get(8);
                    Date projectDate = simpleDateFormat.parse(projectDateString);

                    // if: Project is overdue:
                    if (projectDate.before(currentDate)) {
                        overdueProjects.append("______________________________________________________\n" + "Date Today: ").append(currentDateString).append("\nOverdue Project:" + "\n");

                        Project projectDetails = new Project(
                            Integer.parseInt(projectList.get(1)),
                            projectList.get(2),
                            projectList.get(3),
                            projectList.get(4),
                            Integer.parseInt(projectList.get(5)),
                            Float.parseFloat(projectList.get(6)),
                            Float.parseFloat(projectList.get(7)),
                            projectList.get(8)
                        );
                        // append: Overdue Project to String Builder:
                        overdueProjects.append(projectDetails.toString()).append("\n");
                    }
                }
            }
            // if: No Projects are Overdue and Incomplete:
            if (overdueProjects.length() <= 0) {
                System.out.println("All projects are up to date:\n");
            } else {
                // Display Overdue Projects:
                System.out.println(overdueProjects.toString());
            }
            // return Main Menu:
            getMenu();
        }
        // catch: Exception:
        catch (FileNotFoundException | ParseException overdueEx) {
            System.out.println("File Not Found.");
            // return to Main Menu:
            getMenu();
        }
    }

    /**
     * Project number user input.
     * Locate Project Number in PoisedProject.txt
     * Displays Project.
     *
     * @param findProjectScanner - Scanner for Finding Existing Project.
     */
    // Find Existing Task:
    public static void findExistingProject(Scanner findProjectScanner) {
        // try: Read File: Find Project:
        try {
            // Request Project number:
            System.out.print("Enter Project Number\n" +
                ": ");
            int userProjectNumber = Integer.parseInt(findProjectScanner.nextLine());

            // Read File:
            File projectFile = new File("PoisedProject.txt");
            @SuppressWarnings("resource")
			Scanner projectScannerFile = new Scanner(projectFile);

            StringBuilder foundProject = new StringBuilder();

            // Store: Every 4 Lines as 4 Lines = One Project:
            while (projectScannerFile.hasNext()) {
                String customerLine = projectScannerFile.nextLine();
                String projectLine = projectScannerFile.nextLine();
                String architectLine = projectScannerFile.nextLine();
                String builderLine = projectScannerFile.nextLine();

                // Customer List:
                ArrayList < String > customerList;
                customerList = new ArrayList < > (Arrays.asList(customerLine.split(", ")));
                // Project List:
                ArrayList < String > projectList;
                projectList = new ArrayList < > (Arrays.asList(projectLine.split(", ")));
                // Architect List:
                ArrayList < String > architectList;
                architectList = new ArrayList < > (Arrays.asList(architectLine.split(", ")));
                // Builder List:
                ArrayList < String > builderList;
                builderList = new ArrayList < > (Arrays.asList(builderLine.split(", ")));

                // if Project Number equals User Project Number
                if (projectList.get(1).equals(String.valueOf(userProjectNumber))) {
                	TheCustomer customerDetails = new TheCustomer(
                        customerList.get(1),
                        customerList.get(2),
                        customerList.get(3),
                        customerList.get(4)
                    );
                    // append: Customer Details to StringBuilder foundProject:
                    foundProject.append(customerDetails.toString()).append("\n");

                    Project projectDetails = new Project(
                        Integer.parseInt(projectList.get(1)),
                        projectList.get(2),
                        projectList.get(3),
                        projectList.get(4),
                        Integer.parseInt(projectList.get(5)),
                        Float.parseFloat(projectList.get(6)),
                        Float.parseFloat(projectList.get(7)),
                        projectList.get(8)
                    );
                    // append: Project Details to StringBuilder foundProject:
                    foundProject.append(projectDetails.toString()).append("\n");

                    TheArchitect architectDetails = new TheArchitect(
                        architectList.get(1),
                        architectList.get(2),
                        architectList.get(3),
                        architectList.get(4)
                    );
                    // append: Architect Details to StringBuilder foundProject:
                    foundProject.append(architectDetails).append("\n");

                    TheContractor contractorDetails = new TheContractor(
                        builderList.get(1),
                        builderList.get(2),
                        builderList.get(3),
                        builderList.get(4)
                    );
                    // append: Builder Details to StringBuilder foundProject:
                    foundProject.append(contractorDetails).append("\n");
                }
            }
            if (foundProject.length() <= 0) {
                System.out.println("Project Doesn't Exist.");
            } else {
                // Display Project:
                System.out.println(foundProject.toString());
            }
            // return Main Menu:
            getMenu();
        }
        // catch: Exception:
        catch (FileNotFoundException exception) {
            System.out.println("File Not Found.");

            // Return getMenu:
            getMenu();
        }
    }
	
	

    /** Main Method. */
    public static void main(String[] args) {
        // Welcome Screen:
        System.out.println("----------------------------------------");
        System.out.println("|   POISED PROJECT MANAGEMENT SYSTEM   |");  
        System.out.println("----------------------------------------" +
            "\n");

        // Start: Main Menu:
        getMenu();
    }


}
