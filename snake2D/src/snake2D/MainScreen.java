package snake2D;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.*;

/**
 * @author Mohammed.Talaat (mtala3t)
 * @version 1.0
 */

public class MainScreen extends JFrame implements ActionListener {
    private static final long serialVersionUID = -1299314404835604855L;

    int choice[] = new int[4];

    ButtonGroup levelGroup= new ButtonGroup();
    ArrayList<JRadioButton> levels=new ArrayList<>();
    String levelStrings[] = {"Easy", "Normal", "Hard"};

    ButtonGroup mapGroup= new ButtonGroup();
    ArrayList<JRadioButton> maps=new ArrayList<>();
    String mapStrings[] = {"Map1", "Map2", "Map3"};

    ButtonGroup colorGroup= new ButtonGroup();
    ArrayList<JRadioButton> colors=new ArrayList<>();
    String snakecolor[] = {"white", "yellow", "green"};
    /////
    ButtonGroup ModeGroup= new ButtonGroup();
    ArrayList<JRadioButton> modes=new ArrayList<>();
    String modeStrings[] = {"Day", "Night"};
    ////////////////////
    MainScreenPanel buttonPanel;    //�̳���JPanel

    public MainScreen() {
        //������ҳ����
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(0, 0, 600, 400);
        setResizable(false);

        //������ҳ��panel��Ӱ�ť
        buttonPanel = new MainScreenPanel();

        /////set Map
        for (int i = 0; i < mapStrings.length; i++) {    //���������ť
            maps.add(new JRadioButton(mapStrings[i]));
            mapGroup.add(maps.get(i));
            //mapGroup.getSelection()
             maps.get(i).addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent a) {
                    choice[0]=maps.indexOf(a.getSource());
                }
            });
            maps.get(i).setBackground(Color.yellow);    //��ť��ɫ
            maps.get(i).setBounds(190 + i * 80, 100, 60, 20);
            buttonPanel.add(maps.get(i));
        }


        ///set color to be continue
        colors.add(new JRadioButton("."));
        colors.get(0).setBackground(NewColor.snake1);
        colors.add(new JRadioButton(".."));
        colors.get(1).setBackground(NewColor.snake2);
        colors.add(new JRadioButton("..."));
        colors.get(2).setBackground(NewColor.snake3);
        for (int i = 0; i < 3; i++) {
            colorGroup.add(colors.get(i));
            colors.get(i).setBounds(80, 170 + i * 30, 20, 20);
            buttonPanel.add(colors.get(i));
            colors.get(i).addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    choice[2]=colors.indexOf(e.getSource());
                }
            });
        }

        //set level
        for (int i = 0; i < 3; i++) {    //���������ť
            levels.add(new JRadioButton(levelStrings[i]));
            levelGroup.add(levels.get(i));
            levels.get(i).addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    choice[1]=levels.indexOf(e.getSource())+1;
                }
            });
            ////////////////
            levels.get(i).setBackground(Color.YELLOW);    //��ť��ɫ
            levels.get(i).setBounds(260, 150 + i * 50, 80, 30);
            buttonPanel.add(levels.get(i));
        }

        ///////
        
      //set Mode
        for (int i = 0; i < 2; i++) {    //���������ť
            modes.add(new JRadioButton(modeStrings[i]));
            ModeGroup.add(modes.get(i));
            modes.get(i).addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    choice[3]=modes.indexOf(e.getSource());
                }
            });
        }
        modes.get(0).setBackground(Color.white);    //
        modes.get(1).setBackground(Color.black);    //
        modes.get(0).setBounds(450, 210, 60, 20);
        modes.get(1).setBounds(450, 240, 60, 20);
        buttonPanel.add(modes.get(0));
        buttonPanel.add(modes.get(1));
        ///////
        
        JButton enter = new JButton("Enter");
        enter.addActionListener(this);
        enter.setBackground(Color.GREEN);
        enter.setBounds(265, 300, 70, 20);
        buttonPanel.add(enter);
        ///////////////////////////////
        add(buttonPanel);


        //Ĭ��ѡ��

        maps.get(0).setSelected(true);

        setVisible(true);
    }
//choice ��ͼ �ٶ� ��ɫ
    public void actionPerformed(ActionEvent e) {
        //System.out.println(choice[0]+" "+choice[1]+" "+choice[2]+" "+choice[3]);
        new GameBoardWindow(choice[0], choice[1], choice[2], choice[3]);
        dispose();
    }
}

