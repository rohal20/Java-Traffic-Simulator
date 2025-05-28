package GameMapPackage;

public class TrafficLight {
    TrafficSignal signal;

    public TrafficLight(TrafficSignal signal) {this.signal = signal;}

    // Method to set the traffic signal
    public void setSignal(TrafficSignal signal) {this.signal = signal;}

    // To check if light is green
    public boolean isSignalGreen() {return this.signal == TrafficSignal.GREEN;}

}

