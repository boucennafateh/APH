package datacollection;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Document {

    private int id;
    private int nbrAccesses;
    private Set<Bloc> blocSet;

    public Document(int id) {
        this.id = id;
        this.nbrAccesses = 0;
        blocSet = new HashSet<>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getNbrAccesses() {
        return nbrAccesses;
    }

    public void setNbrAccesses(int nbrAccesses) {
        this.nbrAccesses = nbrAccesses;
    }

    public Set<Bloc> getBlocSet() {
        return blocSet;
    }

    public void setBlocSet(Set<Bloc> blocSet) {
        this.blocSet = blocSet;
    }

    public void incrementNbrAccesses(){
        this.nbrAccesses++;
    }

    public void addBloc(Bloc bloc){
        blocSet.add(bloc);
        bloc.setDocument(this);
    }
}
