import acm.graphics.GCompound;
import acm.graphics.GRect;

public class BonusMethod {
    private Breakout game;
    private GRect racket;

    private GCompound ballsContainer;


    BonusMethod(Breakout game, GCompound ballsContainer) {

        this.game = game;
        this.ballsContainer = ballsContainer;
        racket = game.getRacket();

    }

    // метод що додає нову кульку
    public void addBall() {

        ballsContainer.add(new Ball((int) racket.getX() + Breakout.PADDLE_WIDTH / 2,
                game.getHeight() - Breakout.PADDLE_Y_OFFSET - Breakout.BALL_RADIUS - Breakout.PADDLE_HEIGHT - 20
                , Breakout.BALL_RADIUS * 2, Breakout.BALL_RADIUS * 2, game.random.nextBoolean() ? game.random.nextInt(Breakout.MIN_VX, Breakout.MAX_VX) :
                -1 * game.random.nextInt(Breakout.MIN_VX, Breakout.MAX_VX), -1 * game.random.nextInt(Breakout.MIN_VY, Breakout.MAX_VY)));
    }

    // метод що подвоює всі кульки
    public void doubleBalls() {

        int len = ballsContainer.getElementCount();
        GCompound tempCompaund = new GCompound();
        int index = 0;
        for (int i = 0; i < len; i++) {
            Ball newBall = new Ball((Ball) ballsContainer.getElement(index++));
            if (newBall.getVx() != 0) {
                newBall.setVx(-1 * newBall.getVx());
                tempCompaund.add(newBall);
            }
        }
        while (tempCompaund.getElementCount() > 0) {
            ballsContainer.add(tempCompaund.getElement(0));
        }
        tempCompaund = null;
    }

    public void addHealth() {
        game.playerHealth++;
        game.updatePlayerHp();
    }
    public void addExpansion(){
        game.expansionCountdown = 1000;
        game.racket.setSize(Breakout.PADDLE_WIDTH + 50, Breakout.PADDLE_HEIGHT);
    }
}
