import acm.graphics.GOval;

public class Ball extends GOval {
    private int vx;
    private int vy;

    /**
     *
     * @param x x coord
     * @param y y coord
     * @param r1 radius first
     * @param r2 radius second
     * @param vx horizontal speed
     * @param vy vertical speed
     */
    Ball(int x, int y, int r1 , int r2, int vx, int vy) {
        super(x, y, r1, r2);
        this.vx = vx;
        this.vy = vy;
        this.setFilled(true);
    }

    /**
     * copy another ball
     * @param orig another ball
     */
    Ball (Ball orig) {
        super(orig.getX(),orig.getY(),orig.getWidth(),orig.getHeight());
        this.vx = orig.vx;
        this.vy = orig.vy;
        this.setFilled(true);
    }

    /**
     *
     * @return horizontal speed
     */
    public int getVx() {
        return vx;
    }

    /**
     *
     * @param vx horizontal speed
     */
    public void setVx(int vx) {
        this.vx = vx;
    }

    /**
     *
     * @return vertical speed
     */
    public int getVy() {
        return vy;
    }

    /**
     *
     * @param vy vertical speed
     */

    public void setVy(int vy) {
        this.vy = vy;
    }
}
