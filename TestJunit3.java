import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TestJunit3{
	
	Playlist testPlaylist;
	String result = "";	
	@Test
    public void testSearch() throws Exception{

      System.out.println("Testing search for song");

   	  testPlaylist = App.deserialize("tp");
   	  
   	  result = "Song's name: E, Singer: F, Duration: 300s";
      assertEquals(result, testPlaylist.search("E").toString());

   }
}