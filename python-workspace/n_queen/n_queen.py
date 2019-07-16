size = 0
def in_matrix(i, n):
    return i>=0 and i<size and n>=0 and n<size
        
	
def is_valid_move(chess, i, n):
    #print(\"in method is_valid for i = {} and n = {} for chess {}\".format(i,n,chess))
    if i == 0:
        return True
    for c in range(i-1,-1,-1):
        #rook  
        if in_matrix(c, n) and chess[c] == n:
            return False
        #bishop in upper diagonal
        if in_matrix(c, n-i+c) and chess[c] == n-i+c:
            return False
		#bishop in lower diagonal
        if in_matrix(c, n+i-c) and chess[c] == n+i-c:
            return False
    return True
	
def solve(chess, i):
    #print(\"in method solve for i = {} for chess {}\".format(i,chess))
    if i == size:
        return True
    for n in range(0,size,1):
        chess[i] = -1
        if is_valid_move(chess,i,n):        
            chess[i] = n
            if solve(chess, i+1):
                return True
    chess[i] = -1        
    return False

def pretty_print(chess):
    import pandas as pd
    print(chess)
    a = [['.' for y in range(0,size,1)] for x in range(0,size,1)]
    for i in range(0,size,1):
        a[chess[i]][i] = "Q"
    df = pd.DataFrame(a)
    print(df)

if __name__ == "__main__":
    import sys
    size = int(sys.argv[1]) if len(sys.argv) > 1 else int(input("argument missing - enter board size:"))
    print("Running with board size {}".format(size))
    chess = [-1]*size
    if solve(chess,0):
        pretty_print(chess)
        print("Note: this is one of the solutions")
    else:
        print("no solution")
