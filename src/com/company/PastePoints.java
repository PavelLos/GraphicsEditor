package com.company;

import java.awt.*;
import java.util.ArrayList;


/**
 * Created by pasha on 06.04.2016.
 */
public class PastePoints {

    private DrawPanel drawPanel;
    private Polygon polygon;
    private ArrayList<Integer> xPoints, yPoints;
    private int previousX, previousY, drawX, drawY;
    private int[] figureX;
    private int[] figureY;
    private int[] pointRectangleX;
    private int[] pointRectangleY;
    protected int relocationX;
    protected int relocationY;

    public PastePoints(DrawPanel p) {
        drawPanel = p;
        xPoints = new ArrayList<Integer>();
        yPoints = new ArrayList<Integer>();
    }

    public void setPointsRectangle(int x, int y, int w, int h) {
        xPoints.add(x);
        yPoints.add(y);
        xPoints.add(x);
        yPoints.add(y + h);
        xPoints.add(x + w);
        yPoints.add(y + h);
        xPoints.add(x + w);
        yPoints.add(y);
        previousX = x;
        previousY = y;
        setXPointRectangle();
        setYPointRectangle();
        xPoints.clear();
        yPoints.clear();
    }

    public void setPointsPolygon(int x, int y) {
        xPoints.add(x);
        yPoints.add(y);
        setXPointRectangle();
        setYPointRectangle();
        previousX = xPoints.get(0);
        previousY = yPoints.get(0);
    }

    public void setXPointRectangle() {
        pointRectangleX = new int[xPoints.size()];
        for (int i = 0; i < xPoints.size(); i++) {
            pointRectangleX[i] = xPoints.get(i);
        }

    }

    public void setYPointRectangle() {
        pointRectangleY = new int[yPoints.size()];
        for (int i = 0; i < yPoints.size(); i++) {
            pointRectangleY[i] = yPoints.get(i);
        }
    }

    public void setMousePoints(int x, int y) {
        drawX = x;
        drawY = y;
        findPastePosition();
    }

    public void findPastePosition(){
        xPoints.clear();
        yPoints.clear();
        figureX = new int[pointRectangleX.length];
        figureY = new int[pointRectangleY.length];
        relocationX = drawX - previousX;
        relocationY = drawY - previousY;
        for (int i = 0; i < pointRectangleX.length; i++) {
            figureX[i] = pointRectangleX[i] + relocationX;
            figureY[i] = pointRectangleY[i] + relocationY;
        }
    }

    public int[] getX() {
        return figureX;
    }

    public int[] getY() {
        return figureY;
    }

    public int[] getPointRectangleX() {
        xPoints.clear();
        return pointRectangleX;
    }

    public int[] getPointRectangleY() {
        yPoints.clear();
        return pointRectangleY;
    }

    public int getDrawX() {
        return drawX;
    }

    public int getDrawY() {
        return drawY;
    }
}
