/*
 * Copyright (C) 2015 Sascha
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.Time;

/**
 *
 * @author Sascha
 */
class Sender extends Thread {

    private PrintWriter mOut;
    private ClientInfo cl = new ClientInfo();

    public Sender(PrintWriter aOut) {
        mOut = aOut;
    }

    /**
     * Until interrupted reads messages from the standard input (keyboard) and
     * sends them to the chat server through the socket.
     */
    @Override
    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            while (!isInterrupted()) {
                String message = in.readLine();
                if (message.equals("/quit")) {
                    mOut.println("quit");
                    mOut.flush();
                    break;
                }else if(message.startsWith("name:")){
                    cl.name = message.substring(5);
                    mOut.println(message);
                    mOut.flush();
                }else {
                    cl.last_msg =  System.currentTimeMillis();
                    mOut.println(cl.name+":"+cl.last_msg+"::"+message);
                    mOut.flush();
                }
            }
        } catch (IOException ioe) {
            // Communication is broken
        }
    }
}
