
import server.EchoServer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Sascha
 */
public class MyApp {
    public static void main(String[] args){
        EchoServer srv = new EchoServer(2002);
    }
}
