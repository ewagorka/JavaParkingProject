import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

/**
 * Class CarPArk
 * @author ewg2
 */
public class CarPark {

    private Zone1 zone1;
    private Zone2 zone2;
    private Zone3 zone3;
    private Zone4 zone4;
    private Zone5 zone5;
    private ArrayList<Attendant> attendants;
    private Scanner scan;

    /**
     * Constructor for class CarPArk
     * creates 5 zones, each zones can have amount of spaces defined by user
     * @throws IOException
     */

    public CarPark() throws IOException {
        zone1 = new Zone1();
        zone1.initialiseZone(10, zone1);
        zone2 = new Zone2();
        zone2.initialiseZone(10, zone2);
        zone3 = new Zone3();
        zone3.initialiseZone(7, zone3);
        zone4 = new Zone4();
        zone4.initialiseZone(5, zone4);
        zone5 = new Zone5();
        zone5.initialiseZone(5, zone5);
        attendants = new ArrayList<>();
        scan = new Scanner(System.in);
    }

    /**
     * Finds an appropriate zone for vehicle type and lets an attendant pick a space
     * @param type vehicle type
     * @return appropriate zone
     */
    public Zone findAppropriateZoneAndPickParkingSpace(String type) {
        Zone zone;
        zone = zone1; // First initialization to stop compilation error (undefined variable)
        String answer;
        switch (type.toLowerCase()) {
            case "standard":
                do {
                    System.out.println("Zones 1 and 4 are available for this type of vehicle, ");
                    System.out.println("----------ZONE 1--------------- ");
                    System.out.println(zone1);
                    System.out.println("----------ZONE 4--------------- ");
                    System.out.println(zone4);
                    System.out.println("Press 1 to pick Zone 1, or press 4 pick Zone 4");
                    answer = scan.nextLine();
                    switch (answer.toUpperCase()) {
                        case "1":
                            zone = zone1;
                            break;
                        case "4":
                            zone = zone4;
                            break;
                    }
                } while (!(answer.equals("1") || answer.equals("4")));
                break;
            case "higher":
                System.out.println("Zone 1 is available for this type of vehicle, ");
                System.out.println("----------ZONE 3--------------- ");
                System.out.println(zone1);
                zone = zone1;
                break;
            case "longer":
                System.out.println("Zone 3 is available for this type of vehicle, ");
                System.out.println("----------ZONE 3--------------- ");
                System.out.println(zone3);
                zone = zone3;
                break;
        }
        return zone;
    }

    /**
     * Finds an appropriate zone for vehicle type and picks space automatically
     *@param type vehicle type
     * @return appropriate zone
     */

    public Zone findAppropriateZoneAndParkingSpace(String type) {  //Pick zone for vehicle by type
        Zone zone;
        zone = zone1; // First initialization to stop compilation error (undefined variable)
        switch (type.toLowerCase()) {
            case "standard":
                zone = zone4;
                if (zone.findFreeParkingSpace() == null)
                    zone = zone1;
                if (checkIfFull(zone)) {
                    zone = null;
                }
                break;
            case "higher":
                zone = zone1;
                if (checkIfFull(zone)) {
                    zone = null;
                }
                break;
            case "longer":
                zone = zone2;
                if (checkIfFull(zone)) {
                    zone = null;
                }
                break;
            case "coach":
                zone = zone3;
                if (checkIfFull(zone)) {
                    zone = null;
                }
                break;
            case "motorbike":
                zone = zone5;
                if (checkIfFull(zone)) {
                    zone = null;
                }
                break;
        }
        if (zone == null) {
            System.out.println("Sorry there are no places available, try again later.");
        }
        return zone;
    }

    /**
     * Checks if zone is full
     * @param zone
     * @return true or false
     */
    public boolean checkIfFull(Zone zone) {
        if (zone.findFreeParkingSpace() == null) {
            System.out.println("Sorry we have no available space for your vehicle");
            return true;
        }
        return false;
    }

    /**
     * Add attendant from the Admin menu
     */
    public void addAttendantFromMenu() {
        Attendant attendant = new Attendant();
        System.out.println("Enter name: ");
        String name = scan.nextLine();
        attendant.setName(name);
        attendants.add(attendant);
    }

    /**
     * Adds attendant to the ArrayList
     * @param attendant
     */
    public void addAttendant(Attendant attendant) {
        attendants.add(attendant);
    }

    /**
     * Prints a list of attendants
     */
    public void printAttendants() {
        int i = 1;
        for (Attendant a : attendants) {
            System.out.print(i++);
            System.out.print(" ");
            System.out.print(a.toString());
            System.out.println();
        }
    }

