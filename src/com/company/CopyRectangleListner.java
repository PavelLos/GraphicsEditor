package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * Created by pasha on 30.03.2016.
 */
public class CopyRectangleListner implements MouseListener, MouseMotionListener{
    private int previousX, previousY, drawX, drawY;
    private int width, height;
    private int x, y;
    private DrawPanel drawPanel;
    private BasicStroke stroke;

    public CopyRectangleListner(DrawPanel p){
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
        drawPanel.drawPreviousImag();
        drawX = e.getX();
        drawY = e.getY();
        findSizeOfRect();
        drawPanel.g2.setStroke(stroke);
        drawPanel.g2.setColor(Color.BLUE);
        drawPanel.g2.drawRect(x, y, width, height);
        drawPanel.repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        drawPanel.drawPreviousImag();
        drawX = e.getX();
        drawY = e.getY();
        findSizeOfRect();
        drawPanel.g2.setStroke(stroke);
        drawPanel.g2.setColor(Color.BLUE);
        drawPanel.paste.setPointsRectangle(x, y, width, height);
        drawPanel.g2.drawRect(x, y, width, height);
        drawPanel.createCopyImage();
        drawPanel.g2.setStroke(new BasicStroke());
        drawPanel.repaint();
    }

    public void findSizeOfRect() {
        x = Math.min(previousX, drawX);
        y = Math.min(previousY, drawY);
        width = Math.abs(previousX - drawX);
        height = Math.abs(previousY - drawY);

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
