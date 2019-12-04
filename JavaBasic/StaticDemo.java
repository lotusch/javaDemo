/**
 * Static 静态修饰符
 * 修饰属性：创建类的多个对象，多个对象共享一个静态变量。当某个对象.属性 修改属性值后，其它对              象调用的属性为修改后的值。
 *           1 静态变量随着类的加载而加载，可以通过“类名.静态变量”方式调用。
 *           2 静态变量的加载早于对象的创建。
 *           3 由于类只加载一次，则静态变量在内存中也只会存在一份，存在于方法区的静态域中。
 *       举例：Math.PI; System.out;
 *
 * 修饰方法：静态方法可以操作静态变量。
 * 	     静态方法可以通过 类名.静态方法名 或 实例对象名.静态方法名调用。
 *
 * 	     不可以 类名.实例变量   类名。实例方法
 * 	     实例对象既可以调用静态变量和静态方法，也可以调用实例对象和实例方法。
 *
 * */

public class StaticTest{
	public static void main(String[] args){
		Circle c1 = new Circle();
		Circle c2 = new Circle();
		Circle c3 = new Circle(6.2);

		System.out.println("c1的id:"+ c1.getId());
		System.out.println("c2的id:"+ c2.getId());
		System.out.println("c3的id:"+ c3.getId());
		System.out.println("创建圆的个数:"+ Circle.getTotal());
	}
}

class Circle{
	private double radius;
	private int id;

	private static int total;//记录创建圆的个数
	private static int init = 1001;//static声明的属性被所有对象共享
	public Circle(){
		id = init++;
		total++;
	}

	public Circle(double radius){
		this();//调用Circle()空参构造器，代替下面两行冗余。
		//id = init++;
		//total++;
		this.radius = radius;

		
	}

	public double getCircleArea(){
		return Math.PI * radius * radius;
	}

	public double getRadius(){
		return radius;
	}

	public void setRadius(double radius){
		this.radius = radius;
	}

	public int getId(){
		return id;
	}

	public static int getTotal(){
		return total;
	}
}
