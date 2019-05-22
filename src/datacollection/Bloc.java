package datacollection;

import utilities.Tools;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Bloc {

    private int id;
    private Document document;
    private List<Version> versionSet;

    public Bloc(int id) {
        this.id = id;
        versionSet = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public List<Version> getVersionSet() {
        return versionSet;
    }

    public void setVersionSet(List<Version> versionSet) {
        this.versionSet = versionSet;
    }

    public void addVersion(Version version){
        versionSet.add(version);
        version.setBloc(this);
    }

    public Version getRandomVersion(){
        int index = Tools.getRandom(this.versionSet.size())  - 1;
        return this.versionSet.get(index);
    }
}
