package com.registerbook.registerbook.service.member.fileOperation.fileWriter;

import com.google.gson.Gson;
import com.registerbook.registerbook.model.entities.Member;
import java.io.*;
import java.util.List;

public class FileWriter {

    public static void writeToFile(List<Member> listToWriteJson,String fileName) {
        File file = new File("C://testfiles//" + fileName + ".txt");
        FileOutputStream fos = null;
        BufferedWriter bw;
        try {
            fos = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        bw = new BufferedWriter(new OutputStreamWriter(fos));
        String json = new Gson().toJson(listToWriteJson);

        try {
            bw.write(json);
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            bw.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
