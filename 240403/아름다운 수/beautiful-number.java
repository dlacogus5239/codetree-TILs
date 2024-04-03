import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static int N;
	static int result = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		makeBeautifulNumber(0);

		System.out.println(result);

	}

	public static void makeBeautifulNumber(int cnt) {
		if (cnt == N) {
			result++;
			return;
		} else if (cnt > N) {
			return;
		}

		for (int i = 1; i <= 4; i++) {
			makeBeautifulNumber(cnt + i);
		}
	}

}