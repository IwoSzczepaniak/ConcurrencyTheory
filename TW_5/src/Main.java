public class Main {
    static int n = 5;
    static int m = 5;
    static int[][] tab = new int[n][m];


    public static void printTab(){
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                System.out.printf("%d\t", tab[i][j]);
            }
            System.out.println();
        }
    }

    public static void insertTab(){
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                tab[i][j] = 0;
            }
        }
    }


    public static void main(String[] args) {
        insertTab();

        printTab();

    }
}