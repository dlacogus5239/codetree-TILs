import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	// 상 우 하 좌
	static int[] dr = { 1, 0, -1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st;

		int[] pos = { 0, 0 };
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());

			char dir = st.nextToken().charAt(0);
			int d = 0;
			int move = Integer.parseInt(st.nextToken());
			switch (dir) {
			case 'N':
				d = 0;
				break;
			case 'E':
				d = 1;
				break;
			case 'S':
				d = 2;
				break;
			case 'W':
				d = 3;
				break;
			}

			pos[0] += dr[d] * move;
			pos[1] += dc[d] * move;
		}
		System.out.println(pos[1] + " " + pos[0]);

	}

}