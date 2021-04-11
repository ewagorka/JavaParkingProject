import java.io.PrintWriter;

public class Zone5 extends Zone {
    private float cost ;

    public Zone5(){

    }

    @Override
    public void save(PrintWriter outfile){
       outfile.println("ZONE 5");
        super.save(outfile);

    }
    @Override
    public float calculatePayment(long diff){
        float result = super.calculatePayment(diff)*cost;
        System.out.println(result);
        return result;
    }

    @Override
    public String getZoneName(){
        return "Zone5";
    }

    @Override
    public String  toString(){
        return "Zone 5 Multi-story building, motorbike zone " + super.toString();
    }

    @Override
    public String zoneName(){
        return super.zoneName()+"5";
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }
}
