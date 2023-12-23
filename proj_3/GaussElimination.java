import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;



public class GaussElimination {

    int n;
    double[][] augmentedMatrix;
    ArrayList<ArrayList<String>> Orders;
    // double[][] factors;
    Map<String, Double> fDict = new HashMap<>();
    Map<String, Double> bDict = new HashMap<>();



    public GaussElimination(String matrix_filename, String foata_filename) {
        inputMatrix(matrix_filename);
        cacluationsOrder(foata_filename);
    }

    public void showMatrix(){
        for (int i = 0; i < this.n; i++) {
            for (int j = 0; j <=this.n; j++) {
                System.out.print(this.augmentedMatrix[i][j]+"\t");
            }
            System.out.println();
        }
        System.out.println();
    }

    public void inputMatrix(String matrix_filename) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(matrix_filename));
            this.n = Integer.parseInt(reader.readLine());
            this.augmentedMatrix = new double[n][n + 1];
            // System.out.println(n);

            for (int i = 0; i < this.n; i++) {
                String[] rowValues = reader.readLine().split(" ");
                for (int j = 0; j <this.n; j++) {
                    this.augmentedMatrix[i][j] = Double.parseDouble(rowValues[j]);
                }
            }
            String[] rowValues = reader.readLine().split(" ");
            for (int i = 0; i <this.n; i++) {
                    this.augmentedMatrix[i][n] = Double.parseDouble(rowValues[i]);
            }

            
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

   public void cacluationsOrder(String foataFile) {
        try {
            this.Orders = new ArrayList<>();
            BufferedReader reader = new BufferedReader(new FileReader(foataFile));
            int m = Integer.parseInt(reader.readLine());
            for(int i = 0; i< m; i++){
                String[] rowValues = reader.readLine().split(" ");
                ArrayList<String> foataClass = new ArrayList<>();
                for(String el: rowValues){
                    foataClass.add(el);
                }
                Orders.add(foataClass);
            }
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showCalculationsOrder(){
        for(ArrayList<String> foataClass: this.Orders){
            for(String el : foataClass){
                System.out.print(el + "\t");
            }
            System.out.println();
        }
        System.out.println();
    }
 


    
    public void gaussElimination(){
        // Wykonaj eliminację Gaussa
            // for (int k = 0; k < n - 1; k++) {
            //     for (int i = k + 1; i < n; i++) {
            //         ACalculator aCalculator = new ACalculator(augmentedMatrix[i][k], augmentedMatrix[k][k]);
            //         double factor = aCalculator.calc();
            //         for (int j = k; j <= n; j++) {
            //             BCalculator bCalculator = new BCalculator(augmentedMatrix[k][j], factor);
            //             double mult_res = bCalculator.calc();
            //             CCalculator cCalculator = new CCalculator(augmentedMatrix[i][j], mult_res);
            //             augmentedMatrix[i][j] = cCalculator.calc();
            //         }
            //     }
            // }
            Calculator calculator;
            String key;
            for(ArrayList<String> foataClass: this.Orders){
                for(String el : foataClass){

                    char toDo = el.charAt(0);
                    int i = el.charAt(2) - '0';
                    int j = el.charAt(4) - '0';
                    int k = el.charAt(6) - '0';
                    
                    switch (toDo) {
                        case 'A':
                            calculator = new ACalculator(augmentedMatrix[k][i], augmentedMatrix[i][i]);
                            key = el.substring(2, 3) + el.substring(6, 7);
                            fDict.put(key, calculator.calc());
                            break;
                        case 'B':
                            key = el.substring(2, 3) + el.substring(6, 7);
                            calculator = new BCalculator(augmentedMatrix[i][j], fDict.get(key));
                            bDict.put(el.substring(2, 7), calculator.calc()); 
                            break;
                        case 'C':
                            calculator = new CCalculator(augmentedMatrix[k][j], bDict.get(el.substring(2, 7)));
                            augmentedMatrix[k][j] = calculator.calc(); 
                            break;
                        default:
                            throw new IllegalStateException("Unexpected value: " + toDo);
                    };
                    
                }
            }

        return;
    }
    
    public static void main(String[] args) {
        GaussElimination gaussElimination = new GaussElimination("input_.txt", "foata.txt");   
        gaussElimination.showMatrix();
        gaussElimination.showCalculationsOrder();
        gaussElimination.gaussElimination();
        gaussElimination.showMatrix();
    }   
}
