import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import javax.swing.*;

public class OnlineTest extends JFrame implements ActionListener {

    JLabel l;
    JRadioButton[] jb = new JRadioButton[5]; //The 5th button is an invisible button that will make it so that the selected option resets for each question
    JButton b1, b2;
    ButtonGroup bg;

    int count = 0, current = 0, x = 1, y = 1, now = 0;
    int[] m = new int[10];

    OnlineTest(String s) {
        super(s); //Sets the title
        l = new JLabel(); //This will be used to display questions.
        add(l);
        bg = new ButtonGroup();

        for(int i=0;i<5;i++) {
            jb[i] = new JRadioButton();
            add(jb[i]);
            bg.add(jb[i]); //So that only 1 option can be selected
        }

        b1 = new JButton("Next");
        b2 = new JButton("Bookmark");
        b1.addActionListener(this);
        b2.addActionListener(this);
        add(b1);
        add(b2);
        set();
        l.setBounds(30, 40, 450, 20);
        jb[0].setBounds(50, 80, 150, 20);
        jb[1].setBounds(50, 110, 150, 20);
        jb[2].setBounds(50, 140, 150, 20);
        jb[3].setBounds(50, 170, 150, 20);
        b1.setBounds(100, 240, 100, 30);
        b2.setBounds(270, 240, 100, 30);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setLocation(250, 100); //The location where window will open on your screen
        setVisible(true);
        setSize(600, 350);
    }

    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == b1) { //If Next is pressed
            if(check()) {
                count++;
                //System.out.println(count);
            }
            now++;
            if(current<9) {
                //current++;
                current = now;
            }
            //System.out.println("Curr  : "+current);
            set();
            if(current == 9) {
                b1.setEnabled(false);
                b2.setText("Result");
            }
        }
        if(ae.getActionCommand().equals("Bookmark")) { //Using this instead of getSource command because we're changing the button for last question to Result
            JButton bk = new JButton("Bookmark "+x);
            bk.setBounds(480, 20+30*x, 100, 30);
            add(bk);
            bk.addActionListener(this);
            now++;
            //now = current;
            m[x] = current;
            x++;
            current++;
            set();
            if(current == 9) {
                b2.setText("Result");
                b1.setEnabled(false);
            }
            setVisible(false);
            setVisible(true); //These 2 commands basically close and reopen the window with the newly added bookmark buttons
        }

        for(int i=0,y=1;i<x;i++,y++) {
            if(ae.getActionCommand().equals("Bookmark "+y)) {

                //System.out.println("Curr before: "+current);
                if(check()) {
                    count++;
                    //System.out.println(count);
                }

                now = current;
                current = m[y];
                //System.out.println("Now: "+now+" Current: "+current+" m: "+ Arrays.toString(m));
                //current++;
                //System.out.println("Curr after: "+current);
                set();
                ((JButton)ae.getSource()).setEnabled(false);
                //current = now;
                //System.out.println("Curr now: "+current);
                //System.out.println("Now: "+now);
            }
        }

        if(ae.getActionCommand().equals("Result")) {
            if(check()) {
                count++;
                //System.out.println(count+" "+ current);
            }
            current++;
            //System.out.println("Curr: "+current);
            JOptionPane.showMessageDialog(this, "Correct answer: "+count);
            System.exit(0);
        }
    }

    //Questionnaire
    void set() {
        jb[4].setSelected(true);

        if(current == 0) {
            l.setText("Q.1: What is the name of the creator of this project?");
            jb[0].setText("Rohit Sharma");
            jb[1].setText("Aryan Jolly");
            jb[2].setText("Akshay Kumar");
            jb[3].setText("Kendrick Lamar");
        }

        if(current == 1) {
            l.setText("Q.2: When is the creator's birthday?");
            jb[0].setText("Sep 7");
            jb[1].setText("July 9");
            jb[2].setText("Sep 9");
            jb[3].setText("July 7");
        }

        if(current == 2) {
            l.setText("Q.3: What is his favourite dish?");
            jb[0].setText("Dal Makhani");
            jb[1].setText("Butter Chicken");
            jb[2].setText("Mutton Rogan Josh");
            jb[3].setText("Rajma Chawal");
        }

        if(current == 3) {
            l.setText("Q.4: What is his favourite snack?");
            jb[0].setText("Kebab");
            jb[1].setText("Cocktail Salami");
            jb[2].setText("Red Tikka");
            jb[3].setText("Momos");
        }

        if(current == 4) {
            l.setText("Q.5: Which language does he not know?");
            jb[0].setText("Python");
            jb[1].setText("C++");
            jb[2].setText("Java");
            jb[3].setText("None of the above");
        }

        if(current == 5) {
            l.setText("Q.6: Which season is his favourite?");
            jb[0].setText("Spring");
            jb[1].setText("Summer");
            jb[2].setText("Autumn");
            jb[3].setText("Winter");
        }

        if(current == 6) {
            l.setText("Q.7: Which is his favourite shooter game?");
            jb[0].setText("PUBG");
            jb[1].setText("Call of Duty: Mobile");
            jb[2].setText("Counter Strike 1.6");
            jb[3].setText("Valorant");
        }

        if(current == 7) {
            l.setText("Q.8: What is the name of his dog?");
            jb[0].setText("Snowy");
            jb[1].setText("Flaaffy");
            jb[2].setText("Luffy");
            jb[3].setText("He doesn't have a dog.");
        }

        if(current == 8) {
            l.setText("Q.9: Which sports does he play?");
            jb[0].setText("Basketball");
            jb[1].setText("Cricket");
            jb[2].setText("Badminton");
            jb[3].setText("Football");
        }

        if(current == 9) {
            l.setText("Q.10: What was his first purchase?");
            jb[0].setText("Sneakers");
            jb[1].setText("Headphones");
            jb[2].setText("Cocktail Salami");
            jb[3].setText("Movie Tickets");
        }

        l.setBounds(30, 40, 450, 20);

        for(int i=0,j=0;i<=90;i+=30,j++) {
            jb[j].setBounds(50, 80+i, 200, 20);
        }
    }

    //Answer sheet
    boolean check() {
        if(current == 0) {
            //System.out.println("Selected: "+bg.getSelection());
            return(jb[1].isSelected());
        }

        if(current == 1) {
            return(jb[0].isSelected());
        }

        if(current == 2) {
            return(jb[3].isSelected());
        }

        if(current == 3) {
            return(jb[0].isSelected());
        }

        if(current == 4) {
            return(jb[3].isSelected());
        }

        if(current == 5) {
            return(jb[3].isSelected());
        }

        if(current == 6) {
            return(jb[1].isSelected());
        }

        if(current == 7) {
            return(jb[3].isSelected());
        }

        if(current == 8) {
            return(jb[3].isSelected());
        }

        if(current == 9) {
            return(jb[2].isSelected());
        }

        //System.out.println("False"+current);
        return false;
    }
}