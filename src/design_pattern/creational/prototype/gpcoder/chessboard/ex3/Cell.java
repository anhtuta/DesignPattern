package design_pattern.creational.prototype.gpcoder.chessboard.ex3;

import java.util.concurrent.TimeUnit;

enum Color {
    BLACK, WHITE;
}

public class Cell implements Cloneable {
    private String color;
    private String coordinate;

    public Cell(String color) {
        // Do more time to create an cell
        this.color = color;
        try {
            TimeUnit.MILLISECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(
                "New Cell object has been created using constructor with color: " + color);
    }

    public void setCoordinate(String coordinate) {
        this.coordinate = coordinate;
    }

    @Override
    public String toString() {
        return "Cell [color=" + color + ", coordinate=" + coordinate + "]";
    }

    @Override
    protected Cell clone() {
        try {
            return (Cell) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
