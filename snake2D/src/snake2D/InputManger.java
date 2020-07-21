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

        if (!gameBoard.moved) {//避免转向过快
            return;
        }

        int key = e.getKeyCode();

        if (gameBoard.isGameRunning()) {//游戏运行中
            if (key == KeyEvent.VK_UP) {
                gameBoard.changeSnakeDirection(1);
                gameBoard.moved = false;//转向完成，等待paint完成后才能进行下一次键入
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
            if (key == KeyEvent.VK_SPACE) {//暂停游戏
                gameBoard.pauseGame();
            }
            return;
        }

        if (key == KeyEvent.VK_SPACE) {//游戏暂停中，空格键
            if (!gameBoard.isGameOver()) {
                try {
                    gameBoard.startGame();//继续游戏
                } catch (InterruptedException ex) {
                }
            } else {
                gameBoard.replay();//重新开始
            }
            return;
        }

        if (key == KeyEvent.VK_ESCAPE) {//退出键
            System.exit(0);
        }

    }

    public void keyReleased(KeyEvent e) {
    }

}

