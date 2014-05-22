package mikebolt.circuitSolver;

public class Connection {
   private static final double EPSILON = 0.000001;

   private Point start;
   private Point end;
   private double voltage; // Actually represents the voltage difference.
   private double current;
   private double resistance;
   private boolean voltageKnown;
   private boolean currentKnown;
   private boolean resistanceKnown;
   private boolean valid;

   public Connection(Point start, Point end, double voltage, double current,
    double resistance, boolean voltageKnown, boolean currentKnown,
    boolean resistanceKnown) {
      this.start = start;
      this.end = end;
      this.voltage = voltage;
      this.current = current;
      this.resistance = resistance;
      this.voltageKnown = voltageKnown;
      this.currentKnown = currentKnown;
      this.resistanceKnown = resistanceKnown;

      start.add(this);
      end.add(this);

      solveUnknowns();
      validate();
   }

   public Connection(Point start, Point end) {
      this(start, end, 0.0, 0.0, 0.0, false, false, false);
   }

   public Point getStart() {
      return start;
   }

   public Point getEnd() {
      return end;
   }

   public void setVoltage(double voltage) {
      this.voltage = voltage;
      voltageKnown = true;
   }

   public double getVoltage() {
      return voltage;
   }

   public void setCurrent(double current) {
      this.current = current;
      currentKnown = true;
   }

   public double getCurrent() {
      return current;
   }

   public void setResistance(double resistance) {
      this.resistance = resistance;
      resistanceKnown = true;
   }

   public double getResistance() {
      return resistance;
   }

   /**
    * If possible, solve for an unknown of this connection.
    */
   public void solveUnknowns() {
      if (currentKnown && resistanceKnown && !voltageKnown) {
         setVoltage(current * resistance);
      }
      else if (voltageKnown && currentKnown && !resistanceKnown) {
         if (current == 0.0) {
            if (voltage > 0.0) {
               setResistance(Double.POSITIVE_INFINITY);
            }
            else if (voltage < 0.0) {
               setResistance(Double.NEGATIVE_INFINITY);
            }
            // Otherwise, the resistance cannot be solved.
         }
         else {
            setResistance(voltage / current);
         }
      }
      else if (voltageKnown && resistanceKnown && !currentKnown) {
         if (resistance == 0.0) {
            if (voltage > 0.0) {
               setCurrent(Double.POSITIVE_INFINITY);
            }
            else if (voltage < 0.0) {
               setCurrent(Double.NEGATIVE_INFINITY);
            }
            // Otherwise, the current cannot be solved.
         }
         else {
            setCurrent(voltage / resistance);
         }
      }
   }

   private void validate() {
      valid = currentKnown && voltageKnown && resistanceKnown &&
       current * resistance >= voltage - EPSILON &&
       current * resistance <= voltage + EPSILON;
   }
}
