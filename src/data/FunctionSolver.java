package src.data;

import src.exceptions.*;

/**
 * Classe principal, responsável por verificar e calcular a equação fornecida
 * 
 * @author Rodrigo Hiury
 * @version 1.0.0
 */
public class FunctionSolver{

  /**
   * Verifica se a equação dada é válida e executa um escaneamento da equação.
   * <p>
   * Equação é válida se os parênteses estão todos fechados, 
   * e se as operações dadas na equação são da forma (E ° E)
   * para as operações de Soma, Subtração, Multiplicação, Divisão e Expoencial.
   * Ou (° E) para as operações de Raíz quadrada, Módulo e Valor Negativo.
   * Sendo E um número real ou uma subequação.
   * 
   * @param equacao String da equação a ser verificada
   * @return Objeto da Classe Verification com todas as informações relevantes da equação
   * @throws FormatException quando a equação não respeita a formatação exigida
   */
  
  public Verification verify(String equacao) throws FormatException{
    String eqCopy = equacao;
    Verification verificacao = new Verification();
    if(eqCopy.length() == 0){
      throw new FormatException("Equação Vazia");
    }
    for(int i = 0; i < eqCopy.length(); i++){
      char c = eqCopy.charAt(i);
      if(c == '('){
        if(!verificacao.gotPriority4()){
          if(verificacao.noOpenBrackets()){
            verificacao.setPriority4(true);
            verificacao.setFirstPriority4(i);
          }
        }
        verificacao.addParentesisOpen(i);
        if(verificacao.getNumbersSize() % 2 != 0){
          verificacao.addNumberPosition(i-1);
        }
      }else if(c == ')'){
        verificacao.addParentesisClosed(i);
        if(verificacao.getNumbersSize() % 2 != 0){
          verificacao.addNumberPosition(i-1);
        }
        if(verificacao.existInvisibleBracketOpen()){
          verificacao.closeInvisibleBracket(i);
        }
      }else if(c == '['){
        if(verificacao.noOpenBrackets()){
          if(!verificacao.gotPriority3()){
            verificacao.setPriority3(true);
            verificacao.setFirstPriority3(i);
          }
        }
        verificacao.addBracketOpen(i);
        if(verificacao.getNumbersSize() % 2 != 0){
          verificacao.addNumberPosition(i-1);
        }
      }else if(c == ']'){
        verificacao.addBracketClosed(i);
        if(verificacao.getNumbersSize() % 2 != 0){
          verificacao.addNumberPosition(i-1);
        }
        if(verificacao.existInvisibleBracketOpen()){
          verificacao.closeInvisibleBracket(i);
        }
      }else if(c == '√'){
        if(eqCopy.charAt(i+1) != '('){
          if(!this.isNumber(eqCopy.charAt(i+1))){
            throw new FormatException("Raíz não delimidata");
          }
        }
        if(verificacao.noOpenBrackets()){
          if(!verificacao.gotPriority3()){
            verificacao.setPriority3(true);
            verificacao.setFirstPriority3(i);
          }
        }
        if(verificacao.getNumbersSize() % 2 != 0){
          verificacao.addNumberPosition(i-1);
        }
      }else if(c == '^'){
        if(verificacao.getNumbersSize() % 2 != 0){
          verificacao.addNumberPosition(i-1);
          if(verificacao.existInvisibleBracketOpen()){
            verificacao.closeInvisibleBracket(i);
          }
        }
        if(verificacao.noOpenBrackets()){
          verificacao.setPriority3(true);
          verificacao.setFirstPriority3(i);
          verificacao.addInvisibleBracketOpen(i+1);
        }
        if(verificacao.getNumbersAmount() == 0){
          throw new FormatException("Operação binária com 1 único número");
        }
      }else if(this.isPriority2(c)){
        if(verificacao.noOpenBrackets()){
          verificacao.setPriority2(true);
          verificacao.setFirstPriority2(i);
        }
        if(verificacao.getNumbersSize() % 2 != 0){
          verificacao.addNumberPosition(i-1);
          if(verificacao.existInvisibleBracketOpen()){
            verificacao.closeInvisibleBracket(i);
          }
        }
        if(verificacao.getNumbersAmount() == 0){
          throw new FormatException("Operação binária com 1 único número");
        }
      }else if(this.isPriority1(c)){
        if(verificacao.noOpenBrackets()){
          verificacao.setPriority1(true);
          verificacao.setFirstPriority1(i);
        }
        if(verificacao.getNumbersSize() % 2 != 0){
          verificacao.addNumberPosition(i-1);
          if(verificacao.existInvisibleBracketOpen()){
            verificacao.closeInvisibleBracket(i);
          }
        }
      }else if(this.isNumber(c)){
        if(verificacao.getNumbersSize() % 2 == 0){
          if(c == '.'){
            throw new FormatException("Número fracionário ilegal!");
          }
          verificacao.addNumberPosition(i);
        }
      }
    }
    if(verificacao.getNumbersSize() % 2 != 0){
      verificacao.addNumberPosition(eqCopy.length()-1);
      if(verificacao.existInvisibleBracketOpen()){
        verificacao.closeInvisibleBracket(eqCopy.length()-1);
      }
    }
    if(!verificacao.noOpenBrackets()){
      throw new FormatException("Chave/Parêntese não pareado!");
    }
    if(verificacao.getNumbersAmount() <= 1){
      if(verificacao.gotPriority2() || eqCopy.contains("^")){
        throw new FormatException("Operação binária com 1 único número");
      }
    }
    verificacao.setValid(true);
    return verificacao;
  }

