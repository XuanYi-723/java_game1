import game.framework.*;
import java.util.*;
import java.awt.*; //for Color.white
import java.awt.event.*;

public class MyGame {
    public static void main(String[] args) {
//Step 1. 產生遊戲背景物件: 標題, 寬度, 高度, 背景顏色
    GameContext ctx = new GameContext ("MY Warrior Game", 500, 500, Color.white);
//Step 2. 產生遊戲物件 
    Game gameEngine = new Game(ctx ); //Game就是遊戲引擎
//Step 3. 產生各種角色 (目前是空的)
    ArrayList<Role> myroles = new ArrayList<> (); //建立角色清單
    //[act][dir]: act:0 stop, act 1: walk, act 2: fly
    
        
    MyRole player = new MyRole(200, 450, 50, 50, 0, -100, 450);
    myroles.add(player ); 
    gameEngine.registerKeyEventHandler(player); //註冊接受鍵盤事件
    
//Step 4: 開始執行
    gameEngine.go(myroles );
}
}