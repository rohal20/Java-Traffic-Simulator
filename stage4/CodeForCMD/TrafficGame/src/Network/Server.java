package Network;

import Controller.GameController;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

/**
 * This is the Server class.
 * We define our port number, then try to open our socket.
 * It waits and listens for a client, then verifies authentication by the client, by comparing to hard coded list of possible users.
 * Once this is done it waits for a response from the client for vehicle selection prompt and then starts the game.
 *
 */

public class Server {
    private static final List<String> validUsernames = Arrays.asList("user1", "user2", "user3");

    public static void main(String[] args) {
        int portNumber = 12345;

        try (ServerSocket serverSocket = new ServerSocket(portNumber)) {
            System.out.println("Server is listening on port " + portNumber);

            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                     BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

                    System.out.println("Client connected");

                    String username = in.readLine();
                    if (validUsernames.contains(username)) {
                        out.println("Authentication Successful");
                    } else {
                        out.println("Authentication Failed");
                        continue;
                    }


                    String vehicleChoiceStr = in.readLine();
                    try {
                        int vehicleChoice = Integer.parseInt(vehicleChoiceStr);
                        System.out.println("Vehicle choice received: " + vehicleChoice);

                        // Start game
                        GameController gameController = new GameController();
                        gameController.startGame(vehicleChoice);

                    } catch (NumberFormatException e) {
                        System.err.println("Error parsing vehicle choice: " + vehicleChoiceStr);
                        out.println("Invalid vehicle choice received.");
                    }

                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
