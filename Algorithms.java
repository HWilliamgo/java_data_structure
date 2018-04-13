
public class Algorithms {
    private Algorithms() {

    }

    public static Algorithms getInstance() {
        return SingletonHolder.instance;
    }

    private static class SingletonHolder {
        private static final Algorithms instance = new Algorithms();
    }

    /**
     * 线性查找
     *
     * @param arrays 被查找的数组
     * @param count  查找多少个，比如输入3，就只查找数组中对应下标0,1,2的三个元素
     * @param data   要查找的值
     * @param <T>    泛型T
     * @return 如果找到了，返回最后一次出现的下标；如果没找到，返回-1。
     */
    public <T> int linearSearch(T[] arrays, int count, T data) {
        int result = -1;
        for (int i = 0; i < count - 1; i++) {
            if (data.equals(arrays[i])) {
                result = i;
            }
        }
        return result;
    }

    //与上面的方法的区别：在循环体内若找到了data，直接返回第一个找到的下标。
    public <T> int betterLinearSearch(T[] arrays, int count, T data) {
        int result = -1;
        for (int i = 0; i < count - 1; i++) {
            if (arrays[i].equals(data)) {
                return i;
            }
        }
        return result;
    }

    /**
     * 相比上面两个算法，该算法效率更高，因为：
     * 上面用for循环时，除了循环体中的判断，还要进行for循环条件的i<count-1的判断；循环做两次判断。
     * 该算法的while循环，只有循环条件的判断；循环做一次判断
     *
     * @param arrays 同上
     * @param count  同上
     * @param data   同上
     * @param <T>    同上
     * @return 同上
     */
    public <T> int sentinelLinearSearch(T[] arrays, int count, T data) {
        //lastIndex是要查找的最后一个元素的下标，如果输入的查找个数count>数组元素个数，
        // 赋值其为数组最末尾下标，反之赋值count-1。
        int lastIndex = count > arrays.length ? arrays.length - 1 : count - 1;
        //保存要查找的最末尾的值
        T lastData = arrays[lastIndex];
        //将要查找的最末尾的值赋值为data（为了while循环最后可以退出）（后面再还原）
        arrays[lastIndex] = data;
        int i = 0;
        //如果data不等于数组从0开始的元素，i自增
        while (!data.equals(arrays[i])) {
            i++;
        }
        //将先前改变的数组末尾的值还原
        arrays[lastIndex] = lastData;
        //如果i<lastIndex，说明while循环中匹配到了。如果data等于此时array末尾元素，说明也匹配到了
        if (i < lastIndex || data.equals(arrays[lastIndex])) {
            return i;
        } else {
            return -1;
        }
    }

    /**
     * factorial.n : 递归
     * 递归的两个特性：
     * 1、必须有一个或多个不用递归而直接计算出结果的基础情况。
     * 2、每个递归调用最终能迭代到基础情况。
     *
     * @param n
     * @return
     */
    public int factorial(int n) {
        if (n == 0) {
            return 1;
        } else {
            return n * factorial(n - 1);
        }
    }

    //错误，递归迭代不到基础情况
    @Deprecated
    public int badFactorial(int n) {
        if (n == 0) {
            return 1;
        } else {
            return badFactorial(n + 1) / (n + 1);
        }

    }

    /**
     * 递归线性查找
     * 基础情况是第一个if语句和第一个else if语句，else语句进行递归
     * 最后要么到第一个基础情况结束，要么第二个基础情况结束。
     *
     * @param array 被查找的数组
     * @param start 开始下标
     * @param end   结束下标
     * @param data  要查找的元素
     * @param <T>   泛型
     * @return 如果找到，返回找到的第一个元素的下标；否则返回-1
     */
    public <T> int recursiveLinearSearch(T[] array, int start, int end, T data) {
        if (start > end) {
            return -1;
        } else if (data.equals(array[start])) {
            return start;
        } else {//!data.equals(array[start])
            return recursiveLinearSearch(array, start + 1, end, data);
        }
    }

