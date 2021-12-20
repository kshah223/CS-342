import java.io.Serializable;

public class SamplePacket implements Serializable {
    String message;
    public SamplePacket(String message){
        this.message = message;
    }
}
