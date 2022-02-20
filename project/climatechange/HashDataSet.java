package climatechange;

import java.util.ArrayList;
import java.util.HashSet;

public class HashDataSet<ITemperature> extends HashSet<ITemperature>{
	private static final long serialVersionUID = 1L;

	
	//
	// default constructor
	//
	public HashDataSet()
	{
		super();
	}
	
	
	//
	// constructor builds a HashDataSet from ArrayList
	//
	public HashDataSet ( ArrayList<ITemperature> list)
	{
		super(list);
	}
	
	
	//
	// returns the ArrayList containing the current elements of HashDataSet
	// no particular order
	//
	public ArrayList<ITemperature> getList()
	{
		return new ArrayList<ITemperature>(this);

	}
}
