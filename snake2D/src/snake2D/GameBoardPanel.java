package snake2D;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;
import javax.swing.Timer;
///
import java.lang.Math;
////////

import sound.SoundManger;


/////
import java.io.IOException;
//////////////////////

/**
 * @author Mohammed.Talaat (mtala3t)
 * @version 1.0
 */
@SuppressWarnings("serial")
public class GameBoardPanel extends JPanel implements ActionListener {

    /**
     * Creates a new instance of GameBoard
     */
    private int lock = 0;

    private Snake snake;
    private AbstractItem snakeFood;
    private AbstractItem shrinker;
    private AbstractItem slower;

    private InputManger inputManager;
    private SoundManger soundManger;
    private SoundManger eatingfoodsound;
    private SoundManger collisionsound;
    private SoundManger timeitemsound;
    private SoundManger shrinkitemsound;
    private SoundManger shrinkitemsound2;
	// food sound manager

    private Timer gameThread;
    private Timer timerThread;

    private boolean isGameOver = false;

    private int timer = 0;
    private int playerScore = 0;

    private String soundFilePath;
    private String eatfoodFilePath;
    private String collideFilePath;
    private String timeitemFilePath;
    private String shrinkerFilePath;
    private String shrinkerFilePath2;
    ///
    private Rectangle2D.Double[] mymap;
    private int mapp;
    private int snakecolor;
    private int mode;

    private int foodType;

    /////////////
    public boolean moved = true;

