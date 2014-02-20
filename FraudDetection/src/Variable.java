
public class Variable 
{
	//================================================================================
    // Properties
    //================================================================================
	private int _index;
	private String _desc;
	
	//================================================================================
    // Constructors
    //================================================================================
	public Variable()
	{
	}
	
	//================================================================================
    // Overridden Methods
    //================================================================================
	@Override
	public boolean equals(Object o)
	{
		if (o != null && o instanceof Variable)
		{
			return ((Variable)o).getDesc() == this.getDesc();
		}
		
		return false;
	}
	
	//================================================================================
    // Accessors
    //================================================================================
	public String getDesc() {
		return _desc;
	}

	public void setDesc(String desc) {
		this._desc = desc;
	}

	public int getIndex() {
		return _index;
	}

	public void setIndex(int index) {
		this._index = index;
	}
}
