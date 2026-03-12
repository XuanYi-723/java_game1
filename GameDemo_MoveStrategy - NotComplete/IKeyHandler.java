import java.awt.event.*;
/**
 * 在这里给出对接口 IKeyHandle 的描述。
 * 
 * @author (你的名字)
 * @version (一个版本号或者一个日期)
 */

public interface IKeyHandler
{
    void handlePress(MyRole r,KeyEvent e);
    default void handleRelease(MyRole r,KeyEvent e){}
}