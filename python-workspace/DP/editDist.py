def editDist(s1,s2,n,m):
    ED = []
    ED.append([0]*(n+1))
    for j in range(1,n+1):
        ED[0][j] = j
    for i in range(1,m+1):
        ED.append([0]*(n+1))
        ED[i][0] = i
        for j in range(1,n+1):
            if s1[j-1] == s2[i-1]:
                ED[i][j] = ED[i-1][j-1]
            else: 
                ED[i][j] = 1+min(ED[i-1][j], ED[i-1][j-1], ED[i][j-1])
    print(ED)
    return ED[m][n]
if __name__ == "__main__":
    s1 = "geek"
    s2 = "gesek"
    print(editDist(s1,s2,len(s1),len(s2)))

    """
    Given two strings str1 and str2 and below operations that can performed on str1. Find minimum number of edits (operations) required to convert ‘str1’ into ‘str2’.

    1. Insert
    2. Remove
    3. Replace
    All of the above operations are of equal cost.
    Input:   str1 = "geek", str2 = "gesek"
    Output:  1  We can convert str1 into str2 by inserting a 's'.
    
    
     f(n,m) = if n == 0 return m
              if m == 0 return n
              if(s[n-1] == s[m-1]) return f(n-1, m-1)
              return 1 + min(
                               f(n-1,m), delete case
                               f(n,m-1),  insert
                               f(n-1,m-1), update 
                            )

    """