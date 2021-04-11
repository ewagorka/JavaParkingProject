import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Scanner;

/**
 * Class Receipt
 * @author ewg2
 */
public class Receipt {

    private int number;
    private static int count = 1; //needs to be static
    private Vehicle vehicle;
    private Zone zone;
    private ParkingSpace parkingSpace;
    private Calendar startTime;
    private Token token;

    public Receipt(Vehicle vehicle){
        this.vehicle = vehicle;
        this.zone = vehicle.getZone();
        this.parkingSpace = vehicle.getParkingSpace();
        this.number = count++;
        this.token = new Token();

    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public static int getCount() {
        return count;
    }

    public static void setCount(int count) {
        Receipt.count = count;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public ParkingSpace getParkingSpace() {
        return parkingSpace;
    }

    public void setParkingSpace(ParkingSpace parkingSpace) {
        this.parkingSpace = parkingSpace;
    }

    public Zone getZone() {
        return zone;
    }

    public void setZone(Zone zone) {
        this.zone = zone;
    }

    public Calendar getStartTime() {
        return startTime;
    }

    public void setStartTime(Calendar startTime) {
        this.startTime = startTime;
    }


    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "Receipt " +
                "number= " + number +
                ", vehicle= " + vehicle +
                ", zone= " + zone.getZoneName() +
                ", parking space= " + parkingSpace.parkingSpaceName() +
                " Start time: "+ startTime.getTime();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Receipt receipt = (Receipt) o;
        return number == receipt.number;
    }


    public void save(PrintWriter outfile) {
        if (outfile == null)
            throw new IllegalArgumentException("outfile must not be null");
        outfile.println("Receipt");
        outfile.println(number);
        vehicle.save(outfile);
        writeDateTime(outfile,startTime);
    }

    private void writeDateTime(PrintWriter outfile, Calendar dateTime) {
        outfile.println(dateTime.get(Calendar.YEAR));
        outfile.println(dateTime.get(Calendar.MONTH));
        outfile.println(dateTime.get(Calendar.DAY_OF_MONTH));
        outfile.println(dateTime.get(Calendar.HOUR_OF_DAY));
        outfile.println(dateTime.get(Calendar.MINUTE));
    }

    public void load(Scanner infile) {
        if (infile == null) {
            throw new IllegalArgumentException("infile must not be null");
        }

    }



}
