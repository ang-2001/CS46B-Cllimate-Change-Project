package climatechange;

//
// A Class specifically for getting the delta of 2 temperature objects
//

public class DeltaTemperature extends Temperature{

	ITemperature temp1;
	ITemperature temp2;
	
	
	//
	// constructs a DeltaTemperature object from 2 Temperature objects
	// Initializes additional fields temp1 and temp2
	//
	public DeltaTemperature (ITemperature temp1, ITemperature temp2)
	{
		super(temp1,temp2);
		
		this.temp1 = temp1;
		this.temp2 = temp2;
		
	}
	
	
	//
	// Overrides getTemperature from Temperature, 
	// returns the Delta temperature of 2 Temp Objects(temp1 & temp2)
	// 
	// Necessary because,
	// the delta of 2 temps in Fahrenheit != the delta of 2 temps in Celsius converted to Fahrenheit
	//
	@Override
	public double getTemperature(boolean getFahrenheit)
	{
		// converts to fahrenheit then gets the absolute value of their difference
		if ( getFahrenheit )
		{
			return Math.abs(
					temp1.getTemperature(true) - temp2.getTemperature(true));
		}
		
		return super.getTemperature(false);
	}
	
}
