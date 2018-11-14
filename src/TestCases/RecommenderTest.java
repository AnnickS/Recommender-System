package TestCases;
import RecommenderProgram.Recommender;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class RecommenderTest {
	static Recommender recommender;
	
	
	@BeforeAll
	public static void setUp() {
		recommender = new Recommender();
	}

	@Test
	void testCommonFriends() {
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		
		recommender.commonFriends(2, 275);
		
		String expectedOutput = "Common friends of 2 and 275 are: 831\r\n\r\n";
		
		assertEquals(expectedOutput, outContent.toString()); 
	}
	
	@Test
	void testListFriends() {
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		
		recommender.listFriends(29);
		
		String expectedOutput = "User 29's friends: \r\n946\r\n\r\n";
		
		assertEquals(expectedOutput, outContent.toString());
	}
	
	@Test
	void testListArtists() {
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		
		recommender.listArtists(29);
		
		String expectedOutput = "User 29's artists:\r\nNew Found Glory\r\n" + 
				"Fresno\r\nSlipknot\r\nBoys Like Girls\r\nBruno Mars\r\n" + 
				"Gloria\r\nForever the Sickest Kids\r\nTeen Hearts\r\n" + 
				"The Maine\r\nNicki Minaj\r\nAnarbor\r\nBreathe Carolina\r\n" + 
				"Kerli\r\nRunner Runner\r\nJessie J\r\nNickelback\r\nBon Jovi\r\n" + 
				"Vanessa Hudgens\r\nAC/DC\r\nThe Pretty Reckless\r\n" + 
				"All Time Low\r\nSelena Gomez & the Scene\r\nGlee Cast\r\n" + 
				"Nicole Scherzinger\r\nThe Used\r\nNo Doubt\r\nTaio Cruz\r\n" + 
				"Pixie Lott\r\nBullet for My Valentine\r\nParamore\r\n" + 
				"Simple Plan\r\n3 Doors Down\r\nKe$ha\r\n3OH!3\r\n" + 
				"Miley Cyrus\r\nBackstreet Boys\r\nEvanescence\r\n" + 
				"Avril Lavigne\r\nDavid Guetta\r\nHilary Duff\r\nKeri Hilson\r\n" + 
				"P!nk\r\nKaty Perry\r\nChristina Aguilera\r\nBritney Spears\r\n" + 
				"Rihanna\r\nMy Chemical Romance\r\nEnrique Iglesias\r\n" + 
				"Lady Gaga\r\nKylie Minogue\r\n\r\n";
		
		assertEquals(expectedOutput, outContent.toString());
	}
	
	@Test
	void testListArtistsII() {
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		
		recommender.listArtists(2, 4);
		
		String expectedOutput = "User 2 and user 4 have these artists in common:\r\n" + 
				"George Michael\r\nDepeche Mode\r\nMoby\r\nColdplay\r\n" + 
				"Röyksopp\r\nAir\r\nDuran Duran\r\n\r\n";
		
		assertEquals(expectedOutput, outContent.toString());
	}
	
	@Test
	void testListTop10() {
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		
		recommender.listTop10();
		
		String expectedOutput = "The top artists are: \r\n" + 
				"Britney Spears\r\nDepeche Mode\r\nLady Gaga\r\n" + 
				"Christina Aguilera\r\nParamore\r\nMadonna\r\nRihanna\r\n" + 
				"Shakira\r\nThe Beatles\r\nKaty Perry\r\n\r\n";
		
		assertEquals(expectedOutput, outContent.toString());
	}
	
	@Test
	void testRecommend10() {
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		
		recommender.recommend10(29);
		
		String expectedOutput = "The top shared artists of user 29 and their friends are: \r\n" + 
				"All Time Low\r\nFresno\r\nMetro Station\r\n" + 
				"DOYOULIKE?\r\nForever the Sickest Kids\r\nBoys Like Girls\r\n" + 
				"You Me At Six\r\nA Rocket to the Moon\r\nGreen Day\r\n" + 
				"Beeshop\r\n\r\n";
		
		assertEquals(expectedOutput, outContent.toString());
	}
	
	@Test
	void testThrows() {
		//Tests List Friends for IndexOutOfBounds
		Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
			recommender.listFriends(1); });
		Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
			recommender.listFriends(2101); });
		
		//Tests List Artist for IndexOutOfBounds
		Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
			recommender.listArtists(1, 200); });
		Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
			recommender.listArtists(2101, 200); });
		Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
			recommender.listArtists(2, 1); });
		Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
			recommender.listArtists(2, 21001); });
		
		//Tests Common Friends for IndexOutOfBounds
		Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
			recommender.commonFriends(1,  200); });
		Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
			recommender.commonFriends(2101,  200); });
		Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
			recommender.commonFriends(2,  1); });
		Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
			recommender.commonFriends(2,  2101); });
		
		//Tests List Artists for IndexOutOfBounds
		Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
			recommender.listArtists(1,  200); });
		Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
			recommender.listArtists(2101,  200); });
		Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
			recommender.listArtists(2,  1); });
		Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
			recommender.listArtists(2,  2101); });
		
		//Tests Recommend10 for IndexOutOfBounds
		Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
			recommender.recommend10(1); });
		Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
			recommender.recommend10(2101); });
	}

}
