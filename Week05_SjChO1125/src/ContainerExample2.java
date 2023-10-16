class Container2<K,V>{
	private K key;
	private V value;
	
	public K getKey() {
		return this.key;
		
	}
	public V getValue() {
		return this.value;
	}
	public void set(K key, V value) {
		this.key=key;
		this.value=value;
	}
	
}
public class ContainerExample2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Container2<String,String> container1=new Container2<String,String>();
		container1.set("홍길동","도적");
		Container2<String,Integer> container2=new Container2<String,Integer>();
		container2.set("홍길동",35);
		
		
		

	}

}
