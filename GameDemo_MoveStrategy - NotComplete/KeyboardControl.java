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
    public void addHandlers(IKeyHandler handler){
        handlers.add(handler);
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