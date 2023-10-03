public static class FunctionSolver{

  /*
  
  Verifica se a equação dada é válida.
  Se os parênteses estão todos fechados,
  e se as operações dadas na equação são da forma (E ° E)
  para as operações de Soma, Subtração, Multiplicação,
  Divisão e Expoencial. Ou (° E) para as operações de
  Raíz quadrada, Módulo e Valor Negativo.
  Sendo E um número real ou uma subequação.
  
  */
  
  public string verifier(String equacao){
    String eqCopy = equacao;
    String subEqL;
    String subEqR;
    int parentesisOpenCounter = 0;
    int parentesisClosedCounter = 0;
    for(int i = 0; i < eqCopy.lenght; i++){
      char c = eqCopy.charAt(i);
      if(c == '('){
        parentesisOpenCounter++;
      }else if(c == ')'){
        parentesisClosedCounter++;
        if(parentesisOpenCounter == 0){
          //lançar erro
        }
      }else if(this.isOperation(c)){
        
      }
    }
  }

  private boolean isOperation(String c){
    if(isPriority3(c) || isPriority2(c) || isPriority1(c)){
      return true;
    }else{
      return false;
    }
  }

  private boolean isPriority4(char c){
    if(c == '(' || c == ')'){
      return true;
    }else{
      return false;
    }
  }

  private boolean isPriority3(char c){
    if(c == '|' || c == '√' || c == '^'){
      return true;
    }else{
      return false;
    }
  }

  private boolean isPriority2(char c){
    if(c=='*' || c == '/'){
      return true;
    }else{
      return false;
    }
  }

  private boolean isPriority1(char c){
    if(c == '+' || c == '-'){
      return true;
    }else{
      return false;
    }
  }
}