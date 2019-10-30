
/**
	WeakHashMap �������ڻ����о���ʹ�ã�GC֮��һ�ɻ��ա���ջ��棬������ܡ�

	print �����
	{1=HashMap}
	{1=HashMap}
	{1=HashMap} 1    //ǿ���ã�gc֮�󲻻���
	======================
	{2=WeakHashMap}
	{2=WeakHashMap}
	{} 0             //weak�����ã�gc֮��һ�ɻ��ա�
*/
public class weakHashMapDemo
{
	public static void main(String[] args){
		myHashMap();
		System.out.println("======================");
		myWeakHashMap();
	}

	private static void myHashMap(){
		HashMap<Integer,String> map = new HashMap<>();
		Integer key = new Integer(1);
		String value = "HashMap";

		map.put(key,value);
		System.out.println(map);

		key = null;
		System.out.println(map);

		System.gc();
		System.out.println(map+"\t"+map.size());

	}

	private static void myWeakHashMap(){
		WeakHashMap<Integer,String> map = new WeakHashMap<>();
		Integer key = new Integer(2);
		String value = "WeakHashMap";

		map.put(key,value);
		System.out.println(map);

		key = null;
		System.out.println(map);

		System.gc();
		System.out.println(map+"\t"+map.size());

	}
}