import datacollection.DocumentsManagement;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        int dataCollectionSize = 1000;
        int accessesNbr = 100000;
        String docFile = "doc.dat";
        String versionFile = "version.dat";
        final int BLOCS_NBR = 10 ;
        final int VERSIONS_NBR = 10;
        final boolean isDummy = false;
        final boolean isGrouping = true;
        final int dummy_per_document = 5;

        DocumentsManagement documentsManagement = new DocumentsManagement(dataCollectionSize, BLOCS_NBR, VERSIONS_NBR, dummy_per_document, isDummy, isGrouping);
        documentsManagement.mapPopulating();
        documentsManagement.documentAccesses(accessesNbr);
        try {
            documentsManagement.saveResult(docFile, versionFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }




}
