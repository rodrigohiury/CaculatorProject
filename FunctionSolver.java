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
  
  public Verification verifier(String equacao){
    String eqCopy = equacao;
    int parentesisOpenCounter = 0;
    int parentesisClosedCounter = 0;
    int absoluteCounter = 0;
    Verification operacoes = new Verification();
    for(int i = 0; i < eqCopy.lenght; i++){
      char c = eqCopy.charAt(i);
      if(c == '('){
        parentesisOpenCounter++;
        if(!operacoes.gotPriority4()){
          operacoes.setPriority4(true);
        }
      }else if(c == ')'){
        parentesisClosedCounter++;
        if(parentesisOpenCounter == 0){
          //lançar erro
        }
        if(!operacoes.gotPriority4()){
          operacoes.setPriority4(true);
        }
      }else if(c == '|'){
        absoluteCounter++;
        if(!operacoes.gotPriority3()){
          operacoes.setPriority3(true);
        }
      }else if(c == '√'){
        if(eqCopy.charAt(i+1) != '('){
          return false;
        }
        if(!operacoes.gotPriority3()){
          operacoes.setPriority3(true);
        }
      }else if(this.isPriority3(c)){
        if(!operacoes.gotPriority3()){
          operacoes.setPriority3(true);
        }
      }else if(this.isPriority2(c)){
        if(!operacoes.gotPriority2()){
          operacoes.setPriority2(true);
        }
      }else if(this.isPriority1(c)){
        if(!operacoes.gotPriority1()){
          operacoes.setPriority1(true);
        }
        if(parentesisOpenCounter - parentesisClosedCounter == 0){
          if(absoluteCounter % 2 == 0){
            operacoes.setFirstPriority1(i);
          }
        }
      }
    }
    if(parentesisOpenCounter - parentesisClosedCounter != 0){
      //lancar erro
    }else if(absoluteCounter % 2 != 0){
      //lancar erro
    }
    operacoes.setValid(true);
    return operacoes;
  }

  public String solve(String equacao){
    String eqCopy = equacao;
    Verification verificacao = this.verify(eqCopy);
    if(verificacao.isValid()){
      if(verificacao.gotPriority1()){
        
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