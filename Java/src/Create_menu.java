import acm.graphics.GCompound;
import acm.graphics.GLabel;
import acm.graphics.GRect;

import java.awt.*;
import java.util.StringTokenizer;

public class Create_menu {
    String rulesText;


    /*
    menu = Create_menu.Create_menu(getWidth(), getHeight(), 4);
        add(menu);
     */

    //перша сторінка
    public static GCompound first_title_menu(double getWidth, double getHeight) {
        GCompound first_menu = new GCompound();
        double width_button_game_Breakout = getWidth * 0.7;
        double height_button_game_Breakout = getHeight * 0.18;
        double height_of_authors_box = getHeight * 0.09;
        double play_game_height = getHeight * 0.3;
        double height_of_rules_box = getHeight * 0.15;
        double height_of_exit_box = height_of_rules_box;


        GRect backgraound = new GRect(0, 0, getWidth, getHeight);
        backgraound.setColor(Color.decode("#1ED987"));
        backgraound.setFilled(true);
        backgraound.sendBackward();
        first_menu.add(backgraound);


        Button game_Breakout = new Button(width_button_game_Breakout, height_button_game_Breakout / 2, "Гра Breakout", new Font("Congenial Black", Font.BOLD, (int) height_button_game_Breakout / 3));
        game_Breakout.setLocation(getWidth / 2 - width_button_game_Breakout / 2, getHeight * 0.05);
        game_Breakout.setColor(Color.decode("#d99d1e"));
        first_menu.add(game_Breakout);

        Button authors_box = new Button(width_button_game_Breakout, height_of_authors_box, "Автори: Станіслав Кошинський, Кравчук Богдан", new Font("SansSerif", Font.BOLD, (int) (height_of_authors_box * 0.35)));
        authors_box.setLocation(getWidth / 2 - width_button_game_Breakout / 2, getHeight * 0.15);
        authors_box.setColor(Color.decode("#d99d1e"));
        first_menu.add(authors_box);

        Button play_game = new Button(width_button_game_Breakout, play_game_height, "Почати гру", new Font("Congenial Black", Font.BOLD, (int) play_game_height / 3));
        play_game.setLocation(getWidth / 2 - width_button_game_Breakout / 2, getHeight * 0.25);
        play_game.setColor(Color.decode("#d99d1e"));
        first_menu.add(play_game);

        Button rules_of_game = new Button(width_button_game_Breakout, height_of_rules_box, "Правила гри", new Font("SansSerif", Font.BOLD, (int) height_of_rules_box / 3));
        rules_of_game.setLocation(getWidth / 2 - width_button_game_Breakout / 2, getHeight * 0.25 + play_game_height + getHeight * 0.01);
        rules_of_game.setColor(Color.decode("#d99d1e"));
        first_menu.add(rules_of_game);

        Button exit = new Button(width_button_game_Breakout, height_of_exit_box, "Вихід", new Font("SansSerif", Font.BOLD, (int) height_of_exit_box / 3));
        exit.setLocation(getWidth / 2 - width_button_game_Breakout / 2, getHeight * 0.25 + play_game_height + getHeight * 0.02 + height_of_rules_box);
        exit.setColor(Color.decode("#d99d1e"));
        first_menu.add(exit);
        return first_menu;
    }

