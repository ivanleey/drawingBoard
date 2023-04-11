import java.awt.*;
import java.awt.geom.Ellipse2D;

public class MyCircle extends MyShape{
    Ellipse2D circle;

    @Override
    public MyShape paste() {
        MyCircle mc = new MyCircle(this.x0 + 100,this.y0 + 100,this.x + 100,this.y + 100,this.color);
        mc.circle = new Ellipse2D.Double(mc.x0,mc.y0,mc.x - mc.x0,mc.x - mc.x0);
        return mc;
    }

    public MyCircle(int x0, int y0, int x, int y, String color){
        super(x0,y0,x,y,color);
        this.circle = new Ellipse2D.Double(x0,y0,x - x0,x - x0);

    }
    double preX,preY;
    double count = 0;

    @Override
    public void move(double x, double y, double width,double height){
        if(this.count == 0){
            this.preX = x;
            this.preY = y;
        }
        count++;

        double currX = x;
        double currY = y;
        double dx = currX - preX;
        double dy = currY - preY;
        this.circle.setFrame(this.circle.getX() + dx,this.circle.getY() + dy,this.circle.getWidth(),this.circle.getHeight());
        this.preX = currX;
        this.preY = currY;

        //this.circle.setFrame(x,y,width,height);
    }



    public double getWidth(){
        return circle.getWidth();
    }

    public double getHeight(){
        return circle.getHeight();
    }


    @Override
    public boolean contains(int x, int y) {
        return this.circle.contains(x,y);
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(new Color(colorValue()));
        g.drawOval((int)this.circle.getX(), (int)this.circle.getY(), (int)this.circle.getWidth(), (int)this.circle.getWidth());
    }
}
