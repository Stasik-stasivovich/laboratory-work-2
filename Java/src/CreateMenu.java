import acm.graphics.GCompound;
import acm.graphics.GLabel;
import acm.graphics.GRect;

import java.awt.*;
import java.util.StringTokenizer;

public class CreateMenu {
    String rulesText;


    //перша сторінка
    public static GCompound firstTitleMenu(double getWidth, double getHeight) {
        GCompound firstMenu = new GCompound();
        double widthButtonGameBreakout = getWidth * 0.7;
        double heightButtonGameBreakout = getHeight * 0.18;
        double heightOfAuthorsBox = getHeight * 0.09;
        double playGameHeight = getHeight * 0.3;
        double heightOfRulesBox = getHeight * 0.15;
        double heightOfExitBox = heightOfRulesBox;


        GRect backgraound = new GRect(0, 0, getWidth, getHeight);
        backgraound.setColor(Color.decode("#1ED987"));
        backgraound.setFilled(true);
        backgraound.sendBackward();
        firstMenu.add(backgraound);


        Button gameBreakout = new Button(widthButtonGameBreakout, heightButtonGameBreakout / 2, "Гра Breakout", new Font("Congenial Black", Font.BOLD, (int) heightButtonGameBreakout / 3));
        gameBreakout.setLocation(getWidth / 2 - widthButtonGameBreakout / 2, getHeight * 0.05);
        gameBreakout.setColor(Color.decode("#d99d1e"));
        firstMenu.add(gameBreakout);

        Button authorsBox = new Button(widthButtonGameBreakout, heightOfAuthorsBox, "Автори: Станіслав Кошинський, Кравчук Богдан", new Font("SansSerif", Font.BOLD, (int) (heightOfAuthorsBox * 0.35)));
        authorsBox.setLocation(getWidth / 2 - widthButtonGameBreakout / 2, getHeight * 0.15);
        authorsBox.setColor(Color.decode("#d99d1e"));
        firstMenu.add(authorsBox);

        Button playGame = new Button(widthButtonGameBreakout, playGameHeight, "Почати гру", new Font("Congenial Black", Font.BOLD, (int) playGameHeight / 3));
        playGame.setLocation(getWidth / 2 - widthButtonGameBreakout / 2, getHeight * 0.25);
        playGame.setColor(Color.decode("#d99d1e"));
        firstMenu.add(playGame);

        Button rulesOfGame = new Button(widthButtonGameBreakout, heightOfRulesBox, "Правила гри", new Font("SansSerif", Font.BOLD, (int) heightOfRulesBox / 3));
        rulesOfGame.setLocation(getWidth / 2 - widthButtonGameBreakout / 2, getHeight * 0.25 + playGameHeight + getHeight * 0.01);
        rulesOfGame.setColor(Color.decode("#d99d1e"));
        firstMenu.add(rulesOfGame);

        Button exit = new Button(widthButtonGameBreakout, heightOfExitBox, "Вихід", new Font("SansSerif", Font.BOLD, (int) heightOfExitBox / 3));
        exit.setLocation(getWidth / 2 - widthButtonGameBreakout / 2, getHeight * 0.25 + playGameHeight + getHeight * 0.02 + heightOfRulesBox);
        exit.setColor(Color.decode("#d99d1e"));
        firstMenu.add(exit);
        return firstMenu;
    }

