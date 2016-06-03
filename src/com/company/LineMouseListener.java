package com.company;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * Created by Pavel_Los on 11.03.2016.
 */
public class LineMouseListener implements MouseListener, MouseMotionListener {

    private int previousX, previousY, drawX, drawY;
    private DrawPanel drawPanel;

    public LineMouseListener(DrawPanel p) {
        this.drawPanel = p;
        drawPanel.addMouseMotionListener(this);
        drawPanel.drawPreviousImag();
        drawPanel.currentImag();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        drawPanel.drawPreviousImag();
        drawX = e.getX();
        drawY = e.getY();
        drawPanel.g2.setColor(drawPanel.drawColor);
        drawPanel.g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        drawPanel.g2.setStroke(new BasicStroke((float) drawPanel.getSizePen()));
        drawPanel.g2.drawLine(previousX, previousY, drawX, drawY);
        drawPanel.repaint();

    }

    @Override
    public void mousePressed(MouseEvent e) {
        previousX = e.getX();
        previousY = e.getY();

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        drawPanel.drawPreviousImag();
        drawX = e.getX();
        drawY = e.getY();
        drawPanel.g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        drawPanel.g2.setStroke(new BasicStroke((float) drawPanel.getSizePen()));
        drawPanel.g2.drawLine(previousX, previousY, drawX, drawY);
        drawPanel.currentImag();
        drawPanel.repaint();

    }


    @Override
    public void mouseMoved(MouseEvent e) {

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

}
