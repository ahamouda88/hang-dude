package challenges.hangdude;

import java.util.Stack;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {
	@Test
	public void atest() {
		int[] values = new int[] { 5, 6, 3, 1, 2, 4 };
		bstDistance(values, values.length, 2, 4);
	}

	public int bstDistance(int[] values, int n, int node1, int node2) {
		if (values == null || values.length == 0) return 0;

		Node root = new Node(values[0]);
		for (int i = 1; i < values.length; i++) {
			addNode(root, values[i]);
		}

		System.out.println(root.val);
		return 0;
	}

	private void addNode(Node root, int val) {
		Node tmp = root;
		while (tmp != null) {
			if (tmp.val < val) {
				if(tmp.right == null){
					tmp.right = new Node(val);
					break;
				}else{
					tmp = tmp.right;
				}
			} else {
				if(tmp.left == null){
					tmp.left = new Node(val);
					break;
				}else{
					tmp = tmp.left;
				}
			}
		}
	}

	// A simple node class
	public class Node {
		int val;
		Node right;
		Node left;

		public Node(int val) {
			this.val = val;
		}
	}
}