    /**
     * 二分查找（while循环）
     *
     * @param array 从小到大排序的有序数组
     * @param end   要查找的终点
     * @param data  要查找的数据
     * @param <T>   实现了Comparable接口的类型
     * @return 找到返回找到的下标，否则返回-1。
     */
    public <T extends Comparable<T>> int binarySearch(T[] array, int end, T data) {
        if (end > array.length - 1) {
            //如果end超出数组边界
            end = array.length - 1;
        }

        int start = 0;
        while (start < end) {
            int middle = (start + end) / 2;

            int compareResult = data.compareTo(array[middle]);

            if (compareResult == 0) {
                return middle;
            } else if (compareResult < 0) {
                end = middle - 1;
            } else {
                start = middle + 1;
            }
        }
        return -1;
    }

    /**
     * 二分查找（递归）
     *
     * @param array 从小到大排序的有序数组
     * @param start 起点
     * @param end   终点
     * @param data  要查找的数据
     * @param <T>   实现了Comparable接口的类型
     * @return 同上
     */
    public <T extends Comparable<T>> int recursiveBinarySearch(T[] array, int start, int end, T data) {
        if (end > array.length - 1) {//如果end超出数组边界
            end = array.length - 1;
        }
        if (start < 0) {//如果start超出数组边界
            start = 0;
        }

        if (start > end) {
            return -1;
        } else {
            int middle = (start + end) / 2;

            int compareResult = data.compareTo(array[middle]);
            if (compareResult == 0) {
                return middle;
            } else if (compareResult < 0) {
                return recursiveBinarySearch(array, start, middle - 1, data);
            } else {//compareResult>0
                return recursiveBinarySearch(array, middle + 1, end, data);
            }
        }
    }

