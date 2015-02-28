public class PercolationStats {
	private int i;
	private int j;
	private double[] results;
	private int times;

	public PercolationStats(int N, int T) {
		//TODO N>=1 T>=1 N=1?
		if(N <= 0 || T <= 0) {
			throw new IllegalArgumentException();
		}
		Percolation p;
		times = getT(T);
		results = new double[T];
		for(int k=0;k<T;k++) {
			p = new Percolation(N);
			int count = 0;
			while(false == p.percolates()) {
//				System.out.println("round: " + k);
				i = StdRandom.uniform(N) + 1;
//				System.out.println(i);
				j = StdRandom.uniform(N) + 1;
//				System.out.println(j);
				if(!(p.isOpen(i, j))) {
					count++;
				}
				p.open(i, j);
//				System.out.println("count:" + count);
			}
			results[k] = (double)count / (N*N);
//			System.out.println("rsult[k]: " + results[k]);
		}
	}
	
	public double mean() {
//		System.out.println("l:" + (results.length - 1));
		return StdStats.mean(results, 0, results.length - 1);
	}
	   
	public double stddev() {
		return StdStats.stddev(results, 0, results.length - 1);
	}
	  
	public double confidenceLo() {
		double mean = mean();
		double stddev = stddev();
		return mean - 1.96 * stddev / Math.sqrt((double)times);
	}
	   
	public double confidenceHi(){
		double mean = mean();
		double stddev = stddev();
		return mean + 1.96 * stddev / Math.sqrt((double)times);
	}
	
	private int getT(int T) {
		return T;
	}
	
	public static void main(String[] args) {
		PercolationStats ps = new PercolationStats(Integer.parseInt(args[0]),
				Integer.parseInt(args[1]));
		StdOut.println("mean = " + ps.mean());
		StdOut.println("stddev = " + ps.stddev());
		StdOut.println("95% confidence interval =  " + ps.confidenceLo() + ", " + ps.confidenceHi());
	}

}
