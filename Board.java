package SnakeGame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Time;

public class Board extends JPanel implements ActionListener {
    private int dots;
    private  Image apple;
    private  Image dot;
    private  Image head;
    private final int ALL_DOTS=900;
    private final int Dot_Size=10;
    private  final int Random_Position=9;
    private int apple_x;
    private int apple_y;
private  Timer timer;
private boolean ingame=true;

    private  final int x[]=new int[ALL_DOTS];
    private  final int y[]=new int[ALL_DOTS];
  private boolean leftDirection=false;
    private boolean rightDirection=true;
    private boolean upDirection=false;
    private boolean downDirection=false;




    Board(){
        addKeyListener(new TAdpater());
   setBackground(Color.darkGray);
   setPreferredSize(new Dimension(300,300));
   setFocusable(true);

     intitGame();
     loadImages();

    }
    public  void loadImages(){
     ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("SnakeGame/icons/apple.png"));
        ImageIcon i2=new ImageIcon(ClassLoader.getSystemResource("SnakeGame/icons/dot.png"));
        ImageIcon i3=new ImageIcon(ClassLoader.getSystemResource("SnakeGame/icons/head.png"));

       apple=i1.getImage();
       dot=i2.getImage();
       head=i3.getImage();
    }
    public  void intitGame(){
        dots=3;
        for(int i=0;i<dots;i++){
            y[i]=50;
            x[i]=50-i*Dot_Size;

        }
        locateApple();
        Timer timer=new Timer(140,this);
        timer.start();

    }
    public void locateApple(){
        int r=(int)(Math.random()*Random_Position);
        apple_x=r*Dot_Size;
        r=(int)(Math.random()*Random_Position);
        apple_y=r*Dot_Size;

    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);

    }
    public void draw(Graphics g){
        if(ingame) {
            g.drawImage(apple, apple_x, apple_y, this);
            for (int i = 0; i < dots; i++) {
                if (i == 0) {
                    g.drawImage(head, x[i], y[i], this);
                } else {
                    g.drawImage(dot, x[i], y[i], this);
                }
            }

            Toolkit.getDefaultToolkit().sync();
        }
        else {
            gameover(g);
        }
        }
        public void  gameover(Graphics g){
        String msg="Game Over!";
        Font font=new Font("SAN_SERIF",Font.BOLD,15);
        FontMetrics metrics=getFontMetrics(font);
        g.setFont(font);
        setBackground(Color.white);
        g.drawString(msg,(300-metrics.stringWidth(msg))/2,300/2);
        }


    public void move() {
        for (int i = dots; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }
        if (leftDirection) {
            x[0] -= Dot_Size;
        }
        if (rightDirection) {
            x[0] += Dot_Size;
        }
        if (upDirection) {
            y[0] -= Dot_Size;
        }
        if (downDirection) {
            y[0] += Dot_Size;
        }
    }

    public void actionPerformed(ActionEvent e) {
      move();
      repaint();
      checkApple();
      checkCollision();

    }
    public void checkApple(){
        if((x[0]==apple_x && y[0]==apple_y)){
            dots++;
          locateApple();
        }

    }
    public void checkCollision(){
          for(int z=dots;z>0;z--){
              if((z>4)&& (x[0]==x[z]) && (y[0]==y[z])){
                  ingame=false;
              }
          }
          if(y[0]>=300){
              ingame=false;
          }
          if(x[0]>=300){
              ingame=false;
          }
          if(y[0]<0){
              ingame=false;
          }
          if(x[0]<=0){
              ingame=false;
          }
          if(!ingame){
              timer.stop();
          }
    }
    public  class  TAdpater extends KeyAdapter{

        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            if (key == KeyEvent.VK_LEFT && !rightDirection) {
                leftDirection = true;
                downDirection = false;
                upDirection = false;
            }
            if (key == KeyEvent.VK_RIGHT && !leftDirection) {
                rightDirection = true;
                downDirection = false;
                upDirection = false;
            }
            if (key == KeyEvent.VK_UP && !downDirection) {
                upDirection = true;
                rightDirection = false;
                leftDirection = false;
            }
            if (key == KeyEvent.VK_DOWN && !upDirection) {
                downDirection = true;
                rightDirection = false;
                leftDirection = false;
            }
        }
    }
}
