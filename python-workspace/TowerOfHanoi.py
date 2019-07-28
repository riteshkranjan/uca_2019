def solve(h,t_from, t_helper, t_to):
    if h>0:
        solve(h-1, t_from,t_to,t_helper)
        move(t_from,t_to)
        solve(h-1, t_helper, t_from, t_to)
def move(t_from, t_to):
    a = 0
    for i in range(len(t_from)-1, 0,-1):
        if t_from[i] != 0:
            a=t_from[i]
            t_from[i] = 0
            break
    for i in range(len(t_to)):
        if t_to[i] == 0:
            t_to[i] = a
            break
    print("moving {} from {} to {}".format(a,t_from,t_to))

a = ["A",3,2,1]
b = ["B",0,0,0]
c = ["C",0,0,0]

print(a)
print(b)
print(c)

solve(3,a,b,c)

print(a)
print(b)
print(c)