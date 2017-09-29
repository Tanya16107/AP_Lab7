import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TestJunit1{
	
	Playlist testPlaylist;
	String result = "";	
   
   @Test	
   public void testAddSong() throws Exception{
   	System.out.println("Testing add song");

	   testPlaylist = App.deserialize("tp");

   	Song s = new Song("G", "H", "400");
   	testPlaylist.addSong(s);

   	App.serialize(testPlaylist);

   	result="1. Song's name: A, Singer: B, Duration: 100s\n2. Song's name: C, Singer: D, Duration: 200s\n3. Song's name: E, Singer: F, Duration: 300s\n4. Song's name: G, Singer: H, Duration: 400s\n";
    assertEquals(result, testPlaylist.show());
   }

}