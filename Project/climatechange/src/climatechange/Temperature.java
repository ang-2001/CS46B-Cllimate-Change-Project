package climatechange;

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
	
	
	//
	// constructor takes in 2 different temperature objects
	// asserts that these two temperatures have the same country, month, and 3 letter code
	// initializes temperature and year from the absolute value of the difference between 
	//
	public Temperature(ITemperature temp1, ITemperature temp2)
	{
		assert  temp1.getCountry().equalsIgnoreCase( temp2.getCountry() ) &&
				temp1.getMonth().equalsIgnoreCase( temp2.getMonth() ) &&
				temp1.getCountry3LetterCode().equalsIgnoreCase( temp2.getCountry3LetterCode() );
		
		this.temperature 	= Math.abs(temp1.getTemperature(false) - temp2.getTemperature(false));
		this.year 			= Math.abs(temp1.getYear() - temp2.getYear());
		this.month 			= temp1.getMonth();
		this.country 		= temp1.getCountry();
		this.code			= temp1.getCountry3LetterCode();
	}
	
	
	//
	//getter method for country
	//
	@Override
	public String getCountry() 
	{
		return this.country;
	}
	
	
	//getter Method for 3 letter code
	@Override
	public String getCountry3LetterCode()
	{
		return this.code;
	}
	
	
	//getter method for month
	@Override
	public String getMonth()
	{
		return this.month;
	}
	
	
	// getter method for year
	@Override
	public int getYear()
	{
		return this.year;
	}
	
	
	//
	// Temperatures originally in Celsius
	// if getFahrenheit is true, converts temperature to Fahrenheit
	// else remains in Celsius
	//
	@Override
	public double getTemperature(boolean getFahrenheit)
	{
		// conversion formula found on Google conversion tool
		if ( getFahrenheit )
		{
			return (temperature * (9.0/5.0)) + 32.0;
		}
		
		return temperature;
	}
	
	
	//
	// compareTo method for treeSets
	// compares temp values, if they're the same,
	// then compares country, then year, then month, then country code
	//
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
	
	
	//
	// checks equality of two temperature objects
	// uses the compareTo method implemented above
	//
	public boolean equals(Object x)
	{
		Temperature that = (Temperature) x;
		
		if ( this.compareTo(that) != 0 )
		{
			return false;
		}
		
		return true;
	}
	
	
	//
	// hashcodes method gets the sum of the hashcodes of all fields
	//
	//
	public int hashCode()
	{
		return ( (int) temperature) + country.hashCode() 
				+ year + month.hashCode() + code.hashCode();
	}
	
	
	//
	// Prints all data stored in Temperature in formatted form:
	// [temp in Celsius(C)],[temp in Fahrenheit(F)],[year],[month],[country],[code]
	// Temperatures rounded to 2 decimal places 
	//
	// rounding method from slides and online forum:
	// https://stackoverflow.com/questions/27832131/round-off-a-double-while-maintaining-the-trailing-zero
	//
	public String toString()
	{
		String roundedCelsius 		= String.format("%.2f", getTemperature(false));
		String roundedFahrenheit 	= String.format("%.2f", getTemperature(true));
		
		return roundedCelsius + "(C) " + roundedFahrenheit + "(F), " 
				+ year + ", " + month + ", " + country + ", " + code;
	}
}
