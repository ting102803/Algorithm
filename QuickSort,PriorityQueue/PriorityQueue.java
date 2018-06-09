import java.util.*;
import java.io.*;

public class PriorityQueue {
	public static void clearScreen() {
	    for (int i = 0; i < 5; i++)
	      System.out.println("");
	  }//프로그램 실행간 간격을 알려주기위한 메소드
	public static void printset() {
		System.out.println("---------------------------------------------------");
		System.out.println("1.작업 추가  2.최대값  3.최대 우선순위 작업 처리");
		System.out.println("4.원소 키값 증가  5.작업제거  6.종료");
		System.out.println("---------------------------------------------------");
	  }//프로그램 입력을 안내하는 메소드
	
	static Scanner sc1 = new Scanner(System.in);
	static Scanner sc2 = new Scanner(System.in);
	//사용자의 입력을 받는 스캐너
	public static void main(String[] args) {
		HeapPriorityQueue data = new HeapPriorityQueue();
		String Snumber,Slecture,line;
		try {
			FileReader fr = new FileReader("data_heap.txt");
			BufferedReader br = new BufferedReader(fr);//텍스트파일을 읽어온다.
			line = br.readLine();//한줄씩 입력받는다.
			while (line != null) //줄이 없어질때까지 받음
			{StringTokenizer tk = new StringTokenizer(line, ","); //tk라는 분해기가 쉼표마다 단어를 자른다
	
				while(tk.hasMoreTokens()){
					Snumber = tk.nextToken();
					Slecture = tk.nextToken();
					data.insert(Integer.parseInt(Snumber),Slecture);
					}//입력을 받으며 insert해주며 힙을 구성한다
				line = br.readLine();// txt파일의 커서를 다음으로 옮기고, 다음 줄의 내용을 line에 저장한다.
					
			}br.close();}// 자원소모 최소화
	catch (IOException e) {}
	System.out.println("**현재 우선 순위 큐에 저장되어 있는 작업 대기 목록은 다음과 같습니다.**");
	System.out.println(data);
	printset();
	int sw = -1;//입력에 따라 실행할 명령어 지정
	while (sw!=6){
		sw = new Scanner(System.in).nextInt();//어떤 메소드를 할지숫자를 입력 받음
		switch(sw){
		case 1: {
			System.out.print("추가할 과목명을 입력해주세요");
			String temp = sc1.nextLine();
			System.out.print("우선순위를 입력하여주세요");
			int Prioritytemp = sc2.nextInt();
		data.insert(Prioritytemp, temp);//읽은 과목명과 우선순위값을 insert한다
		clearScreen();
		System.out.println("**추가후 현재 우선 순위 큐에 저장되어 있는 작업 대기 목록은 다음과 같습니다.**");
		System.out.println(data);
		printset();
		break;
		} 
		case 2: {
		String max = data.max().lecture;//가장높은 우선순위의 강의값을 저장
		System.out.println("우선순위가 가장높은 강의는 "+max+"입니다.");
		break;
		}	
		case 3: {
			data.extract_max();//최대작업을 수행
			clearScreen();
			System.out.println("**처리를 완료하였습니다.**");
			System.out.println(data);
			printset();
			break;
		}
		case 4:{
			System.out.print("몇번째 과목의 우선순위를 증가시키겠습니까??");
			int x = sc1.nextInt();
			if(x>data.size||x<1) {
				System.out.println("잘못된 큐번호를 입력하였습니다. 현재작업을 종료합니다. 어떤작업을 하시겠습니까?");
				break;
			}//잘못된 입력이 들어올경우 에러 메세지 출력
			System.out.print("몇으로 변경하시겠습니까?");
			int y = sc2.nextInt();
			data.increase_key(x,y);
			//키값 변경 메소드 실행
			clearScreen();
			System.out.println("**증가시킨후 현재 우선 순위 큐에 저장되어 있는 작업 대기 목록은 다음과 같습니다.**");
			System.out.println(data);
			printset();
			break;
			
		}
		case 5:{
			System.out.print("삭제하실 큐는 몇 번째 입니까?");
			int x = sc1.nextInt();
			if(x>data.size||x<1) {
				System.out.println("잘못된 큐번호를 입력하였습니다. 현재작업을 종료합니다. 어떤작업을 하시겠습니까?");
				break;
			}//잘못된 입력이 들어올경우 에러 메세지 출력
			data.delete(x);//x번째 작업 삭제
			clearScreen();
			System.out.println("**삭제후 현재 우선 순위 큐에 저장되어 있는 작업 대기 목록은 다음과 같습니다.**");
			System.out.println(data);
			printset();
			break;
		}
		case 6:{
			System.out.println("프로그램을 종료합니다.");
		break;
		}
		
		default : {
			System.out.println("숫자를 다시 입력하세요");}
		}}//숫자 잘못입력시 발생하는 에러
	}



	}
