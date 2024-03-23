import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int width = Integer.parseInt(st.nextToken());
		int start = Integer.parseInt(st.nextToken()) - 1;

		int[][] map = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int i = 0;
		Drop: for (i = 0; i < N - 1; i++) {
			for (int j = start; j < start + width; j++) {
				if (map[i][j] != 0) {
					for (int k = start; k < start + width; k++) {

						map[i - 1][k] = 1;
					}
					break Drop;
				}
			}
		}

		if (i == N - 1) {
			for (int k = start; k < start + width; k++) {
				map[i][k] = 1;
			}
		}

		for (i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}

}