package src.data;

import java.util.ArrayList;
import java.util.List;

import src.exceptions.FormatException;
import src.exceptions.MathException;
import src.exceptions.NoNumberException;
import src.exceptions.ProcessingException;


/**
 * Essa classe é a Calculadora em si. Ela contém todas as funções da calculadora
 * @author Rodrigo Hiury
 * @version 1.0.0
 */
public class Calculator {

    private final double PI = Math.PI;                      // Constante pi
    private final double E = Math.E;                        // Constante de Euler
    private double memory;                                  // Variável de memória
    private FunctionSolver solver = new FunctionSolver();   // Classe FunctionSolver
    private List<OperationHistory> history = new ArrayList<>(10);

    public double getPI() {
        return PI;
    }
    public double getE() {
        return E;
    }
    public double getMemory() {
        return memory;
    }
    public void setMemory(double memory) {
        this.memory = memory;
    }

    public void addMemory(double memory){
        this.memory += memory;
    }

    public void clearMemory(){
        this.memory = 0;
    }

    public List<Double> functionSolveInterval(String equation, double beggining, double end, double step) throws MathException, FormatException, NoNumberException, ProcessingException{
        List<Double> result = new ArrayList<>();
        double number;
        String eqCopy, variable;
        for(double i = beggining ; i <= end ; i = i + step){
            variable = "(" + i + ")";
            eqCopy = equation.replace("X", variable);
            number = solver.solve(eqCopy);
            result.add(number);
        }
        return result;
    }

    public double functionSolve(String equation, double variable) throws MathException, FormatException, NoNumberException, ProcessingException{
        equation = equation.replace("X", "(" + variable + ")");
        return solver.solve(equation);
    }

    public double solve(String equation) throws MathException, FormatException, NoNumberException, ProcessingException{
        return solver.solve(equation);
    }

    public OperationHistory getLatestHistory(){
        if(history.size() > 0){
            return history.get(history.size()-1);
        }
        return null;
    }

    public OperationHistory getHistory(int i){
        if(i <= history.size()){
            return history.get(history.size()-i);
        }
        return null;
    }

    public void addHistory(OperationHistory operation){
        if(history.size() >= 10){
            history.remove(0);
        }
        history.add(operation);
    }
    
}
