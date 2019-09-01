def swap(a, i, j):
    t = a[i]
    a[i] = a[j]
    a[j] = t


def search(a, L, R, key):
    if key > R:
        "when k is less than n"
        return None
    if R <= L:
        "no possible"
        return None
    last = L
    for i in range(L+1, R+1):
        if a[i] < a[L]:
            swap(a, i, ++last)
    swap(a, L, last)
    if last == key:
        return a[last]
    if key < last:
        return search(a, L, last-1, key)
    return search(a, last+1, R, key)


if __name__ == "__main__":
    a = [5, 8, 2, 9, 1, 7, 2, 0, 12, 10]
    b = a.copy()
    a.sort()
    assert search(b, 0, len(b)-1, 5) == a[5]
    assert search(b, 0, len(b)-1, 8) == a[8]
    assert search(b, 0, len(b)-1, 2) == a[2]

    assert search(b, 0, len(b)-1, 100) == None
    print("test case passed!!")
