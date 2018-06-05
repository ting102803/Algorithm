import java.util.*;
import java.io.*;

public class InsertSort {

	public static void main(String[] args) {
		int[] data = new int[10000000];//배열의 크기는 최대인 천만이다
		String Snumber,line;
		int count = 0;// 숫자를 얼마나 읽은지 알기위해 사용하였다.
		try {
			FileReader fr = new FileReader("input.txt");
			BufferedReader br = new BufferedReader(fr);//텍스트파일을 읽어온다.
			line = br.readLine();//한줄씩 입력받는다.
			while (line != null) //줄이 없어질때까지 받음
			{StringTokenizer tk = new StringTokenizer(line, " "); //tk라는 분해기가 띄어쓰기마다 단어를 자른다
				while(tk.hasMoreTokens()){
					Snumber = tk.nextToken();
					data[count++] =  Integer.parseInt(Snumber);//자른 문자열은 String이기때문에 Int로 변형한다
				
					line = br.readLine();}// txt파일의 커서를 다음으로 옮기고, 다음 줄의 내용을 line에 저장한다.
					br.close();}}// 자원소모 최소화
	catch (IOException e) {}
	long start = System.nanoTime();// 삽입정렬 시간을 측정하기위한 메소드 선언
	for(int i =1;i<count;i++) {//배열인덱스 0을 기준으로 증가할수록 정렬한다고 생각하고 구현하였다.
		int key = data[i];//key는 아직 정렬안된 숫자값이다
		int com = i-1;//정렬된 숫자값중 큰값부터 들어가면서 비교를 한다
		while(com >= 0 && key < data[com]) {//키값이 더작아을경우에는 계속 탐색을해야한다
			data[com+1] = data[com]; 
			com = com -1;//키값이 비교할숫자보다 클때까지 계속 한칸씩 옴긴다
		}
		data[com+1]=key;//찾을경우에는 그자리가 key값의 자리이다.
		}
	long end = System.nanoTime();//정렬 끝날때 시간을 저장하는 메소드
	System.out.println("걸린시간 : "+((end - start)/(1000000000.0))+"초입니다.");
	//나노초 이기때문에 나눠주어서 나타냈다. 정렬에 걸린시간을 출력하였다.
	try {//파일 출력부분
		FileWriter fw = new FileWriter("201302423_output.txt");
		BufferedWriter bw = new BufferedWriter(fw);
		for (int m = 0; m < count-1; m++) {
			bw.append(String.valueOf(data[m])+" ");}
		bw.append(String.valueOf(data[count-1]));//마지막 배열의 값엔 스페이스바가 없다
		bw.flush();//입력된 내용들을 파일에 출력한다.
		bw.close();}//파일을 닫아 자원들을 반환함
	catch(IOException e) {System.out.println("오류가있습니다.");}}
	}//파일 에러시 에러메세지
	
	
	


