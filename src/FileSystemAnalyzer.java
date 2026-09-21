
public class FileSystemAnalyzer {

    public static int countFilesRecursive(FileSystemItem item) {

        // Base case: an individual file counts as 1
        if (item instanceof FileItem) {
            return 1;
        }

        // Recursive case: search through a folder
        if (item instanceof Folder) {

            Folder folder = (Folder) item;
            int fileCount = 0;

            for (FileSystemItem child : folder.getItems()) {
                fileCount += countFilesRecursive(child);
            }

            return fileCount;
        }

        return 0;
    }
}