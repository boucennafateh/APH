import datacollection.DocumentsManagement;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        int dataCollectionSize = 10;
        int accessesNbr = 20;
        String docFile = "doc.dat";
        String versionFile = "version.dat";
        final int BLOCS_NBR = 5 ;
        final int VERSIONS_NBR = 2;

        DocumentsManagement documentsManagement = new DocumentsManagement(dataCollectionSize, BLOCS_NBR, VERSIONS_NBR);
        documentsManagement.mapPopulating();
        documentsManagement.documentAccesses(accessesNbr);
        try {
            documentsManagement.saveResult(docFile, versionFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }




}
