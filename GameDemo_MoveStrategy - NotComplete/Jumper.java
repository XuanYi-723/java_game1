import game.framework.*;

public class Jumper implements IMoveState
{
    Movement jump = null;
    public void move(SampleRole5 r){
        int x = 0;
        int y = 0;
        if (jump == null) {
            jump = new Jump(r.getJVX(), r.getJVY());
        }
        jump.updatePos(r.getX(), r.getY());
        x = jump.getX();
        y = jump.getY();
        if (jump != null) {
            if (y >= r.getBottom()) {
                y = r.getBottom();
                jump.reset();
            }
            r.setX(x);
            r.setY(y);
        }
    }
}
