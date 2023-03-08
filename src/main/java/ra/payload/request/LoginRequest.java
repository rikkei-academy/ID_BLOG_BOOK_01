package ra.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;

public class LoginRequest {
    private String userName;
    private String passwords;

    public LoginRequest(String userName, String passwords) {
        this.userName = userName;
        this.passwords = passwords;
    }

    public LoginRequest() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPasswords() {
        return passwords;
    }

    public void setPasswords(String passwords) {
        this.passwords = passwords;
    }
}
