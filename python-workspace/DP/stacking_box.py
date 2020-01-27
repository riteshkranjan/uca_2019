class Box:
    def __init__(self, h,w,d):
        self.h = h
        self.w = w
        self.d = d
    def __lt__(self, o):
        return self.d * self.w < o.d * o.w 
    def __str__(self):
        return "{},{},{}".format(self.h,self.w, self.d)
    def __repr__(self):
        return "{},{},{}".format(self.h,self.w, self.d)
    def placeable(self, o):
        return o.w < self.w and o.d < self.d
        
def solve(a,n):
    b = []
    for idx, val in enumerate(a):
        c = Box(val[0], max(val[1], val[2]),min(val[1], val[2]))
        b.append(c)
        c = Box(val[1], max(val[0], val[2]),min(val[0], val[2]))
        b.append(c)
        print(c)
        if c.w != c.d:
            c = Box(val[2], max(val[1], val[0]),min(val[1], val[0]))
            b.append(c)
    print(b)
    b.sort(reverse=True)
    print(b)
    n = n*3
    msh = [0] * n
    for i in range(n): 
        msh[i] = b[i].h
    print(msh)
    for i in range(1, n): 
        for j in range(0, i): 
            if (b[j].placeable(b[i])): 
                msh[i] = max(msh[i], msh[j] + b[i].h)
    print(msh)
    return max(msh)

if __name__ == "__main__":
    a = [[4, 6, 7],[1, 2, 3],[4, 5, 6],[10, 12, 32]]
    n = len(a) 
    print(solve(a,n))
    print(a)
