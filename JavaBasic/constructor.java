/**
 *	Constructor 构造器  构造方法
 *	1. 构造器的作用
 *	  1.1 创建对象； Person p1=new Person();
 *
 *	  2.2 初始化对象的属性；name age isMale
 *
 *	2. 说明：
 *	  2.1 如果没有显示的定义类的构造器的话，则系统默认提供一个空参的构造器
 *
 *	  2.2 定义构造器的格式：权限修饰符  类名(形参列表){}
 *
 *	  2.3 一个类中可定义多个构造器，彼此构成重载
 *
 *	  2.4 一旦显示的定义了类的构造器 public Person(){},系统就不再提供默认的空参构造器
 *	  2.5 一个类中，至少会有一个构造器。
 *
 * */

public class ConstructorDemo{

	public static void main(String[] args){
	    Person p=new Person();
	    p.eat();

	    Person p1=new Person("Lucy",20);
	    System.out.println("name:"+p1.getName()+"\t age:"+p1.getAge());

	}
}

class Person{
	private int age;
	private String name;
	boolean isMale;
	
	public Person(){
		System.out.println("Person()...");
	}

	public Person(String n,int a){
		name = n;
		age = a;
	}

	public void setAge(int a){
		age = a;
	}

	public int getAge(){
		return age;
	}

	public void setName(String n){
		name = n;
	}

	public String getName(){
		return name;
	}

	public int eat(){
		System.out.println("人吃饭");
	}

}
