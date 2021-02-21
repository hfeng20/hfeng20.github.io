public class Search {
    public static int linearSearch(int[] arr, int key) {
        for(int i = 0; i < arr.length; i++) {
            if(arr[i] == key) {
                return i;
            }
        }
        return -1;
    }
    public static int binarySearch(int[] arr, int key) {
        int l = 0;
        int r = arr.length-1;
        while(l <= r) {
            int m = (l+r)/2;
            if(key > arr[m]) {
                l = m + 1;
            }
            else if(key < arr[m]) {
                r = m - 1;
            }
            else {
                return m;
            }
        }
        return -1;
    }
    public static int binaryRecursive(int[] arr, int key) {
        return binaryRecursiveAux(arr, key, 0, arr.length-1);
    }
    private static int binaryRecursiveAux(int[] arr, int key, int l, int r) {
        System.out.println("Currently searching from " + l + " to " + r);
        if(l > r) {
            return -1;
        }
        int m = (l + r)/2;
        if(arr[m] < key) {
            System.out.println("Key is on right side of " + m + " but to the left of " + r);
            return binaryRecursiveAux(arr, key, m + 1, r);
        } 
        else if(arr[m] > key) {
            System.out.println("Key is on left side of " + m + " but to the right of " + l);
            return binaryRecursiveAux(arr, key, l, m - 1);
        }
        else {
            System.out.println("Found key: " + m);
            return m;
        }
    }
}