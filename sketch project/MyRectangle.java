import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class MyRectangle extends MyShape{
    Rectangle2D rec;

    @Override
    public MyShape paste() {
        MyRectangle mc = new MyRectangle(this.x0 + 100,this.y0 +100,this.x + 100,this.y + 100,this.color);
        mc.rec = new Rectangle2D.Double(mc.x0,mc.y0,mc.x - mc.x0,mc.y - mc.y0);
        return mc;
    }

    public MyRectangle(int x0, int y0, int x, int y, String color){
        super(x0,y0,x,y,color);
        rec = new Rectangle2D.Double(x0,y0,x - x0,y - y0);

    }

    public double getX(){
        return rec.getX();

    }

    public double getY(){
        return rec.getY();
    }

    public double getWidth(){
        return rec.getWidth();
    }

    public double getHeight(){
        return rec.getHeight();
    }

    public boolean contains(int x, int y){
        return this.rec.contains(x,y);
    }
    double preX,preY;
    double count = 0;
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


        this.rec.setRect(this.rec.getX() + dx,this.rec.getY() + dy,this.rec.getWidth(),this.rec.getHeight());
        this.preX = currX;
        this.preY = currY;
    }

    @Override
    public void paint(Graphics g) {
        //System.out.println("x0:"+ x0 + "  y0:" + y0 + "   x:" + x + "   y:" + y);
        g.setColor(new Color(colorValue()));
        g.drawRect((int)this.rec.getX(), (int)this.rec.getY(), (int)this.rec.getWidth(), (int)this.rec.getHeight());

    }
}
