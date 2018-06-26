
public class DFS {
	static int time =0;
	public static void main(String[] args) {
		boolean[][] bool = {
				{false,true,false,true,false,false},//u
				{false,false,false,false,true,false},//v
				{false,false,false,false,true,true},//w
				{false,true,false,false,false,false},//x
				{false,false,false,true,false,false},//y
				{false,false,false,false,false,true}//z
				};//정점들의 연결상태를 선언
 
		node[] W = new node[bool.length];
		for(int i=0;i<W.length;i++) {
				W[i]=new node(-1,-1, -1,"white");//시간 초기화는 -1,루트노드는 부모는 -1로 표시
		}//각각 노드들의 초기 상태를 지정
		time =0;
		for(int i=0;i<W.length;i++) {
			if(W[i].color.equals("white")) {
				dfs_visit(W,bool,i);
			}
	}//dfs 함수 시작
	
		for(int i=0;i<W.length;i++) {
		System.out.printf("인덱스 번호 : %d 탐색 시작 시간 : %2d ",i,W[i].depart);
		System.out.printf("탐색 종료 시간 : %2d ",W[i].finish);
		System.out.println("부모노드 : "+W[i].parent);
		}
	}
	public static void dfs_visit(node[] u,boolean[][] bool,int index){
		time=time+1;
		u[index].depart = time;
		u[index].color = "gray";
		for(int k =0;k<bool.length;k++) {
			if(bool[index][k]==true)
				if(u[k].color.equals("white")) {//아직 미방문일경우
					u[k].parent=index;//부모노드를 설정하고
					dfs_visit(u,bool,k);//방문한다.
				}
		}
		u[index].color="black";//주변 이웃 방문이 끝나고 black으로 하고
		time=time+1;
		u[index].finish=time;//끝난시간을 저장한다
	}
	static class node {
		int 	depart;
		int 	parent;
		int		finish;
		String 	color;
		 public node(int d,int f,int x,String y){
			 this.depart=d;
			 this.finish=f;
			 this.parent=x;
			 this.color = y;
		 }
	
	}
}
