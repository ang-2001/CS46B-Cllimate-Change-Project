package test;

import test.Temperature;

public class Temperature implements ITemperature, Comparable<Temperature>{
	private double 		temperature;
	private int 		year;
	private String 		month;
	private String 		country;
	private String 		code;
	
	
	//
	// constructor that takes in temperature, year, month, country, and code
	//
	// initializes those fields
	//
	public Temperature(double temperature, int year, String month, String country, String code)
	{
		this.temperature 	= temperature;
		this.year 			= year; 
		this.month 			= month;
		this.country 		= country;
		this.code 			= code;
	}
	
	
	@Override
	public String getCountry() 
	{
		return this.country;
	}
	
	
	
	@Override
	public String getCountry3LetterCode()
	{
		return this.code;
	}
	
	
	@Override
	public String getMonth()
	{
		return this.month;
	}
	
	
	@Override
	public int getYear()
	{
		return this.year;
	}
	
	
	//
	// Temperatures originally in Celsius
	// if getFahrenheit is true, converts temperature to Fahrenheit
	// else remains in Celsius
	// returns temperature, rounded to 2 decimal places
	//
	@Override
	public double getTemperature(boolean getFahrenheit)
	{
		// conversion formula found on Google conversion tool
		if ( getFahrenheit )
		{
			return (temperature * (9/5)) + 32;

		}
		
		return temperature;
	}
	
	
	@Override
	public int compareTo(Temperature that) {
		int temperatureDiff = (int) Math.signum(this.temperature - that.temperature);
		int countryDiff 	= this.country.compareTo(that.country);
		int yearDiff 		= (int) Math.signum(this.year - that.year);
		int monthDiff		= this.month.compareTo(that.month);
		int codeDiff 		= this.code.compareTo(that.code);
		
		if ( temperatureDiff != 0 )
		{
			return temperatureDiff;
		} 
		else if( countryDiff != 0 )
		{
			return countryDiff;
		}
		else if ( yearDiff != 0 )
		{
			return yearDiff;
		} 
		else if ( monthDiff != 0 )
		{
			return monthDiff;
		}
		else if ( codeDiff != 0 )
		{
			return codeDiff;
		}
		
		return 0;
	}
	
	
	public boolean equals(Object x)
	{
		Temperature that = (Temperature) x;
		
		if ( this.compareTo(that) != 0 )
		{
			return false;
		}
		
		return true;
	}
	
	
	public int hashCode()
	{
		return ( (int) temperature) + country.hashCode() 
				+ year + month.hashCode() + code.hashCode();
	}
	
	
	public int compareByCountry(Temperature that)
	{
		return this.country.compareTo(that.country);
	}
	
	
	//
	// Prints all data stored in Temperature in formatted form:
	// [temp in Celsius(C)],[temp in Fahrenheit(F)],[year],[month],[country],[code]
	// Temperatures rounded to 2 decimal places 
	//
	public String toString()
	{
		double roundedCelsius 		= Math.round(getTemperature(false) * 100.0) / 100.0;
		double roundedFahrenheit 	= Math.round(getTemperature(true) * 100.0) / 100.0;
		
		return roundedCelsius + "(C) " + roundedFahrenheit + "(F)," 
				+ year + "," + month + "," + country + "," + code;
	}
}
