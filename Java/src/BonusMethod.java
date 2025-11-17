import acm.graphics.GRect;

import java.awt.desktop.AppReopenedEvent;
import java.lang.classfile.instruction.BranchInstruction;

public class BonusMethod {
    private Breakout game;
    private BallLinkedList ballLinkedList;
    private GRect racket;






    BonusMethod(Breakout game,  BallLinkedList ballLinkedList) {
        this.game = game;
        this.ballLinkedList = ballLinkedList;
        racket = game.getRacket();
    }

    // метод що додає нову кульку
    public void addBall() {
        ballLinkedList.add(new Ball((int) racket.getX() + Breakout.PADDLE_WIDTH / 2,
                game.getHeight() - Breakout.PADDLE_Y_OFFSET - Breakout.BALL_RADIUS - Breakout.PADDLE_HEIGHT - 20
                , Breakout.BALL_RADIUS * 2, Breakout.BALL_RADIUS * 2, game.random.nextBoolean() ? game.random.nextInt(Breakout.MIN_VX, Breakout.MAX_VX) :
                -1 * game.random.nextInt(Breakout.MIN_VX, Breakout.MAX_VX), -1 * game.random.nextInt(Breakout.MIN_VY, Breakout.MAX_VY)));
        game.add(ballLinkedList.tail.ball);
    }

    // метод що подвоює всі кульки
    public void doubleBalls() {
        BallNode temp = ballLinkedList.get(0);
        BallLinkedList newBalls = new BallLinkedList();

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
        ballLinkedList.tail.next = newBalls.head;
        ballLinkedList.tail = newBalls.tail;
    }

    public void addHealth() {
        game.playerHealth++;
    }
}
