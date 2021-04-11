import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * SuperClass Zone
 * @author ewg2
 */
public class Zone {

    private ArrayList<ParkingSpace> parkingSpaces = new ArrayList<>();
    private int amountOfSpaces;
    private Scanner scan;
    private float cost;

    public Zone() {
        scan = new Scanner(System.in);
    }

    /**
     * Prepares a nice, clear zone with the amount of spaces defined by the user
     */
    public void initialiseZone(int amountOfSpaces, Zone z){
        parkingSpaces.clear();
        for (int i = 0; i< amountOfSpaces;i++) {
            ParkingSpace parkingSpace = new ParkingSpace(z);
            parkingSpaces.add(i, parkingSpace);
            parkingSpace.setId(i+1);
            parkingSpace.setFree(true);
            this.amountOfSpaces = amountOfSpaces;
        }
    }

    /**
     * Finds a free parking space in the zone.
     * @return
     */
    public ParkingSpace findFreeParkingSpace(){

        ParkingSpace freeParkingSpace = null;
        for (ParkingSpace parkingSpace : parkingSpaces) {
            if(parkingSpace.isFree()){
                freeParkingSpace = parkingSpace;
                break;
            }
        }
        return freeParkingSpace;
    }

    /**
     * Allows to pick a free parking space by the user
     * @param vehicle
     */
    public void pickParkingSpace(Vehicle vehicle){
        System.out.println("Pick parking space by entering id:");
        int id = scan.nextInt();
        ParkingSpace parkingSpace = getParkingSpace(id);
            if(parkingSpace.isFree()){
                vehicle.setParkingSpace(parkingSpace);
            }else{
                System.out.println("Sorry the place you picked seems to be taken, please try again");
                pickParkingSpace(vehicle);
            }
    }

    /**
     * Base for other zones to calculate payment (counts amount of hours).
     * @param diff
     * @return
     */
    public float calculatePayment(long diff){
        long checkIfExtraHour = diff%3600;
        int amountOfHours = (int) (diff/3600);
        float result;
        if(checkIfExtraHour!=0){
            result = (amountOfHours+1);
        }else{
            result = amountOfHours;
        }
        System.out.println(result);
        return result;
    }


    public void save(PrintWriter outfile) {
        if (outfile == null)
            throw new IllegalArgumentException("outfile must not be null");
        outfile.println(amountOfSpaces);
        for(ParkingSpace parkingSpace : parkingSpaces){
            outfile.println(parkingSpace.getId());
            outfile.println(parkingSpace.isFree());
        }
    }

    //SETTERS / GETTERS

    public ParkingSpace getParkingSpace(int id){
        return parkingSpaces.get(id-1);
    }
    public int getAmountOfSpaces() {
        return amountOfSpaces;
    }

    public void setAmountOfSpaces(int amountOfSpaces) {
        this.amountOfSpaces = amountOfSpaces;
    }

    public String getZoneName(){
        return "Zone";
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public float getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return
                "\nparking Spaces: " + parkingSpaces +
                ", amount of spaces =" + amountOfSpaces;
    }

    public String zoneName(){
        return "Zone ";
    }


}
