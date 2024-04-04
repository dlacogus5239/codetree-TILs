import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Main {
	static int N;
	static char[][] map;

	static class Coin {
		int num;
		int r, c;

		public Coin(int num, int r, int c) {
			super();
			this.num = num;
			this.r = r;
			this.c = c;
		}

		@Override
		public String toString() {
			return "Coin [num=" + num + ", r=" + r + ", c=" + c + "]";
		}

	}

	static ArrayList<Coin> coins = new ArrayList<>();
	static int[] start = new int[2];
	static int[] end = new int[2];
	static int M;

	static int answer = Integer.MAX_VALUE;

	static boolean[] isChoosen;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new char[N][N];
		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (Character.isDigit(map[i][j])) {
					int num = map[i][j] - '0';
					coins.add(new Coin(num, i, j));
				} else if (map[i][j] == 'S') {
					start[0] = i;
					start[1] = j;
				} else if (map[i][j] == 'E') {
					end[0] = i;
					end[1] = j;
				}
			}
		}
		Collections.sort(coins, (o1, o2) -> (o1.num - o2.num));
		M = coins.size();
		isChoosen = new boolean[M];
		Comb(0, 0);

		System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
	}

	public static void Comb(int start, int cnt) {
		if (cnt == 3) {
//			System.out.println(Arrays.toString(isChoosen));
			int tmp = Dist();
			answer = Math.min(answer, tmp);
			return;
		}

		for (int i = start; i < M; i++) {
			isChoosen[i] = true;
			Comb(i + 1, cnt + 1);
			isChoosen[i] = false;
		}
	}

	public static int Dist() {
		int[] pre = new int[2];
		pre[0] = start[0];
		pre[1] = start[1];
		int distance = 0;

		for (int i = 0; i < isChoosen.length; i++) {
			if (isChoosen[i]) {
				distance += Calc(pre[0], pre[1], coins.get(i).r, coins.get(i).c);
				pre[0] = coins.get(i).r;
				pre[1] = coins.get(i).c;
			}
		}

		distance += Calc(pre[0], pre[1], end[0], end[1]);

//		System.out.println(distance);
		return distance;
	}

	public static int Calc(int r1, int c1, int r2, int c2) {
		return Math.abs(r1 - r2) + Math.abs(c1 - c2);
	}

}