    //меню вибору
    public static GCompound levelMenu(double getWidth, double getHeight) {
        GCompound levelMenu = new GCompound();
        int amountOfAllObjects = 10;

        double widthButtonGameBreakout = getWidth * 0.7;
        double heightButtonGameBreakout = getHeight * 0.10;
        double heightChoiceLevel = getHeight * 0.07;
        double heightChoiceDifficulty = getHeight * 0.085;
        double heightExit = getHeight * 0.1;
        double heightLevels = (getHeight - (heightExit + heightChoiceLevel + heightButtonGameBreakout + heightChoiceDifficulty)) / amountOfAllObjects;
        int spaceBetweenObjects = amountOfAllObjects + 1;

        double allHeightsOfObjects = heightButtonGameBreakout + heightChoiceLevel + 6 * heightLevels + heightExit + heightChoiceDifficulty;
        double yStep = (getHeight - allHeightsOfObjects) / spaceBetweenObjects;


        double yButtonGameBreakout = yStep;
        double yButtonChoiceLevel = yButtonGameBreakout + heightButtonGameBreakout + yStep;
        double yButtonChoiceDifficulty = yButtonChoiceLevel + heightChoiceLevel + yStep;
        double yLevel1 = yButtonChoiceDifficulty + heightChoiceDifficulty + yStep;
        double yLevel2 = yLevel1 + heightLevels + yStep;
        double yLevel3 = yLevel2 + heightLevels + yStep;
        double yLevel4 = yLevel3 + heightLevels + yStep;
        double yLevel5 = yLevel4 + heightLevels + yStep;
        double yMusic = yLevel5 + heightLevels + yStep;
        double yExit = yMusic + heightLevels + yStep;



        GRect backgraound = new GRect(0, 0, getWidth, getHeight);
        backgraound.setColor(Color.decode("#1ED987"));
        backgraound.setFilled(true);
        backgraound.sendBackward();
        levelMenu.add(backgraound);

        Button gameBreakout = new Button(widthButtonGameBreakout, heightButtonGameBreakout, "Гра Breakout", new Font("Congenial Black", Font.BOLD, (int) (heightButtonGameBreakout / 1.7)));
        gameBreakout.setLocation(getWidth / 2 - widthButtonGameBreakout / 2, yButtonGameBreakout);
        gameBreakout.setColor(Color.decode("#d99d1e"));
        levelMenu.add(gameBreakout);

        //лейбл складність
        Button levelDifficulty = new Button(widthButtonGameBreakout, heightChoiceLevel, "Складність", new Font("Congenial Black", Font.BOLD, (int) (heightChoiceLevel / 2)));
        levelDifficulty.setLocation(getWidth / 2 - widthButtonGameBreakout / 2, yButtonChoiceLevel);
        levelDifficulty.setColor(Color.decode("#d99d1e"));
        levelMenu.add(levelDifficulty);

        //складність
        double xStepBetweenDifficulty = (0.1 * (widthButtonGameBreakout / 3));

        //Gcompound easy
        GCompound easyCompound = new GCompound();
        //Gcompound medium
        GCompound mediumCompound = new GCompound();
        //Gcompound hard
        GCompound hardCompound = new GCompound();

        //легко складність обгортка
        GRect wrapperEasy = new GRect((widthButtonGameBreakout - 2 * xStepBetweenDifficulty) / 3, heightChoiceDifficulty);
        wrapperEasy.setLocation(getWidth / 2 - widthButtonGameBreakout / 2, yButtonChoiceDifficulty);
        wrapperEasy.setColor(Color.BLACK);
        wrapperEasy.setFilled(true);
        easyCompound.add(wrapperEasy);

        //середня складність обгортка

        GRect wrapperMedium = new GRect((widthButtonGameBreakout - 2 * xStepBetweenDifficulty) / 3, heightChoiceDifficulty);
        wrapperMedium.setLocation((getWidth / 2 - widthButtonGameBreakout / 2) + (widthButtonGameBreakout - 2 * xStepBetweenDifficulty) / 3 + xStepBetweenDifficulty, yButtonChoiceDifficulty);
        wrapperMedium.setColor(Color.BLACK);
        wrapperMedium.setFilled(true);
        mediumCompound.add(wrapperMedium);

        //важка складність обгортка

        GRect wrapperHard = new GRect((widthButtonGameBreakout - 2 * xStepBetweenDifficulty) / 3, heightChoiceDifficulty);
        wrapperHard.setLocation((getWidth / 2 - widthButtonGameBreakout / 2) + 2 * ((widthButtonGameBreakout - 2 * xStepBetweenDifficulty) / 3) + 2 * xStepBetweenDifficulty, yButtonChoiceDifficulty);
        wrapperHard.setColor(Color.BLACK);
        wrapperHard.setFilled(true);
        hardCompound.add(wrapperHard);

        // button easy
        Button easy = new Button((widthButtonGameBreakout - 2 * xStepBetweenDifficulty) / 3 - 6, heightChoiceDifficulty - 6, "Легко", new Font("Congenial Black", Font.BOLD, (int) (heightChoiceLevel / 2)));
        easy.setColor(Color.decode("#d99d1e"));
        easy.setLocation(getWidth / 2 - widthButtonGameBreakout / 2 + 3, yButtonChoiceDifficulty + 3);
        easyCompound.add(easy);

        //button medium
        Button medium = new Button((widthButtonGameBreakout - 2 * xStepBetweenDifficulty) / 3 - 6, heightChoiceDifficulty - 6, "Нормально", new Font("Congenial Black", Font.BOLD, (int) (heightChoiceLevel / 2)));
        medium.setColor(Color.decode("#d99d1e"));
        medium.setLocation((getWidth / 2 - widthButtonGameBreakout / 2) + (widthButtonGameBreakout - 2 * xStepBetweenDifficulty) / 3 + xStepBetweenDifficulty + 3, yButtonChoiceDifficulty + 3);
        mediumCompound.add(medium);

        //button hard
        Button hard = new Button((widthButtonGameBreakout - 2 * xStepBetweenDifficulty) / 3 - 6, heightChoiceDifficulty - 6, "Важко", new Font("Congenial Black", Font.BOLD, (int) (heightChoiceLevel / 2)));
        hard.setColor(Color.decode("#d99d1e"));
        hard.setLocation((getWidth / 2 - widthButtonGameBreakout / 2) + 2 * ((widthButtonGameBreakout - 2 * xStepBetweenDifficulty) / 3) + 2 * xStepBetweenDifficulty + 3, yButtonChoiceDifficulty + 3);
        hardCompound.add(hard);

        levelMenu.add(easyCompound);
        levelMenu.add(mediumCompound);
        levelMenu.add(hardCompound);


        //1
        Button level1 = new Button(widthButtonGameBreakout, heightLevels, "Рівень 1", new Font("Congenial Black", Font.BOLD, (int) (heightLevels / 1.7)));
        level1.setLocation(getWidth / 2 - widthButtonGameBreakout / 2, yLevel1);
        level1.setColor(Color.decode("#d99d1e"));
        levelMenu.add(level1);
//2
        Button level2 = new Button(widthButtonGameBreakout, heightLevels, "Рівень 2", new Font("Congenial Black", Font.BOLD, (int) (heightLevels / 1.7)));
        level2.setLocation(getWidth / 2 - widthButtonGameBreakout / 2, yLevel2);
        level2.setColor(Color.decode("#d99d1e"));
        levelMenu.add(level2);
//3
        Button level3 = new Button(widthButtonGameBreakout, heightLevels, "Рівень 3", new Font("Congenial Black", Font.BOLD, (int) (heightLevels / 1.7)));
        level3.setLocation(getWidth / 2 - widthButtonGameBreakout / 2, yLevel3);
        level3.setColor(Color.decode("#d99d1e"));
        levelMenu.add(level3);
//4
        Button level4 = new Button(widthButtonGameBreakout, heightLevels, "Рівень 4", new Font("Congenial Black", Font.BOLD, (int) (heightLevels / 1.7)));
        level4.setLocation(getWidth / 2 - widthButtonGameBreakout / 2, yLevel4);
        level4.setColor(Color.decode("#d99d1e"));
        levelMenu.add(level4);

        Button level5 = new Button(widthButtonGameBreakout, heightLevels, "Рівень 5", new Font("Congenial Black", Font.BOLD, (int) (heightLevels / 1.7)));
        level5.setLocation(getWidth / 2 - widthButtonGameBreakout / 2, yLevel5);
        level5.setColor(Color.decode("#d99d1e"));
        levelMenu.add(level5);

        Button music = new Button(widthButtonGameBreakout, heightLevels, "Музика", new Font("Congenial Black", Font.BOLD, (int) (heightLevels / 1.7)));
        music.setLocation(getWidth / 2 - widthButtonGameBreakout / 2, yMusic);
        music.setColor(Color.decode("#d99d1e"));
        levelMenu.add(music);

//5
        Button exit = new Button(widthButtonGameBreakout, heightExit, "Назад", new Font("Congenial Black", Font.BOLD, (int) (heightExit / 1.7)));
        exit.setLocation(getWidth / 2 - widthButtonGameBreakout / 2, yExit);
        exit.setColor(Color.decode("#d99d1e"));
        levelMenu.add(exit);
        return levelMenu;


    }

