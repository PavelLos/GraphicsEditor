package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Pavel_Los on 12.03.2016.
 */

public class DrawPanel extends JComponent {
    private BufferedImage imag;
    private BufferedImage previousImag;
    private BufferedImage copyImag;
    protected boolean magnifier = false;
    protected Graphics2D g2;
    protected Color drawColor = Color.BLACK;
    protected PastePoints paste;
    private int panelHeight;
    private int panelWidth;
    private double sizePen;

    public DrawPanel() {
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        panelHeight = screenSize.height;
        panelWidth = screenSize.width;
        paste = new PastePoints(this);
        sizePen = 1;
        sizePen = 1;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (imag == null) {
            imag = new BufferedImage(panelWidth, panelHeight, BufferedImage.TYPE_INT_RGB);
            g2 = (Graphics2D) imag.createGraphics();
            g2.setColor(Color.WHITE);
            g2.fillRect(0, 0, panelWidth, panelHeight);
        }
        g.drawImage(imag, 0, 0, null);
    }

    public void currentImag() {
        previousImag = new BufferedImage(panelWidth, panelHeight, BufferedImage.TYPE_INT_RGB);
        Graphics g2 = previousImag.getGraphics();
        g2.drawImage(imag, 0, 0, null);
    }


    public void drawPreviousImag() {
        Graphics g2 = imag.getGraphics();
        g2.drawImage(previousImag, 0, 0, null);
    }

    public void setImag(BufferedImage i){
        imag = new BufferedImage(i.getWidth(), i.getHeight(), BufferedImage.TYPE_INT_RGB);
        g2 = imag.createGraphics();
        g2.drawImage(i, 0, 0, null);
        currentImag();
    }

    public void setSizedImag(Image i, int w, int h){
        imag = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        g2 = (Graphics2D) imag.getGraphics();
        g2.drawImage(i, 0, 0, null);
        currentImag();
    }

    public void createCopyImage(){
        copyImag = new BufferedImage(panelWidth, panelHeight, BufferedImage.TYPE_INT_RGB);
        Graphics g = copyImag.getGraphics();
        g.drawImage(imag, 0, 0, null);
    }

    public void drawCopyImage(Polygon p){
        Graphics g2 = imag.getGraphics();
        g2.setClip(p);
        g2.drawImage(copyImag, paste.relocationX, paste.relocationY, this);
    }

    public BufferedImage getImage(){
        return imag;
    }

    public int getPanelHeight() {
        return panelHeight;
    }

    public int getPanelWidth() {
        return panelWidth;
    }

    public double getSizePen() {
        return sizePen;
    }

    public void setSizePen(double sizePen) {
        this.sizePen = sizePen;
    }
}
