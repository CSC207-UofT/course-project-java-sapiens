import presenters.OrderSystem;
import presenters.SystemInOut;

public class Main {
    public static void main(String[] args) {
        SystemInOut sio = new SystemInOut();
        OrderSystem os = new OrderSystem();
        os.run(sio);
    }
}
