import acm.graphics.GCompound;
import acm.graphics.GObject;
import acm.graphics.GOval;
import acm.program.GraphicsProgram;
import acm.util.RandomGenerator;

import java.awt.event.MouseEvent;

public class Breakout extends GraphicsProgram {
    private static final int WIDTH = 600;
    private static final int HEIGHT = 600;
    private static final int BRICK_HEALTH = 1;
    private static final int BALL_RADIUS = 10;
    private int vx = 0;
    private int vy = 0;

    private static final int DELAY = 50;

    private static final int MAX_VX = 15;
    private static final int MAX_VY = 10;
    private static final int PADDLE_WIDTH = 60;
    private static final int PADDLE_HEIGHT = 10;
    private static final int PADDLE_Y_OFFSET = 20;

    private boolean isChoisedLevel = false;
    private int level = -1;
    private boolean isChoisedResult = false;

    private static final int PLAYER_STARTHP = 2;

    private int playerHealth = PLAYER_STARTHP;
    private boolean gameOver;

    private GOval ball;
    private RandomGenerator random = RandomGenerator.getInstance();

    private GObject collision;

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
        GCompound level1 = Create_Level.create_Level(getWidth(), getHeight(), 1);
        add(level1);
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

        ball = new GOval(getWidth() / 2 - BALL_RADIUS,
                getHeight() - PADDLE_Y_OFFSET - BALL_RADIUS - PADDLE_HEIGHT - 20
                , BALL_RADIUS * 2, BALL_RADIUS * 2);
        ball.setFilled(true);
        add(ball);
        //рівні гри
        levels();


    }

    private void startGame() {
        while (!gameOver) {
            moveBall();
            if (checkIfOutOfBound()) {
                ball.setLocation(getWidth() / 2 - BALL_RADIUS, getHeight() - PADDLE_Y_OFFSET - BALL_RADIUS - PADDLE_HEIGHT - 20);
                vx = 0;
                vy = 0;
                playerHealth--;
                if (playerHealth <= 0) {
                    gameOver = true;
                }

            }// це я потім зроблю
            /*collision = checkForCollision();
            if(collision != null){
                changeDirectory();
            }*/
            pause(DELAY);
        }
    }

    private boolean checkIfOutOfBound() {
        return ball.getY() + BALL_RADIUS * 2 > getHeight();
    }

    private void moveBall() {
        ball.move(vx, vy);
        if (ball.getX() > getWidth() - 2 * BALL_RADIUS || ball.getX() <= 0) vx = -vx;
        if (ball.getY() < 0) vy = -vy;
    }


    public void mouseClicked(MouseEvent e) {
        if (isChoisedResult) {
            // отут логіка кнопок
            // потім не забуть поміняти
            // isChoisedResult = false;
        }
        if (isChoisedLevel) {
        }

        if (!gameOver && vx == 0) {

            vy = -random.nextInt(5, MAX_VY);
            vx = random.nextInt(5, MAX_VX);
            if (random.nextBoolean(0.5)) vx = -vx;
        }
    }


    public static void main(String[] args) {
        Breakout game = new Breakout();
        game.start(args);
    }
}
