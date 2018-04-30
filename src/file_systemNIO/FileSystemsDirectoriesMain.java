package file_systemNIO;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.sql.SQLOutput;

public class FileSystemsDirectoriesMain {

    public static void main(String[] args) {

//        DirectoryStream.Filter<Path> filter = new DirectoryStream.Filter<Path>(){
//            public boolean accept(Path path) throws IOException{
//                return (Files.isRegularFile(path));
//            };
//        };
        DirectoryStream.Filter<Path> filter = p -> Files.isRegularFile(p);

        Path path = FileSystems.getDefault().getPath("Examples\\dir2");

        try(DirectoryStream<Path> contents = Files.newDirectoryStream(path, filter)){
            for(Path content : contents){
                System.out.println(content.getFileName());
            }
        }catch(IOException | DirectoryIteratorException e){
            e.printStackTrace();
        }

        String separator = File.separator;
        System.out.println("SEPARATOR: " + separator);
        separator = FileSystems.getDefault().getSeparator();
        System.out.println("SEPARATOR: " + separator);

        try{
            Path tempFile = Files.createTempFile("directoryApp", ".app");
            System.out.println("Temporary file created: " + tempFile.toAbsolutePath());
        }catch(IOException e){
            e.printStackTrace();
        }

        //STORING DIRECTORIES
        Iterable<FileStore> stores = FileSystems.getDefault().getFileStores();
        for(FileStore store : stores){
            System.out.println(store + " VOLUME NAME: " + store.name());
        }

        //ROOT DIRECTORIES
        Iterable<Path> paths = FileSystems.getDefault().getRootDirectories();
        for(Path currentPath : paths){
            System.out.println(currentPath);
        }

        //WALKING DOWN THE DIRECTORY TREE
        Path dir2Path = FileSystems.getDefault().getPath("Examples" + File.separator + "dir2");
        try{
            Files.walkFileTree(dir2Path, new PrintNames());
        }catch(IOException e){
            System.out.println(e.getMessage());
        }

        //COPYING A DIRECTORY AND ITS CONTENT TO ANOTHER DIRECTORY
        System.out.println("////////////////////////COPYING/////////////////////////");
        Path copyPath = FileSystems.getDefault().getPath("Examples" + File.separator + "newDir4" + File.separator + "newDir2");
        try{
            Files.walkFileTree(dir2Path, new CopyFiles(dir2Path, copyPath));
        }catch(IOException e){
            System.out.println(e.getMessage());
        }

        //JAVA NIO in JAVA IO
        File file = new File("Examples\\file.txt");
        System.out.println("converted path = " + file.toPath());

        File parent = new File("C:\\Users\\Blazej W\\IdeaProjects\\FileSystemNIO\\Examples");
        File resolvedFile = new File(parent, "\\dir1\\file1.txt");
        System.out.println("converted path = " + resolvedFile.toPath() + " EXISTS? => " +  Files.exists(resolvedFile.toPath()));

        File workingDirectory = new File("").getAbsoluteFile();
        System.out.println("Absolute file/Working directory = " + workingDirectory + " EXISTS? => " + Files.exists(workingDirectory.toPath()));

        System.out.println("///////////////////////DIR1 CONTENTS WITH IO///////////////////////////////////");
        File dir1file = new File(workingDirectory, "Examples\\dir1");
        System.out.println("dir1 = " + dir1file + " EXISTS? => " + Files.exists(dir1file.toPath()));
        String[] dir1contents = dir1file.list();
        for(int i = 0; i<dir1contents.length; i++){
            System.out.println(dir1contents[i]);
        }

        
    }
}