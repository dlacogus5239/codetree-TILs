import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[] choose;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		choose = new int[M];
		comb(1, 0);
	}

	public static void comb(int idx, int cnt) {

		if (cnt == M) {
			for (int i = 0; i < choose.length; i++) {
				System.out.print(choose[i] + " ");
			}
			System.out.println();
			return;
		}
		if (idx == N + 1) {
			return;
		}

		choose[cnt] = idx;
		comb(idx + 1, cnt + 1);
		choose[cnt] = 0;
		comb(idx + 1, cnt);
	}

}