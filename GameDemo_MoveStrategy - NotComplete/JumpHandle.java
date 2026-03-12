import java.awt.event.*;
/**
 * 在这里给出对类 JumpHandle 的描述。
 * 
 * @author (你的名字)
 * @version (一个版本号或者一个日期)
 */
public class JumpHandle implements IKeyHandler
{
    public void handlePress(MyRole r,KeyEvent e){
        // --- 1. 跳躍鍵 (空白鍵 或 向上鍵) ---
        if (e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_UP) {
            // 檢查：只有當目前「不是」跳躍狀態時，才可以起跳 (防止空中無限連跳)
            if (!(r.getMvState() instanceof Jumper)) {
                r.setMvState(new Jumper());
                r.setDim1(2);       // 切換為跳躍的分鏡圖
                r.setDim2(r.getDir());     // 保持原本面朝的方向
            }
        }
    }
}