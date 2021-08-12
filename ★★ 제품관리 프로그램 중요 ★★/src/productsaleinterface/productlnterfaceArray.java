package productsaleinterface;

import java.util.Arrays;
import java.util.Scanner;


	 /* [추상클래스]=[인터페이스] 공통된 특징
	 * 1. '미완성된 추상 메서드'가 존재하므로 '객체 생성 불가'
	 *    미완성된 추상 메서드는 반드시 자식에서 재정의(안하면 오류)
	 *    오류 해결방법 :1. 자식에서 재정의하거나
	 *               2. 자식도 추상 클래스로 만들면 됨
	 *               
	 * 2. 부모 역할 가능함
	 *    자식객체를 "부모타입으로 자동형변환"하여 받아들일 수 있다.
	 *     (예1) method(Product p){//Product의 자식객체 (Tv, Computer, Audio)->자동으로 Product로 형변환되어 대입 
	 *    
	 *           }
	 *    
	 *     (예2) Product p=new Tv();  Product p=new Computer();  Product p=new Audio();
	 * ------------------------------------------------------------------------
	 * [추상 클래스만의 특징]
	 * 1. 여러 메서드 중 단 1개라도 미완성된 추상 메서드가 있으면 이 클래스는 추상 클래스가 된다.
	 * ------------------------------------------------------------------------
	 * [인터페이스만의 특징]
	 * 1. 멤버변수(=필드)가 모두 '상수'
	 *    int A = 1; //public static final 생략 가능
	 * 2. 메서드가 모두 다 '미완성된 추상 메서드'
	 *    void a(); //public abstract 생략
	 *    
	 *    ★예외1 : static 메서드-자체로 완성된 메서드 , static이 메모리에 올라가있으닌깐 재정의 못함
	 *             =>자식 클래스에서 재정의 못함
	 *             =>static 메서드는 추상 메서드가 될 수 없음
	 *             
	 *    ★예외2 : default 메서드(정석401p)-자체로 완성된 메서드
	 *             =>재정의를 원하는 자식 클래스에서만 재정의하면 됨
	 *             default메서드 왜 만들까?
	 *             
	 *             인터페이스구현하고 a,b,c,d 만들고 b,c,d는 a재정의 하고 {}쓰면 완료된클레스가됌
	 */
	
	interface ProductInter{
		int TV=1, COMPUTER=2, AUDIO=3;
		
		static void buyerEnter(){//첨부터 메모리에 올리고 시작
			//메모리에 올라갈라면 완성되어야 한다. 미완성이면 못올라간다
			System.out.println("구매자가 전자마트에 입장하였습니다.");
		}
		
		default void defaultmethod(){}//{}쓰면 완성된 메서드
		
		//그외 메서드는 모두 추상 메서드
		void meun();//반드시 제정의 해야한다
		//Objce menu(); //"재정의 조건" 설명을 위해 추가한 코드임
	}
	
	abstract class Product{ //밑에 미완성 메서드만들었으닌깐 abstract붙여야한다
		//제품이고 - 부모 클레스이다 : "자식의 공통된 특성"
		//부모의 필드 : 자식의 공통된 속성
		final String makeCountry="Korea"; //제조국가.    자바에서는 final 자바스크림트에서는 const();
		int price;//제품가격
		int bonusPoint;//제품의 포인트
		int count; //갯수
		
		//기본생성자 Product(){super();}임
		public Product(int price) {//같은 패키지만 생성,추가 가능하다
			//가격이 있는 제품 생성
			super();
			this.price = price;
			bonusPoint = (int)(price/10.0);//200.0/10.0=20.0
			
		}
		
		 //재정의 안할라고 미완성된메서드
		abstract void show();//반드시 자식이 재정의해서 사용할라고 미완성 메서드 만들었다잉
//		abstract 써야 미완성 메서드임
	}
	
	class Tv extends Product{ // TV는 Product를 상속받음
		// TODO 부모 상속받은 필드 + 추가 필드
		Scanner sc = new Scanner(System.in);
		String makeCompany = "삼성";//TV는 삼성에서 만들었다
		
		//기본생성자 Tv(){super();}
		
		public Tv() {//
			super(200);
			// TODO 삼성 TV는 보너스 포인트 5프로만해라
			bonusPoint = (int)(price/5.0);
		}

		@Override
		void show() {
			// TODO Auto-generated method stub
			System.out.println("TV 제조국 :"+ makeCountry+", TV제조사:"+makeCompany
					+", TV 가격:"+price+", TV보너스 포인트:"+bonusPoint+"입니다."+count+"개 삿습니다");
		}

		@Override
		public String toString() {
			// TODO @해쉬코드 안나올라고 재정의함
			return "Tv";
		}
		
		
	}
	
	class Computer extends Product{

		final String brand = "LG 그램";
		
		public Computer() {
			super(100);
			// TODO 가격재정의 해야함
		}

		@Override
		void show() {
			// TODO 출력하는거 재정의해야함
			System.out.println("컴퓨터 제조국 :"+ makeCountry+", 컴퓨터브렌드:"+brand
					+", 컴퓨터 가격:"+price+", 컴퓨터보너스 포인트:"+bonusPoint+"입니다."+count+"개 삿습니다");
			
		}
		
		@Override
		public String toString() {
			// TODO @해쉬코드 안나올라고 재정의함
			return "Computer";
		}
	}
	
	class Audio extends Product{
		//부모 상속받은 필드 + 추가필드 없음
		public Audio() {
			super(50);
			// TODO 오디오 가격 50
		}
		
		@Override
		void show() {
			// TODO 재정의하고
			System.out.println("오디오 제조국 :"+ makeCountry+", 오디오 가격:"+price+", 오디오보너스 포인트:"+bonusPoint+"입니다."+count+"개 삿습니다");
			
		}
		
		@Override
		public String toString() {
			// TODO @해쉬코드 안나올라고 재정의함
			return "Audio";
		}
	}
	
	class Buter{
		// TODO 구매자
		//1. 맴버변수 = 필드 : 속성
		//int money = 0;//돈
		int money;//돈
		int bonusPoint = 0;//구매자의 보너스포인트
		int count;
		
		//구매자의 제품 목록(배열)
		//배열 : 반드시 같은 타입만 저장, 크기 고정되므로 최대크기로 선언. 추가와 삭제에 대한 코드를 직접 장성해줘야 하는 번거러옴
		Product[] item = new Product[10]; //최대10개까지 제품 구매가능함.[null,null,null,...]기본값으로 채워짐 . 배열도 객체이기떄문에 null값나옴
		//클레스는 설계도고
		//객체는 설계도 안의 제품이닌깐
		
		//2. 생성자 : 돈을 가진 구매자
		public Buter(int money) {
			super();
			this.money = money;
		}
		
		//3. 메서드 : 기능!
		//제품에 대해 물어본다.
		void askInfo(Product p){
			System.out.println(p+"제품에 대한 정보 부탁드립니다");
			//"클래스명@16진수 해쉬코드" 제품에 대한 정보 부탁드립니다 나옴ㅡㅡ
			//->재정의된 toString()제품에 대한 정보 부탁드립니다 라고 나옴!!
			p.show();
		}
		//제품이 마음에 들어 산다!!
		int i = 0;//메서드 밖에 있으닌깐 맴버변수와 같다 : 제품을 item배열에 추가할 때마다 1씩 증가
		void buy(Product p){//Product의 자식객체(Tv,Computer,Audio)중 하나를Product타입으로 자동형변환되어서 받아들임
			if(this.money < p.price) {
				System.out.println("잔액이 부족하여"+p+"제품을 살수 없습니다.");
				return;//해당메서드(buy메서드)종료됌. system.exit(0);//프로그램종료임
			}
			//this.money = this.money-p.price;
			this.money -= p.price;//구매자의 돈은 제품에서 빠진다
			this.bonusPoint += p.bonusPoint;//구매자의 보너스포인트는 증가
		//	count++;
			//제품목록(배열)에 제품을 추가 후 i값 1증가
			item[i++]=p;//이걸해야 티비,컴터,오디오를 구매할수있는거다
			System.out.println(p+"를 구입하였습니다.");
		}
		
		//구매한 정보 요약
		void summary() {//지역변수 이닌깐 조기화해줘야함
			int sum = 0;//구매한 제품 가격 합계
			
			String itemlist = "";//구매한 제품목록들(예)"Tv"를 구매했으면""+"Tv", 컴터를구매했으면 바로밑에 정리했음
			//""+"Tv"+","+"computer" => "Tv,Computer"
			
			//반복문을 이용해서 구입한 제품의 '총 가격'과 '목록'을 만들어 출력함
			int cntTv=0, cntCom=0, cntAud=0;
			for(i=0;i<item.length;i++) { // i = index역할도  + 구입한 제품 수
				if(item[i] == null ) break;
				
				
			 
				sum += item[i].price; //sum은0원부터 티비를삿으면 item[i]는 주소를가지고있어서 0+200이되고 컴터를사면+100을더한 0+200+100이된다
				
				//과제-1 : , 을 만들어라 방법-1 : 조건연산자(조건문) ?참:거짓
				//itemlist += itemlist + ((i == 0)? ""+item[i] : ","+item[i]);
				
				//과제-2 : 
				
				itemlist += item[i]+", ";
				
				switch (item[i].toString().toUpperCase()) {
				case "Tv": cntTv++;
					break; 
				case "Computer": cntCom++;
					break;
				case "Audio": cntAud++;
					break;
				}
				
				
				
			}
			
			//과제-2 : Tv 1대, computer 2대 총 3대입니다.출력
			if(itemlist.length() != 0) {
				System.out.println("구입하신 제품의 총 금액은 "+sum+"만원입니다");
				System.out.println("구입하신 제품은 "+itemlist+"입니다.");
				System.out.println("총 "+count+"개를 구매했습니다");//여기다 하는거 
		
				String itemCnt = "";
				if(cntTv >0) itemCnt += "Tv: "+cntTv+"대,";
				if(cntCom >0) itemCnt += "Computer: "+cntCom+"대,";
				if(cntAud >0) itemCnt += "Audio: "+cntAud+"대,";
				
			//	itemCnt = itemCnt.substring(0, itemCnt.length()-1);
				
				//if(itemCnt.length() != 0) {
				//System.out.println(itemCnt+"이므로");
				System.out.println("총"+ i +"대 입니다");
				//}
			}else {
				System.out.println("구매자는 제품을 구매하지 않았습니다.");
			}
		}
	}
	
