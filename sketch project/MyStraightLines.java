import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

public class MyStraightLines extends MyShape{
    Line2D line;

    @Override
    public MyShape paste() {
        MyStraightLines mc = new MyStraightLines(this.x0 + 100,this.y0 + 100,this.x + 100,this.y + 100,this.color);
        mc.line = new Line2D.Double(mc.x0,mc.y0,mc.x,mc.y);
        return mc;
    }

    public MyStraightLines(int x0, int y0, int x, int y, String color){
        super(x0,y0,x,y,color);
        this.line = new Line2D.Double(x0,y0,x,y);
    }
    double preX,preY;
    double count = 0;
    @Override
    public void move(double x, double y, double width,double height) {

        if(this.count == 0){
            this.preX = x;
            this.preY = y;
        }
        count++;
        double currX = x;
        double currY = y;
        double dx = currX - preX;
        double dy = currY - preY;
        this.line.setLine(this.line.getX1() + dx,this.line.getY1() + dy,this.line.getX2() + dx,this.line.getY2() + dy);
        this.preX = currX;
        this.preY = currY;
    }

    public double getX1(){
        return this.line.getX1();
    }

    public double getX2(){
        return this.line.getX2();
    }
    public double getY1(){
        return this.line.getY1();
    }
    public double getY2(){
        return this.line.getY2();
    }


    @Override
    public boolean contains(int x, int y) {
        Rectangle temp = this.line.getBounds();
        if(temp.contains(x,y)){
            return true;
        }else{
            return false;
        }


    }

    @Override
    public void paint(Graphics g) {
        g.setColor(new Color(colorValue()));
        g.drawLine((int)this.line.getX1(), (int)this.line.getY1(), (int)this.line.getX2(), (int)this.line.getY2());
    }
}
