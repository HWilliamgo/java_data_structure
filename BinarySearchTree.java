import org.omg.CORBA.Any;

public class BinarySearchTree<AnyType extends Comparable<? super AnyType>> {
    //内部类：结点
    private static class BinaryNode<AnyType> {
        public BinaryNode(AnyType theElement) {
            this(theElement, null, null);
        }

        BinaryNode(AnyType element, BinaryNode<AnyType> left, BinaryNode<AnyType> right) {
            this.element = element;
            this.left = left;
            this.right = right;
        }

        AnyType element;
        BinaryNode<AnyType> left;
        BinaryNode<AnyType> right;
    }

    private BinaryNode<AnyType> root;

    public BinarySearchTree() {
        root = null;
    }

    public void makeEmpty() {
        root = null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public boolean contains(AnyType x) {
        return contains(x, root);
    }

    private boolean contains(AnyType x, BinaryNode<AnyType> t) {
        if (t == null) {
            return false;
        }
        int compareResult = x.compareTo(t.element);
        if (compareResult < 0) {
            return contains(x, t.left);
        } else if (compareResult > 0) {
            return contains(x, t.right);
        } else {
            return true;//Match
        }
    }

    public AnyType findMin() throws Exception {
        if (isEmpty()) {
            throw new Exception("the tree is empty");
        }
        return findMin(root).element;
    }

    private BinaryNode<AnyType> findMin(BinaryNode<AnyType> node) {
        if (node==null){
            return null;//如果一开始的起点是Null,直接返回Null
        }else if (node.left==null){
            return node;//如果结点左子树是null，那么本身是最小结点，返回自己。
        }
        //自己不是null,左子树不是null,那么递归到左子树去查找。
        return findMin(node.left);
    }

    public AnyType findMax() throws Exception {
        if (isEmpty()) {
            throw new Exception("the tree is empty");
        }
        return findMax(root).element;
    }

    private BinaryNode<AnyType> findMax(BinaryNode<AnyType> node) {
//        if (node==null){
//            return null;//起点是Null,直接返回null
//        }else if (node.right==null){
//            return node;//右子树是null，自己就是最大结点，返回自己
//        }
//        //存在右子树，递归到右子树去查找最大值。
//        return findMax(node.right);

        //上述是通过递归，这是通过循环的方法来findMax
        if(node!=null){
            while (node.right!=null){
                node=node.right;
            }
        }
        return node;
    }

    public void insert(AnyType x) {
        root = insert(x, root);
    }

    private BinaryNode<AnyType> insert(AnyType x, BinaryNode<AnyType> node) {
        if (node==null){
            return new BinaryNode<AnyType>(x,null,null);
        }

        int compareResult=x.compareTo(node.element);
        if (compareResult<0){
            node.left=insert(x,node.left);
        }else if (compareResult>0){
            node.right=insert(x,node.right);
        }else {
            //树中存在数据域等于x的结点
            //duplicate;do nothing
        }
        return node;
    }

    public void remove(AnyType x) {
        root = remove(x, root);
    }

    private BinaryNode<AnyType> remove(AnyType x, BinaryNode<AnyType> node) {
        if (node==null){
            return node;//Item not found;do nothing
        }
        int compareResult=x.compareTo(node.element);
        if (compareResult<0){
            node.left=remove(x,node.left);
        }else if (compareResult>0){
            node.right=remove(x,node.right);
        }else if (node.left!=null&&node.right!=null){//两个子树的情况
            //若要删除的结点有两个子树，将右子树中的最小结点的值复制给自己
            node.element=findMin(node.right).element;
            //然后删除被复制的那个右子树中的最小结点
            node.right=remove(node.element,node.right);
        }else {//一个子树和没有子树的情况
            //如果有一个左子树，让自己等于左子树，否则等于右子树。
            node=(node.left!=null)?node.left:node.right;
        }
        return node;
    }


    public void printTree() {

    }

}

