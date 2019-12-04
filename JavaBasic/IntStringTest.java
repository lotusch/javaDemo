/**
 *  将一个int型598461 颠倒成164895输出
 *
 *  int 转换成 string
 *  遍历字符换位
 *  string 再转换成 int
 * */

class testDemo{
	public static void main(String[] args){
		int num = 598461;
		String str = String.valueOf(num);
		for(int i=0;i<str.length/2;i++){
			String temp = str[i];
			str[i] = str[length-1-i];
			str[length-1-i] = temp;
		}
		num = Integer.parseInt(str);
		System.out.println(num);
	}
}
