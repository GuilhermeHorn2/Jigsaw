package misc;

import java.util.ArrayList;

public class main_misc2 {
	
	
	public static class node{
		int data;
		boolean visited = false;
		
		public node(int d){
			data = d;
		}
		
		public void set_visit(){
			visited = true;
		}
		
		public String toString(){
			return ""+data;
		}
	}
	
	public static void main(String[] args) {
	//(a+b)%k = ((a % k)+(b % k)) % k    || ||
	
	int n = 3;
	ArrayList<ArrayList<node>> l = new ArrayList<>();
	for(int i = 0;i < n;i++){
		l.add(new ArrayList<>());
		for(int j = 0;j < n;j++){
			node x = new node(j);
			l.get(i).add(x);
		}
	}
	
	System.out.println(l);
	circular_trav(l,n);
		
		
	
	
	
	}
	
	private static void circular_trav(ArrayList<ArrayList<node>> l,int n){
		
		int c = 0;
		
		node strt = l.get(0).get(0);
		
		while(true){
			
			if(strt.visited) {
				break;
			}
			
			System.out.println(strt);
			strt.set_visit();
			int j = 0;
            for(int i = c+1;i < n-c;i++){
            	node x = l.get(c).get(i);
            	if(x.visited) {
            		continue;
            	}
            	System.out.println(x);
            	x.set_visit();
            	j = i;
		    }
            
            for(int i = c+1;i < n-c;i++) {
            	node x = l.get(i).get(j);
            	if(x.visited) {
            		continue;
            	}
            	System.out.println(x);
            	x.set_visit();
            }
            
            for(int i = n-c-1;i > c;i--) {
            	node x = l.get(j).get(i);
            	if(x.visited) {
            		continue;
            	}
            	System.out.println(x);
            	x.set_visit();
            }
            
            for(int i = n-c-1;i > c;i--) {
            	node x = l.get(i).get(c);
            	if(x.visited) {
            		continue;
            	}
            	System.out.println(x);
            	x.set_visit();
            }
            c++;
            strt = l.get(c).get(c);
            
            }
            
		
		
	}
	
	
}
