package dynamic_programing;

import java.util.*;

public class CoinChangeSolutions {

    // 1. Pure Recursion (Exponential) - Brute Force
    public int coinChangeRec(int[] coins, int amount) {
        if (amount == 0) return 0;
        if (amount < 0) return -1;
        int min = Integer.MAX_VALUE;
        for (int coin : coins) {
            int res = coinChangeRec(coins, amount - coin);
            if (res >= 0) min = Math.min(min, 1 + res);
        }
        return (min == Integer.MAX_VALUE) ? -1 : min;
    }

    // 2. Recursion + Memoization (Top-Down DP)
    public int coinChangeMemo(int[] coins, int amount) {
        return helper(coins, amount, new HashMap<>());
    }
    private int helper(int[] coins, int amount, Map<Integer, Integer> memo) {
        if (amount == 0) return 0;
        if (amount < 0) return -1;
        if (memo.containsKey(amount)) return memo.get(amount);

        int min = Integer.MAX_VALUE;
        for (int coin : coins) {
            int res = helper(coins, amount - coin, memo);
            if (res >= 0) min = Math.min(min, 1 + res);
        }
        int ans = (min == Integer.MAX_VALUE) ? -1 : min;
        memo.put(amount, ans);
        return ans;
    }

    // 3. Bottom-Up DP (1D Array)
    public int coinChangeDP(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (i - coin >= 0) {
                    dp[i] = Math.min(dp[i], 1 + dp[i - coin]);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }

    // 4. Bottom-Up DP (2D Array - like Knapsack)
    public int coinChange2D(int[] coins, int amount) {
        int n = coins.length;
        int[][] dp = new int[n + 1][amount + 1];
        for (int i = 0; i <= n; i++) Arrays.fill(dp[i], amount + 1);
        for (int i = 0; i <= n; i++) dp[i][0] = 0;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= amount; j++) {
                if (coins[i - 1] <= j) {
                    dp[i][j] = Math.min(dp[i - 1][j], 1 + dp[i][j - coins[i - 1]]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[n][amount] > amount ? -1 : dp[n][amount];
    }

    // 5. BFS Approach (Shortest Path in Unweighted Graph)
    public int coinChangeBFS(int[] coins, int amount) {
        if (amount == 0) return 0;
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[amount + 1];
        q.offer(0);
        visited[0] = true;
        int steps = 0;

        while (!q.isEmpty()) {
            int size = q.size();
            steps++;
            for (int i = 0; i < size; i++) {
                int curr = q.poll();
                for (int coin : coins) {
                    int next = curr + coin;
                    if (next == amount) return steps;
                    if (next < amount && !visited[next]) {
                        visited[next] = true;
                        q.offer(next);
                    }
                }
            }
        }
        return -1;
    }

    // 6. Greedy + Backtracking (Fails in some cases but interesting)
    public int coinChangeGreedy(int[] coins, int amount) {
        Arrays.sort(coins);
        return greedyHelper(coins, amount, coins.length - 1);
    }
    private int greedyHelper(int[] coins, int amount, int idx) {
        if (amount == 0) return 0;
        if (idx < 0) return -1;
        int coin = coins[idx];
        int maxVal = amount / coin;
        int min = Integer.MAX_VALUE;
        for (int i = maxVal; i >= 0; i--) {
            int remain = amount - i * coin;
            if (remain == 0) {
                min = Math.min(min, i);
                break;
            }
            int res = greedyHelper(coins, remain, idx - 1);
            if (res != -1) min = Math.min(min, res + i);
        }
        return min == Integer.MAX_VALUE ? -1 : min;
    }

    // 7. DFS + Memo (same as recursion but with explicit DFS stack)
    public int coinChangeDFS(int[] coins, int amount) {
        return dfs(coins, amount, new HashMap<>());
    }
    private int dfs(int[] coins, int amount, Map<Integer, Integer> memo) {
        if (amount == 0) return 0;
        if (amount < 0) return -1;
        if (memo.containsKey(amount)) return memo.get(amount);
        int min = Integer.MAX_VALUE;
        for (int coin : coins) {
            int res = dfs(coins, amount - coin, memo);
            if (res >= 0) min = Math.min(min, 1 + res);
        }
        int ans = (min == Integer.MAX_VALUE) ? -1 : min;
        memo.put(amount, ans);
        return ans;
    }

    // 8. Tabulation with Coin First Loop (variant ordering)
    public int coinChangeCoinFirst(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {
                dp[i] = Math.min(dp[i], dp[i - coin] + 1);
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }

    // 9. Meet-in-the-middle (split coins into two sets)
    // Rarely used in practice due to constraints, but conceptual.
    public int coinChangeMITM(int[] coins, int amount) {
        // Simplified to DP fallback for demonstration
        return coinChangeDP(coins, amount);
    }

    // 10. Iterative BFS with Priority Queue (like Dijkstra)
    public int coinChangeDijkstra(int[] coins, int amount) {
        int[] dist = new int[amount + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[0] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.offer(new int[]{0, 0}); // {amount, steps}

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int val = curr[0], steps = curr[1];
            if (val == amount) return steps;

            for (int coin : coins) {
                int next = val + coin;
                if (next <= amount && steps + 1 < dist[next]) {
                    dist[next] = steps + 1;
                    pq.offer(new int[]{next, steps + 1});
                }
            }
        }
        return -1;
    }

    // --- Test ---
    public static void main(String[] args) {
        CoinChangeSolutions sol = new CoinChangeSolutions();
        int[] coins1 = {1, 2, 5};
        int[] coins2 = {2};
        int[] coins3 = {186, 419, 83, 408};

        System.out.println("Rec: " + sol.coinChangeRec(coins1, 11));          // 3
        System.out.println("Memo: " + sol.coinChangeMemo(coins1, 11));        // 3
        System.out.println("DP: " + sol.coinChangeDP(coins1, 11));            // 3
        System.out.println("2D: " + sol.coinChange2D(coins1, 11));            // 3
        System.out.println("BFS: " + sol.coinChangeBFS(coins1, 11));          // 3
        System.out.println("Greedy: " + sol.coinChangeGreedy(coins1, 11));    // 3
        System.out.println("DFS+Memo: " + sol.coinChangeDFS(coins1, 11));     // 3
        System.out.println("CoinFirst: " + sol.coinChangeCoinFirst(coins1, 11)); // 3
        System.out.println("MITM (DP fallback): " + sol.coinChangeMITM(coins1, 11)); // 3
        System.out.println("Dijkstra: " + sol.coinChangeDijkstra(coins1, 11)); // 3

        System.out.println("Case 2: " + sol.coinChangeDP(coins2, 3));         // -1
        System.out.println("Case 3: " + sol.coinChangeMemo(coins3, 6249));    // 20
    }
}
