import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Karatsuba {

	public static void main(String[] args) {
		long[] data = new long[2];//수 2개만 받기때문에 2개이다.
		String Snumber,line;
		int count = 0;// 현재 빈배열중 가장 앞에 배열의 위치를 나타내는 상수이다
		try {
			FileReader fr = new FileReader("data05_karatsuba.txt");
			BufferedReader br = new BufferedReader(fr);//텍스트파일을 읽어온다.
			line = br.readLine();//한줄씩 입력받는다.
			while (line != null) //줄이 없어질때까지 받음
			{StringTokenizer tk = new StringTokenizer(line, ""); //tk라는 분해기가 띄어쓰기마다 단어를 자른다
				while(tk.hasMoreTokens()){
					Snumber = tk.nextToken();//분리기로 처음 자른걸(이름이 처음으로 잘린다) name에 저장한다 그후 커서를 뒤로 옮긴다
					data[count++] =  Long.parseLong(Snumber);
						}// txt파일의 커서를 다음으로 옮기고, 다음 줄의 내용을 line에 저장한다.
				line = br.readLine();}br.close();}// 자원소모 최소화
	catch (IOException e) {}
	System.out.print("input data : ");
	for(int i=0;i<count;i++) {
		System.out.print(data[i]+" ");}
	System.out.println("");//inputdata 추력한다.
	
	BigInteger result =Karatsub(data[0],data[1]);
	System.out.println("Result : "+result);
	}
	
	static BigInteger Karatsub(long a, long b) {//int 값 2개를 받아서 곱한후 BigInteger로 반환하는 메소드이다.
		int flag =0;
		int Numcount = 0;//자리수 확인을 위한 변수
		if(a==0||b==0) {
			return BigInteger.valueOf(0);
		}//계산을 빠르게하기위해 선언
		long num=a;
		 while(num>0)
		  {
		   num = num/10;
		   Numcount++;
		  }//자리수를 알아내는 과정
		 if(Numcount%2==1) {
			 Numcount++;
			 a=a*10;
			 b=b*10;
			 flag=1;
		 }//자리수가 홀수개일때 계산이 잘될수있도록 임시로 10을 곱해서 실행한다.
		 num=a;
		 long i = Numcount/2;
		 long small1 = 0;
		 long big1 = (long) (num/(Math.pow(10,(double)(i))));//첫번째 숫자의 높은자리수와 낮은자리수를 관리하기위해 만들었다.
		 while(i!=0) {
			 small1+=num%10*(Math.pow(10,(double)(Numcount/2- i)));
			 num=num/10;
			 i--;
		 }//나머지를 이용하여서 낮은자리수를 구한다.
		 
		 
		 i = Numcount/2;
		 num =b;
		 long small2 = 0;
		 long big2 = (long) (num/(Math.pow(10,(double)(i))));
		 while(i!=0) {
			 small2+=num%10*(Math.pow(10,(double)(Numcount/2- i)));
			 num=num/10;
			 i--;
		 }
		 //다른수도 똑같이 높은자리수와 낮은자리수를 나눠서 구해준다.
		 BigInteger z0,z1,z2,result;
		 if(Numcount==2) {//Numcount가 2이면 나눈 반반은 현재 한자리숫자이다 즉 기본 base인 상태이다.
			 BigInteger x1 =BigInteger.valueOf(big1);
			 BigInteger y1 =BigInteger.valueOf(big2);
			 BigInteger x2 =BigInteger.valueOf(small1);
			 BigInteger y2 =BigInteger.valueOf(small2);
			 z0=x2.multiply(y2);
			 z2=x1.multiply(y1);
			 z1=x2.add(x1).multiply(y2.add(y1)).subtract(z2).subtract(z0);
			 BigInteger digit =BigInteger.valueOf(10);
			 digit =digit.pow(Numcount);//10의 제곱을 하기위해 곱할수를 만드는 과정이다.
			 result = z2.multiply(digit);
			 digit =BigInteger.valueOf(10);
			 digit = digit.pow(Numcount/2);
			 result = result.add(z1.multiply(digit));
			 result = result.add(z0);//만약 4자리수라면 반반
			 if(flag==1) {
					result=result.divide(BigInteger.valueOf(100));
			 }//자리수가 홀수라면 결과값에서 나누기 100을 해준다.
			return result;}
		 else {//만약 2자리가 아니라면 더나눠서 karatsub을 해준다.
		 z0=Karatsub(small1,small2);
		 z2=Karatsub(big1,big2);
		 z1=Karatsub((big1+small1),(big2+small2));
		 z1=z1.subtract(z2);
		 z1=z1.subtract(z0);
		 BigInteger digit =BigInteger.valueOf(10);
		 digit =digit.pow(Numcount);
		 result = z2.multiply(digit);
		 digit =BigInteger.valueOf(10);
		 digit = digit.pow(Numcount/2);
		 result = result.add(z1.multiply(digit));
		 result = result.add(z0);
		 if(flag==1) {
				result=result.divide(BigInteger.valueOf(100));
		 }
		return result;}
		}
	}
	

