package com.github.ryuzu.TestWebServer;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class Debug {
    @RequestMapping("/api/debug/export")
    public static String put() {
        List<String> pages = new ArrayList<>();
        String debug = """
                       
                """;
        var file = new File(System.getProperty("user.dir") + "/" + "src");
        return System.getProperty("user.dir") + "-------" + file.getPath() + "--------" + file.getPath().replace(System.getProperty("user.dir"), "").replace("\\" , "/");
    }

    public static String write(String csv) {
        try {
            FileWriter fw = new FileWriter("values.csv", false);
            PrintWriter pw = new PrintWriter(new BufferedWriter(fw));

            pw.print("key");
            pw.print(",");
            pw.print("value");
            pw.println();

            String[] sets = csv.split("\n");
            for(String set : sets) {
                String question = set.split("\t")[0];
                String answer = set.split("\t")[1];
                pw.print(question.replaceAll(" ", ""));
                pw.print(",");
                pw.print(answer);
                pw.println();
            }
            pw.close();

            return fw.getEncoding();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
