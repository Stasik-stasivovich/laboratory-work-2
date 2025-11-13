
import acm.graphics.GCompound;

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
    public static GCompound create_Level(double getWidth, double getHeight, int what_level) {
        GCompound level = new GCompound();
        //рівень 1
        if (what_level == 1) {
            //метод 1 рівня
            build_level_1(level, getWidth, getHeight);
        }
        //повернення GCompound level
        return level;
    }

    /**
     * build_level_1 - метод 1 рівня void
     * @param level  - рівень 1.
     * @param getWidth  - довжина екрану getWidth отримана з класу Breakout
     * @param getHeight - висота екрану getHeight отримана з класу Breakout
     * variables:
     *         double step_x = 0.005 * getWidth; - відстань між цеглинками по довжині (X)
     *         double step_y = 0.005 * getHeight; - відстань між цеглинками по висоті (Y)
     *         double width_of_brick = ((getWidth-(10*step_x)) / 10); - ширина цеглинки. 10 - це кількість
     *         double height_of_brick = (getHeight/3) / 5; - висота цеглинки. 5 - це кількість
     *         double x_of_brick = 0; - початкові координати розміщення першої цеглинки по X
     *         double y_of_brick = 0; - початкові координати розміщення першої цеглинки по Y
     *         brick1 - обєкт цеглинки
     *
     */
    private static void build_level_1(GCompound level, double getWidth, double getHeight) {
        double step_x = 0.005 * getWidth;
        double step_y = 0.005 * getHeight;
        double width_of_brick = ((getWidth-(10*step_x)) / 10);
        double height_of_brick = (getHeight/3) / 5;
        double x_of_brick = 0;
        double y_of_brick = 0;

        //цикл будування висоти 6 рядків
        for (int y = 0; y < 6; y++) {
            //цикл будування ширини 10 стовпців
            for (int x = 0; x < 10; x++) {
                //створення обєкту - цеглинки
                Brick brick1 = new Brick(width_of_brick, height_of_brick);
                //умова кольору
                if (y % 2 == 0)
                    brick1.setColor_of_brick(Color.BLUE);

                else
                    brick1.setColor_of_brick(Color.YELLOW);
                level.add(brick1);
                brick1.setLocation(x_of_brick, y_of_brick);
                x_of_brick += (step_x + width_of_brick);
            }
            //наступні (змінені) координати розміщення цеглинки: для 2, 3,4... цеглинки
            x_of_brick=0;
            y_of_brick += (step_y + height_of_brick);

        }
    }

}




