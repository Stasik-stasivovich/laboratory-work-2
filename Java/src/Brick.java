import acm.graphics.GRect;

import java.awt.*;

public class Brick extends GRect {
    private double width_of_brick;
    private double height_of_brick;
    private Color color_of_brick;

    public Brick(double x, double y, double width_of_brick_parameter, double height_of_brick_parameter) {
super( width_of_brick_parameter, height_of_brick_parameter);
        this.width_of_brick = width_of_brick_parameter;
        this.height_of_brick = height_of_brick_parameter;
        this.setColor(color_of_brick);
        this.setFilled(true);
    }

    public void setColor_of_brick(Color color_of_brick_parameter) {
        this.color_of_brick = color_of_brick_parameter;
        this.setFillColor(color_of_brick_parameter);
    }

}