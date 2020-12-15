public class BinarySearchTree {
    TreeNode root;

    public BinarySearchTree() {
        root = null;
    }

    public void add(int data) {
        TreeNode node = new TreeNode(data);

        if (root == null) {
            root = node;
        } else {
            TreeNode temp = root;

            while (temp != node) {
                if (data < temp.data && temp.left == null) {
                    temp.left = node;
                    temp.left.parrent = temp;
                    return;
                } else if (data > temp.data && temp.right == null) {
                    temp.right = node;
                    temp.right.parrent = temp;
                    return;
                } else if (data == temp.data) {
                    return;
                } else {
                    temp = data < temp.data ? temp.left : temp.right;
                }
            }
        }
    }

    public TreeNode remove(int data) {
        if (root == null) {
            return null;
        }

        TreeNode temp = root;

        while (temp != null && temp.data != data) {
            temp = data < temp.data ? temp.left : temp.right;
        }

        if (temp == null) {
            return null;
        }

        if (temp.parrent == null) {
            root = null;
            return temp;
        }

        return null;
    }
}
