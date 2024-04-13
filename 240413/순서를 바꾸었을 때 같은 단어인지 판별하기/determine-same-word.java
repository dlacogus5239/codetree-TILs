import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] input1, input2;
		input1 = br.readLine().toCharArray();
		input2 = br.readLine().toCharArray();

		Arrays.sort(input1);
		Arrays.sort(input2);

		if (input1.length != input2.length) {
			System.out.println("No");
			return;
		}

		for (int i = 0; i < input1.length; i++) {
			if (input1[i] != input2[i]) {
				System.out.println("No");
				return;
			}
		}

		System.out.println("Yes");
	}

}