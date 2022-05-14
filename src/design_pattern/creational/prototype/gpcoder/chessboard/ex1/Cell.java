package design_pattern.creational.prototype.gpcoder.chessboard.ex1;

import java.util.concurrent.TimeUnit;

enum Color {
    BLACK, WHITE;
}

public class Cell {
    private String color;

    public Cell(String color) {
        this.color = color;
        try {
            // More time to create an cell
            TimeUnit.MILLISECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(
                "New Cell object has been created using constructor with color: " + color);
    }

    @Override
    public String toString() {
        return "Cell [color=" + color + "]";
    }
}
