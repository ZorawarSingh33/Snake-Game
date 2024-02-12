package SnakeGame;

import javax.swing.JFrame;

public class Main extends JFrame{

    Main(){
        super("Snake Game");
        add(new Board());
       pack();
        setSize(300,300);
         setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }



    public static void main(String[] args) {
        new Main();

    }

}