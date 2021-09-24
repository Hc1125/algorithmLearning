package cn.zju.zuochengyun.Sort;

/**
 * 给定一个无序数组arr中，长度为N，给定一个正数k，返回top k个最大的数
 * 且要求取出来的数字有序
 * 不同时间复杂度三个方法：
 * 1)  O(N * logN)      排序完后依次取
 * 2)  O(N + K * logN)
 *      以自下而上的方式生成大根堆（O(N)）,
 *      再每次弹出来一个（弹出来一个需要重新维护大根堆（O(logN)），
 *      所以总共需要加 O(K * logK)
 *      总时间复杂度O(N + K * logN)
 * 3)  O(N + K * logK)
 *      用改写快排的方式或者bfprt算法找到第k大的数 pivot
 *      遍历数组把 >pivot 这个数的数字都存到长度为k的数组
 *      剩余未填满的数字即都为pivot
 *
 * 最常见的的方法就是平时维护一个K个大小的小根堆，
 * 然后一直维护小根堆至遍历完所有数组O(N * logK)
 * 然后再排序小根堆O(K * log)K
 */
public class code10_TopK {

}
