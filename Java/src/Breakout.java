import acm.graphics.GCompound;
import acm.graphics.GObject;
import acm.graphics.GRect;
import acm.graphics.GRectangle;
import acm.program.GraphicsProgram;
import acm.util.RandomGenerator;

import java.awt.*;
import java.awt.event.MouseEvent;

public class Breakout extends GraphicsProgram {
    private static final int WIDTH = 600;
    private static final int HEIGHT = 600;
    private static final int BRICK_HEALTH = 1;
    private static final int BALL_RADIUS = 10;
    private static final int DELAY = 20;

    private static final int MAX_VX = 8;
    private static final int MAX_VY = 8;
    private static final int MIN_VX = 5;
    private static final int MIN_VY = 5;

    private static final int MAX_BONUS_SPEED = 8;
    private static final int MIN_BONUS_SPEED = 5;

    private static final int PADDLE_WIDTH = 100;
    private static final int PADDLE_HEIGHT = 10;
    private static final int PADDLE_Y_OFFSET = 20;

    private static final double CHANCE_CREATE_BONUS = 1;

    private boolean isChoisedLevel = false;

    private boolean isChoisedResult = false;

    private static final int PLAYER_STARTHP = 2;

    private int playerHealth = PLAYER_STARTHP;
    private boolean gameOver;

    private BallLinkedList ballLinkedList = new BallLinkedList();
    private BonusLinkedList bonusLinkedList = new BonusLinkedList();
    private RandomGenerator random = RandomGenerator.getInstance();

    private GObject collider;

    private GCompound level;

    private GCompound startMenu;
    private GCompound selectLevelMenu;
    private GCompound rulesMenu;
    private GCompound resultMenu;


    //ракетка
    private GRect racket;

    public void run() {
        setSize(WIDTH, HEIGHT);
        addMouseListeners();
        //addMenu();
        while (true) {
            startProgram();
        }
    }

