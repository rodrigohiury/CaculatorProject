package src.exceptions;

import java.lang.Exception;

public class FormatException extends Exception{

    String motivo;

    public FormatException(String motivo){
        super("Erro de Formatação!");
        this.motivo = motivo;
    }

    public String getMotivo() {
        return motivo;
    }

}