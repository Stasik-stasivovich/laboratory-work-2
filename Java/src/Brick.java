import acm.graphics.GCompound;
import acm.graphics.GLabel;
import acm.graphics.GRect;

import java.awt.*;

//клас цеглинок
public class Brick extends GCompound {
    private Color colorOfBrick;
    private int healthOfBrick;
    private GRect brick;
    private GLabel health;


    /**
     * конструктор цеглинок
     *
     * @param widthOfBrickParameter  - довжина цеглинок
     * @param heightOfBrickParameter - висота цеглинок
     * @param healthOfBrickParameter - здоров'я цеглинок
     */
    public Brick(double widthOfBrickParameter, double heightOfBrickParameter, int healthOfBrickParameter) {
        final Font font_of_brick = new Font("PT Serif", Font.BOLD, (int) (heightOfBrickParameter / 2));

        brick = new GRect(widthOfBrickParameter, heightOfBrickParameter);
        add(brick);


        health = new GLabel(Integer.toString(healthOfBrickParameter));
        health.setFont(font_of_brick);

        health.setLocation((widthOfBrickParameter - health.getWidth()) / 2, (heightOfBrickParameter - health.getAscent() / 2));
        health.setColor(Color.BLACK);
        add(health);

        healthOfBrick = healthOfBrickParameter;
    }

    /**
     * change color
     *
     * @param colorOfBrickParameter - Color
     */
    public void setColorOfBrick(Color colorOfBrickParameter) {
        this.colorOfBrick = colorOfBrickParameter;
        brick.setColor(colorOfBrick);
        brick.setFilled(true);
        if (colorOfBrick == Color.BLACK) {
            health.setColor(Color.YELLOW);
        }
    }

    public void hit(){
        healthOfBrick--;
        this.health.setLabel(Integer.toString(healthOfBrick));
    }
    public boolean isDead(){
        return healthOfBrick == 0;
    }


}