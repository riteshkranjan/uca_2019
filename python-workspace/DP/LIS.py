def lis(a, n):
    LIS = [0]*n
    LIS[0] = 1
    for i in range(1,n):
        max = 1
        for j in range(i):
            if a[j] < a[i] and max < LIS[j]+1:
                max = 1 + LIS[j]
        LIS[i] = max   
    max = -1
    for i in LIS:
        if i > max:
            max = i
    print(a)
    print(LIS)
    return max
if __name__ == "__main__":
    """
    The Longest Increasing Subsequence (LIS) problem is to find the length of the longest subsequence of a given sequence such that 
    all elements of the subsequence are sorted in increasing order. 
    For example, the length of LIS for {10, 22, 9, 33, 21, 50, 41, 60, 80} is 6 and LIS is {10, 22, 33, 50, 60, 80}.
    """
    a = [10,22,9,33,21,50,41,60,20]
    n = len(a)
    print("longest increasing squence in arr {} is {}".format(a,lis(a,n)))