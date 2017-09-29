import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TestJunit2{
   
   Playlist testPlaylist;
   String result = "";  

   @Test
   public void testDelSong() throws Exception {
      System.out.println("Testing delete song");
      
      testPlaylist = App.deserialize("tp");



      testPlaylist.delSong("A");

      App.serialize(testPlaylist);

      result="1. Song's name: C, Singer: D, Duration: 200s\n2. Song's name: E, Singer: F, Duration: 300s\n3. Song's name: G, Singer: H, Duration: 400s\n";
    assertEquals(result, testPlaylist.show());

   }
}
   