    public GameBoardPanel(int mapnumber, int level, int color, int mode) {
        /////
        try {
            String current = new java.io.File(".").getCanonicalPath();
            soundFilePath = current + "\\soundfile\\start.wav";    // BGM
            collideFilePath = current + "\\soundfile\\mapcollision.wav";
            timeitemFilePath = current + "\\soundfile\\timeitem.wav";
            shrinkerFilePath = current + "\\soundfile\\cutter.wav";
            shrinkerFilePath2 = current + "\\soundfile\\gameover.wav";
            eatfoodFilePath = current + "\\soundfile\\eatfood.wav";/////////////////////
        } catch (IOException e) {
            e.printStackTrace();
        }
        //////////////////////

        snakecolor = color;
        mapp = mapnumber;
        this.mode = mode;
        Map loadmap = new Map();
        if (mapp != 0) mymap = loadmap.choseMap(mapnumber);//load map

        /////
        if (mode == 1) {
            setBackground(NewColor.gamebackground2);
        } else {
            setBackground(NewColor.gamebackground);
        }
        ///////////////////
        //setBackground(NewColor.gamebackground);
        setFocusable(true);

        snake = new Snake();
        snakeFood = new AbstractItem(mapp, snakeFood.sFood);
        shrinker = new AbstractItem(mapp, snakeFood.sItem);
        slower = new AbstractItem(mapp, snakeFood.sTime);

        inputManager = new InputManger(this);
        ///
        soundManger = new SoundManger(soundFilePath);
        soundManger.start();
        try {
			soundManger.join();
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
        ////////
        gameThread = new Timer(140 / level, this);//每隔delay毫秒，调用一次对象的actionPerform

        timerThread = new Timer(1000, e -> timer++);

        addKeyListener(inputManager);

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        if (isGameRunning()) {
            snake.move();
            try {
                checkCollision();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            DrawSnakeFood(g2);
            DrawSnakeItem(g2);
            DrawSnakeSlower(g2);
        }


        DrawStatusbar(g2);
        DrawBoundry(g2);
        DrawSnake(g2);
        moved = true;
    }

    public void DrawBoundry(Graphics2D g2) {
        for (int i = 0; i < 17; i++) {
            Rectangle2D.Double rect = new Rectangle2D.Double(227.0 - i,
                    127.0 - i, 624, 480);//横38格，竖29格,一次走16格

            g2.setColor(Color.YELLOW);//边框颜色

            g2.draw(rect);
        }
        ///
        //Rectangle2D.Double tmp = new Rectangle2D.Double(227.0, 127.0, 16, 16);//左上顶坐标
        g2.setColor(Color.PINK);

        //Rectangle2D.Double tmp1 = new Rectangle2D.Double(819, 575, 16, 16);//右下底坐标
        g2.setColor(NewColor.wall);
        ///////////////
        if (mapp != 0) {
            if (mode == 0) {
                for (int i = 0; i < mymap.length; i++) {
                    g2.draw(mymap[i]);
                    g2.fill(mymap[i]);
                }
            } else {
                for (int i = 0; i < mymap.length; i++) {
                    /////
                    if (Math.abs(snake.getSnakeBody().get(0).x - mymap[i].x) <= 16 * 3 && Math.abs(snake.getSnakeBody().get(0).y - mymap[i].y) <= 16 * 3)
                    ////////////////////////////////////
                    {
                        g2.draw(mymap[i]);
                        g2.fill(mymap[i]);
                    }
                }
            }
        }

    }

    public void DrawSnake(Graphics2D g2) {

        for (int i = 0; i < snake.getSnakeBody().size(); i++) {

            if (i == 0) {
                if (snakecolor == 0) {
                    g2.setColor(NewColor.snakehead1);
                    g2.fill(snake.getSnakeBody().get(i));
                } else if (snakecolor == 1) {
                    g2.setColor(NewColor.snakehead2);
                    g2.fill(snake.getSnakeBody().get(i));
                } else {
                    g2.setColor(NewColor.snakehead3);
                    g2.fill(snake.getSnakeBody().get(i));
                }
            } else {
                if (snakecolor == 0) {
                    g2.setColor(NewColor.snake1);
                    g2.fill(snake.getSnakeBody().get(i));
                } else if (snakecolor == 1) {
                    g2.setColor(NewColor.snake2);
                    g2.fill(snake.getSnakeBody().get(i));
                } else {
                    g2.setColor(NewColor.snake3);
                    g2.fill(snake.getSnakeBody().get(i));
                }
                g2.draw(snake.getSnakeBody().get(i));
            }

        }
    }

    public void DrawSnakeFood(Graphics2D g2) {
        g2.setColor(NewColor.food);
        g2.fill(snakeFood.getCoordinate());
    }

    public void DrawSnakeItem(Graphics2D g2) {
        g2.setColor(NewColor.shrinker);
        if (mode == 0) {
            g2.fill(shrinker.getCoordinate());
        } else {
            if (Math.abs(snake.getSnakeBody().get(0).x - shrinker.getCoordinate().x) <= 16 * 5 && Math.abs(snake.getSnakeBody().get(0).y - shrinker.getCoordinate().y) <= 16 * 5) {
                g2.fill(shrinker.getCoordinate());
            }
        }
    }

    public void DrawSnakeSlower(Graphics2D g2) {
        if (lock > 1) return;
        g2.setColor(NewColor.slower);
        if (mode == 0) {
            g2.fill(slower.getCoordinate());
        } else {
            if (Math.abs(snake.getSnakeBody().get(0).x - slower.getCoordinate().x) <= 16 * 5 && Math.abs(snake.getSnakeBody().get(0).y - slower.getCoordinate().y) <= 16 * 5) {
                g2.fill(slower.getCoordinate());
            }
        }
    }

    public void DrawStatusbar(Graphics2D g2) {
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Algerian", Font.BOLD, 35));
        g2.drawString("Snake2D Game Remake", 320, 50);
        g2.setColor(Color.ORANGE);
        g2.drawString("404", 450, 100);

        g2.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        g2.setColor(Color.WHITE);
        g2.drawString("Press Esc for exit!", 5, 20);
        g2.drawString("Press Spacebar for pause!", 5, 50);

        g2.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        g2.drawString("Time: ", 210, 100);
        g2.drawString("Your Score: ", 680, 100);
        g2.setColor(Color.BLUE);
        g2.drawString("" + playerScore, 810, 100);
        g2.drawString("" + timer, 270, 100);

        if (isGameOver()) {
            g2.setColor(Color.WHITE);
            g2.drawString("Game Over! Press SpaceBar to replay", 380, 350);

        } else if (!isGameRunning()) {
            g2.setColor(Color.WHITE);
            g2.drawString("Press SpaceBar to Start Game!", 400, 500);
        }

    }

    public void changeSnakeDirection(int direction) {
        this.snake.setDirection(direction);
    }

    public void checkCollision() throws InterruptedException {

        if (isSelfCollisioned() || isBoundryCollisioned() || isMapCollisioned()) {
            isGameOver = true;
            ///
            collisionsound = new SoundManger(collideFilePath);
            collisionsound.start();
            collisionsound.join();
        	//////////
            stopGame();
        }

        if (isItemCollisioned(snakeFood)) {
        	///
        	eatingfoodsound = new SoundManger(eatfoodFilePath);
        	eatingfoodsound.start();
        	eatingfoodsound.join();
        	//////////
            snake.eat();
            snakeFood = new AbstractItem(mapp, AbstractItem.sFood);
            playerScore += 5;
        }

        if (isItemCollisioned(shrinker)) {
            if (snake.getLength() == 1) {
                isGameOver = true;
                stopGame();
                ///
                shrinkitemsound2 = new SoundManger(shrinkerFilePath2);
                shrinkitemsound2.start();
                shrinkitemsound2.join();
            	//////////
                return;
            }
            snake.eatShrinker();
            shrinker = new AbstractItem(mapp, AbstractItem.sItem);
            ///
            shrinkitemsound = new SoundManger(shrinkerFilePath);
            shrinkitemsound.start();
            shrinkitemsound.join();
        	//////////
        }

        if (isItemCollisioned(slower)) {
            slowingdown();
            slower.hide();
            ///
            timeitemsound = new SoundManger(timeitemFilePath);
            timeitemsound.start();
            timeitemsound.join();
        	//////////
        }
    }


    private boolean isBoundryCollisioned() {
        if (snake.getDirection() == 1) {
            double centerY = snake.getSnakeBody().get(0)
                    .getMinY();
            return centerY < 127;
        } else if (snake.getDirection() == 2) {
            double centerY = snake.getSnakeBody().get(0)
                    .getMaxY();
            return centerY > 591;
        } else if (snake.getDirection() == 3) {
            double centerX = snake.getSnakeBody().get(0).x;
            return centerX > 819;
        } else if (snake.getDirection() == 4) {
            double centerX = snake.getSnakeBody().get(0)
                    .getMinX();
            return centerX < 227.0;
        }
        return false;
    }

    //////
    private boolean isMapCollisioned() {
        if (mapp == 0) {
            return false;
        }
        for (int i = 0; i < mymap.length; i++) {
            if (snake.getHead().x == mymap[i].x)
                if (snake.getHead().y == mymap[i].y)
                    return true;
        }
        return false;
    }

    ////////////////////////
    private boolean isSelfCollisioned() {
    	/////
    	for (int i = 1; i < snake.getSnakeBody().size(); i++) {
    		if ((snake.getHead().y == snake.getSnakeBody().get(i).y) && (snake.getHead().x == snake.getSnakeBody().get(i).x)) {
    			return true;
    		}
    	}
    	//////////////
        /*if (snake.getDirection() == 1) {
            for (int i = 1; i < snake.getSnakeBody().size(); i++) {
                if ((snake.getSnakeBody().get(0).getMinY() == snake
                        .getSnakeBody().get(i).getMaxY())
                        && (snake.getSnakeBody().get(0)
                        .getCenterX() == snake
                        .getSnakeBody().get(i).getCenterX())) {
                    return true;
                }
            }

        } else if (snake.getDirection() == 2) {
            for (int i = 1; i < snake.getSnakeBody().size(); i++) {
                if ((snake.getSnakeBody().get(0).getMaxY() == snake
                        .getSnakeBody().get(i).getMinY())
                        && (snake.getSnakeBody().get(0)
                        .getCenterX() == snake
                        .getSnakeBody().get(i).getCenterX())) {
                    return true;
                }
            }

        } else if (snake.getDirection() == 3) {
            for (int i = 1; i < snake.getSnakeBody().size(); i++) {
                if ((snake.getSnakeBody().get(0).getMaxX() == snake
                        .getSnakeBody().get(i).getMinX())
                        && (snake.getSnakeBody().get(0)
                        .getCenterY() == snake
                        .getSnakeBody().get(i).getCenterY())) {
                    return true;
                }
            }

        } else if (snake.getDirection() == 4) {
            for (int i = 1; i < snake.getSnakeBody().size(); i++) {
                if ((snake.getSnakeBody().get(0).getMinX() == snake
                        .getSnakeBody().get(i).getMaxX())
                        && (snake.getSnakeBody().get(0)
                        .getCenterY() == snake
                        .getSnakeBody().get(i).getCenterY())) {
                    return true;
                }
            }
        }*/
        return false;

    }

    private boolean isItemCollisioned(AbstractItem x) {

        Ellipse2D.Double head = snake.getHead();

        return ((head.getCenterY() == x.getCoordinate().getCenterY())
                && (head.getCenterX() == x.getCoordinate().getCenterX()));
    }


    public void startGame() throws InterruptedException {
        if (gameThread.isRunning()) {
            gameThread.restart();
            timerThread.restart();


        } else {
            gameThread.start();
            timerThread.start();

        }
    }

    public void slowingdown() throws InterruptedException {
        lock = 10;
    }

    public void pauseGame() {
        stopGame();
        repaint();
    }

    public void stopGame() {
    	///
    	
    	//////
        gameThread.stop();
        timerThread.stop();
    }

    public void replay() {
        if (isGameOver()) {
            timer = 0;
            playerScore = 0;
            isGameOver = false;
            lock = 0;
            snake = new Snake();
            snakeFood = new AbstractItem(mapp, snakeFood.sFood);
            shrinker = new AbstractItem(mapp, snakeFood.sItem);
            slower = new AbstractItem(mapp, snakeFood.sTime);
            soundManger = new SoundManger(soundFilePath);
            soundManger.start();
            try {
				soundManger.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            repaint();
        }
    }

    public boolean isGameRunning() {
        return gameThread.isRunning() && !isGameOver();
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        if (lock > 0) {
            synchronized (this) {
                try {
                    wait(300);
                    lock--;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        if (lock == 1) {
            slower = new AbstractItem(mapp, snakeFood.sTime);
        }
        repaint();
    }//每隔delay毫秒被调用一次

}

