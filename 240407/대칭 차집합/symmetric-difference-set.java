import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main{
	static int N1, N2; // 원소의 개수

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N1 = Integer.parseInt(st.nextToken());
		N2 = Integer.parseInt(st.nextToken());
		HashSet<Integer> A = new HashSet<>();
		HashSet<Integer> B = new HashSet<>();

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N1; i++) {
			A.add(Integer.parseInt(st.nextToken()));
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N2; i++) {
			B.add(Integer.parseInt(st.nextToken()));
		}

		int cntA = N1;
		int cntB = N2;
		for (Integer i : A) {
			if (B.contains(i)) {
				cntA--;
			}
		}

		for (Integer i : B) {
			if (A.contains(i)) {
				cntB--;
			}
		}

		System.out.println(cntA + cntB);
	}
}