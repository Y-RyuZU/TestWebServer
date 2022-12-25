package com.github.ryuzu.TestWebServer;

import com.github.ryuzu.TestWebServer.Utilities.DataBaseUtilities;
import com.google.gson.Gson;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class Debug {
    @RequestMapping("/api/debug/export")
    public static String put(@RequestParam("page") int i) {
        List<String> pages = new ArrayList<>();
        String debug = """
                        debug
                """;

        return debug;
    }

    public static String write(String csv) {
        try {
            FileWriter fw = new FileWriter("技術2学期一対一.csv", false);
            PrintWriter pw = new PrintWriter(new BufferedWriter(fw));

            pw.print("問題");
            pw.print(",");
            pw.print("答え");
            pw.println();

            String[] sets = csv.split("•");
            for(String set : sets) {
                String question = set.split("=")[0];
                String answer = set.split("=")[1];
                pw.print(question);
                pw.print(",");
                pw.print(answer);
                pw.println();
            }
            pw.close();

            return fw.getEncoding();

        } catch (IOException ex) {
        }
        return null;
    }
}
