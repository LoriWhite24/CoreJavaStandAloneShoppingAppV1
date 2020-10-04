package com.cognixia.jumplus.shoppingapp;

import java.awt.GraphicsEnvironment;
import java.io.Console;
import java.io.IOException;

/**
 * Core Java Stand Alone Shopping Application version 1
 * @author Lori White
 * @version v1 (10/03/2020)
 */
public class App 
{
	/**
	 * The main entry point for this App.
	 * @param args the command line arguments
	 * @throws IOException for input and output errors
	 */
    public static void main( String[] args ) throws IOException
    {
    	Console console = System.console();
        if(console == null && !GraphicsEnvironment.isHeadless()){
            String filename = App.class.getProtectionDomain().getCodeSource().getLocation().toString().substring(6);
            try {
				Runtime.getRuntime().exec(new String[]{"cmd","/c","start","cmd","/k","java -jar \"" + filename + "\""});
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
        Main.main(new String[0]);
        Runtime.getRuntime().exec("taskkill /f /im cmd.exe");
    }
}

