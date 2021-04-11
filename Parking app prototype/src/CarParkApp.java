import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

/**
 * CarParkApp
 * @author ewg2
 */
public class CarParkApp {

    private String filename;
    private Scanner scan;
    private CarPark carPark;
    private Calendar currentTime;
    private ArrayList<Receipt> receipts = new ArrayList<>();

    /**
     * Constructor for class CarParkApp
     * asks for a file to lad info from and creates a new car park
     * @throws IOException
     */
    private CarParkApp() throws IOException {
        scan = new Scanner(System.in);
        System.out.println("Enter file name (carpark.txt)");
        filename = scan.nextLine();
        carPark = new CarPark();
    }

    /**
     * Returns the current date
     * @return current date
     */

    public Calendar getDate(){
        Calendar now = Calendar.getInstance();
        return now;
    }

    /**
     * Prints menu with options for user
     */
    private void printUserMenu() {
        System.out.println("USER MENU: ");
        System.out.println("1 -  Park a car ");
        System.out.println("2 -  Display car park situation ");
        System.out.println("3 -  Get a token to get out of the car park ");
        System.out.println("4 -  Leave car park ");
        System.out.println("q -  Quit");
    }

    /**
     * Handles user's input
     */
    private void runUserMenu() throws Exception {
        String response;        //response from the user
        do {
            printUserMenu();    //print menu
            System.out.println("What would you like to do:");
            response = scan.nextLine().toUpperCase();   //get response and make it upper case
            switch (response) {
                case "1":
                    registerVehicle();
                    break;
                case "2":
                    displayCarPark();
                    break;
                case "3":
                    findReceiptAndActivateToken();
                    break;
                case "4":
                    leave();
                    break;
                case "Q":
                    mainMenu();
                    break;
                default:
                    System.out.println("Sorry wrong input, try again.");
                    break;
            }
        } while (!(response.equals("Q")));
    }
    /**
     * Prints options for the user to pick a vehicle type.
     * Has a "h.Help" option if user is not sure what kind of vehicle they have
     * @return a string containing a number adequate to the type
     */
    public String getVehicleType(){
        System.out.println("Pick vehicle type by entering the number");
        System.out.println("1. standard ");
        System.out.println("2. higher ");
        System.out.println("3. longer ");
        System.out.println("4. coach ");
        System.out.println("5. motorbike ");
        System.out.println("h. help");
        String answer = scan.nextLine();

        return answer;
    }
    /**
     * Asks user if they need an attendant to park the car from them
     * @return boolean true - if the answer was "yes" or false - if the answer was "no"
     */
    public boolean needAttendant(Vehicle vehicle){
        boolean needAttendant = false;
        System.out.println("Do you need an attendant? Y/N");
        String answer = scan.nextLine();
        switch (answer.toUpperCase()){
            case "Y":
                needAttendant = true;
                vehicle.setNeedAttendant(true);
                break;
            case  "N":
                needAttendant = false;
                vehicle.setNeedAttendant(false);
                break;
            default:
                System.out.println("Please type in : \"Y\" or \"N\"");
                needAttendant(vehicle);

        }
        return  needAttendant;
    }

    /**
     * Displays the whole car park- every zone with every parking space and it's current state
     */
    public void displayCarPark(){
        System.out.println(carPark.toString());
    }

    /**
     *Sets license, zone and parking space for vehicle
     * @param vehicle
     */
    public void setLicenseAndZone(Vehicle vehicle){
        Zone zone;
        System.out.println("Enter license plate:");
        String answer = scan.nextLine();         // get license from user
        vehicle.setLicense(answer);             //setting license
        if(vehicle.isNeedAttendant()){
            if(carPark.canGiveVehicleToFreeAttendant(vehicle)) {
                zone = carPark.findAppropriateZoneAndPickParkingSpace(vehicle.getType());
            }else {
                zone = carPark.findAppropriateZoneAndParkingSpace(vehicle.getType());
            }
        }else{
            zone = carPark.findAppropriateZoneAndParkingSpace(vehicle.getType());
        }

        processVehicleAndCreateReceipt(vehicle, zone, vehicle.isNeedAttendant());
    }

    /**
     * Asks user if they have disabled parking permit and sets information
     * @param vehicle
     */
    public void setDisabled(Vehicle vehicle){
        System.out.println("Do you have disabled parking permit? Y / N");
        String answer = scan.nextLine().toUpperCase();
        switch (answer) {
            case "Y":
                vehicle.setDisabled(true);
                break;
            case "N":
                vehicle.setDisabled(false);
                break;
            default:
                System.out.println("Enter Y - for yes or N - for no");
                setDisabled(vehicle);
                break;
        }


    }

