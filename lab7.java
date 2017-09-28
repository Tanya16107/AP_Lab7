//Tanya Gupta 
//2016107
import java.util.*;
import java.io.*;


/*Reader class*/
class Reader
{
    BufferedReader br;
    StringTokenizer tk;
    Reader() throws IOException
    {
        br=new BufferedReader(new InputStreamReader(System.in));
        tk=new StringTokenizer("");
    }
    public int nextInt() throws IOException
    {
        while(!tk.hasMoreTokens())
        {
            tk=new StringTokenizer(br.readLine().replaceAll("\r",""));
        }
        return Integer.parseInt(tk.nextToken());
    }
    public Float nextFloat() throws IOException
    {
        while(!tk.hasMoreTokens())
        {
            tk=new StringTokenizer(br.readLine().replaceAll("\r",""));
        }
        return Float.parseFloat(tk.nextToken());
    }
    public String next() throws IOException
    {
        return br.readLine();
    }
}



class Song implements Serializable{
	private String name;
	private String singer;
	private String duration;

	public Song(String a, String b, String c){
		name = a;
		singer = b;
		duration = c;
	}

	public String getName(){
		return this.name;
	}

	@Override
	public boolean equals(Object o){
		Song oo = (Song) o;
		return oo.getName().equals(name);
	}


	public String toString(){
		return name+" "+singer+" "+duration;
		}

}

class Playlist implements Serializable{
	List<Song> l;

	public Playlist(){
		l = new LinkedList<Song>();
	}

	public void addSong(Song newSong){
		l.add(newSong);
		System.out.println(l.size());
	}

	public Song search(String name) throws SongNotFoundException{
		Song song = new Song(name, "X", "X");
		if(l.indexOf(song)==-1){
			throw new SongNotFoundException("No such song");
		}
		else{
			return l.get(l.indexOf(song));
		}
	}

	public void delSong(String name) throws SongNotFoundException{
		
		try{
			l.remove(search(name));
		}
		catch(SongNotFoundException e){
			System.out.println(e);

		}
	}


	public void show(){
		if(l.size()!=0){
			for(int i=0 ; i<l.size(); i++){
				System.out.println(l.get(i));
			}

		}
		else
			System.out.println("No Songs Exist");

	}



	
}

class SongNotFoundException extends Exception{
	public SongNotFoundException(String message){
        super(message);
    }
}



class App{

	static void dispOptions(){
		System.out.println("Enter an option");
		System.out.println("1. Add");
		System.out.println("2. Delete");
		System.out.println("3. Search");
		System.out.println("4. Show");
		System.out.println("5. Back to Menu");
		System.out.println("6. Exit");
	}


	public static void main(String[] args) throws Exception {
	Reader rd = new Reader();
	Playlist p = new Playlist();


	Song z;
	String l;
	while(true){
		dispOptions();
	int s = rd.nextInt();
	switch(s){
		case 1: 
		{
			l = rd.next();
			String[] ar = l.split(" ");
			z = new Song(ar[0], ar[1], ar[2]);
			p.addSong(z);
			break;
		}
		case 2:
		{
			l = rd.next();
			p.delSong(l);
			break;

		}
		case 3:{
			l = rd.next();
			try{
				System.out.println(p.search(l));
			}
			catch(SongNotFoundException e){
				System.out.println(e);

			}
			break;

		}
		case 4:{
			p.show();
			break;
		}
		case 5:
		case 6: System.exit(0);
	}
}




	
		
	}
	

}