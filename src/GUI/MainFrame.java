package src.GUI;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import src.data.Calculator;
import src.exceptions.FormatException;
import src.exceptions.MathException;
import src.exceptions.NoNumberException;
import src.exceptions.ProcessingException;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame{

    JPanel contentPane;
    GridBagLayout layout;
    GridBagConstraints gridBagConstraints;
    Calculator calculator;
    

    public MainFrame(){
        calculator = new Calculator();
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
        equation.setText("");
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
                double resultado = 0;
                try {
                    resultado = calculator.functionSolve(equation.getText(), 1);
                } catch (MathException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                } catch (FormatException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                } catch (NoNumberException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                } catch (ProcessingException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                if(resultado % 0.1 == 0){
                    int result = (int) resultado;
                    equation.setText(result + "");
                }else {
                    equation.setText(resultado + "");
                }
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
        contentPane.add(jBFunction, gridBagConstraints);
        JButton jBSubtraction = new JButton("-");
        jBSubtraction.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                equation.append("-");
            }
        });
        gridBagConstraints.gridy = 4;
        contentPane.add(jBSubtraction, gridBagConstraints);
        
        JButton jBRightArrow = new JButton("<");
        jBRightArrow.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                
            }
        });
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridx = 1;
        contentPane.add(jBRightArrow, gridBagConstraints);
        JButton jBLeftArrow = new JButton(">");
        jBLeftArrow.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){

            }
        });
        gridBagConstraints.gridx = 2;
        contentPane.add(jBLeftArrow, gridBagConstraints);
        JButton jBDelete = new JButton("Del");
        jBDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                String text = equation.getText();
                if(text.length() > 0){
                    equation.setText(text.substring(0, text.length()-1));
                }
            }
        });
        gridBagConstraints.gridx = 3;
        contentPane.add(jBDelete, gridBagConstraints);
        add(contentPane);
        setVisible(true);
    }
}
