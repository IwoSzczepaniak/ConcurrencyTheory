import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



public class GaussElimination {

    int n;
    double[][] augmentedMatrix;
    ArrayList<ArrayList<String>> Orders;

    public GaussElimination(String matrix_filename) {
        inputMatrix(matrix_filename);
    }

    public void showMatrix(){
        for (int i = 0; i < this.n; i++) {
            for (int j = 0; j <=this.n; j++) {
                System.out.println(this.augmentedMatrix[i][j]);
            }
            System.out.println();
        }
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
                System.out.println(el);
            }
            System.out.println();
        }
    }
 


    
    public void calculate(){
        // // Wykonaj eliminację Gaussa
            // for (int k = 0; k < n - 1; k++) {
            //     for (int i = k + 1; i < n; i++) {
            //         double factor = augmentedMatrix[i][k] / augmentedMatrix[k][k];
            //         for (int j = k; j <= n; j++) {
            //             augmentedMatrix[i][j] -= factor * augmentedMatrix[k][j];
            //         }
            //     }
            // }

            // // Wykonaj substytucję wsteczną
            // double[] solution = new double[n];
            // for (int i = n - 1; i >= 0; i--) {
            //     solution[i] = augmentedMatrix[i][n];
            //     for (int j = i + 1; j < n; j++) {
            //         solution[i] -= augmentedMatrix[i][j] * solution[j];
            //     }
            //     solution[i] /= augmentedMatrix[i][i];
            // }

        return;
    }

    
    public static void main(String[] args) {
        GaussElimination gaussElimination = new GaussElimination("input.txt");   
        // gaussElimination.showMatrix();
        gaussElimination.cacluationsOrder("foata.txt");
        // gaussElimination.showCalculationsOrder();
    }   
}
