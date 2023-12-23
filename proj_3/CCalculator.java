


public class CCalculator implements Calculator{

    double a;
    double b;

    public CCalculator(double first_col, double second_col) {
        this.a = first_col;
        this.b = second_col;
    }

    public double calc(){
        return a-b;
    }
} 
