package ekebookreview;
import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

//this class might not have to be a bean
@ManagedBean(name="User")
@SessionScoped
public class User {
    
    int id;
    String name;
    String username;
    DBConnection db;
    boolean isGuest;
    
    public User(int id) {
        db = new DBConnection();
        this.id = id;
        if(id==0){
            isGuest = true;
        }
        else {
            getUserInfo();
            isGuest = false;
        }
    }
    
    private void getUserInfo(){
        try{
            db.stmt = db.conn.createStatement();
            ResultSet results = db.stmt.executeQuery("SELECT name,username FROM users WHERE id="+id);
            results.next();
            name = results.getString("name");
            username = results.getString("username");
            results.close();
            db.stmt.close();
        }
        catch(SQLException sqlExcept){
            sqlExcept.printStackTrace();
        }
    }
}
