import java.awt.*;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Edge {

    Map<Line2D, Color> lines = new HashMap<Line2D, Color>();
    Map<Integer, Line2D> sequence = new HashMap<Integer, Line2D>();
    Line2D edgeAB = new Line2D.Float(133, 216, 272, 69);
    Line2D edgeBC = new Line2D.Float(338, 50, 469, 50);
    Line2D edgeBD = new Line2D.Float(300, 100, 300, 402);
    Line2D edgeDE = new Line2D.Float(336, 421, 468, 421);
    Line2D edgeEF = new Line2D.Float(527, 405, 671, 258);
    Line2D edgeAD = new Line2D.Float(133, 257, 274, 404);

    public Edge() {
        lines.put(edgeAB, Color.black);
        sequence.put(0, edgeAB);
        lines.put(edgeBC, Color.black);
        sequence.put(1, edgeBC);
        lines.put(edgeBD, Color.black);
        sequence.put(2, edgeBD);
        lines.put(edgeDE, Color.black);
        sequence.put(3, edgeDE);
        lines.put(edgeEF, Color.black);
        sequence.put(4, edgeEF);
        lines.put(edgeAD, Color.black);
        sequence.put(5, edgeAD);

    }

    public void changeColorNext(int key) {
        lines.replace(sequence.get(key), Color.RED);
    }
}