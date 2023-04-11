import java.awt.*;
import java.awt.geom.Ellipse2D;

public class MyEllipse extends MyShape{

    Ellipse2D ellipse;

    @Override
    public MyShape paste() {
        MyEllipse mc = new MyEllipse(this.x0 + 100,this.y0 + 100,this.x + 100,this.y + 100,this.color);

        mc.ellipse = new Ellipse2D.Double(mc.x0,mc.y0,mc.x - mc.x0,mc.y - mc.y0);
        return mc;
    }

    public MyEllipse(int x0, int y0, int x, int y, String color){
        super(x0,y0,x,y,color);
        this.ellipse = new Ellipse2D.Double(x0,y0,x - x0,y - y0);

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
        this.ellipse.setFrame(this.ellipse.getX() + dx,this.ellipse.getY() + dy,this.ellipse.getWidth(),this.ellipse.getHeight());
        this.preX = currX;
        this.preY = currY;
    }

    public double getWidth(){
        return ellipse.getWidth();
    }

    public double getHeight(){
        return ellipse.getHeight();
    }

    @Override
    public boolean contains(int x, int y) {
        return this.ellipse.contains(x,y);
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(new Color(colorValue()));
        g.drawOval((int)this.ellipse.getX(), (int)this.ellipse.getY(), (int)this.ellipse.getWidth(), (int)this.ellipse.getHeight());
    }
}
