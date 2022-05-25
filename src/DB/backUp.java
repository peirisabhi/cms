/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;

/**
 *
 * @author abhi
 */
public class backUp {

    public static void main(String[] args) {
        String dbName = "cloth_shop";
        String dbUserName = "root";
        String dbPassword = "123";
        String path = "E:/backup.sql";

        String executeCmd = "C:/Program Files (x86)/MySQL/MySQL Server 5.1/bin/mysqldump.exe -u " + dbUserName
                + " -p" + dbPassword + " --add-drop-database -B " + dbName + " -r " + path;
        Process runtimeProcess;
        try {

            runtimeProcess = Runtime.getRuntime().exec(executeCmd);
            int processComplete = runtimeProcess.waitFor();

            if (processComplete == 0) {
                System.out.println("Backup created successfully");

            } else {
                System.out.println("Could not create the backup");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
