package climatechange;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.TreeSet;

public class ClimateAnalyzer implements IClimateAnalyzer{
	public static final String[] months = 
		{"Dec", "Jan", "Feb", "Mar", "Apr", "May", 
		 "Jun", "Jul", "Aug", "Sep", "Oct", "Nov"};
	
	private WeatherIO IO;
	private ArrayList<ITemperature> baseData;
	
	// baseData will be null if file is empty, 
	// or if a fileIO Exception has occurred with the given file
	public ClimateAnalyzer(String filename)
	{
		IO = new WeatherIO();
		baseData = IO.readDataFromFile(filename);
	}
	
	
	//
	// ( Helper Method )
	// Sorts a given ArrayList by the order defined in the compareTo method
	// Generally, from low to high temperature
	//
	private ArrayList<ITemperature> getSorted ( ArrayList<ITemperature> data)
	{
		TreeDataSet<ITemperature> sortedData = new TreeDataSet<ITemperature>(data);
		
		return sortedData.getSortedList();
	}
	
	
	//
	// (Helper Method) 
	// Given a month and data set, 
	// returns an ArrayList containing all Temperature objects with that month
	// ArrayList is "unsorted"
	//
	private ArrayList<ITemperature> getFilteredByMonth ( ArrayList<ITemperature> data, int month ) 
			throws IndexOutOfBoundsException, IllegalArgumentException
	{
		if ( month < 1 || month > 12)
		{
			throw new IndexOutOfBoundsException("Input for Month, '" + month + 
					"' is outside of the range 1-12, try again");
		}
		
		// Converts Int month into String month, 
		// for 1(Jan) - 12(Dec), months%12 makes sure it doesn't exceed the limits of the array
		String monthAsString = months[month%12];
		HashDataSet<ITemperature> filteredData = new HashDataSet<ITemperature>();
		
		// Iterates entire length of given data, checks if it has the same month
		// adding to HashDataSet
		for ( ITemperature t : data)
		{
			boolean hasMonth = t.getMonth().equals( monthAsString);
			
			if ( hasMonth ) {
				filteredData.add(t);
			}
		}
		
		if ( filteredData.size() <= 0)
		{
			throw new IllegalArgumentException(
				"No such Temperature for given month, '" + monthAsString +"' was found, try again" );
		}
		
		return filteredData.getList();
	}
	
	
	//
	// (Helper Method) 
	// Given a year and data set,
	// returns an ArrayList containing all Temperature objects with that year
	// ArrayList is "unsorted"
	//
	private ArrayList<ITemperature> getFilteredByYear ( ArrayList<ITemperature> data, int year ) 
			throws IllegalArgumentException
	{
		HashDataSet<ITemperature> filteredData = new HashDataSet<ITemperature>();
		
		for ( ITemperature t : data )
		{
			boolean hasYear = t.getYear() == year;
			
			if ( hasYear ) 
			{
				filteredData.add(t);
			}
		}
		
		// Checks for invalid inputs, years should be realistic values 
		if ( filteredData.size() <= 0)
		{
			throw new IllegalArgumentException(
					"No such temperature in the given year, '" + year +"', was found, try again");
		}
		
		return filteredData.getList();
	}
	
	
	//
	// (Helper Method) 
	// Given a country and data set,
	// returns an ArrayList containing all Temperature objects with that country
	// ArrayList is "unsorted"
	//
	private ArrayList<ITemperature> getFilteredByCountry ( ArrayList<ITemperature> data, String country ) 
			throws IllegalArgumentException
	{
		HashDataSet<ITemperature> filteredData = new HashDataSet<ITemperature>();
		
		for ( ITemperature t : data ) 
		{
			boolean hasCountry = t.getCountry().equalsIgnoreCase(country);
			
			if ( hasCountry )
			{
				filteredData.add(t);
			}
		}
		
		// Checks for invalid inputs, 
		// if user inputs a country that doesn't exist, set will be empty
		if ( filteredData.size() <= 0)
		{
			throw new IllegalArgumentException(
					"No such temperature with given country, '" + country + "', was found, try again");
		}
		
		return filteredData.getList();
	}
	
	
	//
	// (Helper Method) 
	// Given a temperature range and data set,
	// returns an ArrayList containing all Temperature objects within that range
	// ArrayList is "unsorted"
	//
	private ArrayList<ITemperature> getFilteredByRange ( 
			double rangeLowTemp, double rangeHighTemp, 
			ArrayList<ITemperature> data) 
					throws IllegalArgumentException
	{
		HashDataSet<ITemperature> filteredData = new HashDataSet<ITemperature>();
		
		for ( ITemperature t : data )
		{
			boolean isBetweenRange 	= ( t.getTemperature(false) >= rangeLowTemp ) 
									&& ( t.getTemperature(false) <= rangeHighTemp );
			
			if ( isBetweenRange )
			{
				filteredData.add(t);
			}
		}
		
		// Checks for invalid input
		// Makes sure user inputs a realistic Temperature range ( something that exists)
		if ( filteredData.size() <= 0)
		{
			throw new IllegalArgumentException(
					"No such temperature within the given temperature range was found, try again");
		}
		
		return filteredData.getList();
	}
	
	
	//
	// (Helper Method)
	// Given a pre-sorted data set,
	// checks if Temperature object has a unique country ( isn't already in the set)
	// if so, add it, else don't
	// return the data containing temperature objects with unique countries, sorted low to high
	//
	private ArrayList<ITemperature> getUniqueCountries(ArrayList<ITemperature> data)
	{
		// TreeSet for ensuring the data is ordered
		// HashSet for storing known Countries that are already in the TreeSet
		TreeDataSet<ITemperature> 	filteredData	 		= new TreeDataSet<ITemperature>();
		HashSet<String> 			existingCountries 	= new HashSet<String>();
		
		for ( ITemperature t : data ) 
		{
			boolean hasDuplicateCountry = existingCountries.contains( t.getCountry() );
			
			if ( ! ( hasDuplicateCountry) )
			{
				filteredData.add(t);
				existingCountries.add( t.getCountry() );
			}
		}
		
		return filteredData.getSortedList();
	}
	
	
	//
	// (Helper Method)
	// returns all elements of a given ArrayList from start(inclusive) to end (exclusive)
	// If start or end exceed what's expected, then just return what's left in the array
	//
	private ArrayList<ITemperature> getSubList( int start, int end, ArrayList<ITemperature> data)
	{
		ArrayList<ITemperature> subData = new ArrayList<ITemperature>();
		
		if ( (data.size() <= 0) || (end > data.size()) || (start < 0) ) 
		{
			return data;
		}

		for ( int index = start; index < end; index++)
		{
			subData.add(data.get(index));
		}
		
		return subData;
	}
	
	
	//
	// (Helper Method)
	// Given a pre-sorted data set, gets the min and max values of each country inside
	// returns an ArrayList of the min and max temperature values of all countries
	// 
	private ArrayList<ITemperature> getCountryMinMax ( ArrayList<ITemperature> data)
	{
		TreeDataSet<ITemperature> sortedData = new TreeDataSet<ITemperature>();
		
		for ( ITemperature t : data)
		{
			ArrayList<ITemperature> temp =  getFilteredByCountry(data, t.getCountry());
			
			ITemperature min = temp.get(0);
			ITemperature max = temp.get(temp.size() - 1);
			
			// Removes possibility for duplicates for a country
			if (!( min.equals(max)  || sortedData.contains(min) || sortedData.contains(max))) {
				sortedData.add(min);
				sortedData.add(max);
			} else if ( min.equals(max) ) {
				sortedData.add(min);
			}
		}
		
		return sortedData.getSortedList();
	}
	
	
	//
	// A-1 LOWEST
	// Given the country and month, filter base set of data by country, then by month
	// Sorts the array from low to high temperatures
	// returns the first item in the array ( lowest )
	//
	public ITemperature getLowestTempByMonth(String country, int month) 
			throws IllegalArgumentException, IndexOutOfBoundsException
	{
		ArrayList<ITemperature> sortedData = getFilteredByCountry(baseData, country);
								sortedData = getFilteredByMonth(sortedData, month);
								sortedData = getSorted(sortedData);
		
		return sortedData.get(0);
	}
	
	
	//
	// A-1 HIGHEST
	// basically the same as the previous, but returns the last item in the array ( highest )
	//
	public ITemperature getHighestTempByMonth(String country, int month) 
			throws IllegalArgumentException, IndexOutOfBoundsException
	{
		ArrayList<ITemperature> sortedData = getFilteredByCountry(baseData, country);
								sortedData = getFilteredByMonth(sortedData, month);
								sortedData = getSorted(sortedData);
								
		return sortedData.get( sortedData.size() - 1 );
	}
	
	
	//
	// A-2 LOWEST
	// Given country and year, filter the base data set by country, then by year
	// After filtering, sort array from low to high temperature
	// returns the first element in the array ( lowest )
	//
	public ITemperature getLowestTempByYear(String country, int year) 
			throws IllegalArgumentException
	{
		ArrayList<ITemperature> sortedData = getFilteredByCountry(baseData, country);
								sortedData = getFilteredByYear(sortedData, year);
								sortedData = getSorted(sortedData);
								
		return sortedData.get(0);
	}
	
