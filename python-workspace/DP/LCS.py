def LCS(s,t):
    a = []
    for i in range(len(t)+1):
        a.append([0]*(len(s)+1))
    #print(a)
    for i in range(1,len(t)+1):
        for j in range(1,len(s)+1):
            if t[i-1] == s[j-1]:
                a[i][j] = 1 + a[i-1][j-1]
            else:
                a[i][j] = max(a[i-1][j], a[i][j-1])    
    
    #print(a)
    return a[len(t)][len(s)]

if __name__ == "__main__":
    s = "AGGTAB" #input("enter first string : ")
    t = "GXTXAYB" #input("entere second stirng: ")
    print(LCS(s,t))