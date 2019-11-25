/**
	设置三角形的底和高，计算面积
*/

public class TriAngleDemo
{
	public static void main(String[] args){
	
		TriAngle triAngle = new TriAngle(5,3);
		System.out.println(triAngle.getTriAngleArea());
	}
}

class TriAngle
{
	private double base;
	private double height;

	public TriAngle(){
	
	}

	public TriAngle(double b;double h){
		base = b;
		height = h;
	}

	public void setBase(double b){
		base = b;
	}

	public double getBase(){
		return base;
	}

	public void setHeight(double h){
		height = h;
	}

	public double getHeight(){
		return height;
	}

	public double getTriAngleArea(){
		double area = (base*height)/2;
		return area;
	}
}