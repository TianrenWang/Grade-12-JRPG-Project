import java.io.*;

/*****************************************************
* Written by: Gerry Heffernan
* Date: April 2002
* Methods for reading & writing text files.
*****************************************************/

public class TextFile
{
	PrintWriter out;
	BufferedReader in;
	String fileMode, fileName;
	
	/********
	* open - get file ready to read or write
	*/
	public boolean open(String name, String mode)
	{
		boolean exists = true; // see if file exists
		fileMode = mode;
		fileName = name;
		
		if (mode == "r")
		{
			try
			{
				in = new BufferedReader(new FileReader(name));
			}
			catch (IOException e)
			{
				exists = false;
			}
		}
		else if (mode == "w")
		{
			try
			{
				out = new PrintWriter(new FileWriter(name));
			}
			catch (IOException e)
			{
				System.out.println("Cannot open \""+name+"\"");
				System.out.println("Because - " + e);
			}
		}
		else 
			System.out.println("Invalid mode argument to method \"open\"");
		return exists;
	}
	
	/********
	* write - write one line to the file
	*/
	public void write(String s)
	{
		if (out != null)
			out.println(s);
	}
	
	public void close()
	{
		try
		{
			if (fileMode == "w" && out != null)
				out.close();
			else if (fileMode == "r" && in != null)
				in.close();
			else
				System.out.println("There is no file to close");
		}
		catch (IOException e)
		{
			System.out.println("Unable to close \""+fileName+"\"");
			System.out.println("Because - " + e);
		}
	}
	
	public String read()
	{
		String s=null;
		
		if (in == null)
			System.out.println("The file has not been opened");
		else
		{
			try
			{
				s = in.readLine();
			}
			catch (IOException e)
			{
				System.out.println("Unable to read \""+fileName+"\"");
				System.out.println("Because - " + e);
			}
		}
		return s;
	}
		
	
	public static void main(String[]args)
	{
		String s;
		TextFile f = new TextFile();
		
		f.close();
		s = f.read();
		f.open("noway", "x");
		if (!f.open("noway", "r"))
			System.out.println("Noway does not exist!!!");
		f.open("d:\\java\\Fund.java", "r");
		System.out.println(f.read());
		f.close();
		f.open("test", "w");
		f.write("A first test");
		f.write("Some more");
		f.close();
		f.open("test", "r");
		System.out.println("Echoing input file");
		do
		{
			s = f.read();
			if (s != null)
				System.out.println(s);
		} while (s != null);
		f.close();
			
	}
}


