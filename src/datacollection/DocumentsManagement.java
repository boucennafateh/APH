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
    private final int BLOCS_NBR;
    private final int VERSIONS_NBR;
    private final boolean dummy;
    private final boolean grouping;
    private final int grouping_step = 100;
    private final int dummy_per_document;

    public DocumentsManagement(int dataCollectionSize, int blocs_nbr, int versions_nbr, int dummy_per_document, boolean dummy, boolean grouping) {
        this.dataCollectionSize = dataCollectionSize;
        map = new HashMap<>(dataCollectionSize);
        BLOCS_NBR = blocs_nbr;
        VERSIONS_NBR = versions_nbr;
        this.dummy = dummy;
        this.grouping = grouping;
        this.dummy_per_document = dummy_per_document;

    }

    public void mapPopulating() {

        for (int i = 1; i <= dataCollectionSize; i++) {
            int bloc_indx = 1;
            Document document = new Document(i);
            while (bloc_indx <= BLOCS_NBR) {
                int version_indx = 1;
                Bloc bloc = new Bloc(bloc_indx);
                while (version_indx <= VERSIONS_NBR) {
                    Version version = new Version(version_indx);
                    bloc.addVersion(version);
                    version_indx++;
                }
                //System.out.println(bloc.getVersionSet().size());
                document.addBloc(bloc);
                bloc_indx++;
            }
            map.put(i, document);
        }
        System.out.println("Map populating done");
    }

    public void documentAccesses(int nbrOfTime) {

        int accessedDocumentId;
        int indx = 0;
        for (int i = 0; i < nbrOfTime; i++) {


            //simulating accesses
            if (i % 4 == 0)
                accessedDocumentId = Tools.getRandom(dataCollectionSize);
            else if (i % 3 == 0)
                accessedDocumentId = Tools.getRandom(dataCollectionSize * 3 / 4);
            else if (i % 2 == 0)
                accessedDocumentId = Tools.getRandom(dataCollectionSize / 2);
            else
                accessedDocumentId = Tools.getRandom(dataCollectionSize / 4);

            //save result
            Document accessedDocument = map.get(accessedDocumentId);
            accessedDocument.incrementNbrAccesses();

            for (Bloc bloc : accessedDocument.getBlocSet()) {
                bloc.getRandomVersion().incrementAccess();
                indx++;
            }
        }
        System.out.print("index = " + indx);


        // scrambling technique
        if (dummy) {

            for (int i = 0; i < nbrOfTime * dummy_per_document; i++) {


                accessedDocumentId = Tools.getRandom(dataCollectionSize);

                //save result
                Document accessedDocument = map.get(accessedDocumentId);

                for (Bloc bloc : accessedDocument.getBlocSet()) {
                    bloc.getRandomVersion().incrementAccess();
                }
            }

        }

        // grouping technique
        if (grouping) {
                for (Integer indice : map.keySet())
                    for (int i = 0; i < map.get(indice).getNbrAccesses(); i++)
                        for (int j = 0; j < dummy_per_document; j++) {
                            int k = ((j + 1) * grouping_step + indice);
                            if (k > dataCollectionSize)
                                k = k % dataCollectionSize;
                            Document dummy_document = map.get(k);
                            System.out.println("k = " + k);
                            for (Bloc bloc : dummy_document.getBlocSet()) {
                                bloc.getRandomVersion().incrementAccess();
                            }
                        }
        }

        System.out.println("Accessing document done");

    }

    public void saveResult(String docFile, String versionFile) throws IOException {

        BufferedWriter writer = new BufferedWriter(new FileWriter(docFile));
        BufferedWriter writer2 = new BufferedWriter(new FileWriter(versionFile));
        Integer indice = 1;

        for (Document document : this.map.values()) {
            writer.write(document.getId().toString());
            writer.append("            ");
            writer.append(document.getNbrAccesses().toString());
            writer.append("\n");
            System.out.println(document.getBlocSet().size());

            for (Bloc bloc : document.getBlocSet())
                for (Version version : bloc.getVersionSet()) {
                    //System.out.println("writing ...");
                    writer2.write((indice++).toString());
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
