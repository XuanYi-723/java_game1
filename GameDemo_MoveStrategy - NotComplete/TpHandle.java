import java.awt.event.*;
/**
 * 在这里给出对类 TpHandle 的描述。
 * 
 * @author (你的名字)
 * @version (一个版本号或者一个日期)
 */
public class TpHandle implements IKeyHandler
{
    public void handlePress(MyRole r,KeyEvent e){
        if(e.getKeyCode()==KeyEvent.VK_R){
            // 1. 呼叫瞬移類別來執行動作
                new Teleport().execute(r); 
                
            // 2. 判斷瞬移後是否在半空中，需不需要掉下來
            if (r.getY() < r.getBottom()) {
                r.setMvState(new Jumper());
                r.setDim1(2);  
                r.setDX(0);     
            }
        }
    }
}