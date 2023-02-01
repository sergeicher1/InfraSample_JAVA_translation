package infrastructure;

import infrastructure.utilities.ExternalFiles;

import java.io.IOException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        String file_path = "D:\\IntelliJ\\InfraSample_JAVA_translation\\text.txt";

       System.out.println(ExternalFiles.Text.Read(file_path));
    }
}
