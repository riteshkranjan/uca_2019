#include <stdio.h>
#include <stdlib.h>
struct node{
   int key;
   char data;
   struct node *left;
   struct node *right;
} *root = NULL;

struct node * insert_key(struct node *n, int key, char data){
    if(n==NULL){
        n = (struct node *)malloc(1*sizeof(struct node));
        n->key = key;
        n->data = data;
        n->left = n->right = NULL;
        return n;
    }
    if(n->key == key){
        n->data = data;
    }else if(n->key > key){
        n->left = insert_key(n->left, key, data);
    }else{
        n->right = insert_key(n->right, key, data);
    }
    return n;
}

void insert(int key, char data){
    root = insert_key(root, key, data);
}

struct node * search_key(struct node *n, int key){
    if(n==NULL) 
       return NULL;
    if(n->key == key)
       return n;
    if(n->key > key)
       return  search_key(n->left, key);
    return search_key(n->right, key);
}

char * search(int key){
    struct node *result = search_key(root, key);
    if(result == NULL) 
        return "not found";
	
	char *response = (char *)malloc(1);
	response[0] = result->data;
    return response;
}

struct node * get_min_node(struct node *n){
    if(n->left == NULL) return n;
    return get_min_node(n->left);
}

char * get_min(){
    if(root==NULL) 
        return "empty tree";
	char *response = (char *)malloc(1);
	response[0] = get_min_node(root)->data;
    return response;
}

struct node * del_min_node(struct node *n){
    if(n->left == NULL) 
        return n->right;
    n->left = del_min_node(n->left);
	return n;
}

void del_min(){
    if(root==NULL) 
        return;
    root =  del_min_node(root);
}

struct node * get_max_node(struct node *n){
    if(n->right == NULL) return n;
    return get_max_node(n->right);
}

char * get_max(){
    if(root==NULL) 
        return "empty tree";
    char *response = (char *)malloc(1);
    response[0] = get_max_node(root)->data;
    return response;
}

struct node * del_max_node(struct node *n){
    if(n->right == NULL) 
        return n->left;
    n->right = del_max_node(n->right);
	return n;
}

void del_max(){
    if(root==NULL) 
        return;
    root =  del_max_node(root);
}

int main(){
    insert(5,'E');
    insert(3,'C');
    insert(4,'Z');  
    insert(7,'G'); 
    insert(6,'F');
    insert(8,'H');
    printf("5th character in english alphabets is %s\n", search(5));
    printf("8th character in english alphabets is %s\n", search(8));
    printf("1st character in english alphabets is %s\n", search(1));
    printf("4th character in english alphabets is %s\n", search(4));
    //incorrect!! 4th character is D not Z
    insert(4, 'D'); //update 4th character value to D 
    printf("4th character in english alphabets is %s\n", search(4));

    
    printf("lowest alphabets in my dictionary is %s\n", get_min());
    del_min();
    printf("lowest alphabets in my dictionary is %s\n", get_min());
	
	printf("highest alphabets in my dictionary is %s\n", get_max());
    del_max();
	
    printf("highest alphabets in my dictionary is %s\n", get_max());
}
