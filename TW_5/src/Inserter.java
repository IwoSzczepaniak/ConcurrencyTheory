public class Inserter implements Runnable {

    static int[][] tab;
    int x_pos;
    int y_pos;

    public Inserter(int x_pos, int y_pos, int[][] tab){
        this.tab = tab;
        this.x_pos = x_pos;
        this.y_pos = y_pos;
    }

    @Override
    public void run() {

    }
}
