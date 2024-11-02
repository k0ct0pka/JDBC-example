package configure;

import jakarta.servlet.ServletContext;
import org.apache.commons.fileupload2.core.DiskFileItemFactory;
import org.apache.commons.fileupload2.jakarta.servlet6.JakartaFileCleaner;
import org.apache.commons.io.FileCleaningTracker;

import java.io.File;

public class FileUploaderConfig {
    public static DiskFileItemFactory newDiskFileItemFactory(ServletContext context,
                                                             File repository) {
        FileCleaningTracker fileCleaningTracker = JakartaFileCleaner.getFileCleaningTracker(context);
        return new DiskFileItemFactory.Builder()
                .setFileCleaningTracker(fileCleaningTracker)
                .setBufferSize(DiskFileItemFactory.DEFAULT_THRESHOLD)
                .setPath(repository.getPath()).get();
    }
}