    //меню вибору
    public static GCompound level_menu(double getWidth, double getHeight) {
        GCompound level_menu = new GCompound();
        int amount_of_all_objects = 10;

        double width_button_game_Breakout = getWidth * 0.7;
        double height_button_game_Breakout = getHeight * 0.10;
        double height_choice_level = getHeight * 0.07;
        double height_choice_difficulty = getHeight * 0.085;
        double height_exit = getHeight * 0.1;
        double height_levels = (getHeight - (height_exit + height_choice_level + height_button_game_Breakout + height_choice_difficulty)) / amount_of_all_objects;
        int space_between_objects = amount_of_all_objects + 1;

        double all_heights_of_objects = height_button_game_Breakout + height_choice_level + 6 * height_levels + height_exit + height_choice_difficulty;
        double y_step = (getHeight - all_heights_of_objects) / space_between_objects;


        double y_button_game_Breakout = y_step;
        double y_button_choice_level = y_button_game_Breakout + height_button_game_Breakout + y_step;
        double y_button_choice_difficulty = y_button_choice_level + height_choice_level + y_step;
        double y_level_1 = y_button_choice_difficulty + height_choice_difficulty + y_step;
        double y_level_2 = y_level_1 + height_levels + y_step;
        double y_level_3 = y_level_2 + height_levels + y_step;
        double y_level_4 = y_level_3 + height_levels + y_step;
        double y_level_5 = y_level_4 + height_levels + y_step;
        double y_music = y_level_5 + height_levels + y_step;
        double y_exit = y_music + height_levels + y_step;



        GRect backgraound = new GRect(0, 0, getWidth, getHeight);
        backgraound.setColor(Color.decode("#1ED987"));
        backgraound.setFilled(true);
        backgraound.sendBackward();
        level_menu.add(backgraound);

        Button game_Breakout = new Button(width_button_game_Breakout, height_button_game_Breakout, "Гра Breakout", new Font("Congenial Black", Font.BOLD, (int) (height_button_game_Breakout / 1.7)));
        game_Breakout.setLocation(getWidth / 2 - width_button_game_Breakout / 2, y_button_game_Breakout);
        game_Breakout.setColor(Color.decode("#d99d1e"));
        level_menu.add(game_Breakout);

        //лейбл складність
        Button levelDifficulty = new Button(width_button_game_Breakout, height_choice_level, "Складність", new Font("Congenial Black", Font.BOLD, (int) (height_choice_level / 2)));
        levelDifficulty.setLocation(getWidth / 2 - width_button_game_Breakout / 2, y_button_choice_level);
        levelDifficulty.setColor(Color.decode("#d99d1e"));
        level_menu.add(levelDifficulty);

        //складність
        double x_step_betweenDifficulty = (0.1 * (width_button_game_Breakout / 3));

        //Gcompound easy
        GCompound easy_compound = new GCompound();
        //Gcompound medium
        GCompound medium_compound = new GCompound();
        //Gcompound hard
        GCompound hard_compound = new GCompound();

        //легко складність обгортка
        GRect wrapper_easy = new GRect((width_button_game_Breakout - 2 * x_step_betweenDifficulty) / 3, height_choice_difficulty);
        wrapper_easy.setLocation(getWidth / 2 - width_button_game_Breakout / 2, y_button_choice_difficulty);
        wrapper_easy.setColor(Color.BLACK);
        wrapper_easy.setFilled(true);
        easy_compound.add(wrapper_easy);

        //середня складність обгортка

        GRect wrapper_medium = new GRect((width_button_game_Breakout - 2 * x_step_betweenDifficulty) / 3, height_choice_difficulty);
        wrapper_medium.setLocation((getWidth / 2 - width_button_game_Breakout / 2) + (width_button_game_Breakout - 2 * x_step_betweenDifficulty) / 3 + x_step_betweenDifficulty, y_button_choice_difficulty);
        wrapper_medium.setColor(Color.BLACK);
        wrapper_medium.setFilled(true);
        medium_compound.add(wrapper_medium);

        //важка складність обгортка

        GRect wrapper_hard = new GRect((width_button_game_Breakout - 2 * x_step_betweenDifficulty) / 3, height_choice_difficulty);
        wrapper_hard.setLocation((getWidth / 2 - width_button_game_Breakout / 2) + 2 * ((width_button_game_Breakout - 2 * x_step_betweenDifficulty) / 3) + 2 * x_step_betweenDifficulty, y_button_choice_difficulty);
        wrapper_hard.setColor(Color.BLACK);
        wrapper_hard.setFilled(true);
        hard_compound.add(wrapper_hard);

        // button easy
        Button easy = new Button((width_button_game_Breakout - 2 * x_step_betweenDifficulty) / 3 - 6, height_choice_difficulty - 6, "Легко", new Font("Congenial Black", Font.BOLD, (int) (height_choice_level / 2)));
        easy.setColor(Color.decode("#d99d1e"));
        easy.setLocation(getWidth / 2 - width_button_game_Breakout / 2 + 3, y_button_choice_difficulty + 3);
        easy_compound.add(easy);

        //button medium
        Button medium = new Button((width_button_game_Breakout - 2 * x_step_betweenDifficulty) / 3 - 6, height_choice_difficulty - 6, "Нормально", new Font("Congenial Black", Font.BOLD, (int) (height_choice_level / 2)));
        medium.setColor(Color.decode("#d99d1e"));
        medium.setLocation((getWidth / 2 - width_button_game_Breakout / 2) + (width_button_game_Breakout - 2 * x_step_betweenDifficulty) / 3 + x_step_betweenDifficulty + 3, y_button_choice_difficulty + 3);
        medium_compound.add(medium);

        //button hard
        Button hard = new Button((width_button_game_Breakout - 2 * x_step_betweenDifficulty) / 3 - 6, height_choice_difficulty - 6, "Важко", new Font("Congenial Black", Font.BOLD, (int) (height_choice_level / 2)));
        hard.setColor(Color.decode("#d99d1e"));
        hard.setLocation((getWidth / 2 - width_button_game_Breakout / 2) + 2 * ((width_button_game_Breakout - 2 * x_step_betweenDifficulty) / 3) + 2 * x_step_betweenDifficulty + 3, y_button_choice_difficulty + 3);
        hard_compound.add(hard);

        level_menu.add(easy_compound);
        level_menu.add(medium_compound);
        level_menu.add(hard_compound);


        //1
        Button level_1 = new Button(width_button_game_Breakout, height_levels, "Рівень 1", new Font("Congenial Black", Font.BOLD, (int) (height_levels / 1.7)));
        level_1.setLocation(getWidth / 2 - width_button_game_Breakout / 2, y_level_1);
        level_1.setColor(Color.decode("#d99d1e"));
        level_menu.add(level_1);
//2
        Button level_2 = new Button(width_button_game_Breakout, height_levels, "Рівень 2", new Font("Congenial Black", Font.BOLD, (int) (height_levels / 1.7)));
        level_2.setLocation(getWidth / 2 - width_button_game_Breakout / 2, y_level_2);
        level_2.setColor(Color.decode("#d99d1e"));
        level_menu.add(level_2);
//3
        Button level_3 = new Button(width_button_game_Breakout, height_levels, "Рівень 3", new Font("Congenial Black", Font.BOLD, (int) (height_levels / 1.7)));
        level_3.setLocation(getWidth / 2 - width_button_game_Breakout / 2, y_level_3);
        level_3.setColor(Color.decode("#d99d1e"));
        level_menu.add(level_3);
//4
        Button level_4 = new Button(width_button_game_Breakout, height_levels, "Рівень 4", new Font("Congenial Black", Font.BOLD, (int) (height_levels / 1.7)));
        level_4.setLocation(getWidth / 2 - width_button_game_Breakout / 2, y_level_4);
        level_4.setColor(Color.decode("#d99d1e"));
        level_menu.add(level_4);

        Button level_5 = new Button(width_button_game_Breakout, height_levels, "Рівень 5", new Font("Congenial Black", Font.BOLD, (int) (height_levels / 1.7)));
        level_5.setLocation(getWidth / 2 - width_button_game_Breakout / 2, y_level_5);
        level_5.setColor(Color.decode("#d99d1e"));
        level_menu.add(level_5);

        Button music = new Button(width_button_game_Breakout, height_levels, "Музика", new Font("Congenial Black", Font.BOLD, (int) (height_levels / 1.7)));
        music.setLocation(getWidth / 2 - width_button_game_Breakout / 2, y_music);
        music.setColor(Color.decode("#d99d1e"));
        level_menu.add(music);

//5
        Button exit = new Button(width_button_game_Breakout, height_exit, "Назад", new Font("Congenial Black", Font.BOLD, (int) (height_exit / 1.7)));
        exit.setLocation(getWidth / 2 - width_button_game_Breakout / 2, y_exit);
        exit.setColor(Color.decode("#d99d1e"));
        level_menu.add(exit);
        return level_menu;


    }

