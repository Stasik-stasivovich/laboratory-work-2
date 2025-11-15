
import acm.graphics.GCompound;
import acm.graphics.GLabel;

import java.awt.*;

/**
 * Клас рівнів гри. Створення рівнів гри
 */
public class Create_Level {
    /**
     * public static GCompound create_level(param)
     * @param getWidth - довжина екрану getWidth отримана з класу Breakout
     * @param getHeight - висота екрану getHeight отримана з класу Breakout
     * @param what_level - рівень гри. Наприклад рівень 1, 2, 3.... Від рівня залежить важкість гри
     * @return GCompound level
     */
    public static GCompound create_Level(double getWidth, double getHeight, int what_level, int health) {
        GCompound level = new GCompound();
        //рівень 1
        if (what_level == 1)
            //метод 1 рівня
            build_level_1(level, getWidth, getHeight,health);

        // 2 рівень
        if (what_level == 2) {
            //метод 2 рівня
            build_level_2(level, getWidth, getHeight,health);
        }
        //повернення GCompound level
        return level;
    }

    /**
     * build_level_1 - метод 1 рівня void
     * @param level  - рівень 1.
     * @param getWidth  - довжина екрану getWidth отримана з класу Breakout
     * @param getHeight - висота екрану getHeight отримана з класу Breakout
     * @param health_parameter - здоров'я цеглинки
     * variables:
     *         double step_x = 0.005 * getWidth; - відстань між цеглинками по довжині (X)
     *         double step_y = 0.005 * getHeight; - відстань між цеглинками по висоті (Y)
     *         int amount_of_brick_y = 6; - кількість блоків по висоті
     *         int amount_of_brick_x = 10; - кількість блоків по довжині
     *         double width_of_brick = ((getWidth-(10*step_x)) / int amount_of_brick_x); - ширина цеглинки. int amount_of_brick_x- це кількість
     *         double height_of_brick = (getHeight/3) / int amount_of_brick_y; - висота цеглинки. int amount_of_brick_y - це кількість
     *         double x_of_brick = 0; - початкові координати розміщення першої цеглинки по X
     *         double y_of_brick = 0; - початкові координати розміщення першої цеглинки по Y
     *         brick1 - обєкт цеглинки
     *
     *         heath_of_bricks - об'єкт здоров'я цеглинки
     *         координати здоров1я цеглинки -
     *         Х = ((width_of_brick-health_of_breaks.getWidth())/2)+x_health,
     *         У = (height_of_brick-health_of_breaks.getHeight()/2) +y_health
     *         double x_health= 0; - змінна для координат здоров'я цеглинки Х
     *         double y_health= 0; - змінна для координат здоров'я цеглинки У
     *         x_health += (step_x + width_of_brick);
     *         y_health += (step_y + height_of_brick);
     *
     *         level_name_1  - обєкт назва рівня
     *         координати level_name_1 = (0, getHeight)
     *         Шрифт level_name_1 = "PT Serif", Font.BOLD, (int) height_of_brick/2
     *
     *
     */
    private static void build_level_1(GCompound level, double getWidth, double getHeight, int health_parameter) {
        double step_x = 0.005 * getWidth;
        double step_y = 0.005 * getHeight;
        int amount_of_brick_y = 6;
        int amount_of_brick_x = 10;
        double width_of_brick = ((getWidth-(10*step_x)) / amount_of_brick_x);
        double height_of_brick = (getHeight/3) / amount_of_brick_y;
        double x_of_brick = 0;
        double y_of_brick = 0;
        double x_health= 0;
        double y_health= 0;

        //цикл будування висоти 6 рядків
        for (int y = 0; y < amount_of_brick_y; y++) {
            //цикл будування ширини 10 стовпців
            for (int x = 0; x < amount_of_brick_x; x++) {
                //створення обєкту - цеглинки
                Brick brick1 = new Brick(width_of_brick, height_of_brick);


//створення обєкту Glabel health - здоров'я цеглинки
                GLabel health_of_breaks = new GLabel(Integer.toString(health_parameter));


                health_of_breaks.setFont(new Font("PT Serif", Font.BOLD, (int) height_of_brick/2));
                health_of_breaks.setLocation(((width_of_brick-health_of_breaks.getWidth())/2)+x_health, (height_of_brick-health_of_breaks.getHeight()/2) +y_health);
                //умова кольору
                if (y % 2 == 0)
                    brick1.setColor_of_brick(Color.BLUE);

                else
                    brick1.setColor_of_brick(Color.YELLOW);
                level.add(brick1);
                level.add(health_of_breaks);
                brick1.setLocation(x_of_brick, y_of_brick);

                x_of_brick += (step_x + width_of_brick);
                x_health += (step_x + width_of_brick);
            }
            //наступні (змінені) координати розміщення цеглинки: для 2, 3,4... цеглинки
            x_of_brick=0;
            x_health=0;
            y_of_brick += (step_y + height_of_brick);
            y_health += (step_y + height_of_brick);
        }
        // створення Glabel - назва рівня "Рівень 1"
        GLabel level_name_1 = new GLabel("Рівень 1", 0, getHeight);
        level_name_1.setFont(new Font("PT Serif", Font.BOLD, (int) height_of_brick/2));
        level.add(level_name_1);


    }


