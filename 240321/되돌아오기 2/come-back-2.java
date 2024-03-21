import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	// 상우하좌
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		char[] input = br.readLine().toCharArray();
		int[] pos = { 0, 0 };
		int dir = 0;
		boolean flag = false;
		int i = 0;
		for (i = 0; i < input.length; i++) {
			char cur = input[i];
			if (cur == 'F') {
				pos[0] += dr[dir];
				pos[1] += dc[dir];
			} else if (cur == 'L') {
				dir = (dir + 3) % 4;
			} else {
				dir = (dir + 1) % 4;
			}
			if (pos[0] == 0 && pos[1] == 0) {
				flag = true;
				break;
			}
		}

		if (flag) {
			System.out.println(i + 1);
		} else {
			System.out.println(-1);
		}
	}

}