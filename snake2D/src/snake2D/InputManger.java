package snake2D;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * @author Mohammed.Talaat (mtala3t)
 * @version 1.0
 */
public class InputManger implements KeyListener {

    private GameBoardPanel gameBoard;

    public InputManger(GameBoardPanel gameBoard) {
        this.gameBoard = gameBoard;
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {

        if (!gameBoard.moved) {//����ת�����
            return;
        }

        int key = e.getKeyCode();

        if (gameBoard.isGameRunning()) {//��Ϸ������
            if (key == KeyEvent.VK_UP) {
                gameBoard.changeSnakeDirection(1);
                gameBoard.moved = false;//ת����ɣ��ȴ�paint��ɺ���ܽ�����һ�μ���
            }
            if (key == KeyEvent.VK_DOWN) {
                gameBoard.changeSnakeDirection(2);
                gameBoard.moved = false;
            }
            if (key == KeyEvent.VK_RIGHT) {
                gameBoard.changeSnakeDirection(3);
                gameBoard.moved = false;
            }
            if (key == KeyEvent.VK_LEFT) {
                gameBoard.changeSnakeDirection(4);
                gameBoard.moved = false;
            }
            if (key == KeyEvent.VK_SPACE) {//��ͣ��Ϸ
                gameBoard.pauseGame();
            }
            return;
        }

        if (key == KeyEvent.VK_SPACE) {//��Ϸ��ͣ�У��ո��
            if (!gameBoard.isGameOver()) {
                try {
                    gameBoard.startGame();//������Ϸ
                } catch (InterruptedException ex) {
                }
            } else {
                gameBoard.replay();//���¿�ʼ
            }
            return;
        }

        if (key == KeyEvent.VK_ESCAPE) {//�˳���
            System.exit(0);
        }

    }

    public void keyReleased(KeyEvent e) {
    }

}

