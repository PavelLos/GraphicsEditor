package com.company;

import java.awt.*;
import java.awt.event.*;

/**
 * Created by pasha on 21.04.2016.
 */
public class TextListener implements MouseListener, KeyListener {
    private int previousX;
    private int previousY;
    private int firstX;
    private DrawPanel drawPanel;

    public TextListener(DrawPanel p) {
        drawPanel = p;
        drawPanel.addMouseListener(this);
        drawPanel.drawPreviousImag();
        drawPanel.currentImag();
        drawPanel.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
    }

    @Override
    public void mousePressed(MouseEvent e) {
        previousX = e.getX();
        previousY = e.getY();
        firstX = previousX;
        drawPanel.requestFocus();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        drawPanel.g2.setFont(new Font("Ariel", Font.BOLD, 15));
        drawPanel.g2.setColor(drawPanel.drawColor);
        drawPanel.g2.setStroke(new BasicStroke((float) drawPanel.getSizePen()));
        if (e.getKeyChar() == KeyEvent.VK_ENTER) {
            previousY += 12;
            previousX = firstX;
        }
        else {
            drawPanel.g2.drawString(String.valueOf(e.getKeyChar()), previousX, previousY);
            previousX+=10;
            drawPanel.currentImag();
            drawPanel.repaint();
        }
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

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
