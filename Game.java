import java.awt.Font;
import java.awt.Color;
import javax.swing.Timer;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Rectangle;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Game extends JPanel implements KeyListener, ActionListener{

  int score = 0;
  boolean playgame = false;
  int barpos = 225;
  Timer timer;
  Block[] blocks = new Block[18];
  int blockx;
  int blocky;
  int ballx = 250;
  int bally = 400;
  int xspeed = -1;
  int yspeed = -2;
  int life = 2;

  public Game(){
    timer = new Timer(8, this);
    timer.start();
    addKeyListener(this);
    setFocusable(true);
    setFocusTraversalKeysEnabled(false);

    blockx = 10;
    blocky = 10;

    for(int i=0; i < 18; i++){
      blocks[i] = new Block(blockx,blocky);
      blockx+=37;
      if(i==5 || i==11){
        blockx = 10;
        blocky += 37;
      }
    }
  }

  public void paintComponent(Graphics g){
    super.paintComponent(g);
    g.setColor(Color.BLACK);
    g.fillRect(0,0,500,800);

    //lines around window
    g.setColor(Color.BLUE);
    g.fillRect(0, 0, 3, 792);
    g.fillRect(0, 0, 497, 3);
    g.fillRect(497, 0, 3, 792);

    //Bar
    g.setColor(Color.YELLOW);
    g.fillRect(barpos,720,50,5);

    //Blocks
    for(int i = 0; i < blocks.length; i++){
      if(!blocks[i].hit){
        g.setColor(Color.RED);
      }
      else{
        g.setColor(Color.BLACK);
        g.fillRect(blocks[i].x, blocks[i].y,30,30);
      }
    }

    for(int i=0; i < blocks.length; i++){
      g.fillRect(blocks[i].x,blocks[i].y,30,30);
    }

    if(playgame){
    moveBlocks();
    }

    //ball
    g.setColor(Color.GREEN);
    g.fillOval(ballx, bally, 20, 20);

    //extra lives (also balls)
    for(int i = 0; i < life; i++){
      g.fillOval(440+i*30,750,20,20);
    }

    //score
    g.setFont(new Font("Times New Roman", Font.BOLD, 20));
    g.drawString("Score: " + score, 10,770);

    //if ball falls below bar
    if (bally > 730){

      life -= 1;
      playgame = false;
      ballx = 250;
      bally = 400;
      xspeed = -1;
      yspeed = -2;

      if(life < 0){
        xspeed = 0;
        yspeed = 0;
        playgame = false;

      }
      timer.start();
    }

    if(life < 0){
      g.setColor(Color.WHITE);
      g.setFont(new Font("Times New Roman", Font.BOLD, 30));
      g.drawString("Game Over! Your score: " + score, 150,400);
      g.drawString("Press Enter to play again.", 150, 500);
    }
    g.dispose();
  }

  public void moveBlocks(){
    for(int i = 0; i < blocks.length; i++){
      if(blocks[i].right == true){
        blocks[i].x += blocks[i].speed;
      }
      if(blocks[i].left == true){
        blocks[i].x -= blocks[i].speed;
      }
    }

    for(int i = 0; i < blocks.length; i++){
      if(blocks[i].x > 450){
        for(int j = 0; j < blocks.length; j++){
          blocks[j].left = true;
          blocks[j].right = false;
        }
      }

      if(blocks[i].x < 3){
        for(int j = 0; j < blocks.length; j++){
          blocks[j].left = false;
          blocks[j].right = true;
        }
      }

    }
  }

  @Override
  public void keyTyped(KeyEvent e) {}

  @Override
  public void keyReleased(KeyEvent e){}

  @Override
  public void keyPressed(KeyEvent e){
    if(e.getKeyCode() == KeyEvent.VK_RIGHT){
      if (barpos >= 470){
        barpos = 470;
      }
      else{
        playgame = true;
        barpos = barpos + 30;
        if(life < 0){
          playgame = false;
        }
      }
    }
    if(e.getKeyCode() == KeyEvent.VK_LEFT){
      if (barpos <= 0){
        barpos = 0;
      }
      else{
        playgame = true;
        barpos = barpos - 30;
        if(life < 0){
          playgame = false;
        }
      }
    }
  }

  @Override
  public void actionPerformed(ActionEvent e){
    timer.start();

    if(playgame){

      for(int i = 0; i < blocks.length; i++){

        Rectangle test = new Rectangle(blocks[i].x, blocks[i].y,30,30);

          if(new Rectangle(ballx, bally, 20, 20).intersects(test)){
            blocks[i].hit();
            if(ballx == blocks[i].x || ballx >= blocks[i].x + 30) {
              xspeed = -xspeed;
            }
            else {
              yspeed = -yspeed;
            }
            score+=1;
          }
      }

      //contantly moves ball.
      ballx = ballx + xspeed;
      bally = bally + yspeed;

      if (new Rectangle(ballx, bally, 20, 20).intersects(new Rectangle(barpos,720,50,5))){
        yspeed *= -1;
      }

      if(ballx < 4 || ballx > 480){
        xspeed *= -1;
      }

      if(bally < 4){
        yspeed *= -1;
      }
    }
    repaint();
  }
}
