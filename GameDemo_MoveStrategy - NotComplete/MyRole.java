import java.awt.event.*;
import game.framework.*; 
public class MyRole extends SampleRole5
{
    private IMoveState mvState = null;
    
    private ImageSequence[][] is = {  { new ImageSequence("stop/")},
                              { new ImageSequence("walk_right/"),
                                new ImageSequence("walk_left/")}, 
                              { new ImageSequence("fly_right/"), 
                                new ImageSequence("fly_left/") }
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
    public  void keyPressed(KeyEvent e) {
            if (e.getKeyChar()==' ') { 
                jumpAbility = !jumpAbility;
                if (jumpAbility) {
                    dim1=2; dim2=dir; //決定跳閱方向的分鏡圖
                    mvState=new Jumper();
                } else {//stop   
                     mvState=new Stop();
                     dim1=0; dim2 = 0 ; 
                }
            }
            switch (e.getKeyCode()) {
               case KeyEvent.VK_LEFT://left walk
                dy=0; dx = -10; dim1=1; dim2 = 1; dir = 1 ; 
                mvState=new Walk(); 
                break;     //Remember just to set the state only       
               case KeyEvent.VK_RIGHT://right walk
                dy=0; dx = 10;  dim1=1; dim2 = 0; dir = 0 ; 
                mvState=new Walk();
                break;    //Remember just to set the state only  
              
            }
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_RIGHT:
                dx = 0; // 1. 放開左右鍵時，水平速度歸零
                
                // 2. 如果目前「不是」在跳躍狀態中，就乖乖站好
                if (!(mvState instanceof Jumper)) {
                    mvState = new Stop();
                    dim1 = 0;
                    dim2 = 0;
                }
                break;
        }
    }
}