	//
	// A-2 HIGHEST
	// Basically the same as the previous, but returns last item in the array( highest)
	//
	public ITemperature getHighestTempByYear(String country, int year) 
			throws IllegalArgumentException
	{
		ArrayList<ITemperature> sortedData = getFilteredByCountry(baseData, country);
								sortedData = getFilteredByYear(sortedData, year);
								sortedData = getSorted(sortedData);
								
		return sortedData.get( sortedData.size() - 1 );
	}
	
	
	//
	// A-3 RANGE
	// Given country and min and max temps, filter by country, then by temp range
	// then sort data from low to high temps
	// returns sorted data
	//
	public TreeSet<ITemperature> getTempWithinRange(String country, 
			double rangeLowTemp, 
			double rangeHighTemp)
				throws IllegalArgumentException
	{
		ArrayList<ITemperature> sortedData = getFilteredByCountry(baseData, country);
								sortedData = getFilteredByRange(rangeLowTemp, rangeHighTemp, sortedData);
								sortedData = getSorted(sortedData);
								
		return new TreeSet<ITemperature>(sortedData);
	}
	
	
	//
	// A-4
	// Given country, filter by country
	// Sort the data array from low to high temp
	// return first element in the array ( lowest )
	//
	public ITemperature getLowestTempYearByCountry(String country) 
			throws IllegalArgumentException
	{
		ArrayList<ITemperature> sortedData = getFilteredByCountry(baseData, country);
								sortedData = getSorted(sortedData);
								
		return sortedData.get(0);
	}
	
	
	//
	// A-4
	// Basically the previous method, but
	// return last element in the array ( highest)
	//
	public ITemperature getHighestTempYearByCountry(String country) 
			throws IllegalArgumentException
	{
		ArrayList<ITemperature> sortedData = getFilteredByCountry(baseData, country);
								sortedData = getSorted(sortedData);
								
		return sortedData.get( sortedData.size() - 1 );
	}
	
	
	//
	// B1
	// Filters data by month, then sorts it.
	// retrieves the lowest temperature values for a country,
	// removes every other instance of that country
	// returns the first 10 elements in the list, 
	//
	// will return whatever is in the array if there are less than 10 elements but more than 0
	//
	public ArrayList<ITemperature> allCountriesGetTop10LowestTemp(int month) 
			throws IndexOutOfBoundsException, IllegalArgumentException
	{
		ArrayList<ITemperature> sortedData = getFilteredByMonth(baseData, month);
								sortedData = getSorted(sortedData);
								sortedData = getUniqueCountries(sortedData);
								
		int start 	= 0;
		int end 	= 10;
		
		return getSubList(start, end, sortedData);
	}
	
	
	//
	// B1
	// source for reverse method : 
	// https://docs.oracle.com/javase/8/docs/api/java/util/Collections.html 
	//
	// basically the previous method, but after sorting normally,
	// reverses list to go from high-low, removes duplicate countries, then retrieves last 10 elements
	//
	public ArrayList<ITemperature> allCountriesGetTop10HighestTemp(int month) 
			throws IndexOutOfBoundsException, IllegalArgumentException
	{
		// filters out all other months in the data set and sorts it from low-high
		ArrayList<ITemperature> sortedData = getFilteredByMonth(baseData, month);
								sortedData = getSorted(sortedData);
								
		// currently sorted from low-high, this swaps it to high-low,
		Collections.reverse(sortedData);
		// filters out objects with the same country, automatically sorts it low-high
		sortedData = getUniqueCountries(sortedData);
		
		int start 	= sortedData.size() - 10;
		int end 	= sortedData.size();
		
		return getSubList(start, end, sortedData);
	}
	
	
	//
	// B2
	// sorts the base data, then removes repeating countries except the lowest ones
	// returns first 10 in the list
	//
	public ArrayList<ITemperature> allCountriesGetTop10LowestTemp()
	{	
		ArrayList<ITemperature> sortedData = getSorted(baseData);
								sortedData = getUniqueCountries(sortedData);
		
		int firstIndex 	= 0;
		int lastIndex 	= 10;
		
		return getSubList(firstIndex, lastIndex, sortedData);
	}
	
	
	//
	// B2
	// sorts the base data, flips it, then removes repeating countries except the highest ones
	// list is automatically placed back to sorted order, so return last 10 elements
	//
	public ArrayList<ITemperature> allCountriesGetTop10HighestTemp()
	{
		ArrayList<ITemperature> sortedData = getSorted(baseData);
		
		// reverses from low-high to high-low
		Collections.reverse(sortedData);
		sortedData = getUniqueCountries(sortedData);
		
		int start 	= sortedData.size() - 10;
		int end 	= sortedData.size();

		return getSubList(start, end, sortedData);
	}
	
	
	//
	// B3
	// given temperature range,
	// return all temperature objects with temperatures within that range
	//
	public ArrayList<ITemperature> allCountriesGetAllDataWithinTempRange(
			double lowRangeTemp, 
			double highRangeTemp) throws IllegalArgumentException
	{
		ArrayList<ITemperature> sortedData = getFilteredByRange(
				lowRangeTemp, highRangeTemp, baseData);
								sortedData = getSorted(sortedData);
		
		return sortedData;
	}
	

