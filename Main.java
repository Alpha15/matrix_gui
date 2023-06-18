import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.io.*;
import java.net.*;

public class Main extends JFrame {

    int x_len = 20;
    int y_len = 20;
    JLabel[] lbarry = new JLabel[x_len * y_len];
    Thread_lb[] tl = new Thread_lb[x_len * y_len];
    Thread[] th = new Thread[x_len * y_len];

    public static void main(String[] args) {
        Main frame = new Main();

        frame.setBounds(10, 10, 400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);

        // char a = 'a';
        // char z = 'z';
        // for (char now = a; now != z + 1; now++) {
        // System.out.println((char) now + ":" + (int) now);
        // }
        // System.out.println("a-z:" + ((int) 'z' - (int) 'a'));
        // System.out.printf("%c:%d", 0x0061, 0x0061);
    }

    private int color;
    private char text_of_lb;
    // static JLabel lb;

    Random rnd;

    public Main() {

        rnd = new Random();
        // text_of_lb = (char) (rnd.nextInt(25) + 97);
        color = 0x00FF00;
        GridLayout layout = new GridLayout(y_len, x_len);
        layout.setVgap(1);
        layout.setHgap(1);
        JPanel p = new JPanel();
        p.setBackground(Color.BLACK);
        p.setLayout(layout);

        for (int i = 0; i < x_len * y_len; i++) {
            text_of_lb = (char) (rnd.nextInt(25) + 97);
            lbarry[i] = new JLabel(String.valueOf(text_of_lb));
            lbarry[i].setPreferredSize(new Dimension(1, 1));
            lbarry[i].setHorizontalAlignment(JLabel.CENTER);
            lbarry[i].setForeground(new Color(color));

            p.add(lbarry[i]);
        }

        for (int i = 0; i < x_len * y_len; i++) {
            tl[i] = new Thread_lb(lbarry[i]);
            th[i] = new Thread(tl[i]);
            th[i].start();
        }

        Container contentPane = getContentPane();
        contentPane.add(p, BorderLayout.CENTER);

        // new Thread(this).start();
    }

    // public void run() {
    // while (true) {
    // color -= 0x000500;
    // if (color == 0x000000) {
    // text_of_lb = (char) (rnd.nextInt(25) + 97);
    // color = 0x00FF00;
    // }
    // // System.out.println("color:" + color);
    // SwingUtilities.invokeLater(new Runnable() {
    // public void run() {
    // // lb.setText(String.valueOf(text_of_lb));
    // // lb.setForeground(new Color(color));
    // }
    // });
    // try {
    // Thread.sleep(100);
    // } catch (Exception e) {
    // }
    // }
    // }
}

class Thread_lb implements Runnable {
    JLabel lb;
    int color;
    char text_of_lb;
    Random rnd;

    public Thread_lb(JLabel lb) {
        this.lb = lb;
        color = 0x00FF00;
        rnd = new Random();
    }

    public void run() {
        while (true) {
            // color -= 0x000500;
            color -= 0x001000;
            // if (color == 0x000000) {
            if (color <= 0x000000) {
                text_of_lb = (char) (rnd.nextInt(25) + 97);
                lb.setText(String.valueOf(text_of_lb));
                color = 0x00FF00;
            }
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    // lb.setText(String.valueOf(text_of_lb));
                    lb.setForeground(new Color(color));
                }
            });
            try {
                Thread.sleep(rnd.nextInt(127) + 1);
            } catch (Exception e) {
            }
        }
    }
}