    /**
     * Removes attendant from the ArrayList
     * @throws Exception
     */
    public void removeAttendant() throws Exception {
        printAttendants();
        System.out.println("Enter a number corresponding to the attendant you want to remove");
        int index = getNumber();
        if (index >= 0 && index < attendants.size()) {
            attendants.remove(index - 1);
        } else {
            System.out.println("Sorry you typed wrong number");
        }
    }

    /**
     * Takes only a number from the user
     * @return
     */
    public int getNumber() {
        int number = -1;
        try {
            number = scan.nextInt();
            throw new InputMismatchException("value must contain only number");
        } catch (InputMismatchException e) {
        }
        return number;
    }

    /**
     * Checks if there are any free attendants a user could give their vehicle to.
     * @param vehicle
     * @return true or false
     */
    public boolean canGiveVehicleToFreeAttendant(Vehicle vehicle) {
        ArrayList<Attendant> freeAttendants = new ArrayList<>();
        for (Attendant a : attendants) {
            if (a.checkIfFree()) {
                freeAttendants.add(a);
            }
        }
        if (freeAttendants.size() >= 1) {
            int maxi = freeAttendants.size();
            Random r = new Random();
            int index = r.nextInt(maxi);
            Attendant attendant = freeAttendants.get(index);
            System.out.println("Attendant " + attendant.getName() + " will park your car.");
            attendant.setVehicle(vehicle);
            vehicle.setAttendant(attendant);
            return true;
        } else {
            System.out.println("Sorry no attendants available");
            vehicle.setNeedAttendant(false);
            return false;
        }
    }

    /**
     * Clears attendants ArrayList
     */
    public void clearAttendants() {
        attendants.clear();
    }

    //GETTERS
    public Zone getZone1() {
        return zone1;
    }

    public Zone2 getZone2() {
        return zone2;
    }

    public Zone3 getZone3() {
        return zone3;
    }

    public Zone4 getZone4() {
        return zone4;
    }

    public Zone5 getZone5() {
        return zone5;
    }

    /**
     * Returns an appropriate zone by they name - useful with save/load
     * @param zoneName
     * @return
     */
    public Zone getZoneByName(String zoneName) {
        Zone zone = null;
        switch (zoneName) {
            case "Zone 1":
                zone = zone1;
                break;
            case "Zone 2":
                zone = zone2;
                break;
            case "Zone 3":
                zone = zone3;
                break;
            case "Zone 4":
                zone = zone4;
                break;
            case "Zone 5":
                zone = zone5;
                break;
        }
        return zone;
    }

    /**
     * Looks for given attendant's name
     * @param name
     * @return
     */
    public Attendant findAttendant(String name) {
        Attendant attendant = null;
        for (Attendant a : attendants) {
            if (name.equals(a.getName())) {
                attendant = a;
            }
        }
        return attendant;
    }

    @Override
    public String toString() {
        return "CarPark : " + "\n"
                + zone1 + "\n" +
                 zone2 + "\n" +
                zone3 + "\n" +
                zone4 + "\n" +
                zone5 + "\n" ;
    }

    /**
     * Changes hour costs in the zone specified by the user
     */
    public void changeCosts() {
        System.out.println("Pick zone by entering number: ");
        System.out.println("1. ZONE 1 - current cost:" + zone1.getCost());
        System.out.println("2. ZONE 2 - current cost:" + zone2.getCost());
        System.out.println("3. ZONE 3 - current cost:" + zone3.getCost());
        System.out.println("4. ZONE 4 - current cost:" + zone4.getCost());
        System.out.println("5. ZONE 5 - current cost:" + zone5.getCost());
        String answer = scan.nextLine();
        switch (answer) {
            case "1":
                changeCostZone(zone1);
                break;
            case "2":
                changeCostZone(zone2);
                break;
            case "3":
                changeCostZone(zone3);
                break;
            case "4":
                changeCostZone(zone4);
                break;
            case "5":
                changeCostZone(zone5);
                break;

        }
    }

    /**
     * Sets a new hour price in a zone
     * @param zone
     */
    public void changeCostZone(Zone zone) {
        float cost;
        System.out.println("New cost: ");
        cost = scan.nextFloat();
        zone.setCost(cost);
    }

    /**
     * Saves prices and attendants
     * @param outfile
     */
    public void save(PrintWriter outfile) {
        if (outfile == null)
            throw new IllegalArgumentException("outfile must not be null");
        outfile.println(zone1.getCost());
        outfile.println(zone2.getCost());
        outfile.println(zone3.getCost());
        outfile.println(zone4.getCost());
        outfile.println(zone5.getCost());
        outfile.println("Attendants:");
        for (Attendant a : attendants) {
            outfile.println(a.getName());
        }

    }
}
