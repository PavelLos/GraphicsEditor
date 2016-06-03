package com.company;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.List;
import java.awt.event.*;
import java.util.*;


/**
 * Created by Pavel_Los on 25.02.2016.
 */

public class Window {
    private JPanel sliderPanel;
    private JSlider magnifierSlider;
    private JFrame frame;
    private DrawPanel drawPanel;
    private JScrollPane scrollPane;
    private JColorChooser chooser = new JColorChooser();
    private Hashtable<Integer, Component> sliderTable;
    private JToolBar toolBar;
    private JToolBar graphicToolBar;

    public Window(JFrame jFrame) {
        frame = jFrame;
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        frame.setTitle("Paint");
        frame.setBounds(screenWidth / 4, screenHeight / 4, screenWidth / 2, screenHeight / 2);
        drawPanel = new DrawPanel();
        scrollPane = new JScrollPane(drawPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.revalidate();
        drawPanel.setPreferredSize(new Dimension(drawPanel.getPanelWidth(), drawPanel.getPanelHeight()));
        frame.add(scrollPane, BorderLayout.CENTER);
        sliderPanel = new JPanel();
        magnifierSlider = getSlider();
        magnifierSlider.addChangeListener(new MagnifierListner(drawPanel, magnifierSlider, scrollPane));
        sliderPanel.add(magnifierSlider, BorderLayout.WEST);
        frame.add(sliderPanel, BorderLayout.SOUTH);
        CreateMenu();
        ToolBar();
    }

    public void CreateMenu() {
        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);

        JMenu file = new JMenu("File");
        file.setMnemonic('F');
        menuBar.add(file);

        JMenu edit = new JMenu("Edit");
        edit.setMnemonic('E');
        menuBar.add(edit);

        JMenuItem openFail = new JMenuItem("Open");
        openFail.setAccelerator(KeyStroke.getKeyStroke("ctrl O"));
        openFail.addActionListener(new OpenPaintClass(drawPanel));
        file.add(openFail);

        JMenuItem saveFail = new JMenuItem("Save");
        saveFail.addActionListener(new SavePaintAs(drawPanel));
        saveFail.setAccelerator(KeyStroke.getKeyStroke("ctrl S"));
        file.add(saveFail);
        file.addSeparator();

        JMenuItem exitFail = new JMenuItem("Выход");
        exitFail.setAccelerator(KeyStroke.getKeyStroke("ctrl E"));
        exitFail.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        file.add(exitFail);

        JMenuItem pasteItem = new JMenuItem("Paste");
        pasteItem.setAccelerator(KeyStroke.getKeyStroke("ctrl V"));
        pasteItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeListner();
                drawPanel.addMouseListener(new PasteListner(drawPanel));
            }
        });
        edit.add(pasteItem);

        JMenuItem copyRectangleItem = new JMenuItem("Copy Rectangle");
        copyRectangleItem.setAccelerator(KeyStroke.getKeyStroke("ctrl C"));
        copyRectangleItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeListner();
                drawPanel.addMouseListener(new CopyRectangleListner(drawPanel));
            }
        });
        edit.add(copyRectangleItem);

        JMenuItem copyPolygonItem = new JMenuItem("Copy Shape");
        copyPolygonItem.setAccelerator(KeyStroke.getKeyStroke("ctrl X"));
        copyPolygonItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeListner();
                drawPanel.addMouseListener(new CopyPolygonListner(drawPanel));
            }
        });
        edit.add(copyPolygonItem);
    }

    public void ToolBar() {
        toolBar = new JToolBar(SwingConstants.HORIZONTAL);
        graphicToolBar = new JToolBar(SwingConstants.VERTICAL);
        frame.add(toolBar, BorderLayout.NORTH);
        frame.add(graphicToolBar, BorderLayout.WEST);

        JButton saveFile = new JButton();
        saveFile.setIcon(new ImageIcon("picture\\save.png"));
        saveFile.addActionListener(new SavePaintAs(drawPanel));
        toolBar.add(saveFile);
        toolBar.addSeparator();

        JButton openFile = new JButton();
        openFile.setIcon(new ImageIcon("picture\\open.png"));
        openFile.addActionListener(new OpenPaintClass(drawPanel));
        toolBar.add(openFile);
        toolBar.addSeparator(new Dimension(30, 30));


        JButton paste = new JButton();
        paste.setIcon(new ImageIcon("picture\\paste.png"));
        paste.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeListner();
                drawPanel.addMouseListener(new PasteListner(drawPanel));
            }
        });
        toolBar.add(paste);

        JButton copyRectangle = new JButton();
        copyRectangle.setIcon(new ImageIcon("picture\\copyRec.png"));
        copyRectangle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeListner();
                drawPanel.addMouseListener(new CopyRectangleListner(drawPanel));
            }
        });
        toolBar.add(copyRectangle);

        JButton copyPolygon = new JButton();
        copyPolygon.setIcon(new ImageIcon("picture\\copy1.png"));
        copyPolygon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeListner();
                drawPanel.addMouseListener(new CopyPolygonListner(drawPanel));
            }
        });
        toolBar.add(copyPolygon);
        toolBar.addSeparator(new Dimension(30, 30));

        JButton colorChooser = new JButton();
        colorChooser.setMaximumSize(new Dimension(40, 40));
        colorChooser.setPreferredSize(new Dimension(40, 40));
        colorChooser.setBackground(Color.BLACK);
        colorChooser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                drawPanel.drawColor = chooser.showDialog(frame, "Choose a color", Color.black);
                colorChooser.setBackground(drawPanel.drawColor);
            }
        });
        toolBar.add(colorChooser);
        toolBar.addSeparator();

        JButton blackButton = new JButton();
        blackButton.setBackground(Color.BLACK);
        blackButton.setIcon(new ImageIcon("picture\\black.png"));
        blackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                drawPanel.drawColor = Color.BLACK;
                colorChooser.setBackground(Color.BLACK);
            }
        });
        toolBar.add(blackButton);

        JButton redButton = new JButton();
        redButton.setBackground(Color.RED);
        redButton.setIcon(new ImageIcon("picture\\red.png"));
        redButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                drawPanel.drawColor = Color.RED;
                colorChooser.setBackground(Color.RED);
            }
        });
        toolBar.add(redButton);

        JButton greenButton = new JButton();
        greenButton.setBackground(Color.GREEN.darker());
        greenButton.setIcon(new ImageIcon("picture\\green.png"));
        greenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                drawPanel.drawColor = Color.GREEN.darker();
                colorChooser.setBackground(Color.GREEN.darker());
            }
        });
        toolBar.add(greenButton);

        JButton blueButton = new JButton();
        blueButton.setBackground(Color.BLUE);
        blueButton.setIcon(new ImageIcon("picture\\blue.png"));
        blueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                drawPanel.drawColor = Color.BLUE;
                colorChooser.setBackground(Color.BLUE);
            }
        });
        toolBar.add(blueButton);
        toolBar.addSeparator(new Dimension(30, 30));

        String [] sizePenString = {
                "Size: 25%", "Size: 50%", "Size: 100%", "Size: 200%", "Size: 400%"
        };
        JComboBox sizePen = new JComboBox(sizePenString);
        sizePen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String item = ((String)sizePen.getSelectedItem()).substring(6, (((String) sizePen.getSelectedItem()).indexOf('%')));
                drawPanel.setSizePen(Double.parseDouble(item)/100);
            }
        });
        sizePen.setSelectedIndex(2);
        toolBar.add(sizePen);

        JButton pen = new JButton();
        pen.setIcon(new ImageIcon("picture\\pen1.png"));
        pen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeListner();
                drawPanel.addMouseListener(new PenMouseListener(drawPanel));
            }
        });
        graphicToolBar.add(pen);

        JButton line = new JButton();
        line.setIcon(new ImageIcon("picture\\line1.png"));
        line.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeListner();
                drawPanel.addMouseListener(new LineMouseListener(drawPanel));
            }
        });
        graphicToolBar.add(line);

        JButton rectangle = new JButton();
        rectangle.setIcon(new ImageIcon("picture\\rectangle.png"));
        rectangle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeListner();
                drawPanel.addMouseListener(new RectangleMouseListener(drawPanel));
            }
        });
        graphicToolBar.add(rectangle);

        JButton eraser = new JButton();
        eraser.setIcon(new ImageIcon("picture\\erase1.png"));
        eraser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeListner();
                drawPanel.addMouseListener(new EraserMouseListener(Color.white, drawPanel));
            }
        });
        graphicToolBar.add(eraser);

        JButton text = new JButton();
        text.setIcon(new ImageIcon("picture\\text1.png"));
        text.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeListner();
                drawPanel.addKeyListener(new TextListener(drawPanel));
            }
        });
        graphicToolBar.add(text);


    }


    public JSlider getSlider(){
        JSlider slider;
        slider = new JSlider(0, 100, 50);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setSnapToTicks(true);
        slider.setMajorTickSpacing(25);
        sliderTable = new Hashtable<Integer, Component>();
        sliderTable.put(0, new JLabel("25%"));
        sliderTable.put(25, new JLabel("50%"));
        sliderTable.put(50, new JLabel("100%"));
        sliderTable.put(75, new JLabel("200%"));
        sliderTable.put(100, new JLabel("400%"));
        slider.setLabelTable(sliderTable);
        return slider;
    }

    public void removeListner() {
        MouseMotionListener[] mml = drawPanel.getMouseMotionListeners();
        for (MouseMotionListener i : mml) {
            drawPanel.removeMouseMotionListener(i);
        }
        MouseListener[] ml = drawPanel.getMouseListeners();
        for (MouseListener i : ml) {
            drawPanel.removeMouseListener(i);
        }
        KeyListener[] kl = drawPanel.getKeyListeners();
        for (KeyListener i : kl) {
            drawPanel.removeKeyListener(i);
        }
        drawPanel.setCursor(Cursor.getDefaultCursor());
    }
}


