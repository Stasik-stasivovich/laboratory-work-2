import acm.program.GraphicsProgram;

public class MovementLogic {



    // рухаєм всі бонуси
    public static void moveBonus(BonusStructure bonusStructure, GraphicsProgram game) {
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
                    if (temp ==  bonusStructure.tail) bonusStructure.tail = preTemp;
                }
            } else {
                preTemp = temp;
            }
            temp = temp.next;
        }
    }
    //рухаєм всі м'ячі
    public static void moveBall(BallsStructure ballsStructure, GraphicsProgram game) {
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
    }
}
