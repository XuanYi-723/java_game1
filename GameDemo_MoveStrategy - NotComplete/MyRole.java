import java.awt.event.*;
import game.framework.*; 
public class MyRole extends SampleRole5
{
    private IMoveState mvState = null;
    private IControl myControl = null;
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
    public void setControl(IControl control){
        myControl = control;
    }
    public IMoveState getMvState(){
        return mvState;
    }
    public void setMvState(IMoveState mvs){
        mvState = mvs;
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
        myControl.control(this,e);
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
        myControl.stopControl(this,e);
    }
}
