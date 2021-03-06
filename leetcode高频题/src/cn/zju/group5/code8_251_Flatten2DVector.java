package cn.zju.group5;

public class code8_251_Flatten2DVector {
    public class Vector2D{
        private int[][] matrix;
        private int row;
        private int col;
        private boolean curUse;

        public Vector2D(int[][] v) {
            matrix = v;
            row = 0;
            col = -1;
            curUse = true;
            hasNext();
        }

        public int next() {
            int ans = matrix[row][col];
            curUse = true;
            hasNext();
            return ans;
        }

        public boolean hasNext() {
            if (row == matrix.length) {
                return false;
            }
            if (!curUse) {
                return true;
            }
            if (col < matrix[row].length - 1) {
                col++;
            } else {
                col = 0;
                do {
                    row++;
                } while (row < matrix.length && matrix[row].length == 0);
            }
            if (row != matrix.length) {
                curUse = false;
                return true;
            } else {
                return false;
            }
        }
    }
}
