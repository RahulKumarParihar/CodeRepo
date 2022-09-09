import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
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

    public int MaxDepth() {
        return MaxDepthHelper(root);
    }

    public boolean isSymmetric() {
        if (root == null)
            return true;

        return CheckMirror(root.leftChild, root.rightChild);
    }

    public boolean CheckMirror(BinaryTreeNode<Integer> root1, BinaryTreeNode<Integer> root2) {
        if (root1 == null && root2 == null)
            return true;

        if (root1 != null && root2 == null)
            return false;

        if (root1 == null && root2 != null)
            return false;

        if (root1.data != root2.data)
            return false;

        if (!CheckMirror(root1.leftChild, root2.rightChild))
            return false;

        if (!CheckMirror(root1.rightChild, root2.leftChild))
            return false;

        return true;
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

    private int MaxDepthHelper(BinaryTreeNode<Integer> root) {
        if (root == null)
            return 0;

        int left = MaxDepthHelper(root.leftChild);
        int right = MaxDepthHelper(root.rightChild);

        return 1 + Math.max(left, right);
    }

    /**
     * PreOrder
     * @param root
     */
    public void createMirror(BinaryTreeNode<Integer> root) {
        if (root == null)
            return;

        BinaryTreeNode<Integer> temp = root.leftChild;
        root.leftChild = root.rightChild;
        root.rightChild = temp;

        createMirror(root.leftChild);
        createMirror(root.rightChild);
    }

    /**
     * PreOrder
     * @param root
     * @param target
     * @return
     */
    public boolean search(BinaryTreeNode<Integer> root, int target) {
        // base case
        if (root == null)
            return false;

        if (root.data == target)
            return true;

        return search(root.leftChild, target)
                || search(root.rightChild, target);
    }

    public List<String> getPaths() {
        List<String> paths = new ArrayList<>();

        getPathsHelper(root, paths, new StringBuilder());

        return paths;
    }

    private void getPathsHelper(BinaryTreeNode<Integer> root, List<String> paths, StringBuilder sb) {
        if(root == null) {
            paths.add(sb.toString());
            return;
        }

        sb.append(root.data);

        // Since this will print the path twice
        if(root.leftChild == null && root.rightChild == null) {
            paths.add(sb.toString());
            sb.deleteCharAt(sb.length() - 1);
            return;
        }

        getPathsHelper(root.leftChild, paths, sb);
        getPathsHelper(root.rightChild, paths, sb);

        // replacing 
        sb.deleteCharAt(sb.length() - 1);
    }

    public String getLongestPath() {
        StringBuilder longestPath = new StringBuilder();
        getLongestPathHelper(root, new StringBuilder(), longestPath);
        return longestPath.toString();
    }

    private void getLongestPathHelper(BinaryTreeNode<Integer> root, StringBuilder sb, StringBuilder longestPath) {
        if(root == null) {
            if(longestPath.length() < sb.length()) {
                longestPath.delete(0, longestPath.length());
                longestPath.append(sb.toString());
            }

            return;
        }

        sb.append(root.data);

        // Since this will print the path twice
        if(root.leftChild == null && root.rightChild == null) {
            if(longestPath.length() < sb.length()) {
                longestPath.delete(0, longestPath.length());
                longestPath.append(sb.toString());
            }
            sb.deleteCharAt(sb.length() - 1);
            return;
        }

        getLongestPathHelper(root.leftChild, sb, longestPath);
        getLongestPathHelper(root.rightChild, sb, longestPath);

        // replacing 
        sb.deleteCharAt(sb.length() - 1);
    }

    public static void main(String args[]) {
        BinaryTrees binaryTrees = new BinaryTrees();
        binaryTrees.BuildTree();
        binaryTrees.PreOrderTraversal();
        binaryTrees.InOrderTraversal();
        binaryTrees.PostOrderTraversal();
        System.out.println("Height/Depth of the tree: " + binaryTrees.MaxDepth());
        binaryTrees.createMirror(binaryTrees.root);
        binaryTrees.PreOrderTraversal();
        binaryTrees.InOrderTraversal();
        binaryTrees.PostOrderTraversal();
        System.out.println("Search 8: " + binaryTrees.search(binaryTrees.root, 8));
        System.out.println("Search 15: " + binaryTrees.search(binaryTrees.root, 15));
        System.out.println("Search -1: " + binaryTrees.search(binaryTrees.root, -1));

        List<String> paths = binaryTrees.getPaths();

        for (String path : paths) {
            System.out.println(path);
        }

        System.out.println("Longest Path: " + binaryTrees.getLongestPath());
    }
}
