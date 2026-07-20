package networks.dto;
import java.io.Serializable;

public class RegistrationDTO implements Serializable {
    private final int playerId;
    private final String username;
    private final String password;
    private final String email;

    public RegistrationDTO(int playerId, String username, String email, String password){
        this.playerId = playerId;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public int getPlayerId(){
        return playerId;
    }
    public String getUsername(){
        return username;
    }
    public String getPassword() {
        return password;
    }
    public String getEmail() {
        return email;
    }
}
