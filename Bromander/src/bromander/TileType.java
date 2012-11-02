package bromander;

public enum TileType {
	  Gas,
	  Liquid,
	  Solid,
	  Null;
	  
	  public static TileType parse(String str)
	  {
	    if (str.equalsIgnoreCase("Solid"))
	    	return TileType.Solid;
	    else if (str.equalsIgnoreCase("Liquid"))
	    	return TileType.Liquid;
	    else if (str.equalsIgnoreCase("Gas"))
	    	return TileType.Gas;
	    else
	        return TileType.Null;
	  }
}
