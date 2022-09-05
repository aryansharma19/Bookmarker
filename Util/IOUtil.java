package Util;

import java.io.*;
import java.util.List;

public class IOUtil {

    public static void read(List<String> data, String filename) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filename), "UTF-8"))) {
            String line;
            while ((line = br.readLine()) != null) {
                data.add(line);
            }
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static String read(InputStream in){
        StringBuilder sb = new StringBuilder();
        try(BufferedReader br = new BufferedReader(new InputStreamReader(in,"UTF-8"))){
            String line;
            while((line = br.readLine()) != null){
                sb.append(line).append("\n");
            }
            return sb.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void write(String data,long id){
        try(BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("./src/"+String.valueOf(id)+".html"),"UTF-8"))){
            System.out.println("writing the page");
            bw.write(data);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}