    /**
     * Registers a new vehicle
     */
    public void registerVehicle(){
        System.out.println("You are registering a new vehicle");
        Vehicle vehicle = new Vehicle();        //create a new vehicle
        String  answer = getVehicleType();

        switch (answer){
            case "1":
                vehicle.setType("standard");
                setDisabled(vehicle);           //check for disabled parking permit
                if(needAttendant(vehicle)){     //check if attendant needed
                    attendantParkVehicle(vehicle);
                }else {
                    setLicenseAndZone(vehicle);
                }
                break;
            case "2":
                vehicle.setType("higher");
                setDisabled(vehicle);
                if(needAttendant(vehicle)){
                    attendantParkVehicle(vehicle);
                }else {
                    setLicenseAndZone(vehicle);
                }
                break;
            case "3":
                vehicle.setType("longer");
                setDisabled(vehicle);
                if(needAttendant(vehicle)){
                    attendantParkVehicle(vehicle);
                }else {
                    setLicenseAndZone(vehicle);
                }
                break;
            case "4":
                vehicle.setType("coach");
                setLicenseAndZone(vehicle);
                break;
            case "5":
                vehicle.setType("motorbike");
                setDisabled(vehicle);
                setLicenseAndZone(vehicle);
                break;
            case "h":
                helpType();
                registerVehicle();
            default:
                System.err.println("Error, please start again");
                registerVehicle();
                break;
        }


    }

    /**
     * Lets attendant pick a parking space
     * @param vehicle
     */
     public void attendantParkVehicle(Vehicle vehicle) {
        System.out.println("---ATTENDANT----");
        System.out.println("Enter license plate:");
        String answer = scan.nextLine();         // get license from user
        vehicle.setLicense(answer);
        vehicle.setNeedAttendant(true);
        carPark.canGiveVehicleToFreeAttendant(vehicle);
        Zone zone = carPark.findAppropriateZoneAndPickParkingSpace(vehicle.getType());
        vehicle.setZone(zone);
        processVehicleAndCreateReceipt(vehicle,vehicle.getZone(),vehicle.isNeedAttendant());

    }

    /**
     * Displays and runs main menu
     * @throws Exception
     */
    public void mainMenu() throws Exception {
        System.out.println("Type in 1 - for user view, " +
                " 2 - for admin view, Q - to quit" );
        String answer = scan. nextLine();
        switch (answer.toUpperCase()){
            case "1":
                runUserMenu();
                break;
            case "2":
                System.out.println("admin");
                runAdminMenu();
                break;
            case "Q":
                save(filename);
                break;
            default:
                mainMenu();
        }
    }

    /**
     * Prints Admin menu
     */
    public void printAdminMenu(){
        System.out.println("ADMIN");
        System.out.println("Select an option by entering the number");
        System.out.println("1.Display car park");
        System.out.println("2.Show attendants");
        System.out.println("3.Add attendant");
        System.out.println("4.Remove attendant");
        System.out.println("5.Find receipt");
        System.out.println("6.Change zone costs");
        System.out.println("Q - quit");
    }

    /**
     * Handles Admin menu
     * @throws Exception
     */
    public void runAdminMenu() throws Exception {
        String response;
        do {
            printAdminMenu();
            System.out.println("What would you like to do?");
            response = scan.nextLine().toUpperCase();
            switch (response) {
                case "1":
                    displayCarPark();
                    break;
                case "2":
                    carPark.printAttendants();
                    break;
                case "3":
                    carPark.addAttendantFromMenu();
                    break;
                case "4":
                    carPark.removeAttendant();
                    break;
                case "5":
                    findReceipt();
                    break;
                case "6":
                    changeCosts();
                    break;
                case "Q":
                    mainMenu();
                    break;
                default:
                    System.out.println("Try again");
                    break;

            }
        }while(!(response.equals("Q")));
    }

    /**
     * Changes costs in zones
     */
    public void changeCosts(){
        carPark.changeCosts();
    }

