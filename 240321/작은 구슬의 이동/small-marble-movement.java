import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	// 상 우 하 좌
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };
	static int N;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// [r, c]
		int[] marble = new int[2];

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int time = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());

		marble[0] = Integer.parseInt(st.nextToken()) - 1;
		marble[1] = Integer.parseInt(st.nextToken()) - 1;
		String dir = st.nextToken();

		int d = getDir(dir);

		for (int t = 0; t < time; t++) {
			int nr = marble[0] + dr[d];
			int nc = marble[1] + dc[d];
			if (!isIn(nr, nc)) {
				d = (d + 2) % 4;
				continue;
			}

			marble[0] = nr;
			marble[1] = nc;
		}

		System.out.println((marble[0] + 1) + " " + (marble[1] + 1));
	}

	public static int getDir(String dir) {
		if (dir.equals("U")) {
			return 0;
		}
		if (dir.equals("D")) {
			return 2;
		}
		if (dir.equals("R")) {
			return 1;
		}
		if (dir.equals("L")) {
			return 3;
		}

		return -1;
	}

	public static boolean isIn(int r, int c) {
		return !(r < 0 || c < 0 || r >= N || c >= N);
	}

}