package Rippling.rule;
public class Violation{
    private final String message;
    public Violation(String message) {
        this.message = message;
    }
    public static Violation of(String message){
        return new Violation(message);
    }
    public String getMessage(){
        return message;
    }
}
