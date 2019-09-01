import string
import math
encode_map = string.ascii_uppercase + \
    string.ascii_lowercase + string.digits + '+/'


def process(n, x):
    d = 18
    for i in range(4):
        x.append(encode_map[(n & (63 << d)) >> d])
        d -= 6


def encode(s):
    n = 0
    res = []
    for i in range(len(s)):
        """ multiple of 3 characters """
        x = ord(s[i])
        n = (n << 8) | x
        if (i+1) % 3 == 0:
            process(n, res)
            n = 0
    """ remaining characters """
    if len(s) % 3 != 0:
        l = 8 * (len(s) % 3)
        while l >= 6:
            l -= 6
            res.append(encode_map[(n & (63 << l)) >> l])
        res.append(encode_map[(n & (int(math.pow(2, l)) - 1)) << (6-l)])
        """ add = pads at the end """
        for i in range(4-len(res) % 4):
            res.append('=')
    return "".join(res)

def decoder(s):
    pass


def test_encoding(s):
    print("\ntesting with string ", s)
    import base64 as b
    encoded = encode(s)
    assert encoded == b.b64encode(str.encode(s)).decode('utf-8')
    assert len(encoded) % 4 == 0
    if len(s) % 3 == 1:
        encoded.index('==') == len(encoded)-2
    if len(s) % 3 == 2:
        encoded.index('=') == len(encoded)-1


def genrate_random_string(lengh=10):
    import random
    return ''.join((random.choice(string.printable) for i in range(lengh)))


if __name__ == '__main__':
    test_encoding("abcdef")
    test_encoding("abcdefg")
    test_encoding("abcdefgh")
    test_encoding(genrate_random_string())
    test_encoding(genrate_random_string(11))
    test_encoding(genrate_random_string(12))
    print("all test case passed")
