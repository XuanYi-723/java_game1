import java.awt.event.*;
/**
 * 在这里给出对类 ClimbHandle 的描述。
 * 
 * @author (你的名字)
 * @version (一个版本号或者一个日期)
 */
public class ClimbHandle implements IKeyHandler
{
    public void handlePress(MyRole r,KeyEvent e){
        if(e.getKeyCode()==KeyEvent.VK_W){
            if (r.getX() <= 0 || r.getX() >= 500 - r.getW()) {
                    r.setDY(-10);             // 垂直向上的攀爬速度
                    r.setMvState(new Walk()); // 利用 Walk 狀態來直線移動 (不受重力影響)
                    // dim1 = 0;          //若有攀爬的分鏡圖，這裡切換
                }
        }
    }
    public void handleRelease(MyRole r,KeyEvent e){
        if(e.getKeyCode()==KeyEvent.VK_W) {
            r.setDY(0); // 停止向上攀爬
                
                //如果放開 W 的時候人還在半空中，就讓他自由落體
            if (r.getY() < r.getBottom() && !(r.getMvState() instanceof Jumper)) {
                r.setMvState(new Jumper(0)); //傳入 0，代表沒有向上衝力，直接受重力落下
                r.setDim1(2);//動話切換
            }
        }
    }
}