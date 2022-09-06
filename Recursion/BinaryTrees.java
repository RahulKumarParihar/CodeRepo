import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class BinaryTreeNode<T> {
    T data;
    BinaryTreeNode<T> leftChild;
    BinaryTreeNode<T> rightChild;

    public BinaryTreeNode(T data) {
        this.data = data;
        leftChild = null;
        rightChild = null;
    }
}

public class BinaryTrees {
    BinaryTreeNode<Integer> root;
    Scanner scan;

    public BinaryTrees() {
        root = null;
        scan = new Scanner(System.in);
    }

    /**
     * Contruct a binary tree
     */
    public void BuildTree() {
        Queue<BinaryTreeNode<Integer>> queue = new LinkedList<BinaryTreeNode<Integer>>();
        System.out.println("Please enter integer values, -1 Represents NULL");

        // root
        System.out.print("Enter the value for root of the tree: ");
        int rootValue = scan.nextInt();
        root = CreateBinaryTreeNode(rootValue);
        queue.offer(root);

        // child
        AddChildNodes(queue);

        System.out.println();

        // If not closed will lead to memory leak
        scan.close();
    }

    public void PreOrderTraversal() {
        System.out.print("PreOrder: ");
        PreOrderTraversalHelper(root);
        System.out.println();
    }

    public void InOrderTraversal() {
        System.out.print("InOrder: ");
        InOrderTraversalHelper(root);
        System.out.println();
    }

    public void PostOrderTraversal() {
        System.out.print("PostOrder: ");
        PostOrderTraversalHelper(root);
        System.out.println();
    }

    private BinaryTreeNode<Integer> CreateBinaryTreeNode(Integer data) {
        return new BinaryTreeNode<Integer>(data);
    }

    private void AddChildNodes(Queue<BinaryTreeNode<Integer>> queue) {
        while (!queue.isEmpty()) {
            BinaryTreeNode<Integer> parentNode = queue.poll();

            if (parentNode == null) {
                continue;
            }

            // left child
            System.out.print("Enter the value for left child of " + parentNode.data + ": ");
            int leftChildValue = scan.nextInt();
            if (!CheckIfRepresentsNull(leftChildValue)) {
                parentNode.leftChild = CreateBinaryTreeNode(leftChildValue);
                queue.offer(parentNode.leftChild);
            }

            // right child
            System.out.print("Enter the value for right child of " + parentNode.data + ": ");
            int rightChildValue = scan.nextInt();
            if (!CheckIfRepresentsNull(rightChildValue)) {
                parentNode.rightChild = CreateBinaryTreeNode(rightChildValue);
                queue.offer(parentNode.rightChild);
            }

        }
    }

    private boolean CheckIfRepresentsNull(int value) {
        return value == -1;
    }

    private void PreOrderTraversalHelper(BinaryTreeNode<Integer> root) {
        // Base Case
        if (root == null)
            return;

        // NLR
        System.out.print(root.data + " ");
        PreOrderTraversalHelper(root.leftChild);
        PreOrderTraversalHelper(root.rightChild);
    }

    private void InOrderTraversalHelper(BinaryTreeNode<Integer> root) {
        // Base Case
        if (root == null)
            return;

        // LNR
        InOrderTraversalHelper(root.leftChild);
        System.out.print(root.data + " ");
        InOrderTraversalHelper(root.rightChild);
    }

    private void PostOrderTraversalHelper(BinaryTreeNode<Integer> root) {
        // Base Case
        if (root == null)
            return;

        // LRN
        PostOrderTraversalHelper(root.leftChild);
        PostOrderTraversalHelper(root.rightChild);
        System.out.print(root.data + " ");
    }

    public static void main(String args[]) {
        BinaryTrees binaryTrees = new BinaryTrees();
        binaryTrees.BuildTree();
        binaryTrees.PreOrderTraversal();
        binaryTrees.InOrderTraversal();
        binaryTrees.PostOrderTraversal();
    }
}
