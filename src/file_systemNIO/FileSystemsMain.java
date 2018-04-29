package file_systemNIO;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLOutput;

public class FileSystemsMain {

    public static void main(String[] args) {

        //COPYING EXISTING FILE

        try{Path sourceFile = FileSystems.getDefault().getPath("Examples\\file1.txt");
            Path copyFile = FileSystems.getDefault().getPath("Examples\\file1copy.txt");
            if(!Files.exists(copyFile)){
                Files.copy(sourceFile, copyFile);
            }
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
