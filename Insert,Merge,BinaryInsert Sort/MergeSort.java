import java.util.*;
import java.io.*;

public class MergeSort {
	
public static void merge(int[] first,int[]second,int[] third) {
	int firstI = 0;
	int secondI = 0;
	int thirdI = 0;//인자로 세개가 들어오는데 앞에 두개를 합치면서 세번째 배열에 정렬하는것이다
	
	while (firstI<first.length) {
		if(secondI < second.length) {//아직 비교할 갯수가 서로 남아있다면
			if(first[firstI] < second[secondI]){//만약 두번째 배열의 남은값이 더크다면
				third[thirdI] = first[firstI];//더 작은 첫번째 배열의 남은값을 합친 배열에 넣어준다
				firstI++;
			}else {
				third[thirdI] = second[secondI];//그게 아니라면 더 작은 값을 가진 두번째배열의 남은값을 합친 배열에 넣어준다
				secondI++;
			}
			thirdI++;
		} else {
			while(firstI<first.length) {//first 배열값은 아직남아있지만 second 배열값이 없을경우에는 남은 first배열을 다 합친배열에 넣어준다
				third[thirdI++] = first[firstI++];
			}
		}
		
	}
	while(secondI<second.length) {
		third[thirdI++]=second[secondI++];//반대로 first 배열이 비었을경우 second배열값을 모두 합친 배열에 저장한다.
	}
	}
public static int[] mSort(int[] data) {//배열을 인자로 받고 배열을 리턴하는 메소드이다.
	int size = data.length;
	
	if (size ==1 ) {
		return data;}//크기가 1이란말은 하나의 배열이고 정렬이 되어있는 상태이다.
	
	int[] datatemp1 = new int [size/2];
	int[] datatemp2 = new int [size-size/2];//배열을 자르기위해 선언
	
	System.arraycopy(data, 0, datatemp1, 0, size/2);
	System.arraycopy(data, (size/2), datatemp2, 0, size-(size/2));
	//배열을 반반씩 저장하는 과정이다. 
	datatemp1=mSort(datatemp1);
	datatemp2=mSort(datatemp2);//나눈것도 mergesort 시킨다
	merge(datatemp1,datatemp2,data);//나눈것들은 data로 비교하며 합병한다.
	return data;
	
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
					data[count++] =  Integer.parseInt(Snumber);
				
					line = br.readLine();}// txt파일의 커서를 다음으로 옮기고, 다음 줄의 내용을 line에 저장한다.
					br.close();}}// 자원소모 최소화
	catch (IOException e) {}
	int[] countdata = new int [count];
	System.arraycopy(data, 0, countdata, 0, count);//합병정렬의 경우에는 배열전체를 자르고 합치는 과정이 있어서 빈배열이있으면 제대로 되지않기때문에
	//읽은 숫자와 크기가 같은 배열로 재선언후 arraycopy로 값을 옮겨야한다.
	
	long start = System.nanoTime();
	
	countdata = mSort(countdata);
		
	long end = System.nanoTime();
	System.out.println("걸린시간 : "+((end - start)/(1000000000.0))+"초입니다.");
	//걸린시간 출력

	
	try {
		FileWriter fw = new FileWriter("201302423_output.txt");
		BufferedWriter bw = new BufferedWriter(fw);
		for (int m = 0; m < count-1; m++) {
			bw.append(String.valueOf(countdata[m])+" ");}
		bw.append(String.valueOf(countdata[count-1]));//변경된 countdata배열로 출력하여야하며 마지막은 공백이 없다.
		bw.flush();//입력된 내용들을 파일에 출력한다.
		bw.close();}//파일을 닫아 자원들을 반환함
	catch(IOException e) {System.out.println("오류가있습니다.");}//오류 표시
	
	}
	}


