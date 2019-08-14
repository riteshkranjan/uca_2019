def sort(a):
    for i in range(0,len(a)):
        max = 0
        for j in range(1, len(a)-i):
            if a[max] < a[j]:
                max = j
        t = a[max]
        a[max] = a[len(a)-i-1]
        a[len(a)-i-1] = t

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