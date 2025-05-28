package Model.GameMapPackage;
public class TrafficLight implements TrafficComponent {
    private TrafficSignal signal;

    public TrafficLight(TrafficSignal signal) {
        this.signal = signal;
    }

    @Override
    public void setSignal(TrafficSignal signal) {
        this.signal = signal;
    }

    @Override
    public boolean isSignalGreen() {
        return this.signal == TrafficSignal.GREEN;
    }
}
