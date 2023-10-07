package src.data;

import java.util.ArrayList;
import java.util.List;

import src.exceptions.FormatException;
import src.exceptions.NoNumberException;

/**
 * Classe que guarda todas as informações relevantes de uma dada equação.
 * @author Rodrigo Hiury
 * @version 1.0.0
 */

public class Verification{
  private boolean priority4; //Parentesis
  private boolean priority3; //Abs[] or √ or ^
  private boolean priority2; //* or /
  private boolean priority1; //+ or -
  private boolean valid;
  private int firstPriority1;
  private int firstPriority2;
  private int firstPriority3;
  private int firstPriority4;
  private List<Integer> numbers = new ArrayList<>();
  private List<BracketsPairs> parentesis = new ArrayList<>();
  private List<BracketsPairs> brackets = new ArrayList<>();


  public boolean gotPriority4(){
    return this.priority4;
  }

  public boolean gotPriority3(){
    return this.priority3;
  }

  public boolean gotPriority2(){
    return this.priority2;
  }

  public boolean gotPriority1(){
    return this.priority1;
  }

  public boolean isValid(){
    return this.valid;
  }

  public int getFirstPriority1(){
    return this.firstPriority1;
  }

  public int getFirstPriority2() {
    return firstPriority2;
  }

  public int getFirstPriority3() {
    return firstPriority3;
  }

  public int getFirstPriority4() {
    return firstPriority4;
  }

  public int getNumbersSize(){
    return this.numbers.size();
  }

  public int getNumbersAmount(){
    return this.numbers.size()/2;
  }

  public List<BracketsPairs> getParentesis() {
    return parentesis;
  }

  public BracketsPairs getParentesisOpenAt(int p){
    for (BracketsPairs parentese : this.parentesis) {
      if(parentese.getPositionOpen() == p){
        return parentese;
      }
    }
    return null;
  }

  public BracketsPairs getBracketOpenAt(int p){
    for (BracketsPairs bracket : this.brackets) {
      if(bracket.getPositionOpen() == p){
        return bracket;
      }
    }
    return null;
  }

  public BracketsPairs getParentesisClosedAt(int p){
    for (BracketsPairs parentese : this.parentesis) {
      if(parentese.getPositionClosed() == p){
        return parentese;
      }
    }
    return null;
  }

  public BracketsPairs getBracketClosedAt(int p){
    for (BracketsPairs bracket : this.brackets) {
      if(bracket.getPositionClosed() == p){
        return bracket;
      }
    }
    return null;
  }

  public BracketsPairs getParentesisAt(int p){
    for (BracketsPairs parentese : this.parentesis) {
      if(parentese.getPositionOpen() == p){
        return parentese;
      }else if(parentese.getPositionClosed() == p){
        return parentese;
      }
    }
    return null;
  }

  public BracketsPairs getBracketAt(int p){
    for (BracketsPairs bracket : this.brackets) {
      if(bracket.getPositionOpen() == p){
        return bracket;
      }else if(bracket.getPositionClosed() == p){
        return bracket;
      }
    }
    return null;
  }

  public List<BracketsPairs> getBrackets() {
    return brackets;
  }

  public List<Integer> getNumbers() {
    return numbers;
  }

  public void setParentesis(List<BracketsPairs> parentesis) {
    this.parentesis = parentesis;
  }

  public void setPipes(List<BracketsPairs> brackets) {
    this.brackets = brackets;
  }

  public void setPriority4(boolean value){
    this.priority4 = value;
  }

  public void setPriority3(boolean value){
    this.priority3 = value;
  }

  public void setPriority2(boolean value){
    this.priority2 = value;
  }

  public void setPriority1(boolean value){
    this.priority1 = value;
  }

  public void setValid(boolean value){
    this.valid = value;
  }

  public void setFirstPriority1(int value){
    this.firstPriority1 = value;
  }

  public void setFirstPriority2(int firstPriority2) {
    this.firstPriority2 = firstPriority2;
  }

  public void setFirstPriority3(int firstPriority3) {
    this.firstPriority3 = firstPriority3;
  }

  public void setFirstPriority4(int firstPriority4) {
    this.firstPriority4 = firstPriority4;
  }

  public void addNumber(int b, int e){
    this.numbers.add(b);
    this.numbers.add(e);
  }

  public void addNumberPosition(int p){
    this.numbers.add(p);
  }

  public void removeLastNumber(){
    int lastNumberEnd = this.getNumbersSize()-1;
    int lastNumberBegginning = lastNumberEnd-1;
    this.numbers.remove(lastNumberEnd);
    this.numbers.remove(lastNumberBegginning);
  }

  public void removeNumber(int e){
    int lastNumberEnd = e*2;
    int lastNumberBegginning = lastNumberEnd-1;
    this.numbers.remove(lastNumberEnd);
    this.numbers.remove(lastNumberBegginning);
  }

  public void addParentesisOpen(int p){
    BracketsPairs parentese = new BracketsPairs();
    parentese.setPositionOpen(p);
    this.parentesis.add(parentese);
    if(!this.gotPriority4() && this.noOpenBrackets()){
      this.priority4 = true;
      this.firstPriority4 = p;
    }
  }

  public void addParentesisClosed(int p) throws FormatException{
    boolean added = false;
    for (int i = this.parentesis.size()-1 ; i >= 0 ; i--) {
      if(this.parentesis.get(i).getPositionClosed() == -1){
        this.parentesis.get(i).setPositionClosed(p);
        added = true;
        break;
      }
    }
    if(!added){
      throw new FormatException("Parentese não fechado!");
    }
  }

  public void addBracketOpen(int p){
    BracketsPairs bracket = new BracketsPairs();
    bracket.setPositionOpen(p);
    this.brackets.add(bracket);
    if(!this.gotPriority3() && this.noOpenBrackets()){
      this.priority3 = true;
      this.firstPriority3 = p;
    }
  }

  public void addBracketClosed(int p) throws FormatException{
    boolean added = false;
    for (int i = this.brackets.size()-1 ; i >= 0 ; i--) {
      if(this.brackets.get(i).getPositionClosed() == -1){
        this.brackets.get(i).setPositionClosed(p);
        added = true;
        break;
      }
    }
    if(!added){
      throw new FormatException("Chave não fechada!");
    }
  }

  public boolean noOpenBrackets(){
    for (BracketsPairs parentese : this.parentesis) {
      if(parentese.getPositionClosed() == -1){
        return false;
      }
    }
    for (BracketsPairs bracket : this.brackets) {
      if(bracket.getPositionClosed() == -1){
        return false;
      }
    }
    return true;
  }

  public boolean existNumberStartingAt(int p){
    for (int i = 0 ; i < this.numbers.size() ; i = i + 2) {
      if(this.numbers.get(i) == p){
        return true;
      }
    }
    return false;
  }

  public int getNumberEndPosition(int p) throws NoNumberException{
    if(numbers != null){
      for (int i = 0 ; i < this.numbers.size() ; i = i + 2) {
        if(this.numbers.get(i) == p){
          return this.numbers.get(i+1);
        }
      }
      return -1;
    }else{
      throw new NoNumberException();
    }
    
  }
  
  public int getNumberBegginingPosition(int p) throws NoNumberException{
    if(numbers != null){
      for (int i = 1 ; i < this.numbers.size() ; i = i + 2) {
        if(this.numbers.get(i) == p){
          return this.numbers.get(i-1);
        }
      }
      return -1;
    }else{
      throw new NoNumberException();
    }
    
  }

  public boolean gotAnyPriority(){
    return (this.gotPriority1() || this.gotPriority2() || this.gotPriority3() || this.gotPriority4());
  }
}