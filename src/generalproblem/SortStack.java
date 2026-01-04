package generalproblem;

import java.util.Stack;

public class SortStack {

    // Main function to sort the stack
    public static void sortStack(Stack<Integer> stack) {
        // Base case
        if (stack.isEmpty()) {
            return;
        }

        // Step 1: Remove the top element
        int top = stack.pop();

        // Step 2: Sort the remaining stack recursively
        sortStack(stack);

        // Step 3: Insert the popped element back in sorted position
        insertInSortedOrder(stack, top);
    }

    // Helper to insert an element into a sorted stack (descending order)
    private static void insertInSortedOrder(Stack<Integer> stack, int element) {
        // Base case: stack is empty or top of stack is smaller or equal
        if (stack.isEmpty() || element <= stack.peek()) {
            stack.push(element);
            return;
        }

        // Pop the top and insert recursively
        int top = stack.pop();
        insertInSortedOrder(stack, element);
        stack.push(top);
    }

    // Test the sorting
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(2);
        stack.push(9);
        stack.push(1);
        stack.push(7);
        stack.push(3);

        System.out.println("Original Stack: " + stack);
        sortStack(stack);
        System.out.println("Sorted Stack (Descending): " + stack);
    }
}
