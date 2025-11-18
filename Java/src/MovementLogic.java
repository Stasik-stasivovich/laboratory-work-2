import acm.graphics.GCompound;
import acm.program.GraphicsProgram;

public class MovementLogic {
    private GCompound ballsContainer = new GCompound();
    private GCompound bonusContainer = new GCompound();
    private Breakout game;

    MovementLogic(Breakout game, GCompound ballsContainer, GCompound bonusContainer) {
        this.game = game;
        this.ballsContainer = ballsContainer;
        this.bonusContainer = bonusContainer;
    }


    // рухаєм всі бонуси
    public void moveBonus() {
        int len = bonusContainer.getElementCount();
        int index = 0;
        while (index < len) {
            Bonus tempBonus = (Bonus) bonusContainer.getElement(index);
            tempBonus.move(0,tempBonus.getVy());
            if (tempBonus.getY() > game.getHeight()) {
                bonusContainer.remove(tempBonus);
                len --;
            }
            else index ++;
        }


        /*
        BonusNode temp = bonusStructure.head;
        BonusNode preTemp = bonusStructure.head;
        while (temp != null) {
            temp.bonus.move(0, temp.bonus.getVy());
            if (temp.bonus.getY() >= game.getHeight()) {
                game.remove(temp.bonus);
                if (preTemp == temp) {
                    bonusStructure.head = bonusStructure.head.next;
                } else {
                    preTemp.next = temp.next;
                    if (temp == bonusStructure.tail) bonusStructure.tail = preTemp;
                }
            } else {
                preTemp = temp;
            }
            temp = temp.next;

        }
         */
    }

    //рухаєм всі м'ячі
    public void moveBall() {

        int len = ballsContainer.getElementCount();
        int index = 0;
        while (index < len) {
            Ball tempBall = (Ball) ballsContainer.getElement(index);
            tempBall.move(tempBall.getVx(), tempBall.getVy());
            if (tempBall.getX() < 0) {
                tempBall.setVx(Math.abs(tempBall.getVx()));
            } else if (tempBall.getX() > game.getWidth() - Breakout.BALL_RADIUS * 2) {
                tempBall.setVx(-Math.abs(tempBall.getVx()));
            }
            index++;
        }





        /*
        BallNode temp = ballsStructure.get(0);
        while (temp != null) {
            temp.ball.move(temp.ball.getVx(), temp.ball.getVy());
            if (temp.ball.getX() < 0 || temp.ball.getX() + Breakout.BALL_RADIUS * 2 >= game.getWidth())
                temp.ball.setVx(-1 * temp.ball.getVx());
            if (temp.ball.getY() < 0) {
                temp.ball.setVy(Math.abs(temp.ball.getVy()));
            }
            temp = temp.next;
        }

         */
    }
}
