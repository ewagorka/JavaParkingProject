import java.util.Objects;

/**
 * Class Attendant
 * @author ewg2
 */
public class Attendant {

    private String name;
    private Vehicle vehicle;

    public Attendant(){

    }

    /**
     * Checks if attendant is available
     * @return true or false
     */
    public boolean checkIfFree(){
        if(this.vehicle == null){
            return true;
        }
        else {
            return false;
        }
    }

//GETTERS AND SETTERS
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }


    @Override
    public String toString() {
        String vehicleString = "";
        if(vehicle!=null){
            vehicleString = " has the following vehicle : "+vehicle;
        }else{
            vehicleString = " has no vehicle";
        }
        return "Attendant " + name + vehicleString;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Attendant attendant = (Attendant) o;
        return Objects.equals(name, attendant.name);
    }

}
