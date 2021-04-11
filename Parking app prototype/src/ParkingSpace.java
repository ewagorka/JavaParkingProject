/**
 * Class ParkingSpace
 * @author ewg2
 */
public class ParkingSpace {

    private int id;
    private boolean free;
    private Zone zone;
    public ParkingSpace(Zone z){
        zone = z;
    }

    public boolean isFree() {
        return free;
    }

    public void setFree(boolean free) {
        this.free = free;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Zone getZone() {
        return zone;
    }

    public void setZone(Zone zone) {
        this.zone = zone;
    }

    @Override
    public String toString() {
        String isFree;
        if(isFree())
            isFree="free";
        else
            isFree = "taken";
        return "\nParking Space " +
                id +
                " is currently " + isFree ;
    }

    public String parkingSpaceName(){
        return "Parking Space " +
                id;
    }
}