//1개의 파일에 여러 개의 클래스가 있다면 public 붙일 수 있는 클래스는 단 1개뿐....
	//main() 실행용 클래스 = 파일명
public class productlnterfaceArray implements ProductInter{	
	//필드
	
	//기본생성자 - public productlnterfaceArray(){super();}
	
	/*
	 * 재정의 조건 
	 * 1. 반드시 부모의 "리턴타입 메서드명(매개변수) 3개가같아야한다.
	 * 		단, JAVA_JDK1.5부터 수정된 사항 : (예)부모의 리턴타입을(Object)을  자식클래스의 타입(productlnterfaceArray)으로 변경가능함
	 * 
	 * 2. 접근제한자는 부모와 같거나 넓은 범위로 
	 * 3. static 있는 메서드 -> static 없는 메서드(X)
	 * 	  static 없는 메서드 -> static 있는 메서드(X)
	 * 4. 예외는 조상클래스의 메서드보다 많이 선언할 수 없다
	 * 
	 */
	
	//메서드
	/*
	 * @Override public productlnterfaceArray meun() {//static붙이면 오류 왜? // TODO
	 * 
	 * }
	 */

	@Override
	public void meun() {
		// TODO Auto-generated method stub
		System.out.println();//구분위한 빈줄
		System.out.println("************** 가전 제품 목록 **************");
		System.out.println("1. TV         2.Computer         3.Audio");
	}

	
	public static void main(String[] args) {
		// 아래 코드는 "재정의 조건" 설명위해 추가한 코드임
		Scanner sc = new Scanner(System.in);
		
		
		//1000만원 가진 구매자 생성
		Buter b = new Buter(1000);
		ProductInter.buyerEnter();
		
		//티비,컴터,오디오만들자
		Tv tv = new Tv();
		Computer com = new Computer();
		Audio audio = new Audio();
		
		
		String tmp = null;
		productlnterfaceArray pi = new productlnterfaceArray();
		while(b.money != 0) {
			pi.meun();
			System.out.print("원하시는 가전제품 번호를 입력하세요.  종료는 stop ");
			tmp = sc.next();
			if(tmp.equalsIgnoreCase("stop")) break;// 종료 먼저 만들어준다
			
			int num = 0;
			try {
				num = Integer.parseInt(tmp);
			} catch (NumberFormatException e) {
				// TODO "a" "ㅁ" "소수점" 입력하면  오류 나오닌깐
				System.out.println("잘못된 값 입니다");
				System.out.println(e.getMessage());//예외 메세지 출력
				continue;
			}
			
			//2번째 방법 이 더 좋음
			if(!(1<=num && num<=3)) {
				System.out.println("잘못된 번호 입니다. 다시 입력해 주세요~");
				continue; //반복문의 제일 위로 올라감
			}
			switch(num) {
			case ProductInter.TV : b.askInfo(tv);
									//if(b.money >= tv.price); 위에 작성했으닌깐 빼도됌
									b.buy(tv);
									break;
			case ProductInter.COMPUTER :b.askInfo(com);
										b.buy(com);
										break;
			case ProductInter.AUDIO :b.askInfo(audio);
									b.buy(audio);
									break;
			//1번째 방법 default : System.out.println("해당 제품은 없습니다.");
			}
			System.out.println("\n************* 가전제품 판매를 종료 합니다 ************");
			
			b.summary();
			
			
//			if(Integer.parseInt(sc.next()) != 4) {//"1" "2" "a" "ㅁ" "5" "stop" "Stop" "STOP" 이렇게 받을수있는지 고민하고 생각해라
//				
//			}
		}
		
		
		
	}


} //public class 끝









































