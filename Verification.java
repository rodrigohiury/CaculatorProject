public class Verification{
  private boolean priority4; //Parentesis
  private boolean priority3; //Abs or √ or ^
  private boolean priority2; //* or /
  private boolean priority1; //+ or -
  private boolean valid;
  private int firstPriority1;

  Verification(){
    
  }

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
}