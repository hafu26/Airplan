package demo.isoft.com.airplan.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by hafu_16 on 2019/7/13.
 */
/*文件存储相关*/
public class IOUtil {
    public static void clearFile(File file) {
        try {
            new FileWriter(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<String> readData(File file) {
        ArrayList<String> list=new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String str = "";
            while ((str = br.readLine()) != null) {
                list.add(str);
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void writeData(File file, String str) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(file,true));
            bw.write(str);
            bw.flush();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
