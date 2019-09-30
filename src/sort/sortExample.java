package sort;

public class sortExample {
    public static void quickSort(int[] a, int l, int r){
        if(l>r)
            return;

        int i,j,index;
        i=l;
        j=r;
        index=a[i];
        while(i<j){
            while(i<j&&a[j]>=index)
                j--;
            if(i<j)
                a[i++]=a[j];
            while(i<j&&a[i]<index)
                i++;
            if(i<j)
                a[j--]=a[i];
        }
        a[i]=index;
        quickSort(a,l,i-1);
        quickSort(a,i+1,r);
    }
    public static void merge(int[] a, int l, int mid, int r){
        int[] tmp=new int[r-l+1];
        int i=l;
        int j=mid+1;
        int k=0;
        while(i<=mid&&j<=r){
            if(a[i]<a[j])
                tmp[k++]=a[i++];
            else
                tmp[k++]=a[j++];
        }
        int index=(i<=mid)?i:j;
        int end=(i<=mid)?mid:r;
        while(index<=end){
            tmp[k++]=a[index++];
        }
        for(int b=0;b<tmp.length;b++){
            a[b+l]=tmp[b];
        }
    }
    public static void mergeSort(int[] a, int l, int r){
        int mid=(l+r)/2;
        if(l<r){
            mergeSort(a,l,mid);
            mergeSort(a,mid+1,r);
            merge(a,l,mid,r);
        }

    }
    public static void main(String[] args){
        int[] nums={1,2,5,3,9,4};
        //quickSort(nums,0,nums.length-1);
        mergeSort(nums,0,nums.length-1);
        for(int i=0;i<nums.length;i++){
            System.out.print(nums[i]+" ");
        }
    }
}
