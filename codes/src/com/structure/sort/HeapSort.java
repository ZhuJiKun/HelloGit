package com.structure.sort;

/**
 * 堆排序<br>
 *
 * @author zhujk
 */
public class HeapSort {

	public static void main(String[] args) {
		int a[] = {
				10, 9, 8, 7, 6,
				5, 4, 3, 2, 1
		};
		
		a = HeapSort.sort(a, Boolean.TRUE);
		for (int i : a) {
			System.out.println(i);
		}
	}
	
	
	
	public static int[] sort(int[] waitedSort, boolean isAsc) {
		int[] arr = new int[waitedSort.length];
		System.arraycopy(waitedSort, 0, arr, 0, waitedSort.length); //
				
		if (isAsc) 
			sortAsc(arr);
		else {}
			
		
		
		return arr;
	}
	
	private static void sortAsc(int[] arr) {
		int size = arr.length; 	 // 数组容量
		int index = 1;           // 当前待排序元素的下标
		
		// 向堆中存数据
		for (; index<size; index++) {
			// 从数组无需部分取出， 置入堆部分
			
			// 堆排序始终向最后一个子节点添加元素，形成新的堆， 调整堆使其符合堆的特性
			trickleUpAsc(arr, index);
		}
		
		// 从堆中取数据
		int[] a = new int[size];
		index = 0;
		for (; index<size; index++) {
			a[index] = arr[0];
			trickleDownAsc(arr, size-index-1);
		}
	}


	private static void trickleUpAsc(int[] arr, int index) {
		// 与 父节点比较， 如果父节点比自己大，就交换位置 父节点下标是  （index -1 ）/ 2
		
		int temp = arr[index];
		
		while (index>=0) {
			if (index == 0) {
				arr[index] = temp;
			}
			
			if (arr[(index-1)/2] > temp) {
				// 如果父节点比自己大， 交换位置
				arr[index] = arr[(index-1)/2];
				index = (index-1)/2;
			}
			else {
				arr[index] = temp;
				break;
			}
		}
	}
	
	private static void trickleDownAsc(int[] arr, int lastIndex) {
		int temp = arr[lastIndex];
		arr[0] = temp;
		
		int index = 0;
		while (index <= (lastIndex - 1)/2) {
			// 比较左子节点和右子节点
			if (index*2+1 == lastIndex || index*2+2 == lastIndex) {
				
				break;
			}
				
			if (temp<arr[index*2+1] && temp<arr[index*2+2]){}
			
			
			if (arr[index*2+1] < arr[index*2+2]) {
				arr[index] = arr[index*2+1];
			} else {
				arr[index*2+2] = arr[index*2+1];
			}
			
			
			
			
		}
	}
	
	
	
	
	
	
	
	
	
	
	
}