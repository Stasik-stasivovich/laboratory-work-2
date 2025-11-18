import acm.graphics.GCompound;
import acm.graphics.GObject;
import acm.graphics.GRect;
import acm.program.GraphicsProgram;
import acm.util.RandomGenerator;

import java.awt.*;
import java.awt.event.MouseEvent;

public class Breakout extends GraphicsProgram {
    private static final int WIDTH = 600;
    private static final int HEIGHT = 600;
    private static final int BRICK_HEALTH = 1;
    public static final int BALL_RADIUS = 10;
    private static final int DELAY = 15;

    public static final int MAX_VX = 5;
    public static final int MAX_VY = 5;
    public static final int MIN_VX = 3;
    public static final int MIN_VY = 3;

    public static final int MAX_BONUS_SPEED = 5;
    public static final int MIN_BONUS_SPEED = 3;

    public static final int PADDLE_WIDTH = 100;
    public static final int PADDLE_HEIGHT = 10;
    public static final int PADDLE_Y_OFFSET = 20;

    public static final double CHANCE_CREATE_BONUS = 1;

    private boolean isChoisedLevel = false;

    private boolean isChoisedResult = false;

    private static final int PLAYER_STARTHP = 2;

    public int playerHealth = PLAYER_STARTHP;
    private boolean gameOver;
    private boolean isWin = false;

    private GCompound ballsContainer = new GCompound();
    private GCompound bonusContainer = new GCompound();


    public RandomGenerator random = RandomGenerator.getInstance();

    private GObject collider;

    public GCompound level;

    private GCompound startMenu;
    private GCompound selectLevelMenu;
    private GCompound rulesMenu;
    private GCompound resultMenu;

    private CollisionLogic collisionLogic;
    private BonusMethod bonusMethod;
    private MovementLogic movementLogic;


    //ракетка
    protected GRect racket;

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
        removeAll();
/*
        showResultMenu(); // це ти пишеш
        waitForChoiseResult();
*/
    }

    // показати меню завершення
    private void showResultMenu() {
        removeAll();
        isChoisedResult = true;
        // отут пишеш
    }

    // переходимо в режим старту (стартове меню, правила, вибір рівнів)
    private void showChoiseMenu() {
        removeAll();
        isChoisedLevel = true;

        //levels();
        // тут пишеш
    }

    private void levels() {
//рівень 1
        level = Create_Level.create_Level(getWidth(), getHeight(), 4, 2);
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

        levels();
        createRacket();


        ballsContainer = new GCompound();
        bonusContainer = new GCompound();

        add(ballsContainer);
        add(bonusContainer);


        bonusMethod = new BonusMethod(this, ballsContainer);
        collisionLogic = new CollisionLogic(this, ballsContainer, bonusContainer, bonusMethod);
        movementLogic = new MovementLogic(this, ballsContainer, bonusContainer);

        gameOver = false;
        playerHealth = PLAYER_STARTHP;

       /* ballLinkedList.add(new Ball(getWidth() / 2 - BALL_RADIUS,
                getHeight() - PADDLE_Y_OFFSET - BALL_RADIUS - PADDLE_HEIGHT - 20
                , BALL_RADIUS * 2, BALL_RADIUS * 2, 0, 0));

        add(ballLinkedList.head.ball);
        */
        //рівні гри


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
            movementLogic.moveBall();
            movementLogic.moveBonus();
            collisionLogic.checkBonusCollision();
            collisionLogic.checkBallCollision();
            collisionLogic.checkOutOfBaunds();
            checkForEnd();
            pause(DELAY);
        }

    }

    private void checkForEnd() {

        if (level.getElementCount() == 2) {
            gameOver = true;
            isWin = true;
        }
        if (playerHealth == 0 && ballsContainer.getElementCount() == 0) {
            gameOver = true;
            isWin = false;
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
        if (!gameOver) {
            if (ballsContainer.getElementCount() == 0) {
                bonusMethod.addBall();
                playerHealth--;
            }
        }
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

    public GCompound getLevel() {
        return level;
    }

    public GRect getRacket() {
        return racket;
    }


    public static void main(String[] args) {
        Breakout game = new Breakout();
        game.start(args);
    }
}
