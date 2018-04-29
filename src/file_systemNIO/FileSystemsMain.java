package file_systemNIO;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileSystemsMain {

    public static void main(String[] args) {
        Path path;
        path = FileSystems.getDefault().getPath("workingDirectoryFile.txt");
        printFile(path);
        path = FileSystems.getDefault().getPath("Files","subdirectoryFile.txt");
        printFile(path);
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
