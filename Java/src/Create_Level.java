
    import acm.graphics.GCompound;
    import acm.graphics.GLabel;

    import java.awt.*;

    /**
     * Клас рівнів гри. Створення рівнів гри
     */
    public class Create_Level {
        /**
         * public static GCompound create_level(param)
         *
         * @param getWidth   - довжина екрану getWidth отримана з класу Breakout
         * @param getHeight  - висота екрану getHeight отримана з класу Breakout
         * @param what_level - рівень гри. Наприклад рівень 1, 2, 3.... Від рівня залежить важкість гри
         * @return GCompound level
         */
        public static GCompound create_Level(double getWidth, double getHeight, int what_level, int health) {
            GCompound level = new GCompound();
            //рівень 1
            if (what_level == 1)
                //метод 1 рівня
                buildLevel_1(level, getWidth, getHeight, health);

            // 2 рівень
            if (what_level == 2)
                //метод 2 рівня
                buildLevel2(level, getWidth, getHeight, health);

            // 3 рівень
            if (what_level == 3)
                //метод 3 рівня
                buildLevel3(level, getWidth, getHeight, health);

            //4 рівень
            if (what_level == 4) {
                //метод 4 рівня
                build_level_4(level, getWidth, getHeight, health);
            }

            //5 рівень
            if (what_level == 5) {
                //метод 5 рівня
                buildLevel5(level, getWidth, getHeight, health);
            }



            //повернення GCompound level
            return level;
        }

        /**
         * build_level_1 - метод 1 рівня void
         *
         * @param level            - рівень 1.
         * @param getWidth         - довжина екрану getWidth отримана з класу Breakout
         * @param getHeight        - висота екрану getHeight отримана з класу Breakout
         * @param health_parameter - здоров'я цеглинки
         *                         variables:
         *                         double step_x = 0.005 * getWidth; - відстань між цеглинками по довжині (X)
         *                         double step_y = 0.005 * getHeight; - відстань між цеглинками по висоті (Y)
         *                         int amount_of_brick_y = 6; - кількість блоків по висоті
         *                         int amount_of_brick_x = 10; - кількість блоків по довжині
         *                         double width_of_brick = ((getWidth-(10*step_x)) / int amount_of_brick_x); - ширина цеглинки. int amount_of_brick_x- це кількість
         *                         double height_of_brick = (getHeight/3) / int amount_of_brick_y; - висота цеглинки. int amount_of_brick_y - це кількість
         *                         double x_of_brick = 0; - початкові координати розміщення першої цеглинки по X
         *                         double y_of_brick = 0; - початкові координати розміщення першої цеглинки по Y
         *                         brick1 - обєкт цеглинки
         *                         <p>
         *                         heath_of_bricks - об'єкт здоров'я цеглинки
         *                         координати здоров1я цеглинки -
         *                         Х = ((width_of_brick-health_of_breaks.getWidth())/2)+x_health,
         *                         У = (height_of_brick-health_of_breaks.getHeight()/2) +y_health
         *                         double x_health= 0; - змінна для координат здоров'я цеглинки Х
         *                         double y_health= 0; - змінна для координат здоров'я цеглинки У
         *                         x_health += (step_x + width_of_brick);
         *                         y_health += (step_y + height_of_brick);
         *                         <p>
         *                         level_name_1  - обєкт назва рівня
         *                         координати level_name_1 = (0, getHeight)
         *                         Шрифт level_name_1 = "PT Serif", Font.BOLD, (int) height_of_brick/2
         *
         *
         */
        private static void buildLevel_1(GCompound level, double getWidth, double getHeight, int health_parameter) {
            double stepX = 0.005 * getWidth;
            double stepY = 0.005 * getHeight;
            int amountOfBrickY = 6;
            int amountOfBrickX = 10;
            double widthOfBrick = ((getWidth - (amountOfBrickX * stepX)) / amountOfBrickX);
            double heightOfBrick = (getHeight / 3) / amountOfBrickY;
            double xOfBrick = 0;
            double yOfBrick = 0;
            //цикл будування висоти 6 рядків
            for (int y = 0; y < amountOfBrickY; y++) {
                //цикл будування ширини 10 стовпців
                for (int x = 0; x < amountOfBrickX; x++) {
                    //створення обєкту - цеглинки
                    Brick brick1 = new Brick(widthOfBrick, heightOfBrick, health_parameter);


                    //умова кольору
                    if (y % 2 == 0)
                        brick1.setColorOfBrick(Color.BLUE);

                    else
                        brick1.setColorOfBrick(Color.YELLOW);


                    level.add(brick1);
                    brick1.setLocation(xOfBrick, 1 + yOfBrick);

                    xOfBrick += (stepX + widthOfBrick);
                }
                //наступні (змінені) координати розміщення цеглинки: для 2, 3,4... цеглинки
                xOfBrick = 0;
                yOfBrick += (stepY + heightOfBrick);
            }
            // створення Glabel - назва рівня "Рівень 1"
            GLabel levelName1 = new GLabel("Рівень 1", 0, getHeight);
            levelName1.setFont(new Font("PT Serif", Font.BOLD, (int) heightOfBrick / 2));
            level.add(levelName1);


        }


        /**
         *
         * @param level            - рівень 2.
         * @param getWidth         - довжина екрану getWidth отримана з класу Breakout
         * @param getHeight        - висота екрану getHeight отримана з класу Breakout
         * @param health_parameter - здоров'я цеглинки
         *                         <p>
         *                         <p>
         *                         variables:
         *                         double step_x = 0.005 * getWidth; - відстань між цеглинками по довжині (X)
         *                         double step_y = 0.005 * getHeight; - відстань між цеглинками по висоті (Y)
         *                         int brick_in_piramide = 3; - кількість блоків про довжині
         *                         int y_brick_in_piramide=brick_in_piramide; - кількість блоків по висоті
         *                         double width_of_brick = ((getWidth-(brick_in_piramide*step_x)) / brick_in_piramide); - ширина цеглинки
         *                         double height_of_brick = (getHeight/3) / brick_in_piramide; - висота цеглинки
         *                         double x_of_brick = 0; - потім дорівнює x_of_brick += (step_x + width_of_brick/2); - це зсув блоку по ширині (щоб піраміда мала нормальний вигляд)
         *                         <p>
         *                         <p>
         *                         Початкові координати прямокутника піраміди:
         *                         Х =  x_of_brick+ x*(width_of_brick+step_x),
         *                         У =  ((y_brick_in_piramide-1)*(height_of_brick)+(step_y*(y_brick_in_piramide-1)))+1- y*(height_of_brick+step_y)
         */
        private static void buildLevel2(GCompound level, double getWidth, double getHeight, int health_parameter) {
            double stepX = 0.005 * getWidth;
            double stepY = 0.005 * getHeight;
            int brickInPiramide = 10;
            int yBrickInPiramide = brickInPiramide;
            double widthOfBrick = ((getWidth - (brickInPiramide * stepX)) / brickInPiramide);
            double heightOfBrick = (getHeight / 3) / brickInPiramide;
            double xOfBrick = 0;


            for (int y = 0; y < yBrickInPiramide; y++) {
                for (int x = 0; x < brickInPiramide; x++) {

                    Brick brick2 = new Brick(widthOfBrick, heightOfBrick, health_parameter);

                    if (y % 2 == 0)
                        brick2.setColorOfBrick(Color.BLUE);

                    else
                        brick2.setColorOfBrick(Color.YELLOW);
                    level.add(brick2);

                    brick2.setLocation(xOfBrick + x * (widthOfBrick + stepX), ((yBrickInPiramide - 1) * (heightOfBrick) + (stepY * (yBrickInPiramide - 1))) + 1 - y * (heightOfBrick + stepY));

                }
                brickInPiramide--;
                xOfBrick += (stepX + widthOfBrick) / 2;


            }


    // створення Glabel - назва рівня "Рівень 2"
            GLabel levelName1 = new GLabel("Рівень 2", 0, getHeight);
            levelName1.setFont(new Font("PT Serif", Font.BOLD, (int) heightOfBrick / 2));
            level.add(levelName1);


        }

        /**
         *
         * @param level            - рівень 2.
         * @param getWidth         - довжина екрану getWidth отримана з класу Breakout
         * @param getHeight        - висота екрану getHeight отримана з класу Breakout
         * @param health_parameter - здоров'я цеглинки
         *                         <p>
         *                         <p>
         *                         variables:
         *                         double step_x = 0.005 * getWidth; - відстань між цеглинками по довжині (X)
         *                         double step_y = 0.005 * getHeight; - відстань між цеглинками по висоті (Y)
         *                         int brick_in_piramide = 3; - кількість блоків про довжині
         *                         int y_brick_in_piramide=brick_in_piramide; - кількість блоків по висоті
         *                         double width_of_brick = ((getWidth-(brick_in_piramide*step_x)) / brick_in_piramide); - ширина цеглинки
         *                         double height_of_brick = (getHeight/3) / brick_in_piramide; - висота цеглинки
         *                         double x_of_brick = 0; - потім дорівнює x_of_brick += (step_x + width_of_brick/2); - це зсув блоку по ширині (щоб піраміда мала нормальний вигляд)
         *                         <p>
         *                         <p>
         *                         Початкові координати прямокутника піраміди:
         *                         Х =  x_of_brick+ x*(width_of_brick+step_x),
         *                         У =  1+ y*(height_of_brick+step_y)
         */
        private static void buildLevel3(GCompound level, double getWidth, double getHeight, int health_parameter) {
            double stepX = 0.005 * getWidth;
            double stepY = 0.005 * getHeight;
            int brickInPiramide = 10;
            int yBrickInPiramide = brickInPiramide;
            double widthOfBrick = ((getWidth - (brickInPiramide * stepX)) / brickInPiramide);
            double heightOfBrick = (getHeight / 3) / brickInPiramide;
            double xOfBrick = 0;


            for (int y = 0; y < yBrickInPiramide; y++) {
                for (int x = 0; x < brickInPiramide; x++) {

                    Brick brick3 = new Brick(widthOfBrick, heightOfBrick, health_parameter);

                    if (y % 2 == 0)
                        brick3.setColorOfBrick(Color.BLUE);

                    else
                        brick3.setColorOfBrick(Color.YELLOW);
                    level.add(brick3);

                    brick3.setLocation(xOfBrick + x * (widthOfBrick + stepX), 1 + y * (heightOfBrick + stepY));

                }
                brickInPiramide--;
                xOfBrick += (stepX + widthOfBrick) / 2;


            }


    // створення Glabel - назва рівня "Рівень 3"
            GLabel levelName1 = new GLabel("Рівень 3", 0, getHeight);
            levelName1.setFont(new Font("PT Serif", Font.BOLD, (int) heightOfBrick / 2));
            level.add(levelName1);


        }


        private static void build_level_4(GCompound level, double getWidth, double getHeight, int health_parameter) {
            double stepX = 0;
            double stepY = 0;
            int amountOfBrickY = 15;
            int amountOfBrickX = 15;
            double widthOfBrick = ((getWidth - (amountOfBrickX * stepX)) / amountOfBrickX);
            double heightOfBrick = (getHeight / 2) / amountOfBrickY;
            double xOfBrick = 0;
            double yOfBrick = 0;

            //цикл будування висоти 15 рядків
            for (int y = 0; y < amountOfBrickY; y++) {
                //цикл будування ширини 15 стовпців
                for (int x = 0; x < amountOfBrickX; x++) {
                    //створення обєкту - цеглинки
                    Brick brick4 = new Brick(widthOfBrick, heightOfBrick, health_parameter);


                    //умова кольору
                    switch (y) {
                        //1 рядок - y=0
                        case 0:
                            if (y == 0) {
                                if (x == 0)
                                    //brick4.setColor_of_brick(Color.WHITE);
                                    continue;
                                xOfBrick = (stepX + widthOfBrick);
                                if (x == 1)
                                    //brick4.setColor_of_brick(Color.WHITE);
                                    continue;
                                xOfBrick = x * (stepX + widthOfBrick);
                                if (x == 2)
                                    // brick4.setColor_of_brick(Color.WHITE);
                                    continue;
                                xOfBrick = x * (stepX + widthOfBrick);
                                if (x == 3)
                                    //  brick4.setColor_of_brick(Color.WHITE);
                                    continue;
                                xOfBrick = x * (stepX + widthOfBrick);
                                if (x == 4)
                                    //brick4.setColor_of_brick(Color.WHITE);
                                    continue;
                                xOfBrick = x * (stepX + widthOfBrick);
                                if (x == 5)
                                    //  brick4.setColor_of_brick(Color.WHITE);
                                    continue;
                                xOfBrick = x * (stepX + widthOfBrick);
                                if (x == 6)
                                    //  brick4.setColor_of_brick(Color.WHITE);
                                    continue;
                                xOfBrick = x * (stepX + widthOfBrick);

                                if (x == 7)
                                    brick4.setColorOfBrick(Color.BLACK);
                                if (x == 8)
                                    brick4.setColorOfBrick(Color.BLACK);
                                if (x == 9)
                                    brick4.setColorOfBrick(Color.BLACK);
                                if (x == 10)
                                    brick4.setColorOfBrick(Color.BLACK);
                                if (x == 11)
                                    // brick4.setColor_of_brick(Color.WHITE);
                                    continue;
                                xOfBrick = x * (stepX + widthOfBrick);
                                if (x == 12)
                                    // brick4.setColor_of_brick(Color.WHITE);
                                    continue;
                                xOfBrick = x * (stepX + widthOfBrick);
                                if (x == 13)
                                    //brick4.setColor_of_brick(Color.WHITE);
                                    continue;
                                xOfBrick = x * (stepX + widthOfBrick);
                                if (x == 14)
                                    // brick4.setColor_of_brick(Color.WHITE);
                                    continue;
                                xOfBrick = x * (stepX + widthOfBrick);
                            }
                            //2 рядок - y=1
                        case 1:
                            if (y == 1) {
                                if (x == 0)
                                    //  brick4.setColor_of_brick(Color.WHITE);
                                    continue;
                                xOfBrick = x * (stepX + widthOfBrick);
                                if (x == 1)
                                    //  brick4.setColor_of_brick(Color.WHITE);
                                    continue;
                                xOfBrick = x * (stepX + widthOfBrick);
                                if (x == 2)
                                    //  brick4.setColor_of_brick(Color.WHITE);
                                    continue;
                                xOfBrick = x * (stepX + widthOfBrick);
                                if (x == 3)
                                    //brick4.setColor_of_brick(Color.WHITE);
                                    continue;
                                xOfBrick = x * (stepX + widthOfBrick);
                                if (x == 4)
                                    // brick4.setColor_of_brick(Color.WHITE);
                                    continue;
                                xOfBrick = x * (stepX + widthOfBrick);
                                if (x == 5)
                                    //  brick4.setColor_of_brick(Color.WHITE);
                                    continue;
                                xOfBrick = x * (stepX + widthOfBrick);

                                if (x == 6)
                                    brick4.setColorOfBrick(Color.BLACK);

                                if (x == 7)
                                    brick4.setColorOfBrick(Color.YELLOW);
                                if (x == 8)
                                    brick4.setColorOfBrick(Color.YELLOW);
                                if (x == 9)
                                    brick4.setColorOfBrick(Color.YELLOW);
                                if (x == 10)
                                    brick4.setColorOfBrick(Color.YELLOW);

                                if (x == 11)
                                    brick4.setColorOfBrick(Color.BLACK);

                                if (x == 12)
                                    //brick4.setColor_of_brick(Color.WHITE);
                                    continue;
                                xOfBrick = x * (stepX + widthOfBrick);
                                if (x == 13)
                                    //  brick4.setColor_of_brick(Color.WHITE);
                                    continue;
                                xOfBrick = x * (stepX + widthOfBrick);
                                if (x == 14)
                                    // brick4.setColor_of_brick(Color.WHITE);
                                    continue;
                                xOfBrick = x * (stepX + widthOfBrick);
                            }
                            //3 рядок - y=2
                        case 2:
                            if (y == 2) {
                                if (x == 0)
                                    // brick4.setColor_of_brick(Color.WHITE);
                                    continue;
                                xOfBrick = x * (stepX + widthOfBrick);
                                if (x == 1)
                                    //  brick4.setColor_of_brick(Color.WHITE);
                                    continue;
                                xOfBrick = x * (stepX + widthOfBrick);
                                if (x == 2)
                                    // brick4.setColor_of_brick(Color.WHITE);
                                    continue;
                                xOfBrick = x * (stepX + widthOfBrick);
                                if (x == 3)
                                    //  brick4.setColor_of_brick(Color.WHITE);
                                    continue;
                                xOfBrick = x * (stepX + widthOfBrick);
                                if (x == 4)
                                    //  brick4.setColor_of_brick(Color.WHITE);
                                    continue;
                                xOfBrick = x * (stepX + widthOfBrick);


                                if (x == 5)
                                    brick4.setColorOfBrick(Color.BLACK);


                                if (x == 6)
                                    brick4.setColorOfBrick(Color.YELLOW);

                                if (x == 7)
                                    brick4.setColorOfBrick(Color.YELLOW);
                                if (x == 8)
                                    brick4.setColorOfBrick(Color.YELLOW);
                                if (x == 9)
                                    brick4.setColorOfBrick(Color.YELLOW);
                                if (x == 10)
                                    brick4.setColorOfBrick(Color.YELLOW);

                                if (x == 11)
                                    brick4.setColorOfBrick(Color.YELLOW);

                                if (x == 12)
                                    brick4.setColorOfBrick(Color.BLACK);
                                if (x == 13)
                                    // brick4.setColor_of_brick(Color.WHITE);
                                    continue;
                                xOfBrick = x * (stepX + widthOfBrick);
                                if (x == 14)
                                    // brick4.setColor_of_brick(Color.WHITE);
                                    continue;
                                xOfBrick = x * (stepX + widthOfBrick);
                            }
                            //4 рядок - y=3
                        case 3:
                            if (y == 3) {
                                if (x == 0)
                                    //  brick4.setColor_of_brick(Color.WHITE);
                                    continue;
                                xOfBrick = x * (stepX + widthOfBrick);
                                if (x == 1)
                                    //  brick4.setColor_of_brick(Color.WHITE);
                                    continue;
                                xOfBrick = x * (stepX + widthOfBrick);
                                if (x == 2)
                                    // brick4.setColor_of_brick(Color.WHITE);
                                    continue;
                                xOfBrick = x * (stepX + widthOfBrick);
                                if (x == 3)
                                    // brick4.setColor_of_brick(Color.WHITE);
                                    continue;
                                xOfBrick = x * (stepX + widthOfBrick);
                                if (x == 4)
                                    //  brick4.setColor_of_brick(Color.WHITE);
                                    continue;
                                xOfBrick = x * (stepX + widthOfBrick);


                                if (x == 5)
                                    brick4.setColorOfBrick(Color.BLACK);


                                if (x == 6)
                                    brick4.setColorOfBrick(Color.YELLOW);

                                if (x == 7)
                                    brick4.setColorOfBrick(Color.YELLOW);
                                if (x == 8)
                                    brick4.setColorOfBrick(Color.YELLOW);
                                if (x == 9)
                                    brick4.setColorOfBrick(Color.YELLOW);
                                if (x == 10)
                                    brick4.setColorOfBrick(Color.BLACK);

                                if (x == 11)
                                    brick4.setColorOfBrick(Color.YELLOW);

                                if (x == 12)
                                    brick4.setColorOfBrick(Color.BLACK);
                                if (x == 13)
                                    brick4.setColorOfBrick(Color.BLACK);
                                if (x == 14)
                                    brick4.setColorOfBrick(Color.BLACK);
                            }
                            //5 рядок - y=4
                        case 4:
                            if (y == 4) {
                                if (x == 0)
                                    // brick4.setColor_of_brick(Color.WHITE);
                                    continue;
                                xOfBrick = x * (stepX + widthOfBrick);
                                if (x == 1)
                                    //brick4.setColor_of_brick(Color.WHITE);
                                    continue;
                                xOfBrick = x * (stepX + widthOfBrick);
                                if (x == 2)
                                    //  brick4.setColor_of_brick(Color.WHITE);
                                    continue;
                                xOfBrick = x * (stepX + widthOfBrick);
                                if (x == 3)
                                    // brick4.setColor_of_brick(Color.WHITE);
                                    continue;
                                xOfBrick = x * (stepX + widthOfBrick);
                                if (x == 4)
                                    // brick4.setColor_of_brick(Color.WHITE);
                                    continue;
                                xOfBrick = x * (stepX + widthOfBrick);


                                if (x == 5)
                                    brick4.setColorOfBrick(Color.BLACK);


                                if (x == 6)
                                    brick4.setColorOfBrick(Color.YELLOW);

                                if (x == 7)
                                    brick4.setColorOfBrick(Color.YELLOW);
                                if (x == 8)
                                    brick4.setColorOfBrick(Color.YELLOW);
                                if (x == 9)
                                    brick4.setColorOfBrick(Color.YELLOW);
                                if (x == 10)
                                    brick4.setColorOfBrick(Color.YELLOW);

                                if (x == 11)
                                    brick4.setColorOfBrick(Color.YELLOW);

                                if (x == 12)
                                    brick4.setColorOfBrick(Color.RED);
                                if (x == 13)
                                    brick4.setColorOfBrick(Color.RED);
                                if (x == 14)
                                    brick4.setColorOfBrick(Color.BLACK);
                            }
                            //6 рядок - y=5
                        case 5:
                            if (y == 5) {
                                if (x == 0)
                                    //  brick4.setColor_of_brick(Color.WHITE);
                                    continue;
                                xOfBrick = x * (stepX + widthOfBrick);
                                if (x == 1)
                                    // brick4.setColor_of_brick(Color.WHITE);
                                    continue;
                                xOfBrick = x * (stepX + widthOfBrick);
                                if (x == 2)
                                    //  brick4.setColor_of_brick(Color.WHITE);
                                    continue;
                                xOfBrick = x * (stepX + widthOfBrick);
                                if (x == 3)
                                    //  brick4.setColor_of_brick(Color.WHITE);
                                    continue;
                                xOfBrick = x * (stepX + widthOfBrick);
                                if (x == 4)
                                    // brick4.setColor_of_brick(Color.WHITE);
                                    continue;
                                xOfBrick = x * (stepX + widthOfBrick);


                                if (x == 5)
                                    brick4.setColorOfBrick(Color.BLACK);


                                if (x == 6)
                                    brick4.setColorOfBrick(Color.BLACK);

                                if (x == 7)
                                    brick4.setColorOfBrick(Color.YELLOW);
                                if (x == 8)
                                    brick4.setColorOfBrick(Color.YELLOW);
                                if (x == 9)
                                    brick4.setColorOfBrick(Color.YELLOW);
                                if (x == 10)
                                    brick4.setColorOfBrick(Color.YELLOW);

                                if (x == 11)
                                    brick4.setColorOfBrick(Color.YELLOW);

                                if (x == 12)
                                    brick4.setColorOfBrick(Color.RED);
                                if (x == 13)
                                    brick4.setColorOfBrick(Color.BLACK);
                                if (x == 14)
                                    brick4.setColorOfBrick(Color.BLACK);
                            }
                            //7 рядок - y=6
                        case 6:
                            if (y == 6) {
                                if (x == 0)
                                    brick4.setColorOfBrick(Color.BLACK);
                                if (x == 1)
                                    brick4.setColorOfBrick(Color.BLACK);

                                if (x == 2)
                                    // brick4.setColor_of_brick(Color.WHITE);
                                    continue;
                                xOfBrick = x * (stepX + widthOfBrick);
                                if (x == 3)
                                    //  brick4.setColor_of_brick(Color.WHITE);
                                    continue;
                                xOfBrick = x * (stepX + widthOfBrick);
                                if (x == 4)
                                    //   brick4.setColor_of_brick(Color.WHITE);
                                    continue;
                                xOfBrick = x * (stepX + widthOfBrick);
                                if (x == 5)
                                    //   brick4.setColor_of_brick(Color.WHITE);
                                    continue;
                                xOfBrick = x * (stepX + widthOfBrick);


                                if (x == 6)
                                    brick4.setColorOfBrick(Color.BLACK);
                                if (x == 7)
                                    brick4.setColorOfBrick(Color.BLACK);

                                if (x == 8)
                                    brick4.setColorOfBrick(Color.YELLOW);
                                if (x == 9)
                                    brick4.setColorOfBrick(Color.YELLOW);
                                if (x == 10)
                                    brick4.setColorOfBrick(Color.YELLOW);

                                if (x == 11)
                                    brick4.setColorOfBrick(Color.BLACK);

                                if (x == 12)
                                    brick4.setColorOfBrick(Color.BLACK);
                                if (x == 13)
                                    brick4.setColorOfBrick(Color.BLACK);
                                if (x == 14)
                                    //  brick4.setColor_of_brick(Color.WHITE);
                                    continue;
                                xOfBrick = x * (stepX + widthOfBrick);
                            }
                            //8 рядок - y=7
                        case 7:
                            if (y == 7) {
                                if (x == 0)
                                    brick4.setColorOfBrick(Color.BLACK);
                                if (x == 1)
                                    brick4.setColorOfBrick(Color.YELLOW);
                                if (x == 2)
                                    brick4.setColorOfBrick(Color.BLACK);
                                if (x == 3)
                                    brick4.setColorOfBrick(Color.BLACK);

                                if (x == 4)
                                    //  brick4.setColor_of_brick(Color.WHITE);
                                    continue;
                                xOfBrick = x * (stepX + widthOfBrick);
                                if (x == 5)
                                    //  brick4.setColor_of_brick(Color.WHITE);
                                    continue;
                                xOfBrick = x * (stepX + widthOfBrick);
                                if (x == 6)
                                    //  brick4.setColor_of_brick(Color.WHITE);
                                    continue;
                                xOfBrick = x * (stepX + widthOfBrick);


                                if (x == 7)
                                    brick4.setColorOfBrick(Color.BLACK);

                                if (x == 8)
                                    brick4.setColorOfBrick(Color.YELLOW);
                                if (x == 9)
                                    brick4.setColorOfBrick(Color.YELLOW);
                                if (x == 10)
                                    brick4.setColorOfBrick(Color.YELLOW);

                                if (x == 11)
                                    brick4.setColorOfBrick(Color.BLACK);

                                if (x == 12)
                                    //   brick4.setColor_of_brick(Color.WHITE);
                                    continue;
                                xOfBrick = x * (stepX + widthOfBrick);
                                if (x == 13)
                                    // brick4.setColor_of_brick(Color.WHITE);
                                    continue;
                                xOfBrick = x * (stepX + widthOfBrick);
                                if (x == 14)
                                    //   brick4.setColor_of_brick(Color.WHITE);
                                    continue;
                                xOfBrick = x * (stepX + widthOfBrick);
                            }
    //9 рядок - y=8
                        case 8:
                            if (y == 8) {
                                if (x == 0)
                                    brick4.setColorOfBrick(Color.BLACK);

                                if (x == 1)
                                    brick4.setColorOfBrick(Color.YELLOW);
                                if (x == 2)
                                    brick4.setColorOfBrick(Color.YELLOW);
                                if (x == 3)
                                    brick4.setColorOfBrick(Color.YELLOW);

                                if (x == 4)
                                    brick4.setColorOfBrick(Color.BLACK);
                                if (x == 5)
                                    brick4.setColorOfBrick(Color.BLACK);
                                if (x == 6)
                                    brick4.setColorOfBrick(Color.BLACK);

                                if (x == 7)
                                    brick4.setColorOfBrick(Color.YELLOW);
                                if (x == 8)
                                    brick4.setColorOfBrick(Color.YELLOW);
                                if (x == 9)
                                    brick4.setColorOfBrick(Color.YELLOW);
                                if (x == 10)
                                    brick4.setColorOfBrick(Color.YELLOW);

                                if (x == 11)
                                    brick4.setColorOfBrick(Color.BLACK);

                                if (x == 12)
                                    //  brick4.setColor_of_brick(Color.WHITE);
                                    continue;
                                xOfBrick = x * (stepX + widthOfBrick);
                                if (x == 13)
                                    //  brick4.setColor_of_brick(Color.WHITE);
                                    continue;
                                xOfBrick = x * (stepX + widthOfBrick);
                                if (x == 14)
                                    //   brick4.setColor_of_brick(Color.WHITE);
                                    continue;
                                xOfBrick = x * (stepX + widthOfBrick);
                            }
                            //10 рядок - y=9
                        case 9:
                            if (y == 9) {
                                if (x == 0)
                                    brick4.setColorOfBrick(Color.BLACK);

                                if (x == 1)
                                    brick4.setColorOfBrick(Color.YELLOW);
                                if (x == 2)
                                    brick4.setColorOfBrick(Color.YELLOW);
                                if (x == 3)
                                    brick4.setColorOfBrick(Color.YELLOW);
                                if (x == 4)
                                    brick4.setColorOfBrick(Color.YELLOW);
                                if (x == 5)
                                    brick4.setColorOfBrick(Color.YELLOW);
                                if (x == 6)
                                    brick4.setColorOfBrick(Color.YELLOW);
                                if (x == 7)
                                    brick4.setColorOfBrick(Color.YELLOW);

                                if (x == 8)
                                    brick4.setColorOfBrick(Color.BLACK);

                                if (x == 9)
                                    brick4.setColorOfBrick(Color.YELLOW);
                                if (x == 10)
                                    brick4.setColorOfBrick(Color.YELLOW);

                                if (x == 11)
                                    brick4.setColorOfBrick(Color.YELLOW);

                                if (x == 12)
                                    brick4.setColorOfBrick(Color.BLACK);

                                if (x == 13)
                                    //   brick4.setColor_of_brick(Color.WHITE);
                                    continue;
                                xOfBrick = x * (stepX + widthOfBrick);
                                if (x == 14)
                                    //   brick4.setColor_of_brick(Color.WHITE);
                                    continue;
                                xOfBrick = x * (stepX + widthOfBrick);
                            }
                            //11 рядок - y=10
                        case 10:
                            if (y == 10) {
                                if (x == 0)
                                    brick4.setColorOfBrick(Color.BLACK);

                                if (x == 1)
                                    brick4.setColorOfBrick(Color.YELLOW);
                                if (x == 2)
                                    brick4.setColorOfBrick(Color.YELLOW);

                                if (x == 3)
                                    brick4.setColorOfBrick(Color.BLACK);

                                if (x == 4)
                                    brick4.setColorOfBrick(Color.YELLOW);
                                if (x == 5)
                                    brick4.setColorOfBrick(Color.YELLOW);
                                if (x == 6)
                                    brick4.setColorOfBrick(Color.YELLOW);
                                if (x == 7)
                                    brick4.setColorOfBrick(Color.YELLOW);
                                if (x == 8)
                                    brick4.setColorOfBrick(Color.YELLOW);

                                if (x == 9)
                                    brick4.setColorOfBrick(Color.BLACK);

                                if (x == 10)
                                    brick4.setColorOfBrick(Color.YELLOW);
                                if (x == 11)
                                    brick4.setColorOfBrick(Color.YELLOW);

                                if (x == 12)
                                    brick4.setColorOfBrick(Color.BLACK);
                                if (x == 13)
                                    //  brick4.setColor_of_brick(Color.WHITE);
                                    continue;
                                xOfBrick = x * (stepX + widthOfBrick);
                                if (x == 14)
                                    //  brick4.setColor_of_brick(Color.WHITE);
                                    continue;
                                xOfBrick = x * (stepX + widthOfBrick);
                            }
                            //12 рядок - y=11
                        case 11:
                            if (y == 11) {
                                if (x == 0)
                                    //  brick4.setColor_of_brick(Color.WHITE);
                                    continue;
                                xOfBrick = x * (stepX + widthOfBrick);

                                if (x == 1)
                                    brick4.setColorOfBrick(Color.BLACK);

                                if (x == 2)
                                    brick4.setColorOfBrick(Color.YELLOW);
                                if (x == 3)
                                    brick4.setColorOfBrick(Color.YELLOW);

                                if (x == 4)
                                    brick4.setColorOfBrick(Color.BLACK);

                                if (x == 5)
                                    brick4.setColorOfBrick(Color.YELLOW);
                                if (x == 6)
                                    brick4.setColorOfBrick(Color.YELLOW);
                                if (x == 7)
                                    brick4.setColorOfBrick(Color.YELLOW);

                                if (x == 8)
                                    brick4.setColorOfBrick(Color.BLACK);

                                if (x == 9)
                                    brick4.setColorOfBrick(Color.YELLOW);
                                if (x == 10)
                                    brick4.setColorOfBrick(Color.YELLOW);
                                if (x == 11)
                                    brick4.setColorOfBrick(Color.YELLOW);

                                if (x == 12)
                                    brick4.setColorOfBrick(Color.BLACK);

                                if (x == 13)
                                    //  brick4.setColor_of_brick(Color.WHITE);
                                    continue;
                                xOfBrick = x * (stepX + widthOfBrick);
                                if (x == 14)
                                    // brick4.setColor_of_brick(Color.WHITE);
                                    continue;
                                xOfBrick = x * (stepX + widthOfBrick);
                            }
                            //13 рядок - y=12
                        case 12:
                            if (y == 12) {
                                if (x == 0)
                                    //  brick4.setColor_of_brick(Color.WHITE);
                                    continue;
                                xOfBrick = x * (stepX + widthOfBrick);

                                if (x == 1)
                                    brick4.setColorOfBrick(Color.BLACK);
                                if (x == 2)
                                    brick4.setColorOfBrick(Color.BLACK);

                                if (x == 3)
                                    brick4.setColorOfBrick(Color.YELLOW);
                                if (x == 4)
                                    brick4.setColorOfBrick(Color.YELLOW);


                                if (x == 5)
                                    brick4.setColorOfBrick(Color.BLACK);
                                if (x == 6)
                                    brick4.setColorOfBrick(Color.BLACK);
                                if (x == 7)
                                    brick4.setColorOfBrick(Color.BLACK);

                                if (x == 8)
                                    brick4.setColorOfBrick(Color.YELLOW);
                                if (x == 9)
                                    brick4.setColorOfBrick(Color.YELLOW);
                                if (x == 10)
                                    brick4.setColorOfBrick(Color.YELLOW);

                                if (x == 11)
                                    brick4.setColorOfBrick(Color.BLACK);

                                if (x == 12)
                                    // brick4.setColor_of_brick(Color.WHITE);
                                    continue;
                                xOfBrick = x * (stepX + widthOfBrick);
                                if (x == 13)
                                    //  brick4.setColor_of_brick(Color.WHITE);
                                    continue;
                                xOfBrick = x * (stepX + widthOfBrick);
                                if (x == 14)
                                    //   brick4.setColor_of_brick(Color.WHITE);
                                    continue;
                                xOfBrick = x * (stepX + widthOfBrick);
                            }
                            //14 рядок - y=13
                        case 13:
                            if (y == 13) {
                                if (x == 0)
                                    //  brick4.setColor_of_brick(Color.WHITE);
                                    continue;
                                xOfBrick = x * (stepX + widthOfBrick);
                                if (x == 1)
                                    //   brick4.setColor_of_brick(Color.WHITE);
                                    continue;
                                xOfBrick = x * (stepX + widthOfBrick);

                                if (x == 2)
                                    brick4.setColorOfBrick(Color.BLACK);
                                if (x == 3)
                                    brick4.setColorOfBrick(Color.BLACK);

                                if (x == 4)
                                    brick4.setColorOfBrick(Color.YELLOW);
                                if (x == 5)
                                    brick4.setColorOfBrick(Color.YELLOW);
                                if (x == 6)
                                    brick4.setColorOfBrick(Color.YELLOW);
                                if (x == 7)
                                    brick4.setColorOfBrick(Color.YELLOW);
                                if (x == 8)
                                    brick4.setColorOfBrick(Color.YELLOW);

                                if (x == 9)
                                    brick4.setColorOfBrick(Color.BLACK);
                                if (x == 10)
                                    brick4.setColorOfBrick(Color.BLACK);

                                if (x == 11)
                                    //  brick4.setColor_of_brick(Color.WHITE);
                                    continue;
                                xOfBrick = x * (stepX + widthOfBrick);
                                if (x == 12)
                                    //  brick4.setColor_of_brick(Color.WHITE);
                                    continue;
                                xOfBrick = x * (stepX + widthOfBrick);
                                if (x == 13)
                                    //  brick4.setColor_of_brick(Color.WHITE);
                                    continue;
                                xOfBrick = x * (stepX + widthOfBrick);
                                if (x == 14)
                                    //   brick4.setColor_of_brick(Color.WHITE);
                                    continue;
                                xOfBrick = x * (stepX + widthOfBrick);
                            }
                            //15 рядок - y=14
                        case 14:
                            if (y == 14) {
                                if (x == 0)
                                    //  brick4.setColor_of_brick(Color.WHITE);
                                    continue;
                                xOfBrick = x * (stepX + widthOfBrick);
                                if (x == 1)
                                    //  brick4.setColor_of_brick(Color.WHITE);
                                    continue;
                                xOfBrick = x * (stepX + widthOfBrick);
                                if (x == 2)
                                    //  brick4.setColor_of_brick(Color.WHITE);
                                    continue;
                                xOfBrick = x * (stepX + widthOfBrick);
                                if (x == 3)
                                    //  brick4.setColor_of_brick(Color.WHITE);
                                    continue;
                                xOfBrick = x * (stepX + widthOfBrick);

                                if (x == 4)
                                    brick4.setColorOfBrick(Color.BLACK);
                                if (x == 5)
                                    brick4.setColorOfBrick(Color.BLACK);
                                if (x == 6)
                                    brick4.setColorOfBrick(Color.BLACK);
                                if (x == 7)
                                    brick4.setColorOfBrick(Color.BLACK);
                                if (x == 8)
                                    brick4.setColorOfBrick(Color.BLACK);
                                if (x == 9)
                                    brick4.setColorOfBrick(Color.BLACK);

                                if (x == 10)
                                    //   brick4.setColor_of_brick(Color.WHITE);
                                    continue;
                                xOfBrick = x * (stepX + widthOfBrick);
                                if (x == 11)
                                    //  brick4.setColor_of_brick(Color.WHITE);
                                    continue;
                                xOfBrick = x * (stepX + widthOfBrick);
                                if (x == 12)
                                    //   brick4.setColor_of_brick(Color.WHITE);
                                    continue;
                                xOfBrick = x * (stepX + widthOfBrick);
                                if (x == 13)
                                    //  brick4.setColor_of_brick(Color.WHITE);
                                    continue;
                                xOfBrick = x * (stepX + widthOfBrick);
                                if (x == 14)
                                    //  brick4.setColor_of_brick(Color.WHITE);
                                    continue;
                                xOfBrick = x * (stepX + widthOfBrick);
                            }
                        default:
                            break;
                    }


                    level.add(brick4);
                    brick4.setLocation(xOfBrick, 1 + yOfBrick);

                    xOfBrick += (stepX + widthOfBrick);
                }
                //наступні (змінені) координати розміщення цеглинки: для 2, 3,4... цеглинки
                xOfBrick = 0;
                yOfBrick += (stepY + heightOfBrick);
            }
            // створення Glabel - назва рівня "Рівень 1"
            GLabel levelName1 = new GLabel("Рівень 4", 0, getHeight);
            levelName1.setFont(new Font("PT Serif", Font.BOLD, (int) heightOfBrick / 2));
            level.add(levelName1);


        }



        private static void buildLevel5(GCompound level, double getWidth, double getHeight, int health_parameter) {
            double stepX = 0;
            double stepY = 0;
            int amountOfBrickY = 8;
            int amountOfBrickX = 8;
            double widthOfBrick = ((getWidth - (amountOfBrickX * stepX)) / amountOfBrickX);
            double heightOfBrick = (getHeight / 2) / amountOfBrickY;
            double xOfBrick = 0;
            double yOfBrick = 0;
            //цикл будування висоти 8 рядків
            for (int y = 0; y < amountOfBrickY; y++) {
                //цикл будування ширини 8 стовпців
                for (int x = 0; x < amountOfBrickX; x++) {
                    //створення обєкту - цеглинки
                    Brick brick1 = new Brick(widthOfBrick, heightOfBrick, health_parameter);


                    //умова кольору
                    if ((y+x )%2== 0)
                        brick1.setColorOfBrick(Color.decode("#835c38"));
                    else
                        brick1.setColorOfBrick(Color.decode("#c5a571"));



                    //Не малює деякі клітинки

                    if ((y+x )%2!= 0)
                        continue;
                    xOfBrick = x * (stepX + widthOfBrick);



                    level.add(brick1);
                    brick1.setLocation(xOfBrick, 1 + yOfBrick);

                    xOfBrick += (stepX + widthOfBrick);
                }
                //наступні (змінені) координати розміщення цеглинки: для 2, 3,4... цеглинки
                xOfBrick = 0;
                yOfBrick += (stepY + heightOfBrick);
            }
            // створення Glabel - назва рівня "Рівень 1"
            GLabel levelName1 = new GLabel("Рівень 5", 0, getHeight);
            levelName1.setFont(new Font("PT Serif", Font.BOLD, (int) heightOfBrick / 2));
            level.add(levelName1);




    }}




