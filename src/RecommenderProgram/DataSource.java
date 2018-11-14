package RecommenderProgram;
import java.util.HashMap;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.Graph;

public class DataSource {
	//Stores users and their relationships
	public Graph graphFriend;
	//Maps artists to a numeric key
	public HashMap artistKey;
	//Maps users to the artists they listen to
	public HashMap userArtists;
	//Used to measure popularity
	public HashMap artistWeights;
	
	public DataSource() {
		In in = new In("artists.dat");
		createArtistHash(in);
		in.close();
		in = new In("user_artists.dat");
		createMapArtist(in);
		in.close();
		in = new In("user_friends.dat");
		createGraphFriends(in);
		in.close();
	}
	
	//Arranges the user/artist relationship
	//Also creates the general artist weights
	//as all information on them are already
	//accessed in this method
	private void createMapArtist(In in) {
		userArtists = new HashMap(2100);
		artistWeights = new HashMap(17632);
		Bag<ArtistWeight> artists = new Bag();
		int userID;
		int artistID;
		int weight;
		int prevWeight = 0;
		int previous = -1;
		
		in.readLine();
		
		while(!in.isEmpty()) {
			userID = in.readInt();
			artistID = in.readInt();
			weight = in.readInt();
			
			//Adds to the bag if it's the same user as the previously read line
			if(previous == -1 || userID == previous) {
				artists.add(new ArtistWeight(artistID, weight));
				if(artistWeights.containsKey(artistID)) {
					prevWeight = (int)artistWeights.get(artistID);
					artistWeights.put(artistID, prevWeight+weight);
				}
				else {
					artistWeights.put(artistID, weight);
				}
			}
			else { //Adds if it's a different user than the previous line
				userArtists.put(previous, artists);
				artists = new Bag();
				artists.add(new ArtistWeight(artistID, weight));
				if(artistWeights.containsKey(artistID)) {
					prevWeight = (int)artistWeights.get(artistID);
					artistWeights.put(artistID, prevWeight+weight);
				}
				else {
					artistWeights.put(artistID, weight);
				}
			}
			previous = userID;
		}
		
		userArtists.put(previous, artists);
	}
	
	//Creates a graph of friendships
	private void createGraphFriends(In in) {
		graphFriend = new Graph(2101);
		in.readLine();
		
		while(!in.isEmpty()) {
			int user1 = in.readInt();
			int user2 = in.readInt();
			
			if(user1 < user2) {
				graphFriend.addEdge(user1, user2);
			}
		}
	}
	
	//Initializes artist map
	private void createArtistHash(In in) {
		artistKey = new HashMap(17632);
		in.readLine();
		
		while(!in.isEmpty()) {
			int id = in.readInt();
			String line = in.readLine();
			String[] parts = line.split("\t");
			String artist = parts[1];
			artistKey.put(id, artist);
		}
	}
}
