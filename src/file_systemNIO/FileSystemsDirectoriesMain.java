package file_systemNIO;

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
    }
}
