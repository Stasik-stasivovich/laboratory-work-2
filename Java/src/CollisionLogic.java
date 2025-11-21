import acm.graphics.GCompound;
import acm.graphics.GObject;
import acm.graphics.GRect;
import acm.graphics.GRectangle;

public class CollisionLogic {
    private Breakout game;
    private GCompound level;
    private GRect racket;

    private GCompound bonusContainer;
    private GCompound ballsContainer;


    private GObject collider;
    private BonusMethod bonusMethod;

    public CollisionLogic(Breakout game,GCompound ballsContainer, GCompound bonusContainer, BonusMethod bonusMethod) {
        this.game = game;

        this.bonusContainer = bonusContainer;
        this.ballsContainer = ballsContainer;

        racket = game.getRacket();
        level = game.getLevel();
        this.bonusMethod = bonusMethod;

    }

    // перебирає всі м'ячі в списку і викликає методи що обробляють колізію якщо така є
    public void checkBallCollision() {
        int len = ballsContainer.getElementCount();
        for (int i = 0; i < len; i++) {
            Ball tempBall = (Ball) ballsContainer.getElement(i);
            collider = checkCollider(tempBall, Breakout.BALL_RADIUS);
            if (collider != null) {
                resolveCollision(collider, tempBall);
            }
            collider = null;
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

    // етод що визначає з чим був удар і чи був він вертикальним чи горизонтальним у випадку з цеглиною
    private void resolveCollision(GObject collider, Ball ball) {
        if (collider == racket) {
            ball.setVy(-1 * Math.abs(ball.getVy()));
            ball.setVx((int) Math.signum(ball.getVx()) * game.random.nextInt(Breakout.MIN_VX, Breakout.MAX_VX));
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

                tryCreateBonus();

            }


        }
    }

    // якщо випало тру то додаємо випадковий бонус
    private void tryCreateBonus() {

        if (game.random.nextBoolean(Breakout.CHANCE_CREATE_BONUS)) {
            Bonus bonus = null;
            switch (game.random.nextInt(1, 3)) {
                case 1:
                    bonus = new Bonus(collider.getX(), collider.getY(), 1, game.random.nextInt(Breakout.MIN_BONUS_SPEED, Breakout.MAX_BONUS_SPEED));
                    break;
                case 2:
                    bonus = new Bonus(collider.getX(), collider.getY(), 2, game.random.nextInt(Breakout.MIN_BONUS_SPEED, Breakout.MAX_BONUS_SPEED));
                    break;
                case 3:
                    bonus = new Bonus(collider.getX(), collider.getY(), 3, game.random.nextInt(Breakout.MIN_BONUS_SPEED, Breakout.MAX_BONUS_SPEED));
            }
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
                }
                bonusContainer.remove(tempBonus);
                len--;

            }
            else index++;
        }
    }

    public void checkOutOfBaunds() {
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



    private boolean checkIfOutOfBound(Ball ball) {
        return ball.getY() > game.getHeight();
    }
}
