package homework6;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Trees {
    private static class Cat {
        String name;
        int age;

        public Cat(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Cat cat = (Cat) o;
            return age == cat.age &&
                    name.equals(cat.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, age);
        }

        @Override
        public String toString() {
            return String.format("C (%s, %d)", name, age);
        }
    }

    public static class Tree {
        public class TreeNode {
            private Cat cat;
            public TreeNode leftChild;
            public TreeNode rightChild;

            public TreeNode(Cat cat) {
                this.cat = cat;
            }

            @Override
            public String toString() {
                return String.format("TN(%s)", cat);
            }
        }

        private TreeNode root;

        public Tree() {
            root = null;
        }

        public void insert(Cat c) {
            TreeNode node = new TreeNode(c);
            if (root == null) {
                root = node;
            } else {
                TreeNode current = root;
                TreeNode parent;
                while (true) {
                    parent = current;
                    if (c.age < current.cat.age) {
                        current = current.leftChild;
                        if (current == null) {
                            parent.leftChild = node;
                            return;
                        }
                    } else if (c.age > current.cat.age) {
                        current = current.rightChild;
                        if (current == null) {
                            parent.rightChild = node;
                            return;
                        }
                    } else {
                        return;
                    }
                }

            }
        }

        public Cat find(int age) {
            TreeNode current = root;
            while (current.cat.age != age) {
                if (age < current.cat.age)
                    current = current.leftChild;
                else
                    current = current.rightChild;

                if (current == null)
                    return null;
            }
            return current.cat;
        }

        private void inOrderTravers(TreeNode current) {
            if (current != null) {
                System.out.println(current);
                inOrderTravers(current.leftChild);
                inOrderTravers(current.rightChild);
            }
        }

        public void displayTree() {
            inOrderTravers(root);
        }

        public boolean delete(int age) {
            TreeNode curr = root;
            TreeNode prev = root;
            boolean isLeftChild = true;
            while (curr.cat.age != age) {
                prev = curr;
                if (age < curr.cat.age) {
                    isLeftChild = true;
                    curr = curr.leftChild;
                } else {
                    isLeftChild = false;
                    curr = curr.rightChild;
                }

                if (curr == null)
                    return false;
            }

            if (curr.leftChild == null && curr.rightChild == null) {
                if (curr == root) {
                    root = null;
                } else if (isLeftChild) {
                    prev.leftChild = null;
                } else {
                    prev.rightChild = null;
                }
            } else if (curr.rightChild == null) {
                if (isLeftChild) {
                    prev.leftChild = curr.leftChild;
                } else {
                    prev.rightChild = curr.leftChild;
                }
            } else if (curr.leftChild == null) {
                if (isLeftChild) {
                    prev.leftChild = curr.rightChild;
                } else {
                    prev.rightChild = curr.rightChild;
                }
            } else {
                TreeNode successor = getSuccessor(curr);
                if (curr == root) {
                    root = successor;
                } else if (isLeftChild) {
                    prev.leftChild = successor;
                } else {
                    prev.rightChild = successor;
                }
                successor.leftChild = curr.leftChild;
            }
            return true;
        }

        private TreeNode getSuccessor(TreeNode deleted) {
            TreeNode successorParent = deleted;
            TreeNode successor = deleted;
            TreeNode flag = deleted.rightChild;

            while (flag != null) {
                successorParent = successor;
                successor = flag;
                flag = flag.leftChild;
            }
            if (successor != deleted.rightChild) {
                successorParent.leftChild = successor.rightChild;
                successor.rightChild = deleted.rightChild;
            }
            return successor;
        }

        public Tree(List<Integer> sampleData) {
            sampleData.forEach(o -> insert(new Cat("Cat" + o, o)));
        }

        public boolean isBalanced(boolean precision) {
            return Math.abs(countDepth(root.leftChild) - countDepth(root.rightChild))
                    <= ((precision) ? 0 : 1);
        }

        private int countDepth(TreeNode node) {
            if (node == null) return 0;

            int left = 0;
            int right = 0;

            if (node.leftChild != null)
                left = countDepth(node.leftChild);

            if (node.rightChild != null)
                right = countDepth(node.rightChild);

            return 1 + Math.max(left, right);
        }

        //every subtree checked
        int isBalanced(TreeNode root) {
            if (root == null) return 0;
            int lDepth;
            int rDepth;
            lDepth = isBalanced(root.leftChild);
            if (lDepth == -1) return -1;
            rDepth = isBalanced(root.rightChild);
            if (rDepth == -1) return -1;
            if (Math.abs(lDepth - rDepth) > 1)
                return -1;
            return 1 + ((lDepth > rDepth) ? lDepth : rDepth);
        }
    }

    private static void uniqueRandom(List<Integer> a, int amount) {
        SecureRandom sr = new SecureRandom();
        while (a.size() < amount) {
            int num = sr.nextInt();
            if (!a.contains(num))
                a.add(num);
        }
    }

    public static void main(String[] args) {
        final int TREES = 50;
        int balanced = 0;
        for (int i = 0; i < TREES; i++) {
            ArrayList<Integer> a = new ArrayList<>();
            uniqueRandom(a, 1000);
            Tree t = new Tree(a);
            balanced += (t.isBalanced(false)) ? 1 : 0;
        }
        System.out.println(balanced * 100f / TREES + "%");
    }
}
