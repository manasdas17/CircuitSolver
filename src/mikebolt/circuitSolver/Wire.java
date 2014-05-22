package mikebolt.circuitSolver;

public class Wire extends Connection {
   public Wire(Point start, Point end) {
      super(start, end, 0.0, 0.0, 0.0, true, false, true);
   }
}
