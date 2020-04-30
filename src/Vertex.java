import javax.swing.*;
import java.awt.*;

public class Vertex extends JButton {
    String label;
    boolean wasVisited;
    int rowIndex;
    int columnIndex;


    public Vertex(String lab, int rowIndex, int columnIndex) {
        label = lab;
        setText(lab);
        setContentAreaFilled(false);
        setBorder(BorderFactory.createLineBorder(Color.black, 2, true)); // Especially important
        setFont(new Font("Arial", Font.PLAIN, 40));
        setForeground(Color.BLACK);
        this.rowIndex = rowIndex;
        this.columnIndex = columnIndex;
        wasVisited = false;
    }
}



