import java.awt.*;
import java.awt.geom.Rectangle2D;

public class MySquare extends MyShape{

    Rectangle2D square;

    @Override
    public MyShape paste() {
        MySquare mc = new MySquare(this.x0 + 100,this.y0 + 100,this.x + 100,this.y + 100,this.color);
        mc.square = new Rectangle2D.Double(mc.x0,mc.y0,mc.x - mc.x0,mc.x - mc.x0);
        return mc;
    }

    public MySquare(int x0, int y0, int x, int y, String color){
        super(x0,y0,x,y,color);


        square = new Rectangle2D.Double(x0,y0,x - x0,x - x0);

    }

    public double getWidth(){
        return square.getWidth();
    }

    public double getHeight(){
        return square.getHeight();
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
        this.square.setRect(this.square.getX() + dx,this.square.getY() + dy,this.square.getWidth(),this.square.getHeight());
        this.preX = currX;
        this.preY = currY;
    }

    @Override
    public boolean contains(int x, int y) {
        return this.square.contains(x,y);
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(new Color(colorValue()));
        g.drawRect((int)this.square.getX(), (int)this.square.getY(), (int)this.square.getWidth(), (int)this.square.getWidth());
    }
}
