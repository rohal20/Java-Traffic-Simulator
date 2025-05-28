package Model.GameMapPackage;
import java.util.ArrayList;
import java.util.List;
public class TrafficControl implements TrafficComponent {
    private List<TrafficComponent> controls = new ArrayList<>();

    @Override
    public void setSignal(TrafficSignal signal) {
        for (TrafficComponent control : controls) {
            control.setSignal(signal);
        }
    }

    @Override
    public boolean isSignalGreen() {
        for (TrafficComponent control : controls) {
            if (!control.isSignalGreen()) {
                return false;
            }
        }
        return true;
    }
}
