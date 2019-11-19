/**
	单例设计模式：
	1. 所有类的单例设计模式，就是采用一定的方法保证在整个软件系统中，对某个类只能存在一个对象实例。

	2.如何实现？
		2.1 饿汉式

		2.2 懒汉式

	3. 区分饿汉式和懒汉式
		饿汉式：
			坏处：对象加载时间过长
			好处：是线程安全的
		
		懒汉式：
			好处：延迟对象的创建
			目前是线程不安全的。===>到多线程内容时，再修改。

*/

public class SingletonTest1
{
	public static void main(String[] args){
		Bank bank1 = Bank.getInstance();
		Bank bank2 = Bank.getInstance();

		System.out.println(bank1==bank2);//true,说明bank1和bank2指向相同的堆地址，同一个对象。

		Order order1 = Order.getInstance();
		Order order2 = Order.getInstance();
		
		System.out.println(order1==order2);
	}
}

//饿汉式

class Bank
{
	
	//1.私有化类构造器
	private Bank(){
		
	}

	//2.内部创建类的对象
	//4.要求此对象也必须声明为静态的
	private static Bank instance = new Bank();

	//3.提供公共的静态的方法，返回类的对象
	public static Bank getInstance(){
		return instance;
	}
}

//懒汉式
class Order
{
	//1. 私有化类构造器
	private Order(){
	
	}

	//2. 内部创建类的对象
	//4. 要求此对象也必须声明为静态的、
	private static Order instance = null;
	
	//3. 提供公共的静态方法，返回类的对象
	public static Order getInstance(){
		if(instance == null){
			instance = new Order();
		}
		return instance;
	}
}