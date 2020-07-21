package snake2D;

import javax.swing.*;
import java.awt.*;

class MainScreenPanel extends JPanel {

    MainScreenPanel(){
        setLayout(null);
        setBackground(NewColor.gamebackground); //������ɫ
    }

    @Override   //JPanel<-JComponent<-paintComponent()
    public void paintComponent(Graphics g) {//�Զ����� ��С���ָ����ڵ�������

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

