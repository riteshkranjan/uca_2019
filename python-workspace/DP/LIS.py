def lis(a, n):
    LIS = [1]*n
    for i in range(1,n):
        max = 1
        for j in range(i):
            if a[j] < a[i] and max < 1 + LIS[j]:
                max  = 1 + LIS[j]
        LIS[i] = max
    max = -1
    for i in LIS:
        if max < i:
            max = i
    return max

if __name__ == "__main__":
    a = [10, 22, 9, 33,21,50,41,60,20]
    n = len(a)
    print("longest increasing squence in arr {} is {}".format(a,lis(a,n)))
