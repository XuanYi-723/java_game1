import game.framework.*;

public class Jumper implements IMoveState
{   
    private double vy = -3;
    private double gravity = 1.2;
    
    public void move(SampleRole5 r){
        int x = r.getX();
        int y = r.getY();
        
        // 3. 垂直移動 (套用重力物理)
        y += (int)vy;     // Y 座標加上目前的速度
        vy += gravity;    // 速度受到重力影響，不斷往下加 (越來越偏向正數)

        // 4. 判斷是否落地
        if (y >= r.getBottom()) {
            y = r.getBottom();
            // 這裡不需要切換回 Stop 狀態，因為我們已經在 MyRole.java 的 run() 裡面做掉這件事了！
        }

        // 5. 將計算好的新座標存回角色身上
        r.setX(x);
        r.setY(y);
    }
}
