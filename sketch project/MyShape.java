import java.awt.*;
public abstract class MyShape{
    //String shapeName;
    int x0,y0,x,y;
    String color;
    int groupId = -1;

    public MyShape(){

    }
    public abstract MyShape paste();
    public MyShape(int x0, int y0, int x, int y,String color){
        //this.shapeName = shapeName;
        this.x0 = x0;
        this.y0 = y0;
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public void setGroupId(int id){
        this.groupId = id;
    }

    public int colorValue(){
        if(this.color == "red"){
            return 167729999;
        }else if(this.color == "black"){
            return 0;
        }else if(this.color == "blue"){
            return 255;
        }else{
            return 1223456;
        }
    }

    public void show(){
        System.out.println("x0:"+ x0 + "  y0:" + y0 + "   x:" + x + "   y:" + y);
    }

    public abstract void move(double x, double y, double width,double height);

    public abstract boolean contains(int x, int y);

    public abstract void paint(Graphics g);
    //if(shapeName.equals("rectangle")){
       // g.drawRect(x0, y0, x - x0 + 1, y - y0 + 1);
}
