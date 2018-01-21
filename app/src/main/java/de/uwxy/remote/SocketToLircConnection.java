/*
 * Copyright (C) 2018 Ulrich Wiegand, brisbone@gmx.de
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 */

package de.uwxy.remote;

import android.os.AsyncTask;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;


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
