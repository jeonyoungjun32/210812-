package productsaleinterface_refund;

import java.util.Scanner;

/*
* [추상클래스]=[인터페이스] 공통된 특징
 * 1. '미완성된 추상 메서드'가 존재하므로 '객체 생성 불가'
 *    미완성된 추상 메서드는 반드시 자식에서 재정의(안하면 오류)
 *    오류 해결방법 :1. 자식에서 재정의하거나
 *               2. 자식도 '추상 클래스'나 '인터페이스'로 만들면 됨
 *               
 * 2. 부모 역할 가능함
 *    자식객체를 "부모타입으로 자동형변환"하여 받아들일 수 있다.
 *     (예1) method(Product p){//Product의 자식객체 (Tv, Computer, Audio)->자동으로 Product로 형변환되어 대입 
 *    
 *           }
 *    
 *     (예2) Product p=new Tv();  Product p=new Computer();  Product p=new Audio();
 * ------------------------------------------------------------------    
 * [추상 클래스만의 특징]
 * 1. 여러 메서드 중 단 1개라도 미완성된 추상 메서드가 있으면 이 클래스는 추상 클래스가 된다.
 * ------------------------------------------------------------------
 * [인터페이스만의 특징]
 * 1. 멤버변수(=필드)가 모두 '상수'
 *    int A = 1; //public static final 생략 가능
 * 2. 메서드가 모두 다 '미완성된 추상 메서드'
 *    void a(); //public abstract 생략
 *    
 *    ★예외1 : static 메서드-자체로 완성된 메서드
 *             =>자식 클래스에서 재정의 못함
 *             =>static 메서드는 추상 메서드가 될 수 없음
 *             
 *    ★예외2 : default 메서드-자체로 완성된 메서드
 *             =>재정의를 원하는 자식 클래스에서만 재정의하면 됨 
 */
interface ProductInter{
	int TV=1, COMPUTER=2, AUDIO=3;
	
	static void buyerEnter(){
		System.out.println("구매자가 전자마트에 입장하셨습니다.");
	}
	
	default void defaultmethod(){}
	
	//그외 메서드는 모두 추상 메서드
	void menu();
	//Object menu();//"재정의"조건 설명을 위해 추가한 코드임
}

abstract class Product{ //제품-부모:"자식의 공통된 특성"
	//부모의 필드:자식의 공통된 속성
	final String makeCountry="Korea";//제조국가. const(자바스크립트)
	int price;//제품가격 200
	int bonusPoint;//제품의 포인트	20
	
	//기본생성자 Product(){super();}
	
	public Product(int price) {//가격이 있는 제품 생성
		super();
		this.price = price;
		bonusPoint = (int)(price/10.0);//200.0/10.0=20.0
	}
	
	//미완성된 메서드 : 필드의 값을 출력
	abstract void show();//반드시 자식이 재정의해서 사용	
}

class Tv extends Product{
	//부모 상속받은 필드+추가 필드
	String makeCompany="삼성";
	
	//기본생성자 Tv(){super();}
	
	public Tv() {
		super(200);
		bonusPoint = (int)(price/5.0);//200.0/5.0=10.0
	}

	@Override
	void show() {
		System.out.println("TV 제조국:"+makeCountry +", TV 제조사:"+makeCompany
				+", TV 가격:"+price+", TV 보너스포인트:"+bonusPoint);		
	}

	@Override
	public String toString() {		
		return "Tv";
	}
	
	
}

class Computer extends Product{
	//부모 상속받은 필드+추가 필드
	final String brand="LG 그램";
	
	public Computer(){
		super(100);	
	}
	
	@Override
	void show() {
		System.out.println("Computer 제조국:"+makeCountry +", Computer 브랜드:"+brand
				+", Computer 가격:"+price+", Computer 보너스포인트:"+bonusPoint);		
	}
	
	@Override
	public String toString() {		
		return "Computer";
	}
}

class Audio extends Product{
	//부모 상속받은 필드+추가 필드 없음
	
