package ekebookreview;

import com.sun.org.apache.xml.internal.security.utils.Base64;
import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="LoginBean")
@RequestScoped
public class LoginBean {

    public LoginBean() {
        this.successful = false;
        this.error = false;
    }

    private String username;
    private String password;
    private boolean successful;
    private boolean error;
    private String hashed;
    private String salt;
    private String errorMessage;

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
    public boolean isSuccessful() {
        return successful;
    }

    public void setSuccessful(boolean successful) {
        this.successful = successful;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    
    public void loginButtonAction(){
        if(username.isEmpty()||password.isEmpty()){
            error=true;
            return;
        }
        int id = getHashSalt();
        String tempHash = hashPassword();
        errorMessage = salt;
        if(tempHash.equals(hashed)){
            successful=true;
            new User(id);
        }
        else
            error=true;
    }
    private String hashPassword(){
        try{
            SecureRandom random = new SecureRandom();
            byte[] saltTemp;
            if(salt==null){
                saltTemp = new byte[16];
                random.nextBytes(saltTemp);
            }
            else
                saltTemp = Base64.decode(salt);
            
            KeySpec spec = new PBEKeySpec(password.toCharArray(), saltTemp, 65536, 128);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            
            byte[] hash = factory.generateSecret(spec).getEncoded();
            return Base64.encode(hash);
        }
        catch(Exception e){
            return null;
        }
    }
    private int getHashSalt(){
        int id;
        try{
            DBConnection db = new DBConnection();
            db.stmt = db.conn.createStatement();
            ResultSet results = db.stmt.executeQuery("SELECT password,salt,id FROM users WHERE username='"+username+"'");
            results.next();
            hashed = results.getString("password");
            salt = results.getString("salt");
            id = results.getInt("id");
            results.close();
            db.stmt.close();
            return id;
        }
        catch(SQLException sqlExcept){
            sqlExcept.printStackTrace();
            
            return 0;
        }
    }
}