    /**
     * 选择排序法
     *
     * @param array
     * @return
     */
    public int[] selectionSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int temp = array[i];
            int minIndex = i;
            //查找最小值的外部的变量minValue。
            int minValue = array[i];
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < minValue) {
                    minValue = array[j];//一定要在这里将array[j]记录下来，赋值给minValue
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                array[i] = array[minIndex];
                array[minIndex] = temp;
            }
        }
        return array;
    }

    private int findMin(int[] array, int start, int end) {
        int min = array[start];
        int index = start;
        for (int i = start + 1; i < end; i++) {
            if (array[i] < min) {
                index = i;
                min = array[i];
            }
        }
        return index;
    }

    public int[] anotherSelectionSort(int[] array) {
        for (int i = 0, len = array.length; i < len - 1; i++) {
            int temp = array[i];
            int min = findMin(array, i, len);
            array[i] = array[min];
            array[min] = temp;
        }
        return array;
    }

    /**
     * 插入排序
     *
     * @param array    插入排序的数组
     * @param endIndex 排序的最后的下标
     */
    public void insertionSort(int[] array, int endIndex) {
        for (int i = 1; i <= endIndex; i++) {
            //由于下方while循环的第一次迭代会覆盖array[i],所以必须讲array[i]保存在key中
            int key = array[i];
            int j = i - 1;
            while (j >= 0 && key < array[j]) {//如果j没有越界，并且在前面找到比自己小的array[j]
                //把array[j]往后移动一个位置
                array[j + 1] = array[j];
                //自己递减，以便下次循环接着往前找
                j--;
            }
            //最后将key放到挪开的那个空的位置上去
            array[j + 1] = key;
        }
    }


    /**
     * 归并排序法
     *
     * @param array 要被归并排序的数组
     * @param p     previous,起点下标
     * @param r     rear,终点下标
     */
    public void mergeSort(int[] array, int p, int r) {
        if (p >= r) {//如果起点大于等于终点,说明递归到了基础情况,返回.
            return;
        } else {
            int q = (p + r) / 2;//取中间的那个数
            //分成两部分来递归,注意!mergeSort()执行完,就说明排好一次序,
            // 因此merge()方法总能拿到数组内部两截排序好的情况.
            mergeSort(array, p, q);//前半截
            mergeSort(array, q + 1, r);//后半截
            //递归到最小的基础情况,开始归并排序.
            merge(array, p, q, r);
        }
    }

    /**
     * 真正执行归并的算法
     * 有点复杂,忘了的话还是看书回忆原理.
     * <p>
     * 传入的数组分为两截,从p到q和q+1到r,都是有序的.(如果两截都只有一个元素也成为两部分各自是有序的)
     * 将这两部分有序数组分别拷贝到两个新的数组中,因为是有序的,从下标0开始循环递归比较两个新的数组.
     * 较小的那个放入array中,依次递归下去,最后将生成新的有序的array.
     * <p>
     * "哨兵"处理的思路:
     * 两个新拷贝的数组b,c一定率先有一方全部"出队",并且游标自增,
     * 那么下一轮的判断条件就会造成数组越界(游标自增到了数组外面),
     * 新的做法是给数组b,c额外多申请一个空间,放入MAX值.
     * 那么当b或c中有意义的元素全部出队之后,游标到了哨兵的位置,且此时不会数组越界.
     * 因为哨兵是MaxValue,所以下一轮以及之后的所有比较,都会让另一个数组出队,直到有意义的元素全部出队.
     * k=r,排序结束.
     *
     * @param array 要归并排序的数组
     * @param p     起点下标
     * @param q     中点下标
     * @param r     终点下标
     */
    private void merge(int[] array, int p, int q, int r) {
        int n1 = q - p + 1;//新数组b的长度
        int n2 = r - q;//新数组c的长度
        int[] b = new int[n1 + 1];//用于拷贝,为了下面的操作不用每次判断是否数组越界,额外地多申请一个地址,用"哨兵"技巧,所以n1+1.
        int[] c = new int[n2 + 1];//用于拷贝,同上
        /*关于为什么要拷贝出新的数组而不在原址上操作,是因为在上原址操作更加麻烦,性能更差,详见<<算法基础>>page.44*/
        for (int i = 0, len = b.length; i < len - 1; i++) {
            b[i] = array[p + i];//拷贝
        }
        for (int i = 0, len = c.length; i < len - 1; i++) {
            c[i] = array[q + 1 + i];//拷贝
        }
        //将两个数组最末尾的元素设成最大值,做"哨兵".
        b[n1] = Integer.MAX_VALUE;
        c[n2] = Integer.MAX_VALUE;

        int i = 0, j = 0;//i是数组b的开始下标,j是数组c的开始下标.

        for (int k = p; k <= r; k++) {//开始排序,k是原数组array的开始下标,从p取到r.
            if (b[i] <= c[j]) {
                array[k] = b[i];
                i++;
            } else {
                array[k] = c[j];
                j++;
            }
        }

    }

    /**
     * 快速排序法
     *
     * @param array 快排数组
     * @param p     起点下标
     * @param r     终点下标
     */
    public void quickSort(int[] array, int p, int r) {
        if (p >= r) {
            //基础情况,数组为空,返回
            return;
        } else {
            //将数组划分成各自无序的left组和right组两部分，但是left组全部小于right组。
            //q就是分界线。
            int q = partition(array, p, r);
            quickSort(array, p, q - 1);//递归下去，让left组也这样分
            quickSort(array, q + 1, r);//递归下去，让right组也这样分
            //最后结果是数组只有两个元素，一个left一个right，接着递归下去就是基础情况，return。
        }
    }

    /**
     * 划分
     * 结果：
     * 将传入的数组划分成left和right两组
     * 选择array[r]作为主元，小于array[r]的都进入left组，大于array[r]的都进入right组。
     * 最后q作为分界线返回。
     *
     * 变量说明：
     * 将数组array分成四部分：left组，right组，unknown组，主元r。
     * left组全部小于主元且无序，right全部大于主元且无序，unknown组是待排序的元素的组。
     *
     * 算法中有四个指针p,r,q,u。
     * 其中q和u会变动。
     * q是指向right组的第一个元素，u组指向unknown组的第一个元素。
     *
     * @param array 要划分的数组
     * @param p 起点下标
     * @param r 终点下标
     * @return 返回分界线指针（也就是主元）的下标。
     */
    private int partition(int[] array, int p, int r) {
        int q = p;//q指向right组的第一个元素
        for (int u = p; u < r; u++) {//u指向unknown组的第一个元素
            if (array[u] <= array[r]) {//如果未知元素小于主元
                //那么让right组的第一个元素和unknown交换
                int temp = array[q];
                array[q] = array[u];
                array[u] = temp;
                //q++,让q继续正确指向right组第一个元素
                q++;
                //这样就让unknown组较小的元素放到了right组左边,即Left组
            }//else{
                //就是array[u]>array[r]
                //那么就是u++,让unknown指针后移,刚好把对应元素放进了right组.
            // }
        }
        //最后让主元和right组第一个元素互换即可
        //(主元到达分割线位置,原位置的元素依然在right组,只是跑到最后去了 )
        int temp = array[q];
        array[q] = array[r];
        array[r] = temp;
        return q;
    }
}



