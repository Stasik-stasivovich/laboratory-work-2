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

    private BallLinkedList ballLinkedList = new BallLinkedList();
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

        ballLinkedList.add(new Ball(getWidth() / 2 - BALL_RADIUS,
                getHeight() - PADDLE_Y_OFFSET - BALL_RADIUS - PADDLE_HEIGHT - 20
                , BALL_RADIUS * 2, BALL_RADIUS * 2, 0, 0));

        add(ballLinkedList.head.ball);
        //рівні гри
        levels();


    }

    private void startGame() {
        while (!gameOver) {
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
                if (prev == temp){
                    ballLinkedList.head = ballLinkedList.head.next;
                }
                else{
                    prev.next = temp.next;
                }
            }
            else{
                prev = temp;
            }
            temp = temp.next;
        }

        if (ballLinkedList.isEmpty()){
            ballLinkedList.add(new Ball (getWidth() / 2 - BALL_RADIUS,
                    getHeight() - PADDLE_Y_OFFSET - BALL_RADIUS - PADDLE_HEIGHT - 20
                    , BALL_RADIUS * 2, BALL_RADIUS * 2, 0, 0));
            add(ballLinkedList.head.ball);

            playerHealth--;
        }


    }

    private boolean checkIfOutOfBound(Ball ball) {
        return ball.getY()  > getHeight();
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

        if (!gameOver && ballLinkedList.head.next == null && ballLinkedList.head.ball.getVx()==0) {
            ballLinkedList.head.ball.setVx(random.nextInt(5, MAX_VX));
            ballLinkedList.head.ball.setVy(-random.nextInt(5, MAX_VY));
            if (random.nextBoolean()) ballLinkedList.head.ball.setVx(-1 * ballLinkedList.head.ball.getVx());
        }
    }


    public static void main(String[] args) {
        Breakout game = new Breakout();
        game.start(args);
    }
}
