public class Teleport {
    
    // 傳入角色，讓這個技能去操作角色
    public void execute(MyRole role) {
        int screenWidth = 500;
        int bottom = role.getBottom();
        
        // 1. 計算座標
        int randomX = (int)(Math.random() * (screenWidth - role.getW()));
        int randomY = (int)(Math.random() * bottom);
        
        // 2. 改變角色座標
        role.setX(randomX);
        role.setY(randomY);
    }
}