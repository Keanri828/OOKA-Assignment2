package org.hbrs.ooka.ws2020.uebung2.view;

import org.hbrs.ooka.ws2020.uebung2.runtime.RuntimeEnv;

import java.awt.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GUI extends JPanel implements ActionListener {
    protected JTextField textField;
    protected JTextArea textArea;
    private final static String newline = "\n";
    private RuntimeEnv re = new RuntimeEnv();
    public GUI() {
        super(new GridBagLayout());

        textField = new JTextField(20);
        textField.addActionListener(this);

        textArea = new JTextArea(5, 20);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        //Add Components to this panel.
        GridBagConstraints c = new GridBagConstraints();
        c.gridwidth = GridBagConstraints.REMAINDER;

        c.fill = GridBagConstraints.HORIZONTAL;
        add(textField, c);

        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1.0;
        c.weighty = 1.0;
        add(scrollPane, c);
    }

    public void actionPerformed(ActionEvent evt) {
        String text = textField.getText();
        String[] s = text.split(" ");
        try {
            if (s[0].equals("init")) {
                textArea.append(re.initComponent(s[1])+newline);
            }
            else if(s[0].equals("delete")){
                textArea.append(re.deleteComponent(s[1])+newline);
            }
            else if(s[0].equals("start")){
                textArea.append(re.startComp(s[1])+newline);
            }
            else if(s[0].equals("stop")){
                textArea.append(re.stopComp(s[1])+newline);
            }
            else if(s[0].equals("state")){
                textArea.append(re.getState(s[1])+newline);
            }
        }catch(Exception e){
            textArea.append("Error at: " +text+newline);
        }
        textArea.append(text + newline);
        textField.selectAll();

        //Make sure the new text is visible, even if there
        //was a selection in the text area.
        textArea.setCaretPosition(textArea.getDocument().getLength());
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event dispatch thread.
     */
    public static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("Input");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Add contents to the window.
        frame.add(new GUI());

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public void print(String s){
        textArea.append(s + "\n"); // all outputs get their own line
    }

    public String getInput(){

        return "";
    }
}
