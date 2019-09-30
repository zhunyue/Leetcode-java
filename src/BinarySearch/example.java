package BinarySearch;

public class example {
        //第一个大于某值
        public static int Search1(int[] arr, int t){
            int left=0;
            int right=arr.length-1;
            while(left<=right){
                int mid=(left+right);
                if(arr[mid]<=t){
                    left=mid+1;
                }
                else
                    right=mid-1;
            }
            if(left==arr.length)
                return -1;

            return left;
        }

        //最后一个小于等于
        public static int Search2(int[] arr, int t){
            int left=0;
            int right=arr.length-1;
            while(left<=right){
                int mid=(left+right);
                if(arr[mid]<=t){
                    left=mid+1;
                }
                else
                    right=mid-1;
            }
            if(left==arr.length)
                return -1;
            return left-1;
        }

        //最后一个等于
        public static int Search3(int[] arr, int t){
            int left=0;
            int right=arr.length-1;
            while(left<=right){
                int mid=(left+right);
                if(arr[mid]<=t){
                    left=mid+1;
                }
                else
                    right=mid-1;
            }
            if(right>=0&&arr[right]==t)
                return right;
            return -1;
        }

        public static void main(String[] args){
            int[] arr={0,0,0,1,3,5,7,9};
            System.out.println(Search1(arr,6));
            System.out.println(Search2(arr,2));
            System.out.println(Search3(arr,0));
        }
}
