package test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.TreeSet;

public class TreeDataSet<ITemperature> extends TreeSet<ITemperature>{
	private static final long serialVersionUID = 1L;
	
	
	//remove getdata
	public ArrayList<ITemperature> getSorted()
	{
		return new ArrayList<ITemperature>(this);

	}
	
}
