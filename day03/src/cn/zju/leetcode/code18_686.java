package cn.zju.leetcode;

import java.util.HashSet;
import java.util.Set;

public class code18_686 {
    public int repeatedStringMatch(String a, String b){
        if(b.equals("")){
            return 0;
        }
        if(a.contains(b)){
            return 1;
        }
        int min = b.length()/a.length();
        StringBuffer sb = new StringBuffer(a);
        for (int i = 2; i < min + 2; i++) {
            sb.append(a);
            if(i>=min && sb.toString().contains(b)){
                return i;
            }
        }
        return -1;
    }
    public int repeatedStringMatch1(String a, String b){
        if(b.equals("")){
            return 0;
        }
        if(containsString(a,b)){
            return 1;
        }
        int min = b.length()/a.length();
        StringBuffer sb = new StringBuffer(a);
        for (int i = 2; i < min + 2; i++) {
            sb.append(a);
            if(i>=min && containsString(sb.toString(),b)){
                return i;
            }
        }
        return -1;
    }

    private boolean containsString(String a, String b) {
        int A = 26;
        long modulus = (long)Math.pow(2,32);
        int lenA = a.length();
        int lenB = b.length();
        int[] numA = new int[lenA];
        int[] numB = new int[lenB];
        char[] charA = a.toCharArray();
        char[] charB = b.toCharArray();
        long ansA = 0, ansB = 0;
        long hashIndex = 1;
        for (int i = 0; i < lenA; i++) {
            numA[i] = charA[i] - 'a';
        }
        for (int i = 0; i < lenB; i++) {
            numB[i] = charB[i] - 'a';
            ansB = (ansB * A + numB[i]) % modulus;
            hashIndex = (hashIndex * A) % modulus;
        }
        for (int i = 0; i < lenB; i++) {
            ansA = (ansA * A + numA[i]) % modulus;
        }
        if(ansA == ansB){
            if(b.equals(a.substring(0,lenB))){
                return true;
            }
        }
        for (int i = 1; i < lenA - lenB + 1; i++) {
            ansA = (ansA * A - numA[i-1] * hashIndex + numA[i+lenB-1]) % modulus;
            if(ansA == ansB){
                if(b.equals(a.substring(i,i+lenB))){
                    return true;
                }
            }
        }
        return false;
    }
}
