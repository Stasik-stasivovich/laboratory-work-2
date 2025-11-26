import acm.graphics.GCompound;
import acm.graphics.GObject;
import acm.graphics.GRect;
import acm.graphics.GRectangle;

/**
 * class with collision logic and out of bounce checker
 */
public class CollisionLogic {
    private Breakout game;
    private GCompound level;
    private GRect racket;

    private GCompound bonusContainer;
    private GCompound ballsContainer;


    private GObject collider;
    private BonusMethod bonusMethod;

    /**
     *
     * @param game object of canvas
     * @param ballsContainer GCompound with balls
     * @param bonusContainer GCompound with bonus
     * @param bonusMethod object of BonusMethod Class
     */
    public CollisionLogic(Breakout game, GCompound ballsContainer, GCompound bonusContainer, BonusMethod bonusMethod) {
        this.game = game;

        this.bonusContainer = bonusContainer;
        this.ballsContainer = ballsContainer;

        racket = game.getRacket();
        level = game.getLevel();
        this.bonusMethod = bonusMethod;

    }

    // перебирає всі м'ячі в списку і викликає методи що обробляють колізію якщо така є

    /**
     * check collision balls in balls GCompound
     */
    public void checkBallCollision() {
        int len = ballsContainer.getElementCount();
        for (int i = 0; i < len; i++) {
            Ball tempBall = (Ball) ballsContainer.getElement(i);
            collider = checkCollider(tempBall, Breakout.ballRadius);
            if (collider != null) {
                resolveCollision(collider, tempBall);
            }
            collider = null;
        }

    }

    /**
     * check countdown of change size
     */
    public void checkExpansionCountdown(){
        if (game.expansionCountdown != 0){
            game.expansionCountdown--;
            if (game.expansionCountdown == 0){
                double delta = game.racket.getWidth()  - game.paddleWidth ;
                game.racket.setSize(Breakout.paddleWidth, game.racket.getHeight());
                game.racket.move(delta/2,0);
            }
        }

    }

    // метод що перевіряє чи об'єкт пересікся з об'єктом на рівні
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

    /** етод що визначає з чим був удар і чи був він вертикальним чи горизонтальним у випадку з цеглиною
    Якщо попали в ракетку відзеркалюємо швидкість по вертикалі.
    Якщо попали в цеглину робим наступне:
        1) визначаємо перетин цеглини і м'яча
        2) якщо ширина перетину більша за висоту то удар горизонтальний інакше вертикальний
        ps на наближені дає хорошу точність (але можлива поведінка 'черва' (фіча))
    */
    private void resolveCollision(GObject collider, Ball ball) {
        if (collider == racket) {
            ball.setVy(-1 * Math.abs(ball.getVy()));
            ball.setVx((int) Math.signum(ball.getVx()) * game.random.nextDouble(Breakout.minVx, Breakout.maxVx));
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
            if(game.impactSound.isRunning()){
                game.impactSound.stop();
            }
            game.impactSound.setFramePosition(0);
            game.impactSound.start();
            if (((Brick) collider).isDead()) {
                level.remove(collider);

                tryCreateBonus();

            }


        }
    }

    // спроба створити бонус, використовуючи шанс
    private void tryCreateBonus() {

        if (game.random.nextBoolean(Breakout.chanceCreateBonus)) {
            Bonus bonus = null;
            int type = game.random.nextInt(1,5);
            bonus = new Bonus(collider.getX(), collider.getY(), type, game.random.nextInt(Breakout.minBonusSpeed, Breakout.maxBonusSpeed));
            bonusContainer.add(bonus);
        }

    }

    // перевіряємо чи задів бонус ракетку
    public void checkBonusCollision() {
        int len = bonusContainer.getElementCount();
        int index = 0;
        while (index < len) {
            Bonus tempBonus = (Bonus) bonusContainer.getElement(index);
            if (checkCollider(tempBonus, (int) (tempBonus.getWidth()) / 2) == racket) {
                switch (tempBonus.getType()) {
                    case 1:
                        bonusMethod.addBall();
                        break;
                    case 2:
                        bonusMethod.doubleBalls();
                        break;
                    case 3:
                        bonusMethod.addHealth();
                        break;
                    case 4:
                        bonusMethod.changeSizeRacket(game.paddleExpansion);
                        break;
                    case 5:
                        bonusMethod.changeSizeRacket(-1 * game.paddleReduction);
                }
                bonusContainer.remove(tempBonus);
                len--;

            }
            else index++;
        }
    }

    /**
     * check if balls out of bounds
     */
    public void checkOutOfBounds() {
        int len = ballsContainer.getElementCount();
        int index = 0;
        while (index < len) {
            Ball tempBall = (Ball) ballsContainer.getElement(index);
            if (checkIfOutOfBound(tempBall)) {
                ballsContainer.remove(tempBall);
                len --;
            }
            else index++;
        }
    }


    // перевіряємо чи м'яч за межами
    private boolean checkIfOutOfBound(Ball ball) {
        return ball.getY() > game.getHeight();
    }
}
