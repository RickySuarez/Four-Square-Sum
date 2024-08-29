import java.util.HashMap;
import java.util.ArrayList;

class square {

    // Dynamic programming approach to compute the minimum number of squares that sum up to a given integer n
    public static ArrayList<Integer> SquareDollars(int n) {
        // Initialize an array G to store the minimum number of squares needed for each integer up to n
        int[] G = new int[n + 1];
        // HashMap to store the list of squares that sum up to each integer up to n
        HashMap<Integer, ArrayList<Integer>> pre = new HashMap<>();
        // Initialize an ArrayList to store the result for 0, which is just [0]
        ArrayList<Integer> first = new ArrayList<>();
        first.add(0);
        pre.put(0, first);
        // The minimum number of squares needed to sum up to 0 is 0
        G[0] = 0;

        // Loop through each integer from 1 to n
        for (int i = 1; i <= n; i++) {
            int min = Integer.MAX_VALUE; // Start with a large number to find the minimum
            // Check all possible squares less than or equal to i
            for (int k = 1; k * k <= i; k++) {
                int c = i / (k * k); // Number of squares of size k*k
                int r = i % (k * k); // Remaining value after using c squares of size k*k
                // If using c squares of size k*k is better than the current min, update min and pre
                if (min > c + G[r]) {
                    ArrayList<Integer> list1 = new ArrayList<>();
                    ArrayList<Integer> list2 = pre.get(r); // List of squares summing up to remaining value r
                    // Add c squares of size k*k to list1
                    for (int j = 0; j < c; j++) {
                        list1.add(k * k);
                    }
                    // Add squares from list2 to list1
                    list1.addAll(list2);
                    // Update the HashMap with the new list for the current integer i
                    pre.remove(i);
                    pre.put(i, list1);
                    // Update the minimum number of squares needed for i
                    min = c + G[r];
                }
            }
            // Store the minimum number of squares needed for i
            G[i] = min;
        }

        // Retrieve the result list of squares for the integer n
        ArrayList<Integer> result = pre.get(n);
        // If there are more than 4 squares, remove the last one
        if (result.size() > 4) {
            result.remove(result.size() - 1);
        }
        // Ensure the result list has exactly 4 elements by padding with 0s if necessary
        while (result.size() < 4) {
            result.add(0);
        }
        return result; // Return the list of squares
    }

    public static void main(String[] args) {
        //Convert string to int.
        int i = Integer.parseInt(args[0]);
        // Print the result of SquareDollars method for the given integer
        System.out.println("The four squares that add up to " + i + ":");
        System.out.print(SquareDollars(i));
    }
}
