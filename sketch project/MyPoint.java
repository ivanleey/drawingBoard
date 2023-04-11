public class MyPoint<K> {
    K x;
    K y;
    public MyPoint(K x, K y){
        this.x = x;
        this.y = y;
    }

    public K getX(){
        return x;
    }

    public K getY(){
        return y;
    }

}
