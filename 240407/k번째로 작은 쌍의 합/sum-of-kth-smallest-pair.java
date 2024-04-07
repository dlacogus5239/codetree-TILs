import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int N, M, K;
	static final long INF = 100_000_001L;

	static class Pair implements Comparable<Pair> {
		int sum;
		int idx1, idx2;

		public Pair(int sum, int idx1, int idx2) {
			super();
			this.sum = sum;
			this.idx1 = idx1;
			this.idx2 = idx2;
		}

		@Override
		public String toString() {
			return "Pair [sum=" + sum + ", idx1=" + idx1 + ", idx2=" + idx2 + "]";
		}

		@Override
		public int compareTo(Pair o) {
			if (this.sum != o.sum) {
				return this.sum - o.sum;
			} else if (this.idx1 != o.idx1) {
				return this.idx1 - o.idx1;
			} else {
				return this.idx2 - o.idx2;
			}
		}

	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken()) - 1;
		int[] arr1 = new int[N];
		int[] arr2 = new int[M];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr1[i] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			arr2[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr1);
		Arrays.sort(arr2);

		PriorityQueue<Pair> pq = new PriorityQueue<>();
		// 두번째 배열의 첫 원소와 두번째 배열의 모든 원소를 배치시켜서 일단 넣어준다
		// 만약 최솟값으로 꺼낸 값이 A(i) + B(j) 였다면, 이를 빼주고 A(i) + B(j + 1)을 새로 넣어주면
		// 최솟값 후보들 갱신
		// 2-3번째줄 K번 반복
		for (int i = 0; i < N; i++) {
			pq.offer(new Pair(arr1[i] + arr2[0], 0, i));
		}

		// K번 반복
		while (K-- > 0) {
			Pair min = pq.poll();
			int idx1 = min.idx1;
			int idx2 = min.idx2;

			idx2++;
			if (idx2 < M) {
				pq.offer(new Pair(arr1[idx1] + arr2[idx2], idx1, idx2));
			}
//			System.out.println(pq.toString());

		}
//		System.out.println(Arrays.toString(arr1));
//		System.out.println(Arrays.toString(arr2));
		System.out.println(pq.peek().sum);
	}

}