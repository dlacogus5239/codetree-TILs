import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int[] firstResult;

	static int[] result;
	static int N, M;

	static class Ladder {
		int num;
		int col;

		public Ladder(int num, int col) {
			super();
			this.num = num;
			this.col = col;
		}

		@Override
		public String toString() {
			return "Ladder [num=" + num + ", col=" + col + "]";
		}

	}

	static ArrayList<Ladder>[] ladders;

	static boolean[] isChoosen;

	static int answer = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		firstResult = new int[N + 1];
		ladders = new ArrayList[16];

		// 사다리 초기화
		for (int i = 1; i < 16; i++) {
			ladders[i] = new ArrayList<>();
		}

		isChoosen = new boolean[M + 1];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());

			int c = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			ladders[r].add(new Ladder(i + 1, c));

		}

		Arrays.fill(isChoosen, true);
		firstResult = Simulate();

		Arrays.fill(isChoosen, false);

		chooseLadder(0);
		System.out.println(answer);

	}

	public static void chooseLadder(int cnt) {
		if (cnt == M) {
			int[] tmpResult = Simulate();
			int tmp = 0;
			for (int i = 0; i < tmpResult.length; i++) {
				if (tmpResult[i] != firstResult[i]) {
					return;
				}
			}
			for (int i = 1; i < isChoosen.length; i++) {
//				System.out.print(isChoosen[i] + " ");
				if (isChoosen[i]) {
					tmp++;
				}
			}
//			System.out.println();

			answer = Math.min(answer, tmp);
			return;
		}

		isChoosen[cnt] = true;
		chooseLadder(cnt + 1);
		isChoosen[cnt] = false;
		chooseLadder(cnt + 1);

	}

	public static int[] Simulate() {
		int[] tmpPerson = new int[N + 1];
		for (int i = 0; i < tmpPerson.length; i++) {
			tmpPerson[i] = i;
		}

		for (int i = 1; i <= 15; i++) {
			// 윗줄부터 한줄씩 검사
			for (int j = 0; j < ladders[i].size(); j++) {
				if (isChoosen[ladders[i].get(j).num]) {
					int tmp = tmpPerson[ladders[i].get(j).col + 1];
					// 교환
					tmpPerson[ladders[i].get(j).col + 1] = tmpPerson[ladders[i].get(j).col];
					tmpPerson[ladders[i].get(j).col] = tmp;
				}
			}

		}
//
//		for (int i = 1; i < tmpPerson.length; i++) {
//			System.out.print(tmpPerson[i] + " ");
//		}

//		System.out.println();

		return tmpPerson;
	}
}