import java.util.ArrayList;
import java.util.Scanner;

public class Sequence {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<node> MinRoute = new ArrayList<>();// 루트를저장하기위한 리스트
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
		int col = str1.length();
		int row = str2.length();
		//역으로 추적하는게 구현으로 더쉽기때문에 시작점을 끝점으로 하였다.
		while (col != 0 || row != 0) {
			int minI, minJ;
			if (col == 0) {
				minI = col;
				minJ = row - 1;
				row--;
			} else if (row == 0) {
				minI = col - 1;
				minJ = row;
				col--;//만약 행이나 열이 0이라면 바로 감소시키고
			} else if ( diff[col - 1][row] > diff[col - 1][row - 1]
					&& diff[col][row - 1] > diff[col - 1][row - 1]) {
				minI = col - 1;
				minJ = row - 1;
				col--;
				row--;
			//현재 행과 열에서 왼쪽,위쪽,대각선 왼쪽 위중 가장 작은 값을 인덱스를 구하는 과정이다.
			} 
			 else if (diff[col - 1][row] > diff[col][row - 1] && diff[col - 1][row - 1] > diff[col][row - 1]) {
				minI = col;
				minJ = row - 1;
				row--;
			} else {
				minI = col - 1;
				minJ = row;
				col--;
			}
			MinRoute.add(new node(minI, minJ));
			//여기서 나온 minI와 minJ가 지나온 루트이다.
		}
		System.out.println("MinCost : " + diff[str1.length()][str2.length()]);
		//MinCost는 마지막 배열이다
		
		for (int i = MinRoute.size() - 1; i >= 0; i--)
			System.out.println("[" + MinRoute.get(i).i + "][" + MinRoute.get(i).j + "] 배열 선택");
		//선택된배열은 역순으로 더하였기때문에 역순으로 출력
		System.out.println("[" + str1.length() + "][" + str2.length() + "] 배열 선택");
		//마지막 배열 출력
	}
//최소로 선택된 배열의 인덱스를 저장하기위한 노드
	static class node {
		int i;
		int j;

		node(int i, int j) {
			this.i = i;
			this.j = j;

		}
	}
}
