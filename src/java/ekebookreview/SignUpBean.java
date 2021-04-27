package ekebookreview;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="SignUpBean")
@RequestScoped
public class SignUpBean {

    public SignUpBean() {
        this.successful = false;
        this.error = false;
    }

    private String username;
    private String email;
    private String name;
    private String age;
    private String password;
    private String passworda;
    private boolean successful;
    private boolean error;
    
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
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAge() {
        return age;
    }
    public void setAge(String age) {
        this.age = age;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getPassworda() {
        return passworda;
    }
    public void setPassworda(String passworda) {
        this.passworda = passworda;
    }
    
    public void signupButtonAction(){
        if(!password.equals(passworda)){
            error=true;
        }
        else 
            successful=false;
    }
}
