package com.gygk.fightking.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.gygk.fightking.pojo.Arena;
import com.gygk.fightking.pojo.BallPlayer;
import com.gygk.fightking.pojo.Warp;
import com.gygk.fightking.pojo.ArenaIv;

import java.io.*;
import java.util.List;

public class FileUtil {


    public static void writeArenaFiles(String uri, List<Arena> arenas){
        File f1 = new File(uri);
        try {
            f1.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Gson gson = new Gson();

        String s = gson.toJson(arenas);
        try {
            FileWriter fileWriter = new FileWriter(f1.getAbsolutePath());
            fileWriter.write(s);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static List<Arena> readArenaFiles(String uri){
        File f1 = new File(uri);
        BufferedReader br = null;
        Gson gson = new Gson();
        try {
            br = new BufferedReader(new InputStreamReader
                    (new FileInputStream(new File
                            (f1.getAbsolutePath())
                    ), "GBK"));//UTF-8
            String str = null;
            String s1 = "";
            int i = 0;
            while ((str = br.readLine()) != null) {
                String[] v1 = str.trim().split("\\s+"); //剔除调前、后、中间所有的空格
                System.out.println(str);
                s1 = s1 + str;
                i++;
            }
            List<Arena> plist = gson.fromJson(s1, new TypeToken<List<Arena>>(){}.getType());


            return plist;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    public static void writeWarpFiles(String uri, List<Warp> warp){
        File f1 = new File(uri);
        try {
            f1.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Gson gson = new Gson();
        String s = gson.toJson(warp);
        try {
            FileWriter fileWriter = new FileWriter(f1.getAbsolutePath());
            fileWriter.write(s);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Warp> readWarpFiles(String uri){
        File f1 = new File(uri);
        BufferedReader br = null;
        Gson gson = new Gson();
        try {
            br = new BufferedReader(new InputStreamReader
                    (new FileInputStream(new File
                            (f1.getAbsolutePath())
                    ), "GBK"));//UTF-8
            String str = null;
            String s1 = "";
            int i = 0;
            while ((str = br.readLine()) != null) {
                String[] v1 = str.trim().split("\\s+"); //剔除调前、后、中间所有的空格
                System.out.println(str);
                s1 = s1 + str;
                i++;
            }
            List<Warp> plist = gson.fromJson(s1, new TypeToken<List<Warp>>(){}.getType());


            return plist;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static void writeBPFiles(String uri, BallPlayer ballPlayer){
        File f1 = new File(uri);
        try {
            f1.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Gson gson = new Gson();
        String s = gson.toJson(ballPlayer);
        try {
            FileWriter fileWriter = new FileWriter(f1.getAbsolutePath());
            fileWriter.write(s);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static BallPlayer readBPFiles(String uri){
        File f1 = new File(uri);
        BufferedReader br = null;
        Gson gson = new Gson();
        try {
            br = new BufferedReader(new InputStreamReader
                    (new FileInputStream(new File
                            (f1.getAbsolutePath())
                    ), "GBK"));//UTF-8
            String str = null;
            String s1 = "";
            int i = 0;
            while ((str = br.readLine()) != null) {
                String[] v1 = str.trim().split("\\s+"); //剔除调前、后、中间所有的空格
                System.out.println(str);
                s1 = s1 + str;
                i++;
            }
            BallPlayer ballPlayer = gson.fromJson(s1, BallPlayer.class);

            return ballPlayer;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
