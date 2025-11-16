import acm.graphics.GCompound;
import acm.graphics.GLabel;
import acm.graphics.GRect;

import java.awt.*;

public class Create_menu  {
    private static boolean first_title_menu =false;
    private static boolean level_menu =false;


    public static GCompound Create_menu(double getWidth, double getHeight, int what_menu) {
        GCompound menu = new GCompound();
        if (what_menu == 1)
            first_title_menu(menu, getWidth, getHeight, first_title_menu);



        return menu;
    }

    private static void first_title_menu(GCompound menu, double getWidth, double getHeight, boolean first_title_menu) {
   double width_button_game_Breakout = getWidth*0.5;
   double y_button_game_Breakout = 0.084*getHeight;
   double height_button_game_Breakout= getHeight*0.18;
   double height_of_authors_box =  getHeight*0.09;
   double play_game_height =  getHeight*0.255;
   double height_of_rules_box = getHeight*0.15;
   double height_of_exit_box = height_of_rules_box;




        first_title_menu=true;
        GRect name_of_game_box = new GRect(getWidth/2-width_button_game_Breakout/2, y_button_game_Breakout, width_button_game_Breakout, height_button_game_Breakout);
        name_of_game_box.setFillColor(Color.ORANGE);
        menu.add(name_of_game_box);
        GLabel name_of_game = new GLabel("Breakout");
        name_of_game.setFont(new Font("WaflSoft", Font.BOLD,( int) height_button_game_Breakout/2));
        name_of_game.setLocation(getWidth/2-width_button_game_Breakout/2 - name_of_game_box.getWidth()/2,y_button_game_Breakout/2-name_of_game_box.getHeight()/2 );
        menu.add(name_of_game);


    }
}
