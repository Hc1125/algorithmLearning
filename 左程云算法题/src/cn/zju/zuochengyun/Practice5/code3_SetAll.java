package cn.zju.zuochengyun.Practice5;

import java.util.HashMap;

/**
 * 数据结构设计。
 * 在原来的HashMap上新增一个功能
 * setAll(V val),把所有数据的value改成val，要求时间复杂度为O（1）
 * 同时能支持put，get，setAll方法轮流使用，并都保证O（1）时间复杂度
 */
public class code3_SetAll {

	public static class MyValue<V> {
		public V value;
		public long time;

		public MyValue(V v, long t) {
			value = v;
			time = t;
		}
	}

	public static class MyHashMap<K, V> {
		private HashMap<K, MyValue<V>> map;
		private long time;
		private MyValue<V> setAll;

		public MyHashMap() {
			map = new HashMap<>();
			time = 0;
			setAll = new MyValue<V>(null, -1);
		}

		public void put(K key, V value) {
			map.put(key, new MyValue<V>(value, time++));
		}

		public void setAll(V value) {
			setAll = new MyValue<V>(value, time++);
		}

		public V get(K key) {
			if (!map.containsKey(key)) {
				return null;
			}
			if (map.get(key).time > setAll.time) {
				return map.get(key).value;
			} else {
				return setAll.value;
			}
		}
	}

}
