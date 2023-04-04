# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

class Solution:
    def sortedArrayToBST(self,nums):
        """
        :type nums: List[int]
        :rtype: TreeNode
        """
        self.nums = nums # 不是构造函数时，此行可缺省
        if nums == None or len(nums) == 0:
            return None
        return self.getBST(nums)

    def getBST(self,nums):
        self.nums = nums # 不是构造函数时，此行可缺省
        length = len(nums)
        if length == 0:
            return None
        if length == 1:
            return TreeNode(nums[0])
        root = TreeNode(nums[int(length/2)])
        root.left = self.getBST(nums[:int(length/2)])
        root.right = self.getBST(nums[int(length/2)+1:])
        return root

class TreeNode:
    def __init__(self,x):
        self.val = x
        self.left = None
        self.right = None
    # def setLeft(self,left):
    #     self.left = left
    # def setRight(self,right):
    #     self.right = right
