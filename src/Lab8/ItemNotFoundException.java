package Lab8;

/**
 * Exception class for item not found 
 * in Weiss's binary search tree removal.
 * @author jdalbey
 */
public class ItemNotFoundException extends RuntimeException
{
    /**
     * Construct this exception object.
     */
    public ItemNotFoundException( )
    {
        super( );
    }
    /**
     * Construct this exception object.
     * @param message the error message.
     */
    public ItemNotFoundException( String message )
    {
        super( message );
    }
}
