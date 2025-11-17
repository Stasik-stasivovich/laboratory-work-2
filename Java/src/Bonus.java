import acm.graphics.GImage;

public class Bonus extends GImage {
    private String action;

    /**
     *
     * @param type тип 1 - +1 кулька 2- х2 кульки 3 - +1 життя
     * @return шлях до картинки (String)
     */
    private static String getImageLocationFromType (int type){
        switch (type){
            case 1:
                return  "images/bonus1.png";
            case 2:
                return  "images/bonus2.png";
            case 3:
                return  "images/bonus3.png";
            default:
                return  null;
        }
    }
    Bonus(int x,int y,int type){
        super(getImageLocationFromType(type),x,y);
        if (type <=2) scale(0.03);
        else scale(0.1);
    }
}
