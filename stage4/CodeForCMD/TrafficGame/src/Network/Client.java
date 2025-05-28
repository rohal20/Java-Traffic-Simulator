package Network;

import java.io.*;
import java.net.*;
import View.GameView;

/**
 * This Client class is used to establish a network connection with the server using a socket.
 * We are using PrintWriter and BufferedReader for text-based communications with the server.
 * We are using the GameView class to display messages and prompts.
 * We have created an authentication protocol. where only (user1, user2, user3) can log in to play.
 * When successful a prompt for the vehicle selection occurs and the input entered by the user is sent to the server.
 */

public class Client {
    public static void main(String[] args) throws IOException {
        String hostName = "localhost";
        int portNumber = 12345;
        GameView gameView = new GameView();

        try (
                Socket echoSocket = new Socket(hostName, portNumber);
                PrintWriter out = new PrintWriter(echoSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()))
        ) {
            gameView.displayMessage("Connected to server");

            String username = gameView.promptForUsername();
            out.println(username);

            String response = in.readLine();
            gameView.displayMessage(response);
            if (!"Authentication Successful".equals(response)) {
                return;
            }

            int vehicleChoice = gameView.promptVehicleSelection();
            out.println(vehicleChoice);
            gameView.displayMessage("Vehicle choice (" + vehicleChoice + ") sent to server");
            gameView.displayMessage("You are in the Game, PLAY ON THE SERVER");


        }
    }
}
