import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Lateness {
	public static void main(String[] args) {

	int[] workT = new int[1000];//배열의 크기는  넉넉하게 1000으로 했다. 걸리는 시간과 처음의 갯수를 저장함
	int[] deadT = new int[1000];//배열의 크기는  넉넉하게 1000으로 했다. 끝나는 시간을 저장
	String work,dead,line;
	int count = 0;// 현재 빈배열중 가장 앞에 배열의 위치를 나타내는 상수이다
	try {
		FileReader fr = new FileReader("data06_lateness.txt");
		BufferedReader br = new BufferedReader(fr);//텍스트파일을 읽어온다.
		line = br.readLine();//한줄씩 입력받는다.
		while (line != null) //줄이 없어질때까지 받음
		{StringTokenizer tk = new StringTokenizer(line, " "); //tk라는 분해기가 띄어쓰기마다 단어를 자른다
			while(tk.hasMoreTokens()){
				work = tk.nextToken();//처음 오는 수를 저장한다.
				
				if(tk.hasMoreTokens()) {//만약 다음토큰이 있을경우에는 받는다
				dead = tk.nextToken(); //다음 토큰이 없다는것은 첫줄을 읽은상태이다.
				deadT[count] = Integer.parseInt(dead);}
				workT[count++] =  Integer.parseInt(work);
				}
				line = br.readLine();	// txt파일의 커서를 다음으로 옮기고, 다음 줄의 내용을 line에 저장한다.
				}br.close();
				}// 자원소모 최소화
catch (IOException e) {}
	int n= workT[0];//0번째 배열엔 읽은 일의 갯수가 저장되어있다.
	int time = 0;//시작시간은 0으로 초기화
	int lateness = 0;//lateness는 최소 0이기때문에 0으로 초기화했다.
	System.out.println("Input Data : \n" + n);
	for(int i=1;i<n+1;i++) {//0에는 일의 갯수라서 1부터 읽으면서 for문을 진행한다
		System.out.println(workT[i]+ "  " +deadT[i]);
	time+=workT[i];//일단 일한 시간을 현재 time에 더한다
	if(deadT[i]<time) {//그시간이 종료시간을 넘었으면 Lateness 발생
		if(lateness<time-deadT[i])//현재 저장된 lateness보다 크다면
			lateness=time-deadT[i];//lateness 값을 바꿔준다.
	}
	}
	System.out.println("Output Data : "+lateness);
}}
