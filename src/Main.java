import datacollection.DocumentsManagement;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        // you can change this parameters

        int dataCollectionSize = 1000; // the size of the data collection
        int accessesNbr = 100000; // the number of accesses to the data collection
        String docFile = "doc.dat"; // result when the approach is not applied
        String versionFile = "version.dat"; // result when the approach is applied
        final int BLOCS_NBR = 10 ; // number of blocks for a document
        final int VERSIONS_NBR = 10; // number of versions of a block
        boolean splitting = true; // separating + splitting techniques are applied
        boolean scrambling = true; // separating + splitting + scrambling techniques are applied
        boolean grouping = true; // separating + splitting + scrambling + grouping techniques are applied


        boolean isDummy = false; // don't change
        boolean isGrouping = false; // don't change

        if (splitting){
            isDummy = false;
            isGrouping = false;
        } else if (scrambling){
            isDummy = true;
            isGrouping = false;
        } else if (grouping) {
            isDummy = false;
            isGrouping = true;
        }

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
