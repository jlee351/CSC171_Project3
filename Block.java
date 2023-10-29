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

public class Block extends JPanel{
  int x;
  int y;
  int speed;
  boolean right;
  boolean left;
  boolean hit;

  public Block(int x, int y){
    this.x = x;
    this.y = y;
    this.speed= 5;
    this.right = true;
    this.left = false;
  }

  public void hit(){
    hit = true;
  }
}
