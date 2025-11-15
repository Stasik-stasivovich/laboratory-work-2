import acm.graphics.GCompound;
import acm.graphics.GObject;
import acm.graphics.GOval;
import acm.graphics.GRect;
import acm.program.GraphicsProgram;
import acm.util.RandomGenerator;

import java.awt.*;
import java.awt.event.MouseEvent;

public class Breakout extends GraphicsProgram {
    private static final int WIDTH = 600;
    private static final int HEIGHT = 600;
    private static final int BRICK_HEALTH = 1;
    private static final int BALL_RADIUS = 10;
    private static final int DELAY = 25;

    private static final int MAX_VX = 10;
    private static final int MAX_VY = 15;
    private static final int MIN_VX = 3;
    private static final int MIN_VY = 3;
    private static final int PADDLE_WIDTH = 60;
    private static final int PADDLE_HEIGHT = 10;
    private static final int PADDLE_Y_OFFSET = 20;

    private boolean isChoisedLevel = false;
    private int level = -1;
    private boolean isChoisedResult = false;

    private static final int PLAYER_STARTHP = 2;

    private int playerHealth = PLAYER_STARTHP;
    private boolean gameOver;

    private BallLinkedList ballLinkedList = new BallLinkedList();
    private RandomGenerator random = RandomGenerator.getInstance();

    private GObject collision;


    //ракетка
    private GRect racket;

    public void run() {
        setSize(WIDTH, HEIGHT);
        addMouseListeners();
        while (true) {
            startProgram();
        }
    }

    private void startProgram() {
/*
        showChoiseMenu(); // оце ти пишеш
        waitForChoiseLevel();
*/
        setup();

        startGame();
/*
        showResultMenu(); // це ти пишеш
        waitForChoiseResult();
*/
    }

    private void showResultMenu() {
        removeAll();
        isChoisedResult = true;
        // отут пишеш
    }

    private void showChoiseMenu() {
        removeAll();
        isChoisedLevel = true;

        //levels();
        // тут пишеш
    }

    private void levels() {
//рівень 1
        GCompound level1 = Create_Level.create_Level(getWidth(), getHeight(), 1, 1);
        add(level1);
        //рівень 2

    }

    private void waitForChoiseResult() {
        while (isChoisedResult) pause(100);
    }

    private void waitForChoiseLevel() {
        while (isChoisedLevel) pause(100);
    }

    private void setup() {
        removeAll();
        gameOver = false;
        playerHealth = PLAYER_STARTHP;

        ballLinkedList.add(new Ball(getWidth() / 2 - BALL_RADIUS,
                getHeight() - PADDLE_Y_OFFSET - BALL_RADIUS - PADDLE_HEIGHT - 20
                , BALL_RADIUS * 2, BALL_RADIUS * 2, 0, 0));

        add(ballLinkedList.head.ball);
        //рівні гри
        levels();
        racket();

    }

    //ракетка
    private void racket() {

        racket = new GRect((double) getWidth() / 2 - (0.186 * getWidth() / 2), 0.85 * getHeight(), 0.2 * getWidth(), 0.025 * getHeight());
        racket.setColor(Color.BLACK);
        racket.setFilled(true);
        add(racket, (double) getWidth() / 2 - (0.186 * getWidth() / 2), 0.85 * getHeight());


    }


    private void startGame() {
        while (!gameOver) {
            addBall();
            moveBall();
            checkOutOfBaunds();

            // це я потім зроблю
            /*collision = checkForCollision();
            if(collision != null){
                changeDirectory();
            }*/
            pause(DELAY);
        }
    }


    private void checkOutOfBaunds() {
        BallNode temp = ballLinkedList.get(0);
        BallNode prev = ballLinkedList.get(0);
        while (temp != null) {
            if (checkIfOutOfBound(temp.ball)) {
                remove(temp.ball);
                if (prev == temp) {
                    ballLinkedList.head = ballLinkedList.head.next;
                } else {
                    prev.next = temp.next;
                }
            } else {
                prev = temp;
            }
            temp = temp.next;
        }

        if (ballLinkedList.isEmpty()) {
            ballLinkedList.add(new Ball(getWidth() / 2 - BALL_RADIUS,
                    getHeight() - PADDLE_Y_OFFSET - BALL_RADIUS - PADDLE_HEIGHT - 20
                    , BALL_RADIUS * 2, BALL_RADIUS * 2, 0, 0));
            add(ballLinkedList.head.ball);

            playerHealth--;
        }


    }

    private boolean checkIfOutOfBound(Ball ball) {
        return ball.getY() > getHeight();
    }

    private void moveBall() {
        BallNode temp = ballLinkedList.get(0);
        while (temp != null) {
            temp.ball.move(temp.ball.getVx(), temp.ball.getVy());
            if (temp.ball.getX() < 0 || temp.ball.getX() + BALL_RADIUS * 2 >= getWidth())
                temp.ball.setVx(-1 * temp.ball.getVx());
            if (temp.ball.getY() < 0) temp.ball.setVy(-1 * temp.ball.getVy());
            temp = temp.next;
        }
    }


    public void mouseClicked(MouseEvent e) {
        if (isChoisedResult) {
            // отут логіка кнопок
            // потім не забуть поміняти
            // isChoisedResult = false;
        }
        if (isChoisedLevel) {
        }

        if (!gameOver && ballLinkedList.head.next == null && ballLinkedList.head.ball.getVx() == 0) {
            ballLinkedList.head.ball.setVx(random.nextInt(MIN_VX, MAX_VX));
            ballLinkedList.head.ball.setVy(-random.nextInt(MIN_VY, MAX_VY));
            if (random.nextBoolean()) ballLinkedList.head.ball.setVx(-1 * ballLinkedList.head.ball.getVx());
        }
    }

    // метод що додає нову кульку
    private void addBall() {
        ballLinkedList.add(new Ball(getWidth() / 2 - BALL_RADIUS,
                getHeight() - PADDLE_Y_OFFSET - BALL_RADIUS - PADDLE_HEIGHT - 20
                , BALL_RADIUS * 2, BALL_RADIUS * 2, random.nextBoolean() ? random.nextInt(MIN_VX, MAX_VX) : -1 * random.nextInt(MIN_VX, MAX_VX), -1 * random.nextInt(MIN_VY, MAX_VY)));
        add(ballLinkedList.tail.ball);

    }
    // метод що подвоює всі кульки
    private void doubleBalls() {
        BallNode temp = ballLinkedList.get(0);
        BallLinkedList newBalls = new BallLinkedList();

        while (temp != null) {

            Ball newBall = new Ball(temp.ball);
            newBall.setVx(-1 * newBall.getVx());
            add(newBall);
            newBalls.add(newBall);

            temp = temp.next;
        }
        if (newBalls.isEmpty()) return;
        ballLinkedList.tail.next = newBalls.head;
        ballLinkedList.tail = newBalls.tail;

    }


    //рух ракетки разом з мишкою
    public void mouseMoved(MouseEvent e) {
        double x = e.getX() - 0.2 * getWidth() / 2;
        if (x < 0)
            x = 0;
        if (x + 0.2 * getWidth() > getWidth())
            x = getWidth() - 0.2 * getWidth();

        if (racket != null) {
            racket.setLocation(x, racket.getY());
        }
    }

    ;


    public static void main(String[] args) {
        Breakout game = new Breakout();
        game.start(args);
    }
}
