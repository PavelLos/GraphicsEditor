package com.company;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * Created by pasha on 25.03.2016.
 */

public class RectangleMouseListener implements MouseListener, MouseMotionListener {

    private int previousX, previousY, drawX, drawY;
    private int width, height;
    private int x, y;
    private DrawPanel drawPanel;

    public RectangleMouseListener(DrawPanel p) {
        this.drawPanel = p;
        drawPanel.addMouseMotionListener(this);
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
        //drawPanel.g2.setXORMode(drawPanel.drawColor);
        drawPanel.g2.setColor(drawPanel.drawColor);
        drawPanel.g2.setStroke(new BasicStroke((float) drawPanel.getSizePen()));
        drawPanel.g2.drawRect(x, y, width, height);
        drawPanel.repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        drawPanel.drawPreviousImag();
        drawX = e.getX();
        drawY = e.getY();
        findSizeOfRect();
        //drawPanel.g2.setXORMode(drawPanel.drawColor);
        drawPanel.g2.setColor(drawPanel.drawColor);
        drawPanel.g2.setStroke(new BasicStroke((float) drawPanel.getSizePen()));
        drawPanel.g2.fillRect(x, y, width, height);
        drawPanel.currentImag();
        drawPanel.repaint();
    }

    public void findSizeOfRect() {
        if (previousX < drawX) {
            x = previousX;
        } else {
            x = drawX;
        }
        if (previousY < drawY) {
            y = previousY;
        } else {
            y = drawY;
        }
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
