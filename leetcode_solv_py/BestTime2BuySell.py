import numpy as np

class Solution:
    def maxProfit(self, prices):
        """
        :type prices: List[int]
        :rtype: int
        """
        # prices = np.array(prices)
        length = prices.__len__()
        result = 0
        min = 1e1000
        for i in range(length):
            if prices[i] < min:
                min = prices[i]
            elif prices[i] - min > result:
                result = prices[i] - min
        return result

if __name__ == '__main__':
    solv = Solution()
    data1 = [7, 1, 5, 3, 6, 4]
    print(Solution.maxProfit(solv, data1))
    data2 = [7, 6, 4, 3, 1]
    print(Solution.maxProfit(solv, data2))