    //меню результату виграш
    public static GCompound result_menu(double getWidth, double getHeight, boolean youWin) {
        GCompound resultMenu = new GCompound();
        int width_button_congratulation = (int) (getWidth * 0.4);
        Color color_of_text = Color.decode("#d99d1e");
        int width_you_win = (int) (getWidth * 0.2);
        double width_play_again = getWidth * 0.5;
        double height_play_again = getWidth * 0.2;


        GRect backgraound = new GRect(0, 0, getWidth, getHeight);
        backgraound.setColor(Color.decode("#1ED987"));
        backgraound.setFilled(true);
        backgraound.sendBackward();
        resultMenu.add(backgraound);


        GLabel congratulation = new GLabel("Вітаємо!");
        congratulation.setColor(color_of_text);
        congratulation.setFont(new Font("SansSerif", Font.BOLD, width_button_congratulation / 2));
        congratulation.setLocation(getWidth / 2 - congratulation.getWidth() / 2, (getHeight + congratulation.getAscent()) / 4);
        resultMenu.add(congratulation);


        GLabel you_win;
        if (youWin == true)
            you_win = new GLabel("Ви виграли!");
        else
            you_win = new GLabel("Ви програли!");
        you_win.setColor(color_of_text);
        you_win.setFont(new Font("SansSerif", Font.BOLD, width_you_win / 2));
        you_win.setLocation(getWidth / 2 - you_win.getWidth() / 2, (getHeight + congratulation.getAscent()) / 4 + (getHeight + you_win.getAscent()) / 6);
        resultMenu.add(you_win);

        double y_button_play_again = (getHeight + congratulation.getAscent()) / 4 + (getHeight + you_win.getAscent()) / 6 + (getHeight - (getHeight + congratulation.getAscent()) / 4 + (getHeight + you_win.getAscent()) / 6 - height_play_again) / 4;

        Button play_again = new Button(width_play_again, height_play_again, "Грати знову", new Font("SansSerif", Font.BOLD, (int) (width_play_again / 8)));
        play_again.setColor(color_of_text);
        play_again.setLocation(getWidth / 2 - play_again.getWidth() / 2, y_button_play_again);
        resultMenu.add(play_again);
        return resultMenu;
    }

