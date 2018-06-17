import java.util.*;

public class Knapsack {
	public static void main(String[] args) {

		System.out.println("아이템 갯수와 최대 무게를 입력하세요");
		Scanner scan = new Scanner(System.in);
		int count = scan.nextInt();// 갯수저장하는 변수
		int totalweight = scan.nextInt();// 총 가방크기를 저장
		int[] value = new int[count + 1];// 가치와 무게를 저장
		int[] weight = new int[count + 1];// 0번째 인덱스를 사용하면 계산시 복잡해서 사용하지않기로 했다.
		System.out.println("아이템 갯수 : " + count + " 최대 무게 : " + totalweight);
		System.out.println("데이터를 입력하세요");

		ArrayList<Node> MaxValue = new ArrayList<>();// 가방에 들어간 가치가 가장 높은땔
		// 가치값과 OTPTable의 인덱스값을 저장하기위한 ArrayList

		for (int i = 1; i < count + 1; i++) {
			value[i] = scan.nextInt();
			weight[i] = scan.nextInt();
		} // 데이터를 입력받는다.
		int[][] OPTtable = new int[count + 1][totalweight + 1];// 테이블생성
		int[][][] WhatItem = new int[count + 1][totalweight + 1][count];// 각 테이블 마다 어떤 아이템이 선택되는지 알기위해 3차원배열로 선언
		for (int i = 1; i < count + 1; i++) {// 테이블과 최대값을
			int j;
			for (j = 0; j < totalweight + 1; j++) {
				if (weight[i] > j) {//일단 무게가 안될경우에는
					OPTtable[i][j] = OPTtable[i - 1][j];//이전 OPT값을 저장
					MaxValue.add(new Node(OPTtable[i][j], i, j));//여기서나온 인덱스와 값을 리스트에 저장
						WhatItem[i][j] = WhatItem[i - 1][j];
					//3번째 배열값들을 전부 복사
				} else if (OPTtable[i - 1][j] > value[i] + OPTtable[i - 1][j - weight[i]]) {
					OPTtable[i][j] = OPTtable[i - 1][j];
					MaxValue.add(new Node(OPTtable[i][j], i, j));
					WhatItem[i][j] = WhatItem[i - 1][j];
					//3번째 배열값들을 전부 복사
				} else {
					OPTtable[i][j] = value[i] + OPTtable[i - 1][j - weight[i]];
					MaxValue.add(new Node(OPTtable[i][j], i, j));
					int k = 0;
					WhatItem[i][j][k] = i;//i의 밸류의 값을 썻기때문에 i는 무조건이다
					for (k = 0; k < count - 1; k++) {
						WhatItem[i][j][k + 1] = WhatItem[i - 1][j - weight[i]][k];
					}//그이후는 Item은  그이전의 i-1의 OPT의 Item을 참고하면된다.
				}

			}

		}

		for (int i = 0; i < count + 1; i++) {
			for (int j = 0; j < totalweight + 1; j++) {
				System.out.printf("%4d", OPTtable[i][j]);
			}//깔끔한 출력을위해 간격을 4로 두었다.
			System.out.printf("\n");
		}
		Collections.sort(MaxValue);
		System.out.println("Max : " + MaxValue.get(0).value);//정렬후 최댓값 출력
		
		
		int Maxitemindex1 = MaxValue.get(0).first;
		int Maxitemindex2 = MaxValue.get(0).second;//최댓값의 OTP index이다.
		System.out.print("Item : ");
		for (int k = 0; k < count; k++) {
			if (WhatItem[Maxitemindex1][Maxitemindex2][k] == 0)//0번째 아이템을 만났다는건 더이상 아이템이 없다라고 할 수 있다.
				break;
			System.out.print(WhatItem[Maxitemindex1][Maxitemindex2][k] + " ");
		}//그외는 출력을 해준다.

	}

	static class Node implements Comparable<Node> {
		int value;
		int first;
		int second;

		Node(int value, int first, int second) {
			this.value = value;
			this.first = first;
			this.second = second;
		}

		@Override
		public int compareTo(Node o) {
			if (this.value > o.value) {
				return -1;
			} else if (this.value < o.value) {
				return 1;
			}
			return 0;//Node를 가치를 기준으로 내림차순으로 정렬한다.
		}
	}
}
