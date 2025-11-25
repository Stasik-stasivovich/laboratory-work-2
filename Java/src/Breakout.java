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
    public static int ballRadius = 9;
    private static final int DELAY = 8;  // затримка між кадрами у мілісекундах

    public static int maxVx = 5;
    public static int maxVy = 5;
    public static int minVx = 3;
    public static int minVy = 3;

    public static int maxBonusSpeed = 5;
    public static int minBonusSpeed = 3;

    public static int paddleWidth = 70;
    public static int paddleHeight = 10;
    public static int paddleYOffset = 20; //відступ ракетки від низу екрана

    public int expansionCountdown = 0;
    public int paddleExpansion = 75; // збільшення ракетки при відповідному бонусі
    public int paddleIncrease = 40; // зменшення ракетки при відповідному бонусі


    public static double chanceCreateBonus = 0.3; // шанс спавна бонуса при руйнуванні цеглини

    private static int playerStarthp = 2;
    private static int brickHealth = 2;


    public int playerHealth = playerStarthp;
    public GLabel playerHpLabel;


    private GCompound ballsContainer = new GCompound();
    private GCompound bonusContainer = new GCompound();


    public RandomGenerator random = RandomGenerator.getInstance();

    private GObject collider;

    public GCompound level;
    private int whatLevel;

    //
    private GCompound startMenu;
    private GCompound selectLevelMenu;
    private GCompound rulesMenu;
    private GCompound resultMenuWin;
    private GCompound resultMenuDefeat;
    private GCompound musicPlayerMenu;

    private CollisionLogic collisionLogic;
    private BonusMethod bonusMethod;
    private MovementLogic movementLogic;

    private GameStatus currentGameStatus = GameStatus.MAIN_MENU;
    private final Font fontHealth = new Font("Sans Serif", Font.BOLD, 16);


    //ракетка
    protected GRect racket;

    private boolean difficultSelect = false;
    private Button lastDifficultButton;

    private final Player musicPlayer = new Player() ; // об'єкт музичного плеєра

    // метод що починає програму
    public void run() {
        setSize(WIDTH, HEIGHT);
        addMouseListeners();
        //addMenu();
        while (true) {
            startProgram();
        }
    }

    // метод що виконує життєвий цикл гри
    private void startProgram() {

        showChoiseMenu();

        waitForChoiseLevel();

        setup();

        startGame();
        removeAll();

        showResultMenu();
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

        selectLevelMenu = Create_menu.level_menu(getWidth(), getHeight());
        resultMenuWin = Create_menu.result_menu(getWidth(), getHeight(), true);
        resultMenuDefeat = Create_menu.result_menu(getWidth(), getHeight(), false);
        rulesMenu = Create_menu.rules_menu(getWidth(), getHeight());

        musicPlayerMenu = Create_menu.music(getWidth(), getHeight());

        if (currentGameStatus == GameStatus.MAIN_MENU) add(startMenu);

        else if (currentGameStatus == GameStatus.RULES_MENU) add(rulesMenu);

        else if (currentGameStatus == GameStatus.CHOOSE_LEVEL) add(selectLevelMenu);

    }

    //метод що додає на екран GCompaund рівня
    private void levels() {

        level = Create_Level.create_Level(getWidth(), getHeight(), whatLevel, brickHealth);
        add(level);

    }
    // метод що чекає поки ми в кінцевому меню
    private void waitForChoiseResult() {
        while (currentGameStatus == GameStatus.GAME_OVER_WIN || currentGameStatus == GameStatus.GAME_OVER_LOSE) {

            pause(100);
        }
    }
    // метод що чекає поки ми в початкових меню
    private void waitForChoiseLevel() {
        while (currentGameStatus == GameStatus.MAIN_MENU || currentGameStatus == GameStatus.RULES_MENU || currentGameStatus == GameStatus.CHOOSE_LEVEL||currentGameStatus==GameStatus.MUSICPLAYER_MENU)
            pause(100);
    }
    // встановлює початкові значення деяких параметрів готує об'єкти до початку гри
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
        playerHealth = playerStarthp;
        playerHpLabel = new GLabel("Player Health: " + playerHealth);
        playerHpLabel.setFont(fontHealth);
        add(playerHpLabel, getWidth() - playerHpLabel.getWidth() - 10, getHeight() - playerHpLabel.getHeight() - paddleYOffset);

        currentGameStatus = GameStatus.PLAYING;
        //рівні гри

        difficultSelect = false;
    }

    //створює ракетку
    private void createRacket() {

        racket = new GRect((double) (getWidth() - paddleWidth) / 2, getHeight() - paddleYOffset, paddleWidth, paddleHeight);
        racket.setColor(Color.BLACK);
        racket.setFilled(true);
        level.add(racket, (double) getWidth() / 2 - ((double) paddleWidth / 2), getHeight() - paddleYOffset);


    }

    // починає гру
    private void startGame() {
        while (currentGameStatus == GameStatus.PLAYING) {
            movementLogic.moveBall();
            movementLogic.moveBonus();
            collisionLogic.checkBonusCollision();
            collisionLogic.checkExpansionCountdown();
            collisionLogic.checkBallCollision();
            collisionLogic.checkOutOfBounds();
            checkForEnd();
            pause(DELAY);
        }

    }
    // перевіряє умову закінчення гри
    private void checkForEnd() {

        if (level.getElementCount() == 2) { // на рівні є 3 типи об'єктів одна цеглина, 1 лейбл з номером рівня і цегли, коли
            // цегли ламаються то залишається 2 об'єкта

            currentGameStatus = GameStatus.GAME_OVER_WIN;

        }
        if (playerHealth == 0 && ballsContainer.getElementCount() == 0) {

            currentGameStatus = GameStatus.GAME_OVER_LOSE;

        }

    }

    // метод що реагує на кліки
    public void mouseClicked(MouseEvent e) {
        // якщо в стартовому меню
        if (currentGameStatus == GameStatus.MAIN_MENU) {
            if (startMenu.getElementAt(e.getX(), e.getY()).getClass() == Button.class) {
                Button button = (Button) startMenu.getElementAt(e.getX(), e.getY());
                if (button.text.equals("Почати гру")) {
                    removeAll();
                    add(selectLevelMenu);
                    currentGameStatus = GameStatus.CHOOSE_LEVEL;
                } else if (button.text.equals("Вихід")) {
                    exit();
                } else if (button.text.equals("Правила гри")) {
                    removeAll();
                    add(rulesMenu);
                    currentGameStatus = GameStatus.RULES_MENU;
                }
            }
            // якщо в меню правил
        } else if (currentGameStatus == GameStatus.RULES_MENU) {
            if (rulesMenu.getElementAt(e.getX(), e.getY()).getClass() == Button.class) {
                Button button = (Button) rulesMenu.getElementAt(e.getX(), e.getY());
                if (button.text.equals("Назад")) {
                    removeAll();
                    add(startMenu);
                    currentGameStatus = GameStatus.MAIN_MENU;

                }
            }
        }


        //якщо в меню вибора рівнів
        else if (currentGameStatus == GameStatus.CHOOSE_LEVEL) {
            if (selectLevelMenu.getElementAt(e.getX(), e.getY()).getClass() == GCompound.class) {
                GCompound GcompoundDifficulty = (GCompound) selectLevelMenu.getElementAt(e.getX(), e.getY());

                Button difficulty = (Button) GcompoundDifficulty.getElementAt(e.getX(), e.getY());
                if (difficulty.text.equals("Легко")) {
                    if (lastDifficultButton != null) {
                        lastDifficultButton.setColor(Color.decode("#d99d1e"));
                    }
                    difficulty.setColor(Color.decode("#00bf63"));
                    lastDifficultButton = difficulty;
                    setEasy();
                    difficultSelect = true;


                } else if (difficulty.text.equals("Нормально")) {
                    if (lastDifficultButton != null) {
                        lastDifficultButton.setColor(Color.decode("#d99d1e"));
                    }
                    difficulty.setColor(Color.decode("#ffde59"));
                    lastDifficultButton = difficulty;
                    setMedium();
                    difficultSelect = true;

                } else if (difficulty.text.equals("Важко")) {
                    if (lastDifficultButton != null) {
                        lastDifficultButton.setColor(Color.decode("#d99d1e"));
                    }
                    lastDifficultButton = difficulty;
                    setHard();
                    difficultSelect = true;
                    difficulty.setColor(Color.decode("#ff5555"));

                }
            }
            if (selectLevelMenu.getElementAt(e.getX(), e.getY()).getClass() == Button.class) {
                if (lastDifficultButton != null) {
                    lastDifficultButton.setColor(Color.decode("#d99d1e"));
                    lastDifficultButton = null;
                }
                Button button = (Button) selectLevelMenu.getElementAt(e.getX(), e.getY());
                if (button.text.equals("Назад")) {
                    removeAll();
                    add(startMenu);
                    currentGameStatus = GameStatus.MAIN_MENU;
                    difficultSelect = false;
                }else if (button.text.equals("Музика")) {
                    removeAll();
                    add(musicPlayerMenu);
                    currentGameStatus = GameStatus.MUSICPLAYER_MENU;
                }
                    if (difficultSelect) {


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
                        } else if (button.text.equals("Рівень 5")) {
                            removeAll();
                            whatLevel = 5;
                            currentGameStatus = GameStatus.PLAYING;
                        }
                    }
                }
        }
        // якщо в меню плеєра
        else if (currentGameStatus == GameStatus.MUSICPLAYER_MENU) {
            if (musicPlayerMenu.getElementAt(e.getX(), e.getY()).getClass() == Button.class) {
                Button button = (Button) musicPlayerMenu.getElementAt(e.getX(), e.getY());
                if (button.text.equals("Вихід")) {
                    removeAll();
                    add(selectLevelMenu);
                    currentGameStatus = GameStatus.CHOOSE_LEVEL;
                    difficultSelect = false;

                } else if (button.text.equals("Запустити/Стоп")){
                    musicPlayer.toggleMusic();
                }else if (button.text.equals("Наступна")){
                    musicPlayer.nextTrack();
                }else if (button.text.equals("Попередня")){
                    musicPlayer.prevTrack();
                }
            }
        }

        // якщо кінцеве меню
        else if (currentGameStatus == GameStatus.GAME_OVER_WIN) {
            if (resultMenuWin.getElementAt(e.getX(), e.getY()).getClass() == Button.class) {
                Button button = (Button) resultMenuWin.getElementAt(e.getX(), e.getY());
                if (button.text.equals("Грати знову")) {
                    removeAll();
                    add(selectLevelMenu);
                    currentGameStatus = GameStatus.CHOOSE_LEVEL;
                    difficultSelect = false;

                }
            }
            // якщо кінцеве меню
        } else if (currentGameStatus == GameStatus.GAME_OVER_LOSE) {
            if (resultMenuDefeat.getElementAt(e.getX(), e.getY()).getClass() == Button.class) {
                Button button = (Button) resultMenuDefeat.getElementAt(e.getX(), e.getY());
                if (button.text.equals("Грати знову")) {
                    removeAll();
                    add(selectLevelMenu);
                    currentGameStatus = GameStatus.CHOOSE_LEVEL;
                    difficultSelect = false;

                }
            }
            // якщо зараз гра
        } else if (currentGameStatus == GameStatus.PLAYING) {
            if (ballsContainer.getElementCount() == 0) {
                bonusMethod.addBall();
                playerHealth--;
                updatePlayerHp();
            }
        }

    }
    // задає складну складність
    private void setHard() {
        ballRadius = 8;

        maxVx = 7;
        maxVy = 7;
        minVx = 5;
        minVy = 5;

        maxBonusSpeed = 20;
        minBonusSpeed = 10;

        paddleWidth = 25;
        paddleHeight = 5;
        paddleYOffset = 80;

        chanceCreateBonus = 0.1;
        playerStarthp = 2;
        brickHealth = 6;
    }
    // задає середню складність
    private void setMedium() {
        ballRadius = 7;

        maxVx = 5;
        maxVy = 5;
        minVx = 3;
        minVy = 3;

        maxBonusSpeed = 10;
        minBonusSpeed = 3;

        paddleWidth = 60;
        paddleHeight = 7;
        paddleYOffset = 40;

        chanceCreateBonus = 0.2;
        playerStarthp = 4;
        brickHealth =4;
    }
    // задає легку складність
    private void setEasy() {
        ballRadius = 6;

        maxVx = 4;
        maxVy = 4;
        minVx = 3;
        minVy = 3;

        maxBonusSpeed = 5;
        minBonusSpeed = 3;

        paddleWidth = 70;
        paddleHeight = 10;
        paddleYOffset = 20;

        chanceCreateBonus = 0.3;

        playerStarthp = 6;
        brickHealth = 2;
    }

    /**
     * update health label
     */
    public void updatePlayerHp() {
        playerHpLabel.setLabel("Player Health: " + playerHealth);
    }


    //рух ракетки разом з мишкою
    public void mouseMoved(MouseEvent e) {
        if (currentGameStatus == GameStatus.PLAYING) {
            double x = e.getX() - (double) racket.getWidth() / 2;
            if (x < 0) x = 0;
            if (x + racket.getWidth() > getWidth()) x = getWidth() - racket.getWidth();

            if (racket != null) {
                racket.setLocation(x, racket.getY());
            }
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

