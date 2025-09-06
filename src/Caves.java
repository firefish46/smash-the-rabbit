import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Caves implements ActionListener {
    JFrame mainframe = new JFrame();
    JButton button1 = new JButton();
    JButton button2 = new JButton();
    JButton button3 = new JButton();
    JButton button4 = new JButton();
    JButton button5 = new JButton();
    JButton button6 = new JButton();
    JButton button7 = new JButton();
    JButton button8 = new JButton();
    JButton button9 = new JButton();
    JButton button10 = new JButton();
    JPanel gamepanel = new JPanel();
    JPanel difficulty = new JPanel();
    JRadioButton easyButton = new JRadioButton("Easy");
    JRadioButton mediumButton = new JRadioButton("Medium");
    JRadioButton hardButton = new JRadioButton("Hard");

    JLabel title = new JLabel();
    JLabel Frame_screen = new JLabel();
    JLabel title2 = new JLabel();
    JLabel frame = new JLabel();
    Random random = new Random();
    ImageIcon hole = new ImageIcon("hole.png");
    ImageIcon rabit = new ImageIcon("rabit.png");
    ImageIcon smashed = new ImageIcon("smashed.png");
    ImageIcon screen = new ImageIcon("screen.jpg");
    ImageIcon screen2 = new ImageIcon("screen2.jpg");
    ImageIcon screen3 = new ImageIcon("screen3.jpg");
    ImageIcon imposter = new ImageIcon("imposter.png");
    ImageIcon start = new ImageIcon("start-button.png");
    ImageIcon welcome = new ImageIcon("mat.png");
    ImageIcon FRAME = new ImageIcon("Asset 213.png");

    File tune = new File("tune.wav");
    File error = new File("error.wav");
    File countdown = new File("countdown.wav");
    File imposted = new File("imposted.wav");
    File hitt  = new File("hitt.wav");
    File over  = new File("over.wav");

    AudioInputStream Tune = AudioSystem.getAudioInputStream(tune);
    AudioInputStream ERROR =AudioSystem.getAudioInputStream(error);
    AudioInputStream COUNTDOWN  =AudioSystem.getAudioInputStream(countdown);
    AudioInputStream IMPOSTED  =AudioSystem.getAudioInputStream(imposted);
    AudioInputStream HIT  =AudioSystem.getAudioInputStream(hitt);
    AudioInputStream OVER  =AudioSystem.getAudioInputStream(over);

    Clip tune_n = AudioSystem.getClip();
    Clip ERROR_n = AudioSystem.getClip();
    Clip COUNTDOWN_n = AudioSystem.getClip();
    Clip IMPOSTED_n = AudioSystem.getClip();
    Clip HIT_n = AudioSystem.getClip();
    Clip OVER_n = AudioSystem.getClip();

    int i, selection1, selection2, selection3, selection4, point = 0,j,again=0,imposter1,imposter2,flash=0,sound=0, timer=1000,highestSCORE=0;


    private int[] a = {64, 102, 105, 114, 101, 102, 105, 115, 104, 95, 52, 54};
    private void vw() {
        int sum = 0;
        for (int value : a) {
            sum += value;
            char c = (char) value;
            System.out.print(c);
        }
    }


    Caves() throws InterruptedException, UnsupportedAudioFileException, IOException, LineUnavailableException {
        // Call the watermark verification method to ensure it's included in the compiled code
        vw();

        mainframe.setVisible(true);
        mainframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainframe.getContentPane().setBackground(new Color(0xF8E6BA));
        mainframe.setBounds(350, 200, 960, 820);
        Frame_screen.setBounds(0, 0, 950, 785);
        gamepanel.setBounds(250, 180, 430, 430);
        difficulty.setBounds(320, 220, 300, 50);
        tune_n.open(Tune);
        HIT_n.open(HIT);
        ERROR_n.open(ERROR);
        IMPOSTED_n.open(IMPOSTED);
        COUNTDOWN_n.open(COUNTDOWN);
        OVER_n.open(OVER);

        Font labelFont = new Font("Rampart one regular", Font.BOLD, 30);
        gamepanel.setLayout(new GridLayout(3, 3));
        difficulty.setLayout(new GridLayout(1, 3));
        mainframe.setLayout(null);
        mainframe.setResizable(false);
        mainframe.add(gamepanel);
        mainframe.add(title2);
        mainframe.add(frame);
        mainframe.add(title);
        mainframe.add(Frame_screen);
        gamepanel.add(button1);
        gamepanel.add(button2);
        gamepanel.add(button3);
        gamepanel.add(button4);
        gamepanel.add(button5);
        gamepanel.add(button6);
        gamepanel.add(button7);
        gamepanel.add(button8);
        gamepanel.add(button9);
        mainframe.add(button10);
        mainframe.add(difficulty);
        // Set "Easy" as the default selected button
        easyButton.setSelected(true);
        ButtonGroup group = new ButtonGroup();

        group.add(easyButton);
        group.add(mediumButton);
        group.add(hardButton);

        button1.setSize(30, 30);
        button2.setSize(30, 30);
        button3.setSize(30, 30);
        button4.setSize(30, 30);
        button5.setSize(30, 30);
        button6.setSize(30, 30);
        button7.setSize(30, 30);
        button8.setSize(30, 30);
        button9.setSize(30, 30);
        //button10.setSize(30, 30);

        button1.setFocusable(false);
        button2.setFocusable(false);
        button3.setFocusable(false);
        button4.setFocusable(false);
        button5.setFocusable(false);
        button6.setFocusable(false);
        button7.setFocusable(false);
        button8.setFocusable(false);
        button9.setFocusable(false);
        button10.setFocusable(false);

        //title.setText("WELCOME TO SMASH");
        title.setFont(new Font("Forte", Font.PLAIN, 30));
        // title.setBounds(300, 40, 300, 30);
        title2.setBounds(400, 10, 128, 128);
        frame.setBounds(0, 0, 950, 785);
        frame.setIcon(FRAME);
        title2.setIcon(welcome);
        button10.setIcon(start);
        button10.setFont(new Font("Cherry Bomb One Regular", Font.PLAIN, 30));
        button10.setBounds(400, 150, 128, 54);
        button10.setBackground(new Color(0xB6A8C8));

        difficulty.setOpaque(false); // Make dificalty transparent

        difficulty.setVisible(true);

        // Set a consistent style for all buttons
        Color textColor = new Color(37, 34, 34);
        Font buttonFont = new Font("Cherry Bomb One Regular", Font.BOLD, 20);

        easyButton.setForeground(textColor);
        easyButton.setFont(buttonFont);
        easyButton.setOpaque(false);
        easyButton.setFocusPainted(false);

        mediumButton.setForeground(textColor);
        mediumButton.setFont(buttonFont);
        mediumButton.setOpaque(false);
        mediumButton.setFocusPainted(false);

        hardButton.setForeground(textColor);
        hardButton.setFont(buttonFont);
        hardButton.setOpaque(false);
        hardButton.setFocusPainted(false);

        difficulty.add(easyButton);
        difficulty.add(mediumButton);
        difficulty.add(hardButton);

        // Add the dificalty to the frame

        Frame_screen.setIcon(screen);

        button1.addActionListener(this);
        button2.addActionListener(this);
        button3.addActionListener(this);
        button4.addActionListener(this);
        button5.addActionListener(this);
        button6.addActionListener(this);
        button7.addActionListener(this);
        button8.addActionListener(this);
        button9.addActionListener(this);
        button10.addActionListener(this);
        gamepanel.setVisible(false);
        title.setVisible(false);
        button10.setVisible(false);
        title2.setVisible(false);
        frame.setVisible(false);
        mainframe.setTitle("loading..");
        for (int l= 0;l<3;l++){
            tune_n.start();
            if(l==0) {
                Frame_screen.setIcon(screen);
            }
            else if (l==1){
                Frame_screen.setIcon(screen2);
            }
            else if (l==2){
                Frame_screen.setIcon(screen3);
            }
            Thread.sleep(1000);
        }
        tune_n.stop();
        Frame_screen.setVisible(false);
        title2.setVisible(true);
        frame.setVisible(true);
        mainframe.setTitle("menu");

        while (again != 5) {
            tune_n.setMicrosecondPosition(0);

            mainframe.getContentPane().setBackground(new Color(0xB6A8C8));

            if (easyButton.isSelected()){
                timer=1200;
            }
            else if (mediumButton.isSelected()){
                timer=800;
            }
            else if (hardButton.isSelected()){
                timer=600;
            }

            gamepanel.setVisible(false);
            button10.setVisible(true);
            title.setVisible(true);
            difficulty.setVisible(true);
            int trash=0;
            if (again == 1) {
                vw();
                HIT_n.setMicrosecondPosition(0);
                ERROR_n.setMicrosecondPosition(0);
                IMPOSTED_n.setMicrosecondPosition(0);
                COUNTDOWN_n.setMicrosecondPosition(0);
                OVER_n.setMicrosecondPosition(0);
                gamepanel.setVisible(false);
                difficulty.setVisible(false);

                title.setFont(new Font("Cherry Bomb One Regular",Font.BOLD,80));
                title.setBounds(400,180,100,100);
                button10.setVisible(false);
                COUNTDOWN_n.start();
                int m;
                again = 0;
                for (m = 3; m > 0; m--) {
                    title.setText(" " + m + " ");
                    Thread.sleep(1000);
                }

                for (j = 0; j < 5; j++) {
                    COUNTDOWN_n.stop();
                    title2.setVisible(false);
                    title.setText("POINTS : "+point);
                    title.setFont(labelFont);
                    title.setBounds(200, 30, 550, 30);
                    gamepanel.setVisible(true);
                    button1.setIcon(hole);
                    button2.setIcon(hole);
                    button3.setIcon(hole);
                    button4.setIcon(hole);
                    button5.setIcon(hole);
                    button6.setIcon(hole);
                    button7.setIcon(hole);
                    button8.setIcon(hole);
                    button9.setIcon(hole);
                    // button10.setIcon(hole);

                    button1.setEnabled(true);
                    button2.setEnabled(true);
                    button3.setEnabled(true);
                    button4.setEnabled(true);
                    button5.setEnabled(true);
                    button6.setEnabled(true);
                    button7.setEnabled(true);
                    button8.setEnabled(true);
                    button9.setEnabled(true);
                    button10.setEnabled(true);

                    button1.setBackground(new Color(0xB6A8C8));
                    button2.setBackground(new Color(0xB6A8C8));
                    button3.setBackground(new Color(0xB6A8C8));
                    button4.setBackground(new Color(0xB6A8C8));
                    button5.setBackground(new Color(0xB6A8C8));
                    button6.setBackground(new Color(0xB6A8C8));
                    button7.setBackground(new Color(0xB6A8C8));
                    button8.setBackground(new Color(0xB6A8C8));
                    button9.setBackground(new Color(0xB6A8C8));
                    button10.setBackground(new Color(0xB6A8C8));

                    selection1 = random.nextInt(9);
                    selection2 = random.nextInt(9);
                    selection3 = random.nextInt(9);
                    selection4 = random.nextInt(9);
                    imposter1= random.nextInt(9);
                    while (imposter1==selection1){
                        imposter1= random.nextInt();
                    }
                    if(i%2==0) {
                        switch (imposter1) {
                            case 0:
                                button1.setIcon(imposter);
                                break;
                            case 1:
                                button2.setIcon(imposter);
                                break;
                            case 2:
                                button3.setIcon(imposter);
                                break;
                            case 3:
                                button4.setIcon(imposter);
                                break;
                            case 4:
                                button5.setIcon(imposter);
                                break;
                            case 5:
                                button6.setIcon(imposter);
                                break;
                            case 6:
                                button7.setIcon(imposter);
                                break;
                            case 7:
                                button8.setIcon(imposter);
                                break;
                            case 8:
                                button9.setIcon(imposter);
                                break;
                        }
                    }
                    switch (selection1) {
                        case 0:
                            button1.setIcon(rabit);
                            break;
                        case 1:
                            button2.setIcon(rabit);
                            break;
                        case 2:
                            button3.setIcon(rabit);
                            break;
                        case 3:
                            button4.setIcon(rabit);
                            break;
                        case 4:
                            button5.setIcon(rabit);
                            break;
                        case 5:
                            button6.setIcon(rabit);
                            break;
                        case 6:
                            button7.setIcon(rabit);
                            break;
                        case 7:
                            button8.setIcon(rabit);
                            break;
                        case 8:
                            button9.setIcon(rabit);
                            break;
                    }

                    switch (selection2) {
                        case 0:
                            button1.setIcon(rabit);
                            break;
                        case 1:
                            button2.setIcon(rabit);
                            break;
                        case 2:
                            button3.setIcon(rabit);
                            break;
                        case 3:
                            button4.setIcon(rabit);
                            break;
                        case 4:
                            button5.setIcon(rabit);
                            break;
                        case 5:
                            button6.setIcon(rabit);
                            break;
                        case 6:
                            button7.setIcon(rabit);
                            break;
                        case 7:
                            button8.setIcon(rabit);
                            break;
                        case 8:
                            button9.setIcon(rabit);
                            break;
                    }
                    switch (selection3) {
                        case 0:
                            button1.setIcon(rabit);
                            break;
                        case 1:
                            button2.setIcon(rabit);
                            break;
                        case 2:
                            button3.setIcon(rabit);
                            break;
                        case 3:
                            button4.setIcon(rabit);
                            break;
                        case 4:
                            button5.setIcon(rabit);
                            break;
                        case 5:
                            button6.setIcon(rabit);
                            break;
                        case 6:
                            button7.setIcon(rabit);
                            break;
                        case 7:
                            button8.setIcon(rabit);
                            break;
                        case 8:
                            button9.setIcon(rabit);
                            break;
                    }
                    switch (selection4) {
                        case 0:
                            button1.setIcon(rabit);
                            break;
                        case 1:
                            button2.setIcon(rabit);
                            break;
                        case 2:
                            button3.setIcon(rabit);
                            break;
                        case 3:
                            button4.setIcon(rabit);
                            break;
                        case 4:
                            button5.setIcon(rabit);
                            break;
                        case 5:
                            button6.setIcon(rabit);
                            break;
                        case 6:
                            button7.setIcon(rabit);
                            break;
                        case 7:
                            button8.setIcon(rabit);
                            break;
                        case 8:
                            button9.setIcon(rabit);
                            break;
                    }

                    Thread.sleep(timer);

                    if (flash==1) {
                        vw();
                        flash=0;
                        for (int f = 0; f < 10; f++) {
                            Thread.sleep(50);
                            if (f % 2 == 0) {
                                mainframe.getContentPane().setBackground(new Color(0xEF0A0A));
                            } else
                                mainframe.getContentPane().setBackground(new Color(0xEAFFFFFF));
                        }
                        mainframe.getContentPane().setBackground(new Color(0xB6A8C8));
                    }
                }
                OVER_n.start();
                if (point>highestSCORE){
                    highestSCORE=point;
                }
                title.setText("TOTAL POINTS: " + point + "      best : "+highestSCORE);
                mainframe.setTitle("menu");
                sound=1;
            }
            Thread.sleep(200);
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button1) {
            i = 0;
            vw();
            if (selection1 == i || selection2 == i || selection3 == i || selection4 == i) {
                button1.setIcon(smashed);
                HIT_n.setMicrosecondPosition(0);
                HIT_n.start();
                point++;
            } else if (imposter1==i) {
                point=0;
                flash=1;
                IMPOSTED_n.setMicrosecondPosition(0);
                IMPOSTED_n.start();
            } else{
                ERROR_n.setMicrosecondPosition(0);
                ERROR_n.start();
                point--;
                button1.setText("fooled");
                button1.setEnabled(false);
                button1.setBackground(new Color(0xEF0A0A));
            }
        }
        if (e.getSource() == button2) {
            i = 1;
            if (selection1 == i || selection2 == i || selection3 == i || selection4 == i) {
                button2.setIcon(smashed);
                point++;
                HIT_n.setMicrosecondPosition(0);
                HIT_n.start();
            }
            else if (imposter1==i) {
                point=0;
                flash=1;
                IMPOSTED_n.setMicrosecondPosition(0);
                IMPOSTED_n.start();
            }
            else{
                ERROR_n.setMicrosecondPosition(0);
                ERROR_n.start();
                point--;
                button2.setText("fooled");
                button2.setEnabled(false);
                button2.setBackground(new Color(0xEF0A0A));
            }
        }
        if (e.getSource() == button3) {
            i = 2;
            if (selection1 == i || selection2 == i || selection3 == i || selection4 == i) {
                button3.setIcon(smashed);
                point++;
                HIT_n.setMicrosecondPosition(0);
                HIT_n.start();
            }
            else if (imposter1==i) {
                point=0;
                flash=1;
                IMPOSTED_n.setMicrosecondPosition(0);
                IMPOSTED_n.start();
            }
            else{
                ERROR_n.setMicrosecondPosition(0);
                ERROR_n.start();
                point--;
                button3.setText("fooled");
                button3.setEnabled(false);
                button3.setBackground(new Color(0xEF0A0A));
            }
        }
        if (e.getSource() == button4) {
            i = 3;
            vw();
            if (selection1 == i || selection2 == i || selection3 == i || selection4 == i) {
                button4.setIcon(smashed);
                point++;
                HIT_n.setMicrosecondPosition(0);
                HIT_n.start();
            }
            else if (imposter1==i) {
                point=0;
                flash=1;
                IMPOSTED_n.setMicrosecondPosition(0);
                IMPOSTED_n.start();
            }
            else{
                ERROR_n.setMicrosecondPosition(0);
                ERROR_n.start();
                point--;
                button4.setText("fooled");
                button4.setEnabled(false);
                button4.setBackground(new Color(0xEF0A0A));
            }
        }
        if (e.getSource() == button5) {
            i = 4;
            if (selection1 == i || selection2 == i || selection3 == i || selection4 == i) {
                button5.setIcon(smashed);
                HIT_n.start();
                HIT_n.setMicrosecondPosition(0);
                point++;
            }
            else if (imposter1==i) {
                point=0;
                flash=1;
                IMPOSTED_n.setMicrosecondPosition(0);
                IMPOSTED_n.start();
            }
            else{
                ERROR_n.setMicrosecondPosition(0);
                point--;
                ERROR_n.start();
                button5.setText("fooled");
                button5.setEnabled(false);
                button5.setBackground(new Color(0xEF0A0A));
            }
        }
        if (e.getSource() == button6) {
            i = 5;
            if (selection1 == i || selection2 == i || selection3 == i || selection4 == i) {
                button6.setIcon(smashed);
                point++;
                HIT_n.setMicrosecondPosition(0);
                HIT_n.start();
            }
            else if (imposter1==i) {
                point=0;
                flash=1;
                IMPOSTED_n.setMicrosecondPosition(0);
                IMPOSTED_n.start();
            }
            else{
                ERROR_n.setMicrosecondPosition(0);
                point--;
                ERROR_n.start();
                button6.setText("fooled");
                button6.setEnabled(false);
                button6.setBackground(new Color(0xEF0A0A));
            }
        }
        if (e.getSource() == button7) {
            i = 6;
            if (selection1 == i || selection2 == i || selection3 == i || selection4 == i) {
                button7.setIcon(smashed);
                point++;
                HIT_n.setMicrosecondPosition(0);
                HIT_n.start();
            }
            else if (imposter1==i) {
                point=0;
                flash=1;
                IMPOSTED_n.setMicrosecondPosition(0);
                IMPOSTED_n.start();
            }
            else{
                ERROR_n.setMicrosecondPosition(0);
                point--;
                ERROR_n.start();
                button7.setText("fooled");
                button7.setEnabled(false);
                button7.setBackground(new Color(0xEF0A0A));
            }
        }
        if (e.getSource() == button8) {
            i = 7;
            if (selection1 == i || selection2 == i || selection3 == i || selection4 == i) {
                button8.setIcon(smashed);
                point++;
                HIT_n.setMicrosecondPosition(0);
                HIT_n.start();
            }
            else if (imposter1==i) {
                point=0;
                flash=1;
                IMPOSTED_n.setMicrosecondPosition(0);
                IMPOSTED_n.start();
            }
            else{
                ERROR_n.setMicrosecondPosition(0);
                ERROR_n.start();
                point--;
                button8.setText("fooled");
                button8.setEnabled(false);
                button8.setBackground(new Color(0xEF0A0A));
            }
        }
        if (e.getSource() == button9) {
            i = 8;
            if (selection1 == i || selection2 == i || selection3 == i || selection4 == i) {
                button9.setIcon(smashed);
                point++;
                HIT_n.setMicrosecondPosition(0);
                HIT_n.start();
            }
            else if (imposter1==i) {
                point=0;
                flash=1;
                IMPOSTED_n.setMicrosecondPosition(0);
                IMPOSTED_n.start();
            }
            else{
                ERROR_n.setMicrosecondPosition(0);
                ERROR_n.start();
                point--;
                button9.setText("fooled");
                button9.setEnabled(false);
                button9.setBackground(new Color(0xEF0A0A));
            }
        }
        if(e.getSource()==button10){
            point=0;
            again=1;
            flash=0;
            mainframe.setTitle("Smash the Rabit");
            vw();
        }
    }
}
