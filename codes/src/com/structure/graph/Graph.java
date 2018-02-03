package com.structure.graph;

import java.util.Stack;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * 图
 */
public class Graph {
	
	
	
	public static void main(String[] args) {
		Graph g = new Graph();
		g.addNode('A');
		g.addNode('B');
		g.addNode('C');
		g.addNode('D');
		g.addNode('E');
		g.addNode('F');
		g.addNode('G');
		g.addNode('H');
		g.addNode('I');
		g.addNode('K');
		g.addNode('Z');
		g.addNode('X');
		g.addNode('Y');
		g.addNode('T');
		g.addNode('S');
		
		g.addEdge('A', 'B');
		g.addEdge('E', 'B');
		g.addEdge('E', 'H');
		g.addEdge('F', 'B');
		g.addEdge('F', 'I');
		g.addEdge('F', 'K');
		
		g.addEdge('A', 'C');
		g.addEdge('G', 'C');
		
		g.addEdge('A', 'D');
		g.addEdge('Z', 'D');
		g.addEdge('Z', 'X');
		g.addEdge('Y', 'X');
		g.addEdge('Y', 'S');
		g.addEdge('T', 'X');
		g.addEdge('T', 'S');
		
		g.printEdge();
		System.out.println();
		g.dfs();
		System.out.println();
		g.bfs();
	}
	
	// 默认20个节点
	private static final int DEFAULT_LENTH = 20;
	
	// 节点数组
	private char[] nodeList;
	
	// 邻接矩阵
	private int[][] adjMat;
	
	// 当前节点数量
	private int count;
	
	public Graph() {
		this(DEFAULT_LENTH);
	}
	
	public Graph(int length) {
		nodeList = new char[length];
		adjMat = new int[length][length];
		count = 0;
		
		// 数组数据默认为0
	}
	
	
	// 新加入一个节点
	public void addNode(char name) {
		nodeList[count] = name;
		count++;
	}
	
	// 新加入一条边
	public void addEdge(char start, char end) {
		int index_1  = -1;
		int index_2 = -1;
		
		for (int i=0; i<count; i++) {
			if (nodeList[i] == start) {
				index_1 = i;
			} else if (nodeList[i] == end) {
				index_2 = i;
			}
		}
		
		adjMat[index_1][index_2] = 1;
		adjMat[index_2][index_1] = 1;
	}
	
	public void printEdge() {
		for (int i=0; i<count; i++) {
			for (int j=0; j<count; j++) {
				System.out.print(adjMat[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	
	// 深度优先遍历，  利用栈
	public void dfs() {
		int isView[] = new int[count];
		Stack<Integer> stack = new Stack<>();
		
		// 从第一个节点遍历,  注意只针对连通图
		int index = 0;
		stack.push(index); 					// 将第一个元素放入栈顶
		isView[index] = 1;                  // 第一个元素置为已读
		System.out.print(nodeList[index] + " ");
		
		while (true) {
			index = getAdjNode(stack.peek(), isView); // 取出栈顶元素，但不删除， 找到栈顶元素的邻接节点
			if (index != -1) {                        // 有邻接节点
				stack.push(index);
				isView[index] = 1;
				System.out.print(nodeList[index] + " ");
			}
			else {
				stack.pop(); // 当前栈顶元素没有邻接节点了，移除它
				
				if (stack.isEmpty())
					return ;
			}
		}
	}
	
	// 得到与之相连的第一个节点
	private int getAdjNode(int row, int[] isView) {
		for (int i = 0; i < count; i++) {
			if (adjMat[row][i]==1 && isView[i]==0)
				return i;
		}
		return -1;
	}
	
	
	
	// 广度优先遍历，  利用队列
	public void bfs() {
		int isView[] = new int[count];
		ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<>(count);
		
		int index = 0;
		queue.add(0);			  		    // 将第一个元素放入队列
		isView[index] = 1;                  // 第一个元素置为已读
		System.out.print(nodeList[index] + " ");
		
		while (true) {
			index = getAdjNode(queue.element(), isView); // 取出收个元素，但不删除
			if (index != -1) {                           // 有邻接节点
				queue.add(index);			 
				isView[index] = 1;
				System.out.print(nodeList[index] + " ");
			}
			else {
				queue.remove(); 			// 当前队首元素没有邻接节点了，移除它
				                            // 继续下一次的遍历，找队首节点的全部的邻接节点
				if (queue.isEmpty())
					return ;
			}
		}
	}
	
	
	
	
	
	
	
}
