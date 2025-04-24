package sort;

import java.util.Arrays;

public class test {
    public static void main(String[] args) {
        int[] nums = {5,4,3,2,1,6,7,8,9,0};
//        quick.quickSort(nums, 0, nums.length - 1);
        merge.mergeSort(nums, 0, nums.length - 1);
        System.out.println(Arrays.toString(nums));
    }

}
