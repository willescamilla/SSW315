package Lab10;

/**
 * Wrapper class for use with generic data structures.
 * Mimics Integer.
 * In Java 1.2, you can use Integer if Comparable is needed.
 * @author Mark Allen Weiss
 */
public final class MyString implements Comparable, Hashable
{
    /**
     * Construct the MyInteger object with initial value 0.
     */
    public MyString( )
    {
        this( "" );
    }

    /**
     * Construct the MyInteger object.
     * @param x the initial value.
     */
    public MyString( String x )
    {
        value = x;
    }

    /**
     * Gets the stored int value.
     * @return the stored value.
     */
    public String stringValue( )
    {
        return value;
    }

    /**
     * Implements the toString method.
     * @return the String representation.
     */
    public String toString( )
    {
        return value;
    }

    /**
     * Implements the compareTo method.
     * @param rhs the other MyInteger object.
     * @return 0 if two objects are equal;
     *     less than zero if this object is smaller;
     *     greater than zero if this object is larger.
     * @exception ClassCastException if rhs is not
     *     a MyInteger.
     */
    public int compareTo( Comparable rhs )
    {
        return value.compareTo(((MyString)rhs).value);
    }

    /**
     * Implements the equals method.
     * @param rhs the second MyInteger.
     * @return true if the objects are equal, false otherwise.
     * @exception ClassCastException if rhs is not
     *     a MyInteger.
     */
    public boolean equals( Object rhs )
    {
        return rhs != null && value.equals(((MyString)rhs).value);
    }

    /**
     * Implements the hash method.
     * @param tableSize the hash table size.
     * @return a number between 0 and tableSize-1.
     */
    public int hash( int tableSize )
    {
    	int hashVal = 0;
    	
    	for( int i = 0; i < value.length( ); i++ )
			hashVal = 37 * hashVal + value.charAt( i );

		hashVal %= tableSize;
		if( hashVal < 0 )
			hashVal += tableSize;

		return hashVal;
    }
    
	


    private String value;
    
}
