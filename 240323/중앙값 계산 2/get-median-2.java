import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		StringTokenizer st = new StringTokenizer(br.readLine());

		ArrayList<Integer> arr = new ArrayList<>();
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= N; i++) {
			int cur = Integer.parseInt(st.nextToken());
			arr.add(cur);

			if (i % 2 != 0) {
				Collections.sort(arr);
				int tmp = arr.get(arr.size() / 2);
				sb.append(tmp).append(" ");
			}
		}

		System.out.println(sb.toString());
	}

}