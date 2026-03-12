import java.awt.event.*;
/**
 * 在这里给出对类 SleepHandle 的描述。
 * 
 * @author (你的名字)
 * @version (一个版本号或者一个日期)
 */
public class SleepHandle implements IKeyHandler
{
    public void handlePress(MyRole r,KeyEvent e){
        if(e.getKeyCode()==KeyEvent.VK_Z){
            if (r.getMvState() instanceof Sleep) {
                    r.setMvState(new Stop()); // 狀態變回停止，這樣就解除睡覺的鎖定了
                    r.setDim1(3);             // 依然使用第 4 排的動作
                    r.setDim2(1);             // ✨ 切換成 wake/ (睡醒) 的圖片
                } else if (!(r.getMvState() instanceof Jumper)) {
                    r.setDX(0);
                    r.setMvState(new Sleep());
                    r.setDim1(3);              
                    r.setDim2(0);
                }
        }
    }
}