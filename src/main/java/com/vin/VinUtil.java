package com.vin;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author shkstart
 * @create 2022-08-30 11:24
 */
//VIN码有效校验
public class VinUtil {
    public static String checkVin(String vin) {
        //VIN码第N位加权值
        Map<Integer, Integer> vinPosition = new HashMap<Integer, Integer>();
        //VIN码第N位码数字对应分值，N为VIN码位数
        Map<Character, Integer> vinValue = new HashMap<Character, Integer>();
        //往map集合里面添加值
        vinPosition.put(1, 8);
        vinPosition.put(2, 7);
        vinPosition.put(3, 6);
        vinPosition.put(4, 5);
        vinPosition.put(5, 4);
        vinPosition.put(6, 3);
        vinPosition.put(7, 2);
        vinPosition.put(8, 10);
        vinPosition.put(9, 0);
        vinPosition.put(10, 9);
        vinPosition.put(11, 8);
        vinPosition.put(12, 7);
        vinPosition.put(13, 6);
        vinPosition.put(14, 5);
        vinPosition.put(15, 4);
        vinPosition.put(16, 3);
        vinPosition.put(17, 2);

        vinValue.put('0', 0);
        vinValue.put('1', 1);
        vinValue.put('2', 2);
        vinValue.put('3', 3);
        vinValue.put('4', 4);
        vinValue.put('5', 5);
        vinValue.put('6', 6);
        vinValue.put('7', 7);
        vinValue.put('8', 8);
        vinValue.put('9', 9);
        vinValue.put('A', 1);
        vinValue.put('B', 2);
        vinValue.put('C', 3);
        vinValue.put('D', 4);
        vinValue.put('E', 5);
        vinValue.put('F', 6);
        vinValue.put('G', 7);
        vinValue.put('H', 8);
        vinValue.put('J', 1);
        vinValue.put('K', 2);
        vinValue.put('L', 3);
        vinValue.put('M', 4);
        vinValue.put('N', 5);
        vinValue.put('P', 7);
        vinValue.put('R', 9);
        vinValue.put('S', 2);
        vinValue.put('T', 3);
        vinValue.put('U', 4);
        vinValue.put('V', 5);
        vinValue.put('W', 6);
        vinValue.put('X', 7);
        vinValue.put('Y', 8);
        vinValue.put('Z', 9);

        String result = "0";
        //排除字母O、I
        if (vin == null || vin.indexOf("O") >= 0 || vin.indexOf("I") >= 0 || vin.indexOf("o") >= 0 || vin.indexOf("i") >= 0) {
            result = null;
        } else {
            //长度为17
            if (vin.length() == 17) {
                //小写字符转变为大写字符
                String upperVin = vin.toUpperCase();
                //把VIN字符串转换为字符数组
                char[] vinArr = upperVin.toCharArray();
                int amount = 0;
                for (int i = 0; i < vinArr.length; i++) {
                    //从第一位开始，该位的加权值*码数字的对应分值，计算全部17位的乘积值然后相加除以11，余数就是第九位校验值
                    amount += vinPosition.get(i + 1) * vinValue.get(vinArr[i]);
                }
                //总和除以11取余数，若余数是10，记为X
                if (amount % 11 == 10) {
                    if (vinArr[8] == 'X') {
                        result = "1";
                    } else {
                        result = "0";
                    }
                } else {
                    if (amount % 11 != vinArr[8]) {
                        result = "0";
                    } else {
                        result = "1";
                    }
                }
            } else {
                result = null;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        String result = checkVin("LTVBJ874X60005678");
        System.out.println(result);
    }
}


