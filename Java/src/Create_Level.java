
import acm.graphics.GCompound;

import java.awt.*;

public class Create_Level {
    public static GCompound create_Level(double getWidth, double getHeight, int what_level) {
        GCompound level = new GCompound();
        if (what_level == 1) {
            build_level_1(level, getWidth, getHeight);
        }

        return level;
    }

    private static void build_level_1(GCompound level, double getWidth, double getHeight) {
        double step_x = 0.01 * getWidth;
        double step_y = 0.01 * getHeight;
        double width_of_brick = getWidth / (10);
        double height_of_brick = getHeight / (5);
        double x_of_brick = 0;
        double y_of_brick = 0;
        for (int y = 0; y < 5; y++) {
            for (int x = 0; x < 10; x++) {

                Brick brick1 = new Brick(x_of_brick, y_of_brick, width_of_brick, height_of_brick);
                if (y % 2 == 0)
                    brick1.setColor_of_brick(Color.BLUE);

                else
                    brick1.setColor_of_brick(Color.YELLOW);
                level.add(brick1);
                brick1.setLocation(x_of_brick, y_of_brick);
                x_of_brick += (step_x + width_of_brick);
            }
            y_of_brick += (step_y + height_of_brick);
        }
    }

}




