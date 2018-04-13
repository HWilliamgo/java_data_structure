
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
