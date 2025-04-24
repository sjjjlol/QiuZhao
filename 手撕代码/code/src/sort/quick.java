package sort;

public class quick {
    static void quickSort(int[] nums, int left, int right){
        if(left < right){
            int pivotIndex = partiton(nums, left, right);
            quickSort(nums, left, pivotIndex - 1);
            quickSort(nums, pivotIndex + 1, right);
        }
    }

    static int partiton(int[] nums, int left, int right){
        int pivot = nums[right];
        int slow = left;
        int fast = left;
        for(; fast < right; fast++){
            if(nums[fast] < pivot){
                swap(nums, slow, fast);
                slow++;
            }
        }
        swap(nums, slow, right);
        return slow;
    }

    static void swap(int[] nums, int left, int right){
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }
}
