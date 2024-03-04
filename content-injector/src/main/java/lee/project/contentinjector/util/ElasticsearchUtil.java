package lee.project.contentinjector.util;

import lee.project.contentinjector.dto.FileSource;

public class ElasticsearchUtil {

    public static String getDocumentId(FileSource fileSource) {
        return fileSource.getTargetSeq() + "_" + fileSource.getUniqueSeq() + "_" + fileSource.getFileName();
    }
}
