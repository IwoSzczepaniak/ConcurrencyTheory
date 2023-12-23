
public class ACalculator implements Calculator{

    double a;
    double b;

    public ACalculator(double first_col, double second_col) {
        this.a = first_col;
        this.b = second_col; 
    }

    public double calc(){
        return a/b;
    }
} 
