package climatechange;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.TreeSet;

public class TreeDataSet<ITemperature> extends TreeSet<ITemperature>{
	private static final long serialVersionUID = 1L;

	
	//
	// default constructor
	//
	public TreeDataSet()
	{
		super();
	}
	
	
	//
	// constructor builds a TreeDataSet from ArrayList, as a TreeSet would
	//
	public TreeDataSet ( ArrayList<ITemperature> list)
	{
		super(list);
	}
	
	
	//
	// returns an ArrayList containing the current elements of TreeDataSet
	// in the exact order they were in
	//
	public ArrayList<ITemperature> getSortedList()
	{
		return new ArrayList<ITemperature>(this);

	}
	
}
