package src.test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import src.data.FunctionSolver;
import src.exceptions.FormatException;
import src.exceptions.MathException;
import src.exceptions.NoNumberException;
import src.exceptions.ProcessingException;

public class FunctionSolverTest {

    FunctionSolver calculadora = new FunctionSolver();
    String equacao;

    @Test
    public void basicFunction() throws MathException, FormatException, NoNumberException, ProcessingException{
        equacao = "2+3*5";
        double resultado = calculadora.solve(equacao);
        assertEquals(resultado, 17, 0.001d);
    }

    @Test
    public void numberFunction() throws MathException, FormatException, NoNumberException, ProcessingException{
        equacao = "2";
        assertEquals(2, calculadora.solve(equacao), 0.0000001d);
    }

    @Test
    public void bigNumber() throws MathException, FormatException, NoNumberException, ProcessingException{
        equacao = "23350";
        assertEquals(23350, calculadora.solve(equacao), 0.0000001d);
    }

    @Test
    public void negativeNumber() throws MathException, FormatException, NoNumberException, ProcessingException{
        equacao = "-7";
        assertEquals(-7, calculadora.solve(equacao), 0.0000001d);
    }

    @Test
    public void negativeBigNumber() throws MathException, FormatException, NoNumberException, ProcessingException{
        equacao = "-57550";
        assertEquals(-57550, calculadora.solve(equacao), 0.000001d);
    }

    @Test
    public void basicSum() throws MathException, FormatException, NoNumberException, ProcessingException{
        equacao = "2+3";
        assertEquals(5, calculadora.solve(equacao), 0.00000001d);
    }

    @Test
    public void basicSubtraction() throws MathException, FormatException, NoNumberException, ProcessingException{
        equacao = "5-8";
        assertEquals(-3, calculadora.solve(equacao), 0.00000001d);
    }

    @Test
    public void basicMultiplication() throws MathException, FormatException, NoNumberException, ProcessingException{
        equacao = "8*9";
        assertEquals(72, calculadora.solve(equacao), 0.0000001d);
    }

    @Test
    public void basicDivision() throws MathException, FormatException, NoNumberException, ProcessingException{
        equacao = "10/2";
        assertEquals(5, calculadora.solve(equacao), 0.00000001d);
    }

    @Test
    public void basicAbsoluteValue() throws MathException, FormatException, NoNumberException, ProcessingException{
        equacao = "[-23]";
        assertEquals(23, calculadora.solve(equacao), 0.0001d);
    }

    @Test
    public void basicSquareRoot() throws MathException, FormatException, NoNumberException, ProcessingException{
        equacao = "√(144)";
        assertEquals(12, calculadora.solve(equacao), 0.0001d);
    }

    @Test
    public void basicSquareRootNoParentesis() throws MathException, FormatException, NoNumberException, ProcessingException{
        equacao = "√144";
        assertEquals(12, calculadora.solve(equacao), 0.0001d);
    }

    @Test
    public void basicPower() throws MathException, FormatException, NoNumberException, ProcessingException{
        equacao = "(2)^(2)";
        assertEquals(4, calculadora.solve(equacao), 0.00001d);
    }

    @Test
    public void basicPowerNoParentesis() throws MathException, FormatException, NoNumberException, ProcessingException{
        equacao = "2^2";
        assertEquals(4, calculadora.solve(equacao), 0.00001d);
    }

    @Test
    public void basicFunctionChangedOrder() throws MathException, FormatException, NoNumberException, ProcessingException{
        equacao = "(2+3)*5";
        assertEquals(25, calculadora.solve(equacao), 0.00000001d);
    }

    @Test
    public void operationsTogether() throws MathException, FormatException, NoNumberException, ProcessingException{
        equacao = "√([-23]+(11)^(2))-5*2+3";
        assertEquals(5, calculadora.solve(equacao), 0.0001d);
    }

    @Test
    public void operationsTogetherSquareRoot() throws MathException, FormatException, NoNumberException, ProcessingException{
        equacao = "√([-23]+(11)^(2))";
        assertEquals(12, calculadora.solve(equacao), 0.0001d);
    }

    @Test
    public void powerOfSquareRoot() throws MathException, FormatException, NoNumberException, ProcessingException{
        equacao = "2^√4";
        assertEquals(4, calculadora.solve(equacao), 0.000001d);
    }

    @Test
    public void squareRootAndSum() throws MathException, FormatException, NoNumberException, ProcessingException{
        equacao = "√4+2";
        assertEquals(4, calculadora.solve(equacao), 0.000001d);
    }

    @Test
    public void mediumFunction() throws MathException, FormatException, NoNumberException, ProcessingException{
        equacao = "[(2^√4)^[-5^2]]";
        assertEquals(1.125899906842624E15, calculadora.solve(equacao), 0.0001d);
    }
}
