
public class Dijkstra {

	public static void main(String[] args) {
		int M=Integer.MAX_VALUE;//가중치에서 최대값은 연결안된 상태를 나타낸다.
		int[][] W = {
					{M,10,3,M,M},
					{M,M,1,2,M},
					{M,4,M,8,2},
					{M,M,M,M,7},
					{M,M,M,9,M}
					};//정점과 간선의 가중치들을 선언하였다.
		int[] d = {0,M,M,M,M};//a에서 다른곳까지 가는 최단거리를 저장하는 배열
		String[] complete = new String[d.length];
		HeapPriorityQueue Q= new HeapPriorityQueue(10);
		int compoint =0;
		Q.insert(0, "A");
		Q.insert(M, "B");
		Q.insert(M, "C");
		Q.insert(M, "D");
		Q.insert(M, "E");//일단 q에 모든값을 삽입
		node temp = Q.extract_min();
		complete[compoint++]=temp.ch;
		System.out.print("S["+(compoint-1)+"] : d["+complete[compoint-1]+"] = "+temp.Priority+" \n\n");
		while(Q.size!=0){
		int u = returnNumber(temp.ch);
		for(int s =0;s<Q.size;s++) {
		int v = returnNumber(Q.a[s].ch);
		System.out.print("Q["+s+"] : d["+Q.a[s].ch+"] = "+d[v]);
		if(d[u]+W[u][v]>0&&d[u]+W[u][v]<d[v])
		{//overflow를 생각해서 0보다큰 조건을 추가하였다.
			d[v]=d[u]+W[u][v];
			Q.chagne((s+1), d[v]);
			System.out.print(" --> "+"d["+Q.a[s].ch+"] = "+d[v] );
		}
		System.out.println();
		}
		
		temp = Q.extract_min();
		complete[compoint]=temp.ch;
		System.out.print("\nS["+compoint+"] : d["+complete[compoint]+"] = "+temp.Priority+" \n\n");
		compoint++;
		}
	}
	static public int returnNumber(String c) {
		if(c.equals("A")) {
			return 0;
		}else if (c.equals("B")){
			return 1;
		}else if (c.equals("C")){
			return 2;
		}else if (c.equals("D")){
			return 3;
		}else if (c.equals("E")){
			return 4;
		}else
			return -1;
		//문자열을 인덱스로 바꾸는 함수
	}
	static class node {
		 String ch;
		 int Priority;
		 public node(int Priority,String ch){
			 this.Priority=Priority;
			 this.ch=ch;
		 }//문자열과 우선순위를 저장
	}
static class HeapPriorityQueue {
		private static final int CAPACITY = 10;
		
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
				if (a[i].Priority <= P) {
					a[j]=new node(P,L);
					return;
				}
				a[j] = a[i];
			}
			a[i]=new node(P,L);//다끝나고 난뒤 남은 부모인덱스가 새로들어온 정보가 저장되면된다.
		}
		
		public node min() {
			if (size == 0)
			{	System.out.println("작업이 더이상없습니다.");
				throw new java.util.NoSuchElementException();}
			return a[0];
		}//우선순위가 가장높은걸 리턴한다

		public node extract_min() {
			node max = min();
			a[0] = a[--size];
			heapify(0, size);//현재 max값이 사라졌으니 0을 기준으로 heapify 해준다.
			return max;
		}//가장높은 값을 제거하면서 꺼낸다

		public node pop(int x) {
			node temp = a[x-1];
			a[x-1] = a[--size];//삭제할 노드에 현재가장 아래단에 있는 리프 노드를 넣고 사이즈를 줄인다
			heapify(x-1, size);
			return temp;
		}
		public void heapify(int i, int n) {//heapify하고싶은 인덱스와 사이즈를 입력한다
			node ai = a[i];//일단 현재 인덱스(부모노드)를 저장한다
			while (i < n / 2) {//i가 리프노드가 아니라면 계속 실행한다
				int j = 2 * i + 1;//j는 자식노드의 인덱스이다
				if (j + 1 < n && a[j + 1].Priority < a[j].Priority)
					++j;//양쪽의 자식값중 더작은값을 골라내는 작업이다.
				if (a[j].Priority >= ai.Priority)
					break;
				a[i] = a[j];
				i = j;
			}
			a[i] = ai;//다끝난뒤 i값이 heap이 시작될때 부모값의 자리이다.
		}

		public int size() {
			return size;
		}
		public void chagne(int x, int y) {
			if(a[x-1].Priority>=y){
			a[x-1].Priority =y;
			while(x!=1)
			{heapify(x/2-1, size);
			x=(x/2);
			}}//값이 바꿔져서 현재의 min heap은 깨졌다.
			//현재의 부모로가서 heapify를 하면서 root까지 heapify 해준다.
			else {
				a[x-1].Priority =y;
				{heapify(x-1, size);
				}//그럴경우 heapify로 위치를 맞춰준다.
			}	
		}



		private void resize() {
			node[] aa = new node[2 * a.length];
			System.arraycopy(a, 0, aa, 0, a.length);
			a = aa;
		}//크기를 리사이즈함
}}
