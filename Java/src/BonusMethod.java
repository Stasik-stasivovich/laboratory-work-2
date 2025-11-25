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

        ballsContainer.add(new Ball((int) racket.getX() + Breakout.paddleWidth / 2,
                game.getHeight() - Breakout.paddleYOffset - Breakout.BALL_RADIUS - Breakout.paddleHeight - 20
                , Breakout.BALL_RADIUS * 2, Breakout.BALL_RADIUS * 2, game.random.nextBoolean() ? game.random.nextInt(Breakout.minVx, Breakout.maxVx) :
                -1 * game.random.nextInt(Breakout.minVx, Breakout.maxVx), -1 * game.random.nextInt(Breakout.minVy, Breakout.maxVy)));
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
    }

    public void addHealth() {
        game.playerHealth++;
        game.updatePlayerHp();
    }
    public void changeSizeRacket(int sizeDelta){
        game.expansionCountdown = 1000;
        game.racket.setSize(Breakout.paddleWidth + sizeDelta, Breakout.paddleHeight);
    }

}
