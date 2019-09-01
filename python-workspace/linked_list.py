
class node:
    data = None
    next_node = None
  
    def __init__(self, data):
      self.data = data
      self.next_node = None
  
class linked_list:
    head = None
    def __init__(self):
      self.head = None
  
    def insert(self, data):
      self.head = self.insert_node(self.head, data)
    
    def insert_node(self, n, data):
      if n is None:
        return node(data)
      n.next_node = self.insert_node(n.next_node, data)    
      return n
    
    def search(self, data):
      return self.search_node(self.head, data)
    def search_node(self, n, data):
      if n is None:
        return False
      if n.data == data:
        return True
      return self.search_node(n.next_node, data)
    
    def print(self):
      temp = self.head
      x="Linked List = "
      while temp is not None:
        x = x+str(temp.data)+"->"
        temp = temp.next_node  
      x = x + "null"
      print(x)
    
    def reverse_ite(self):
      if self.head is None:
        return
      prev = None
      curr = self.head
      
      while curr is not None:
        temp = curr.next_node
        curr.next_node = prev
        prev = curr
        curr = temp
        
      self.head = prev
  
    def reverse_rec(self):
      temp = self.reverse(self.head)
      temp.next_node = None
  
    def reverse(self, node):
      print(node.data)
      if node.next_node is None:
        self.head = node
      else:
        prev = self.reverse(node.next_node)
        prev.next_node = node
      return node
  
    def odd_even(self):
      odd = True
      temp = self.head
      odd_list = linked_list()
      even_list = linked_list()
      while temp is not None:
        if odd is True:
          odd_list.insert(temp.data)
        else:
          even_list.insert(temp.data)
        temp = temp.next_node
        odd = not odd
      return odd_list, even_list
      
def test_linkedlist():
    print("testing linked list")
    l = linked_list()
    #l.print()
    l.insert(1)
    l.reverse_ite()
    #l.print()
    l.reverse_ite()
    #l.print()
    l.insert(2)
    l.insert(3)
    l.insert(4)
    l.insert(5)
    #l.print()
    #print("start")
    #print(l.search(2))
    #print(l.search(5))
    #print(l.search(-1))
    #print(l.head.data)
    #print("end")
    l.reverse_rec()
    l.print()
    o,e = l.odd_even()
    o.print()
    e.print()
    assert l.search(3) == True
    assert l.search(9) == False
if __name__ == "__main__":
    print("main program started")
    test_linkedlist()