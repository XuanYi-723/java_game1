import game.framework.*;

public class Background extends SampleRole5 {
    
    // 建構子：設定背景的座標與大小
    public Background(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        
        // 載入 bg 資料夾圖片
        super.is = new ImageSequence[][] {
            { new ImageSequence("bg/") }
        };
        
        this.dim1 = 0;
        this.dim2 = 0;
    }
    
    // 因為背景不需要重力也不需要移動，把 run 留空或只呼叫 super.run()
    @Override
    public void run() {
        super.run(); 
    }
}