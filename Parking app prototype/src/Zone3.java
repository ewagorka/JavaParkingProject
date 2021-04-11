import java.io.PrintWriter;

public class Zone3 extends Zone {

    private float cost;
    public Zone3(){

    }

    @Override
    public float calculatePayment(long diff){
        float result = super.calculatePayment(diff)*cost;
        System.out.println(result);
        return result;
    }

    @Override
    public void save(PrintWriter outfile){
        outfile.println("ZONE 3");
        super.save(outfile);

    }

    @Override
    public String getZoneName(){
        return "Zone3";
    }
    @Override
    public String  toString(){
        return "Zone 3 Outside, coach zone " + super.toString();
    }

    @Override
    public String zoneName(){
        return super.zoneName()+"3";
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }
}
