import java.util.Calendar;

/**
 * Class Token
 * @author ewg2
 */
public class Token {

    private boolean valid;
    private Calendar time;

    public Token(){

    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public Calendar getTime() {
        return time;
    }

    public void setTime(Calendar time) {
        this.time = time;
    }
}
