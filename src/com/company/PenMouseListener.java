package com.company;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;


/**
 * Created by Pavel_Los on 11.03.2016.
 */
public class PenMouseListener implements MouseListener,MouseMotionListener {
    private Image erCursor;
    private int previousX, previousY, drawX, drawY;
    private DrawPanel drawPanel;

    public PenMouseListener(DrawPanel p) {
        this.drawPanel = p;
        drawPanel.addMouseMotionListener(this);
        drawPanel.drawPreviousImag();
        drawPanel.currentImag();
        erCursor = Toolkit.getDefaultToolkit().getImage("picture\\penCur.gif");
        drawPanel.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(erCursor, new Point(0, 0), null));
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
        drawPanel.g2.setColor(drawPanel.drawColor);
        drawPanel.g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        drawPanel.g2.setStroke(new BasicStroke((float) drawPanel.getSizePen()));
        drawPanel.g2.drawLine(previousX, previousY, drawX, drawY);
        drawPanel.repaint();
        previousX = drawX;
        previousY = drawY;
        drawPanel.currentImag();
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
