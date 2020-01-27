def solve(floors, a):
    a.append([1]*(floors+1))
    a.append([1]*(floors+1))
    for i in range(floors+1):
        a[0][i] = i
    a[1][0] = 0
    a[1][1] = 1
    a[1][2] = 2

    for i in range (3, floors+1):
        #print("loop {} a = {}".format(i, a))
        y = floors + 1000  
        for r in range(1, i):
            x = 1 + max(a[0][r-1], a[1][i-r])
            y = min(x,y)
        a[1][i] = y

if __name__ == "__main__":
    floors = int(input("enter floor count : "))
    a = []
    solve(floors, a)
    print("minimum possible ways to find floor (worst case) is {}".format(a[1][floors]))
    
    # print steps 
    b = []
    f = floors
    while f >= 1:
        x = a[1][f]
        b.append(x)
        f = f - x
    for i in range(1, len(b)):
        b[i] = b[i]+b[i-1]
    print("steps are : {}".format(b))
