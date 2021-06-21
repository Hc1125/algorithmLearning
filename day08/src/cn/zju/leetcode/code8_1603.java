package cn.zju.leetcode;

public class code8_1603 {
    class ParkingSystem {
        int bigNum;
        int medNum;
        int smallNum;
        public ParkingSystem(int big, int medium, int small) {
            this.bigNum = big;
            this.medNum = medium;
            this.smallNum = small;
        }

        public boolean addCar(int carType) {
            if (carType == 3) {
                if (smallNum > 0) {
                    smallNum--;
                    return true;
                } else {
                    return false;
                }
            } else if (carType == 2) {
                if (medNum > 0) {
                    medNum--;
                    return true;
                } else {
                    return false;
                }
            } else {
                if (bigNum > 0) {
                    bigNum--;
                    return true;
                } else {
                    return false;
                }
            }
        }
    }
}
