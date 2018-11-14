package RecommenderProgram;

public class ArtistWeight implements Comparable{
	public int a;
	public int w;
	
	public ArtistWeight(int a, int w) {
		this.a = a;
		this.w = w;
	}
	
	@Override
	public int compareTo(Object o) {
		ArtistWeight other = (ArtistWeight)o;
		if(w < other.w) return -1;
		if(w > other.w) return 1;
		else return 0;
	}
}
