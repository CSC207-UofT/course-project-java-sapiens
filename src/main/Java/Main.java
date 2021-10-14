import controllers.OrderSystem;
import ui.SystemInOut;

public class Main {
    public static void main(String[] args) {
        SystemInOut sio = new SystemInOut();
        OrderSystem os = new OrderSystem();
        os.run(sio);
    }
}
