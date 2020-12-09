package org.hbrs.ooka.ws2020.uebung2.runtime;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class BackupManager {
    private static BackupManager bmInstance;
    private final Path backupPath = Paths.get("backup.txt");
    private boolean isCurrentConfig = false;

    private BackupManager() {    }

    public static BackupManager getInstance() {
        if (BackupManager.bmInstance == null) {
            BackupManager.bmInstance = new BackupManager();
        }
        return BackupManager.bmInstance;
    }

    public ArrayList<String> readConfig() {
        ArrayList<String> list = new ArrayList<>();
        try {
            InputStream in = Files.newInputStream(this.backupPath);
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line = null;
            while ((line = reader.readLine()) != null) {
                System.out.println("read line: " + line);
                list.add(line);
            }
        } catch (IOException e) {
            System.err.println(e);
        }
        if (!list.isEmpty()) {
            this.isCurrentConfig = true;
        }
        return list;
    }

    public void writeConfig(String instruction) {
        try {
            if (this.isCurrentConfig) {
                // do not rewrite the file
                BufferedWriter writer = new BufferedWriter(new FileWriter(String.valueOf(this.backupPath), true));
                writer.newLine();
                writer.write(instruction, 0, instruction.length());
                writer.close();
            } else {
                // rewrite file
                BufferedWriter writer = new BufferedWriter(new FileWriter(String.valueOf(this.backupPath), false));
                writer.write(instruction, 0, instruction.length());
                this.isCurrentConfig = true;
                writer.close();
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }



}
