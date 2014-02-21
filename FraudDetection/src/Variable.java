
public class Variable 
{
	//================================================================================
    // Properties
    //================================================================================
	private int _index;
	private String _desc;
	private boolean _value;
	

	//================================================================================
    // Constructors
    //================================================================================
	public Variable()
	{
	}
	
	public Variable(Variable v)
	{
		_desc = v.getDesc();
	}
	
	@Override
	public int hashCode() 
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((_desc == null) ? 0 : _desc.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) 
	{
		if (this == obj)
		{
			return true;
		}
		if (obj == null)
		{
			return false;
		}
		if (getClass() != obj.getClass())
		{
			return false;
		}
		
		Variable other = (Variable) obj;
		if (_desc == null) 
		{
			if (other._desc != null)
				return false;
		} 
		else if (!_desc.equals(other._desc))
		{
			return false;
		}
		return true;
	}
	
	@Override
	public String toString()
	{
		return _desc;
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
	public boolean getValue() {
		return _value;
	}

	public void setValue(boolean _value) {
		this._value = _value;
	}
}
