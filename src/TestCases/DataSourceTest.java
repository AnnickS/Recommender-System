package TestCases;
import RecommenderProgram.DataSource;
import RecommenderProgram.ArtistWeight;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import edu.princeton.cs.algs4.Bag;

class DataSourceTest {
	static DataSource source;
	
	@BeforeAll
	public static void setUp() {
		source = new DataSource();
	}
	
	@Test //Tests to see if the Friend Graph was stored correctly
	void testFriendGraph() {
		Iterable<Integer> a = source.graphFriend.adj(29);
		for(int i : a) {
			assert(i == 946);
		}
		
		Iterable<Integer> b = source.graphFriend.adj(123);
		for(int j : b) {
			assert(j == 655);
		}
	}
	
	@Test //Tests to see if the Artist Map was stored correctly
	void testArtistMap() {
		String name1 = (String)source.artistKey.get(13);
		
		assert(name1.equals("Hocico"));
		
		String name2 = (String)source.artistKey.get(15008);
		
		assert(name2.equals("Mylon LeFevre"));
		
		String name3 = (String)source.artistKey.get(18999);
		
		assert(name3 == null);
	}
	
	@Test //Tests to see if the User Artists Map was stored correctly
	void testUserArtists() {
		Bag<ArtistWeight> b = (Bag)source.userArtists.get(1993);
		
		for(ArtistWeight a : b) {
			if(a.a == 3797) {
				assert(a.w == 14);
			}
		}
		
		Bag<ArtistWeight> c = (Bag)source.userArtists.get(2100);
		
		for(ArtistWeight a : c) {
			if(a.a == 18726) {
				assert(a.w == 337);
			}
		}
	}
}
