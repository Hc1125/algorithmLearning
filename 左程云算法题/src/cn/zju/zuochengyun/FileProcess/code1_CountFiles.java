package cn.zju.zuochengyun.FileProcess;

import java.io.File;
import java.util.Stack;

/**
 * 给定一个文件目录的路径，
 * 写一个函数统计这个目录下所有的文件数量并返回隐藏文件也算，但是文件夹不算
 */
public class code1_CountFiles {
    // 注意这个函数也会统计隐藏文件
    public static int getFileNumber(String folderPath) {
        File root = new File(folderPath);
        if (!root.isDirectory() && !root.isFile()) {
            return 0;
        }
        if (root.isFile()) {
            return 1;
        }
        Stack<File> stack = new Stack<>();
        stack.add(root);
        int files = 0;
        while (!stack.isEmpty()) {
            File folder = stack.pop();
            for (File next : folder.listFiles()) {
                if (next.isFile()) {
                    files++;
                }
                if (next.isDirectory()) {
                    stack.push(next);
                }
            }
        }
        return files;
    }
}
