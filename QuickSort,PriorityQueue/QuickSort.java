import java.util.*;
import java.io.*;

public class QuickSort {
	public static int partition(int A[], int left, int right)
	{
	      int i = left, j = right-1;
	      int temp;
	      int key = A[right];//배열의 마지막값을 비교값으로 설정
	     
	      while (j!=i-1) {//j와 i가 교차될때까지 한다
	           if(A[i]<=key) {//인덱스가 낮은 쪽에는 key값보다 낮은값이 쌓여야함으로 작다면 다음 인덱스로 간다
	        	   i++;
	           }
	           else if(key<A[j]) {//인덱스가 높은 쪽에는 key값보다 높은값이 쌓여야함으로 크다면 다음 인덱스로 간다
	        	   j--;
	           }
	           else {
	        	   temp=A[i];
	        	   A[i]=A[j];
	        	   A[j]=temp;  
	           }//둘다 아니라면 위치를 서로 swap해준다
	      }
	      A[right]=A[i];
	      A[i]=key;//정렬이 끝난뒤에는 i와 마지막값을 swap해준다
	      return i;//끝난뒤의 중심 인덱스인 i의 index를 리턴한다
	}
	 
	public static void quickSort(int A[], int left, int right) {
	      int key = partition(A, left, right);//여기서 key인덱스를 기준으로 왼쪽은 key보다 낮은값 오른쪽은 key보다 큰값이 정렬된다
	      if (left < key - 1)
	            quickSort(A, left, key - 1);//키값보다 하나 낮은 인덱스까지를 다시 퀵소트한다
	      if (key < right)
	            quickSort(A, key+1, right);//키값보다 하나 큰 인덱스부터 다시 퀵소트한다.
	}
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
	
	 quickSort(data,0,count-1);
	
	long end = System.nanoTime();//정렬 끝날때 시간을 저장하는 메소드
	System.out.println("걸린시간 : "+((end - start)/(1000000000.0))+"초입니다.");
	//나노초 이기때문에 나눠주어서 나타냈다. 정렬에 걸린시간을 출력하였다.
	try {//파일 출력부분
		FileWriter fw = new FileWriter("201302423_quick.txt");
		BufferedWriter bw = new BufferedWriter(fw);
		for (int m = 0; m < count-1; m++) {
			bw.append(String.valueOf(data[m])+" ");}
		bw.append(String.valueOf(data[count-1]));//마지막 배열의 값엔 스페이스바가 없다
		bw.flush();//입력된 내용들을 파일에 출력한다.
		bw.close();}//파일을 닫아 자원들을 반환함
	catch(IOException e) {System.out.println("오류가있습니다.");}}
	}//파일 에러시 에러메세지
	
	
	


