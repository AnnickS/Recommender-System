package RecommenderProgram;

//Leaves room for expansion for a UI
public class RecommenderProgram {

	//To run the program
	public static void main(String[] args) {
		Recommender recommender = new Recommender();
		recommender.listFriends(4);
		recommender.commonFriends(2, 275);
		recommender.listFriends(29);
		recommender.listArtists(29);
		recommender.listArtists(2, 4);
		recommender.listTop10();
		recommender.recommend10(29);
	}
}
