package mikebolt.circuitSolver;

public class Battery extends Connection {
   public Battery(Point start, Point end, double voltage) {
      // The battery will be considered to have a resistance once it is fully solved,
      // but the value will not be meaningful.
      super(start, end, voltage, 0.0, 0.0, true, false, false);
   }
}
