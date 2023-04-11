import java.awt.*;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;

public class FreeHand extends MyShape{
    List<MyPoint<Integer>> pointList;


    public FreeHand(int x0, int y0, int x, int y,List<MyPoint<Integer>> pointList,String color){
        super(x0,y0,x,y,color);
//        MyPoint<Integer> mp = new MyPoint<>(x0,y0);
//        pointList.add(mp);

        this.pointList = pointList;


        //this.line = new Line2D.Double(x0,y0,x,y);
    }

    public MyShape paste(){
        List<MyPoint<Integer>> npl = new ArrayList<>();
        for(MyPoint<Integer> pp: this.pointList){
            npl.add(new MyPoint<Integer>(pp.x + 100,pp.y + 100));
        }



        return new FreeHand(this.x0 + 100,this.y0 + 100,this.x + 100,this.y + 100,npl,super.color);

    }
    double preX,preY;
    double count = 0;
    @Override
    public void move(double x, double y, double width, double height) {
        if(this.count == 0){
            this.preX = x;
            this.preY = y;
        }
        count++;

        double currX = x;
        double currY = y;
        double dx = currX - preX;
        double dy = currY - preY;


        for(MyPoint<Integer> mp : pointList){
            mp.x = mp.x + (int) dx;
            mp.y = mp.y + (int) dy;

        }
        this.preX = currX;
        this.preY = currY;
    }

    @Override
    public boolean contains(int x, int y) {
        boolean result = false;
        for(MyPoint<Integer> mp : pointList){
            if((mp.x <= x + 20 && mp.x >= x - 20) && (mp.y <= y + 20 && mp.y >= y - 20)){
                result = true;

            }
            continue;
        }
        return result;
    }

    @Override
    public void paint(Graphics g) {
        //System.out.println(pointList.size());
        g.setColor(new Color(colorValue()));

                for (int i = 0; i < pointList.size() - 1; i++) {
                    g.drawLine(pointList.get(i).getX(), pointList.get(i).getY(), pointList.get(i + 1).getX(), pointList.get(i + 1).getY());

                }


    }
}
