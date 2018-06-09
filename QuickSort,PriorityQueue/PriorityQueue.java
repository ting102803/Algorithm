import java.util.*;
import java.io.*;

public class PriorityQueue {
	public static void clearScreen() {
	    for (int i = 0; i < 5; i++)
	      System.out.println("");
	  }//���α׷� ���ణ ������ �˷��ֱ����� �޼ҵ�
	public static void printset() {
		System.out.println("---------------------------------------------------");
		System.out.println("1.�۾� �߰�  2.�ִ밪  3.�ִ� �켱���� �۾� ó��");
		System.out.println("4.���� Ű�� ����  5.�۾�����  6.����");
		System.out.println("---------------------------------------------------");
	  }//���α׷� �Է��� �ȳ��ϴ� �޼ҵ�
	
	static Scanner sc1 = new Scanner(System.in);
	static Scanner sc2 = new Scanner(System.in);
	//������� �Է��� �޴� ��ĳ��
	public static void main(String[] args) {
		HeapPriorityQueue data = new HeapPriorityQueue();
		String Snumber,Slecture,line;
		try {
			FileReader fr = new FileReader("data_heap.txt");
			BufferedReader br = new BufferedReader(fr);//�ؽ�Ʈ������ �о�´�.
			line = br.readLine();//���پ� �Է¹޴´�.
			while (line != null) //���� ������������ ����
			{StringTokenizer tk = new StringTokenizer(line, ","); //tk��� ���رⰡ ��ǥ���� �ܾ �ڸ���
	
				while(tk.hasMoreTokens()){
					Snumber = tk.nextToken();
					Slecture = tk.nextToken();
					data.insert(Integer.parseInt(Snumber),Slecture);
					}//�Է��� ������ insert���ָ� ���� �����Ѵ�
				line = br.readLine();// txt������ Ŀ���� �������� �ű��, ���� ���� ������ line�� �����Ѵ�.
					
			}br.close();}// �ڿ��Ҹ� �ּ�ȭ
	catch (IOException e) {}
	System.out.println("**���� �켱 ���� ť�� ����Ǿ� �ִ� �۾� ��� ����� ������ �����ϴ�.**");
	System.out.println(data);
	printset();
	int sw = -1;//�Է¿� ���� ������ ��ɾ� ����
	while (sw!=6){
		sw = new Scanner(System.in).nextInt();//� �޼ҵ带 �������ڸ� �Է� ����
		switch(sw){
		case 1: {
			System.out.print("�߰��� ������� �Է����ּ���");
			String temp = sc1.nextLine();
			System.out.print("�켱������ �Է��Ͽ��ּ���");
			int Prioritytemp = sc2.nextInt();
		data.insert(Prioritytemp, temp);//���� ������ �켱�������� insert�Ѵ�
		clearScreen();
		System.out.println("**�߰��� ���� �켱 ���� ť�� ����Ǿ� �ִ� �۾� ��� ����� ������ �����ϴ�.**");
		System.out.println(data);
		printset();
		break;
		} 
		case 2: {
		String max = data.max().lecture;//������� �켱������ ���ǰ��� ����
		System.out.println("�켱������ ������� ���Ǵ� "+max+"�Դϴ�.");
		break;
		}	
		case 3: {
			data.extract_max();//�ִ��۾��� ����
			clearScreen();
			System.out.println("**ó���� �Ϸ��Ͽ����ϴ�.**");
			System.out.println(data);
			printset();
			break;
		}
		case 4:{
			System.out.print("���° ������ �켱������ ������Ű�ڽ��ϱ�??");
			int x = sc1.nextInt();
			if(x>data.size||x<1) {
				System.out.println("�߸��� ť��ȣ�� �Է��Ͽ����ϴ�. �����۾��� �����մϴ�. ��۾��� �Ͻðڽ��ϱ�?");
				break;
			}//�߸��� �Է��� ���ð�� ���� �޼��� ���
			System.out.print("������ �����Ͻðڽ��ϱ�?");
			int y = sc2.nextInt();
			data.increase_key(x,y);
			//Ű�� ���� �޼ҵ� ����
			clearScreen();
			System.out.println("**������Ų�� ���� �켱 ���� ť�� ����Ǿ� �ִ� �۾� ��� ����� ������ �����ϴ�.**");
			System.out.println(data);
			printset();
			break;
			
		}
		case 5:{
			System.out.print("�����Ͻ� ť�� �� ��° �Դϱ�?");
			int x = sc1.nextInt();
			if(x>data.size||x<1) {
				System.out.println("�߸��� ť��ȣ�� �Է��Ͽ����ϴ�. �����۾��� �����մϴ�. ��۾��� �Ͻðڽ��ϱ�?");
				break;
			}//�߸��� �Է��� ���ð�� ���� �޼��� ���
			data.delete(x);//x��° �۾� ����
			clearScreen();
			System.out.println("**������ ���� �켱 ���� ť�� ����Ǿ� �ִ� �۾� ��� ����� ������ �����ϴ�.**");
			System.out.println(data);
			printset();
			break;
		}
		case 6:{
			System.out.println("���α׷��� �����մϴ�.");
		break;
		}
		
		default : {
			System.out.println("���ڸ� �ٽ� �Է��ϼ���");}
		}}//���� �߸��Է½� �߻��ϴ� ����
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
		 }//�켱������ �����̸��� �����ϱ����� ��� ����
	}
	public node[] a;
	public int size;//����� �����ϱ����� ����



	public HeapPriorityQueue() {
		this(CAPACITY);
	}//�⺻ ������ ũ�� 10���� �����Ѵ�

	public HeapPriorityQueue(int capacity) {
		a = new node[capacity];
	}

	public void insert(int P,String L) {
	
		if (size == a.length)
			resize();//ũ�Ⱑ �����Ұ�� resize �Ѵ�
		int i = size++;//�ǳ� ������忡 �ϴ� ������ �˸��� �ڸ��� ã�½��̴�.
		while (i > 0) {
			int j = i;//���� ��ġ�� ����
			i = (i - 1) / 2;//�θ� �ε����� ������ �����Ѵ�
			if (a[i].Priority >= P) {
				a[j]=new node(P,L);
				return;//���� �θ��尡 ����ġ���� ��ȣ���� ���ٸ� �ùٸ� max heap�̱⶧���� �״�� �����Ѵ�
			}
			a[j] = a[i];//�װ� �ƴ϶�� �θ� ���۱⶧���� �ڽ���ġ�� �θ� �����Ѵ�
		}
		a[i]=new node(P,L);//�ٳ����� ���� ���� �θ��ε����� ���ε��� ������ ����Ǹ�ȴ�.
	}
	
	public node max() {
		if (size == 0)
		{	System.out.println("�۾��� ���̻�����ϴ�.");
			throw new java.util.NoSuchElementException();}
		return a[0];
	}//�켱������ ��������� �����Ѵ�

	public node extract_max() {
		node max = max();
		a[0] = a[--size];
		heapify(0, size);//���� max���� ��������� 0�� �������� heapify ���ش�.
		return max;
	}//������� ���� �����ϸ鼭 ������
	public void increase_key(int x, int y) {
		if(a[x-1].Priority<=y){//���簪���� �������� ���ð��
		a[x-1].Priority =y;//�ϴ� �켱������ ��������ش�
		while(x!=1)//x�� 1�̸� �ִ밪�̱⶧���� �����ʿ䰡����.
		{heapify(x/2-1, size);
		x=(x/2);
		}}//���� ���������Ƿ� ������ max heap�� ������.
		//������ �θ�ΰ��� heapify�� �ϸ鼭 root���� heapify ���ش�.
		else {//���� �����ϴ� ���̸� �θ� ���忡�� ������ �������̴� ������ �Ʒ��� �ڽĵ��� ���� heapify�� �����������ִ�.
			a[x-1].Priority =y;
			{heapify(x-1, size);
			}//�׷���� heapify�� ��ġ�� �����ش�.
		}	
	}
	public node delete(int x) {
		node temp = a[x-1];
		a[x-1] = a[--size];//������ ��忡 ���簡�� �Ʒ��ܿ� �ִ� ���� ��带 �ְ� ����� ���δ�
		heapify(x-1, size);//���� �ڸ��� ã�� �ε����� max_heap�� �������� �ʱ⶧���� heapify�Ѵ�
		return temp;
	}
	public void heapify(int i, int n) {//heapify�ϰ���� �ε����� ����� �Է��Ѵ�
		node ai = a[i];//�ϴ� ���� �ε���(�θ���)�� �����Ѵ�
		while (i < n / 2) {//i�� ������尡 �ƴ϶�� ��� �����Ѵ�
			int j = 2 * i + 1;//j�� �ڽĳ���� �ε����̴�
			if (j + 1 < n && a[j + 1].Priority > a[j].Priority)
				++j;//������ �ڽİ��� ��ū���� ��󳻴� �۾��̴�.
			if (a[j].Priority <= ai.Priority)
				break;//�ڽĵ��� ū���� �θ��ε������� ������ heapify�� �״�� ������ȴ�
			a[i] = a[j];//�ڽ��� ���� ��ũ�� �ڽİ��� �θ��忡 �����Ѵ�
			i = j;//�״��� �ٲ� �ڽ��� �ٽ� �θ�� �ϰ� �����Ѵ�
		}
		a[i] = ai;//�ٳ����� i���� heap�� ���۵ɶ� �θ��� �ڸ��̴�.
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
	}//node���� ����ϴ� ����



	private void resize() {
		node[] aa = new node[2 * a.length];
		System.arraycopy(a, 0, aa, 0, a.length);
		a = aa;
	}//ũ�⸦ ����������

}