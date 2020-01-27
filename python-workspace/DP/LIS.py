def Lis(a, n):
    if n == 0:
        return 1, a[n]
    c, m = Lis(a, n-1)
    if m < a[n]:
        return 1+c, a[n]
    return c,m

if __name__ == "__main__":
    a = [10, 22, 9, 33]
    c,m = Lis(a,len(a)-1)
    print(c)