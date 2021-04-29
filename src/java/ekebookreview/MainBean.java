package ekebookreview;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="Main")
@SessionScoped
public class MainBean {

    User user;
    
    public MainBean() {
        this.user = new User(0);
    }
}
