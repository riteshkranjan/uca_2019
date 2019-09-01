def sort(a):
    for i in range(1,len(a)):
        t = a[i]
        k = i
        while k > 0 and a[k-1] > t:
            a[k] =  a[k-1]
            k -= 1
        a[k] = t


if __name__ == '__main__':
    l = int(input("enter sample size : "))
    import random as r
    import time
    a = []
    for i in range(l):
        a.append(r.randint(1,100))
    b = a.copy()
    print(int(time.time()))
    sort(b)
    print(int(time.time()))
    a.sort()
    assert a == b
    print("test case passed!!")