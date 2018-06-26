import java.util.LinkedList;
import java.util.Queue;

public class BFS {
	public static void main(String[] args) {
		boolean[][] bool = {
				{true,true,false,false,true,false,false,false},//r
				{true,true,false,false,false,true,false,false},//s
				{false,false,true,true,false,true,true,false},//t
				{false,false,true,true,false,false,true,true},//u
				{true,false,false,false,true,false,false,false},//v
				{false,true,true,false,false,true,true,false},//w
				{false,false,true,true,false,true,true,true},//x
				{false,false,false,true,false,false,true,true},//y
				};//정점들의 연결상태를 선언
		int M = Integer.MAX_VALUE;  
		node[] W = new node[bool.length];
		for(int i=0;i<W.length;i++) {
				W[i]=new node(M, -1,"white");
		}//각각 노드들의 상태를 지정
		int start =1;//첫노드 선택 1번째 인덱스 선택
		W[start]=new node(0,-1,"gray");//root의 부모의 경우에는 -1로 표현
		Queue<node> queue = new LinkedList<node>();//노드관리 큐
		Queue<Integer> queuewhatnum = new LinkedList<Integer>();//접근 한노드 인덱스 저장하기위한 큐
		queue.add(W[start]);
		/////////////////초기화
		while(!queue.isEmpty()) {//큐가 빌때까지
			node u = queue.poll();//큐의 맨앞을 꺼내서 저장
			for(int k =0;k<bool.length;k++) {
				if(bool[start][k]==true)
					if(W[k].color.equals("white")) {
						W[k].color="gray";
						W[k].distan=u.distan+1;
						W[k].parent=start;
						queue.offer(W[k]);//큐뒤에 추가
						queuewhatnum.offer(k);//큐 맨 뒤에 추가
					}
			}
			u.color="black";
			if(!queue.isEmpty()) {
			start=queuewhatnum.poll();}
		}		
		for(int i=0;i<W.length;i++) {
		System.out.print("인덱스 번호 : "+i+" 시작노드에서 거리 :"+W[i].distan+" ");
		System.out.println("부모노드 : "+W[i].parent);
		}
	}
	static class node {
		int 	distan;
		int 	parent;
		String 	color;
		 public node(int x,int y,String z){
			 this.distan=x;
			 this.parent=y;
			 this.color = z;
		 }
	
	}
}
