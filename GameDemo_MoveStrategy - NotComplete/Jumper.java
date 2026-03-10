import game.framework.*;

public class Jumper implements IMoveState
{   
    private double vy;
    private double gravity = 0.9;
    
    public Jumper () {
        this.vy = -5;
    }
    //新增的自由落體 (初速度由參數決定，傳入 0 就是直接掉下來)
    public Jumper (double initialVy) {
        this.vy = initialVy;
    }
    
    public void move(SampleRole5 r){
        int y = r.getY();

        //垂直移動(套用重力)
        y += (int)vy;
        vy += gravity;

        if (y >= r.getBottom()) {
            y = r.getBottom();
        }

        r.setY(y);
    }
}
