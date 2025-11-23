import acm.graphics.GCompound;
import acm.graphics.GLabel;
import acm.graphics.GRect;

import java.awt.*;

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
        double width_button_game_Breakout = getWidth * 0.7;
        double height_button_game_Breakout = getHeight * 0.11;
        double height_choice_level = getHeight * 0.12;
        double height_levels = getHeight * 0.1;
        double height_exit = getHeight * 0.12;

        int amount_of_all_objects = 8;
        int space_between_objects = amount_of_all_objects + 1;
        double all_heights_of_objects = height_button_game_Breakout + height_choice_level + 5 * height_levels + height_exit;

        double y_step = (getHeight - all_heights_of_objects) / space_between_objects;

        double y_button_game_Breakout = y_step;
        double y_button_choice_level = y_button_game_Breakout + height_button_game_Breakout + y_step;
        double y_level_1 = y_button_choice_level + height_choice_level + y_step;
        double y_level_2 = y_level_1 + height_levels + y_step;
        double y_level_3 = y_level_2 + height_levels + y_step;
        double y_level_4 = y_level_3 + height_levels + y_step;
        double y_level_5 = y_level_4 + height_levels + y_step;
        double y_exit = y_level_5 + height_levels + y_step;


        GRect backgraound = new GRect(0, 0, getWidth, getHeight);
        backgraound.setColor(Color.decode("#1ED987"));
        backgraound.setFilled(true);
        backgraound.sendBackward();
        level_menu.add(backgraound);

        Button game_Breakout = new Button(width_button_game_Breakout, height_button_game_Breakout, "Гра Breakout", new Font("Congenial Black", Font.BOLD, (int) (height_button_game_Breakout / 1.7)));
        game_Breakout.setLocation(getWidth / 2 - width_button_game_Breakout / 2, y_button_game_Breakout);
        game_Breakout.setColor(Color.decode("#d99d1e"));
        level_menu.add(game_Breakout);

        Button choice_level = new Button(width_button_game_Breakout, height_choice_level, "Вибір рівнів", new Font("Congenial Black", Font.BOLD, (int) (height_choice_level / 2)));
        choice_level.setLocation(getWidth / 2 - width_button_game_Breakout / 2, y_button_choice_level);
        choice_level.setColor(Color.decode("#d99d1e"));
        level_menu.add(choice_level);
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

        double width_headline_box = getWidth * 0.85;
        double height_headline_box = getWidth * 0.1;
        double y_step =0.0185;
        GCompound rulesMenu = new GCompound();
        GRect backgraound = new GRect(0, 0, getWidth, getHeight);
        backgraound.setColor(Color.decode("#1ED987"));
        backgraound.setFilled(true);
        backgraound.sendBackward();
        rulesMenu.add(backgraound);



        Button headline = new Button(width_headline_box, height_headline_box, "Правила гри Breakout",new Font("Congenial Black", Font.BOLD, (int) height_headline_box / 2 ));
        headline.setColor(Color.decode("#d99d1e"));
        headline.setLocation(getWidth / 2 - width_headline_box / 2, y_step * getHeight);
        rulesMenu.add(headline);

        Button mainPartofRules = new Button(width_headline_box, getHeight-4*y_step*getHeight-2*height_headline_box, "Правила ",new Font("Congenial Black", Font.BOLD, (int) height_headline_box / 2 ));
        mainPartofRules.setColor(Color.decode("#d99d1e"));
        mainPartofRules.setLocation(getWidth / 2 - width_headline_box / 2, 2*y_step * getHeight+height_headline_box);
        rulesMenu.add(mainPartofRules);




        Button exit = new Button(width_headline_box, height_headline_box, "Назад",new Font("Congenial Black", Font.BOLD, (int) height_headline_box / 2 ));
        exit.setColor(Color.decode("#d99d1e"));
        exit.setLocation(getWidth / 2 - width_headline_box / 2, getHeight-height_headline_box-y_step * getHeight);
        rulesMenu.add(exit);






        return rulesMenu;
    }


}




