package cn.zju.group20;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 给定一个起始 IP 地址 ip 和一个我们需要包含的 IP 的数量 n，返回用列表（最小可能的长度）表示的 CIDR块的范围。
 *
 * CIDR 块是包含 IP 的字符串，后接斜杠和固定长度。例如：“123.45.67.89/20”。固定长度 “20” 表示在特定的范围中公共前缀位的长度。
 *
 * 示例 1：
 * 输入：ip = "255.0.0.7", n = 10
 * 输出：["255.0.0.7/32","255.0.0.8/29","255.0.0.16/32"]
 * 解释：
 * 转换为二进制时，初始IP地址如下所示（为清晰起见添加了空格）：
 * 255.0.0.7 -> 11111111 00000000 00000000 00000111
 * 地址 "255.0.0.7/32" 表示与给定地址有相同的 32 位前缀的所有地址，
 * 在这里只有这一个地址。
 *
 * 地址 "255.0.0.8/29" 表示与给定地址有相同的 29 位前缀的所有地址：
 * 255.0.0.8 -> 11111111 00000000 00000000 00001000
 * 有相同的 29 位前缀的地址如下：
 * 11111111 00000000 00000000 00001000
 * 11111111 00000000 00000000 00001001
 * 11111111 00000000 00000000 00001010
 * 11111111 00000000 00000000 00001011
 * 11111111 00000000 00000000 00001100
 * 11111111 00000000 00000000 00001101
 * 11111111 00000000 00000000 00001110
 * 11111111 00000000 00000000 00001111
 *
 * 地址 "255.0.0.16/32" 表示与给定地址有相同的 32 位前缀的所有地址，
 * 这里只有 11111111 00000000 00000000 00010000。
 *
 * 总之，答案指定了从 255.0.0.7 开始的 10 个 IP 的范围。
 *
 * 有一些其他的表示方法，例如：
 * ["255.0.0.7/32","255.0.0.8/30", "255.0.0.12/30", "255.0.0.16/32"],
 * 但是我们的答案是最短可能的答案。
 *
 * 另外请注意以 "255.0.0.7/30" 开始的表示不正确，
 * 因为其包括了 255.0.0.4 = 11111111 00000000 00000000 00000100 这样的地址，
 * 超出了需要表示的范围。
 *
 * 注：
 * ip 是有效的 IPv4 地址。
 * 每一个隐含地址 ip + x (其中 x < n) 都是有效的 IPv4 地址。
 * n 为整数，范围为 [1, 1000]。
 */
// 本题测试链接 : https://leetcode-cn.com/problems/ip-to-cidr/
public class code7_751_IPToCIDR {

	public static List<String> ipToCIDR(String ip, int n) {
		// ip -> 32位整数
		int cur = status(ip);
		// cur这个状态，最右侧的1，能表示下2的几次方
		int maxPower = 0;
		// 已经解决了多少ip了
		int solved = 0;
		// 临时变量
		int power = 0;
		List<String> ans = new ArrayList<>();
		while (n > 0) {
			// cur最右侧的1，能搞定2的几次方个ip
			// cur : 000...000 01001000
			// 3
			maxPower = mostRightPower(cur);
			// cur : 0000....0000 00001000 -> 2的3次方的问题
			// sol : 0000....0000 00000001 -> 1 2的0次方
			// sol : 0000....0000 00000010 -> 2 2的1次方
			// sol : 0000....0000 00000100 -> 4 2的2次方
			// sol : 0000....0000 00001000 -> 8 2的3次方
			solved = 1;
			power = 0;
			// 怕溢出
			// solved
			while ((solved << 1) <= n && (power + 1) <= maxPower) {
				solved <<= 1;
				power++;
			}
			ans.add(content(cur, power));
			n -= solved;
			cur += solved;
		}
		return ans;
	}

	// ip -> int(32位状态)
	public static int status(String ip) {
		int ans = 0;
		int move = 24;
		for (String str : ip.split("\\.")) {
			// 17.23.16.5 "17" "23" "16" "5"
			// "17" -> 17 << 24
			// "23" -> 23 << 16
			// "16" -> 16 << 8
			// "5" -> 5 << 0
			ans |= Integer.parseInt(str) << move;
			move -= 8;
		}
		return ans;
	}

	public static HashMap<Integer, Integer> map = new HashMap<>();
	// 1 000000....000000 -> 2的32次方

	public static int mostRightPower(int num) {
		// map只会生成1次，以后直接用
		if (map.isEmpty()) {
			map.put(0, 32);
			for (int i = 0; i < 32; i++) {
				// 00...0000 00000001 2的0次方
				// 00...0000 00000010 2的1次方
				// 00...0000 00000100 2的2次方
				// 00...0000 00001000 2的3次方
				map.put(1 << i, i);
			}
		}
		// num & (-num) -> num & (~num+1) -> 提取出最右侧的1
		return map.get(num & (-num));
	}

	public static String content(int status, int power) {
		StringBuilder builder = new StringBuilder();
		for (int move = 24; move >= 0; move -= 8) {
			builder.append((status & (255 << move)) >>> move).append(".");
		}
		builder.setCharAt(builder.length() - 1, '/');
		builder.append(32 - power);
		return builder.toString();
	}

}
