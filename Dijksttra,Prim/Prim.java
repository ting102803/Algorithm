	import java.util.*;




public class Prim {
	
	public static void main(String[] args) {
		HashSet<String> chain = new HashSet<String>();
		int M=Integer.MAX_VALUE;//가중치에서 최대값은 연결안된 상태를 나타낸다.
		int[][] W = {
					{M,4,M,M,M,M,M,8,M},
					{4,M,8,M,M,M,M,11,M},
					{M,8,M,7,M,4,M,M,2},
					{M,M,7,M,9,14,M,M,M},
					{M,M,M,9,M,10,M,M,M},
					{M,M,4,14,10,M,2,M,M},
					{M,M,M,M,M,2,M,1,6},
					{8,11,M,M,M,M,1,M,7},
					{M,M,2,M,M,M,6,7,M}
					};//정점과 간선의 가중치들을 선언하였다.

		int[] d = {0,M,M,M,M,M,M,M,M};
		HeapPriorityQueue Q= new HeapPriorityQueue(10);
		String[] name = {"a","b","c","d","e","f","g","h","i"};
		Q.insert(0, "a");
		Q.insert(M, "b");
		Q.insert(M, "c");
		Q.insert(M, "d");
		Q.insert(M, "e");
		Q.insert(M, "f");
		Q.insert(M, "g");
		Q.insert(M, "h");
		Q.insert(M, "i");
		node temp = Q.extract_min();
		chain.add(temp.ch);
		System.out.print("w<"+" , " );
		System.out.println(temp.ch+"> = "+temp.Priority );
		int cost=0;
		while(Q.size!=0){
			int v=0;
			int s;
			int o=0;
			for(o = 0;o<name.length;o++) {
				if(chain.contains(name[o])){//이미 연결된 집합에 존재하고
			for(s =0;s<Q.size;s++) {
			v = returnNumber(Q.a[s].ch);
			if(W[o][v]<d[v])//값이 더 작을때만
			{	
				d[v]=W[o][v];
				Q.chagne((s+1), d[v]);
				}//값을 변경하여준다 동시에 heapify도 진행
		}}	}
			chain.add(Q.min().ch);
			int k= returnNumber(Q.min().ch);
			int p;
			for( p=0;p<d.length;p++) {
			if(W[p][k]==Q.min().Priority&&chain.contains(name[p])) {
				break;
			}
			}
			System.out.print("w<"+name[p]+", " );
			temp = Q.extract_min();
			System.out.println(temp.ch+"> = "+temp.Priority );
		cost = cost+temp.Priority;
			}
		System.out.println("W<MST> = "+cost);
	}
	static public int returnNumber(String c) {
		if(c.equals("a")) {
			return 0;
		}else if (c.equals("b")){
			return 1;
		}else if (c.equals("c")){
			return 2;
		}else if (c.equals("d")){
			return 3;
		}else if (c.equals("e")){
			return 4;
		}else if (c.equals("f")){
			return 5;}
		else if (c.equals("g")){
			return 6;}
		else if (c.equals("h")){
			return 7;
		}else if (c.equals("i")){
			return 8;}
		else return -1;
	}
	static public class node {
		 String ch;
		 int Priority;
		 public node(int Priority,String ch){
			 this.Priority=Priority;
			 this.ch=ch;
		 }
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
}
}
