import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;



public class GaussElimination {

    int n;
    double[][] augmentedMatrix;
    private ArrayList<ArrayList<String>> Orders;
    private Map<String, Double> factorDict = new HashMap<>();
    private Map<String, Double> bResDict = new HashMap<>();
    private Lock factorDictLock = new ReentrantLock();
    private Lock bResDictLock = new ReentrantLock();
    private Lock[][] augmentedMatrixLocks;



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
            this.augmentedMatrixLocks = new ReentrantLock[n][n + 1];

            for (int i = 0; i < this.n; i++) {
                String[] rowValues = reader.readLine().split(" ");
                for (int j = 0; j <this.n; j++) {
                    this.augmentedMatrix[i][j] = Double.parseDouble(rowValues[j]);
                    this.augmentedMatrixLocks[i][j] = new ReentrantLock();
                }
            }
            String[] rowValues = reader.readLine().split(" ");
            for (int i = 0; i <this.n; i++) {
                    this.augmentedMatrix[i][n] = Double.parseDouble(rowValues[i]);
                    this.augmentedMatrixLocks[i][n] = new ReentrantLock();
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
        
        for(ArrayList<String> foataClass: this.Orders){   
            ExecutorService executor = Executors.newCachedThreadPool();         
            try{   
                for(String el : foataClass){
                    executor.submit(() -> {
                        try{
                            char toDo = el.charAt(0);
                            int i = el.charAt(2) - '0';
                            int j = el.charAt(4) - '0';
                            int k = el.charAt(6) - '0';
                            
                            Calculator calculator;
                            String factorKey;

                            switch (toDo) {
                                case 'A':
                                    factorKey = el.substring(2, 3) + el.substring(6, 7);
                                    
                                    augmentedMatrixLocks[k][i].lock();
                                    augmentedMatrixLocks[i][i].lock();
                                    try{
                                        calculator = new ACalculator(augmentedMatrix[k][i], augmentedMatrix[i][i]);
                                    }finally{
                                        augmentedMatrixLocks[i][i].unlock();
                                        augmentedMatrixLocks[k][i].unlock();
                                    }
                                    factorDictLock.lock();
                                    try{
                                        factorDict.put(factorKey, calculator.calc());
                                    }finally{
                                        factorDictLock.unlock();
                                    }
                                    break;
                                case 'B':
                                    factorKey = el.substring(2, 3) + el.substring(6, 7);
                                    augmentedMatrixLocks[i][j].lock();
                                    factorDictLock.lock();
                                    try{
                                        calculator = new BCalculator(augmentedMatrix[i][j], factorDict.get(factorKey));
                                    }finally{
                                        factorDictLock.unlock();
                                        augmentedMatrixLocks[i][j].unlock();
                                    }
                                    bResDictLock.lock();
                                    try{
                                        bResDict.put(el.substring(2, 7), calculator.calc());
                                    }finally{
                                        bResDictLock.unlock();
                                    }
                                    break;
                                case 'C':
                                    augmentedMatrixLocks[k][j].lock();
                                    bResDictLock.lock();
                                    try{
                                        calculator = new CCalculator(augmentedMatrix[k][j], bResDict.get(el.substring(2, 7)));
                                    }finally{
                                        bResDictLock.unlock();
                                        augmentedMatrixLocks[k][j].unlock();
                                    }
                                    
                                    augmentedMatrixLocks[k][j].lock();
                                    try{
                                        augmentedMatrix[k][j] = calculator.calc();
                                    }finally{
                                        augmentedMatrixLocks[k][j].unlock();
                                    }
                                    break;
                                default:
                                    throw new IllegalStateException("Unexpected value: " + toDo);
                            };
                        }catch(Exception e){
                            e.printStackTrace();
                        }

                    });
                }
            }finally{
                executor.shutdown();
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
