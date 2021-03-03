/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tspgeneticalg;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.geom.Line2D;

import javax.swing.JComponent;

import java.util.ArrayList;

public class LinesComponent extends JComponent{
    ArrayList<Line2D.Double> lines;
    Point[] points;
    LinesComponent(int bounds, Point[] points){
        super();
        setPreferredSize(new Dimension(bounds,bounds));
        lines = new ArrayList<>();
        this.points = points;
    }
    public void addLine(int x1, int y1, int x2, int y2){
        Line2D.Double line = new Line2D.Double(x1, y1, x2, y2);
        lines.add(line);
        repaint();
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.white);
        g.fillRect(0, 0, getWidth(), getHeight());
        Dimension d = getPreferredSize();
        g.setColor(Color.black);
        for (Line2D.Double line : lines) {
            g.drawLine(
                (int)line.getX1(),
                (int)line.getY1(),
                (int)line.getX2(),
                (int)line.getY2()
                );
        }
        for(Point p : points){
            g.drawOval((int)(p.x -2),(int) (p.y-2), 4,4);
        }
    }
}
