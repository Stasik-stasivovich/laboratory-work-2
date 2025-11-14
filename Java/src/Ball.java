import acm.graphics.GOval;

public class Ball extends GOval {
    private int vx;
    private int vy;
    Ball(int x, int y, int r , int q, int vx, int vy) {
        super(x, y, r, q);
        this.vx = vx;
        this.vy = vy;
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
}
