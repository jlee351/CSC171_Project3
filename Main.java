import javax.swing.JFrame;

public class Main{

  public static void main(String[] args) {

    JFrame frame = new JFrame();
    frame.setBounds(0,0,500,800);
    frame.setTitle("Brick Game");
    frame.setResizable(false);

    Game game = new Game();
    Bar bar = new Bar();
    game.add(bar);
    frame.add(game);

    frame.setVisible(true);

  }
}
