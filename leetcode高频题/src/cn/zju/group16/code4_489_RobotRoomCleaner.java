package cn.zju.group16;

import java.util.HashSet;

/**
 * 房间（用格栅表示）中有一个扫地机器人。
 * 格栅中的每一个格子有空和障碍物两种可能。
 *
 * 扫地机器人提供4个API，可以向前进，向左转或者向右转。每次转弯90度。
 *
 * 当扫地机器人试图进入障碍物格子时，它的碰撞传感器会探测出障碍物，使它停留在原地。
 *
 * 请利用提供的4个API编写让机器人清理整个房间的算法。
 *
 * 示例:
 *
 * 输入:
 * room = [
 *   [1,1,1,1,1,0,1,1],
 *   [1,1,1,1,1,0,1,1],
 *   [1,0,1,1,1,1,1,1],
 *   [0,0,0,1,0,0,0,0],
 *   [1,1,1,1,1,1,1,1]
 * ],
 * row = 1,
 * col = 3
 *
 * 解析:
 * 房间格栅用0或1填充。0表示障碍物，1表示可以通过。
 * 机器人从row=1，col=3的初始位置出发。在左上角的一行以下，三列以右。
 *
 * 注意:
 *
 * 输入只用于初始化房间和机器人的位置。你需要“盲解”这个问题。
 * 换而言之，你必须在对房间和机器人位置一无所知的情况下，只使用4个给出的API解决问题。
 * 扫地机器人的初始位置一定是空地。
 * 扫地机器人的初始方向向上。
 * 所有可抵达的格子都是相连的，亦即所有标记为1的格子机器人都可以抵达。
 * 可以假定格栅的四周都被墙包围。
 */
public class code4_489_RobotRoomCleaner {

	// 不要提交这个接口的内容
	interface Robot {
		public boolean move();

		public void turnLeft();

		public void turnRight();

		public void clean();
	}

	// 提交下面的内容
	public static void cleanRoom(Robot robot) {
		clean(robot, 0, 0, 0, new HashSet<>());
	}

	private static final int[][] ds = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

	// 机器人robot，
	// 当前来到的位置(x,y)，且之前没来过
	// 机器人脸冲什么方向d，0 1 2 3
	// visited里记录了机器人走过哪些位置
	// 函数的功能：不要重复走visited里面的位置，把剩下的位置，都打扫干净！
	//           而且要回去！
	public static void clean(Robot robot, int x, int y, int d, HashSet<String> visited) {
		robot.clean();
		visited.add(x + "_" + y);
		for (int i = 0; i < 4; i++) {
			// d = 0 :  0 1 2 3
			// d = 1 :  1 2 3 0
			// d = 2 :  2 3 0 1
			// d = 3 :  3 0 1 2
			// 下一步的方向！
			int nd = (i + d) % 4;
			// 当下一步的方向定了！下一步的位置在哪？(nx, ny)
			int nx = ds[nd][0] + x;
			int ny = ds[nd][1] + y;
			if (!visited.contains(nx + "_" + ny) && robot.move()) {
				clean(robot, nx, ny, nd, visited);
			}
			robot.turnRight();
		}
		// 负责回去：之前的位置，怎么到你的！你要回去，而且方向和到你之前，要一致！
		robot.turnRight();
		robot.turnRight();
		robot.move();
		robot.turnRight();
		robot.turnRight();
	}

}
