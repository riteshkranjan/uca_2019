"""
1. create functions
2. restrict imports if possible
3. write main function
4. run time improvements : sqrt and even no check
5. range function
6. None, True, False, and/or keywords
"""
def is_even(n):
    return n%2==0

def is_prime(n):
    import math
    if n < 2:
        return False
    if n == 2:
        return True
    if is_even(n):
        return False
    for i in range(3, int(math.sqrt(n))+1, 2):
        if n%i == 0:
            return False
    return True

if __name__ == "__main__":
    input = input().split()
    print(input)
    max = None
    for i in input:
        if is_prime(int(i)) and (max is None or i>max):
            max = i
    print(max)    
    