    /**
     * Prints help info about types of vehicles (their measurements)
     */
    public void helpType(){
        System.out.println("1. Standard sized vehicles (up to 2 metres in height and less than 5\n" +
                "metres in length: cars and small vans).\n" +
                "2. Higher vehicles (over 2 metres in height but less then 3 metres in\n" +
                "height and less than 5 metres in length: tall short wheel-based vans).\n" +
                "3. Longer vehicles (less then 3 metres in height and between 5.1 and 6\n" +
                "metres in length: long wheel-based vans).\n" +
                "4. Coaches (of any height up to 15 metres in length).\n" +
                "5. Motorbikes.");
    }

    /**
     * Creates a receipt for a vehicle
     * @param vehicle
     * @param zone
     * @param attendant
     */
    public void processVehicleAndCreateReceipt(Vehicle vehicle, Zone zone,Boolean attendant){
        Receipt receipt;
        Calendar currentTime;
        if(zone!=null) {
            vehicle.setZone(zone);
            if(!attendant) {
                vehicle.setParkingSpace(zone.findFreeParkingSpace());
            }else{
                vehicle.getZone().pickParkingSpace(vehicle);
            }
            vehicle.getParkingSpace().setFree(false);
            receipt = new Receipt(vehicle);
            receipts.add(receipt);                          //add receipt to array list
            currentTime = getDate();
            receipt.setStartTime(currentTime);
            receipt.getToken().setValid(false);
            System.out.println(receipt.toString());
        }
    }

    /**
     * Looks for a receipt and starts payment. If user payed enough activates a token for user to leave car park.
     * @throws IOException
     */
    public void findReceiptAndActivateToken() throws IOException {
        System.out.println("Give receipt number :");
        int number = scan.nextInt();
        Receipt receipt = null;
        for(Receipt r: receipts){
            if(r.getNumber() ==number){
                receipt =r;
                payment(receipt);
                Vehicle vehicle = receipt.getVehicle();
                if(vehicle.isNeedAttendant()){
                    System.out.println("Attendant will collect your car now");
                    vehicle.getAttendant().setVehicle(null);

                }
                System.out.println("Your vehicle is parked in zone: "+vehicle.getZone().getZoneName() + ", "+vehicle.getParkingSpace().parkingSpaceName());
                receipt.getParkingSpace().setFree(true);
                System.out.println("You now have 15 minutes to leave the car park");
                Calendar startTokenTime = Calendar.getInstance();
                receipt.getToken().setValid(true);
                receipt.getToken().setTime(startTokenTime);
                break;
            }
        }if(receipt == null) {
            System.out.println("Receipt not found.");
        }

    }

    /**
     * Simulates payment procedure
     * @param receipt
     */
    public void payment(Receipt receipt){
        Vehicle vehicle = receipt.getVehicle();
        Zone zone = vehicle.getZone();
        Calendar startTime = receipt.getStartTime();
        System.out.println(startTime.getTime());
        float cost = calculatePayment(startTime, zone);
        if(vehicle.isDisabled()){                       //checks if have disabled parking permit
            cost= cost/2;
           if(startTime.get(Calendar.DAY_OF_WEEK) == 1){    //checks if it's Sunday
              cost = 0;
            }
        }

        float amount;
        System.out.println("You need to pay "+ cost);
        do {
            System.out.println("Enter amount you want to pay: ");
            amount = scan.nextFloat();
            if(amount<cost){
                System.out.println("Not enough. Please try again.");
            }
        }while(amount<cost);
        float change = amount - cost;
        System.out.println("Your change: "+change);
    }

    /**
     * Calculates cost for an appropriate zone
     * @param startTime
     * @param zone
     * @return
     */
    public float calculatePayment(Calendar startTime, Zone zone){
        Calendar endTime = Calendar.getInstance();
        System.out.println(endTime.getTime());
        long diff = (endTime.getTimeInMillis()-startTime.getTimeInMillis())/1000;
        float cost = zone.calculatePayment(diff);
        return cost;
    }

    /**
     * Saves all the information to the file
     * @param outfileName
     * @throws IOException
     */
    public void save(String outfileName) throws IOException {
        // Again using try-with-resource so that I don't need to close the file explicitly
        try (FileWriter fw = new FileWriter(outfileName);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter outfile = new PrintWriter(bw);) {

            carPark.save(outfile);
            outfile.println("Receipts");
            for (Receipt r: receipts){
                r.save(outfile);
            }
            }
        }

    /**
     * Loads all the information from the file
      */
    private void initialise() {
        System.out.println("Using file " + filename);
        try {
            load(filename);
        } catch (FileNotFoundException e) {
            System.err.println("The file: " + " does not exist. Assuming first use and an empty file.");
        } catch (IOException e) {
            System.err.println("An unexpected error occurred when trying to open the file " + filename);
            System.err.println(e.getMessage());
        }
    }

