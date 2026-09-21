
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // Create the sample file system
        Folder root = new Folder("Root");

        Folder documents = new Folder("Documents");
        Folder pictures = new Folder("Pictures");
        Folder vacations = new Folder("Vacations");

        documents.addItem(new FileItem("homework.txt", 100));
        documents.addItem(new FileItem("notes.txt", 50));

        vacations.addItem(new FileItem("beach.jpg", 500));
        vacations.addItem(new FileItem("sunset.jpg", 750));

        pictures.addItem(vacations);

        root.addItem(documents);
        root.addItem(pictures);

        boolean running = true;

        while (running) {

            System.out.println("\n==============================");
            System.out.println(" VIRTUAL FILE SYSTEM ANALYZER");
            System.out.println("==============================");
            System.out.println("1. Display File System Structure");
            System.out.println("2. Add File to a Folder");
            System.out.println("3. Add Subfolder");
            System.out.println("4. Run Recursive Audit");
            System.out.println("5. Run Iterative Audit & Verification");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {

                case "1":

                    System.out.println("\nFile System Structure:");

                    FileSystemAnalyzer.printHierarchy(root, "");

                    break;

                case "2":

                    System.out.print("Enter target folder name: ");
                    String targetName = scanner.nextLine().trim();

                    Folder targetFolder =
                            FileSystemAnalyzer.findFolder(
                                    root, targetName);

                    if (targetFolder == null) {

                        System.out.println("Folder not found.");
                        break;
                    }

                    System.out.print("Enter new file name: ");
                    String fileName = scanner.nextLine().trim();

                    if (fileName.isEmpty()) {

                        System.out.println("File name cannot be empty.");
                        break;
                    }

                    System.out.print("Enter file size in KB: ");
                    String sizeInput = scanner.nextLine().trim();

                    try {

                        int fileSize = Integer.parseInt(sizeInput);

                        if (fileSize < 0) {

                            System.out.println(
                                    "File size cannot be negative.");

                        } else {

                            targetFolder.addItem(
                                    new FileItem(fileName, fileSize));

                            System.out.println(
                                    "File added successfully!");
                        }

                    } catch (NumberFormatException e) {

                        System.out.println(
                                "Invalid size. Please enter a valid integer.");
                    }

                    break;

                case "3":

                    System.out.print("Enter parent folder name: ");
                    String parentName = scanner.nextLine().trim();

                    Folder parentFolder =
                            FileSystemAnalyzer.findFolder(
                                    root, parentName);

                    if (parentFolder == null) {

                        System.out.println("Parent folder not found.");
                        break;
                    }

                    System.out.print("Enter new subfolder name: ");
                    String newFolderName = scanner.nextLine().trim();

                    if (newFolderName.isEmpty()) {

                        System.out.println(
                                "Folder name cannot be empty.");
                        break;
                    }

                    parentFolder.addItem(
                            new Folder(newFolderName));

                    System.out.println(
                            "Subfolder added successfully!");

                    break;

                case "4":

                    System.out.println("\nRECURSIVE AUDIT");

                    int recursiveCount =
                            FileSystemAnalyzer.countFilesRecursive(root);

                    int totalSize =
                            FileSystemAnalyzer.calculateTotalSizeRecursive(root);

                    FileItem largest =
                            FileSystemAnalyzer.findLargestFileRecursive(root);

                    System.out.println(
                            "Total files: " + recursiveCount);

                    System.out.println(
                            "Total storage: " + totalSize + " KB");

                    if (largest != null) {

                        System.out.println(
                                "Largest file: " + largest.getName());

                        System.out.println(
                                "Largest file size: "
                                        + largest.getSizeInKB() + " KB");

                    } else {

                        System.out.println("No files found.");
                    }

                    break;

                case "5":

                    System.out.println("\nITERATIVE AUDIT");

                    int iterativeCount =
                            FileSystemAnalyzer.countFilesIterative(root);

                    int recursiveResult =
                            FileSystemAnalyzer.countFilesRecursive(root);

                    System.out.println(
                            "Iterative file count: " + iterativeCount);

                    System.out.println(
                            "Recursive file count: " + recursiveResult);

                    if (iterativeCount == recursiveResult) {

                        System.out.println(
                                "Verification: Counts match!");

                    } else {

                        System.out.println(
                                "Verification: Counts DO NOT match!");
                    }

                    break;

                case "6":

                    System.out.println(
                            "Exiting Virtual File System Analyzer...");

                    running = false;

                    break;

                default:

                    System.out.println(
                            "Invalid choice. Please select 1-6.");

                    break;
            }
        }

        scanner.close();
    }
}