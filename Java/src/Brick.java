import acm.graphics.GRect;

import java.awt.*;
//клас цеглинок
public class Brick extends GRect {
    private double width_of_brick;
    private double height_of_brick;
    private Color color_of_brick;


    /**
     * конструктор цеглинок
     *
     * @param width_of_brick_parameter  - довжина цеглинок
     * @param height_of_brick_parameter - висота цеглинок
     */
    public Brick(double width_of_brick_parameter, double height_of_brick_parameter) {
        super( width_of_brick_parameter, height_of_brick_parameter);
        this.width_of_brick = width_of_brick_parameter;
        this.height_of_brick = height_of_brick_parameter;
        this.setColor(color_of_brick);
        this.setFilled(true);
    }

    /**
     * сеттер кольору цеглинок
     *
     * @param color_of_brick_parameter - setColor of brick - встановлення кольору цеглинок через сеттер
     */
    public void setColor_of_brick(Color color_of_brick_parameter) {
        this.color_of_brick = color_of_brick_parameter;
        this.setFillColor(color_of_brick_parameter);
    }

}