package com.company;

import com.sun.xml.internal.ws.message.stream.StreamAttachment;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

/**
 * Created by pasha on 31.03.2016.
 */
public class SavePaintAs extends JPanel implements ActionListener {

    JFileChooser fileChooser;
    String fileName;
    DrawPanel drawPanel;

    public SavePaintAs(DrawPanel p){
        drawPanel = p;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            fileChooser = new JFileChooser();
            fileChooser.addChoosableFileFilter(new ImageFileFilter("png"));
            int res = fileChooser.showSaveDialog(this);
            if (res == JFileChooser.APPROVE_OPTION) {
                fileName = fileChooser.getSelectedFile().getAbsolutePath();
            }
            ImageIO.write(drawPanel.getImage(), "png", new File(fileName+".png"));
        }
        catch (IOException ex){
            JOptionPane.showMessageDialog(drawPanel, "ERROR");
        }

    }

    class ImageFileFilter extends FileFilter{
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
