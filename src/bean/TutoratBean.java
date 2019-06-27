package bean;

import entity.Tutorat;

import java.io.Serializable;
import java.util.Collection;
import javax.faces.bean.*;


@ManagedBean(name="tutoratBean")
@SessionScoped
public class TutoratBean implements Serializable {

    private static final long serialVersionUID = 6L;

    private Collection<Tutorat> tutorats;
    private Tutorat tutorat;

    public Collection<Tutorat> getTutorats() {
        return tutorats;
    }

    public void setTutorats(Collection<Tutorat> tutorats) {
        this.tutorats = tutorats;
    }

    public Tutorat getTutorat() {

        return tutorat;
    }

    public void setTutorat(Tutorat tutorat) {
        this.tutorat = tutorat;
    }
}
