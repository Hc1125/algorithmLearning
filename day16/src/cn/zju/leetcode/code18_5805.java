package cn.zju.leetcode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class code18_5805 {
    public class Friend {
        int num;
        int start;
        int end;

        public Friend(int num, int start, int end) {
            this.num = num;
            this.start = start;
            this.end = end;
        }
    }
    public class Chair {
        int end;
        int count;

        public Chair(int end, int count) {
            this.end = end;
            this.count = count;
        }
    }
    public int smallestChair(int[][] times, int targetFriend) {
        PriorityQueue<Friend> pq = new PriorityQueue<>(new Comparator<Friend>() {
            @Override
            public int compare(Friend o1, Friend o2) {
                return o1.start == o2.start ? o1.num - o2.num : o1.start - o2.start;
            }
        });
        for (int i = 0; i < times.length; i++) {
            pq.offer(new Friend(i, times[i][0], times[i][1]));
        }
        PriorityQueue<Chair> chairs = new PriorityQueue<>(new Comparator<Chair>() {
            @Override
            public int compare(Chair o1, Chair o2) {
                return o1.end == o2.end ? o1.count - o2.count : o1.end - o2.end;
            }
        });
        int sequence = 0;
        while (!pq.isEmpty()) {
            Friend friend = pq.poll();
            int chair = 0;
            if (!chairs.isEmpty() && chairs.peek().end <= friend.start) {
                List<Chair> list = new ArrayList<>();
                while (!chairs.isEmpty() && chairs.peek().end <= friend.start) {
                    list.add(chairs.poll());
                }
                int min = Integer.MAX_VALUE;
                for (int i = 0; i < list.size(); i++) {
                    min = Math.min(min, list.get(i).count);
                }
                chair = min;
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).count != min) {
                        chairs.offer(list.get(i));
                    }
                }
            } else {
                chair = sequence;
                sequence++;
            }
            chairs.offer(new Chair(friend.end, chair));
            if (friend.num == targetFriend) {
                return chair;
            }
        }
        return -1;
    }
}
