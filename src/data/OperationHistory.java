package src.data;

import src.exceptions.FormatException;
import src.exceptions.MathException;
import src.exceptions.NoNumberException;
import src.exceptions.ProcessingException;

public class OperationHistory {

    private String equation;
    private double result;
    private Exception error;

    public String getEquation() {
        return equation;
    }
    public String getResult() {
        if(error != null){
            if(error instanceof FormatException){
                return "FORMAT ERROR!";
            }else if(error instanceof MathException){
                return "MATH ERROR!";
            }else if(error instanceof NoNumberException){
                return "EMPTY EQUATION!";
            }else{
                return "ERROR!";
            }
        }
        return result + "";
    }
    public Exception getError() {
        return error;
    }
    public void setEquation(String equation) {
        this.equation = equation;
    }
    public void setResult(double result) {
        this.result = result;
    }
    public void setError(Exception error) {
        this.error = error;
    }
    
}
