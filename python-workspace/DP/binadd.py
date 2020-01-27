import sys
import random
def add(a,b):
    #print("input are {} and {}".format(a,b))
    c = 0
    while b > 0:
        u = a^b
        v = a&b
        a = u
        b = v*2
        c +=1
    #print("sum is {} while loop count is {}".format(a,c))
    return a,c
if __name__ == "__main__":
    #ans,count = add(int(sys.argv[1],2),int(sys.argv[2],2))
    #print("sum of {} is {} with loop count = {}".format(sys.argv,ans,count))
    max = 0
    max_x = 0
    max_y = 0
    for i in range(1000000):
        x = random.randint(1, 1000000)
        y = random.randint(1, 1000000)
        ans1,count1 = add(x,y)
        ans2,count2 = add(y,x)
        if max < count1:
            max = count1
            max_x = x
            max_y = y
        if ans1 != ans2:
            print("error in sum for {}+{}={} while {}+{}={}".format(x,y,ans1,y,x,ans2))
        if count1 != count2:
            print("error in loop count for {}+{}:{} while {}+{}:{}".format(x,y,count1,y,x,count2))
    print("done with testing max is {} for {},{}".format(max,max_x,max_y))