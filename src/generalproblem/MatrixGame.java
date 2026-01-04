package generalproblem;
import java.util.* ;
import java.io.*; 
import java.util.ArrayList;

public class MatrixGame {

	public static boolean matrixGame(ArrayList<ArrayList<Integer>> arr) {

		// Write your code here
		int n=arr.size();
		ArrayList<ArrayList<Integer>> prod=new ArrayList<>();
		for(int i=0 ;i<n; i++){
			prod.add(new ArrayList<>());
			for(int j=0; j<n; j++){
				int sum=0; 
				for(int k=0; k<n; k++){
					sum+=arr.get(i).get(k)*arr.get(k).get(j);
				}

				prod.get(i).add(sum);
			}
		}

		// step 2 compare product with orignal arr
		for(int i=0 ; i<n; i++){
			
			for(int j=0; j<n; j++){
			
			if(!prod.get(i).get(j).equals(arr.get(i).get(j)))
			return false;

			}
		}

		return true;



		
	}
	
	// Helper method to create a matrix
    public static ArrayList<ArrayList<Integer>> matrix(int[][] data) {
        ArrayList<ArrayList<Integer>> matrix = new ArrayList<>();
        for (int[] row : data) {
            ArrayList<Integer> list = new ArrayList<>();
            for (int val : row) {
                list.add(val);
            }
            matrix.add(list);
        }
        return matrix;
    }
	
    public static void main(String[] args) {
        // Test case 1: Idempotent matrix
        int[][] data1 = {
            {1, 0},
            {0, 0}
        };

        // Test case 2: Idempotent matrix
        int[][] data2 = {
            {2, -2},
            {-2, 2}
        };

        // Test case 3: Non-idempotent matrix
        int[][] data3 = {
            {1, 2},
            {3, 4}
        };

        // Test case 4: Identity matrix (always idempotent)
        int[][] data4 = {
            {1, 0, 0},
            {0, 1, 0},
            {0, 0, 1}
        };

        ArrayList<ArrayList<Integer>> m1 = matrix(data1);
        ArrayList<ArrayList<Integer>> m2 = matrix(data2);
        ArrayList<ArrayList<Integer>> m3 = matrix(data3);
        ArrayList<ArrayList<Integer>> m4 = matrix(data4);

        System.out.println("Test 1: " + (matrixGame(m1) ? "Idempotent" : "Not Idempotent"));
        System.out.println("Test 2: " + (matrixGame(m2) ? "Idempotent" : "Not Idempotent"));
        System.out.println("Test 3: " + (matrixGame(m3) ? "Idempotent" : "Not Idempotent"));
        System.out.println("Test 4: " + (matrixGame(m4) ? "Idempotent" : "Not Idempotent"));
    }
}