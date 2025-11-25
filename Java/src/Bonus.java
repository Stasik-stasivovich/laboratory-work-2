import acm.graphics.GImage;

public class Bonus extends GImage {
    private int type;
    private int vy;

    /**
     *
     * @param type тип 1 - +1 кулька 2- х2 кульки 3 - +1 життя 4 - розширення ракетки 5 -звуження
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
            case 4:
                return  "images/bonus4.png";
            case 5:
                return  "images/bonus5.png";
            default:
                return  null;
        }
    }

    /**
     *
     * @param x x coord
     * @param y y coord
     * @param type type тип 1 - +1 кулька 2- х2 кульки 3 - +1 життя 4 - розширення ракетки 5 -звуження
     * @param vy vertical speed
     */
    Bonus(double x,double y,int type, int vy){
        super(getImageLocationFromType(type),x,y);
        if (type <=2) scale(0.3);
        if (type == 4 || type == 5) scale(0.1);
        else scale(0.1);
        this.vy = vy;
        this.type = type;
    }

    /**
     *
     * @return vertical speed
     */
    public int getVy(){
        return vy;
    }

    /**
     *
     * @return type of bonus
     */
    public int getType() {
        return type;
        }

}
