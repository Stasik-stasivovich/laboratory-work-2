import acm.graphics.GCompound;
import acm.graphics.GLabel;
import acm.graphics.GRect;



/**
 * Клас що для імітації кнопки
 * варіанти реалізації функціоналу кнопки
 * В слухачів мишки додати перевірку на що натискаємо, якщо це екземпляр Button то (1) або (2)
 * 1) звіряти по об'єктам класу
 * 2) звіряти по полю text
 */
public class Button extends GCompound {
    String text;

    Button(double width, double height, String text) {
        GRect rect = new GRect(0, 0, width, height);
        GLabel label = new GLabel(" " + text, 0, height / 2);
        label.setFont("Arial-30");
        this.text = text;
        this.add(rect);
        this.add(label);
    }
}
