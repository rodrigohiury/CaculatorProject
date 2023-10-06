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
}