package mikebolt.circuitSolver;

import java.util.ArrayList;
import java.util.List;

public class Point extends ArrayList<Connection> {
   private List<Connection> connections;

   public Point() {
      super();
   }

   public Point(Connection connection) {
      super();
      add(connection);
   }

   public Point(List<Connection> connections) {
      super(connections);
   }
}