	public Audio() {
		super(50);		
	}
	
	@Override
	void show() {
		System.out.println("Audio 제조국:"+makeCountry 
				+", Audio 가격:"+price+", Audio 보너스포인트:"+bonusPoint);		
	}
	
	@Override
	public String toString() {		
		return "Audio";
	}
}

//구매자
class Buyer{
	//1.멤버변수=필드 : 속성
	int money;//돈
	int bonusPoint=0;//구매자의 보너스포인트	
	
	//구매자의 제품 목록 
	//배열:반드시 같은 타입만 저장, 크기 고정되므로 최대크기로 선언. 추가와 삭제에 대한 코드를 직접 작성해줘야 하는 번거로움
	Product[] item=new Product[10];//최대 10개까지 제품 구매가능함.[null,null,null,...]기본값으로 채워짐
	
	//2.생성자:돈을 가진 구매자
	public Buyer(int money) {//1000
		super();
		this.money = money;
	}
	
	//3.메서드:기능
	//제품에 대해 물어본다.
	void askInfo(Product p){//Product의 자식객체 (Tv, Computer, Audio) 중 하나를 Product타입으로 자동형변환되어 받아들임
		System.out.println(p+"제품에 대한 정보 부탁드립니다.");
		//"클래스명@16진수해쉬코드" 제품에 대한 정보 부탁드립니다.
		//->재정의된 toString() 제품에 대한 정보 부탁드립니다.
		p.show();
	}
	
	//제품을 산다.
	int i=0;//멤버변수 : 제품을 item배열에 추가할 때마다 1씩 증가 
	void buy(Product p){//Product의 자식객체 (Tv, Computer, Audio) 중 하나를 Product타입으로 자동형변환되어 받아들임
		if(this.money < p.price) {
			System.out.println("잔액이 부족하여 "+p+"제품을 살 수 없습니다.");
			return;//해당메서드(buy메서드) 종료. System.exit(0);//프로그램 종료
		}
		
		this.money-=p.price;//this.money=this.money-p.price;//구매자의 돈은 제품의 가격만큼 감소
		this.bonusPoint += p.bonusPoint;//구매자의 보너스포인트는 증가
		
		//제품목록(배열)에 제품을 추가 후 i값 1증가
		item[i++]=p;
		//i++;
		System.out.println(p+"를 구입하셨습니다.");
	}	
	
	//구매한 정보 요약
	void summary(){
		int sum=0;//구매한 제품 가격 합계
		String itemlist="";//구매한 제품목록들 (예)""+"Tv"+","+Computer"=>"TV,Computer"
		
		int cntTv=0, cntCom=0, cntAud=0;//★각 제품을 몇 대 샀는지 카운팅 변수
		
		//반복문을 이용해서 구입한 제품의 '총 가격'과 '목록'을 만들어
		for(i=0;i<item.length;i++) {
			if(item[i] == null) break;
			
			sum += item[i].price;//0+200+100
		
			//과제-1 : "Tv,Computer" - 방법-1 : 조건연산자 "(조건문)?참:거짓" 
			itemlist = itemlist + ((i==0)? ""+item[i] : ","+item[i]);
			
			switch(item[i].toString().toUpperCase()) {//"Tv"->"TV", "Computer"->"COMPUTER",
			case "TV"       : cntTv++; break;
			case "COMPUTER" : cntCom++; break;
			case "AUDIO"    : cntAud++;//break;
			}
		}
		
		
		if(itemlist.length() != 0) {//제품을 구매했으면 
			System.out.println("구입하신 제품의 총 금액은 "+sum+"만원입니다.");
			System.out.println("구입하신 제품은 ["+ itemlist +"]입니다.");
			
			//if(cntTv >0) 
			
			//과제-2 : "Tv 1대, Computer 2대 총 3대입니다."출력
		}else {
			System.out.println("구매자는 제품을 구매하지 않았습니다.");
		}
	}
}

