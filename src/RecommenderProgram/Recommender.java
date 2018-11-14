package RecommenderProgram;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeMap;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.IndexMaxPQ;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.SET;

public class Recommender {
	DataSource source;
	
	//Initializes all data
	public Recommender() {
		source = new DataSource();
	}
	
	//Lists all friends of user
	public void listFriends(int user) {
		if(user <= 1 || user > 2100) {
			throw new IndexOutOfBoundsException();
		}
		
		Iterable<Integer> list = source.graphFriend.adj(user);
		System.out.println("User " + user + "'s friends: ");
		for(int i : list) {
			System.out.println(i);
		}
		System.out.println();
	}
	
	public void listArtists(int user) {
		if(user <= 1 || user > 2100) {
			throw new IndexOutOfBoundsException();
		}
		
		Bag<ArtistWeight> artists = (Bag)source.userArtists.get(user);
		
		if(!artists.isEmpty()) {
			System.out.println("User " + user + "'s artists:");
			
			for(ArtistWeight w : artists) {
				System.out.println(source.artistKey.get(w.a));
			}
		}
		else {
			System.out.println("This user doesn''t listen to any artists.");
		}
		
		System.out.println();
	}
	
	//Lists common friends between two users
	public void commonFriends(int user1, int user2) {
		if(user1 <= 1 || user1 > 2100 || user2 <= 1 || user2 > 2100) {
			throw new IndexOutOfBoundsException();
		}
		Iterable<Integer> list1 = source.graphFriend.adj(user1);
		Iterable<Integer> list2 = source.graphFriend.adj(user2);
		SET compare = new SET();
		Queue<Integer> matches = new Queue();
		
		for(int i : list1) {
			compare.add(i);
		}
		
		for(int i : list2) {
			if(compare.contains(i)) {
				matches.enqueue(i);
			}
		}
		
		if(!matches.isEmpty()) {
			System.out.print("Common friends of " + user1 + " and " + user2 + " are: ");
			for(int i : matches) {
				System.out.println(i);
			}
			System.out.println();
		} else {
			System.out.println("There are no common friends between user "
		+ user1 + " and " + user2 + ".");
			System.out.println();
		}
	}
	
	//Lists the common artists listed to by user 1 and user2
	public void listArtists(int user1, int user2) {
		//If the users are the wrong indecies
		if(user1 <= 1 || user1 > 2100 || user2 <= 1 || user2 > 2100) {
			throw new IndexOutOfBoundsException();
		}
		Bag<ArtistWeight> u1Artists = (Bag)source.userArtists.get(user1);
		Bag<ArtistWeight> u2Artists = (Bag)source.userArtists.get(user2);
		ArrayList<Integer> commonArtists = new ArrayList();
		
		//Adds common artists to the list
		for(ArtistWeight i : u1Artists) {
			for(ArtistWeight j : u2Artists) {
				if(i.a == j.a) {
					commonArtists.add(i.a);
				}
			}
		}
		
		//If there are common artists
		if(!commonArtists.isEmpty()) {
			System.out.println("User " + user1 + " and user " + user2 + " have these artists in common:");
			
			for(int i : commonArtists) {
				System.out.println(source.artistKey.get(i));
			}
			System.out.println();
		} else {
			System.out.println("User " + user1 + " and user " + user2 + " have no artists in common.");
			System.out.println();
		}
	}

	//Lists the 10 most listened to artists
	public void listTop10() {
		Iterator<HashMap.Entry<Integer, Integer>> itArtist = source.artistWeights.entrySet().iterator();
		IndexMaxPQ topArtists = new IndexMaxPQ(18745);
		
		while(itArtist.hasNext()) {
			HashMap.Entry<Integer, Integer> pair = itArtist.next();
			topArtists.insert(pair.getKey(), pair.getValue());
		}
		
		System.out.println("The top artists are: ");
		
		int key;
		int value;
		
		for(int i = 0; i < 10; i++) {
			key = (int)topArtists.maxKey();
			value = topArtists.delMax();
			System.out.println(source.artistKey.get(value));
		}
		System.out.println();
	}
	
	//Lists the top ten artists of user and their friends
	public void recommend10(int user) {
		if(user <= 1 || user > 2100) {
			throw new IndexOutOfBoundsException();
		}
		
		Iterable<Integer> list = source.graphFriend.adj(user);
		HashMap<Integer, Integer> listened = new HashMap<Integer, Integer>();
		TreeMap<Integer, Integer> sortListened = new TreeMap<Integer, Integer>();
		int weight;
		Bag<ArtistWeight> artists;
		
		//Search key by artist and stores the cumulative weight
		for(int b : list) {
			artists = (Bag)source.userArtists.get(b);
			for(ArtistWeight w : artists) {
				if(listened.containsKey(w.a)) {
					weight = listened.get(w.a) + w.w;
					listened.put(w.a, weight);
				}
				else {
					listened.put(w.a, w.w);
				}
			}
		}
		
		artists = (Bag)source.userArtists.get(user);
		for(ArtistWeight w : artists) {
			if(listened.containsKey(w.a)) {
				weight = listened.get(w.a) + w.w;
				listened.put(w.a, weight);
			}
			else {
				listened.put(w.a, w.w);
			}
		}
		
		for(int a : listened.keySet()) {
			sortListened.put(listened.get(a), a);
		}
		
		System.out.println("The top shared artists of user " + user + " and their friends are: ");
		
		for(int i = 0; i < 10; i++) {
			if(!sortListened.isEmpty()) {
				int key = sortListened.lastKey();
				int value = sortListened.get(key);
				sortListened.remove(key);
				System.out.println(source.artistKey.get(value));
			}
		}
		System.out.println();
	}
}
