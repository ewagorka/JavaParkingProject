import java.io.PrintWriter;

public class Zone1 extends Zone {

    private float cost;

    public Zone1(){

    }

    @Override
    public float calculatePayment(long diff){

        float result = super.calculatePayment(diff)*cost;
        return result;
    }
    @Override
    public void save(PrintWriter outfile){
        outfile.println("ZONE 1");
        super.save(outfile);

    }
    @Override
    public String getZoneName(){
        return "Zone1";
    }

    @Override
    public String  toString(){
        return "Zone 1 Outside, standard-sized zone " + super.toString();
    }

    @Override
    public String zoneName(){
        return super.zoneName()+"1";
    }

    public float getCost() {
        return cost;
    }
    public void setCost(float cost) {
        this.cost = cost;
    }
}
