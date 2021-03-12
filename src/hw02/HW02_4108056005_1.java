// normal case: O(N^2*logN)
//package hw02;

public class HW02_4108056005_1 extends ThreeSum {
	public static void main(String[] args) {
		
		HW02_4108056005_1 test = new HW02_4108056005_1();
//		RandomArray ra = new RandomArray();
//		int[] A = ra.createArray(10000, 10000);
//		int[] A = {43, 29, -75, 37, -94, -9, 85, 83, 75, -76, 72, 46, 84, -90, -85, -37, 99, -88, 96, -43, -12, 70, -69, 45, 51, -89, -77, 59, 76, 60, -93, -45, 69, 92, -53, -28, -74, 89, -38, -34, 36, -36, -25, -3, 95, 12, -97, -16, 21, -60, -61, 8, 32, 93, -33, -1, 33, 57, 4, 18, -73, -32, 53, -29, -46, -35, -100, 61, 55, -8, 5, 40, -98, 77, -31, 13, 65, 62, 7, -49, 97, 98, 1, -55, -64, -14, -92, 38, -62, -7, 58, 78, -96, 20, 11, -83, 31, 14, 94, -95};
//		Stopwatch stopwatch = new Stopwatch();
//		System.out.println("ThreeSum is zero: "+test.T_sum(A));
//		double time = stopwatch.elapsedTime();
//		System.out.println("elapsed time " + time);
	}

	@Override
	// O(N^2*logN)
	public int T_sum(int[] A) {
		int count = 0;
		this.quickSort(A, 0, A.length-1);	// quickSort O(NlogN)
//		this.show(A);
		// O(N^2*logN)
		for(int i=0; i<A.length; i++) {
			for(int j=i+1; j<A.length; j++) {
				if(this.rank(A, -(A[i]+A[j])) > j) {
					count++;
				}
			}
		}
		return count;
	}
	
	public void show(int[] A) {
		for(int i=0; i<A.length; i++) {
			System.out.print(A[i]+" ");
		}
		System.out.println();
	}
	
	public int rank(int[] A, int key) {
		int low = 0, high = A.length-1;
		while(low <= high) {
			int mid = low+(high-low)/2;
			if(key < A[mid]) high = mid-1;
			else if(key > A[mid]) low = mid+1;
			else return mid;
		}
		return -1;
	}
	
	public void quickSort(int arr[], int begin, int end) {
	    if (begin < end) {
	        int partitionIndex = partition(arr, begin, end);

	        quickSort(arr, begin, partitionIndex-1);
	        quickSort(arr, partitionIndex+1, end);
	    }
	}
	
	private int partition(int arr[], int begin, int end) {
	    int pivot = arr[end];
	    int i = (begin-1);

	    for (int j = begin; j < end; j++) {
	        if (arr[j] <= pivot) {
	            i++;

	            int swapTemp = arr[i];
	            arr[i] = arr[j];
	            arr[j] = swapTemp;
	        }
	    }

	    int swapTemp = arr[i+1];
	    arr[i+1] = arr[end];
	    arr[end] = swapTemp;

	    return i+1;
	}
}