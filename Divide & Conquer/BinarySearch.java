import java.io.*;
import java.util.*;

public class BinarySearch {
	static Scanner sc1 = new Scanner(System.in);
	public static void main(String[] args) {
		int[] temp = new int[100000];//배열의 크기는  넉넉하게 10만으로 했다.
		String Snumber,line;
		int count = 0;// 현재 빈배열중 가장 앞에 배열의 위치를 나타내는 상수이다
		try {
			FileReader fr = new FileReader("invariant_data.txt");
			BufferedReader br = new BufferedReader(fr);//텍스트파일을 읽어온다.
			line = br.readLine();//한줄씩 입력받는다.
			while (line != null) //줄이 없어질때까지 받음
			{StringTokenizer tk = new StringTokenizer(line, " "); //tk라는 분해기가 띄어쓰기마다 단어를 자른다
				while(tk.hasMoreTokens()){
					Snumber = tk.nextToken();//분리기로 처음 자른걸(이름이 처음으로 잘린다) name에 저장한다 그후 커서를 뒤로 옮긴다
					temp[count++] =  Integer.parseInt(Snumber);
					line = br.readLine();	}// txt파일의 커서를 다음으로 옮기고, 다음 줄의 내용을 line에 저장한다.
					br.close();}}// 자원소모 최소화
	catch (IOException e) {}
		int[] data = new int[count];
		System.arraycopy(temp, 0, data, 0, count);//크기에 맞게 바꿔준다.
		int key = -10;//입력에 따라 실행할 명령어 지정
		while (key!=-1){
			System.out.println("프로그램을 종료할려면 -1을,숫자를 탐색할려면 수를 입력하여주세요");
			key = new Scanner(System.in).nextInt();//어떤 메소드를 할지숫자를 입력 받음
			switch(key){
			case -1 :{
				System.out.println("프로그램을 종료합니다");
				break;
			}
			default : {
				int p =0;
				int q = count-1;//p와 q의 초기조건
				int mid = 0;
				while(q>=p) {//p가 q보다 클때 해당되지않기때문에 not상태인 반대를 조건으로 넣었다
					mid=(p+q)/2;
					if(key<data[mid]) {
						q=mid-1;
					}
					else if(key>data[mid]) {
						p=mid+1;
					}//이진탐색으로 검색할곳을 줄여가면서 하는 과정이다.
					else if(key==data[mid]) {
						System.out.println("찾으시는 값은 "+mid+"인덱스에 있습니다.");
						break;
					}
					if(p==count||mid==0) {
						break;//만약 없을경우를 생각해서 예외 처리를 하기위해 만들어 줬다.
					}
					
				}
				if(key==data[mid]) {
					System.out.println("검색완료");
					break;
				}else
				System.out.println("없는 숫자입니다. 숫자를 다시 입력하세요");}
			}}//숫자 잘못입력시 발생하는 에러
		
	}
	}