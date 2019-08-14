def merge(a, L, mid, R, aux):
    i = L
    j = mid+1
    for k in range(L,R+1):
        if i > mid:
            aux[k] = a[j]
            j += 1
        elif j > R:
            aux[k] = a[i]
            i += 1
        elif a[i] > a[j]:
            aux[k] = a[j]
            j += 1
        else:
            aux[k] = a[i]
            i += 1
    
    for k in range(L, R+1):
        a[k] = aux[k]

def merge_sort(a, L, R, aux):
    if L == R:
        return 
    mid = int((L+R)/2)
    merge_sort(a,L,mid, aux)
    merge_sort(a, mid+1, R, aux)
    merge(a, L, mid, R, aux)

if __name__ == '__main__':
    l = int(input("enter sample size : "))
    import random as r
    import time
    a = []
    for i in range(l):
        a.append(r.randint(1,100))
    aux = [None] * len(a)
    b = a.copy()
    print(int(time.time()))
    merge_sort(b,0,len(b)-1, aux)
    print(int(time.time()))
    a.sort()
    assert a == b
    print("test case passed!!")