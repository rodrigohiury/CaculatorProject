package src.GUI;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame{

    JPanel contentPane;
    GridBagLayout layout;
    GridBagConstraints gridBagConstraints;
    

    public MainFrame(){
        setTitle("Calculadora");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(350, 550);
        setResizable(false);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5,5, 5));
        layout = new GridBagLayout();
        gridBagConstraints = new GridBagConstraints();
        contentPane.setLayout(layout);
        JTextArea equation = new JTextArea();
        JPanel display = new JPanel();
        equation.setFont(new Font("Nirmala UI", Font.PLAIN, 40));
        equation.setText("função");
        equation.setLineWrap(false);
        display.add(equation);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.anchor = GridBagConstraints.NORTH;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        contentPane.add(display, gridBagConstraints);
        JButton jBFunction = new JButton("F(X)");
        jBFunction.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){

            }
        });
        JButton jBSum = new JButton("+");
        jBSum.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                equation.append("+");
            }
        });
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridheight = 1;
        contentPane.add(jBSum, gridBagConstraints);
        add(contentPane);
        setVisible(true);
    }
}
