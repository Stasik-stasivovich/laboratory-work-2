import acm.graphics.GCompound;
import acm.graphics.GLabel;
import acm.graphics.GRect;

import java.awt.*;


/**
 * Клас що для імітації кнопки
 * варіанти реалізації функціоналу кнопки
 * В слухачів мишки додати перевірку на що натискаємо, якщо це екземпляр Button то (1) або (2)
 * 1) звіряти по об'єктам класу
 * 2) звіряти по полю text
 *
 *
 */
public class Button extends GCompound {
    String text;
    Font font_button ;
    //Сщдщ
    /**
     *
     *
     * @param width - довжина прямокутника
     * @param height - висота прямокутника
     * @param text - текст кнопки
     * @param font_button - шрифт тексту всередині кнопки
     */
    Button(double width, double height, String text,  Font font_button) {
        //font_button = new Font("SansSerif",Font.BOLD,(int) height/5);
        GRect rect = new GRect(0, 0, width, height);
        rect.setFilled(true);
        GLabel label = new GLabel(" " + text, 0, height / 2);
        label.setFont(font_button);
        label.setColor(Color.BLACK);
        this.text = text;
        label.setLocation(width/2-label.getWidth()/2, height/2+label.getAscent()/2);
        this.add(rect);
        this.add(label);
    }

}
