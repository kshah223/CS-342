import java.io.Serializable;

public class Card implements Serializable{
    public String suite;
    public int value;
    public String fullName;

    public Card(String theSuite, int theValue){
        suite = theSuite;
        value = theValue;
    }

    String getSuite() { return suite; }

    int getValue() { return value; }

    String getImageString() {
        return fullName + ".png";
    }
}