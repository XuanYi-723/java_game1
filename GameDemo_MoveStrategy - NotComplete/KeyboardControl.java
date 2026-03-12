import java.awt.event.*;
import java.util.*;
/**
 * 在这里给出对类 KeyboardControl 的描述。
 * 
 * @author (你的名字)
 * @version (一个版本号或者一个日期)
 */
public class KeyboardControl implements IControl{
    private List<IKeyHandler> handlers = new ArrayList<>();
    public KeyboardControl(){
        handlers.add(new JumpHandle());
        handlers.add(new MoveHandle());
        handlers.add(new TpHandle());
        handlers.add(new ClimbHandle());
        handlers.add(new SleepHandle());
    }
    public void control(MyRole r,KeyEvent e){
        for (IKeyHandler handler : handlers) {
            handler.handlePress(r, e);
        }
    }
    public void stopControl(MyRole r,KeyEvent e){
        for (IKeyHandler handler : handlers) {
            handler.handleRelease(r, e);
        }
    }
}