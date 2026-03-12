import java.awt.event.*;
/**
 * 在这里给出对类 IControl 的描述。
 * 
 * @author (你的名字)
 * @version (一个版本号或者一个日期)
 */
public interface IControl
{
    void control(MyRole r,KeyEvent e);
    void stopControl(MyRole r,KeyEvent e);
}