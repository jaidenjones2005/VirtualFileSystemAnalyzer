import java.util.Stack;
public class FileSystemAnalyzer {

    // ==========================================
    // PHASE 1: Recursive File Counter
    // ==========================================

    public static int countFilesRecursive(FileSystemItem item) {

        if (item instanceof FileItem) {
            return 1;
        }

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

    // ==========================================
    // PHASE 2: Recursive Storage Calculation
    // ==========================================

    public static int calculateTotalSizeRecursive(FileSystemItem item) {

        if (item instanceof FileItem) {
            return item.getSizeInKB();
        }

        if (item instanceof Folder) {

            Folder folder = (Folder) item;
            int totalSize = 0;

            for (FileSystemItem child : folder.getItems()) {
                totalSize += calculateTotalSizeRecursive(child);
            }

            return totalSize;
        }

        return 0;
    }

    // ==========================================
    // PHASE 2: Find the Largest File
    // ==========================================

    public static FileItem findLargestFileRecursive(FileSystemItem item) {

        if (item instanceof FileItem) {
            return (FileItem) item;
        }

        if (item instanceof Folder) {

            Folder folder = (Folder) item;
            FileItem largest = null;

            for (FileSystemItem child : folder.getItems()) {

                FileItem childMax = findLargestFileRecursive(child);

                if (childMax != null &&
                        (largest == null ||
                                childMax.getSizeInKB() > largest.getSizeInKB())) {

                    largest = childMax;
                }
            }

            return largest;
        }

        return null;
    }

    // ==========================================
    // PHASE 3: Iterative File Counter
    // ==========================================

    public static int countFilesIterative(Folder rootFolder) {

        Stack<FileSystemItem> stack = new Stack<>();

        // Start with the root folder
        stack.push(rootFolder);

        int fileCount = 0;

        // Continue until all items have been visited
        while (!stack.isEmpty()) {

            FileSystemItem current = stack.pop();

            if (current instanceof FileItem) {

                fileCount++;

            } else if (current instanceof Folder) {

                Folder folder = (Folder) current;

                for (FileSystemItem child : folder.getItems()) {
                    stack.push(child);
                }
            }
        }

        return fileCount;
    }
}