import java.awt.*;
import java.awt.geom.Rectangle2D;

public class MyOpenPolygon extends MyShape{
    Polygon polygon;

    @Override
    public MyShape paste() {
        return null;
    }

    public MyOpenPolygon(int x0, int y0, int x, int y, String color){
        super(x0,y0,x,y,color);


        polygon = new Polygon();

    }

    public void add(int x , int y){
        this.polygon.addPoint(x,y);
    }
    @Override
    public void move(double x, double y, double width, double height) {

    }

    @Override
    public boolean contains(int x, int y) {
        return false;
    }

    @Override
    public void paint(Graphics g) {
        g.drawPolygon(this.polygon.xpoints,this.polygon.ypoints, this.polygon.xpoints.length);
    }
}
