/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.io.*;

/**
 *
 * @author matthewguerra
 */
public class ActivityLoger {
    public static void logUserLogIn(String userName, String time){
        try{
            BufferedWriter log = new BufferedWriter( new FileWriter("UserActivityLogs.txt", true)); 
            log.write(userName + " loged in at: " + time);
            log.newLine();
            log.close();
        } catch (IOException e){
            System.out.println("File do not output properly.");
        }
    }
}
