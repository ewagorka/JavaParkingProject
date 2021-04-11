import java.io.PrintWriter;

public class Zone4 extends Zone {
    private float cost ;
    public Zone4(){

    }

    @Override
    public void save(PrintWriter outfile){
        outfile.println("ZONE 4");
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
        return "Zone4";
    }
    @Override
    public String  toString(){
        return "Zone 4 Multi-story building, standard-sized zone " + super.toString();
    }

    @Override
    public String zoneName(){
        return super.zoneName()+"4";
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }
}