class HeapPriorityQueue {
	private static final int CAPACITY = 10;
	class node {
		 String lecture;
		 int Priority;
		 public node(int Priority,String lecture){
			 this.Priority=Priority;
			 this.lecture=lecture;
		 }//우선순위라 강의이름을 저장하기위한 노드 생성
	}
	public node[] a;
	public int size;//사이즈를 저장하기위한 변순



	public HeapPriorityQueue() {
		this(CAPACITY);
	}//기본 생성시 크기 10으로 생성한다

	public HeapPriorityQueue(int capacity) {
		a = new node[capacity];
	}

	public void insert(int P,String L) {
	
		if (size == a.length)
			resize();//크기가 부족할경우 resize 한다
		int i = size++;//맨끝 리프노드에 일단 삽입후 알맞은 자리를 찾는식이다.
		while (i > 0) {
			int j = i;//현재 위치를 저장
			i = (i - 1) / 2;//부모 인덱스를 구한후 저장한다
			if (a[i].Priority >= P) {
				a[j]=new node(P,L);
				return;//만약 부모노드가 현위치보다 선호도가 높다면 올바른 max heap이기때문에 그대로 종료한다
			}
			a[j] = a[i];//그게 아니라면 부모가 더작기때문에 자식위치에 부모를 저장한다
		}
		a[i]=new node(P,L);//다끝나고 난뒤 남은 부모인덱스가 새로들어온 정보가 저장되면된다.
	}
	
	public node max() {
		if (size == 0)
		{	System.out.println("작업이 더이상없습니다.");
			throw new java.util.NoSuchElementException();}
		return a[0];
	}//우선순위가 가장높은걸 리턴한다

	public node extract_max() {
		node max = max();
		a[0] = a[--size];
		heapify(0, size);//현재 max값이 사라졌으니 0을 기준으로 heapify 해준다.
		return max;
	}//가장높은 값을 제거하면서 꺼낸다
	public void increase_key(int x, int y) {
		if(a[x-1].Priority<=y){//현재값보다 높은값이 들어올경우
		a[x-1].Priority =y;//일단 우선순위를 변경시켜준다
		while(x!=1)//x가 1이면 최대값이기때문에 해줄필요가없다.
		{heapify(x/2-1, size);
		x=(x/2);
		}}//값이 증가했으므로 현재의 max heap은 깨졌다.
		//현재의 부모로가서 heapify를 하면서 root까지 heapify 해준다.
		else {//만약 감소하는 값이면 부모에 입장에선 여전히 작은값이다 하지만 아래로 자식들을 보면 heapify가 깨졌을수도있다.
			a[x-1].Priority =y;
			{heapify(x-1, size);
			}//그럴경우 heapify로 위치를 맞춰준다.
		}	
	}
	public node delete(int x) {
		node temp = a[x-1];
		a[x-1] = a[--size];//삭제할 노드에 현재가장 아래단에 있는 리프 노드를 넣고 사이즈를 줄인다
		heapify(x-1, size);//새로 자리를 찾은 인덱스는 max_heap을 만족하지 않기때문에 heapify한다
		return temp;
	}
	public void heapify(int i, int n) {//heapify하고싶은 인덱스와 사이즈를 입력한다
		node ai = a[i];//일단 현재 인덱스(부모노드)를 저장한다
		while (i < n / 2) {//i가 리프노드가 아니라면 계속 실행한다
			int j = 2 * i + 1;//j는 자식노드의 인덱스이다
			if (j + 1 < n && a[j + 1].Priority > a[j].Priority)
				++j;//양쪽의 자식값중 더큰값을 골라내는 작업이다.
			if (a[j].Priority <= ai.Priority)
				break;//자식들중 큰값이 부모인덱스보다 작으면 heapify는 그대로 끝내면된다
			a[i] = a[j];//자식의 값이 더크면 자식값을 부모노드에 저장한다
			i = j;//그다음 바뀐 자식을 다시 부모로 하고 진행한다
		}
		a[i] = ai;//다끝난뒤 i값이 heap이 시작될때 부모값의 자리이다.
	}

	public int size() {
		return size;
	}

	public String toString() {
		if (size == 0)
			return "";
		StringBuffer buf = new StringBuffer("1- "+a[0].Priority +" "+a[0].lecture);
		for (int i = 1; i < size; i++)
			buf.append("\n"+(i+1)+" -  " + a[i].Priority+"  "+a[i].lecture);
		return buf+"";
	}//node들을 출력하는 형식



	private void resize() {
		node[] aa = new node[2 * a.length];
		System.arraycopy(a, 0, aa, 0, a.length);
		a = aa;
	}//크기를 리사이즈함

}