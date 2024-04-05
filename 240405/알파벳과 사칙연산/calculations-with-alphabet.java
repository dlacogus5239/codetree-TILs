import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
	static int alphaCnt = 0;
	static int[] alphaBet;
	static ArrayList<Character> oper = new ArrayList<>();
	static ArrayList<Character> num = new ArrayList<>();
	static int result = Integer.MIN_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		char[] input = br.readLine().toCharArray();
		alphaBet = new int[7];
		Arrays.fill(alphaBet, 0);
		int idx = 1;
		for (int i = 0; i < input.length; i++) {
			if (!Character.isAlphabetic(input[i])) {
				oper.add(input[i]);
			} else {
				num.add(input[i]);
			}
		}

//		System.out.println(oper);
//		System.out.println(num);

		Perm(0);
		System.out.println(result);

	}

	public static void Perm(int cnt) {
		if (cnt == 7) {
//			System.out.println(Arrays.toString(alphaBet));
			Calculation();
			return;
		}

		for (int i = 1; i <= 4; i++) {
			alphaBet[cnt] = i;
			Perm(cnt + 1);
		}
	}

	public static void Calculation() {
		int pre = changeNum(num.get(0));
		char tmp;
		int cur = 1;
		int next = 0;
		for (int i = 0; i < oper.size(); i++) {
			tmp = oper.get(i);

			next = changeNum(num.get(cur++));
			switch (tmp) {
			case '+':
				pre += next;
				break;
			case '-':
				pre = pre - next;
				break;
			case '*':
				pre = pre * next;
				break;
			}

		}
		result = Math.max(result, pre);

	}

	public static int changeNum(char c) {
		return alphaBet[(int) c - 'a'];
	}

}