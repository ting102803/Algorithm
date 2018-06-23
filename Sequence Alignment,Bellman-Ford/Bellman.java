import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Bellman {

	public static void main(String[] args) {
		String Fir, Sec, Thir, line;
		int count = 0;// 현재 빈배열중 가장 앞에 배열의 위치를 나타내는 상수이다
		int NodeCount = 0;
		int EdgeCount = 0;
		int start = 0;
		int Target = 0;
		int[][] TempEdge = new int[15][15];
		int M = Integer.MAX_VALUE;
		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 15; j++) {
				TempEdge[i][j] = M;
			}
		}
		try {
			FileReader fr = new FileReader("data10.txt");
			BufferedReader br = new BufferedReader(fr);// 텍스트파일을 읽어온다.
			line = br.readLine();// 한줄씩 입력받는다.
			while (line != null) // 줄이 없어질때까지 받음
			{
				StringTokenizer tk = new StringTokenizer(line, " "); // tk라는 분해기가 띄어쓰기마다 단어를 자른다
				while (tk.hasMoreTokens()) {
					Fir = tk.nextToken();// 처음 오는 수를 저장한다.
					if (count == 0) {
						NodeCount = Integer.parseInt(Fir);
					} else if (count == 1) {
						Sec = tk.nextToken();
						start = Integer.parseInt(Fir);
						Target = Integer.parseInt(Sec);
					} else if (count == 2) {
						EdgeCount = Integer.parseInt(Fir);
					} else {
						Sec = tk.nextToken();
						Thir = tk.nextToken();
						TempEdge[Integer.parseInt(Fir)][Integer.parseInt(Sec)] = Integer.parseInt(Thir);
					}
					count++;
				}
				line = br.readLine(); // txt파일의 커서를 다음으로 옮기고, 다음 줄의 내용을 line에 저장한다.
			}
			br.close();
		} catch (IOException e) {
		}
		int[][] Edge = new int[NodeCount][NodeCount];

		for (int i = 0; i < NodeCount; i++) {
			for (int j = 0; j < NodeCount; j++) {
				Edge[i][j] = TempEdge[i][j];
			}
		} // 엣지옮기기
		
		int[][] OPT = new int[NodeCount + 1][NodeCount];//OPT테이블생성
		boolean[][][] OPTPath = new boolean[NodeCount + 1][NodeCount][NodeCount];
		for(int i=0;i<NodeCount + 1;i++) {
			for(int j=0;j<NodeCount;j++) {
				OPTPath[i][j][j]=true;
			}
		}//OPTPath 초기화
		for (int i = 0; i < NodeCount; i++) {
			OPT[0][i] = M;
		}

		OPT[0][Target] = 0;
		// 초기화
		for (int i = 1; i < NodeCount + 1; i++) {
			for (int j = 0; j < NodeCount - 1; j++) {
				int temp = M;
				int MinI = M;
				int MinJ = M;
				int MinK = M;
				int flag = 0;
				for (int k = 0; k < NodeCount; k++) {
					if (Edge[j][k] != M)//엣지가 무한대이면 할필요가없다
						if (Edge[j][k] > 0 && OPT[i - 1][k] > 0 && Edge[j][k] + OPT[i - 1][k] < 0) {//양의 오버플로우를 체크
						} else if (temp > Edge[j][k] + OPT[i - 1][k]&&OPT[i-1][k]!=M) {//temp보다 작을경우
							if(OPTPath[i-1][k][j]!=true)//사이클 확인 if문
							{temp = Edge[j][k] + OPT[i - 1][k];
							MinI = i-1;
							MinJ = j;
							MinK = k;
							flag = 1;}//사이클이 아닐때 저장하여 기억함
						}

				}
				
				if (OPT[i - 1][j] <= temp) {
					OPT[i][j] = OPT[i - 1][j];
					OPTPath[i][j] = OPTPath[i-1][j];
				}//temp가 더클경우에는 그냥 이전의 i-1,j를 그대로 받아오면된다.
				else {//그외는 값을 바꿔야한다
					OPT[i][j] = temp;
					if(flag==1)//변화가 있을경우에만
						{if(i!=1) {
						for(int R=0;R<NodeCount;R++)
							OPTPath[i][j][R] = OPTPath[MinI][MinK][R];//a->b--//->target의로 갈경우에
						//b--//-->target까지의 경로를 새롭게 저장한다.
						OPTPath[i][j][j] =true;}//혹시 초기값이 수정될수도있으니 해당 인덱스를 다시 초기화한다
					OPTPath[i][j][Target] =true;//새로운 값을 발견했다는건 target에 도착했다는 뜻임으로  true로한다
					OPTPath[i][j][MinK] =true; //새롭게 연결한값이 flag가 아닐가능성도 있으니 한번더 선언
					}
				}
			}
		}
		for (int i = 0; i < NodeCount + 1; i++) {
			for (int j = 0; j < NodeCount; j++) {
				if (OPT[i][j] == M) {
					System.out.print("   ∞");
				} else
					System.out.printf("%4d", OPT[i][j]);
			}
			System.out.print("\n");
		}
		System.out.println("mincost : "+OPT[NodeCount][start]);
		for(int p =0;p<NodeCount;p++)
		System.out.println(OPTPath[NodeCount][start][p]);
	}

}
