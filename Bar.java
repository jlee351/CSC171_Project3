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

public class Bar extends JPanel implements KeyListener, ActionListener{

  int xpos;

  public Bar(){
    this.xpos = 225;
  }

  public int getBar(){
    return xpos;
  }

  public void setBar(int x){
    xpos = x;
  }

  public void paintComponent(Graphics g){
    g.setColor(Color.YELLOW);
    g.fillRect(getBar(),470,50,5);

  }

  @Override
  public void keyTyped(KeyEvent e) {}

  @Override
  public void keyReleased(KeyEvent e){}

  @Override
  public void keyPressed(KeyEvent e){}

  @Override
  public void actionPerformed(ActionEvent e){}

}
