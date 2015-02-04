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
package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Sascha A. Köhne
 */
public class EchoServer {

    private int LISTENING_PORT = 2002;
    private ServerSocket serverSocket = null;
    private Socket socket = null;
    private ClientInfo clientInfo = null;
    private ClientListener clientListener = null;
    private ClientSender clientSender = null;
    private ServerDispatcher serverDispatcher = null;
    
    public EchoServer(int Port){
        this.LISTENING_PORT = Port;
        this.starting();
    }
    
    private void starting(){
        // Open server socket for listening
        this.serverSocket = null;
        try {
           serverSocket = new ServerSocket(LISTENING_PORT);
           System.out.println("NakovChatServer started on port " + LISTENING_PORT);
        } catch (IOException se) {
           System.err.println("Can not start listening on port " + LISTENING_PORT);
           se.printStackTrace();
           System.exit(-1);
        }
 
        // Start ServerDispatcher thread
        this.serverDispatcher = new ServerDispatcher();
        this.serverDispatcher.start();
 
        // Accept and handle client connections
        while (true) {
           try {
               this.socket = serverSocket.accept();
               this.clientInfo = new ClientInfo();
               this.clientInfo.mSocket = socket;
               this.clientListener =
                   new ClientListener(this.clientInfo, this.serverDispatcher);
               this.clientSender =
                   new ClientSender(this.clientInfo, this.serverDispatcher);
               this.clientInfo.mClientListener = this.clientListener;
               this.clientInfo.mClientSender = this.clientSender;
               this.clientListener.start();
               this.clientSender.start();
               serverDispatcher.addClient(this.clientInfo);
           } catch (IOException ioe) {
               ioe.printStackTrace();
           }
        }
    }
}
