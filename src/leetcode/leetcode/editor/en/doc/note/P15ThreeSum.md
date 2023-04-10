```
    class Solution {
        // 25ms 43.7MB
        public List<List<Integer>> threeSum(int[] nums) {
            List<List<Integer>> result = new ArrayList<>();
            if (nums == null || nums.length < 3) {
                return result;
            }
            Arrays.sort(nums);
            int len = nums.length;
            for (int i = 0; i < len - 2; i++) {
                if (nums[i] > 0) {
                    return result;
                }
                if (i > 0 && nums[i] == nums[i - 1]) {
                    continue;
                }
                int l = i + 1;
                int r = len - 1;
                while(l < r) {
                    int curr = nums[i] + nums[l] + nums[r];
                    if(curr == 0) {
                        result.add(Arrays.asList(nums[i],nums[l],nums[r]));
                        while(l < r && nums[l] == nums[l+1]) {
                            l++;
                        }
                        while(l < r && nums[r] == nums[r-1]) {
                            r--;
                        }
                        l++;
                        r--;
                    } else if(curr < 0) {
                        l++;
                    } else {
                        r--;
                    }
                }
            }
            return result;
        }
    }
```