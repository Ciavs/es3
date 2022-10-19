package com.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws Exception {

        Socket s = new Socket("localhost", 3000);
        

        try {

            // per parlare
            PrintWriter pr = new PrintWriter(s.getOutputStream(), true);

            // perfsf ascoltare
            BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));

            // per la tastiera
            BufferedReader tastiera = new BufferedReader(new InputStreamReader(System.in));

            pr.println("Eccomi");
            System.out.println(br.readLine()); // rivevo: ciao come ti chiami ?
            pr.println(tastiera.readLine()); // leggo da tastiera il nome e lo invio
            System.out.println(br.readLine()); // ricevo: salve 'nome' sei l'utente connesso num 'x'
            pr.println(tastiera.readLine()); // leggo da tastiera l'altezza e la invio
            System.out.println(br.readLine());
            pr.println(tastiera.readLine()); // invio uno dei 5 comandi
            

            s.close();
        } catch (Exception e) {
        }
    }
}
