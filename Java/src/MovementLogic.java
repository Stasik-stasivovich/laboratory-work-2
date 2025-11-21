import acm.graphics.GCompound;
import acm.program.GraphicsProgram;

public class MovementLogic {
    private GCompound ballsContainer = new GCompound();
    private GCompound bonusContainer = new GCompound();
    private Breakout game;

    MovementLogic(Breakout game, GCompound ballsContainer, GCompound bonusContainer) {
        this.game = game;
        this.ballsContainer = ballsContainer;
        this.bonusContainer = bonusContainer;
    }


    // рухаєм всі бонуси
    public void moveBonus() {
        int len = bonusContainer.getElementCount();
        int index = 0;
        while (index < len) {
            Bonus tempBonus = (Bonus) bonusContainer.getElement(index);
            tempBonus.move(0, tempBonus.getVy());
            if (tempBonus.getY() > game.getHeight()) {
                bonusContainer.remove(tempBonus);
                len--;
            } else index++;
        }
    }

    //рухаєм всі м'ячі
    public void moveBall() {

        int len = ballsContainer.getElementCount();
        int index = 0;
        while (index < len) {
            Ball tempBall = (Ball) ballsContainer.getElement(index);
            tempBall.move(tempBall.getVx(), tempBall.getVy());
            if (tempBall.getY() < 0)tempBall.setVy(Math.abs(tempBall.getVy()));
            if (tempBall.getX() < 0) {
                tempBall.setVx(Math.abs(tempBall.getVx()));
            } else if (tempBall.getX() > game.getWidth() - Breakout.BALL_RADIUS * 2) {
                tempBall.setVx(-Math.abs(tempBall.getVx()));
            }
            index++;
        }
    }
}
