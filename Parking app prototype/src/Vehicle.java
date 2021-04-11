import java.io.PrintWriter;
import java.util.Objects;
import java.util.Scanner;

public class Vehicle {

    private String license;
    private String type;
    private Zone zone;
    private ParkingSpace parkingSpace;
    private Receipt receipt;
    private boolean disabled;
    private boolean needAttendant;
    private Attendant attendant;


    public Vehicle(){}
    public Vehicle(String license, String type, Zone zone, ParkingSpace parkingSpace, boolean disabled, boolean needAttendant){
        this.license = license;
        this.type = type;
        this.zone = zone;
        this.parkingSpace = parkingSpace;
        this.disabled = disabled;
        this.needAttendant = needAttendant;
    }

    public void setParkingDetails(ParkingSpace ps){
        parkingSpace = ps;
        zone = ps.getZone();
    }


    public Receipt getReceipt() {
        return receipt;
    }

    public void setReceipt(Receipt receipt) {
        this.receipt = receipt;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Zone getZone() {
        return zone;
    }

    public void setZone(Zone zone) {
        this.zone = zone;
    }

    public ParkingSpace getParkingSpace() {
        return parkingSpace;
    }

    public void setParkingSpace(ParkingSpace parkingSpace) {
        this.parkingSpace = parkingSpace;
    }

    public boolean isNeedAttendant() {
        return needAttendant;
    }

    public void setNeedAttendant(boolean needAttendant) {
        this.needAttendant = needAttendant;
    }

    public Attendant getAttendant() {
        return attendant;
    }

    public void setAttendant(Attendant attendant) {
        this.attendant = attendant;
    }

    @Override
    public String toString() {
        return "Vehicle license plate : "+this.license+"\n and type: "+this.type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return Objects.equals(license, vehicle.license);
    }

    public void save(PrintWriter outfile) {
        if (outfile == null)
            throw new IllegalArgumentException("outfile must not be null");
        outfile.println(license);
        outfile.println(type);
        outfile.println(zone.zoneName());
        outfile.println(parkingSpace.getId());
        outfile.println(disabled);
        outfile.println(isNeedAttendant());
        if(isNeedAttendant()){
            outfile.println(attendant.getName());
        }
    }


}
