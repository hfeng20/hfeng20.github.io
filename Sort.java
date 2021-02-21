public class Sort
{
    //selection sort
    public static void sSort(int[] arr) {
        int n = arr.length;
        /* For each i, find i'th smallest element and place at i. */
        for (int i = 0; i < n; i++) {
            int min = arr[i];
            int minIndex = i;
            for (int j = i+1; j < n; j++) {
                if (arr[j] < min) {
                    min = arr[j];
                    minIndex = j;
                }
            }
            arr[minIndex] = arr[i];
            arr[i] = min;
        }
    }
    //insertion sort
    public static void iSort(int[] arr) {
        for(int i = 1; i < arr.length; i++) {
            int j = i-1;
            while(j >= 0 && arr[j+1] < arr[j]){
                int temp = arr[j+1];
                arr[j+1] = arr[j];
                arr[j] = temp;
                j--;
            }
        }
    }
    // bubble sort
    public static void bSort(int[] arr) {
        /*var t: the final index of the (arr.length-t)th greatest value
        * the t biggest elements are sorted to the right of t.
        */
        for(int t = arr.length-1; t >= 0; t--) {
            for(int i = 0; i < t; i++) {
                if(arr[i+1] < arr[i]) {
                    int temp = arr[i];
                    arr[i] = arr[i+1];
                    arr[i+1] = temp;
                }
            }
        }
    }
    //mergesort
    public static void mSort(int[] arr) {
        mSortAux(arr, 0, arr.length-1);
    }
    public static void mSortAux(int[] arr, int l, int r) {
        int m = (l + r)/2;
        if(l >= r) {
            return;
        }
        mSortAux(arr,l,m);
        mSortAux(arr,m+1, r);
        merge(arr, l, r);
    }
    public static void merge(int[] arr, int l, int r) {
        int m = (l+r)/2;
        int[] lArr = new int[m-l+1];
        int[] rArr = new int[r-m];
        for(int i = l; i <= m; i++) {
            lArr[i-l] = arr[i];
        }
        for(int i = m+1; i <= r; i++) {
            rArr[i - m-1] = arr[i];
        }
        for(int i = 0; i < lArr.length; i++) {
            System.out.print(lArr[i] + " ");
        }
        System.out.println();
        for(int i = 0; i < rArr.length; i++) {
            System.out.print(rArr[i] + " ");
        }
        int lCounter = 0;
        int rCounter = 0;
        int pCounter = l;
        while(lCounter < lArr.length && rCounter < rArr.length) {
            if(lArr[lCounter] < rArr[rCounter]) {
                arr[pCounter] = lArr[lCounter];
                lCounter++;
            }
            else {
                arr[pCounter] = rArr[rCounter];
                rCounter++;
            }
            pCounter++;
        }
        while(lCounter < lArr.length) {
            arr[pCounter] = arr[lCounter];
            lCounter++;
            pCounter++;
        }
        while(rCounter < rArr.length) {
            arr[pCounter] = arr[rCounter];
            rCounter++;
            pCounter++;
        }
    }
    //quicksort
    public static void qSort(int[] arr) {
        
    }
}