    //меню результату виграш
    public static GCompound resultMenu(double getWidth, double getHeight, boolean youWin) {
        GCompound resultMenu = new GCompound();
        int widthButtonCongratulation = (int) (getWidth * 0.4);
        Color colorOfText = Color.decode("#d99d1e");
        int widthYouWin = (int) (getWidth * 0.2);
        double widthPlayAgain = getWidth * 0.5;
        double heightPlayAgain = getWidth * 0.2;


        GRect backgraound = new GRect(0, 0, getWidth, getHeight);
        backgraound.setColor(Color.decode("#1ED987"));
        backgraound.setFilled(true);
        backgraound.sendBackward();
        resultMenu.add(backgraound);


        GLabel congratulation = new GLabel("Вітаємо!");
        congratulation.setColor(colorOfText);
        congratulation.setFont(new Font("SansSerif", Font.BOLD, widthButtonCongratulation / 2));
        congratulation.setLocation(getWidth / 2 - congratulation.getWidth() / 2, (getHeight + congratulation.getAscent()) / 4);
        resultMenu.add(congratulation);


        GLabel youWinLabel;
        if (youWin)
            youWinLabel = new GLabel("Ви виграли!");
        else
            youWinLabel = new GLabel("Ви програли!");
        youWinLabel.setColor(colorOfText);
        youWinLabel.setFont(new Font("SansSerif", Font.BOLD, widthYouWin / 2));
        youWinLabel.setLocation(getWidth / 2 - youWinLabel.getWidth() / 2, (getHeight + congratulation.getAscent()) / 4 + (getHeight + youWinLabel.getAscent()) / 6);
        resultMenu.add(youWinLabel);

        double yButtonPlayAgain = (getHeight + congratulation.getAscent()) / 4 + (getHeight + youWinLabel.getAscent()) / 6 + (getHeight - (getHeight + congratulation.getAscent()) / 4 + (getHeight + youWinLabel.getAscent()) / 6 - heightPlayAgain) / 4;

        Button playAgain = new Button(widthPlayAgain, heightPlayAgain, "Грати знову", new Font("SansSerif", Font.BOLD, (int) (widthPlayAgain / 8)));
        playAgain.setColor(colorOfText);
        playAgain.setLocation(getWidth / 2 - playAgain.getWidth() / 2, yButtonPlayAgain);
        resultMenu.add(playAgain);
        return resultMenu;
    }

