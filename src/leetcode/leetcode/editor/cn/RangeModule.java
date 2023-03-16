package leetcode.leetcode.editor.cn;

class RangeModule {

    // solv 2: segment tree

    SegmentTree node;
    public RangeModule() {
    }

    public static void main(String[] args) {
//        ["RangeModule","addRange","queryRange","removeRange","removeRange","addRange","queryRange","addRange","queryRange","removeRange"]
//[[],[5,8],[3,4],[5,6],[3,6],[1,3],[2,3],[4,8],[2,3],[4,9]]
        RangeModule range = new RangeModule();
        range.addRange(5, 8);
        range.removeRange(5, 6);
        range.removeRange(3, 6);
        range.addRange(1, 3);
//        range.queryRange(10, 14);
//        range.queryRange(13, 15);
        range.queryRange(2, 3);
    }

    public void addRange(int left, int right) {
        if (node == null) {
            node = build(left, right-1);
            return;
        }
        update(node, left, right-1, 1);
    }

    public boolean queryRange(int left, int right) {
        if (node == null) {
            return false;
        }
        return query(node, left, right-1) == right-left;
    }

    public void removeRange(int left, int right) {
        if (node == null) {
            return;
        }
        update(node, left, right-1, 0);
    }

    public SegmentTree build(int start, int end) {
        SegmentTree treeNode = new SegmentTree(start, end);
        if (start == end) {
            treeNode.val = 1;
            return treeNode;
        }
        int mid = (start+end)/2;
        treeNode.left = build(start, mid);
        treeNode.right = build(mid+1, end);
        treeNode.val = treeNode.left.val + treeNode.right.val;
        return treeNode;
    }
    public void update(SegmentTree treeNode, int l, int r, int val) {
        if (l > treeNode.end || r < treeNode.start) {
            return;
        }
        if (treeNode.start == treeNode.end) {
            treeNode.val = val;
            return;
        }
        int mid = (treeNode.start+treeNode.end) / 2;
        if (l <= mid) {
            update(treeNode.left, l, r, val);
        }
        if (r > mid) {
            update(treeNode.right, l, r, val);
        }
        treeNode.val = treeNode.left.val + treeNode.right.val;
    }

    public int query(SegmentTree treeNode, int l, int r) {
        if (l > treeNode.end || r < treeNode.start) {
            return 0;
        }
        if (l <= treeNode.start && r >= treeNode.end) {
            return treeNode.val;
        }
        int mid = (treeNode.start+treeNode.end) / 2;
        int ans = 0;
        if (l <= mid) {
            ans += query(treeNode.left, l, r);
        }
        if (r > mid) {
            ans += query(treeNode.right, l, r);
        }
        return ans;
    }
}
class SegmentTree {
    int start, end;
    int val;
    SegmentTree left, right;
    public SegmentTree(int _start, int _end) {
        this.start = _start;
        this.end = _end;
    }
}