    /**
     * Reads from the file and sets all information to needed destinations
     * @param filename
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void load(String filename) throws FileNotFoundException, IOException {
        try (FileReader fr = new FileReader(filename);
             BufferedReader br = new BufferedReader(fr);
             Scanner infile = new Scanner(br)) {

            carPark.clearAttendants();
            receipts.clear();

            infile.useDelimiter("\r?\n|\r"); // End of line characters on Unix or DOS
            if(infile.hasNext()){
                Float cost = infile.nextFloat();    //Loading costs
                carPark.getZone1().setCost(cost);
                cost = infile.nextFloat();
                carPark.getZone2().setCost(cost);
                cost = infile.nextFloat();
                carPark.getZone3().setCost(cost);
                cost = infile.nextFloat();
                carPark.getZone4().setCost(cost);
                cost = infile.nextFloat();
                carPark.getZone5().setCost(cost);
            String name = infile.next();
            name = infile.next();
            while (!(name.equals("Receipts"))) {        //Loading attendants
                Attendant attendant = new Attendant();
                attendant.setName(name);
                carPark.addAttendant(attendant);
                name = infile.next();
            }
            while (infile.hasNext()) {
                name = infile.next();
                if (name.equals("Receipt")) {           //Loading receipts and creating vehicles

                    int number = infile.nextInt();
                    String license = infile.next();
                    String type = infile.next();
                    Zone zone = carPark.getZoneByName(infile.next());
                    ParkingSpace parkingSpace = zone.getParkingSpace(infile.nextInt());
                    parkingSpace.setFree(false);
                    boolean disabled = infile.nextBoolean();
                    boolean needAttendant = infile.nextBoolean();
                    Vehicle vehicle = new Vehicle(license,type,zone, parkingSpace,disabled,needAttendant);
                    Receipt receipt = new Receipt(vehicle);
                    receipt.setNumber(number);
                    if (needAttendant) {
                        String attendantName = infile.next();
                        Attendant attendant = carPark.findAttendant(attendantName);
                        vehicle.setAttendant(attendant);
                        attendant.setVehicle(vehicle);
                    }
                    Calendar startTime = Calendar.getInstance();    //getting correct time
                    int year = infile.nextInt();
                    int month = infile.nextInt() - 1;
                    int day = infile.nextInt();
                    int hour = infile.nextInt();
                    int minutes = infile.nextInt();
                    startTime.clear();
                    startTime.set(year, month+1, day, hour, minutes);
                    receipt.setStartTime(startTime);
                    receipts.add(receipt);
                    ;
                }
            }
        }}
    }

    /**
     * Prints receipt specified by the user
     */
    public void findReceipt(){
        System.out.println("Nr:");
        int number = carPark.getNumber();
        Receipt receipt =  null;
        for(Receipt r: receipts){
            if(r.getNumber() ==number){
                receipt = r;
            }
        }
        if(receipt!=null) {
            System.out.println(receipt.toString());
        }else{
            System.out.println("Sorry receipt not found");
        }
    }

    /**
     * Checks if token from the receipt is valid and if it is lets user get out of the car park.
     */
    public void leave(){
        System.out.println("Give receipt number :");
        int number = scan.nextInt();
        Token token = null;
        Receipt receipt = null;
        for(Receipt r: receipts){
            if(r.getNumber() ==number){
                receipt = r;
            }
        }
        if(receipt!=null) {
            token = receipt.getToken();
        }
        if(token!=null){
            leaveCarPark(token);
            receipts.remove(receipt);
        }else{
            System.out.println("Sorry you may have entered wrong receipt number");
        }


    }

    /**
     * Checks if user took more than 15 minutes to get out
     * @param token
     */
    public void leaveCarPark(Token token){
        if(token.isValid()){
            Calendar currentTime = Calendar.getInstance();
            long check = currentTime.getTimeInMillis() - token.getTime().getTimeInMillis();
            if(check>900000){
                System.out.println("You took more than 15 minutes to leave the car park. Please contact help.");
            }else{
                System.out.println("Thanks see you later :)");
            }

        }else {
            System.out.println("Your token is not valid please call help");
        }
    }


    public static void main(String args[]) throws Exception {
        CarParkApp app = new CarParkApp();
        app.initialise();
        app.mainMenu();
    }
}


