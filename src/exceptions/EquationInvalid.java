package src.exceptions;

import java.lang.Exception;

public class EquationInvalid extends Exception{

    String motivo;

    public EquationInvalid(String motivo){
        super("Equação dada não é válida!");
        this.motivo = motivo;
    }

    public String getMotivo() {
        return motivo;
    }

}