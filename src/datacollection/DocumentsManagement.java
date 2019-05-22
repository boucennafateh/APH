package datacollection;

import utilities.Tools;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DocumentsManagement {

    private Map<Integer, Document> map;
    private int dataCollectionSize;
    private final int BLOCS_NBR ;
    private final int VERSIONS_NBR;

    public DocumentsManagement(int dataCollectionSize, int blocs_nbr, int versions_nbr) {
        this.dataCollectionSize = dataCollectionSize;
        map = new HashMap<>(dataCollectionSize);
        BLOCS_NBR = blocs_nbr;
        VERSIONS_NBR = versions_nbr;
    }

    public void mapPopulating(){

        for (int i = 1; i <= dataCollectionSize; i++) {
            int bloc_indx = 1;
            Document document = new Document(i);
            while(bloc_indx <= BLOCS_NBR){
                int version_indx = 1;
                Bloc bloc = new Bloc(bloc_indx);
                while (version_indx <= VERSIONS_NBR){
                    Version version = new Version(version_indx);
                    bloc.addVersion(version);
                    version_indx++;
                }
                System.out.println(bloc.getVersionSet().size());
                document.addBloc(bloc);
                bloc_indx++;
            }
            map.put(i, document);
        }
        System.out.println("Map populating done");
    }

    public void documentAccesses(int nbrOfTime){

        int accessedDocumentId;

        for (int i=0; i<nbrOfTime; i++) {

            //simulating accesses
            if (i % 2  == 1)
                accessedDocumentId = Tools.getRandom(dataCollectionSize) ;
            else
                accessedDocumentId = Tools.getRandom(dataCollectionSize / 2);

            //save result
            Document accessedDocument = map.get(accessedDocumentId);
            accessedDocument.incrementNbrAccesses();

            for (Bloc bloc : accessedDocument.getBlocSet()) {
                bloc.getRandomVersion().incrementAccess();
            }
        }

        System.out.println("Accessing document done");

    }

    public void saveResult(String docFile, String versionFile) throws IOException {

        BufferedWriter writer = new BufferedWriter(new FileWriter(docFile));
        BufferedWriter writer2 = new BufferedWriter(new FileWriter(versionFile));

        for (Document document : this.map.values()) {
            writer.write(document.getId().toString());
            writer.append("            ");
            writer.append(document.getNbrAccesses().toString());
            writer.append("\n");
            System.out.println(document.getBlocSet().size());

            for(Bloc bloc : document.getBlocSet())
                for (Version version : bloc.getVersionSet()) {
                    System.out.println("writing ...");
                    Integer id = version.getId() * version.getBloc().getId() * version.getBloc().getDocument().getId();
                    writer2.write(id.toString());
                    writer2.append("            ");
                    writer2.append(version.getAccessNbr().toString());
                    writer2.append("\n");
                }

        }

        writer.close();
        writer2.close();

        System.out.println("Saving document done");

    }
}
