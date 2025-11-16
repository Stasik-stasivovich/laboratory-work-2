import acm.graphics.GCompound;
import acm.graphics.GLabel;
import acm.graphics.GRect;

import java.awt.*;

//клас цеглинок
public class Brick extends GCompound {
    private Color color_of_brick;
    private int healthOfBrick;
    private GRect brick;
    private GLabel health;


    /**
     * конструктор цеглинок
     *
     * @param width_of_brick_parameter  - довжина цеглинок
     * @param height_of_brick_parameter - висота цеглинок
     * @param health_of_brick_parameter - здоров'я цеглинок
     *                                  <p>
     *                                  Grect brick - цеглинка
     *                                  Glabel health - здоров'я цеглинок
     *                                  <p>
     *                                  координати здоров'я цеглинок:
     *                                  Х = (width_of_brick_parameter-health.getWidth())/2,
     *                                  У = (height_of_brick_parameter-health.getAscent()/2)
     *                                  <p>
     *                                  шрифт (int) (height_of_brick_parameter/2)
     *
     */
    public Brick(double width_of_brick_parameter, double height_of_brick_parameter, int health_of_brick_parameter) {
        final Font font_of_brick = new Font("PT Serif", Font.BOLD, (int) (height_of_brick_parameter / 2));

        brick = new GRect(width_of_brick_parameter, height_of_brick_parameter);
        add(brick);


        health = new GLabel(Integer.toString(health_of_brick_parameter));
        health.setFont(font_of_brick);
        health.setLocation((width_of_brick_parameter - health.getWidth()) / 2, (height_of_brick_parameter - health.getAscent() / 2));
        health.setColor(Color.BLACK);
        add(health);

        healthOfBrick = health_of_brick_parameter;
    }

    /**
     * сеттер кольору цеглинок
     *
     * @param color_of_brick_parameter - setColor of brick - встановлення кольору цеглинок через сеттер
     */
    public void setColor_of_brick(Color color_of_brick_parameter) {
        this.color_of_brick = color_of_brick_parameter;
        brick.setColor(color_of_brick);
        brick.setFilled(true);
    }

    public void hit(){
        healthOfBrick--;
        this.health.setLabel(Integer.toString(healthOfBrick));
    }
    public boolean isDead(){
        return healthOfBrick == 0;
    }


}