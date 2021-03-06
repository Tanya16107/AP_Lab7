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

	public String getNameSong(){
		return this.name;
	}

	@Override
	public boolean equals(Object o){
		Song oo = (Song) o;
		return oo.getNameSong().equals(name);
	}


	public String toString(){
		return "Song's name: "+name+", Singer: "+singer+", Duration: "+duration+"s";
		}

}

class Playlist implements Serializable{
	private	static	final long serialVersionUID	=42L;	
	private List<Song> l;
	private String name;


	public Playlist(String name){
		l = new LinkedList<Song>();
		this.name = name;
	}


	public String getNamePlaylist(){
		return name;
	}

	public int getNumSongs(){
		return l.size();
	}

	public void addSong(Song newSong){
		l.add(newSong);
		System.out.println("Total number of songs in the playlist are now "+l.size());
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
			System.out.println("Total number of songs in the playlist are now "+l.size());
		}
		catch(SongNotFoundException e){
			System.out.println(e);

		}
	}


	public String show(){
		String out = "";

		if(l.size()!=0){
			for(int i=0 ; i<l.size(); i++){
				out+=((i+1)+". "+l.get(i)+"\n");
			}

		}
		else{
			out = "No Songs Exist\n";
		}
		return out;

	}



	
}

class SongNotFoundException extends Exception{

	private	static	final long serialVersionUID	=41L;	
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
		System.out.println("6. Exit the App");
	}


	static void serialize(Playlist p) throws IOException{
		ObjectOutputStream out = null;
		try{
			
			out = new ObjectOutputStream(new FileOutputStream(String.valueOf(p.getNamePlaylist())+".txt"));
			out.writeObject(p);
		}
		finally{
			out.close();
		}

	}

	public static Playlist deserialize(String name) throws IOException, ClassNotFoundException {
		ObjectInputStream in = null;
		try{
			in = new ObjectInputStream(new FileInputStream(name+".txt"));
			return (Playlist) in.readObject();
		}
		finally{
			in.close();
		}
	}

	static void menu(){
		System.out.println("Enter an option");
		System.out.println("1. Go to playlist");
		System.out.println("2. Know number of songs in a playlist");
		System.out.println("3. Exit the App");
		System.out.println("4. Create a playlist");


	}

	public static void main(String[] args) throws Exception {
	Reader rd = new Reader();
	

	Song z;
	String l;
	Playlist p;


	while(true){
		menu();
		int c = rd.nextInt();
		switch(c){

		case 1:
		{
			System.out.println("Enter name of the playlist");
			l = rd.next();

			try{
			p = deserialize(l);

			boolean b = true;
			while(b){
			dispOptions();
			int s = rd.nextInt();
			switch(s){
			case 1: 
			{
				System.out.println("Enter name of the song");
				String newSong = rd.next();
				System.out.println("Enter name of the singer");
				String newSinger = rd.next();
				System.out.println("Enter duration of the song");
				String newDuration = rd.next();

				z = new Song(newSong, newSinger, newDuration);
				p.addSong(z);
				serialize(p);
				System.out.println();
				break;
			}
			case 2:
			{
				System.out.println("Enter the name of the song to be deleted");
				l = rd.next();
				p.delSong(l);

				serialize(p);

				System.out.println();
				break;

			}
			case 3:{
				System.out.println("Enter the name of the song to be searched");
				l = rd.next();
				try{
					System.out.println(p.search(l));
				}
				catch(SongNotFoundException e){
					System.out.println(e);

				}

				System.out.println();
				break;

			}
			case 4:{
				System.out.println(p.show());

			
				break;
			}
			case 5: 
			{b=false;

			break;}
			case 6: System.exit(0);
			}
			}
		}
			catch(NullPointerException e){
				System.out.println("No such playlist!");
			}

				System.out.println();
			break;
		}

		case 2: {
			System.out.println("Enter name of the playlist");
			l = rd.next();
			try{
			p = deserialize(l);
			System.out.println(p.getNumSongs());}
			catch(NullPointerException e){
				System.out.println("No such playlist!");
			}

				System.out.println();
			break;

		}

		case 3: System.exit(0);
		break;

		case 4: {
			System.out.println("Enter name of the playlist");
			l = rd.next();
			Playlist newPlaylist = new Playlist(l);
			serialize(newPlaylist);


				System.out.println();
		}


		}

	}





		
			
		}
		

	}