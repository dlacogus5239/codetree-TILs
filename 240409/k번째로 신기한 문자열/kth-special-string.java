import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
	static int N, K;
	static String T;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		T = st.nextToken();

		ArrayList<String> list = new ArrayList<>();
		int len = T.length();
		for (int i = 0; i < N; i++) {
			String cur = br.readLine();
//			if (cur.substring(0, len).equals(T)) {
//				System.out.println(cur);
//			}
			if (cur.startsWith(T)) {
				list.add(cur);
			}

		}

		Collections.sort(list, (o1, o2) -> o1.compareTo(o2));
		System.out.println(list.get(K - 1));
	}

}