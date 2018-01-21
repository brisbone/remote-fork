package de.uwxy.remote;

import android.os.AsyncTask;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by brisbone on 09.01.18.
 */

public class SocketToLircConnection extends AsyncTask<String,Void,Void>{

    private Socket socket;
    private PrintWriter printWriter;
    private final String LIRC_IP = "192.168.1.70";
    private final int LIRC_PORT = 8765;


    @Override
    protected  Void doInBackground(String[] command) {
        try  {

            socket = new Socket(LIRC_IP, LIRC_PORT);
            printWriter = new PrintWriter(socket.getOutputStream());
            printWriter.println(command[0] + " " + command[1] + " " + command[2]);
            //Log.e(LIRC_IP,command[0] + " " + command[1] + " " + command[2]);
            printWriter.close();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


}