    public static GCompound rulesMenu(double getWidth, double getHeight) {


        String rule =
                "МЕТА ГРИ \n" +
                        "Ваше завдання просте - знищити всі цеглини на рівні.\n" +
                        "Керуйте ракеткою за допомогою миші \n" +
                        "і не дайте м'ячам вилетіти за екран.\n" +
                        "\n" +
                        "ЯК ПОЧАТИ:\n" +
                        "1. Натисніть почати гру, оберіть складність та рівень\n" +
                        "2. Увага, на старті (і при втраті життя)\n" + "м'яч не з'являється автоматично.\n" +
                        "Клікніть мишкою щоб його запустити.\n" +
                        "\n" +
                        "ОСОБЛИВОСТІ:\n" +
                        "- Міцність: Цеглини, можливо, треба вдарити\n" +
                        " декілька разів.\n" +
                        "- Бонуси: Із розбитих цеглин може щось випасти.\n" +
                        "Ви можете зловити цікавинки ракеткою.\n" +
                        " Але будьте обережні...\n" +
                        "Трапляються і шкідливі речі. Досліджуйте самі!\n" +
                        "\n" +
                        "Успіхів і насолоджуйтеся грою!";
        ;

        double widthHeadlineBox = getWidth * 0.85;
        double heightHeadlineBox = getWidth * 0.1;
        double yStep = 0.0185;
        GCompound rulesMenu = new GCompound();
        GRect background = new GRect(0, 0, getWidth, getHeight);
        background.setColor(Color.decode("#1ED987"));
        background.setFilled(true);
        background.sendBackward();
        rulesMenu.add(background);


        //прямокутник canvas для основного тексту правил
        GRect mainPartOfRules = new GRect(widthHeadlineBox, getHeight - 4 * yStep * getHeight - 2 * heightHeadlineBox);
        mainPartOfRules.setColor(Color.decode("#d99d1e"));
        mainPartOfRules.setFilled(true);
        mainPartOfRules.sendBackward();
        mainPartOfRules.setLocation(getWidth / 2 - widthHeadlineBox / 2, 2 * yStep * getHeight + heightHeadlineBox);
        rulesMenu.add(mainPartOfRules);


        Button headline = new Button(widthHeadlineBox, heightHeadlineBox, "Правила гри Breakout", new Font("Congenial Black", Font.BOLD, (int) heightHeadlineBox / 2));

        int yOfStartRule = 100;//хз шо тут має бути зробиш
        int xOffSet = 50;
        // Button headline = new Button(widthHeadlineBox, heightHeadlineBox, "Правила гри Breakout",new Font("Congenial Black", Font.BOLD, (int) heightHeadlineBox / 2 ));

        headline.setColor(Color.decode("#d99d1e"));
        headline.setLocation(getWidth / 2 - widthHeadlineBox / 2, yStep * getHeight);
        rulesMenu.add(headline);


        // Button mainPartOfRules = new Button(widthHeadlineBox, getHeight - 4 * yStep * getHeight - 2 * heightHeadlineBox, "Правила ", new Font("Congenial Black", Font.BOLD, (int) heightHeadlineBox / 2));

        createLablesFromText(rulesMenu, rule, yOfStartRule, xOffSet);



/*

        Button exit = new Button(widthHeadlineBox, heightHeadlineBox, "Назад", new Font("Congenial Black", Font.BOLD, (int) heightHeadlineBox / 2));


         */


        Button exit = new Button(widthHeadlineBox, heightHeadlineBox, "Назад", new Font("Congenial Black", Font.BOLD, (int) heightHeadlineBox / 2));

        exit.setColor(Color.decode("#d99d1e"));
        exit.setLocation(getWidth / 2 - widthHeadlineBox / 2, getHeight - heightHeadlineBox - yStep * getHeight);
        rulesMenu.add(exit);


        return rulesMenu;
    }

