package org.hbrs.ooka.ws2020.uebung2.runtime;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import static java.nio.file.StandardOpenOption.*;

public class BackupManager {
    private Path backupPath;
    private boolean isCurrentConfig = false;

    public BackupManager(String path) {
        this.backupPath = Paths.get(path);
    }

    public ArrayList<String> readConfig() {
        ArrayList<String> list = new ArrayList<>();
        try {
            InputStream in = Files.newInputStream(this.backupPath);
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line = null;
            while ((line = reader.readLine()) != null) {
                list.add(line);
            }
        } catch (IOException e) {
            System.err.println(e);
        }
        this.isCurrentConfig = true;
        return list;
    }

    public void writeConfig(String instruction) {
        try {
            byte data[] = instruction.getBytes();
            if (this.isCurrentConfig) {
                // do not rewrite the file
                OutputStream out = new BufferedOutputStream(
                        Files.newOutputStream(this.backupPath, APPEND));
                out.write(data);
            } else {
                // rewrite file
                OutputStream out = new BufferedOutputStream(
                        Files.newOutputStream(this.backupPath, CREATE, TRUNCATE_EXISTING));
                out.write(data);
                this.isCurrentConfig = true;
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }



}
