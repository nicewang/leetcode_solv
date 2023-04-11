#### 1. quick-sort solution
<tb>
<iframe src="https://leetcode.com/playground/Eq3DYVBK/shared" frameBorder="0" width="400" height="300"></iframe>
</tb>

```java
    Random rand = new Random();
    public int[] sortArray(int[] nums) {
        quickSort(nums);
        return nums;
    }
    public void quickSort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }
    
    private void quickSort(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
    
        int pivot = arr[left+rand.nextInt(right-left+1)];
        int index = partition(arr, left, right, pivot);
        quickSort(arr, left, index - 1);
        quickSort(arr, index, right);
    }
    
    private int partition(int[] arr, int left, int right, int pivot) {
        while (left <= right) {
            while (arr[left] < pivot) {
                left++;
            }
    
            while (arr[right] > pivot) {
                right--;
            }
    
            if (left <= right) {
                swap(arr, left, right);
                left++;
                right--;
            }
        }
    
        return left;
    }
    
    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
```
#### 2. insert-sort solution
###### Time Limit Exceeded
```java
    Random rand = new Random();
    public int[] sortArray(int[] nums) {
        insertSort(nums);
        return nums;
    }
    public void insertSort(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            int tmp = nums[i];
            int j = i;
            while (j > 0 && nums[j-1] > tmp) {
                nums[j] = nums[j-1];
                j--;
            }
            nums[j] = tmp;
        }
    }
```