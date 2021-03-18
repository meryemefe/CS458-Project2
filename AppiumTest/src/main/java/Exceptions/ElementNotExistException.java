package Exceptions;

public class ElementNotExistException extends AbstractException {

    public ElementNotExistException() {
        super("100001", "Element cannot be found. ");
    }

    public ElementNotExistException(String elementName) {
        super("100001", "Element cannot be found Element: " + elementName);
    }
}
