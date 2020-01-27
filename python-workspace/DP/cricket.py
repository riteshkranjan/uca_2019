def solve(runs, a):
    a.append([0]*(runs+1))
    a.append([0]*(runs+1))
    a[0][1] = 1
    a[1][1] = 1
    a[0][2] = 2
    a[1][2] = 1
    for i in range(3, runs+1):
        a[0][i] = a[0][i-1] + a[1][i-2]
        a[1][i] = a[0][i-1]
if __name__ == "__main__":
    runs = int(input("enter no of runs needed : "))
    a = []
    solve(runs, a)
    print(a[0])
    print(a[1])
    print("number of ways {} runs can be takes is {}".format(runs, a[0][runs]))
    