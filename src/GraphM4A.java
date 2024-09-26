import java.util.Scanner;

public class GraphM4A {

    private int n;
    private int type; //0 if undirected, 1 if directed
    private int weighted; // 0 if unweighted, 1 otherwise
    private float[][] adjmat;


    public GraphM4A(Scanner sc) {
        String[] firstline = sc.nextLine().split(" ");
        this.n = Integer.parseInt(firstline[0]);
        System.out.println("Number of vertices " + this.n);
        if (firstline[1].equals("undirected"))
            this.type = 0;
        else
            this.type = 1;
        System.out.println("Type= " + this.type);
        if (firstline[2].equals("unweighted"))
            this.weighted = 0;
        else
            this.weighted = 1;
        System.out.println("Weighted= " + this.weighted);

        this.adjmat = new float[this.n][this.n];
        for (int i = 0; i < this.n; i++)
            for (int j = 0; j < this.n; j++)
                adjmat[i][j] = 0; // replace 0 with something else if the weights can be 0

        for (int k = 0; k < this.n; k++) {
            String[] line = sc.nextLine().split(" : ");
            int i = Integer.parseInt(line[0]); //the vertex "source"
            if (weighted == 0) {
                if ((line.length > 1) && (line[1].charAt(0) != ' ')) {
                    String[] successors = line[1].split(", ");
                    System.out.println(i + " " + successors.length);
                    for (int h = 0; h < successors.length; h++) {
                        System.out.println(Integer.parseInt(successors[h]));
                        this.adjmat[i - 1][Integer.parseInt(successors[h]) - 1] = 1;
                    }
                }
            } else {
                line = line[1].split(" // ");
                if ((line.length == 2) && (line[1].charAt(0) != ' ')) {// if there really are some successors, then we must have something different from " " after "// "
                    String[] successors = line[0].split(", ");
                    String[] theirweights = line[1].split(", ");
                    for (int h = 0; h < successors.length; h++)
                        this.adjmat[i - 1][Integer.parseInt(successors[h]) - 1] = Float.parseFloat(theirweights[h]);
                }
            }
        }
    }

    //method to be applied only when type=0
    public int[] degree() {
        int[] tmp = new int[this.n];
        for (int i = 0; i < this.n; i++)
            tmp[i] = 0;
        for (int i = 0; i < this.n; i++)
            for (int j = 0; j < this.n; j++)
                if (this.adjmat[i][j] != 0)
                    tmp[i] = tmp[i] + 1;
        return tmp;
    }

    //method to be applied only when type=1
    public TwoArrays4A degrees() {
        int[] tmp1 = new int[this.n]; //indegrees
        int[] tmp2 = new int[this.n]; //outdegrees
        for (int i = 0; i < this.n; i++) {
            tmp1[i] = 0;
            tmp2[i] = 0;
        }
        for (int i = 0; i < this.n; i++)
            for (int j = 0; j < this.n; j++)
                if (this.adjmat[i][j] != 0) {
                    tmp2[i] = tmp2[i] + 1;
                    tmp1[j] = tmp1[j] + 1;
                }
        return (new TwoArrays4A(tmp1, tmp2));
    }

    //method to be applied only when type=1

    /**
     * TP1 Exercise 1
     * @param outMatrix display the result as an adjacency matrix (or as an adjacency list)
     */
    public void printTransposed(boolean outMatrix) {
        if(outMatrix)
            Tools4A.printMatrix(transposedMM());
        else
            transposedML(); //TODO Tools4A.printAdjList(...);
    }

    /**
     * TP1 Exercise 1
     * Compute the transposed graph, represented by an adjacency matrix
     */
    private float[][] transposedMM() {
        float[][] trans = new float[this.adjmat.length][this.adjmat.length];
        for (int i = 0; i < this.n; i++) {
            for (int j = 0; j < this.n; j++) {
                trans[i][j] = this.adjmat[j][i];
            }
        }
        return trans;
    }
    /**
     * Compute the transposed graph, represented by an adjacency list
     */
    private void transposedML() {
        //TODO
    }




    //TP1
    public int getType() {
        return this.type;
    }

    //TP1
    public float[][] getAdjmat() {
        return adjmat;
    }
}