  /**
   * Método que resolve a equação dada.
   * 
   * @param equacao String da equação a ser resolvida
   * @return valor do tipo double do resultado da equação
   * @throws MathException Quando é achado indeterminação na equação
   * @throws FormatException Quando a equação não respeita a formatação exigida
   * @throws NoNumberException
   * @throws ProcessingException
   */

  public double solve(String equacao) throws MathException, FormatException, NoNumberException, ProcessingException{
    String eqCopy = equacao;
    Verification verificacao = this.verify(eqCopy);
    BracketsPairs parentesis;
    char op;
    double resultLeft, resultRight, number;
    int subEquationEnd, subEquationBeggining;
    if(verificacao.isValid()){
      if(verificacao.getNumbersAmount() == 1){
        if(!verificacao.gotAnyPriority()){
          return Double.parseDouble(eqCopy);
        }else{
          if(verificacao.gotPriority1()){
            if(verificacao.getFirstPriority1() == 0){
              op = eqCopy.charAt(verificacao.getFirstPriority1());
              resultLeft = this.solve(eqCopy.substring(verificacao.getFirstPriority1()+1));
              switch (op){
                case '+':
                  return resultLeft;
                case '-':
                  return -resultLeft;
                default:
                  throw new FormatException("Operador não permitido!");
              }
            }
          }else if(verificacao.gotPriority3()){
            op = eqCopy.charAt(verificacao.getFirstPriority3());
            switch (op) {
              case '√':
                op = eqCopy.charAt(verificacao.getFirstPriority3()+1);
                if (op == '(') {
                  parentesis = verificacao.getParentesisOpenAt(verificacao.getFirstPriority3()+1);
                  if(parentesis == null){
                    throw new FormatException("Parentese Não Pareado!");
                  }
                  number = this.solve(eqCopy.substring(verificacao.getFirstPriority3()+2, parentesis.getPositionClosed()));
                  if(number < 0){
                    throw new MathException("Raíz de número complexo");
                  }
                  return Math.sqrt(number);
                }else if(op == '['){
                  parentesis = verificacao.getBracketOpenAt(verificacao.getFirstPriority3()+1);
                  if(parentesis == null){
                    throw new FormatException("Chave Não Pareada!");
                  }
                  return Math.sqrt(this.solve(eqCopy.substring(verificacao.getFirstPriority3()+1, parentesis.getPositionClosed()+1)));
                }else if(this.isNumber(op)){
                  number = this.solve(eqCopy.substring(verificacao.getFirstPriority3()+1));
                  if(number < 0){
                    throw new MathException("Raíz de número complexo");
                  }
                  return Math.sqrt(number);
                }else {
                  number = this.solve(eqCopy.substring(verificacao.getFirstPriority3()+1));
                  if(number < 0){
                    throw new MathException("Raíz de número complexo");
                  }
                  return Math.sqrt(number);
                }
              case '[':
                parentesis = verificacao.getBracketOpenAt(verificacao.getFirstPriority3());
                if(parentesis == null){
                  throw new FormatException("Chave não pareada!");
                }
                return Math.abs(this.solve(eqCopy.substring(verificacao.getFirstPriority3()+1, parentesis.getPositionClosed())));
              default:
                throw new FormatException("Operação Binária com 1 número");
            }
          }else if(verificacao.gotPriority4()){
            parentesis = verificacao.getParentesisOpenAt(0);
            if(parentesis == null){
              throw new FormatException("Parêntese não pareado!");
            }
            int positionParentesisClosed = parentesis.getPositionClosed();
            return this.solve(eqCopy.substring(verificacao.getFirstPriority4()+1, positionParentesisClosed));
          }
        }
      }else{
        if(verificacao.gotPriority1()){
          op = eqCopy.charAt(verificacao.getFirstPriority1());
          if(verificacao.getFirstPriority1() == eqCopy.length() - 1){
            throw new FormatException("Ordenação errada de operador!");
          }else if(verificacao.getFirstPriority1() == 0){
            switch (op) {
              case '+':
                return this.solve(eqCopy.substring(verificacao.getFirstPriority1()+1));
              case '-':
                return -this.solve(eqCopy.substring(verificacao.getFirstPriority1()+1));
              default:
                throw new FormatException("Operador não Permitido!");
            }
          }
          resultLeft = this.solve(eqCopy.substring(0, verificacao.getFirstPriority1()));
          resultRight = this.solve(eqCopy.substring(verificacao.getFirstPriority1()+1, eqCopy.length()));
          switch (op){
            case '+':
              return resultLeft + resultRight;
            case '-':
              return resultLeft - resultRight;
            default:
              throw new FormatException("Operador não permitido!");
          }
        }else if(verificacao.gotPriority2()){
          if(verificacao.getFirstPriority2() == eqCopy.length() - 1){
            throw new FormatException("Ordenação errada de operador!");
          }
          op = eqCopy.charAt(verificacao.getFirstPriority2());
          resultLeft = this.solve(eqCopy.substring(0, verificacao.getFirstPriority2()));
          resultRight = this.solve(eqCopy.substring(verificacao.getFirstPriority2()+1, eqCopy.length()));
          switch (op){
            case '*':
              return resultLeft * resultRight;
            case '/':
              if(resultRight == 0){
                throw new MathException();
              }
              return resultLeft / resultRight;
            default:
              throw new FormatException("Operador não permitido!");
          }
        }else if(verificacao.gotPriority3()){
          op = eqCopy.charAt(verificacao.getFirstPriority3());
          switch (op){
            case '√':
              if (eqCopy.charAt(verificacao.getFirstPriority3()+1) != '('){
                if(!verificacao.existNumberStartingAt(verificacao.getFirstPriority3()+1)){
                  throw new FormatException("Raíz não delimitada");
                }
                subEquationEnd = verificacao.getNumberEndPosition(verificacao.getFirstPriority3()+1);
                if(subEquationEnd == -1){
                  throw new FormatException("Erro de Formatação!");
                }
                resultLeft = this.solve(eqCopy.substring(verificacao.getFirstPriority3()+1, subEquationEnd+1));
                if(resultLeft < 0){
                  throw new MathException("Raíz de número complexo");
                }
                return Math.sqrt(resultLeft);
              }
              subEquationEnd = verificacao.getParentesisOpenAt(verificacao.getFirstPriority3()+1).getPositionClosed();
              if(subEquationEnd == -1){
                throw new FormatException("Erro de Formatação!");
              }
              resultLeft = this.solve(eqCopy.substring(verificacao.getFirstPriority3()+1,subEquationEnd+1));
              if(resultLeft < 0){
                throw new MathException("Raíz de número complexo");
              }
              return Math.sqrt(resultLeft);
            case '[':
              subEquationEnd = verificacao.getBracketOpenAt(verificacao.getFirstPriority3()).getPositionClosed();
              if(subEquationEnd == -1){
                throw new FormatException("Erro de Formatação!");
              }
              resultLeft = this.solve(eqCopy.substring(verificacao.getFirstPriority3()+1, subEquationEnd));
              return Math.abs(resultLeft);
            case ']':
              throw new FormatException("Chave não pareada!");
            case '^':
              parentesis = verificacao.getParentesisOpenAt(verificacao.getFirstPriority3()+1);
              if(parentesis == null){
                parentesis = verificacao.getBracketOpenAt(verificacao.getFirstPriority3()+1);
                if(parentesis == null){
                  subEquationEnd = verificacao.getNumberEndPosition(verificacao.getFirstPriority3()+1);
                  if(subEquationEnd == -1){
                    if(eqCopy.charAt(verificacao.getFirstPriority3()+1) != '√'){
                      throw new FormatException("Não foi possível achar expoente!");
                    }else{
                      subEquationEnd = verificacao.getInvisibleBracketOpenAt(verificacao.getFirstPriority3()+1);
                    }
                  }
                }else{
                  subEquationEnd = parentesis.getPositionClosed();
                }
              }else{
                subEquationEnd = parentesis.getPositionClosed();
              }
              parentesis = null;
              parentesis = verificacao.getParentesisClosedAt(verificacao.getFirstPriority3()-1);
              if(parentesis == null){
                parentesis = verificacao.getBracketClosedAt(verificacao.getFirstPriority3()-1);
                if(parentesis == null){
                  subEquationBeggining = verificacao.getNumberBegginingPosition(verificacao.getFirstPriority3()-1);
                  if(subEquationBeggining == -1){
                    throw new FormatException("Não foi possível achar base!");
                  }
                }else{
                  subEquationBeggining = parentesis.getPositionOpen();
                }
              }else{
                subEquationBeggining = parentesis.getPositionOpen();
              }
              resultLeft = this.solve(eqCopy.substring(subEquationBeggining, verificacao.getFirstPriority3()));
              resultRight = this.solve(eqCopy.substring(verificacao.getFirstPriority3()+1, subEquationEnd+1));
              return Math.pow(resultLeft, resultRight);
            default:
              throw new FormatException("Erro de Formatação!");
          }
          
        }else if(verificacao.gotPriority4()){
          subEquationBeggining = verificacao.getFirstPriority4();
          op = eqCopy.charAt(subEquationBeggining);
          switch (op){
            case '(':
              subEquationEnd = verificacao.getParentesisOpenAt(subEquationBeggining).getPositionClosed();
              resultLeft = this.solve(eqCopy.substring(subEquationBeggining+1, subEquationEnd));
              return resultLeft;
            case ')':
              throw new FormatException("Parêntese não pareado!");
            default:
              throw new FormatException("Erro de Formatação!");
          }
        }
      }
    }
    throw new ProcessingException();
  }  

