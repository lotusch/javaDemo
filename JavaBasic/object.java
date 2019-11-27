/**
 * Object类的综合练习
 *
 * */
public class CircleTest{
	public static void main(){
		Circle c1 = new Circle(2.3);
		Circle c2 = new Circle(2.5,"white",3);

		System.out.println("颜色是否相等："c1.getColor().equals(c2.getColor()));//颜色是否相等：true

		System.out.println("半径是否相等："c1.equals(c2));//半径是否相等：false

		System.out.println(c1);//Circle [radius=2.3]
		System.out.println(c2.toString());//Circle [radius=2.5]


	}
}

//父类
public class GeometricObject{

	protected String color;
	protected double weight;

	protected GeometricObject(){
		this.color = "white";
		this.weight = 1.0;
	}

	public GeometricObject(String color,double weight){
		super();
		this.color = color;
		this.weight = weight;
	}

	public String getColor(){
		return color;
	}

	public void setColor(String color){
		this.color = color;
	}

	public double getWeight(){
		return weight;
	}

	public void setWeight(double weight){
		this.weight = weight;
	}
}

public class Circle extends GeometricObject{
	private double radius;

	public Circle(){
		super();
		radius = 1.0;
		//color = "white";//父类构造器中已有，不用再重复赋值。
		//weight = 1.0;
	}

	public Circle(double radius){
		super();
		this.radius = radius;
	}

	public Circle(double radius,String color,double weight){
		super(color,weight);
		this.radius = radius;
	
	}

	public double getRadius(){
		return radius;
	}

	public void setRadius(double radius){
		this.radius = radius;
	}
        
	//求圆面积
	public double getCircleArea(){
		return 3.14*radius*radius;
	}
	
	//重写equals方法，比较两个圆的半径是否相等。
	@override
	public boolean equals(Object obj){
		if(this == obj){
			return true;
		}

		if(obj instanceof Circle){
			Circle c = (Circle)obj;
			return this.radius == c.radius;
		}

		return false;
	}

	@override
	public String toString(){
		return "Circle [radius="+ radius + "]";
	}


}
