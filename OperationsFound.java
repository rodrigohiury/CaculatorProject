public class OperationsFound{
  private boolean priority4; //Parentesis
  private boolean priority3; //Abs or √ or ^
  private boolean priority2; //* or /
  private boolean priority1; //+ or -

  OperationsFound(boolean priority4, boolean priority3, boolean priority2, boolean priority1){
    this.priority4 = priority4;
    this.priority3 = priority3;
    this.priority2 = priority2;
    this.priority1 = priority1;
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
}