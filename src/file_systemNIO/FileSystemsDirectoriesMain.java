package file_systemNIO;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;

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
    }
}