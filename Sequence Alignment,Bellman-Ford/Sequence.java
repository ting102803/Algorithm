
import java.util.Scanner;
import java.util.ArrayList;
import java.util.*;

public class Sequence {
	static ArrayList<node> MinRoute = new ArrayList<>();// 루트를저장하기위한 리스트

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner scan = new Scanner(System.in);

		System.out.print("String1 : ");
		String str1 = scan.nextLine();
		System.out.print("String2 : ");
		String str2 = scan.nextLine();
		int[][] diff = new int[str1.length() + 1][str2.length() + 1];
		// 각자의 길이만큼의 2차원 배열 선언
		for (int i = 0; i <= str1.length(); i++) {
			diff[i][0] = i;
		}
		for (int i = 0; i <= str2.length(); i++) {
			diff[0][i] = i;
		} // 0번째 즉 모든 수치를 갭으로 계산하여서 채워준다.
		for (int i = 1; i < str1.length() + 1; i++) {
			for (int j = 1; j < str2.length() + 1; j++) {
				if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
					diff[i][j] = diff[i - 1][j - 1];// 만약 문자열이 같다면 대각선왼쪽 윗값을 선택한다.
				} else {
					int A = 1 + diff[i - 1][j];
					int B = 1 + diff[i][j - 1];
					int C = 2 + diff[i - 1][j - 1];
					int diffmin = Math.min(A, B);
					diff[i][j] = Math.min(diffmin, C);// A,B,C중에 가장 작은값을 저장한다.
				}
			}
		}
		System.out.printf("      ");
		for (int j = 0; j < str2.length(); j++) {
			System.out.printf("%3s", str2.charAt(j));
		}
		System.out.println("");
		for (int i = 0; i < str1.length() + 1; i++) {
			if (i != 0)
				System.out.printf("%3s", str1.charAt(i - 1));
			else
				System.out.print("   ");
			for (int j = 0; j < str2.length() + 1; j++) {
				System.out.printf("%3d", diff[i][j]);
			}
			System.out.println("");
		} // 만들어진 테이블을 출력

		// 이제 어떤 루트로 생성되었는지 알아보는 코드이다

		Align(0, 0, str1.length(), str2.length(), diff);

		Collections.sort(MinRoute);
		MinRoute.add(0, new node(0, 0));
		MinRoute.add(MinRoute.size(), new node(str1.length(), str2.length()));// 첫노드와 마지막 노드 추가
		for (int z = 0; z < MinRoute.size() - 1; z++) {
			if (MinRoute.get(z + 1).i - MinRoute.get(z).i > 1) {
				int abc = MinRoute.get(z + 1).i - MinRoute.get(z).i;
				while (abc != 1) {
					MinRoute.add(z + 1, new node(MinRoute.get(z).i + 1, MinRoute.get(z).j));
					abc--;
					z++;
				}//선택안된 OPTPath를 선택하는 while문
			}
		}
		System.out.println();
		System.out.println("최종 Path");
		for (int w = 0; w < MinRoute.size(); w++)
			System.out.println(MinRoute.get(w).i + " " + MinRoute.get(w).j);
	}

	// 최소로 선택된 배열의 인덱스를 저장하기위한 노드
	static void Align(int a, int b, int c, int d, int[][] table) {// 0,0부터 x,y까지가지 최단경로 찾기
		if (d - b <= 1)
			return;//차이가 1이하면 종료
		int n = d + b;// 
		int[] Yprefix = new int[c + 1 - a];
		int[] Ysuffix = new int[c + 1 - a];
		Yprefix = AllYprefixCosts(a, b, c, d, n / 2, table);//a,b부터 ?,n/2 까지의 차를 저장
		Ysuffix = AllYsuffixCosts(a, b, c, d, n / 2, table);//?,n/2부터 c,d까지의 차를 저장
		int mincost = 99999;
		int besty = b;//선정된 y좌표를 저장하기위한 변수
		int min = 99999;
		for (int p = a; p < c + 1; p++) {
			min = Math.min(min, table[p][n / 2]);
		}//해당라인에 있는 최소값을 저장
		for (int k = a; k < c + 1; k++) {
			if (Yprefix[k - a] >= 0 && Ysuffix[k - a] >= 0 && mincost >= Yprefix[k - a] + Ysuffix[k - a]//둘다 0이상이고 mincost보다 작고
					&& k != table.length - 1 && table[k][n / 2] <= table[k + 1][n / 2]//선택된 배열의 오른쪽,아래,오른쪽대각선 아래보다 작거나 같아야 최소값이다.
					&& table[k][n / 2] <= table[k + 1][n / 2 + 1] && table[k][n / 2] <= table[k + 1][n / 2 + 1]) {
				if (min == table[k][n / 2]) {//마지막으로 해당값이 최솟값인지 한번더 확인한다.
					mincost = Yprefix[k - a] + Ysuffix[k - a];//맞다면 MINCOST 갱신
					besty = k;//해당 k값을 저장
				}
			}
		}
		MinRoute.add(new node(besty, n / 2));//path에 추가
		System.out.println(besty + " " + n / 2);//선택된값 출력

		Align(a, b, besty, n / 2, table);//왼쪽도 진행
		Align(besty, n / 2, c, d, table);//오른쪽도 진행
	}

	static int[] AllYprefixCosts(int a, int b, int c, int d, int n, int[][] table) {
		int[] temp = new int[c + 1 - a];//k,n과 a,b배열의 차이를 정리하여서 리턴한다.
		for (int k = a; k < c + 1; k++)
			temp[k - a] = table[k][n] - table[a][b];
		return temp;
	}

	static int[] AllYsuffixCosts(int a, int b, int c, int d, int n, int[][] table) {
		int[] temp = new int[c + 1 - a];//k,n과 c,d배열의 차이를 정리하여서 리턴한다.
		for (int k = a; k < c + 1; k++)
			temp[k - a] = table[c][d] - table[k][n];//일반적으로 OPT테이블의 오른편이 값이 더큼으로 오른쪽에서 왼쪽을 빼줘야한다.
		return temp;
	}

	static class node implements Comparable<node> {
		int i;
		int j;

		node(int i, int j) {
			this.i = i;
			this.j = j;

		}

		@Override
		public int compareTo(node o) {
			if (this.j > o.j) {
				return 1;
			} else if (this.j < o.j) {
				return -1;
			} else
				return 0;
		}
	}
}
