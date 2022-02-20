package climatechange;

public interface ITemperature {
	
	public String getCountry(); // returns name of the country
	public String getCountry3LetterCode(); // return 3-letter code of a country
	public String getMonth(); // returns the month
	public int getYear(); // returns the year
	public double getTemperature(boolean getFahrenheit); // if true, returns temperature in fahrenheit, else returns in celsius 
}
