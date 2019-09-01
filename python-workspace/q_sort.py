
def swap(a,i,j):
    t = a[i]
    a[i] = a[j]
    a[j] = t
    
def q_sort(a,L,R):
    if R<=L:
        return
    last = L
    for i in range(L+1,R+1):
        if a[i] < a[L]:
            swap(a,i,++last)
    swap(a,L,last)
    q_sort(a,L,last-1)
    q_sort(a,last+1,R)

if __name__ == "__main__":
    l = int(input("enter sample size : "))
    import random as r
    a = []
    for i in range(l):
        a.append(r.randint(1,100))
    b = a.copy()
    q_sort(b,0,len(b)-1)
    a.sort()
    assert a == b
    print("test case passed!!")

