import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] input = br.readLine().toCharArray();

		int[] R = new int[input.length];

		for (int i = input.length - 2; i >= 0; i--) {
			if (input[i] == ')') {
				if (input[i] == input[i + 1]) {
					R[i] = R[i + 1] + 1;
				} else {
					R[i] = R[i + 1];
				}
			} else {
				R[i] = R[i + 1];
			}
		}

		long answer = 0L;

		for (int i = 0; i < input.length - 2; i++) {
			if (input[i] == '(' && input[i + 1] == '(') {
				answer += R[i + 2];
			}
		}

		System.out.println(answer);

	}

}