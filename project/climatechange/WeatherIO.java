package climatechange;

import java.util.ArrayList;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.File;

//
// class handles I/O ClimateAnalyzer and data files
//


public class WeatherIO implements IWeatherIO
{
	
	//
	// empty default constructor, nothing needs to be initialized
	//
	public WeatherIO(){}
	
	
	//
	// Given a filename/path will read contents of the file 
	// ( assuming that it is in the correct format )
	//
	@Override
	public ArrayList<ITemperature> readDataFromFile (String fileName)
	{
		File f = new File(fileName);
		
		ArrayList<ITemperature> data = new ArrayList<ITemperature>();

		try 
		{
			FileReader 		fr = new FileReader(f);
			BufferedReader 	br = new BufferedReader(fr);
				
			br.readLine();
			String line; 
				
			while ( (line = br.readLine()) != null)
			{
				String[] tempData = line.split(",");
				
				double temperature 	= Double.parseDouble( tempData[0].trim() );
				int 	year 		= Integer.parseInt( tempData[1].trim() );
				String 	month 		= tempData[2].trim();
				String 	country 	= tempData[3].trim();
				String 	code 		= tempData[4].trim();
					
				data.add( new Temperature(temperature, year, month, country, code) );
				
			}
				
			br.close();
			fr.close();
		}
		catch (IOException x)
		{
			System.out.println("Given file doesn't exist!");
			x.printStackTrace();
			data = null;
		}
		catch (NumberFormatException y)
		{
			System.out.println("Invalid file format! File is not in the expected format");
			y.printStackTrace();
			data = null;
		}
		catch (Exception z)
		{
			z.printStackTrace();
			data = null;
		}
		
		return data;
	}
	
	
	//
	// if the filename doesn't exist, creates a new file and and writes to it
	// else appends the subject text to it
	// append implementation from :
	// https://docs.oracle.com/javase/7/docs/api/java/io/FileWriter.html
	//
	@Override
	public void writeSubjectHeaderInFile(String filename, String subject) 
	{	
		try 
		{
			FileWriter 	fr 	= new FileWriter(filename, true);
			PrintWriter pw 	= new PrintWriter(fr);
			
			pw.println(subject);
			
			pw.close();
			fr.close();
		}
		catch (IOException x)
		{
			System.out.println(x.getMessage());
		}
	}
	
	
	//
	// writes to the file, appending whatever data is given
	//
	@Override
	public void writeDataToFile(String filename, String topic,
			ArrayList<ITemperature> theWeatherList)
	{
		try 
		{
			FileWriter 	fw 	= new FileWriter(filename, true);
			PrintWriter pw 	= new PrintWriter(fw);
			
			pw.println(topic);
			for(ITemperature t : theWeatherList)
			{
				pw.println(t);
			}

			pw.close();
			fw.close();
		}
		catch (IOException x)
		{
			System.out.println(x.getMessage());
		}
	}
}
