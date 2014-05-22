package mikebolt.circuitSolver;

public class Resistor extends Connection {
   public Resistor(Point start, Point end, double resistance) {
      super(start, end, 0.0, 0.0, resistance, false, false, true);
   }
}
