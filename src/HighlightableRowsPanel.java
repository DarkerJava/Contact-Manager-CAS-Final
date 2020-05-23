import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

class HighlightableRowsPanel extends JPanel {
    ArrayList<Integer> rows;
    private int rowHeight;
    private ArrayList<? extends JComponent> components;
    private Color highlightColor;
    //private GridBagLayout layout;

    public HighlightableRowsPanel(ArrayList<? extends JComponent> components, int rowHeight) {
        this.setOpaque(true);
        this.setBackground(Color.white);
        rows = new ArrayList<>();
        this.rowHeight = rowHeight;
        this.components = components;
        highlightColor = new java.awt.Color(0, 51, 153);
        //this.layout = layout;
    }


    public void highlightRow(int row) {
        rows.add(row);
        //this.paintImmediately(0, row * rowHeight, this.getWidth(), rowHeight);
        this.repaint();
    }

    public void unhighlightRow(int row) {
        rows.remove((Integer) row);
        //this.paintImmediately(0, row * rowHeight, this.getWidth(), rowHeight);
        this.repaint();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //g.setColor(Color.WHITE);
        //g.fillRect(0, 0, this.getWidth(), this.getHeight());
        g.setColor(highlightColor);
        for (int row : rows) {
            g.fillRect(0, components.get(row).getY(), this.getWidth(), rowHeight);
        }

    }

    public void setHighlighterColor(Color c) {
        highlightColor = c;
        this.repaint();
    }

}