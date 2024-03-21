import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;

	// 상우하좌
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		int[] pos = { 0, 0 };
		boolean isDone = false;
		int result = -1;

		int time = 0;
		Turn: for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());

			int d = GetDir(st.nextToken());
			int move = Integer.parseInt(st.nextToken());
			for (int m = 1; m <= move; m++) {
				pos[0] += dr[d];
				pos[1] += dc[d];
				time++;
				if (pos[0] == 0 && pos[1] == 0) {
					isDone = true;
					break Turn;
				}
			}
		}
		if (!isDone) {
			System.out.println(-1);
		} else {
			System.out.println(time);
		}
	}

	public static int GetDir(String dir) {
		if (dir.equals("N")) { // 상
			return 0;
		} else if (dir.equals("E")) { // 우
			return 1;
		} else if (dir.equals("S")) { // 하
			return 2;
		} else { // 좌
			return 3;
		}
	}

}