    public static GCompound rules_menu(double getWidth, double getHeight) {


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

        double width_headline_box = getWidth * 0.85;
        double height_headline_box = getWidth * 0.1;
        double y_step = 0.0185;
        GCompound rulesMenu = new GCompound();
        GRect backgraound = new GRect(0, 0, getWidth, getHeight);
        backgraound.setColor(Color.decode("#1ED987"));
        backgraound.setFilled(true);
        backgraound.sendBackward();
        rulesMenu.add(backgraound);


        //прямокутник canvas для основного тексту правил
        GRect mainPartofRules = new GRect(width_headline_box, getHeight - 4 * y_step * getHeight - 2 * height_headline_box);
        mainPartofRules.setColor(Color.decode("#d99d1e"));
        mainPartofRules.setFilled(true);
        mainPartofRules.sendBackward();
        mainPartofRules.setLocation(getWidth / 2 - width_headline_box / 2, 2 * y_step * getHeight + height_headline_box);
        rulesMenu.add(mainPartofRules);


        Button headline = new Button(width_headline_box, height_headline_box, "Правила гри Breakout", new Font("Congenial Black", Font.BOLD, (int) height_headline_box / 2));

        int yOfStartRule = 100;//хз шо тут має бути зробиш
        int xOffSet = 50;
        // Button headline = new Button(width_headline_box, height_headline_box, "Правила гри Breakout",new Font("Congenial Black", Font.BOLD, (int) height_headline_box / 2 ));

        headline.setColor(Color.decode("#d99d1e"));
        headline.setLocation(getWidth / 2 - width_headline_box / 2, y_step * getHeight);
        rulesMenu.add(headline);


        // Button mainPartofRules = new Button(width_headline_box, getHeight - 4 * y_step * getHeight - 2 * height_headline_box, "Правила ", new Font("Congenial Black", Font.BOLD, (int) height_headline_box / 2));

        createLablesFromText(rulesMenu, rule, yOfStartRule, xOffSet);



/*

        Button exit = new Button(width_headline_box, height_headline_box, "Назад", new Font("Congenial Black", Font.BOLD, (int) height_headline_box / 2));


         */


        Button exit = new Button(width_headline_box, height_headline_box, "Назад", new Font("Congenial Black", Font.BOLD, (int) height_headline_box / 2));

        exit.setColor(Color.decode("#d99d1e"));
        exit.setLocation(getWidth / 2 - width_headline_box / 2, getHeight - height_headline_box - y_step * getHeight);
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
        GCompound music_player = new GCompound();

        GRect backgraound = new GRect(0, 0, Width, Height);
        backgraound.setColor(Color.decode("#1ED987"));
        backgraound.setFilled(true);
        music_player.add(backgraound);


        double stop_width = 0.3 * Width;
        double stop_height = 0.15 * Height;
        double nextAndPreviousMusic_width = stop_width*0.65;
        double nextAndPreviousMusic_height = stop_height*0.65;
        double exit_width = 0.1 * Width;
        double exit_height = 0.1 * Height;
        double x_step = 0.1*stop_width;
        double label_music_width = 0.65*Width;
        double label_music_height = 0.1*Height;

        //main button
        Button stopMusic = new Button(stop_width, stop_height, "Запустити/Стоп", new Font("Congenial Black", Font.BOLD, (int) (stop_height / 4)));
        stopMusic.setColor(Color.decode("#d99d1e"));
        stopMusic.setLocation(Width / 2 - stop_width / 2, Height - 0.2 * Height);
        music_player.add(stopMusic);

        //next
        Button nextMusic = new Button(nextAndPreviousMusic_width, nextAndPreviousMusic_height, "Наступна", new Font("Congenial Black", Font.BOLD, (int) (stop_height / 5)));
        nextMusic.setColor(Color.decode("#d99d1e"));
        nextMusic.setLocation(Width / 2 - stop_width / 2 +stop_width+x_step, (Height - 0.2 * Height) +(nextAndPreviousMusic_height)/4);
        music_player.add(nextMusic);

        //previous
        Button previousMusic = new Button(nextAndPreviousMusic_width, nextAndPreviousMusic_height, "Попередня", new Font("Congenial Black", Font.BOLD, (int) (stop_height / 5)));
        previousMusic.setColor(Color.decode("#d99d1e"));
        previousMusic.setLocation((Width / 2 - stop_width / 2) -x_step-nextAndPreviousMusic_width, (Height - 0.2 * Height) +(nextAndPreviousMusic_height)/4);
        music_player.add(previousMusic);


        //exit
        Button exitMusic = new Button(exit_width, exit_height, "Вихід", new Font("Congenial Black", Font.BOLD, (int) (exit_height / 3.5)));
        exitMusic.setColor(Color.decode("#d99d1e"));
        exitMusic.setLocation(Width-x_step-exit_width, x_step);
        music_player.add(exitMusic);


        //label music
        Button labelMusic = new Button(label_music_width, label_music_height+2*x_step, "Музика", new Font("Congenial Black", Font.BOLD, (int) (label_music_height )));
        labelMusic.setColor(Color.decode("#d99d1e"));
        labelMusic.sendForward();
        labelMusic.setLocation(Width/2-label_music_width/2, x_step/2);
        music_player.add(labelMusic);




        return music_player;
    }


}




