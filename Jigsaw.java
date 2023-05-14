package misc;
	
import java.util.ArrayList;
	
   public class Jigsaw {
	
	   
	   /*
	    *Jigsaw: Implement an NxN jigsaw puzzle. Design the data structures and explain an algorithm to
         solve the puzzle. You can assume that you have a fitsWith method which, when passed two
         puzzle edges, returns true if the two edges belong together. 
	    */
		
	public static class piece{
		
		//each edge points to the edge that should come after them at an each direction
		private piece left;
		private piece right;
		private piece up;
		private piece down;
		private boolean in_board = false;
		//the id can be the index that this piece is located in the array
		private final int _id;
		
		public piece(piece l,piece r,piece u,piece d,int id){
			left = l;
			right = r;
			up = u;
			down = d;
			_id = id;
		}
		
		public piece get_left() {
			return left;
		}
		public piece get_right() {
			return right;
		}
		public piece get_up() {
			return up;
		}
		public piece get_down() {
			return down;
		}
		
	}
	
	private  final int _n;
	private static ArrayList<piece> all_pieces = new ArrayList<>();
	//this board is used to simulating putting pieces of the jigsaw
	private static ArrayList<ArrayList<piece>> spots = new ArrayList<>();
	
    public Jigsaw(int n,ArrayList<piece> all) {
    	//to create a Jigsaw you would have to instanciate all pieces and add them to a list
    	_n = n;
    	for(int i = 0;i < n;i++) {
    		for(int j = 0;j < n;j++) {
    			spots.get(i).add(null);//start all the board with null
    		}
    	}
    	all_pieces.addAll(all);
    }
    
    
    public boolean fits_with(piece edge1,piece edge2){
    	//I will say that if edge2 fit in edge1 at any side -->true
    	if(edge1.get_left() == edge2.get_right()){
    		return true;
    	}
    	if(edge1.get_right() == edge2.get_left()){
    		return true;
    	}
    	if(edge1.get_up() == edge2.get_down()){
    		return true;
    	}
    	if(edge1.get_down() == edge2.get_up()){
    		return true;
    	}
    	return false;
    }
    
    //Solving the puzzle
    /*
     * Since i only have access to method that check edges,i can start with the edges and fill their lines and columns,
     * after this other pieces would be consider edges and so on,it will surround the central position
     * If a piece points to other 4 pieces its not a edge,but if two neighboring spots of that piece are filled,then 
     * it can be considered an edge and the method fits_with works 
     */
    
    public boolean spot_filled(int i,int j) {
    	if(spots.get(i).get(j) != null){
    		return true;
    	}
    	return false;
    }
    
    public piece find_start(){
    	
    	boolean found = false;
    	piece strt = null;
    	
    	for(int i = 0;i < all_pieces.size();i++){
    		piece x = all_pieces.get(i);
    		if(x.in_board == false) {
    			if(x.get_up() == null && x.get_left() == null) {
    				strt = x;
    				found = true;
    			}
    		}
    	}
    	if(!found) {
    		for(int i = 0;i < all_pieces.size();i++) {
    			piece x = all_pieces.get(i);
    			if(x.in_board == false){
    				if(x.get_up().in_board && x.get_left().in_board){
    					strt = x;
    				}
    			}
    		}
    	}
    	return strt;
    	
    }
    
	public void solve(){
	
		piece strt = this.find_start();
		
		
		int c = 0;
		while(strt != null){
			
			//upper row:
			spots.get(c).add(strt);
			piece x = strt;
			int j = 0;
			for(int i = c+1;i < _n-c;i++){
				if(x.get_right() != null && !x.get_right().in_board) {
					spots.get(c).remove(i);
					spots.get(c).add(i,x.get_right());
					x.get_right().in_board = true;
					x = x.get_right();
					j = i;
				}
			}
			
			//right column:
			for(int i = c+1;i < _n-c;i++){
				if(x.get_down() != null && !x.get_down().in_board) {
					spots.get(i).remove(j);
					spots.get(i).add(j,x.get_down());
					x.get_down().in_board = true;
					x = x.get_down();
				}
			}
			
			//lower row:
			for(int i = _n-c-1;i > c;i--) {
				if(x.get_left() != null && !x.get_left().in_board) {
					spots.get(_n-c).remove(i);
					spots.get(_n-c).add(i,x.get_left());
					x.get_left().in_board = true;
					x = x.get_left();
				}
			}
			
			//left column
			for(int i = _n-c-1;i > c;i--) {
				if(x.get_up() != null && !x.get_up().in_board){
					spots.get(c).remove(i);
					spots.get(c).add(i, x.get_up());
				    x.get_up().in_board = true;
				    x = x.get_up();
				}
			}
			c++;
			strt = this.find_start();
			
		}
		
	}

}
