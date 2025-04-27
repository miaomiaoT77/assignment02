package zest;

import java.util.List;

class ZeroesToEnd {

    public int[] pushZeroesToEnd(int arr[]) {
        ZeroesToEnd result = new ZeroesToEnd();
    //pre-conditions
        if (arr == null || arr.length < 0 || arr.length > 10) {
            throw new IllegalArgumentException("Input array is null, or its length is not within the range 0 to 10");
        }

        int temp[] = new int[arr.length];
        int t = 0;
        for (int i = 0; i < arr.length; i++) {
            // invraint
            if (i < 0 || i >= arr.length) {
                throw new IllegalStateException("Index out of bounds during processing");
            }

            if (arr[i] != 0) {

                temp[t] = arr[i];
                t += 1;
            }
        }
        while (t < arr.length){

            temp[t] = 0;
            t += 1;
        }



        // post-condition

        if (temp.length != arr.length) {
            throw new IllegalStateException("Result array length does not match input array length");
        }


        return temp;
    }
}
