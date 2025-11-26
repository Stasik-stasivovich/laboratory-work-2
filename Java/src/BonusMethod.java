import acm.graphics.GCompound;
import acm.graphics.GRect;

public class BonusMethod {
    private Breakout game;
    private GRect racket;

    private GCompound ballsContainer;

    /**
     *
     * @param game Object our canvas
     * @param ballsContainer GCompaund with balls
     */
    BonusMethod(Breakout game, GCompound ballsContainer) {

        this.game = game;
        this.ballsContainer = ballsContainer;
        racket = game.getRacket();

    }

    // метод що додає нову кульку

    /**
     * add new ball
     */
    public void addBall() {
        Ball ball = new Ball((int) racket.getX() + Breakout.paddleWidth / 2,
                game.getHeight() - Breakout.paddleYOffset - Breakout.ballRadius - Breakout.paddleHeight - 20
                , Breakout.ballRadius * 2, Breakout.ballRadius * 2, game.random.nextBoolean() ? game.random.nextDouble(Breakout.minVx, Breakout.maxVx) :
                -1 * game.random.nextDouble(Breakout.minVx, Breakout.maxVx), -1 * game.random.nextDouble(Breakout.minVy, Breakout.maxVy));
        if (game.isGodMod) ball.setColor(game.random.nextColor());
        ballsContainer.add(ball);
    }

    // метод що подвоює всі кульки

    /**
     * double all balls
     */
    public void doubleBalls() {

        int len = ballsContainer.getElementCount();
        GCompound tempCompaund = new GCompound();
        int index = 0;
        for (int i = 0; i < len; i++) {
            Ball newBall = new Ball((Ball) ballsContainer.getElement(index++));
            if (newBall.getVx() != 0) {
                newBall.setVx(-1 * newBall.getVx());
                if (game.isGodMod) newBall.setColor(game.random.nextColor());
                tempCompaund.add(newBall);
            }
        }
        while (tempCompaund.getElementCount() > 0) {
            ballsContainer.add(tempCompaund.getElement(0));
        }
    }

    /**
     * add one HP
     */
    public void addHealth() {
        game.playerHealth++;
        game.updatePlayerHp();
    }

    /**
     * change rocket size
     * @param sizeDelta delta size
     */
    public void changeSizeRacket(int sizeDelta){
        if (sizeDelta > 0) game.expansionCountdown = game.expansionTimer;
        else game.expansionCountdown = game.reductionTimer;
        game.racket.setSize(Breakout.paddleWidth + sizeDelta, Breakout.paddleHeight);
        game.racket.move(-sizeDelta/2,0);
    }

}
