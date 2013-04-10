package test;

import java.awt.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Scanner;
import opencsv.CSVReader;
import opencsv.CSVWriter;
public class samp {

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		String directories = "C:\\Users\\user\\Desktop\\testing";
		String path; 
		if( args.length == 2)
		{
		path = new String(args[0]);
		System.out.println(path);
		directories = new String(args[1]);
		}
		else
		{
			System.out.println("Please enter file path as well as directory path: REMEMBER java -jar 'pathoflogfile' 'pathofsessionfolderforthatuser");
			return;
		}
        File file1 = new File(path);
        Scanner input = null;
		try 
		{
			input = new Scanner(file1);
		} 
		catch (FileNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//ArrayList<String[]> data = new ArrayList<String[]>();
		CSVWriter writer = null;
		String csv = null;
		int i = 0;
		int j = 1;
		File f = new File(directories+"\\Block"+j);
    	boolean res = f.mkdirs();
        while(input.hasNext()) 
        {
            String nextLine = input.nextLine();
            if(nextLine.toLowerCase().startsWith("bsp"))
            {
            	/*This means it is inside a new phrase */
            	if (i>=10)
            		{
            		j++;
            		i=1;
            		File f1 = new File(directories+"\\Block"+j);
                	boolean res1 = f1.mkdirs();
            		}
            	else
            	{
            		i++;
            	}
            	System.out.println("Inside block: "+j+" phrase: "+i);
            	csv = directories.concat("\\Block"+j+"\\"+String.valueOf(i)+".csv");
            	if(writer!=null)
            	{
            		try {
						writer.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
            	}
            	
            		try 
            		{
					    writer = new CSVWriter(new FileWriter(csv));
            		} 
            		catch (IOException e) 
            		{
					// TODO Auto-generated catch block
					e.printStackTrace();
            		}
            		
            	    String[] data = nextLine.split(",");
            	    writer.writeNext(data);
            	
            }
            else if(nextLine.toLowerCase().startsWith("block"))
            {
            	 String[] data = nextLine.split(",");
            	 j = Integer.parseInt(data[1]);
            	 writer.writeNext(data);
            }
            else if(nextLine.toLowerCase().startsWith("phrase"))
            {
            	 String[] data = nextLine.split(",");
            	 i = Integer.parseInt(data[1]);
            	 writer.writeNext(data);
            	
            }
            else if(!nextLine.isEmpty())
            {
            	String[] data = nextLine.split(",");
            	writer.writeNext(data);
            
            }
        }

        input.close();

	}

}
