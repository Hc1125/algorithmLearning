package cn.zju.zuochengyun.Practice4;

public class code14_Near2Power {

	// 已知n是正数
	// 返回大于等于，且最接近n的，2的某次方的值
	public static int tableSizeFor(int n) {
		n--; // 如果刚好是2的某次方，让他降一位
		// 填满n最高位后续所有位置的1
		n |= n >>> 1;
		n |= n >>> 2;
		n |= n >>> 4;
		n |= n >>> 8;
		n |= n >>> 16;
		return (n < 0) ? 1 : n + 1;
	}

	// 返回让n最高位的1之后的所有位全变为1
	// 例如 2 10 -> 3 11    8 1000 -> 15 1111
	// 如果已经所有位都是1，则返回自己原数
	public static int AllFillOne(int n) {
		n |= n >>> 1;
		n |= n >>> 2;
		n |= n >>> 4;
		n |= n >>> 8;
		n |= n >>> 16;
		return n;
	}

	public static void main(String[] args) {
		int cap = 120;
		System.out.println(tableSizeFor(cap));
	}

}
