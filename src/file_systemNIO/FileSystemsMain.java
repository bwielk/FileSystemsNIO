package file_systemNIO;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class FileSystemsMain {

    public static void main(String[] args) {

        try{
            //CREATING FILES AND DIRECTORIES
            Path dirToCreate = FileSystems.getDefault().getPath("Examples\\newDir4");
            Path fileToCreate = FileSystems.getDefault().getPath("Examples\\newlyCreatedFile2.txt");
            if(!Files.exists(fileToCreate)){
                Files.createFile(fileToCreate);
            }
            if(!Files.exists(dirToCreate)){
                Files.createDirectories(dirToCreate);
            }

            //COPYING EXISTING FILES
            Path sourceFile = FileSystems.getDefault().getPath("Examples\\file1.txt");
            Path copyFile = FileSystems.getDefault().getPath("Examples\\file1copy.txt");
            if(!Files.exists(copyFile)){
                Files.copy(sourceFile, copyFile);
            }else{
                Files.copy(sourceFile, copyFile, StandardCopyOption.REPLACE_EXISTING);
            }

            sourceFile = FileSystems.getDefault().getPath("Examples\\dir1");
            copyFile = FileSystems.getDefault().getPath("Examples\\dir1CopyWithoutContent");
            if(!Files.exists(copyFile)){
                Files.copy(sourceFile, copyFile);
            }

            //MOVING EXISTING FILES
            Path fileToMove = FileSystems.getDefault().getPath("Examples\\file1copy.txt");
            Path destination = FileSystems.getDefault().getPath("Examples\\dir1\\", "file1copy.txt");
            if(!Files.exists(fileToMove)){
                Files.move(fileToMove, destination);
            }

            //MOVING EXISTING FILES AND RENAMING THEM
            destination = FileSystems.getDefault().getPath("Examples\\dir1\\file1copy2.txt");
            if(!Files.exists(destination)){
                Files.move(fileToMove, destination);
            }

            //DELETING
            Path fileToDelete = FileSystems.getDefault().getPath("Examples\\dir1\\file1copy2.txt");
            if(Files.exists(fileToDelete)){
                Files.delete(fileToDelete);
            }

            //GETTING ATTRIBUTES AND PROPERTIES
            Path fileToGet = FileSystems.getDefault().getPath("Examples\\dir2\\file1.txt");
            long size = Files.size(fileToGet);

            System.out.println("SIZE: " + size + "\n" +
                                "LAST MODIFIED: " + Files.getLastModifiedTime(fileToGet) + "\n" +
                                "FILE STORE: " + Files.getFileStore(fileToGet) + "\n" +
                                "FILE OWNER: " + Files.getOwner(fileToGet));

            BasicFileAttributes attr = Files.readAttributes(fileToGet, BasicFileAttributes.class);
            System.out.println("\n\n NEW ATTRIBUTES \n\n" +
                                "SIZE: " + attr.size() + "\n" +
                                "REGULAR FILE?: " + attr.isRegularFile() + "\n" +
                                "DIRECTORY?: " + attr.isDirectory() + "\n" +
                                "DATE CREATED: " + attr.lastModifiedTime());

        }catch(IOException e){
            e.printStackTrace();
        }
        /////////////////////////////////////////////////////////////////////////////////////////
        /////////////////////////////////////////////////////////////////////////////////////////

        //PRINTING OUT THE CONTENTS OF THE FILES IN VARIOUS DIRECTORIES

        Path path;
        path = FileSystems.getDefault().getPath("workingDirectoryFile.txt");
        printFile(path);
        path = FileSystems.getDefault().getPath("Files","subdirectoryFile.txt");
        printFile(path);
        path = FileSystems.getDefault().getPath("..\\externalWorkingDirectory.txt");
        printFile(path);
        path = Paths.get("C:\\Users\\Blazej W\\IdeaProjects\\externalWorkingDirectory.txt"); //Absolute Path
        printFile(path);

        path = Paths.get(".");
        System.out.println(path.toAbsolutePath());

        //CHECKING FOR AN EXISTING PATH

        Path path3 = FileSystems.getDefault().getPath("nonExistentFile.txt");
        System.out.println(Files.exists(path3));
        System.out.println(Files.exists(path));
    }

    private static void printFile(Path path){
        try(BufferedReader fileReader = Files.newBufferedReader(path)){
            System.out.println("\n\n//////////////////////////////////////////////");
            String line;
            while((line = fileReader.readLine()) != null){
                System.out.println(line);
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}