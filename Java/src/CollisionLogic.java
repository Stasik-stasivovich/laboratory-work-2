import acm.graphics.GCompound;
import acm.graphics.GObject;
import acm.graphics.GRect;
import acm.graphics.GRectangle;

public class CollisionLogic {
    private Breakout game;
    private GCompound level;
    private GRect racket;
    private BallLinkedList ballLinkedList;
    private BonusLinkedList bonusLinkedList;
    private GObject collider;
    private BonusMethod bonusMethod;

    public CollisionLogic(Breakout game, BallLinkedList ballLinkedList, BonusLinkedList bonusLinkedList){
        this.game = game;
        this.ballLinkedList = ballLinkedList;
        this.bonusLinkedList = bonusLinkedList;
        racket = game.getRacket();
        level = game.getLevel();
        bonusMethod = new BonusMethod(game, ballLinkedList);

    }


    public void checkBallCollision() {
        BallNode temp = ballLinkedList.head;
        while (temp != null) {
            collider = checkCollider(temp.ball, Breakout.BALL_RADIUS);
            if (collider != null) {
                resolveCollision(collider, temp.ball);
            }
            collider = null;
            temp = temp.next;

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

                tryCreateBonus(collider, game);

            }


        }
    }
    private void tryCreateBonus(GObject collider, Breakout game) {

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
            game.add(bonus);
            bonusLinkedList.add(bonus);
        }

    }
    public void checkBonusCollision() {
        BonusNode temp = bonusLinkedList.head;
        BonusNode preTemp = bonusLinkedList.head;
        while (temp != null) {
            if (checkCollider(temp.bonus, (int) (temp.bonus.getWidth()) / 2) == racket) {
                switch (temp.bonus.getType()) {
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
                game.remove(temp.bonus);

                if (preTemp == temp) bonusLinkedList.head = bonusLinkedList.head.next;
                else {
                    preTemp.next = temp.next;
                    if (temp == bonusLinkedList.tail) {
                        bonusLinkedList.tail = preTemp;

                    }
                }
            } else {
                preTemp = temp;
            }
            temp = temp.next;

        }
    }
    public void checkOutOfBaunds() {
        BallNode temp = ballLinkedList.get(0);
        BallNode prev = ballLinkedList.get(0);
        while (temp != null) {
            if (checkIfOutOfBound(temp.ball)) {
                game.remove(temp.ball);
                if (prev == temp) {
                    ballLinkedList.head = ballLinkedList.head.next;
                } else {
                    prev.next = temp.next;
                    if (temp == ballLinkedList.tail) ballLinkedList.tail = prev;
                }
            } else {
                prev = temp;
            }
            temp = temp.next;
        }


    }

    private boolean checkIfOutOfBound(Ball ball) {
        return ball.getY() > game.getHeight();
    }
}
