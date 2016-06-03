package com.company;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * Created by pasha on 07.04.2016.
 */
public class PasteListner implements MouseListener, MouseMotionListener{

    private DrawPanel drawPanel;
    private int previousX, previousY, drawX, drawY;
    private Polygon polygon;
    private BasicStroke stroke;
    private int [] xMas;
    private int [] yMas;

    public PasteListner(DrawPanel p){
        drawPanel = p;
        drawPanel.addMouseMotionListener(this);
        float[] space = {14, 5};
        stroke = new BasicStroke(1, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 3, space, 0);
        drawPanel.drawPreviousImag();
        drawPanel.currentImag();
        drawPanel.createCopyImage();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        previousX = e.getX();
        previousY = e.getY();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        drawPanel.drawPreviousImag();
        drawX = e.getX();
        drawY = e.getY();
        drawPanel.paste.setMousePoints(drawX, drawY);
        xMas = drawPanel.paste.getX();
        yMas = drawPanel.paste.getY();
        polygon = new Polygon(xMas, yMas , xMas.length);
        drawPanel.drawCopyImage(polygon);
        drawPanel.repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        drawPanel.drawPreviousImag();
        drawX = e.getX();
        drawY = e.getY();
        drawPanel.paste.setMousePoints(drawX, drawY);
        xMas = drawPanel.paste.getX();
        yMas = drawPanel.paste.getY();
        polygon = new Polygon(xMas,yMas , xMas.length);
        drawPanel.drawCopyImage(polygon);
        drawPanel.currentImag();
        drawPanel.repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

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
}
