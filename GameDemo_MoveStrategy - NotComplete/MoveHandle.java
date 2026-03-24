import java.awt.event.*;

public class MoveHandle implements IKeyHandler
{
    public void handlePress(MyRole r,KeyEvent e){
        switch(e.getKeyCode()){
            case KeyEvent.VK_A: // 向左走
                r.setDX(-10); 
                r.setDir(1); 
                r.setDim2(1); // 面朝左
                
                // 如果角色站在地上，才需要切換成走路動畫；如果在空中，只改變 dx 速度就好
                if (!(r.getMvState() instanceof Jumper)) {
                    r.setDim1(1); 
                    r.setMvState(new Walk()); 
                }
                break;
                
            case KeyEvent.VK_D: // 向右走
                r.setDX(10); 
                r.setDir(0); 
                r.setDim2(0); // 面朝右
                
                // 如果角色站在地上，才需要切換成走路動畫；如果在空中，只改變 dx 速度就好
                if (!(r.getMvState() instanceof Jumper)) {
                    r.setDim1(1); 
                    r.setMvState(new Walk());
                }
                break;
                
            case KeyEvent.VK_S: // 設定專屬的「停止/蹲下鍵」，可以用下鍵
                r.setDX(0);
                if (!(r.getMvState() instanceof Jumper)) {
                    r.setMvState(new Stop());
                    r.setDim1(0); 
                    r.setDim2(0);
                }
                break;
        }
    }
    public  void handleRelease(MyRole r,KeyEvent e){
        if(e.getKeyCode()==KeyEvent.VK_A||e.getKeyCode()==KeyEvent.VK_D){
            r.setDX(0); // 1. 放開左右鍵時，水平速度歸零
                
            // 2. 如果目前「不是」在跳躍狀態中，就乖乖站好
            if (!(r.getMvState() instanceof Jumper)) {
                r.setMvState(new Stop());
                r.setDim1(0); 
                r.setDim2(0);
            }
        }
    }
}