    /**    ДОРОБИТИ!!!! КОМЕНТАРІ МОЖЛИВО НЕ ПРАВИЛЬНІ
     *
     * @param level
     * @param getWidth
     * @param getHeight
     * @param health_parameter
     *
     *
     * variables:
     *         double step_x = 0.005 * getWidth; - відстань між цеглинками по довжині (X)
     *         double step_y = 0.005 * getHeight; - відстань між цеглинками по висоті (Y)
     *         int brick_in_piramide = 3; - кількість блоків про довжині
     *         int y_brick_in_piramide=brick_in_piramide; - кількість блоків по висоті
     *         double width_of_brick = ((getWidth-(brick_in_piramide*step_x)) / brick_in_piramide); - ширина цеглинки
     *         double height_of_brick = (getHeight/3) / brick_in_piramide; - висота цеглинки
     *         double x_of_brick = 0; - потім дорівнює x_of_brick += (step_x + width_of_brick/2); - це зсув блоку по ширині (щоб піраміда мала нормальний вигляд)
     *         double x_health= 0;
     *         double y_health= 0;
     *
     *
     *         Початкові координати прямокутника піраміди:
     *        Х =  x_of_brick+ x*(width_of_brick+step_x),
     *        У =  ((y_brick_in_piramide-1)*(height_of_brick)+(step_y*(y_brick_in_piramide-1)))+1- y*(height_of_brick+step_y)
     */
    private static void build_level_2(GCompound level, double getWidth, double getHeight, int health_parameter) {
        double step_x = 0.005 * getWidth;
        double step_y = 0.005 * getHeight;
        int brick_in_piramide = 3;
        int y_brick_in_piramide=brick_in_piramide;
        double width_of_brick = ((getWidth-(brick_in_piramide*step_x)) / brick_in_piramide);
        double height_of_brick = (getHeight/3) / brick_in_piramide;
        double x_of_brick = 0;
        double x_health= 0;
        double y_health= 0;









        for (int y = 0; y <y_brick_in_piramide; y++) {
            for (int x = 0; x < brick_in_piramide; x++) {



                Brick brick2 = new Brick(width_of_brick, height_of_brick);

                //створення обєкту Glabel health - здоров'я цеглинки
                GLabel health_of_breaks = new GLabel(Integer.toString(health_parameter));

                health_of_breaks.setFont(new Font("PT Serif", Font.BOLD, (int) height_of_brick/2));
                //health_of_breaks.setLocation(((width_of_brick-health_of_breaks.getWidth())/2)+x_health,brick_in_piramide*(height_of_brick+step_y) - (health_of_breaks.getHeight()/2)-y_health );
                //умова кольору
                if (y % 2 == 0)
                    brick2.setColor_of_brick(Color.BLUE);

                else
                    brick2.setColor_of_brick(Color.YELLOW);
                level.add(brick2);
                level.add(health_of_breaks);

                brick2.setLocation(x_of_brick+ x*(width_of_brick+step_x), ((y_brick_in_piramide-1)*(height_of_brick)+(step_y*(y_brick_in_piramide-1)))+1- y*(height_of_brick+step_y));
                brick2.setColor_of_brick(Color.WHITE);
                brick2.setColor(Color.BLACK);
             //   x_health+= (step_x + width_of_brick);
            }
           // x_health=width_of_brick/2;
            //y_health+= (step_y + height_of_brick);
            brick_in_piramide--;
            x_of_brick += (step_x + width_of_brick/2);
        }

        /*
       ДОРОБИТИ КООРДИНАТИ РОЗМІЩЕННЯ ЗДОРОВ'Я ЦЕГЛИНОК
         */

// створення Glabel - назва рівня "Рівень 2"
        GLabel level_name_1 = new GLabel("Рівень 2", 0, getHeight);
        level_name_1.setFont(new Font("PT Serif", Font.BOLD, (int) height_of_brick/2));
        level.add(level_name_1);


    }







}




