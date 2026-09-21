
public class Main {

    public static void main(String[] args) {

        // Create the root folder
        Folder root = new Folder("Root");

        // Create subfolders
        Folder documents = new Folder("Documents");
        Folder pictures = new Folder("Pictures");
        Folder vacations = new Folder("Vacations");

        // Add files to Documents
        documents.addItem(new FileItem("homework.txt", 100));
        documents.addItem(new FileItem("notes.txt", 50));

        // Add files to Vacations
        vacations.addItem(new FileItem("beach.jpg", 500));
        vacations.addItem(new FileItem("sunset.jpg", 750));

        // Nest Vacations inside Pictures
        pictures.addItem(vacations);

        // Add Documents and Pictures to Root
        root.addItem(documents);
        root.addItem(pictures);

        // ==========================================
        // PHASE 1: Recursive File Counter
        // ==========================================

        int totalFiles =
                FileSystemAnalyzer.countFilesRecursive(root);

        System.out.println("Total files: " + totalFiles);

        // ==========================================
        // PHASE 2: Recursive Storage Calculation
        // ==========================================

        int totalSize =
                FileSystemAnalyzer.calculateTotalSizeRecursive(root);

        System.out.println("Total storage: " + totalSize + " KB");

        // ==========================================
        // PHASE 2: Find the Largest File
        // ==========================================

        FileItem largest =
                FileSystemAnalyzer.findLargestFileRecursive(root);

        if (largest != null) {

            System.out.println("Largest file: " + largest.getName());

            System.out.println("Largest file size: "
                    + largest.getSizeInKB() + " KB");

        } else {

            System.out.println("No files found.");

        }
    }
}