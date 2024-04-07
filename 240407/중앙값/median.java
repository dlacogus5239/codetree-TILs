import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		int N = 0;
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int[] arr;
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			arr = new int[N];
			for (int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}

			// 중앙값 기준으로
			// arr[i] < mid 이면desc(내림차순-->최댓값찾기)
			// arr[i] > mid 이면 asc(오름차순-->최솟값찾기)

			PriorityQueue<Integer> asc = new PriorityQueue<>((o1, o2) -> o1 - o2); // 오름차순
			PriorityQueue<Integer> desc = new PriorityQueue<>((o1, o2) -> o2 - o1); // 내림차순
			int mid = arr[0];
			sb.append(mid).append(" ");
			// 처음 수는 이미 처리완료
			// 즉 i + 1번째 단계를 진행한다고 생각하자
			for (int i = 1; i < N; i++) {
				if (i % 2 == 1) { // i가 홀수면 짝수번째 단계 진행
					if (arr[i] < mid) {
						desc.add(arr[i]);
					} else {
						asc.add(arr[i]);
					}
				} else {
					// 두 개의 큐 중에 개수가 더 많은 곳에서 수를 뽑아주자
					int next;
					if (asc.size() > desc.size()) {
						next = asc.poll();
					} else {
						next = desc.poll();
					} // 이까지 진행하면 큐 사이즈 동일

					// mid, arr[i], next에서
					// 작은 값은 asc, 중간값은 mid, 큰 값은 desc에 넣어줘야 한다
					// 그러니까 인풋 새로 들어올때 다시 정렬한다고 생각
					int[] nums = new int[] { mid, arr[i], next };
					Arrays.sort(nums);
					desc.add(nums[0]);
					mid = nums[1];
					asc.add(nums[2]);
					sb.append(mid).append(" ");

				}
			}

			sb.append("\n");

		}

		System.out.println(sb.toString());

	}

}