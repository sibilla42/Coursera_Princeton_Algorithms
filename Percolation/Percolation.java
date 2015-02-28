
public class Percolation {
	private int[][] grid;
	private WeightedQuickUnionUF wqu;
	private int n;
	
	public Percolation(int N) {
		if(N <= 0) {
			throw new IllegalArgumentException();
		}
		grid = new int[N+1][N+1];
		for(int i=1;i<N+1; i++) {
			for(int j=1;j<N+1;j++) {
				grid[i][j] = 0;
			}
		}
		wqu = new WeightedQuickUnionUF(N*N+2);
		n = getN(N);
	}
	
	public void open(int i, int j) {
		checkCornerCase(i,j);
		grid[i][j] = 1;
		if(n == i) {
			wqu.union(n*(i-1)+j-1, n*n+1);
		}
		if(1 == i) {
			wqu.union(n*(i-1)+j-1, n*n);
		}
		if(i+1<=n && isOpen(i+1, j) == true) {
			wqu.union(n*i+j-1, n*(i-1)+j-1);
		}
		if(j+1<=n && isOpen(i, j+1) == true) {
			wqu.union(n*(i-1)+j-1, n*(i-1)+j);
		}
		if(j-1>0 && isOpen(i, j-1) == true) {
			wqu.union(n*(i-1)+j-1, n*(i-1)+j-2);
		}
		if(i-1>0 && isOpen(i-1, j) == true) {
			wqu.union(n*(i-2)+j-1, n*(i-1)+j-1);
		}
	}
	
	public boolean isOpen(int i, int j) {
		checkCornerCase(i, j);
		if(grid[i][j] == 1) {
			return true;
		}
		return false;
	}
	
	public boolean isFull(int i, int j) {
		checkCornerCase(i,j);
		if(wqu.connected(n*(i-1)+j-1, n*n) == false) {
			return false;
		}
		return true;
	}
	
	public boolean percolates() {
		if(wqu.connected(n*n+1, n*n) == false) {
			return false;
		}
		return true;
	}
	
	private int getN(int n) {
		return n;
	}
	
	private void checkCornerCase(int i,int j) {
		if(!(i >= 1 && i <= n && j >= 1 && j <= n)) {
			
			throw new IndexOutOfBoundsException();
		}
	}
	
//	public static void main(String[] args) {
//		Percolation p = new Percolation(4);
//		p.open(4, 1);
//
//		
//		System.out.println(p.isFull(2, 1));
//
//	}

}
