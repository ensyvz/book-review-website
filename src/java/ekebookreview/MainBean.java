package ekebookreview;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="Main")
@SessionScoped
public class MainBean {

    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    public MainBean() {
        this.user = new User(0);
    }
    
    public void logout(){
        if(this.getUser().getId() != 0){
            this.user = new User(0);
        }
    }
}
