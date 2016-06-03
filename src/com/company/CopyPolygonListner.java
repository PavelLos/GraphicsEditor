package com.company;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * Created by pasha on 08.04.2016.
 */
public class CopyPolygonListner implements MouseListener, MouseMotionListener
{
    private int previousX, previousY, drawX, drawY;
    private DrawPanel drawPanel;
    private BasicStroke stroke;

    public CopyPolygonListner(DrawPanel p){
        drawPanel = p;
        drawPanel.addMouseMotionListener(this);
        float[] shtrich = {14, 5};
        stroke = new BasicStroke(1, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 3, shtrich, 0);
        drawPanel.drawPreviousImag();
        drawPanel.currentImag();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        previousX = e.getX();
        previousY = e.getY();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        drawX = e.getX();
        drawY = e.getY();
        drawPanel.paste.setPointsPolygon(drawX, drawY);
        drawPanel.g2.setColor(Color.BLUE);
        drawPanel.g2.setStroke(new BasicStroke(1));
        drawPanel.g2.drawLine(previousX, previousY, drawX, drawY);
        drawPanel.repaint();
        previousX = drawX;
        previousY = drawY;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        drawPanel.drawPreviousImag();
        int[] x1 = drawPanel.paste.getPointRectangleX();
        int[] y1 = drawPanel.paste.getPointRectangleY();
        drawPanel.g2.setColor(Color.BLUE);
        drawPanel.g2.setStroke(stroke);
        drawPanel.g2.drawPolygon(x1, y1, x1.length);
        drawPanel.g2.setStroke(new BasicStroke());
        drawPanel.repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }
}
