package com.company;

import javafx.stage.FileChooser;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.plaf.FileChooserUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

/**
 * Created by pasha on 02.04.2016.
 */
public class OpenPaintClass extends JPanel implements ActionListener{

    private JFileChooser fileChooser;
    private String fileName;
    private DrawPanel drawPanel;
    private File file;

    public OpenPaintClass(DrawPanel p){
        drawPanel = p;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        fileChooser = new JFileChooser();
        int res = fileChooser.showOpenDialog(this);
        if (res == JFileChooser.APPROVE_OPTION) {
            try {
                fileName = fileChooser.getSelectedFile().getAbsolutePath();
                file = new File(fileName);
                fileChooser.addChoosableFileFilter(new ImageFileFilter("png"));
                drawPanel.setImag(ImageIO.read(file));
                drawPanel.repaint();
            }
            catch(IOException ex){
            JOptionPane.showMessageDialog(drawPanel, "ERROR");
        }
    }

    }

    class ImageFileFilter extends FileFilter {
        private String str;
        public ImageFileFilter(String s){
            str = s;
        }

        @Override
        public boolean accept(File f) {
            if(f.isDirectory()){
                return true;
            }
            return ( f.getName().endsWith("."+str));
        }

        @Override
        public String getDescription() {
            return  "*."+str;
        }

        public String getFileFilter(){
            return str;
        }
    }
}
