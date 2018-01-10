import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class UI {
    private JPanel mainPanel;
    private JButton helpButton;
    private BufferedImage buttonIcon;
    private JPanel TextPanel;
    private JPanel InputPanel;
    private JTextArea rulesInput;
    private JLabel InputLabel;
    private JTextField inputText;
    private JButton Simulate;
    private JPanel errorPanel;
    private JLabel errorLabel;
    private JTextField outputText;
    private JPanel rulesPanel;
    private JPanel helpPanel;
    private JTextField startStateInput;
    private final String actionHelp = "Actions allowed:\n  1) r: move cursor right.\n  2) l: move cursor left.\n  3) y: accept input string.\n  4) n: reject input string.";
    private final String stateHelp = "State Names can be any string, it's preferred to be different from alphabet,for example:\n  1) state1, state2, ..etc\n  2) q0, q1, ..etc";
    private final String alphabetHelp = "Language Alphabet can be any string, it's preferred to be only one character,for example:\n  1)a ,b, c, ..etc\n  2)1, 2, 3, ..etc";
    private final String transitionHelp = "Transition can be written in any of the following formats:\n  1) q0 0 q1 1 a\n  2) q0, 0, q1, 1, a\n  3) (q0, 0) (q1, 1, a)";
    private final String exampleProgram = "Example Program: The following program will change zeros to ones and vice versa\n  q0 1 q0 0 r\n  q0 0 q0 1 r\n  q0 # q1 # y";

    public UI() {
        createUIComponents();
        errorLabel.setText("Click on the help button above for user manual.");
        Simulate.addActionListener(e->{
            String input = inputText.getText();
            String rules = rulesInput.getText();
            errorLabel.setText("");
            try {
                Simulator sim = new Simulator(rules, input);
                sim.setStartState(startStateInput.getText());
                sim.compute();
                outputText.setText(sim.getOutput());
                errorLabel.setText("current State: "+sim.getFinalTransition().getState() + ", cursor position: " + (sim.getCursor().getCursor() + 1) + ", Action: " + sim.getFinalTransition().action());
            }catch (Exception e1){
                errorLabel.setText(e1.getMessage());
            }
        });
        helpButton.setFocusable(true);
        helpButton.requestFocus();
    }

    private void createUIComponents() {
        // Help Button
        try {
            buttonIcon = ImageIO.read(new File("help2.png"));
        } catch (IOException e) {
            buttonIcon = null;
        }
        if(buttonIcon == null){
            helpButton = new JButton("Help");
        }else{
            helpButton = new JButton(new ImageIcon(buttonIcon));
        }
        helpButton.setBorder(BorderFactory.createEmptyBorder());
        helpButton.addActionListener(e->{
            JOptionPane.showMessageDialog(null, actionHelp+"\n"+alphabetHelp+"\n"+stateHelp+"\n"+transitionHelp+"\n"+exampleProgram);
        });
        helpButton.setContentAreaFilled(false);
        helpPanel.add(helpButton);

        // Transitions input
        rulesInput = new JTextArea();
        rulesInput.setForeground(Color.GRAY);
        rulesInput.setText("Enter Transitions Here...");
        startStateInput.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (startStateInput.getText().equals("Start State")) {
                    rulesInput.setText("");
                    rulesInput.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (rulesInput.getText().isEmpty()) {
                    rulesInput.setForeground(Color.GRAY);
                    rulesInput.setText("Start State");
                }
            }
        });
        rulesInput.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (rulesInput.getText().equals("Enter Transitions Here...")) {
                    rulesInput.setText("");
                    rulesInput.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (rulesInput.getText().isEmpty()) {
                    rulesInput.setForeground(Color.GRAY);
                    rulesInput.setText("Enter Transitions Here...");
                }
            }
        });
        JScrollPane scroll = new JScrollPane (rulesInput, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        rulesPanel.add(scroll);
        rulesPanel.setBorder(BorderFactory.createEmptyBorder());
    }

    public static void main(String[] args){
        JFrame frame = new JFrame();
        frame.setContentPane(new UI().mainPanel);
        frame.setSize(1000, 1000);
        frame.setResizable(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
