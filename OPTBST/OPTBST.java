import java.io.*;
import java.util.*;

public class OPTBST {

	public static void main(String[] args) {
		double[] Pi = new double[100];// p가 k 배열의 크기는 넉넉하게 100으로 했다.
		double[] Qi = new double[100];// q가 d 배열의 크기는 넉넉하게 100으로 했다.
		String line;
		int M = Integer.MAX_VALUE;
		int count = 0;// 현재 빈배열중 가장 앞에 배열의 위치를 나타내는 상수이다
		try {
			FileReader fr = new FileReader("data11.txt");
			BufferedReader br = new BufferedReader(fr);// 텍스트파일을 읽어온다.
			line = br.readLine();// 한줄씩 입력받는다.
			while (line != null) // 줄이 없어질때까지 받음
			{
				StringTokenizer tk = new StringTokenizer(line, "\t, "); // tk라는 분해기가 띄어쓰기마다 단어를 자른다
				while (tk.hasMoreTokens()) {
					Pi[count] = Double.parseDouble(tk.nextToken());// 처음 오는 수를 저장한다.
					Qi[count++] = Double.parseDouble(tk.nextToken());
				}
				line = br.readLine();
			}
			 // txt파일의 커서를 다음으로 옮기고, 다음 줄의 내용을 line에 저장한다.
			br.close();
		} // 자원소모 최소화
		catch (IOException e) {
		}
		double[][] Ei = new double[count+1][count];
		double[][] Wi = new double[count+1][count];
		int[][] root = new int[count][count];
		for(int i = 1;i<count+1;i++) {
			Ei[i][i-1]=Qi[i-1];
			Wi[i][i-1]=Qi[i-1];
		}
		int j;
		for(int l=1;l<count;l++) {
			for(int i =1;i<count-l+1;i++) {
				j=i+l-1;
				Ei[i][j]=M;//초기화
				Wi[i][j]=Wi[i][j-1]+Pi[j]+Qi[j];//이전에 구한값들을 이용해서 W를 구한다
				for(int r=i;r<=j;r++) {
					double t = Ei[i][r-1]+Ei[r+1][j]+Wi[i][j];//새롭게구한 i~j에서 r을 선택할경우 평균비용이다
					if (t<Ei[i][j]) {//만약 더적을경우 최적값을 찾았기때문에 갱신한다.
						Ei[i][j]=t;
						root[i][j]=r;
					}
				}
			}
			
		}
		
		System.out.printf("최적 비용 : %.4f\n루트로 선택된 인덱스 :  %d",Ei[1][count-1],root[1][count-1]);
	}

}
