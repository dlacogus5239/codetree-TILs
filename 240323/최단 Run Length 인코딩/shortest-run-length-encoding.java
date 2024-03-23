import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
	static int answer = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		char[] input = br.readLine().toCharArray();
		Deque<Character> dq = new ArrayDeque<>();

		for (int i = 0; i < input.length; i++) {
			dq.add(input[i]);
		}

		for (int i = 0; i < input.length; i++) {
			dq.addFirst(dq.pollLast());
//			System.out.println(dq.toString());
			RunLengthEncoding(dq);
		}
		System.out.println(answer);

	}

	public static void RunLengthEncoding(Deque<Character> cur) {
		char[] tmp = new char[cur.size()];

		for (int i = 0; i < tmp.length; i++) {
			tmp[i] = cur.pollFirst();
			cur.addLast(tmp[i]);
		}

		int cnt = 1;
		char pre = tmp[0];
		String result = "";
		for (int i = 1; i < tmp.length; i++) {
			if (pre == tmp[i]) {
				cnt++;
			} else {
				result += pre;
				result += cnt;
				pre = tmp[i];
				cnt = 1;
			}
		}
		result += pre;
		result += cnt;
		answer = Math.min(result.length(), answer);
	}

}