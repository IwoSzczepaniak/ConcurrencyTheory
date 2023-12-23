

public class BCalculator implements Calculator{

    double a;
    double b;

    public BCalculator(double val, double factor) {
        this.a = val;
        this.b = factor;
    }

    public double calc(){
        return a*b;
    }
} 
