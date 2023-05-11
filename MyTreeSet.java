public class MyTreeSet<K, V> {
    private int size = 0;
    private Node root = null;

    private class Node {
        public K key;
        public V value;
        public Node left = null;
        public Node right = null;
        public Node (K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public V put(K key, V value) {
        if (root == null) {
            root = new Node(key, value);
            size++;
            return null;
        }
        return putHelper(key, value, root);
    }

    private V putHelper(K key, V value, Node node) {
        Comparable<K> k = (Comparable<K>) key;
        if (k.compareTo(node.key) < 0) {
            if (node.left == null) {
                node.left = new Node(key, value);
                size++;
                return null;
            }
            return putHelper(key, value, node.left);
        }
        if (k.compareTo(node.key) > 0) {
            if (node.right == null) {
                node.right = new Node(key, value);
                size++;
                return null;
            }
            return putHelper(key, value, node.right);
        }
        if (k.compareTo(node.key) == 0) {
            V oldValue = node.value;
            node.value = value;
            return oldValue;
        }
        return null;
    }

    public V remove(Object key) {
        V oldValue = get(key);
        if (oldValue == null) return null;
        if (key == root.key) root = removeHelper(key);
        else removeHelper(key);
        System.out.println("Root = " + root.value);
        return oldValue;
    }

    private Node removeHelper(Object key) {
        Node child = findNode(key);
        Node parent = findParent(key);
        if (child.left == null && child.right == null) {
            if (child == parent.left) parent.left = null;
            if (child == parent.right) parent.right = null;
            size--;
            return parent;
        }
        if (child.right == null) {
            if (child == parent.left) parent.left = child.left;
            if (child == parent.right) parent.right = child.left;
            size--;
            return parent;
        }
        if (child.left == null) {
            if (child == parent.left) parent.left = child.right;
            if (child == parent.right) parent.right = child.right;
            size--;
            return parent;
        }
        Node tempNode = findSmallest(child.right);
        removeHelper(tempNode.key);
        child.key = tempNode.key;
        child.value = tempNode.value;
        return parent;
    }

    public V get(Object key) {
        try {
            Node node = findNode(key);
            if (node == null) return null;
            return node.value;
        } catch (NullPointerException nullPointerException) {
            return null;
        }
    }

    public boolean contains(Object key) {
        if (get(key) == null) return false;
        return true;
    }

    private Node findNode(Object target) throws NullPointerException {
        Comparable<K> t = (Comparable<K>) target;
        Node node = root;
        while (node != null) {
            if (t.compareTo(node.key) < 0) node = node.left;
            if (t.compareTo(node.key) > 0) node = node.right;
            if (t.compareTo(node.key) == 0) return node;
        }
        return null;
    }

    private Node findParent(Object target) {
        Comparable<K> t = (Comparable<K>) target;
        Node child = root;
        Node parent = root;
        while (child != null) {
            if (t.compareTo(child.key) < 0) {
                parent = child;
                child = child.left;
            }
            if (t.compareTo(child.key) > 0) {
                parent = child;
                child = child.right;
            }
            if (t.compareTo(child.key) == 0) return parent;
        }
        return null;
    }

    private Node findSmallest(Node node) {
        if (node.left == null) return node;
        return findSmallest(node.left);
    }

    public void printTree() {
        LER(root);
        System.out.println("-------------");
    }

    private void LER(Node node) {
        if (node.left != null) LER(node.left);
        System.out.println(node.value);
        if (node.right != null) LER(node.right);
    }
}
