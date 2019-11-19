/**
	�������ģʽ��
	1. ������ĵ������ģʽ�����ǲ���һ���ķ�����֤���������ϵͳ�У���ĳ����ֻ�ܴ���һ������ʵ����

	2.���ʵ�֣�
		2.1 ����ʽ

		2.2 ����ʽ

	3. ���ֶ���ʽ������ʽ
		����ʽ��
			�������������ʱ�����
			�ô������̰߳�ȫ��
		
		����ʽ��
			�ô����ӳٶ���Ĵ���
			Ŀǰ���̲߳���ȫ�ġ�===>�����߳�����ʱ�����޸ġ�

*/

public class SingletonTest1
{
	public static void main(String[] args){
		Bank bank1 = Bank.getInstance();
		Bank bank2 = Bank.getInstance();

		System.out.println(bank1==bank2);//true,˵��bank1��bank2ָ����ͬ�Ķѵ�ַ��ͬһ������

		Order order1 = Order.getInstance();
		Order order2 = Order.getInstance();
		
		System.out.println(order1==order2);
	}
}

//����ʽ

class Bank
{
	
	//1.˽�л��๹����
	private Bank(){
		
	}

	//2.�ڲ�������Ķ���
	//4.Ҫ��˶���Ҳ��������Ϊ��̬��
	private static Bank instance = new Bank();

	//3.�ṩ�����ľ�̬�ķ�����������Ķ���
	public static Bank getInstance(){
		return instance;
	}
}

//����ʽ
class Order
{
	//1. ˽�л��๹����
	private Order(){
	
	}

	//2. �ڲ�������Ķ���
	//4. Ҫ��˶���Ҳ��������Ϊ��̬�ġ�
	private static Order instance = null;
	
	//3. �ṩ�����ľ�̬������������Ķ���
	public static Order getInstance(){
		if(instance == null){
			instance = new Order();
		}
		return instance;
	}
}