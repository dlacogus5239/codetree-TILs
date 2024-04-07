import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, G; // N명의 사람들, G개의 그룹
	static boolean[] isInvited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		G = Integer.parseInt(st.nextToken());
		isInvited = new boolean[N + 1];
		int[] groupNum = new int[G + 1];
		HashSet<Integer>[] group = new HashSet[G + 1]; // 각 그룹마다 속해있는 사람 번호
		for (int i = 1; i < G + 1; i++) {
			group[i] = new HashSet<>();
		} // set Init
		HashSet<Integer>[] person = new HashSet[N + 1]; // 각 사람이 속해있는 그룹들
		for (int i = 1; i <= N; i++) {
			person[i] = new HashSet<>();
		}

		for (int i = 1; i <= G; i++) {
			st = new StringTokenizer(br.readLine());
			int p = Integer.parseInt(st.nextToken());
			groupNum[i] = p;
			for (int j = 0; j < p; j++) {
				int tmp = Integer.parseInt(st.nextToken());
				group[i].add(tmp);
				person[tmp].add(i);
			}
		}
//		System.out.println("group");
//		for (int i = 1; i <= G; i++) {
//			System.out.println(group[i].toString());
//		}
//
//		System.out.println("person");
//		for (int i = 1; i <= N; i++) {
//			System.out.println(person[i].toString());
//		}

		Queue<Integer> q = new LinkedList<>();
		q.offer(1);
		while (!q.isEmpty()) {
			int cur = q.poll();

			isInvited[cur] = true;
			for (Integer i : person[cur]) {
				groupNum[i]--;
				if (groupNum[i] == 1) {
					for (Integer j : group[i]) {

						if (!isInvited[j]) {
							isInvited[j] = true;
//							System.out.println(j);
							q.offer(j);
						}
					}

				}
			}
		}
		int cnt = 0;
		for (int i = 1; i <= N; i++) {
			if (isInvited[i]) {
				cnt++;
			}
		}

		System.out.println(cnt);

	}

}