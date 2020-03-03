package feat.exception;

/**
 * @author user
 * @title: CustomErrorType
 * @projectName my_git
 * @description: TODO
 * @date 2019/4/29 14:46
 */
public class CustomErrorType {

    private int value;
    private String message;

    public CustomErrorType(int value, String message) {
        this.value = value;
        this.message = message;

    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "CustomErrorType{" +
                "value=" + value +
                ", message='" + message + '\'' +
                '}';
    }
}
