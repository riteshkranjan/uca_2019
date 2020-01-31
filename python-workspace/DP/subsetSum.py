def subset(set, sum, n):
    subset=([[False for i in range(sum+1)] for i in range(n+1)]) 
    for i in range(n+1): 
        subset[i][0] = True
    for i in range(1,sum+1): 
            subset[0][i]=False
    for i in range(1,n+1): 
            for j in range(1,sum+1): 
                if j<set[i-1]: 
                    subset[i][j] = subset[i-1][j] 
                if j>=set[i-1]: 
                    subset[i][j] = (subset[i-1][j] or subset[i - 1][j-set[i-1]]) 
    return subset[n][sum] 

if __name__ == "__main__":
    set = [3, 34, 4, 12, 5, 2] 
    sum = 9
    n = len(set)
    print(subset(set,sum,n))
    """
    Given a set of non-negative integers, and a value sum, determine if there is a subset 
    of the given set with sum equal to given sum
    
    Input:  set[] = {3, 34, 4, 12, 5, 2}, sum = 9
    Output:  True  //There is a subset (4, 5) with sum 9.
    Logic:
    
    isSubsetSum(set, n, sum) = isSubsetSum(set, n-1, sum) || 
                           isSubsetSum(set, n-1, sum-set[n-1])
    Base Cases:
    isSubsetSum(set, n, sum) = false, if sum > 0 and n == 0
    isSubsetSum(set, n, sum) = true, if sum == 0 

    """