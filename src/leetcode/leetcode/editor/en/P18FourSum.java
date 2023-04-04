//Given an array nums of n integers and an integer target, are there elements a,
// b, c, and d in nums such that a + b + c + d = target? Find all unique quadruple
//ts in the array which gives the sum of target. 
//
// Note: 
//
// The solution set must not contain duplicate quadruplets. 
//
// Example: 
//
// 
//Given array nums = [1, 0, -1, 0, -2, 2], and target = 0.
//
//A solution set is:
//[
//  [-1,  0, 0, 1],
//  [-2, -1, 1, 2],
//  [-2,  0, 0, 2]
//]
// 
// Related Topics Array Hash Table Two Pointers


package leetcode.leetcode.editor.en;

import java.util.*;

//Java：4Sum
public class P18FourSum {
    public static void main(String[] args) {
        Solution solution = new P18FourSum().new Solution();
        // TO TEST
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        // 解法二：4ms 40MB 比较牛逼的一种解法 TODO 多看几遍 min1 max1 min max 是精髓
        public List<List<Integer>> fourSum(int[] nums,int target){
            /*定义一个返回值*/
            List<List<Integer>> result=new ArrayList<>();
            /*当数组为null或元素小于4个时，直接返回*/
            if(nums==null||nums.length<4){
                return result;
            }
            /*对数组进行从小到大排序*/
            Arrays.sort(nums);
            /*数组长度*/
            int length=nums.length;
            /*定义4个指针k，i，j，h  k从0开始遍历，i从k+1开始遍历，留下j和h，j指向i+1，h指向数组最大值*/
            for(int k=0;k<length-3;k++){
                /*当k的值与前面的值相等时忽略*/
                if(k>0&&nums[k]==nums[k-1]){
                    continue;
                }
                /*获取当前最小值，如果最小值比目标值大，说明后面越来越大的值根本没戏*/
                int min1=nums[k]+nums[k+1]+nums[k+2]+nums[k+3];
                if(min1>target){
                    break;
                }
                /*获取当前最大值，如果最大值比目标值小，说明后面越来越小的值根本没戏，忽略*/
                int max1=nums[k]+nums[length-1]+nums[length-2]+nums[length-3];
                if(max1<target){
                    continue;
                }
                /*第二层循环i，初始值指向k+1*/
                for(int i=k+1;i<length-2;i++){
                    /*当i的值与前面的值相等时忽略*/
                    if(i>k+1&&nums[i]==nums[i-1]){
                        continue;
                    }
                    /*定义指针j指向i+1*/
                    int j=i+1;
                    /*定义指针h指向数组末尾*/
                    int h=length-1;
                    /*获取当前最小值，如果最小值比目标值大，说明后面越来越大的值根本没戏，忽略*/
                    int min=nums[k]+nums[i]+nums[j]+nums[j+1];
                    if(min>target){
                        continue;
                    }
                    /*获取当前最大值，如果最大值比目标值小，说明后面越来越小的值根本没戏，忽略*/
                    int max=nums[k]+nums[i]+nums[h]+nums[h-1];
                    if(max<target){
                        continue;
                    }
                    /*开始j指针和h指针的表演，计算当前和，如果等于目标值，j++并去重，h--并去重，当当前和大于目标值时h--，当当前和小于目标值时j++*/
                    while (j<h){
                        int curr=nums[k]+nums[i]+nums[j]+nums[h];
                        if(curr==target){
                            result.add(Arrays.asList(nums[k],nums[i],nums[j],nums[h]));
                            // TODO 对比这里j++、h--的位置和3sum的区别
                            j++;
                            while(j<h&&nums[j]==nums[j-1]){
                                j++;
                            }
                            h--;
                            while(j<h&&i<h&&nums[h]==nums[h+1]){
                                h--;
                            }
                        }else if(curr>target){
                            h--;
                        }else {
                            j++;
                        }
                    }
                }
            }
            return result;
        }

        List<List<Integer>> list = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        // 解法一：dfs回溯 超时 通过用例220／282
        public List<List<Integer>> fourSumOne(int[] nums, int target) {
            List<Integer> numList = new ArrayList<>();
            for(int i = 0; i < nums.length; i++) {
                numList.add(nums[i]);
            }
            Collections.sort(numList);
            dfs(numList, 0, 4, target);
            return list;
        }
        private void dfs(List<Integer> nums, int start, int k, int target) {
            if(k == 0 && target == 0) {
                if(!stack.isEmpty()) {
                    ArrayList<Integer> newList = new ArrayList<>(stack);
                    Collections.sort(newList);
                    if(!list.contains(newList)) {
                        list.add(newList);
                    }
                }
                return;
            }
            if(k < 0) {
                // 剪枝
                return;
            }
            for(int i = start; i < nums.size(); i++) {
                stack.push(nums.get(i));
                dfs(nums, i+1, k-1, target-nums.get(i));
                stack.pop();
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}