  /**
   * Verifica se o caractere c é um caractere de operação
   * 
   * @param c charactere a ser analisado
   * @return boolean com a informação se c é um caractere de operação
   */

  private boolean isOperation(char c){
    if(this.isPriority3(c) || this.isPriority2(c) || this.isPriority1(c)){
      return true;
    }else{
      return false;
    }
  }

  /**
   * Verifica se o caractere c é um caractere de operação de prioridade 4
   * 
   * @param c charactere a ser analisado
   * @return boolean com a informação se c é um caractere de operação de prioridade 4
   * @see Verification
   */
  private boolean isPriority4(char c){
    if(c == '(' || c == ')'){
      return true;
    }else{
      return false;
    }
  }

  /**
   * Verifica se o caractere c é um caractere de operação de prioridade 3
   * 
   * @param c charactere a ser analisado
   * @return boolean com a informação se c é um caractere de operação de prioridade 3
   * @see Verification
   */

  private boolean isPriority3(char c){
    if(c == '|' || c == '√' || c == '^'){
      return true;
    }else{
      return false;
    }
  }

  /**
   * Verifica se o caractere c é um caractere de operação de prioridade 2
   * 
   * @param c charactere a ser analisado
   * @return boolean com a informação se c é um caractere de operação de prioridade 2
   * @see Verification
   */

  private boolean isPriority2(char c){
    if(c=='*' || c == '/'){
      return true;
    }else{
      return false;
    }
  }

  /**
   * Verifica se o caractere c é um caractere de operação de prioridade 1
   * 
   * @param c charactere a ser analisado
   * @return boolean com a informação se c é um caractere de operação de prioridade 1
   * @see Verification
   */

  private boolean isPriority1(char c){
    if(c == '+' || c == '-'){
      return true;
    }else{
      return false;
    }
  }

  /**
   * Verifica se o caractere c é um número
   * @param c caractere a ser analisado
   * @return boolean com a informação se c é um caractere de um número
   */
  
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
      case '.':
        return true;
      default:
        return false;
    }
  }
}