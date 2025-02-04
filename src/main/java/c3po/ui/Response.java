package c3po.ui;

/**
 * Represents a response to be displayed to the user.
 */
public class Response {

    /**
     * Empty response.
     */
    public static final Response EMPTY_RESPONSE = new Response("");

    private String message;

    /**
     * Constructs a Response.
     *
     * @param message Message to be displayed.
     */
    public Response(String message) {
        this.message = message;
    }

    /**
     * Returns the message to be displayed.
     *
     * @return Message to be displayed.
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * Prints the message to the console.
     */
    public void printMessage() {
        System.out.println(this.message);
    }
}
