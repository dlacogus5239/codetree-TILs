import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	// 상우하좌
	static int[] dr = { 1, 0, -1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		char[] dir = br.readLine().toCharArray();
		int d = 0;
		int[] pos = { 0, 0 };
		for (int i = 0; i < dir.length; i++) {
			char cur = dir[i];
			if (cur == 'F') {
				pos[0] += dr[d];
				pos[1] += dc[d];
			} else if (cur == 'L') {
				d = (d + 3) % 4;
			} else {
				d = (d + 1) % 4;
			}
		}

		System.out.println(pos[1] + " " + pos[0]);
	}

}