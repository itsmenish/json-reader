package com.itsmenish.app;

import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.json.JSONException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        if (args.length == 0) {
            System.out.println("Too few arguments. Pass the config file name");
        }
        else {
            String configFile = args[0];
            System.out.println("Using the config file: " + configFile);
            JSONObject configJSON = new JSONObject();
            configJSON.put("sleep", 30000);
            JSONObject userCreds = new JSONObject();
            userCreds.put("user", "nxp8369");
            userCreds.put("password", "ThisIsNotAPassword");
            configJSON.put("credentials", userCreds);

            System.out.println(configJSON.toString(4));

            System.out.println("Reading contents from the config file");

            try{
                String configString = new String(Files.readAllBytes(Paths.get(configFile)));
                System.out.println("String value: " + configString);
                JSONObject actualConfig = new JSONObject(configString);
                System.out.println(actualConfig.toString(4));

                System.out.println("Getting the value of sleep");
                int sleepSeconds = actualConfig.getInt("sleep");
                System.out.println("Sleep is configured for " + sleepSeconds + " milliseconds");
            } catch (IOException e) {
                System.out.println("Failed to read the config file");
                System.out.println(e.toString());
            } catch (JSONException e) {
                System.out.println("Failed to process JSON config file");
                System.out.println(e.toString());
            }
        }
    }
}
