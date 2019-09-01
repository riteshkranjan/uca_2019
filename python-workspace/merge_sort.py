def merge(a, L, mid, R, aux, inversions):
    i = L
    j = mid+1
    inversion = 0
    for k in range(L, R+1):
        if i > mid:
            aux[k] = a[j]
            j += 1
        elif j > R:
            aux[k] = a[i]
            i += 1
        elif a[i] > a[j]:
            inversion += mid - i + 1
            for x in range(i,mid+1):
                inversions.append((a[x],a[j]))
            aux[k] = a[j]
            j += 1
        else:
            aux[k] = a[i]
            i += 1

    for k in range(L, R+1):
        a[k] = aux[k]
    return R-L, inversion


def merge_sort(a, L, R, aux, inversions):
    if L == R:
        return 0,0
    mid = int((L+R)/2)
    n1,i1 = merge_sort(a, L, mid, aux, inversions)
    n2, i2 = merge_sort(a, mid+1, R, aux, inversions)
    n3, i3 = merge(a, L, mid, R, aux, inversions)
    n1 = n1+n2+n3
    i1 = i1+i2+i3
    return n1,i1


if __name__ == '__main__':
    l = 20
    import random as r
    a = []
    for i in range(l):
        a.append(r.randint(1, 100))
    aux = [None] * len(a)
    b = a.copy()
    inversions = []
    run_time, inversion_count = merge_sort(b, 0, len(b)-1, aux, inversions)
    import math
    print(l * int(math.log2(l)))
    a.sort()
    assert a == b

    print(run_time)
    print(inversion_count)

    b = [2,4,1,3,5]
    inversions = []
    run_time, inversion_count = merge_sort(b, 0, len(b)-1, aux, inversions)
    print("inversions are :", inversions)
    print("run time : ", run_time)
    print("inversion count : ", inversion_count)
    print("test case passed!!")
