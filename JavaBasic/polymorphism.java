/**
 * polymorphism 多态性
 * Person p1 = new Man(); 父类的引用指向子类的对象
 * 总结：编译，看左边；调用的方法在父类中的是否有声明
 *       运行，看右边；调用的方法运行子类中的重写方法
 * 
 * 多态性的使用前提：1.类必须是继承关系   2.方法的重写
 * */

class PolymorphismTest{
	public static void main(String[] args){
		Polymorphism poly = new Polymorphism();
		poly.func(new Dog());

		poly.func(new Cat());
	}

	public void func(Animal animal){//Animal animal = new Dog();
		animal.eat();
		animal.shout();
	}
}

class Animal{
	public void eat(){
		System.out.println("动物吃食物");
	}

	public void shout(){
		System.out.println("动物叫声");
	}
}

class Dog extends Animal{
	@override
	public void eat(){
		System.out.println("狗吃骨头");
	}

	@override
	public void shout(){
		System.out.println("汪汪汪");
	}

}

class Cat extends Animal{
	@override
	public void eat(){
		System.out.println("猫吃鱼");
	
	}

	@override
	public void shout(){
		System.out.println("喵喵喵");
	}
}
