import java.io.*;
import java.util.*;

public class Closest {

	public static void main(String[] args) {
		List<point> data = new ArrayList<>();
		double xdot,ydot;
		String line;
		try {
			FileReader fr = new FileReader("closest_data.txt");
			BufferedReader br = new BufferedReader(fr);//텍스트파일을 읽어온다.
			line = br.readLine();//한줄씩 입력받는다.
			while (line != null) //줄이 없어질때까지 받음
			{StringTokenizer tk = new StringTokenizer(line, " "); //tk라는 분해기가 띄어쓰기마다 단어를 자른다
				while(tk.hasMoreTokens()){
					xdot = Double.parseDouble(tk.nextToken());
					ydot = Double.parseDouble(tk.nextToken());
					data.add(new point(xdot,ydot));
					
					line = br.readLine();	}// txt파일의 커서를 다음으로 옮기고, 다음 줄의 내용을 line에 저장한다.
					}br.close();}// 자원소모 최소화
	catch (IOException e) {}
		//점들의 정보를 data 리스트에 저장하는 try catch문
		
		Collections.sort(data);//리스트의 내장 함수인 콜렉션소트로 x축에대해 sort하였다.
		double distanc = Clopair(data,0,data.size()-1);//현재 data값을 closestpair를 시작한다
		System.out.println(distanc);
	}
	static double Clopair(List<point> data,int q,int w) {
		int middle = (w+q)/2;
		double linex=(data.get(middle).x+data.get(middle+1).x)/2;//중간값과 그옆값의 평균을 구해 구분석의 x값을 구한다
		double alpha;
		if(w-q<=2) {
			alpha = find(data,q,w);
		}//인덱스의 차가 2이하 즉 3개 이하일경우에는 모든점들간의 거리를 구하여 최대값을 구하는 find메소드를 실행하면된다.
		else {
		double left = Clopair(data,q,middle);
		double right = Clopair(data,middle+1,w);//그외의경우에는 왼쪽가 오른쪽을 clopair 한다.
		
		if(left>right) {
			alpha = right;
		}else alpha = left;
		//서로에서 구한 pair값중 낮은값을 alpha로 저장한다.
		double leftalpha = linex-alpha;
		double rightalpha = linex+alpha;//구분선 양쪽으로 alpha값으로 window를 만든다.
		int a,b;
		for(a=middle;a>q;a--) {
			if(data.get(a).x<leftalpha) {
				a++;
				break;
			}//middle의 x값부터 왼쪽으로 window로부터 벗어나는 값을 찾는 for문이다
			//벗어나는걸 찾을경우 그것보다 하나더큰 a가 window안에있는 가장 작은 x값을 가진 point이다
			if(a==0) break;
		}
		for(b=middle;b<w;b++) {
			if(data.get(b).x>rightalpha) {
				b--;
				break;//window 기준 오른쪽으로 벗어난 값중 가장 작은 값을 찾고 그보다 하나 작은 인덱스를 가진 값이
				//window 안에있는 가장 큰 x값을 가진 point이다.
			}
		}
		List<point2> data2 = new ArrayList<>();
		while(a<=b) {
		data2.add(new point2(data.get(a).x,data.get(a).y));
		a++;
		}//임시로 쓸 data2에 저장한다
		Collections.sort(data2);//그후 y축으로 sort한다
		double windowdist = find2(data2,0,data2.size()-1, alpha);//window에서 가장 distance를 찾는다
		if(alpha>windowdist) {
			alpha = windowdist;
		}}
		return alpha;
		
		
	}
	static double find(List<point> data,int first,int last) {
		double subx,suby,dist;
		double min=99999;
		for(int i = first;i<=last-1;i++) {
			for(int j=i+1;j<=last;j++) {
				subx = data.get(j).x-data.get(i).x;
				suby = data.get(j).y-data.get(i).y;
				dist = Math.pow(((Math.pow(subx,2))+Math.pow(suby,2)),(0.5));
				if(min>dist) {min=dist;}}
		}
		return min;
	}
	static double find2(List<point2> data,int first,int last,double alpha) {
		double subx,suby,dist;
		double min=99999;
		for(int i = first;i<last;i++) {//가장아래 y값부터 alpha이하 만큼 떨어진값을 찾는다.
			for(int j=i+1;j<=last;j++) {//i보다 위만 체크하면된다. 왜냐면 스택을 쌓듯이 아래서부터 검사했기때문이다.
				subx = data.get(j).x-data.get(i).x;
				suby = data.get(j).y-data.get(i).y;
				if (Math.abs(suby)>alpha) {break;}//현재 기준이되는 i의 y값에서 j의 y값을 뺏을때 alpha보다 크면 더이상 비교할필요가없다. 
				else {dist = Math.pow(((Math.pow(subx,2))+Math.pow(suby,2)),(0.5));
				if(min>dist) {min=dist;}}}
		}
		return min;
	}
}
class point implements Comparable<point>{
	double 	x;
	double 	y;
	 public point(double x,double y){
		 this.x=x;
		 this.y=y;
	 }//Point의 x값 y값을 저장하기위한 노드 생성
	@Override
	public int compareTo(point o) {
		if (this.x>o.x) {
			return 1;
		}
		else if(this.x<o.x) {
			return -1;
		}
		else return 0;
	}//x축으로 정렬하기위한 compareTo 메소드
}
class point2 extends point{//window를 관리하기위한 point2 선언

	public point2(double x, double y) {
		super(x, y);
	}
	@Override
	public int compareTo(point o) {
		if (this.y>o.y) {
			return 1;
		}
		else if(this.y<o.y) {
			return -1;
		}
		else return 0;
	}//여기서 y축을 기준으로 정렬해야한다
	
}