    /*private void addMenu() {
        startMenu = createStartMenu(getWidth(),getHeight());
        selectMenu = createSelectMenu(getWidth(),getHeight());
        rulesMenu = createRulesMenu(getWidth(),getHeight());
        resultMenu = createResultMenu(getWidth(),getHeight());

    }
    */
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
        level = Create_Level.create_Level(getWidth(), getHeight(), 3, 2);
        add(level);
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
        createRacket();

    }

    //ракетка
    private void createRacket() {

        racket = new GRect((double) (getWidth() - PADDLE_WIDTH) / 2, getHeight() - PADDLE_Y_OFFSET, PADDLE_WIDTH, PADDLE_HEIGHT);
        racket.setColor(Color.BLACK);
        racket.setFilled(true);
        level.add(racket, (double) getWidth() / 2 - ((double) PADDLE_WIDTH / 2), getHeight() - PADDLE_Y_OFFSET);


    }


    private void startGame() {
        while (!gameOver) {
            moveBall();
            moveBonus();
            checkBonusCollision();
            checkOutOfBaunds();
            checkCollision();
            pause(DELAY);
        }

    }

    private void checkBonusCollision() {
        BonusNode temp = bonusLinkedList.head;
        BonusNode preTemp = bonusLinkedList.head;
        while (temp != null) {
            if (checkCollider(temp.bonus, (int) (temp.bonus.getWidth()) / 2) == racket) {
                switch (temp.bonus.getType()) {
                    case 1:
                        addBall();
                        break;
                    case 2:
                        doubleBalls();
                        break;
                    case 3:
                        addHealth();
                        break;
                }
                remove(temp.bonus);

                if (preTemp == temp) bonusLinkedList.head = bonusLinkedList.head.next;
                else preTemp.next = temp.next;
            } else {
                preTemp = temp;
            }
            temp = temp.next;

        }
    }

    private void moveBonus() {
        BonusNode temp = bonusLinkedList.head;
        BonusNode preTemp = bonusLinkedList.head;
        while (temp != null) {
            temp.bonus.move(0, temp.bonus.getVy());
            if (temp.bonus.getY() >= getHeight()) {
                remove(temp.bonus);
                if (preTemp == temp) {
                    bonusLinkedList.head = bonusLinkedList.head.next;
                } else {
                    preTemp.next = temp.next;
                }
            } else {
                preTemp = temp;
            }
            temp = temp.next;
        }
    }

    private void checkCollision() {
        BallNode temp = ballLinkedList.head;
        while (temp != null) {
            collider = checkCollider(temp.ball, BALL_RADIUS);
            if (collider != null) {
                resolveCollision(collider, temp.ball);
            }
            collider = null;
            temp = temp.next;

        }
    }

    private void resolveCollision(GObject collider, Ball ball) {
        if (collider == racket) {
            ball.setVy(-1 * Math.abs(ball.getVy()));
        }
        if (collider.getClass() == Brick.class) {


            GRectangle bound1 = ball.getBounds();
            GRectangle bound2 = collider.getBounds();
            GRectangle overlap = bound1.intersection(bound2);
            if (overlap.getHeight() > overlap.getWidth()) { //якщо ввійшов більше по висоті значить бокове зіткнення
                ball.setVx(-1 * ball.getVx());
                ball.move(ball.getVx() > 0 ? overlap.getWidth() : -1 * overlap.getWidth(), 0);
            } else if (overlap.getHeight() < overlap.getWidth()) { // якщо більше по ширині значить горизонтальне зіткнення
                ball.setVy(-1 * ball.getVy());
                ball.move(0, ball.getVy() > 0 ? overlap.getHeight() : -1 * overlap.getHeight());
            } else { // значить ідеально кутиком вдарився
                ball.setVx(-1 * ball.getVx());
                ball.setVy(-1 * ball.getVy());
                ball.move(ball.getX() > 0 ? overlap.getWidth() : -1 * overlap.getWidth(), ball.getY() > 0 ? overlap.getHeight() : -1 * overlap.getHeight());
            }
            ((Brick) collider).hit();
            if (((Brick) collider).isDead()) {
                level.remove(collider);

                tryCreateBonus(collider);

            }


        }
    }

    private void tryCreateBonus(GObject collider) {

        if (random.nextBoolean(CHANCE_CREATE_BONUS)) {
            Bonus bonus = null;
            switch (random.nextInt(1, 3)) {
                case 1:
                    bonus = new Bonus(collider.getX(), collider.getY(), 1, random.nextInt(MIN_BONUS_SPEED, MAX_BONUS_SPEED));
                    break;
                case 2:
                    bonus = new Bonus(collider.getX(), collider.getY(), 2, random.nextInt(MIN_BONUS_SPEED, MAX_BONUS_SPEED));
                    break;
                case 3:
                    bonus = new Bonus(collider.getX(), collider.getY(), 3, random.nextInt(MIN_BONUS_SPEED, MAX_BONUS_SPEED));
            }
            bonusLinkedList.add(bonus);
            add(bonus);
        }

    }

    private GObject checkCollider(GObject object, int radius) {
        GObject collider = level.getElementAt(object.getX(), object.getY());
        if (collider != null)
            return collider;
        collider = level.getElementAt(object.getX() + 2 * radius, object.getY());
        if (collider != null)
            return collider;
        collider = level.getElementAt(object.getX(), object.getY() + 2 * radius);
        if (collider != null)
            return collider;
        collider = level.getElementAt(object.getX() + 2 * radius, object.getY() + 2 * radius);
        return collider;
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
            if (temp.ball.getY() < 0) {
                temp.ball.setVy(Math.abs(temp.ball.getVy()));
            }
            temp = temp.next;
        }
    }


    public void mouseClicked(MouseEvent e) {
        if (isChoisedResult) {
            // отут логіка кнопок
            // потім не забуть поміняти
            // isChoisedResult = false;
        }
        /*
        if (isChoisedLevel) {
            if (startMenu.isVisible){




            }
            else if (selectLevelMenu){



            }


            else if (rulesMenu.isVisible()){


                if (rulesMenu.getElemenAt(e.getX(),e.getY()).getClass() == Button.class){

                }

            }

        }
        */
        if (!gameOver && ballLinkedList.head.next == null && ballLinkedList.head.ball.getVx() == 0 && ballLinkedList.head.ball.getVy() == 0) {
            ballLinkedList.head.ball.setVx(random.nextInt(MIN_VX, MAX_VX));
            ballLinkedList.head.ball.setVy(-random.nextInt(MIN_VY, MAX_VY));
            if (random.nextBoolean()) ballLinkedList.head.ball.setVx(-1 * ballLinkedList.head.ball.getVx());
        }
    }

    // метод що додає нову кульку
    private void addBall() {
        if (!(ballLinkedList.head.ball.getVx() == 0)) {
            ballLinkedList.add(new Ball(getWidth() / 2 - BALL_RADIUS,
                    getHeight() - PADDLE_Y_OFFSET - BALL_RADIUS - PADDLE_HEIGHT - 20
                    , BALL_RADIUS * 2, BALL_RADIUS * 2, random.nextBoolean() ? random.nextInt(MIN_VX, MAX_VX) : -1 * random.nextInt(MIN_VX, MAX_VX), -1 * random.nextInt(MIN_VY, MAX_VY)));
            add(ballLinkedList.tail.ball);
        }
    }

    // метод що подвоює всі кульки
    private void doubleBalls() {
            BallNode temp = ballLinkedList.get(0);
            BallLinkedList newBalls = new BallLinkedList();

            while (temp != null) {

                if (!(temp.ball.getVx() == 0 && temp.ball.getVy() == 0)) {
                    Ball newBall = new Ball(temp.ball);
                    newBall.setVx(-1 * newBall.getVx());
                    add(newBall);
                    newBalls.add(newBall);
                }

                temp = temp.next;
            }
            if (newBalls.isEmpty()) return;
            ballLinkedList.tail.next = newBalls.head;
            ballLinkedList.tail = newBalls.tail;
    }

    private void addHealth() {
        playerHealth++;
    }


    //рух ракетки разом з мишкою
    public void mouseMoved(MouseEvent e) {
        double x = e.getX() - (double) PADDLE_WIDTH / 2;
        if (x < 0)
            x = 0;
        if (x + PADDLE_WIDTH > getWidth())
            x = getWidth() - PADDLE_WIDTH;

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
