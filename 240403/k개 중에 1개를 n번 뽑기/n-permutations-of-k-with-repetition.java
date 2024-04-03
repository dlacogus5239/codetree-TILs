import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	// 1이상 K이하, N개 수 뽑음
	static int K, N;
	static int[] choose;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		K = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		choose = new int[N];
		chooseNum(0);
	}

	public static void chooseNum(int cnt) {
		if (cnt == N) {

			for (int i = 0; i < N; i++) {
				System.out.print(choose[i] + " ");
			}
			System.out.println();
			return;
		}

		for (int i = 1; i <= K; i++) {
			choose[cnt] = i;
			chooseNum(cnt + 1);
		}
	}

}