package game_or_fun_new;

/**
 * [1,3,2,3,1,2,3,1] -> sort
 * only contain some of {1, 2, 3}
 */
public class SortBySwitch {

    public static void main(String[] args) {
        SortBySwitch solv = new SortBySwitch();
        int[] data = new int[]{1,3,2,3,1,2,3,1};
        solv.solv(data);
        for (int i : data) {
            System.out.println(i);
        }
        System.out.println("==========");
        int[] data1 = new int[]{3,3,3,2,2,1,1,1,1};
        solv.solv(data1);
        for (int i : data1) {
            System.out.println(i);
        }
        System.out.println("==========");
        int[] data2 = new int[]{2,2,2,2,2};
        solv.solv(data2);
        for (int i : data2) {
            System.out.println(i);
        }
        System.out.println("==========");
    }

    public void solv(int[] data) {
        int len = data.length;
        int l = 0, r = len-1;
        while (data[l] == 1) {
            l++;
        }
        while (data[r] == 3) {
            r--;
        }
        int m = l+1;
        while (m <= r) {
            if (m < l) {
                m = l+1;
            }
            if (data[m] == 1) {
                swap(data, l, m);
                do {
                    l++;
                } while (data[l] == 1);
            } else if (data[m] == 3) {
                swap(data, m, r);
                do {
                    r--;
                } while (data[r] == 3);
            } else {
                m++;
            }
        }
    }

    public void swap(int[] data, int i, int j) {
        int tmp = data[i];
        data[i] = data[j];
        data[j] = tmp;
    }

}
