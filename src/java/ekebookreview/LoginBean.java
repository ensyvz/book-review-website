package ekebookreview;

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
        getHashSalt();
        String tempHash = hashPassword();
        if(tempHash.equals(hashed))
            successful=true;
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
                saltTemp = salt.getBytes();
            
            KeySpec spec = new PBEKeySpec(password.toCharArray(), saltTemp, 65536, 128);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            
            byte[] hash = factory.generateSecret(spec).getEncoded();
            return new String(hash);
        }
        catch(Exception e){
            return null;
        }
    }
    private boolean getHashSalt(){
        try{
            DBConnection db = new DBConnection();
            db.stmt = db.conn.createStatement();
            ResultSet results = db.stmt.executeQuery("SELECT password,salt FROM users WHERE username='"+username+"'");
            results.next();
            hashed = results.getString("password");
            salt = results.getString("salt");
            results.close();
            db.stmt.close();
            return true;
        }
        catch(SQLException sqlExcept){
            sqlExcept.printStackTrace();
            return false;
        }
    }
}
