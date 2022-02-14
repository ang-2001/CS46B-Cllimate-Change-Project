package test;

import java.util.ArrayList;

public class test {
//	System.out.println("A-2 ) Lowest Temperature in a given Country and Year  ");
//	
//	done = false;
//	
//	while(!done) 
//	{
//		try {
//			scanner = new Scanner(System.in);
//			
//			System.out.print("1 ) Please enter a [Country] : ");
//			country = scanner.nextLine();
//			
//			System.out.print("2 ) Plese enter a [Year] : ");
//			year = scanner.nextInt();
//			
//			theWeatherList.add(getLowestTempByYear(country, year));/////////
//			subject = "Task A2 : Lowest Temperature for " + country.toUpperCase() + " in " + year;
//			
//			IO.writeSubjectHeaderInFile("data/taskA2_climate_info.csv", subject);
//			IO.writeDataToFile("data/taskA2_climate_info.csv", columnHeader, theWeatherList);
//			
//			done = true;
//			theWeatherList.clear();
//		}
//		catch (IllegalArgumentException x)
//		{
//			System.out.println(x.getMessage());
//		}
//		catch (InputMismatchException ex )
//		{
//			System.out.println("Invalid input for Year, try again");
//		}
//		
//		System.out.println();
//	}
////	
//	
//	//
//	//A2 HIGHEST
//	//
//	System.out.println("A-2 ) Highest Temperature in a given Country and Year  ");
//	
//	done = false;
//	
//	while(!done) 
//	{
//		try {
//			scanner = new Scanner(System.in);
//			
//			System.out.print("1 ) Please enter a [Country] : ");
//			country = scanner.nextLine();
//			
//			System.out.print("2 ) Plese enter a [Year] : ");
//			year = scanner.nextInt();
//			
//			subject = "Task A2 : Highest Temperature for " + country.toUpperCase() + " in " + year;
//			theWeatherList.add(getHighestTempByYear(country, year));
//			
//			IO.writeSubjectHeaderInFile("data/taskA2_climate_info.csv", subject);
//			IO.writeDataToFile("data/taskA2_climate_info.csv", columnHeader, theWeatherList);
//		
//			done = true;
//			theWeatherList.clear();
//		}
//		catch (IllegalArgumentException x)
//		{
//			System.out.println(x.getMessage());
//		}
//		catch (InputMismatchException ex ){
//			System.out.println("Invalid input for Year, try again");
//		}
//		
//		System.out.println();
//	}
//	
//	
//	//
//	// A3 TEMPERATURE RANGE
//	//
//	System.out.println("A-3 ) Temperatures in a Given Country and Temperature Range");
//	
//	done = false;
//	
//	while(!done) {
//		try {
//			scanner = new Scanner(System.in);
//			
//			System.out.print("1 ) Please enter a [Country] : ");
//			country = scanner.nextLine();
//			
//			System.out.print("2 ) Plese enter a [Lower Temperature] : ");
//			minTemp = scanner.nextDouble();
//			
//			System.out.print("3 ) Plese enter a [Higher Temperature] : ");
//			maxTemp = scanner.nextDouble();
//			
//			subject = 	"Task A3 : Highest Temperature for " + country.toUpperCase() + 
//						" between " + minTemp + " - " + maxTemp;
//			theWeatherList = new ArrayList<ITemperature>(
//					getTempWithinRange(country, minTemp, maxTemp));
//			
//			IO.writeSubjectHeaderInFile("data/taskA3_climate_info.csv", subject);
//			IO.writeDataToFile("data/taskA3_climate_info.csv", columnHeader, theWeatherList);
//
//			done = true;
//			theWeatherList.clear();
//		}
//		catch (IllegalArgumentException x)
//		{
//			System.out.println(x.getMessage());
//		}
//		catch ( InputMismatchException ex)
//		{
//			System.out.println("Invalid input for Temperature, try again");
//		}
//		
//		System.out.println();
//	}
//	
//	
//	//
//	//A4 LOWEST
//	//
//	System.out.println("A-4 ) Year with the Lowest Temperature in a Given Country");
//			
//	done = false;
//			
//	while(!done) 
//	{
//		try 
//		{
//			scanner = new Scanner(System.in);
//					
//			System.out.print("1 ) Please enter a [Country] : ");
//			country = scanner.nextLine();
//					
//			subject = "Task A4 : The Year with The Lowest Temperature for " + country.toUpperCase();
//			theWeatherList.add(getLowestTempYearByCountry(country));
//				
//			IO.writeSubjectHeaderInFile("data/taskA4_climate_info.csv", subject);
//			IO.writeDataToFile("data/taskA4_climate_info.csv", columnHeader, theWeatherList);
//				
//			done = true;
//			theWeatherList.clear();
//		}
//		catch (IllegalArgumentException x)
//		{
//				System.out.println(x.getMessage());
//		}
//		
//		System.out.println();
//	}
//	
//	
//	//
//	//A4 HIGHEST
//	//
//	System.out.println("A-4 ) Year with The Highest Temperature in a Given Country");
//			
//	done = false;		
//		
//	while(!done) 
//	{
//		try 
//		{
//			scanner = new Scanner(System.in);
//						
//			System.out.print("1 ) Please enter a [Country] : ");
//			country = scanner.nextLine();
//						
//			subject = "Task A4 : The Year with The Highest Temperature for " + country.toUpperCase();
//			theWeatherList.add(getHighestTempYearByCountry(country)); 
//					
//			IO.writeSubjectHeaderInFile("data/taskA4_climate_info.csv", subject);
//			IO.writeDataToFile("data/taskA4_climate_info.csv", columnHeader, theWeatherList);
//					
//			done = true;
//			theWeatherList.clear();
//		}
//		catch (IllegalArgumentException x)
//		{
//			System.out.println(x.getMessage());
//		}
//		
//		System.out.println();
//	}
//	
//	
////
////B1 LOWEST TOP 10
////
//System.out.println("B-1 ) Top 10 Countries with the Lowest Temperatures in Given Month");
//	
//done = false;		
//	
//while(! done) 
//{
//	try 
//	{
//		scanner = new Scanner(System.in);
//					
//		System.out.print("1 ) Plese enter a [Month] in numeric form \n		(1 = Jan, 12 = Dec) : ");
//		month = scanner.nextInt();
//					
//		subject = "Task B1 : Top 10 Countries with the Lowest Temperatures in " + months[month%12];
//		theWeatherList = (allCountriesGetTop10LowestTemp(month));
//				
//		IO.writeSubjectHeaderInFile("data/taskB1_climate_info.csv", subject);
//		IO.writeDataToFile("data/taskB1_climate_info.csv", columnHeader, theWeatherList);
//				
//		done = true;
//		theWeatherList.clear();
//	}
//	catch (IllegalArgumentException x)
//	{
//		System.out.println(x.getMessage());
//	}
//	catch (InputMismatchException ex ){
//		System.out.println("Invalid input for Month, try again");
//	}
//	catch ( IndexOutOfBoundsException exception)
//	{
//		System.out.println(exception.getMessage());
//	}
//		
//	System.out.println();
//}
//	
//	
////
////B1 HIGHEST TOP 10
////
//System.out.println("B-1 ) Top 10 Countries with the Highest Temperatures in Given Month");
//	
//done = false;		
//	
//while(! done) 
//{
//	try 
//	{
//		scanner = new Scanner(System.in);
//					
//		System.out.print("1 ) Plese enter a [Month] in numeric form \n		(1 = Jan, 12 = Dec) : ");
//		month = scanner.nextInt();
//					
//		subject = "Task B1 : Top 10 Countries with the Highest Temperatures in " + months[month%12];
//		theWeatherList = (allCountriesGetTop10HighestTemp(month));
//				
//		IO.writeSubjectHeaderInFile("data/taskB1_climate_info.csv", subject);
//		IO.writeDataToFile("data/taskb1_climate_info.csv", columnHeader, theWeatherList);
//				
//		done = true;
//		theWeatherList.clear();
//	}
//	catch (IllegalArgumentException x)
//	{
//		System.out.println(x.getMessage());
//	}
//	catch (InputMismatchException ex ){
//		System.out.println("Invalid input for Year, try again");
//	}
//	catch ( IndexOutOfBoundsException exception)
//	{
//		System.out.println(exception.getMessage());
//	}
//	
//	System.out.println();
//}
//	
//
	
//	//
//	//B2 LOWEST TOP 10
//	//
//	System.out.println("B-2 ) Top 10 Countries with the Lowest Temperatures...");	
//
//	subject = "Task B2 : Top 10 Countries with the Lowest Temperatures";
//	theWeatherList = allCountriesGetTop10LowestTemp();
//					
//	IO.writeSubjectHeaderInFile("data/taskB2_climate_info.csv", subject);
//	IO.writeDataToFile("data/taskB2_climate_info.csv", columnHeader, theWeatherList);
//					
//	theWeatherList.clear();
//
//	System.out.println();
//	
//	
//	//
//	//B2 HIGHEST TOP 10
//	//
//	System.out.println("B-2 ) Top 10 Countries with the Highest Temperatures...");
//	
//	subject = "Task B2 : Top 10 Countries with the Highest Temperatures";
//	theWeatherList = allCountriesGetTop10HighestTemp();
//					
//	IO.writeSubjectHeaderInFile("data/taskB2_climate_info.csv", subject);
//	IO.writeDataToFile("data/taskB2_climate_info.csv", columnHeader, theWeatherList);
//
//	theWeatherList.clear();
//
//	System.out.println();
//		
//	//
//	//C1 TOP 10
//	//
//	System.out.println("C-1 ) Top 10 Countries with The Greatest Change in Temperatures Between 2 Years ");
//		
//	done = false;
//		
//	while(! done)
//	{
//		try 
//		{
//			scanner = new Scanner(System.in);
//			columnHeader = "Temperature Delta,Year Delta,Month,Country,Country_Code";
//				
//			System.out.print("1 ) Please enter a [Month] in numeric form \n		(1 = Jan, 12 = Dec) : ");
//			month = scanner.nextInt();
//						
//			System.out.print("2 ) Please enter a [Min Year] : ");
//			int minYear = scanner.nextInt();
//				
//			System.out.print("3 ) Please enter a [Max Year] : ");
//			int maxYear = scanner.nextInt();
//				
//			subject = "Task C1 : Top 10 Countries with the Greatest Temperature Differences in " 
//					+ months[month%12] + " from " + minYear + "-" + maxYear;
//			theWeatherList = allCountriesTop10TempDelta(month, minYear, maxYear);
//					
//			IO.writeSubjectHeaderInFile("data/taskC1_climate_info.csv", subject);
//			IO.writeDataToFile("data/taskC1_climate_info.csv", columnHeader, theWeatherList);
//					
//			done = true;
//			theWeatherList.clear();
//		}
//		catch (IllegalArgumentException x)
//		{
//			System.out.println(x.getMessage());
//		}
//		catch (InputMismatchException ex ){
//			System.out.println("Input for month/year must be an integer, try again...");
//		}
//		catch ( IndexOutOfBoundsException exception)
//		{
//			System.out.println(exception.getMessage());
//		}
//			
//		System.out.println();
//	}
//	
}
