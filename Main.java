package com.company;

import java.io.*;
import java.util.*;

public class Main {

    static int compare(File f1, File f2) throws IOException {
        try(BufferedInputStream bis1 = new BufferedInputStream(new FileInputStream(f1));
            BufferedInputStream bis2 = new BufferedInputStream(new FileInputStream(f1))) {

            while(true) {
                int b1 = bis1.read();
                int b2 = bis2.read();

                if (b1 == -1 && b2 == -1) {
                    return 0;
                }

                if (b1 != b2) return b1-b2;
            }
        }
    }


    public static List<File> getAllFiles (File f) {
        ArrayList<File> files = new ArrayList<>();
        File[] folders = f.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isDirectory();
            }
        });

        for (File folder: folders) {
            files.addAll(getAllFiles(folder));
        }

        File[] files1 = f.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isFile();
            }
        });
        files.addAll(Arrays.asList(files1));

        return files;
    }

    public static void main(String[] args) throws IOException {

        List<File> files = getAllFiles(new File("D:\\Java\\Projects\\duplicteFileFinder"));

        for (int i = 0; i < files.size(); i++) {
            for (int j = i+1; j < files.size(); j++) {
                if (compare(files.get(i), files.get(j)) == 0) {
                    System.out.println("Файлы " + files.get(i) + " и " + files.get(j) + " равны!");
                }
            }
        }
    }
}