    private static void createLablesFromText(GCompound rulesMenu, String rule, int yOfStartRule, int xOffSet) {
        StringTokenizer stringTokenizer = new StringTokenizer(rule, "\n");
        int y = yOfStartRule;

        while (stringTokenizer.hasMoreTokens()) {
            GLabel label = new GLabel(stringTokenizer.nextToken(), xOffSet, y);
            label.setColor(Color.BLACK);
            label.setFont(new Font("Congenial Black", Font.BOLD, 18));
            rulesMenu.add(label);
            label.sendForward();
            y += (int) (label.getHeight() - 2);
        }
    }

    public static GCompound music(double Width, double Height) {
        GCompound musicPlayer = new GCompound();

        GRect backgraound = new GRect(0, 0, Width, Height);
        backgraound.setColor(Color.decode("#1ED987"));
        backgraound.setFilled(true);
        musicPlayer.add(backgraound);


        double stopWidth = 0.3 * Width;
        double stopHeight = 0.15 * Height;
        double nextAndPreviousMusicWidth = stopWidth*0.65;
        double nextAndPreviousMusicHeight = stopHeight*0.65;
        double exitWidth = 0.1 * Width;
        double exitHeight = 0.1 * Height;
        double xStep = 0.1*stopWidth;
        double labelMusicWidth = 0.65*Width;
        double labelMusicHeight = 0.1*Height;

        //main button
        Button stopMusic = new Button(stopWidth, stopHeight, "Запустити/Стоп", new Font("Congenial Black", Font.BOLD, (int) (stopHeight / 4)));
        stopMusic.setColor(Color.decode("#d99d1e"));
        stopMusic.setLocation(Width / 2 - stopWidth / 2, Height - 0.2 * Height);
        musicPlayer.add(stopMusic);

        //next
        Button nextMusic = new Button(nextAndPreviousMusicWidth, nextAndPreviousMusicHeight, "Наступна", new Font("Congenial Black", Font.BOLD, (int) (stopHeight / 5)));
        nextMusic.setColor(Color.decode("#d99d1e"));
        nextMusic.setLocation(Width / 2 - stopWidth / 2 +stopWidth+xStep, (Height - 0.2 * Height) +(nextAndPreviousMusicHeight)/4);
        musicPlayer.add(nextMusic);

        //previous
        Button previousMusic = new Button(nextAndPreviousMusicWidth, nextAndPreviousMusicHeight, "Попередня", new Font("Congenial Black", Font.BOLD, (int) (stopHeight / 5)));
        previousMusic.setColor(Color.decode("#d99d1e"));
        previousMusic.setLocation((Width / 2 - stopWidth / 2) -xStep-nextAndPreviousMusicWidth, (Height - 0.2 * Height) +(nextAndPreviousMusicHeight)/4);
        musicPlayer.add(previousMusic);


        //exit
        Button exitMusic = new Button(exitWidth, exitHeight, "Вихід", new Font("Congenial Black", Font.BOLD, (int) (exitHeight / 3.5)));
        exitMusic.setColor(Color.decode("#d99d1e"));
        exitMusic.setLocation(Width-xStep-exitWidth, xStep);
        musicPlayer.add(exitMusic);


        //label music
        Button labelMusic = new Button(labelMusicWidth, labelMusicHeight+2*xStep, "Музика", new Font("Congenial Black", Font.BOLD, (int) (labelMusicHeight )));
        labelMusic.setColor(Color.decode("#d99d1e"));
        labelMusic.sendForward();
        labelMusic.setLocation(Width/2-labelMusicWidth/2, xStep/2);
        musicPlayer.add(labelMusic);




        return musicPlayer;
    }


}




