package Week03_SjChO1125;

interface Animal{
	void walk();
	void fly();
	void sing();
	
}
class Bird implements Animal{
	public void walk() {
		System.out.println("걸어라");
	}
	public void fly() {
		System.out.println("날아라");
	}
	public void sing() {
		System.out.println("노래해라");
	}
}
public class AnimalTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Bird bird=new Bird();
		bird.walk();
		bird.fly();
		bird.sing();
		

	}

}
