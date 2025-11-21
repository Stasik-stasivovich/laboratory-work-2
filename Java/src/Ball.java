import acm.graphics.GOval;

public class Ball extends GOval {
    private int vx;
    private int vy;
    Ball(int x, int y, int r1 , int r2, int vx, int vy) {
        super(x, y, r1, r2);
        this.vx = vx;
        this.vy = vy;
        this.setFilled(true);
    }
    Ball (Ball orig) {
        super(orig.getX(),orig.getY(),orig.getWidth(),orig.getHeight());
        this.vx = orig.vx;
        this.vy = orig.vy;
        this.setFilled(true);
    }
    public int getVx() {
        return vx;
    }
    public void setVx(int vx) {
        this.vx = vx;
    }
    public int getVy() {
        return vy;
    }
    public void setVy(int vy) {
        this.vy = vy;
    }
    public double getXOfCenter() {
        return getX() + this.getWidth()/2;
    }
    public double getYOfCenter() {
        return getY() + this.getHeight()/2;
    }
}
