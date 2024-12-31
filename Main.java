import java.util.*;

public class Main {
    public static void main(String[] args) {
        //Find pair with given sum in the array
        int[] arr = {2, 4, -1, 1,0};
        int k = 3;
        pair(arr, k, 0, 1);
        //Check if subarray with 0 sum is exists or not
        System.out.println(findZeroSum(arr));
        //Print all sub-arrays with 0 sum
        ArrayList<ArrayList<Integer>> list=subArraysZeroSum(arr);
        for(ArrayList<Integer> l:list){
            int start=l.get(0);
            int end=l.get(1);
            System.out.print("{");
            for(int i=start;i<=end;i++){
                if(i==end)
                    System.out.print(arr[i]);
                else
                   System.out.print(arr[i]+",");
            }
            System.out.print("}");
            System.out.println();
        }
        //Sort binary array in linear time
        int[] qn={1,0,0,1,1};
        int[] arr1=binaryArraySort(qn);
        for(int i=0;i<arr1.length;i++){
            System.out.print(arr1[i]+" ");
        }
        //Find a duplicate element in a limited range array
        int[] dupArray={1,2,3,3,4,5};
        System.out.println();
        System.out.println("Duplicate element is "+dupArrayFunction(dupArray));
        //Find maximum length subarray with sum k
        ArrayList<Integer> list1=findMaxLength(arr,4);
        int i=list1.get(0);
        int j=list1.get(1);
        System.out.println("longest subarray with sum 4 is");
        for(int l=i;l<=j;l++){
            System.out.print(arr[l]+" ");
        }
        //Find maximum length sub-array having equal number of 0’s and 1’s
        ArrayList<Integer> list2=maxLenSubArray01(qn);
        i=list2.get(0);
        j=list2.get(1);
        System.out.println();
        System.out.println("longest subarray with equal no of 0's and 1's");
        for(int l=i;l<=j;l++){
            System.out.print(qn[l]+" ");
        }
        //Sort an array containing 0’s, 1’s and 2’s (Dutch national flag problem)
        int[] sortThree={1,0,0,1,1,2,1,0};
        int[] arr2=sort012(sortThree);
        System.out.println();
        System.out.println("sorted array containing 0’s, 1’s and 2’s");
        for(int m=0;m<arr2.length;m++){
            System.out.print(arr2[m]+" ");
        }
        //Inplace merge two sorted arrays
        int[] sort1={1,4,6,7};
        int[] sort2={2,3,8};
        inplaceMerge(sort1,sort2);
        //Merge two arrays by satisfying given constraints
        int[] x={0,1,3,0,0,6,7,0};
        int[] y={2,10,4,11};
        mergeTwoArrays(x,y);
        //Find maximum product of two integers in an array
        pairProduct(arr);
        //Rearrange the array with alternate high and low elements
        rearrangeHighLow(arr);
        //Shuffle the array
       shuffle(arr);
       //Find index of 0 to replaced to get maximum length sequence of continuous ones
        index0(qn);
        //Find largest sub-array formed by consecutive integers
        largestConseqSubarray(arr);
    }
    static void pair(int[] arr,int sum,int index1,int index2){
        if(index1>=arr.length-1){
            System.out.println("Pair not found");
            return;
        }
        if(index2>=arr.length-1) {
            pair(arr, sum, index1 + 1, index1 + 2);
            return;
        }
        if(arr[index1]+arr[index2]==sum){
            System.out.println("Pair found "+arr[index1]+" "+arr[index2]);
            return;
        }
        pair(arr,sum,index1,index2+1);
    }
    static boolean findZeroSum(int arr[]) {
        HashSet<Integer> sumSet = new HashSet<>();
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            if (sum == 0 || sumSet.contains(sum)) {
                return true;
            }
            sumSet.add(sum);
        }
        return false;
    }
    static ArrayList<ArrayList<Integer>> subArraysZeroSum(int[] arr){
        ArrayList<ArrayList<Integer>> all=new ArrayList<>();

        for(int i=0;i<arr.length;i++){
            int sum=0;
            for(int j=i;j<arr.length;j++){
                sum=sum+arr[j];
                if(sum==0){
                    ArrayList<Integer> sets=new ArrayList<>();
                    sets.add(i);
                    sets.add(j);
                    all.add(sets);
                }
            }
        }
        return all;
    }
    static int[] binaryArraySort(int[] arr){
        int zeroCount=0;
        for(int i=0;i<arr.length;i++){
            if(arr[i]==0)
                zeroCount++;
        }
        int i;
        for(i=0;i<zeroCount;i++){
            arr[i]=0;
        }
        for(;i<arr.length;i++){
            arr[i]=1;
        }
        return arr;
    }
    static int dupArrayFunction(int[] arr){
        HashSet<Integer> set=new HashSet<>();
        for(int i=0;i<arr.length;i++){
            if(set.contains(arr[i])){
                return arr[i];
            }
            else{
                set.add(arr[i]);
            }
        }
        return -1;
    }
    static ArrayList<Integer> findMaxLength(int[] arr,int k){
        int maxLength = 0;
        int startIndex = 0, endIndex = -1;

        for (int i = 0; i < arr.length; i++) {
            int sum = 0;
            for (int j = i; j < arr.length; j++) {
                sum += arr[j];
                if (sum == k) {
                    int currentLength = j - i + 1;
                    if (currentLength > maxLength) {
                        maxLength = currentLength;
                        startIndex = i;
                        endIndex = j;
                    }
                }
            }
        }

        ArrayList<Integer> list = new ArrayList<>();
        list.add(startIndex);
        list.add(endIndex);
        return list;
    }
    static ArrayList<Integer> maxLenSubArray01(int[] arr){
        int maxLength = 0;
        int startIndex = -1, endIndex = -1;
        HashMap<Integer,Integer> map=new HashMap<>();
        map.put(0,-1);
        int sum=0;
        int val;
        for (int i = 0; i < arr.length; i++) {
            if(arr[i]==0){
               val=-1;
            }
            else{
                val=1;
            }
            sum=sum+val;
            if(map.containsKey(sum)){
                int length=i-map.get(sum);
                if (maxLength<length){
                    maxLength=length;
                    startIndex=map.get(sum)+1;
                    endIndex=i;
                }
            }
            else
                map.put(sum,i);
        }
        ArrayList<Integer> list = new ArrayList<>();
        list.add(startIndex);
        list.add(endIndex);
        return list;
    }
    static int[] sort012(int[] arr){
        int one=0,zero=0,two=0;
        for(int i=0;i<arr.length;i++){
            if(arr[i]==0){
                zero++;
            }
            else if(arr[i]==1){
                one++;
            }
            else
                two++;
        }
        int i=0;
        for(;i<zero;i++){
            arr[i]=0;
        }
        for(;i<zero+one;i++){
            arr[i]=1;
        }
        for(;i<arr.length;i++){
            arr[i]=2;
        }
        return arr;
    }
    static void inplaceMerge(int[] arr1,int[] arr2){
        int n=arr1.length;
        int m=arr2.length;
        int[] merge = new int[n+m];
        int j=0;
        for( int i=0;i<arr1.length;i++){
           merge[j]=arr1[i];
            j++;
        }
        for( int i=0;i<arr2.length;i++){
            merge[j]=arr2[i];
            j++;
        }
        Arrays.sort(merge);
        int s=0;
        for( int t=0;t<arr1.length;t++){
            arr1[t]=merge[s];
            s++;
        }
        for( int t=0;t<arr2.length;t++){
            arr2[t]=merge[s];
            s++;
        }
        System.out.println();
        System.out.println("Array 1: ");
        for(int g=0;g<arr1.length;g++){
            System.out.print(arr1[g]+" ");
        }
        System.out.println();
        System.out.println("Array 2: ");
        for(int g=0;g<arr2.length;g++){
            System.out.print(arr2[g]+" ");
        }
    }
    static void mergeTwoArrays(int[] arr1,int[] arr2){
        int j=0;
        for(int i=0;i<arr1.length;i++){
            if(arr1[i]==0){
                arr1[i]=arr2[j];
                j++;
            }
        }
        Arrays.sort(arr1);
        System.out.println();
        System.out.println("Vacant filled Sorted array");
        for(int i=0;i<arr1.length;i++){
           System.out.print(arr1[i]+ " ");
        }

    }
    static void pairProduct(int[] arr){
        int product=0;
        for(int i=0;i<arr.length;i++){
            for(int j=i+1;j<arr.length;j++){
                int product1=arr[i]*arr[j];
                if(product1>product){
                    product=product1;
                }
            }
        }
        System.out.println();
        System.out.println("maximum product is "+product);
    }
    static void rearrangeHighLow(int[] arr){
        for(int i=0;i<arr.length-1;i++){
            if(arr[i]>arr[i+1]){
                int temp=arr[i];
                arr[i]=arr[i+1];
                arr[i+1]=temp;
                i++;
            }
        }
        System.out.println();
        System.out.println("Rearranged array:");
        for(int i=0;i<arr.length;i++){
            System.out.print(arr[i]+ " ");
        }

    }
    static void shuffle(int[] arr)
    {
        for (int i =arr.length - 1;i >= 1;i--)
        {
            Random rand = new Random();
            int j = rand.nextInt(i + 1);
            int temp=arr[i];
            arr[i]=arr[j];
            arr[j]=temp;
        }
        System.out.println();
        System.out.println("Shuffled array:");
        for(int i=0;i<arr.length;i++){
            System.out.print(arr[i]+ " ");
        }
    }
    static void index0(int[] arr){
       int indexZero=-1;
       int count=0;
       int maxCount=0;
       int maxIndex=-1;
        for (int i =0;i <arr.length;i++)
        {
           if(arr[i]==1){
               count++;

           }
           else{
               count=i-indexZero;
               indexZero=i;
           }
            if (count > maxCount)
            {
                maxCount = count;
                maxIndex = indexZero;
            }
        }
        System.out.println();
        System.out.print(maxIndex);
    }
    public static void largestConseqSubarray(int[] arr) {
        int maxLen = 0;
        int start = 0;

        for (int i = 0; i < arr.length; i++) {
            HashSet<Integer> set = new HashSet<>();
            int minVal = arr[i], max_val = arr[i];
            for (int j = i; j < arr.length; j++) {
                if (set.contains(arr[j])) break;
                set.add(arr[j]);
                minVal=Math.min(minVal, arr[j]);
                max_val=Math.max(max_val, arr[j]);
                if (max_val - minVal == j - i) {
                    int length = j - i + 1;
                    if (length > maxLen) {
                        maxLen = length;
                        start = i;
                    }
                }
            }
        }
        int[] result = new int[maxLen];
        for (int i = 0; i < maxLen; i++) {
            result[i] = arr[start + i];
        }
        System.out.println();
        for (int i = 0; i < maxLen; i++) {
           System.out.print(result[i]+" ");
        }
    }
}