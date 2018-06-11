import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Inversion {
	static int counting =0;
	public static void main(String[] args) {
		int[] temp = new int[100000];//배열의 크기는  넉넉하게 10만으로 했다.
		String Snumber,line;
		int count = 0;// 현재 빈배열중 가장 앞에 배열의 위치를 나타내는 상수이다
		try {
			FileReader fr = new FileReader("data05_inversion_01.txt");
			BufferedReader br = new BufferedReader(fr);//텍스트파일을 읽어온다.
			line = br.readLine();//한줄씩 입력받는다.
			while (line != null) //줄이 없어질때까지 받음
			{StringTokenizer tk = new StringTokenizer(line, " "); //tk라는 분해기가 띄어쓰기마다 단어를 자른다
				while(tk.hasMoreTokens()){
					Snumber = tk.nextToken();//분리기로 처음 자른걸(이름이 처음으로 잘린다) name에 저장한다 그후 커서를 뒤로 옮긴다
					temp[count++] =  Integer.parseInt(Snumber);
						}// txt파일의 커서를 다음으로 옮기고, 다음 줄의 내용을 line에 저장한다.
				line = br.readLine();}br.close();}// 자원소모 최소화
	catch (IOException e) {}
	int[] data = new int[count];
	System.arraycopy(temp, 0, data, 0, count);//크기에 맞게 바꿔준다.
	System.out.print("input data : ");
	for(int i=0;i<count;i++) {
		System.out.print(data[i]+" ");}
	System.out.println("");//현재 input된 data를 출력한다.
	
	data=CountInversion(data,0,count-1);//sort된 배열을 리턴
	
	System.out.print("Sort data : ");
	for(int i=0;i<count;i++) {
	System.out.print(data[i]+" ");}
	System.out.println("");
	System.out.print("InversingCount : "+counting);
}
	static int[] CountInversion(int[] input,int start,int end){
		if(end-start==0) {
			return input;
		}//배열이 하나라면 리턴
	
		int[] temp1 = new int[(end-start+1)/2];
		System.arraycopy(input, start, temp1, 0, (end-start+1)/2);
		int[] temp2 = new int[(end-start+1)-((end-start+1)/2)];
		System.arraycopy(input, (end-start+1)/2-start, temp2, 0, (end-start+1)-((end-start+1)/2));
		//배열을 반으로 잘라서 저장해준다
		temp1 = CountInversion(temp1,0,(end-start+1)/2-1);
		temp2 = CountInversion(temp2,0,(end-start+1)-((end-start+1)/2)-1);
		//나눈 배열들도 CountInversion 시켜준다.
		input = MergeSort(temp1,(end-start+1)/2,temp2,(end-start+1)-(end-start+1)/2);
		//나눈 배열을 정렬하면서 합쳐준다.
		return input;
	}
	static int[] MergeSort(int[] input1,int input1size,int[] input2,int input2size) {
		int[] result = new int [input1size+input2size];
		int i=input1size;
		int j=input2size;//i와 j는 남은 배열의 수를 관리하기위해 선언하였다.
		int a=0,b=0,k=0;//배열의 접근을 위한 변수 선언
		while(i!=0&&j!=0) {//한쪽에 남은 배열이 없다면 종료한다.
			if(input1[a]>input2[b]) {
				result[k++]=input2[b];
				b++;
				j--;
				counting+=i;
				//만약 input1이 더커서 input2가 결과 배열에 들어갈때 inversion이 생긴다 그값은 현재 input1에 남은 배열의 수만큼이다.
			}
			else {
				result[k++]=input1[a];
				a++;
				i--;//input2가 더크다면 input1값을 뽑아서 결과값에 저장한다 이때는 inversion이 일어나지않는다.
			}
		}
		if(i!=0) {
			System.arraycopy(input1, a, result,b+a, input1size-a);
		}
		if(j!=0) {
			System.arraycopy(input2, b, result,b+a, input2size-b);
		}//0인 부분이 나오면 한쪽은 다썻지만 나머지 한쪽이 있으므로 그대로 저장한다.
		return result;//정렬이 완료된 배열을 리턴한다.
		
	}
}