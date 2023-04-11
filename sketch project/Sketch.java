import javax.security.auth.callback.ChoiceCallback;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class Sketch extends Frame {
    List<MyShape> savePre;
    Choice modes;
    Choice colors;
    Choice mcp;
    Choice group;
    // String[] modes = { "freehand", "straight lines", "rectangles", "ellipses", "squares", "circles", "open polygons",
    //   "close polygons" };
    int pointer;
    public boolean action(Event evt, Object what) {


        String inputString = (String) what;
        if(inputString.equals("Undo") && pointer >= 0){
            pointer--;
            repaint();
        }else if(inputString.equals("Redo")){
            pointer++;
            repaint();
        }

        return true;
    }

    public Sketch() {
        setSize(1000, 800);
        setLayout(new FlowLayout());
        addMouseListener(new myMouseHandler());
        addMouseMotionListener(new myMouseMotionHandler());


        modes = new Choice();
        modes.add("freehand");
        modes.add("straight lines");
        modes.add("rectangles");
        modes.add("ellipses");
        modes.add("squares");
        modes.add("circles");
        modes.add("open polygons");
        modes.add("close polygons");


        add(modes);

        colors = new Choice();
        colors.add("black");
        colors.add("red");
        colors.add("blue");
        colors.add("green");
        add(colors);
        mcp = new Choice();
        mcp.add("operation");
        mcp.add("move");
        mcp.add("delete");
        mcp.add("paste");
        add(mcp);
        group = new Choice();
        group.add("group/ungroup");
        group.add("group");
        group.add("ungroup");
        add(group);
        add(new Button("Undo"));
        add(new Button("Redo"));



        savePre = new ArrayList<>();
    }



    int x0, y0, x, y;
    MyOpenPolygon op;
    public class myMouseHandler extends MouseAdapter {

//        List<MyShape> selectList = null;
        MyShape sel = null;
        public void mousePressed(MouseEvent e) {

            if(selectMode().equals("freehand")&& !selectMCP().equals("move") && !selectMCP().equals("delete")&&!selectMCP().equals("paste")){
                x0 = e.getX();
                y0 = e.getY();
                mpList = new ArrayList<>();
                mpList.add(new MyPoint<Integer>(x0,y0));
                FreeHand fh = new FreeHand(x0,y0,x,y,mpList,selectColor());
                repaint();

            }else if(selectMode().equals("open polygons")&& !selectMCP().equals("move")){
                x0 = e.getX();
                y0 = e.getY();
                op = new MyOpenPolygon(x0,y0,x,y,selectColor());
                op.add(x0,y0);
                repaint();
            }else if(selectMCP().equals("delete")){

                x0 = e.getX();
                y0 = e.getY();
                MyShape sel = selected(x0,y0);
                List<MyShape> mm = findSameId(sel.groupId);
                System.out.println(mm.size());
                if(mm.size() > 0) {
                    for (int i = 0; i < mm.size(); i++) {
                        savePre.remove(mm.get(i));
                    }
                }else{
                    savePre.remove(sel);
                }


                repaint();
            }else if(selectMCP().equals("paste") && e.getClickCount() == 2){
                x0 = e.getX();
                y0 = e.getY();
                MyShape sel = selected(x0,y0);
                List<MyShape> mm = findSameId(sel.groupId);
                if(mm.size() > 0) {
                    for (int i = 0; i < mm.size(); i++) {
                        savePre.add(mm.get(i).paste());
                    }
                }else{
                    savePre.add(sel.paste());
                }


                repaint();
            }else if(selectGroup().equals("group") && e.getClickCount() == 2){
                //System.out.println(savePre.size());
                x0 = e.getX();
                y0 = e.getY();
                MyShape sel = selected(x0,y0);

                sel.setGroupId(1);


            }else if(selectGroup().equals("ungroup")){
                for(MyShape mm : savePre){
                    mm.setGroupId(-1);
                }
            }
            x0 = e.getX();
            y0 = e.getY();
            //System.out.println(savePre.get(0).contains(x0,y0));
            //System.out.println(findComponentAt(x0,y0));

        }

        public void mouseReleased(MouseEvent e) {
            // Shape shape = new Shape("rectangle", x0, y0, x, y);
            if(selectMCP().equals("operation") && selectGroup().equals("group/ungroup")) {
                savePre.add(myShape);
                pointer = savePre.size();
                System.out.println(pointer);
                myShape = null;
                mpList = null;
            }
            //System.out.println(savePre.size());


        }
    }
    List<MyPoint<Integer>> mpList = new ArrayList<>();
    MyShape sel;
    public class myMouseMotionHandler extends MouseMotionAdapter {
        public void mouseMoved(MouseEvent e) {

        }

        public void mouseDragged(MouseEvent e) throws NullPointerException{

            try {
                if(selectMode().equals("freehand") && !selectMCP().equals("move")&& !selectMCP().equals("delete")&&!selectMCP().equals("paste")){
                    x = e.getX();
                    y = e.getY();
                    MyPoint<Integer> mp = new MyPoint<>(x,y);
                    mpList.add(mp);
                }
                x = e.getX();
                y = e.getY();
                MyShape n;
                if(selectMCP().equals("move")) {
                    MyShape sel = selected(x, y);
                    List<MyShape> mm = findSameId(sel.groupId);


                    if(mm.size() > 0) {

                        for (int i = 0; i < mm.size(); i++) {
                            mm.get(i).move(x, y, x, y);
                        }
                    }else{

                        sel.move(x,y,x,y);
                    }
                }
//

                repaint();
            }catch (Exception ee){

            }
        }
    }

    public MyShape selected(int x, int y) {



        MyShape result = null;
        for (MyShape m : savePre) {

            if (m.contains(x, y)) {
                result = m;
            }
        }

        return result;
    }

    public List<MyShape> findSameId(int id){
//        int count = 0;
//        for(MyShape m : savePre){
//            if (m.groupId == 1){
//                count++;
//
//            }
//        }
//        System.out.println("count is" + count);
        List<MyShape> l = new ArrayList<>();

        for(int i = 0;i<savePre.size();i++){
            if(savePre.get(i).groupId == id && savePre.get(i).groupId != -1){


                l.add(savePre.get(i));


            }
        }

        return l;
    }


    public String selectMode() {
        return modes.getSelectedItem();
    }

    public String selectGroup(){
        return group.getSelectedItem();
    }

    public String selectColor(){
        return colors.getSelectedItem();
    }

    public String selectMCP(){
        return mcp.getSelectedItem();
    }

    MyShape myShape;

    public void paint(Graphics g) {
        System.out.println(pointer);
        // g.drawLine(x0,y0,x,y);
       for(MyShape m : savePre){
           m.paint(g);
       }

        if(selectMCP().equals("operation")) {
            if (selectMode().equals("rectangles")) {
                myShape = new MyRectangle(x0, y0, x, y, selectColor());
                myShape.paint(g);

            } else if (selectMode().equals("circles")) {
                myShape = new MyCircle(x0, y0, x, y, selectColor());
                myShape.paint(g);
            } else if (selectMode().equals("straight lines")) {
                myShape = new MyStraightLines(x0, y0, x, y, selectColor());
                myShape.paint(g);
            } else if (selectMode().equals("ellipses")) {
                myShape = new MyEllipse(x0, y0, x, y, selectColor());
                myShape.paint(g);
            } else if (selectMode().equals("squares")) {
                myShape = new MySquare(x0, y0, x, y, selectColor());
                myShape.paint(g);
            } else if (selectMode().equals("freehand")) {
                FreeHand fh = new FreeHand(x0, y0, x, y, mpList, selectColor());
                myShape = fh;

                fh.paint(g);

            } else if (selectMode().equals("open polygons") || selectMode().equals("close polygons")) {
                myShape = this.op;
                myShape.paint(g);
            }
        }
    }

        public static void main (String[] args){
            new Sketch().setVisible(true);
        }
    }

