import java.io.*;
import java.util.*;

public class HuffmanCode {
	public static void main(String[] args) {
		HashMap<Character, String> result = new HashMap<Character, String>();
		// 최종적으로 만들어지는 허프만코드들을 저장하는공간
		HeapPriorityQueue tree = new HeapPriorityQueue();
		// 허프만 코드를 구현하는데 필요한 우선순위 큐 선언
		HashMap<Character, Integer> huf = new HashMap<Character, Integer>();
		// 문자열과 가중치가 들어가 구성하는 해쉬맵 허프만트리를 구현하기위해 쓰이는 공간
		String word = "";
		
		try {
			BufferedReader in = new BufferedReader(new FileReader("data06_huffman.txt"));
			String line = in.readLine();
			System.out.println("Input data : ");
			while (line != null) {
				StringTokenizer parser = new StringTokenizer(line, " ,:;-.?!");
				while (parser.hasMoreTokens()) {
					word = parser.nextToken();
					System.out.println(word);
					for (int i = 0; i < word.length(); i++) {
						char temp = word.charAt(i);
						if (huf.containsKey(temp))
							huf.put(temp, huf.get(temp) + 1);
						else
							huf.put(temp, 1);// 해쉬맵을 이용해서 문자를 읽으면서 가중치를 더한다 최초이면
												// 1로지정 이미 있는 거라면 +1을 해준다.
					}}
				line = in.readLine();
			}
			in.close();
		} catch (IOException e) {}
		
		for (char Cha : huf.keySet()) {
			// 현재 해쉬맵에 있는 문자를 한개씩 읽으면서 인쇄할방법을 찾다가 for문기능중 ':'가 있다는걸 알았다 Cha에
			// huf.keyset값을 한개씩 넣으면서 진행하는 for문이다
			Huffman temp = new Huffman(Cha, huf.get(Cha));
			tree.add(temp);// 그와 동시에 그값들을 우선순위 큐에 추가한다.
		}
		Huffman code = new Huffman(' ', 0);
		code = code.maketree(tree, tree.size());// 우선순위 큐를 이용하여 허프만트리를 만든다.

		makecode(code, "", result);// 만들어진 허프만 트리를 이용하여 허프만코드를 만든다.

		System.out.println("\nOutPutData : ");
		
		for (char Cha : result.keySet()) {
			System.out.println(Cha + " : " + result.get(Cha));
		} // 허프만 코드를 출력한다

	}
	
	

	public static class HeapPriorityQueue {
		private static final int CAPACITY = 100;
		private Comparable[] a;
		private int size;

		public HeapPriorityQueue() {
			this(CAPACITY);
		}

		public HeapPriorityQueue(int capacity) {
			a = new Comparable[capacity];
		}

		public void add(Object object) {
			if (!(object instanceof Comparable))
				throw new IllegalArgumentException();
			Comparable<?> x = (Comparable<?>) object;
			if (size == a.length)
				resize();
			int i = size++;
			while (i > 0) {
				int j = i;
				i = (i - 1) / 2;
				if (a[i].compareTo(x) <= 0) {
					a[j] = x;
					return;//이번엔 Min Heap이다 CompareTo는 a[i]의 빈도수에서 x의 빈도수를 뺀것이다 그값이 0보다 작으면 minheap을 만족하기때문에 그대로 저장하고 리턴한다.
				}
				a[j] = a[i];
			}
			a[i] = x;
		}

		public Object best() {
			if (size == 0)
				throw new java.util.NoSuchElementException();
			return a[0];
		}//최대값을 가져온다

		public Object remove() {
			Object best = best();
			a[0] = a[--size];
			heapify(0, size);
			return best;
		}//Min값을 꺼내면서 다시 heapify한다

		public int size() {
			return size;
		}

		private void heapify(int i, int n) {
			Comparable ai = a[i];
			while (i < n / 2) {
				int j = 2 * i + 1;
				if (j + 1 < n && a[j + 1].compareTo(a[j]) <= 0)
					++j;
				if (a[j].compareTo(ai) >= 0)
					break;
				a[i] = a[j];
				i = j;
			}
			a[i] = ai;
		}//heapify부분이다 max와 부등호만 반대로하면된다

		private void resize() {
			Comparable[] aa = new Comparable[2 * a.length];
			System.arraycopy(a, 0, aa, 0, a.length);
			a = aa;
		}
		
}
	
	
	public static class Huffman implements Comparable<Object> {
		char alphabet;
		int freq;
		Huffman lchild;
		Huffman rchild;

		public Huffman(char A, int B) {
			this.alphabet = A;
			this.freq = B;
		}

		public int compareTo(Object object) {
			if (!(object instanceof Huffman))
				throw new IllegalArgumentException();
			Huffman that = (Huffman) object;
			return this.freq - that.freq;// 두개의 가중치를 비교하는 compareTo함수이다
		}

		public Huffman maketree(HeapPriorityQueue A, int n) {// 우선순위 큐인 tree를 이용하여서
																// 최종적으로 사용할 허프만 트리를
																// 구현하는 과정이다
			for (int i = 1; i < n; i++) {
				Huffman temp = new Huffman(' ', 0);
				temp.lchild = (Huffman) A.remove();
				temp.rchild = (Huffman) A.remove();// string값이 공백이고 왼쪽오른쪽 자식이 현재
													// 우선순위큐에있는 최소가중치 두개를 뽑아서 만든다.

				temp.freq = temp.rchild.freq + temp.lchild.freq;// 해당 가중치는 자식두개를
																// 더한값으로 한다
				A.add(temp);// 그리고 그 트리도 다시 들어가서 허프만코드를 구성한다
			} // 총 갯수가 n개라면 n-1번하면 모두 완성된다.
			return (Huffman) A.remove();// 그럼 최종적으로있는 루트에있는 트리가 허프만트리가 구현되었으니 리턴한다

		}

	}
	public static void makecode(Huffman A, String S, HashMap<Character, String> B) {
		if (A == null)
			return;
		makecode(A.rchild, S + "1", B);// 오른쪽 자식으로 갈경우 '1'스트링을 더하고 계속 실행
		makecode(A.lchild, S + "0", B);// 왼쪽 자식으로 갈경우 '0'스트링을 더하고 계속 실행
		if (A.alphabet != ' ') {// 만약 허프만코드의 문자가 공백이아니라면 해당문자열에 도착하였음으로 그값을 해쉬맵인
								// B에 저장한다
			B.put(A.alphabet, S);// 순회하는 방식으로 코드를 구현하였다.
		}
	}	

}
