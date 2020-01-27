def fib(sixth,third,n):
    if third > n:
	    return 0;
    return third + fib(third, 4*third + sixth, n);

if __name__ == "__main__":
    n = int(input("enter outbound number : "))
    print("sum of all even numbers lesser than {} is {}".format(n,fib(0,2,n)));