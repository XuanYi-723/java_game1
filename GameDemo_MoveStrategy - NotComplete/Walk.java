import game.framework.*;

public class Walk implements IMoveState
{
     
     public void move(SampleRole5 r){
        int dx = r.getDX();
        int dy = r.getDY();
        int x = r.getX();
        int y = r.getY();
        x += dx;
        y += dy;
        r.setX(x);
        r.setY(y);
     }
    
}
