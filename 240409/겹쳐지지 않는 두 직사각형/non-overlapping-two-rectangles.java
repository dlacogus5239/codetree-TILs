// 정답을 봤는데 
// 그냥 두 사각형 전체 다 잡고, 
// 겹치면 계산하지 말고 안겹칠때만 계산해서 
// 정답 도출

import java.util.Scanner;

public class Main {
	public static final int INT_MIN = Integer.MIN_VALUE;
	public static final int MAX_NUM = 5;

	public static int n, m;
	public static int[][] grid = new int[MAX_NUM][MAX_NUM];
	public static int[][] board = new int[MAX_NUM][MAX_NUM];

	public static void clearBoard() {
		for (int i = 0; i < n; i++)
			for (int j = 0; j < m; j++)
				board[i][j] = 0;
	}

	public static void draw(int x1, int y1, int x2, int y2) {
		for (int i = x1; i <= x2; i++)
			for (int j = y1; j <= y2; j++)
				board[i][j]++;
	}

	public static boolean checkBoard() {
		// 동일한 칸을 2개의 직사각형이 모두 포함한다면
		// 겹치게 됩니다.
		for (int i = 0; i < n; i++)
			for (int j = 0; j < m; j++)
				if (board[i][j] >= 2)
					return true;
		return false;
	}

	// (x1, y1), (x2, y2) 그리고
	// (x3, y3), (x4, y4) 로 이루어져있는
	// 두 직사각형이 겹치는지 확인하는 함수
	public static boolean overlapped(int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4) {
		clearBoard();
		draw(x1, y1, x2, y2);
		draw(x3, y3, x4, y4);
		return checkBoard();
	}

	public static int rectSum(int x1, int y1, int x2, int y2) {
		int sumOfNums = 0;
		for (int i = x1; i <= x2; i++)
			for (int j = y1; j <= y2; j++)
				sumOfNums += grid[i][j];

		return sumOfNums;
	}

	// 첫 번째 직사각형이 (x1, y1), (x2, y2)를 양쪽 꼭지점으로 할 때
	// 두 번째 직사각형을 겹치지 않게 잘 잡아
	// 최대 합을 반환하는 함수
	public static int findMaxSum(int x1, int y1, int x2, int y2) {
		int maxSum = INT_MIN;

		// (i, j), (k, l)을 양쪽 꼭지점으로 하는
		// 두 번째 직사각형을 정하여
		// 겹치지 않았을 때 중
		// 최댓값을 찾아 반환합니다.
		for (int i = 0; i < n; i++)
			for (int j = 0; j < m; j++)
				for (int k = i; k < n; k++)
					for (int l = j; l < m; l++) {
						if (!overlapped(x1, y1, x2, y2, i, j, k, l))
							maxSum = Math.max(maxSum, rectSum(x1, y1, x2, y2) + rectSum(i, j, k, l));
					}

		return maxSum;
	}

	// 두 직사각형을 잘 잡았을 때의 최대 합을 반환하는 함수
	public static int findMaxSum() {
		int maxSum = INT_MIN;

		// (i, j), (k, l)을 양쪽 꼭지점으로 하는
		// 첫 번째 직사각형을 정하여
		// 그 중 최댓값을 찾아 반환합니다.
		for (int i = 0; i < n; i++)
			for (int j = 0; j < m; j++)
				for (int k = i; k < n; k++)
					for (int l = j; l < m; l++)
						maxSum = Math.max(maxSum, findMaxSum(i, j, k, l));
		return maxSum;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();

		for (int i = 0; i < n; i++)
			for (int j = 0; j < m; j++)
				grid[i][j] = sc.nextInt();

		int ans = findMaxSum();
		System.out.print(ans);
	}
}