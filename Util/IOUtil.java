package Util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class IOUtil {

    public static void read(String[] data,String fileName){
        try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName),"UTF-8"))){
            String line;
            int counter = 0;
            if((line = br.readLine()) != null){
                data[counter] = line;
                counter++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
}
