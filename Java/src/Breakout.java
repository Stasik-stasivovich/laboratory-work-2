import acm.graphics.GCompound;
import acm.graphics.GLabel;
import acm.graphics.GObject;
import acm.graphics.GRect;
import acm.program.GraphicsProgram;
import acm.util.RandomGenerator;

import java.awt.*;
import java.awt.event.MouseEvent;

public class Breakout extends GraphicsProgram {
    private static final int WIDTH = 600;
    private static final int HEIGHT = 600;
    public static final int BALL_RADIUS = 9;
    private static final int DELAY = 15;

    public static final int MAX_VX = 6;
    public static final int MAX_VY = 6;
    public static final int MIN_VX = 5;
    public static final int MIN_VY = 5;

    public static final int MAX_BONUS_SPEED = 5;
    public static final int MIN_BONUS_SPEED = 3;

    public static final int PADDLE_WIDTH = 150;
    public static final int PADDLE_HEIGHT = 10;
    public static final int PADDLE_Y_OFFSET = 20;

    public static final double CHANCE_CREATE_BONUS = 0.3;

    private static final int PLAYER_STARTHP = 2;


    public int playerHealth = PLAYER_STARTHP;
    public GLabel playerHpLabel;


    private GCompound ballsContainer = new GCompound();
    private GCompound bonusContainer = new GCompound();


    public RandomGenerator random = RandomGenerator.getInstance();

    private GObject collider;

    public GCompound level;
    private int whatLevel;

    private GCompound startMenu;
    private GCompound selectLevelMenu;
    private GCompound rulesMenu;
    private GCompound resultMenuWin;
    private GCompound resultMenuDefeat;

    private CollisionLogic collisionLogic;
    private BonusMethod bonusMethod;
    private MovementLogic movementLogic;

    private GameStatus currentGameStatus =  GameStatus.MAIN_MENU;


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

        showChoiseMenu();

        // оце ти пишеш

        waitForChoiseLevel();

        setup();

        startGame();
        removeAll();

        showResultMenu(); // це ти пишеш
        waitForChoiseResult();

    }


    // показати меню завершення
    private void showResultMenu() {
        removeAll();
        if (currentGameStatus == GameStatus.GAME_OVER_WIN) {
            add(resultMenuWin);
        } else if (currentGameStatus == GameStatus.GAME_OVER_LOSE) {
            add(resultMenuDefeat);
        }
    }

    // переходимо в режим старту (стартове меню, правила, вибір рівнів)
    private void showChoiseMenu() {
        removeAll();

        startMenu = Create_menu.first_title_menu(getWidth(), getHeight());
        if (currentGameStatus == GameStatus.MAIN_MENU)
            add(startMenu);
        else if (currentGameStatus == GameStatus.CHOOSE_LEVEL)
            add(selectLevelMenu);

        selectLevelMenu = Create_menu.level_menu(getWidth(), getHeight());
        resultMenuWin = Create_menu.result_menu(getWidth(), getHeight(), true);
        resultMenuDefeat = Create_menu.result_menu(getWidth(), getHeight(), false);


        // тут пишеш
    }

    private void levels() {
//рівень 1
        level = Create_Level.create_Level(getWidth(), getHeight(), whatLevel, 2);
        add(level);
        //рівень 2

    }

    private void waitForChoiseResult() {
        while (currentGameStatus == GameStatus.GAME_OVER_WIN || currentGameStatus == GameStatus.GAME_OVER_LOSE)
            pause(100);
    }

    private void waitForChoiseLevel() {
        while (currentGameStatus == GameStatus.MAIN_MENU || currentGameStatus == GameStatus.CHOOSE_LEVEL) pause(100);
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
        playerHealth = PLAYER_STARTHP;
        playerHpLabel = new GLabel("Player Health: " + playerHealth);
        add(playerHpLabel, getWidth() - playerHpLabel.getWidth() - 10, getHeight() - playerHpLabel.getHeight() - PADDLE_Y_OFFSET);

        currentGameStatus = GameStatus.PLAYING;
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
        while (currentGameStatus == GameStatus.PLAYING) {
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

            currentGameStatus = GameStatus.GAME_OVER_WIN;

        }
        if (playerHealth == 0 && ballsContainer.getElementCount() == 0) {

            currentGameStatus = GameStatus.GAME_OVER_LOSE;

        }

    }

    public void mouseClicked(MouseEvent e) {
        if (currentGameStatus == GameStatus.MAIN_MENU) {
            if (startMenu.getElementAt(e.getX(), e.getY()).getClass() == Button.class) {
                Button button = (Button) startMenu.getElementAt(e.getX(), e.getY());
                if (button.text.equals("Почати гру")) {
                    removeAll();
                    add(selectLevelMenu);
                    currentGameStatus = GameStatus.CHOOSE_LEVEL;
                } else if (button.text.equals("Вихід")) {
                    exit();
                }
                // else if (){}
            }
        } else if (currentGameStatus == GameStatus.CHOOSE_LEVEL) {
            if (selectLevelMenu.getElementAt(e.getX(), e.getY()).getClass() == Button.class) {
                Button button = (Button) selectLevelMenu.getElementAt(e.getX(), e.getY());
                if (button.text.equals("Рівень 1")) {
                    removeAll();
                    whatLevel = 1;
                    currentGameStatus = GameStatus.PLAYING;

                } else if (button.text.equals("Рівень 2")) {
                    removeAll();
                    whatLevel = 2;
                    currentGameStatus = GameStatus.PLAYING;
                } else if (button.text.equals("Рівень 3")) {
                    removeAll();
                    whatLevel = 3;
                    currentGameStatus = GameStatus.PLAYING;
                } else if (button.text.equals("Рівень 4")) {
                    removeAll();
                    whatLevel = 4;
                    currentGameStatus = GameStatus.PLAYING;
                } else if (button.text.equals("Вихід")) {
                    removeAll();
                    add(startMenu);
                    currentGameStatus = GameStatus.MAIN_MENU;

                }
            }
        } else if (currentGameStatus == GameStatus.GAME_OVER_WIN) {
            if (resultMenuWin.getElementAt(e.getX(), e.getY()).getClass() == Button.class) {
                Button button = (Button) resultMenuWin.getElementAt(e.getX(), e.getY());
                if (button.text.equals("Грати знову")) {
                    removeAll();
                    add(selectLevelMenu);
                    currentGameStatus = GameStatus.CHOOSE_LEVEL;

                }
                // else if (){}
            }
        } else if (currentGameStatus == GameStatus.GAME_OVER_LOSE) {
            if (resultMenuDefeat.getElementAt(e.getX(), e.getY()).getClass() == Button.class) {
                Button button = (Button) resultMenuDefeat.getElementAt(e.getX(), e.getY());
                if (button.text.equals("Грати знову")) {
                    removeAll();
                    add(selectLevelMenu);
                    currentGameStatus = GameStatus.CHOOSE_LEVEL;

                }
                // else if (){}
            }
        }
        else if (currentGameStatus == GameStatus.PLAYING) {
            if (ballsContainer.getElementCount() == 0) {
                bonusMethod.addBall();
                playerHealth--;
                updatePlayerHp();
            }
        }

    }


    public void updatePlayerHp() {
        playerHpLabel.setLabel("Player Health: " + playerHealth);
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
