import acm.graphics.GRect;

public class BonusMethod {
    private Breakout game;
    private BallsStructure ballsStructure;
    private GRect racket;






    BonusMethod(Breakout game,  BallsStructure ballsStructure) {
        this.game = game;
        this.ballsStructure = ballsStructure;
        racket = game.getRacket();
    }

    // метод що додає нову кульку
    public void addBall() {
        ballsStructure.add(new Ball((int) racket.getX() + Breakout.PADDLE_WIDTH / 2,
                game.getHeight() - Breakout.PADDLE_Y_OFFSET - Breakout.BALL_RADIUS - Breakout.PADDLE_HEIGHT - 20
                , Breakout.BALL_RADIUS * 2, Breakout.BALL_RADIUS * 2, game.random.nextBoolean() ? game.random.nextInt(Breakout.MIN_VX, Breakout.MAX_VX) :
                -1 * game.random.nextInt(Breakout.MIN_VX, Breakout.MAX_VX), -1 * game.random.nextInt(Breakout.MIN_VY, Breakout.MAX_VY)));
        game.add(ballsStructure.tail.ball);
    }

    // метод що подвоює всі кульки
    public void doubleBalls() {
        BallNode temp = ballsStructure.get(0);
        BallsStructure newBalls = new BallsStructure();

        while (temp != null) {

            if (!(temp.ball.getVx() == 0 && temp.ball.getVy() == 0)) {
                Ball newBall = new Ball(temp.ball);
                newBall.setVx(-1 * newBall.getVx());
                game.add(newBall);
                newBalls.add(newBall);
            }

            temp = temp.next;
        }
        if (newBalls.isEmpty()) return;
        ballsStructure.tail.next = newBalls.head;
        ballsStructure.tail = newBalls.tail;
    }

    public void addHealth() {
        game.playerHealth++;
    }
}
