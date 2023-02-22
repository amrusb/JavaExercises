import java.util.Arrays;
import java.util.Random;

class TriangleData{
    private boolean isTriangle;
    private int smallestField;
    private double triangleField;

    public TriangleData(){
        isTriangle = false;
        smallestField = -1;
        triangleField = -1.0;
    }
    public TriangleData(boolean is, int num, double f){
        isTriangle = is;
        smallestField = num;
        triangleField = f;
    }

    public boolean hasTriangle() {
        return isTriangle;
    }
    public int numOfTriangle(){
        return smallestField;
    }
    public double fieldOfTriangle(){
        return triangleField;
    }
}

public class ex3_lvl1 {
    /**
     * Rozmiar tablicy n
     */
    static final int N = 4;
    
    public static double heronFormula(int[]sides){
        double p = (sides[0] + sides[1] + sides[2]) / 2.0;
        double field = p*(p-sides[0])*(p-sides[1])*(p-sides[2]);
        field = Math.sqrt(field);
        return field;
    }
    public static TriangleData checkTriangles(int[][] array, int n) {
        int num = 0;
        double minField = Double.MAX_VALUE;
        boolean isTriangle = false;
        for (int i = 0; i < n; i++) {
            int a = array[i][0], b = array[i][1], c = array[i][2];
            if( a+b>c && a+c > b && c+b > a){
                isTriangle = true;
                double field = heronFormula(array[i]);
                if(field < minField){
                     minField = field;
                     num = i;
                }
            }
        }
        var t =  new TriangleData(isTriangle, num, minField);
        return t;
    }
    public static void main(String[] args) {
        int[][] triangle = new int[N][3];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < 3; j++) {
                int num = new Random().nextInt(20) + 1;
                num = Math.abs(num); 
                triangle[i][j] = num;
            }
        }
        int i = 0;
        for (int[] is : triangle) {
            System.out.printf("%-2d:", i++);
            for (int is2 : is) {
                System.out.printf("%3d|", is2);
            }
            System.out.println();
        }
        var temp = checkTriangles(triangle, N);
        if(temp.hasTriangle()){
            int num = temp.numOfTriangle();
            System.out.println("\nTriangle has been found.");
            System.out.println("Triangle with smallest field: " + num);
            System.out.printf("Field = %4.3f\n" , temp.fieldOfTriangle());
            System.out.println("Triangle: " + Arrays.toString(triangle[num]));
        }
        else{
            System.out.println("\nTriangle has not been found.");
        }
    }
}
