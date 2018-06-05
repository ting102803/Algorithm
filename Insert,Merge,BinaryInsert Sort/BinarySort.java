import java.util.*;
import java.io.*;

public class BinarySort {

	public static void main(String[] args) {
		int[] data = new int[10000000];//배열의 크기는 임의로 20으로 잡았습니다.
		String Snumber,line;
		int count = 0;// 현재 빈배열중 가장 앞에 배열의 위치를 나타내는 상수이다
		try {
			FileReader fr = new FileReader("input.txt");
			BufferedReader br = new BufferedReader(fr);//텍스트파일을 읽어온다.
			line = br.readLine();//한줄씩 입력받는다.
			while (line != null) //줄이 없어질때까지 받음
			{StringTokenizer tk = new StringTokenizer(line, " "); //tk라는 분해기가 띄어쓰기마다 단어를 자른다
				while(tk.hasMoreTokens()){
					Snumber = tk.nextToken();//분리기로 처음 자른걸(이름이 처음으로 잘린다) name에 저장한다 그후 커서를 뒤로 옮긴다
					data[count++] =  Integer.parseInt(Snumber);
				
					line = br.readLine();}// txt파일의 커서를 다음으로 옮기고, 다음 줄의 내용을 line에 저장한다.
					br.close();}}// 자원소모 최소화
	catch (IOException e) {}
		
		long start = System.nanoTime();
		
        for (int x = 1; x < count; x++ ) {
        	int low = 0;
        	int high = x;
        	int middle = x/2;//이진삽입정렬의 경우에는 반을 나누면서 이진탐색을 기반으로 한다
        	do {
        		if(data[x]>data[middle])	low = middle+1;
        		else if(data[x]<data[middle])	high=middle;//이진탐색을하면서 범위를 반씩 좁혀간다
        		else break;
        		middle=low+((high-low)/2);
        	}while(low<high);//low와 high가 교차할때까지 반복하면된다.
        	if(middle<x) {//이경우 middle이 올바른 위치이다
        		int temp = data[x];//원래있던 x값은 임시로 저장하고
        		System.arraycopy(data, middle, data, middle+1, x-middle);//한칸씩 이동해준다
        		data[middle]=temp;//검색해서 찾은 올바른위치에 저장해준다
        	}
        }
        long end = System.nanoTime();
    	System.out.println("걸린시간 : "+((end - start)/(1000000000.0))+"초입니다."); 
        //걸린시간측정
        
	try {
		FileWriter fw = new FileWriter("201302423_output.txt");
		BufferedWriter bw = new BufferedWriter(fw);
		for (int m = 0; m < count-1; m++) {
			bw.append(String.valueOf(data[m])+" ");}
		bw.append(String.valueOf(data[count-1]));
		bw.flush();//입력된 내용들을 파일에 출력한다.
		bw.close();}//파일을 닫아 자원들을 반환함
	catch(IOException e) {System.out.println("오류가있습니다.");}
	//파일 출력부분이다.
	}
	}