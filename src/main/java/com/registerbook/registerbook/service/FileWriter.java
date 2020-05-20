package com.registerbook.registerbook.service;

import com.google.gson.Gson;
import com.registerbook.registerbook.repository.model.Member;
import java.io.*;
import java.util.List;

public class FileWriter {

    public static void writeToFile(List<Member> listToWriteJson,String fileName) {
        File fout = new File(fileName + ".txt");
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(fout);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
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
