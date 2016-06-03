package com.company;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.MemoryImageSource;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by pasha on 25.03.2016.
 */
public class EraserMouseListener implements MouseMotionListener, MouseListener {

    private Toolkit toolKit;
    private Image erCursor;
    private int size;
    private int x, y;
    private int previousX, previousY, drawX, drawY;
    private DrawPanel drawPanel;

    public EraserMouseListener(Color c, DrawPanel p) {
        this.drawPanel = p;
        drawPanel.g2.setColor(c);
        drawPanel.addMouseMotionListener(this);
        toolKit = Toolkit.getDefaultToolkit();
        size = 12;
        erCursor = Toolkit.getDefaultToolkit().getImage("picture\\eraserCur.png");
        drawPanel.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(erCursor, new Point(0, 0), null));
    }

    @Override
    public void mouseDragged(MouseEvent e) {


        drawX = e.getX();
        drawY = e.getY();
        drawPanel.g2.setColor(Color.WHITE);
        drawPanel.g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        drawPanel.g2.setStroke(new BasicStroke((float) drawPanel.getSizePen()*size));
        drawPanel.g2.drawLine(previousX, previousY, drawX, drawY);
        drawPanel.repaint();
        previousX = drawX;
        previousY = drawY;
        drawPanel.currentImag();

    }

    @Override
    public void mousePressed(MouseEvent e) {
        previousX = e.getX();
        previousY = e.getY();
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
    public void mouseMoved(MouseEvent e) {

    }
}
