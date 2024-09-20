
public class Tools4A {

    public static void printArray(int[] tab) {
        for (int i = 0; i < tab.length; i++) {
            System.out.print(" " + tab[i]);
        }
        System.out.println();
    }


    public static void printMatrix(float[][] mat) {
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                System.out.print(mat[i][j] + " ");
            }
            System.out.println();
        }
    }
}
