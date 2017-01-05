// 二分查找法
二分查找又称折半查找，它是一种效率较高的查找方法。 
二分查找要求:1.必须采用顺序存储结构 2.必须按关键字大小有序排列。
二分查找算法是在有序数组中用到的较为频繁的一种算法，在未接触二分查找算法时，最通用的一种做法是，对数组进行遍历，跟每个元素进行比较，其时间为O(n).但二分查找算法则更优，因为其查找时间为O(lgn)，譬如数组{1， 2， 3， 4， 5， 6， 7， 8， 9}，查找元素6，用二分查找的算法执行的话，其顺序为：
1.第一步查找中间元素，即5，由于5<6，则6必然在5之后的数组元素中，那么就在{6， 7， 8， 9}中查找，
2.寻找{6， 7， 8， 9}的中位数，为7，7>6，则6应该在7左边的数组元素中，那么只剩下6，即找到了。
/** 
 * 二分查找又称折半查找，它是一种效率较高的查找方法。  
　　【二分查找要求】：1.必须采用顺序存储结构 2.必须按关键字大小有序排列。 
 * @author Administrator 
 * 
 */  
public class BinarySearch {   
    public static void main(String[] args) {  
        int[] src = new int[] {1, 3, 5, 7, 8, 9};   
        System.out.println(binarySearch(src, 3));  
        System.out.println(binarySearch(src,3,0,src.length-1));  
    }  
  
    /** 
     * * 二分查找<a href="http://lib.csdn.net/base/datastructure" class='replace_word' title="算法与数据结构知识库" target='_blank' style='color:#df3434; font-weight:bold;'>算法</a> * * 
     *  
     * @param srcArray 
     *            有序数组 * 
     * @param des 
     *            查找元素 * 
     * @return des的数组下标，没找到返回-1 
     */   
   public static int binarySearch(int[] srcArray, int des){   
      
        int low = 0;   
        int high = srcArray.length-1;   
        while(low <= high) {   
            int middle = (low + high)/2;   
            if(des == srcArray[middle]) {   
                return middle;   
            }else if(des <srcArray[middle]) {   
                high = middle - 1;   
            }else {   
                low = middle + 1;   
            }  
        }  
        return -1;  
   }  
        
      /**   
     *二分查找特定整数在整型数组中的位置(递归)   
     *@paramdataset   
     *@paramdata   
     *@parambeginIndex   
     *@paramendIndex   
     *@returnindex   
     */  
    public static int binarySearch(int[] dataset,int data,int beginIndex,int endIndex){    
       int midIndex = (beginIndex+endIndex)/2;    
       if(data <dataset[beginIndex]||data>dataset[endIndex]||beginIndex>endIndex){  
           return -1;    
       }  
       if(data <dataset[midIndex]){    
           return binarySearch(dataset,data,beginIndex,midIndex-1);    
       }else if(data>dataset[midIndex]){    
           return binarySearch(dataset,data,midIndex+1,endIndex);    
       }else {    
           return midIndex;    
       }    
   }   
  
} 
