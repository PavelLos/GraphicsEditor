package com.company;


import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.SliderUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.im.InputContext;
import java.awt.image.BufferedImage;
import java.util.Dictionary;

/**
 * Created by pasha on 08.04.2016.
 */
public class MagnifierListner implements ChangeListener{
    private DrawPanel drawPanel;
    private Image bigImag;
    private JSlider sizeSlider;
    private JScrollPane scrollPane;

    public MagnifierListner(DrawPanel p, JSlider s, JScrollPane scroll){
        drawPanel = p;
        bigImag = null;
        sizeSlider = s;
        scrollPane = scroll;
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        switch (sizeSlider.getValue()){
            case 0:{
                drawPanel.magnifier = true;
                setSize(0.25);
                break;
            }
            case 25:{
                drawPanel.magnifier = true;
                setSize(0.5);
                break;
            }
            case 50:{
                setSize(1);
                break;
            }
            case 75:{
                drawPanel.magnifier = true;
                setSize(2);
                break;
            }
            case 100:{
                drawPanel.magnifier = true;
                setSize(4);
                break;
            }
        }
    }

    void setSize(double size){
        if(drawPanel.magnifier != false) {
            bigImag = null;
            int width = (int) (drawPanel.getPanelWidth() * size);
            int height = (int) (drawPanel.getPanelHeight() * size);
            drawPanel.setPreferredSize(new Dimension(width, height));
            scrollPane.getViewport().revalidate();
            bigImag = drawPanel.getImage().getScaledInstance(width, height, BufferedImage.TYPE_INT_RGB);
            drawPanel.setSizedImag(bigImag, width, height);
            drawPanel.repaint();
        }
    }

}
