import java.awt.event.*;
import game.framework.*; 
public class MyRole extends SampleRole5
{
    private IMoveState mvState = null;
    
    private ImageSequence[][] is = {  { new ImageSequence("stop/")},
                              { new ImageSequence("walk_right/"),
                                new ImageSequence("walk_left/")}, 
                              { new ImageSequence("fly_right/"), 
                                new ImageSequence("fly_left/") },
                              { new ImageSequence("sleep/"), new ImageSequence("wake/")}
                         };  //建立角色分鏡圖
    
    public MyRole(int x, int y, int w, int h ,int jvx, int jvy, int bottom) {
        this.x = x; this.y = y;
        this.w = w; this.h = h;
        this.jvx = jvx; this.jvy = jvy;
        this.bottom = bottom;
        //this.path= path;
        //this.type = type;
        super.is= is;
//       this.mvState = new Stop(); 
//       this.dim1 = 0; // 對應 "stop/"
//       this.dim2 = 0;
    }
    
    @Override
    public void run() {
        if (mvState!=null) {
            mvState.move(this);
        }
        int screenWidth = 500;
        int screenHeight = 500;
        //X軸邊界限制(左右)
        if (x < 0) {
            x = 0;
        } else if (x > screenWidth - w) {
            x = screenWidth - w;
        }
        //Y軸邊界限制(上下)
        if (y < 0) {
            y = 0;
        } else if (y > screenHeight - h) {
            y = screenHeight - h;
        }
        
        if (dy < 0 && x > 0 && x < (screenWidth - w)) {
            dy = 0;
            this.mvState = new Jumper(0); // 變成自由落體
            this.dim1 = 2;                // 切換成掉落的分鏡圖
        }
        // 2. 自動判斷是否落地並切換回 Stop 狀態
        // 如果目前是跳躍狀態，且已經精準踩在底線上
        if (mvState instanceof Jumper && y >= bottom) { // instanceof Jumper
            y = bottom;
            jumpAbility = false;
            if (dx == 0) {
                this.mvState = new Stop();
                this.dim1 = 0;
                this.dim2 = 0;
            } else {
                this.mvState = new Walk();
                this.dim1 = 1;
                this.dim2 = (dx > 0) ? 0 : 1;     
            }
        }
        
        //更新模型
        super.run();
    }
    
    private void setMoveState(IMoveState mvState) {
        this.mvState =mvState ;
    }
    @Override
    public void keyPressed(KeyEvent e) {
        
        // --- 1. 跳躍鍵 (空白鍵 或 向上鍵) ---
        if (e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_UP) {
            // 檢查：只有當目前「不是」跳躍狀態時，才可以起跳 (防止空中無限連跳)
            if (!(mvState instanceof Jumper)) {
                mvState = new Jumper();
                dim1 = 2;       // 切換為跳躍的分鏡圖
                dim2 = dir;     // 保持原本面朝的方向
            }
        }

        // --- 2. 移動與其他按鍵 ---
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A: // 向左走
                dx = -10; 
                dir = 1; 
                dim2 = 1; // 面朝左
                
                // 如果角色站在地上，才需要切換成走路動畫；如果在空中，只改變 dx 速度就好
                if (!(mvState instanceof Jumper)) {
                    dim1 = 1; 
                    mvState = new Walk(); 
                }
                break;
                
            case KeyEvent.VK_D: // 向右走
                dx = 10; 
                dir = 0; 
                dim2 = 0; // 面朝右
                
                // 如果角色站在地上，才需要切換成走路動畫；如果在空中，只改變 dx 速度就好
                if (!(mvState instanceof Jumper)) {
                    dim1 = 1; 
                    mvState = new Walk();
                }
                break;
                
            case KeyEvent.VK_S: // 設定專屬的「停止/蹲下鍵」，可以用下鍵
                dx = 0;
                if (!(mvState instanceof Jumper)) {
                    mvState = new Stop();
                    dim1 = 0; 
                    dim2 = 0;
                }
                break;
            
            case KeyEvent.VK_R: //tp
                // 1. 呼叫瞬移類別來執行動作
                new Teleport().execute(this); 
                
                // 2. 判斷瞬移後是否在半空中，需不需要掉下來
                if (this.y < bottom) {
                    this.mvState = new Jumper();
                    this.dim1 = 2;  
                    this.dx = 0;     
                }
                break;
            case KeyEvent.VK_W: 
                //只有在「貼著左牆」或「貼著右牆」時才能爬
                if (x <= 0 || x >= 500 - w) {
                    dy = -10;             // 垂直向上的攀爬速度
                    mvState = new Walk(); // 利用 Walk 狀態來直線移動 (不受重力影響)
                    // dim1 = 0;          //若有攀爬的分鏡圖，這裡切換
                }
                break;
            case KeyEvent.VK_Z:
                if (mvState instanceof Sleep) {
                    this.mvState = new Stop(); // 狀態變回停止，這樣就解除睡覺的鎖定了
                    this.dim1 = 3;             // 依然使用第 4 排的動作
                    this.dim2 = 1;             // ✨ 切換成 wake/ (睡醒) 的圖片
                } else if (!(mvState instanceof Jumper)) {
                    dx = 0;
                    this.mvState = new Sleep();
                    this.dim1 = 3;              
                    this.dim2 = 0;
                }
                break;
        }
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A:
            case KeyEvent.VK_D:
                dx = 0; // 1. 放開左右鍵時，水平速度歸零
                
                // 2. 如果目前「不是」在跳躍狀態中，就乖乖站好
                if (!(mvState instanceof Jumper)) {
                    mvState = new Stop();
                    dim1 = 0;
                    dim2 = 0;
                }
                break;
            case KeyEvent.VK_W:
                dy = 0; // 停止向上攀爬
                
                //如果放開 W 的時候人還在半空中，就讓他自由落體
                if (y < bottom && !(mvState instanceof Jumper)) {
                    mvState = new Jumper(0); //傳入 0，代表沒有向上衝力，直接受重力落下
                    dim1 = 2;//動話切換
                }
                break;
        }
    }
}
