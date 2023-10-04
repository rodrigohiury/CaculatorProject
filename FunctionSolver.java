import java.util.List;

public class FunctionSolver{

  /*
  
  Verifica se a equação dada é válida.
  Se os parênteses estão todos fechados,
  e se as operações dadas na equação são da forma (E ° E)
  para as operações de Soma, Subtração, Multiplicação,
  Divisão e Expoencial. Ou (° E) para as operações de
  Raíz quadrada, Módulo e Valor Negativo.
  Sendo E um número real ou uma subequação.
  
  */
  
  public Verification verify(String equacao){
    String eqCopy = equacao;
    int parentesisOpenCounter = 0;
    int parentesisClosedCounter = 0;
    int
    int absoluteCounter = 0;
    Verification verificacao = new Verification();
    for(int i = 0; i < eqCopy.length(); i++){
      char c = eqCopy.charAt(i);
      if(c == '('){
        verificacao.addParentesisOpen(i);
        if(verificacao.getNumbersSize() % 2 != 0){
          verificacao.addNumberPosition(i-1);
        }
      }else if(c == ')'){
        verificacao.addParentesisClosed(i);
        if(verificacao.getNumbersSize() % 2 != 0){
          verificacao.addNumberPosition(i-1);
        }
      }else if(c == '['){
        verificacao.addBracketOpen(i);
        if(verificacao.getNumbersSize() % 2 != 0){
          verificacao.addNumberPosition(i-1);
        }
      }else if(c == ']'){
        verificacao.addBracketClosed(i);
        if(verificacao.getNumbersSize() % 2 != 0){
          verificacao.addNumberPosition(i-1);
        }
      }else if(c == '√'){
        if(eqCopy.charAt(i+1) != '('){
          //lançar erro
        }
        if(!verificacao.gotPriority3()){
          verificacao.setPriority3(true);
        }
        if(verificacao.getNumbersSize() % 2 != 0){
          verificacao.addNumberPosition(i-1);
        }
      }else if(this.isPriority3(c)){
        if(!verificacao.gotPriority3()){
          verificacao.setPriority3(true);
        }
        if(verificacao.getNumbersSize() % 2 != 0){
          verificacao.addNumberPosition(i-1);
        }
      }else if(this.isPriority2(c)){
        if(!verificacao.gotPriority2()){
          verificacao.setPriority2(true);
        }
        if(verificacao.getNumbersSize() % 2 != 0){
          verificacao.addNumberPosition(i-1);
        }
      }else if(this.isPriority1(c)){
        if(!verificacao.gotPriority1()){
          verificacao.setPriority1(true);
        }
        if(parentesisOpenCounter - parentesisClosedCounter == 0){
          if(absoluteCounter % 2 == 0){
            verificacao.setFirstPriority1(i);
          }
        }
        if(verificacao.getNumbersSize() % 2 != 0){
          verificacao.addNumberPosition(i-1);
        }
      }else if(this.isNumber(c)){
        if(verificacao.getNumbersSize() % 2 == 0){
          verificacao.addNumberPosition(i);
        }
      }
    }
    if(parentesisOpenCounter - parentesisClosedCounter != 0){
      //lancar erro
    }else if(absoluteCounter % 2 != 0){
      //lancar erro
    }
    verificacao.setValid(true);
    return verificacao;
  }

  public double solve(String equacao){
    String eqCopy = equacao;
    Verification verificacao = this.verify(eqCopy);
    if(verificacao.isValid()){
      if(verificacao.getNumbersAmount() == 1){
        int numberPosition = verificacao.getNumbersSize()-1;
        if(verificacao.getNumbers().get(numberPosition) == eqCopy.length()-1){
          if(verificacao.getNumbers().get(numberPosition - 1) == 0){
            return Double.parseDouble(eqCopy);
          }else if(verificacao.getNumbers().get(numberPosition - 1) == 1){
            double number = Double.parseDouble(eqCopy.substring(verificacao.getNumbers().get(numberPosition - 1)));
            if(verificacao.gotPriority1()){
              char op = eqCopy.charAt(verificacao.getFirstPriority1());
              if(verificacao.getFirstPriority1() == 0){
                switch (op){
                  case '+':
                    return number;
                  case '-':
                    return -number;
                  default:
                    //lançar erro
                }
              }
            }
          }
        }
      }else{
        char op;
        double resultLeft, resultRight;
        if(verificacao.gotPriority1()){
          op = eqCopy.charAt(verificacao.getFirstPriority1());
          resultLeft = this.solve(eqCopy.substring(0, verificacao.getFirstPriority1()-1));
          resultRight = this.solve(eqCopy.substring(verificacao.getFirstPriority1()+1, eqCopy.length()-1));
          switch (op){
            case '+':
              return resultLeft + resultRight;
            case '-':
              return resultLeft - resultRight;
            default:
              //lançar erro
          }
        }else if(verificacao.gotPriority2()){
          op = eqCopy.charAt(verificacao.getFirstPriority1());
          resultLeft = this.solve(eqCopy.substring(0, verificacao.getFirstPriority1()-1));
          resultRight = this.solve(eqCopy.substring(verificacao.getFirstPriority1()+1, eqCopy.length()-1));
          switch (op){
            case '*':
              return resultLeft * resultRight;
            case '/':
              return resultLeft / resultRight;
            default:
              //lançar erro
          }
        }else if(verificacao.gotPriority3()){
          op = eqCopy.charAt(verificacao.getFirstPriority1());
          resultLeft = this.solve(eqCopy.substring(0, verificacao.getFirstPriority1()-1));
          resultRight = this.solve(eqCopy.substring(verificacao.getFirstPriority1()+1, eqCopy.length()-1));
          switch (op){
            case '*':
              return resultLeft * resultRight;
            case '/':
              return resultLeft / resultRight;
            default:
              //lançar erro
          }
        }
      }
    }
  }  

  private boolean isOperation(char c){
    if(this.isPriority3(c) || this.isPriority2(c) || this.isPriority1(c)){
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

  private boolean isNumber(char c){
    switch (c){
      case '1':
      case '2':
      case '3':
      case '4':
      case '5':
      case '6':
      case '7':
      case '8':
      case '9':
      case '0':
        return true;
      default:
        return false;
    }
  }
}