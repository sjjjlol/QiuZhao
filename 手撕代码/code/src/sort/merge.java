package sort;

public class merge {
    static void mergeSort(int[] nums, int left, int right){
        if(left >= right) return;
        int mid = left + right >> 1;
        mergeSort(nums, left, mid);
        mergeSort(nums, mid + 1, right);
        int[] temp = new int[right - left + 1];
        int p1 = left;
        int p2 = mid+1;
        int k = 0;
        while (p1 <= mid && p2 <= right){
            if(nums[p1] < nums[p2]){
                temp[k++] = nums[p1++];
            }else{
                temp[k++] = nums[p2++];
            }
        }
        while (p1 <= mid) temp[k++] = nums[p1++];
        while (p2 <= right) temp[k++] = nums[p2++];
        for (int i = left; i <= right; i++) {
            nums[i] = temp[i - left];
        }
    }
}
