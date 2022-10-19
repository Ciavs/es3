package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ClientHandler extends Thread {
    private PrintWriter pr = null;
    private BufferedReader br = null;
    private int contatore;

    public ClientHandler(Socket s) {
    }

    public ClientHandler(Socket s, int c) {
        contatore = c;
        try {
            // per parlare
            pr = new PrintWriter(s.getOutputStream(), true);
            // per ascoltare
            br = new BufferedReader(new InputStreamReader(s.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        try {

            System.out.println(br.readLine());
            pr.println("Ciao come ti chiami?"); // invio messaggio
            String nome = br.readLine(); // ricevo: nome
            String nomeUpper = nome.toUpperCase(); // nome
            System.out.println("nome : " + nomeUpper + " connesso"); // console: nome ricevuto
            pr.println("Salve, " + nomeUpper + " utente connesso con numero " + contatore); // invio messaggio
            
            pr.println("scegli un comando fra data, ora, nome, id e fine");
            
            String command = br.readLine(); // ricevo uno dei 5 comandi

            switch (command) {

                case "data":
                    Date data = new Date();
                    SimpleDateFormat formatter = new SimpleDateFormat("dd/mm/yy");
                    String s = formatter.format(data);
                    pr.println(" la data e' - " + s);
                    break;
                case "ora":
                    Date ora = new Date();
                    SimpleDateFormat frm = new SimpleDateFormat("hh:mm:ss");
                    String t = frm.format(ora);
                    pr.println("l'ora e' - " + t);
                    break;
                case "nome":
                    pr.println("io sono : SERVERONE");
                    break;
                case "id":
                    pr.println(contatore);
                    break;
                case "fine":
                    break;
                default:
                    System.out.println("command not found");

            }
        } catch (Exception e) {
            
        }
    }
}
