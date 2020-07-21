package snake2D;

import javax.swing.*;
import java.awt.*;

class MainScreenPanel extends JPanel {

    MainScreenPanel(){
        setLayout(null);
        setBackground(NewColor.gamebackground); //背景颜色
    }

    @Override   //JPanel<-JComponent<-paintComponent()
    public void paintComponent(Graphics g) {//自动调用 最小化恢复，遮挡。。。

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Algerian", Font.BOLD, 45));
        g2.drawString("Snake2D Remake", 100, 85);
        g2.setColor(Color.ORANGE);
        //g2.drawString("mtala3t", 210, 150);
        /////
        g2.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
        g2.drawString("Snake color", 50, 150);
        ////////////

    }
}

