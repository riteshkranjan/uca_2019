def get_xor(a):
    xor = 0
    for i in a:
        xor = xor ^ i
    return xor
def find_lonely(a):
    return get_xor(a)

def get_spiltter(xor):
    splitter = 1
    while (xor & 1) == 0:
        splitter = splitter << 1
        xor = xor >> 1
    return splitter

def find_two_lonely(a):
    splitter = get_spiltter(get_xor(a))
    x =  y = 0
    for i in a:
        if (i & splitter) == 0:
            x = x ^ i
        else:
            y = y ^ i
    return x,y

if __name__ == '__main__':
    print("test case data prepartion")
    import random as r
    a = []
    for i in range(5):
        n = r.randint(1,100)
        a.append(n)
        a.append(n)
    lonely = r.randint(1,100)
    a.append(lonely)
    r.shuffle(a)
    print("Test case 1 - single lonely number")
    assert lonely == find_lonely(a)

    print("Test case 2 - two lonely number")
    other_lonely = lonely
    while other_lonely == lonely:
        other_lonely = r.randint(1,100)
    a.append(other_lonely)
    x,y = find_two_lonely(a)
    if x == lonely:
        assert y == other_lonely
    else:
        assert y == lonely
        assert x == other_lonely
    print("all test cases passed!!")