//1개의 파일에 여러 개의 클래스가 있다면 public 붙일 수 있는 클래스는 단 1개뿐...
//main() 실행용 클래스=파일명
public class ProductInterfaceArray implements ProductInter{
	//필드
	
	//기본생성자 존재  - public ProductInterfaceArray(){super();}
	
	/*
	 * 재정의 조건
	 * 1. 반드시 부모의 "리턴타입 메서드명(매개변수)" 같아야 한다.
	 *    단, JDK1.5~수정된 사항 : (예)부모의 리턴타입(Object)을 자식클래스의 타입(ProductInterfaceArray)으로 변경가능함
	 * 2. 접근제한자는 부모와 같거나 넓은 범위로   
	 * 3. static 있는 메서드 -> static 없는 메서드(X)
	 *    static 없는 메서드 -> static 있는 메서드(x)
	 * 4. 예외는 조상클래스의 메서드보다 많이 선언할 수 없다.
	 */
	//메서드
	/*
	@Override
	public ProductInterfaceArray menu() {//static붙이면 오류
		ProductInterfaceArray obj=null;
		return obj;
	}
	*/
	
	@Override
	public void menu() {//static붙이면 오류
		System.out.println();//구분위한 빈줄
		System.out.println("************** 가전 제품 목록 **************");
		System.out.println("1.TV         2.Computer         3.Audio");	
	}
	
	public static void main(String[] args) {
		// 아래코드는 "재정의 조건" 설명위해 추가한 코드임
		//ProductInterfaceArray pi=new ProductInterfaceArray();
		//ProductInterfaceArray p2=pi.menu();
		
		Scanner sc=new Scanner(System.in);//키보드로부터 입력
		
		//1000만원 가진 구매자 생성
		Buyer b=new Buyer(1000);
		
		//구매자가 전자마트에 입장
		ProductInter.buyerEnter();
		
		//Tv,Computer,Audio 객체 생성
		Tv tv=new Tv();
		Computer com=new Computer();
		Audio audio=new Audio();
		
		String tmp=null;//지역변수 초기화(기본값이 없음)
		
		ProductInterfaceArray pi=new ProductInterfaceArray();
		while(b.money != 0) {
			pi.menu();
			
			System.out.print("원하시는 가전제품 번호를 입력하세요. 종료는 stop >");
			tmp=sc.next();//수 "1" "2" "5" "1.23", 문자 "a" "ㅁ" //"stop" "Stop" "STOP"
		
			if(tmp.equalsIgnoreCase("stop")) break;
			
			//수 "1"->1, "2"->2 "5"->5, "-1"->-1   (예외발생)"1.23", 문자 "a" "ㅁ"
			int num = 0; //지역변수 초기화
			try {
				num = Integer.parseInt(tmp);
			} catch (NumberFormatException e) {				
				System.out.println("잘못된 값을 입력하셨습니다.");
				System.out.println(e.getMessage());//예외메세지 출력
				continue;//반복문의 제일 위로
			}
			
			//수 "1"->1, "2"->2, "3"->3(정상)  "-1"->-1  "5"->5
			if(!(1<=num && num<=3)) {
				System.out.println("잘못된 번호입니다. 다시 입력해 주세요~");
				continue;//반복문의 제일 위로
			}
			
			//1,2,3
			switch(num) {
			case ProductInter.TV :      b.askInfo(tv);			                      
			                            b.buy(tv);
			                            break;
			case ProductInter.COMPUTER :b.askInfo(com);			                      
							            b.buy(com);
							            break;
			case ProductInter.AUDIO :   b.askInfo(audio);			                      
							            b.buy(audio);
							            break;
			//default : System.out.println("해당 제품은 없습니다.");
			//default필요없음. 위에서 이미 걸러내서 num에 정수 1 또는 2 또는 3만 존재하므로			            							        
			}//switch문 끝
		}//while문 끝
		
		System.out.println("\n**********가전제품 판매를 종료합니다.*************");
		
		b.summary();
	}
	
}



