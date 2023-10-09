package src.exceptions;

public class MathException extends Exception{

    String motivo;

    public MathException(){
        super("Erro Matemático");
    }

    public MathException(String motivo){
        super("Erro Matemático");
        this.motivo = motivo;
    }
    
}
