package datacollection;

public class Version {

    private int id;
    private Bloc bloc;
    private int accessNbr;

    public Version(int id) {
        this.id = id;
        accessNbr = 0;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Bloc getBloc() {
        return bloc;
    }

    public void setBloc(Bloc bloc) {
        this.bloc = bloc;
    }

    public Integer getAccessNbr() {
        return accessNbr;
    }

    public void setAccessNbr(int accessNbr) {
        this.accessNbr = accessNbr;
    }

    public void incrementAccess(){
        this.accessNbr++;
    }
}