	//
	// C1
	// gets the top 10 countries with the greatest change in temperature in the same month
	// between 2 years
	//
	public ArrayList<ITemperature> allCountriesTop10TempDelta(int month, int year1, int year2)
			throws IndexOutOfBoundsException, IllegalArgumentException
	{
		// Filter by month, then by year, then sort,
		// then get the min and max temperatures for every country (for both years)
		ArrayList<ITemperature> sortedDataYear1 = getFilteredByMonth(baseData, month);
								sortedDataYear1 = getFilteredByYear(sortedDataYear1, year1);
								sortedDataYear1 = getSorted(sortedDataYear1);
								sortedDataYear1 = getCountryMinMax(sortedDataYear1);
								
		ArrayList<ITemperature> sortedDataYear2 = getFilteredByMonth(baseData, month);
								sortedDataYear2 = getFilteredByYear(sortedDataYear2, year2);
								sortedDataYear2 = getSorted(sortedDataYear2);
								sortedDataYear2 = getCountryMinMax(sortedDataYear2);

		ArrayList<ITemperature> sortedData = new ArrayList<ITemperature>();				
		
		// for every temperature with the same country, get the delta between them
		// add them to the list
		for ( ITemperature t : sortedDataYear1 )
		{
			for ( ITemperature x : sortedDataYear2)
			{
				if ( t.getCountry().equalsIgnoreCase(x.getCountry()))
				{
					sortedData.add(new DeltaTemperature(t, x));
				}
			}
		}
		
		// sort list from low-high
		sortedData = getSorted(sortedData);
		// ensures no countries appear more than once
		sortedData = getUniqueCountries(sortedData);
		
		// get last 10 to get top 10 highest changes in temp
		int start 	= sortedData.size() - 10;
		int end 	= sortedData.size();
		
		return getSubList(start, end, sortedData);
	}

	
	//
	// (Helper Method)
	// gets user input for both parts of Task A1
	// 'type' dictates whether it will take in user input for writing data for 'lowest' or 'highest' 
	//
	private void executeTaskA1 (Scanner scanner, String header, String type ) 
	{	
		// tells the user what the task is, so they know what values to input
		System.out.println("A-1 ) " + type +" Temperature in a given Country and Month");
		
		// ArrayList stores data to be written to file
		ArrayList<ITemperature> theWeatherList = new ArrayList<ITemperature>();
		boolean done = false;
		
		// ensures that program continues asking user for an input until data is written to the file
		// even through exceptions
		while (! done )
		{
			try 
			{
				// Scanner
				scanner = new Scanner(System.in);
				
				System.out.print("1 ) Please enter a [Country] : ");
				String country = scanner.nextLine();
				
				System.out.print("2 ) Plese enter a [Month] in numeric form \n		(1 = Jan, 12 = Dec) : ");
				int month = scanner.nextInt();
				
				// runs getLowestTempByMonth if 'type' is 'lowest', else getHighestTempByMonth
				if ( type.equals("Lowest"))
				{
					theWeatherList.add(getLowestTempByMonth(country, month));
				} 
				else
				{
					theWeatherList.add(getHighestTempByMonth(country, month));
				}
				
				// subject header being written to the file
				String subject = "Task A1 : " + type + " Temperature for " + country.toUpperCase() + 
						" in " + months[month % 12];
				
				IO.writeSubjectHeaderInFile("data/taskA1_climate_info.csv", subject);
				IO.writeDataToFile("data/taskA1_climate_info.csv", header, theWeatherList);

				done = true;
			}
			catch (IllegalArgumentException a)
			{
				// when user inputs a country that doesn't exist
				System.out.println(a.getMessage());
			}
			catch (InputMismatchException b )
			{
				// when user enters a double or string into for month
				System.out.println("Invalid input for Month ( must be an integer), try again");
			}
			catch ( IndexOutOfBoundsException c)
			{
				// when a user inputs a value for month outside of the range 1-12
				System.out.println(c.getMessage());
			}
			
			System.out.println();
		}
	}
	
	
	//
	// (Helper Method)
	// runs sequence to get user input for A2 specifically ( for either part)
	//
	private void executeTaskA2 ( Scanner scanner, String header, String type) 
	{
		// Tells user what the task is
		System.out.println("A-2 ) " + type + " Temperature in a given Country and Year  ");
		
		// ArrayList for storing data to be written
		ArrayList<ITemperature> theWeatherList = new ArrayList<ITemperature>();
		boolean done = false;
		
		while(!done) 
		{
			try
			{
				scanner = new Scanner(System.in);
				
				System.out.print("1 ) Please enter a [Country] : ");
				String country = scanner.nextLine();
				
				System.out.print("2 ) Please enter a [Year] : ");
				int year = scanner.nextInt();
				
				// if given 'lowest' for type, will run getLowestTempByYear, else getHighestTempByYear
				if ( type.equals("Lowest"))
				{
					theWeatherList.add(getLowestTempByYear(country, year));
				}
				else
				{
					theWeatherList.add(getHighestTempByYear(country, year));
				}
				
				String subject = "Task A2 : " + type + " Temperature for " + country.toUpperCase() +
						" in " + year;
				
				IO.writeSubjectHeaderInFile("data/taskA2_climate_info.csv", subject);
				IO.writeDataToFile("data/taskA2_climate_info.csv", header, theWeatherList);
				
				done = true;
			}
			catch (IllegalArgumentException a)
			{
				// when a given country or year does not exist within the data
				System.out.println(a.getMessage());
			}
			catch (InputMismatchException b ) 
			{
				// for when a double or String has been input instead of an int
				System.out.println("Invalid input for Year ( must be an integer), try again");
			}
			
			System.out.println();
		}
	}
	
	
	//
	// ( Helper Method ) 
	// runs task A3, doesn't require a 'type'
	// same basic format as previous helper methods, with some variations
	//
	private void executeTaskA3(Scanner scanner, String header)
	{
		System.out.println("A-3 ) Temperatures in a Given Country and Temperature Range");
		
		ArrayList<ITemperature> theWeatherList;
		boolean done = false;
		
		while(!done) {
			try 
			{
				// Takes input for country, min temp, and max temp
				scanner = new Scanner(System.in);
				
				System.out.print("1 ) Please enter a [Country] : ");
				String country = scanner.nextLine();
				
				System.out.print("2 ) Please enter a [Lower Temperature] : ");
				double minTemp = scanner.nextDouble();
				
				System.out.print("3 ) Please enter a [Higher Temperature] : ");
				double maxTemp = scanner.nextDouble();
				
				theWeatherList = new ArrayList<ITemperature>(
						getTempWithinRange(country, minTemp, maxTemp));
				
				String subject = "Task A3 : Highest Temperature for " + country.toUpperCase() + 
							" between " + minTemp + " - " + maxTemp;
				
				IO.writeSubjectHeaderInFile("data/taskA3_climate_info.csv", subject);
				IO.writeDataToFile("data/taskA3_climate_info.csv", header, theWeatherList);

				done = true;
			}
			catch (IllegalArgumentException a)
			{
				// if no country with that country or temperature range is found
				System.out.println(a.getMessage());
			}
			catch ( InputMismatchException b)
			{
				// when a string is input for either temperatures
				System.out.println("Invalid input for Temperature( must be a double ), try again");
			}
			
			System.out.println();
		}
	}
	
	
	//
	// (Helper Method) gets user input for task A-4
	// type used to dictate whether to use input in lowest or highest versions of the task
	// similar to previous methods
	//
	private void executeTaskA4(Scanner scanner, String header, String type)
	{	
		System.out.println("A-4 ) Year with the " + type + " Temperature in a Given Country");
		
		ArrayList<ITemperature> theWeatherList = new ArrayList<ITemperature>();
		boolean done = false;
		
		while(!done) 
		{
			try 
			{
				scanner = new Scanner(System.in);
						
				System.out.print("1 ) Please enter a [Country] : ");
				String country = scanner.nextLine();
						
				
				if ( type.equals("Lowest") )
				{
					theWeatherList.add(getLowestTempYearByCountry(country));
				}
				else
				{
					theWeatherList.add(getHighestTempYearByCountry(country));
				}
				
				String subject = "Task A4 : The Year with The "+ type + " Temperature for " 
						+ country.toUpperCase();
					
				IO.writeSubjectHeaderInFile("data/taskA4_climate_info.csv", subject);
				IO.writeDataToFile("data/taskA4_climate_info.csv", header, theWeatherList);
					
				done = true;
			}
			catch (IllegalArgumentException a)
			{
				// if a country does not exist in the data set
				System.out.println(a.getMessage());
			}
			
			System.out.println();
		}
	}
	
	
	//
	// (Helper Method) gets user input for task b-1
	// same basic format as previous methods 
	//
	private void executeTaskB1( Scanner scanner, String header, String type)
	{
		System.out.println("B-1 ) Top 10 Countries with the " + type + " Temperatures in Given Month");
		
		ArrayList<ITemperature> theWeatherList;
		boolean done = false;		
			
		while(! done) 
		{
			try 
			{
				scanner = new Scanner(System.in);
						
				System.out.print("1 ) Please enter a [Month] in numeric form \n		(1 = Jan, 12 = Dec) : ");
				int month = scanner.nextInt();
				
				if ( type.equals("Lowest") )
				{
					theWeatherList = allCountriesGetTop10LowestTemp(month);
				}
				else 
				{
					theWeatherList = allCountriesGetTop10HighestTemp(month);
				}
				
				String subject = "Task B1 : Top 10 Countries with the " + type 
						+ " Temperatures in " + months[month%12];
						
				IO.writeSubjectHeaderInFile("data/taskB1_climate_info.csv", subject);
				IO.writeDataToFile("data/taskB1_climate_info.csv", header, theWeatherList);
						
				done = true;
			}
			catch (IllegalArgumentException a)
			{
				// if no values were found within that month
				System.out.println(a.getMessage());
			}
			catch (InputMismatchException b ){
				// in case user inputs a double or String into month
				System.out.println("Invalid input for Month( must be an integer) , try again");
			}
			catch ( IndexOutOfBoundsException c)
			{
				// when the value used for month is outsie of the range 1-12
				System.out.println(c.getMessage());
			}
			
			System.out.println();
		}
	}
	
	
	//
	// ( Helper Method)
	// same basic format as the previous methods
	// except no user input is required
	//
	private void executeTaskB2(String header, String type)
	{
		System.out.println("B-2 ) Top 10 Countries with the " + type + " Temperatures...");	
		
		ArrayList<ITemperature> theWeatherList;
		
		if ( type.equals("Lowest") ) 
		{
			theWeatherList = allCountriesGetTop10LowestTemp();
		} 
		else 
		{
			theWeatherList = allCountriesGetTop10HighestTemp();
		}
		
		String subject = "Task B2 : Top 10 Countries with the "+ type + " Temperatures";
						
		IO.writeSubjectHeaderInFile("data/taskB2_climate_info.csv", subject);
		IO.writeDataToFile("data/taskB2_climate_info.csv", header, theWeatherList);

		System.out.println();
	}
	
	
	//
	// ( Helper Method)
	// gets user input for task b-3, no 'type' is required 
	// same basic format as previous methods
	//
	private void executeTaskB3(Scanner scanner, String header)
	{
		System.out.println("B-3 ) All Temperatures Within a Given Temperature Range  ");
		
		ArrayList<ITemperature> theWeatherList;
		boolean done = false;
			
		while( ! done ) 
		{
			try 
			{
				scanner = new Scanner(System.in);
		
				System.out.print("1 ) Please enter a [Lower Temperature] : ");
				double minTemp = scanner.nextDouble();
					
				System.out.print("2 ) Please enter a [Higher Temperature] : ");
				double maxTemp = scanner.nextDouble();
				
				theWeatherList = new ArrayList<ITemperature>(
						allCountriesGetAllDataWithinTempRange(minTemp, maxTemp));
				String subject = 	"Task B3 : all Temperatures Between " + minTemp + " - " + maxTemp;
				
				IO.writeSubjectHeaderInFile("data/taskB3_climate_info.csv", subject);
				IO.writeDataToFile("data/taskB3_climate_info.csv", header, theWeatherList);

				done = true;
			}
			catch ( InputMismatchException a)
			{
				// if user inputs a String for either temperature range
				System.out.println("Invalid input for Temperature( input must be of type double ),  try again");
				System.out.println(a.getMessage());
			}
			catch (IllegalArgumentException b)
			{
				// if no data exists between the given temperature ranges
				System.out.println(b.getMessage());
			}
			
			System.out.println();
		}
	}
	
	
	//
	// (Helper Method)
	// gets user input for task c1, no 'type required'
	// basically the same format as the previous methods
	//
	private void executeTaskC1(Scanner scanner, String header)
	{
		System.out.println("C-1 ) Top 10 Countries with The Greatest Change in Temperatures Between 2 Years ");
		
		ArrayList<ITemperature> theWeatherList;
		boolean done = false;
			
		while(! done)
		{
			try 
			{
				scanner = new Scanner(System.in);
					
				System.out.print("1 ) Please enter a [Month] in numeric form \n		(1 = Jan, 12 = Dec) : ");
				int month = scanner.nextInt();
							
				System.out.print("2 ) Please enter a [First Year] : ");
				int minYear = scanner.nextInt();
					
				System.out.print("3 ) Please enter a [Second Year] : ");
				int maxYear = scanner.nextInt();
					
				theWeatherList = allCountriesTop10TempDelta(month, minYear, maxYear);
				String subject = "Task C1 : Top 10 Countries with the Greatest Temperature Differences in " 
						+ months[month%12] + " from " + minYear + "-" + maxYear;
						
				IO.writeSubjectHeaderInFile("data/taskC1_climate_info.csv", subject);
				IO.writeDataToFile("data/taskC1_climate_info.csv", header, theWeatherList);
						
				done = true;
			}
			catch (IllegalArgumentException a)
			{
				// when there is no data within the given month and year range
				System.out.println(a.getMessage());
			}
			catch (InputMismatchException b ){
				// when a double or String is used as an input for month, or year
				System.out.println("Invalid, Input for month/year must be an integer, try again...");
				System.out.println(b.getMessage());
			}
			catch ( IndexOutOfBoundsException c)
			{
				// when the user inputs something outside of the range 1-12 for month
				System.out.println(c.getMessage());
			}
				
			System.out.println();
		}
	}
	
	
	//
	// Calls all methods in ClimateAnalyzer with values from user inputs
	// Using above helper methods
	// Writes to files fileA, fileB, and fileC for all tasks A, B, C respectively
	//
	public void runClimateAnalyzer()
	{	
		String 	columnHeader 	= "Temperature,Year,Month,Country,Country_Code";
		Scanner scanner			= new Scanner(System.in);
		
		//A-1 Lowest and Highest
		executeTaskA1(scanner, columnHeader,"Lowest");
		executeTaskA1(scanner, columnHeader, "Highest");
		
		// A-2 Lowest and Highest
		executeTaskA2(scanner, columnHeader, "Lowest");
		executeTaskA2(scanner, columnHeader, "Highest");
		
		// A-3 RANGE
		executeTaskA3(scanner, columnHeader);
		
		// A-4 LOWEST AND HIGHEST
		executeTaskA4(scanner, columnHeader, "Lowest");
		executeTaskA4(scanner, columnHeader, "Highest");
		
		// B-1 LOWEST AND HIGHEST
		executeTaskB1(scanner, columnHeader, "Lowest");
		executeTaskB1(scanner, columnHeader, "Highest");

		// B-2 LOWEST AND HIGHEST
		executeTaskB2(columnHeader, "Lowest");
		executeTaskB2(columnHeader, "Highest");
		
		// B-3 TEMPERATURE RANGE
		executeTaskB3(scanner, columnHeader);
		
		// C-1
		columnHeader = "Temperature Delta,Year Delta,Month,Country,Country_Code";
		executeTaskC1(scanner, columnHeader);
	
		scanner.close();
		System.out.println("Done!");		
	}
	
	
	public static void main ( String[] args)
	{
		ClimateAnalyzer ca = new ClimateAnalyzer("data/world_temp_2000-2016.csv");
	
		// when there are no fileIO or formatting exceptions within the given file, runs the method
		if ( ! ( ca.baseData == null ) )
		{
			ca.runClimateAnalyzer();
		}
	}
			
}
