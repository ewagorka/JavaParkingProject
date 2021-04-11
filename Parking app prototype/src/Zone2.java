import java.io.PrintWriter;

public class Zone2 extends Zone {

    private float cost;
    public Zone2(){

    }

    @Override
    public float calculatePayment(long diff){
        float result = super.calculatePayment(diff)*cost;
        System.out.println(result);
        return result;
    }
    @Override
    public void save(PrintWriter outfile){
        outfile.println("ZONE 2");
        super.save(outfile);

    }

    @Override
    public String getZoneName(){
        return "Zone2";
    }

    @Override
    public String  toString(){
        return "Zone 2 Outside, longer-sized zone " + super.toString();
    }

    @Override
    public String zoneName(){
        return super.zoneName()+"2";
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }
}
