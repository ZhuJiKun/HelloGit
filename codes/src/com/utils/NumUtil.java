package com.utils;

/**
 * @author zhujk
 */
public class NumUtil {

	
	
	
	/**
    * 功能：不重复的随机数
    * 
    * min:产生随机数的其实位置
    * mab：产生随机数的最大位置
    * n: 所要产生多少个随机数
    *
    */
    public static int[] randomNumber(int min,int max,int n){

        //判断是否已经达到索要输出随机数的个数
        if(n>(max-min+1) || max <min){
            return null;
        }

        int[] result = new int[n]; //用于存放结果的数组

        int count = 0;
        while(count <n){
            int num = (int)(Math.random()*(max-min))+min;
            boolean flag = true;
            for(int j=0;j<n;j++){
                if(num == result[j]){
                    flag = false;
                    break;
                }
            }
            if(flag){
                result[count] = num;
                count++;
            }
        }
        return result;
    }
	    
}
