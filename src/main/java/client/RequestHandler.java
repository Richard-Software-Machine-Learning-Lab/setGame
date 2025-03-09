package client;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Implementation of request handler. A request handler handles all requests to the server.
 */
public class RequestHandler implements IRequestHandler {
  Socket clientSocket;
  String host;
  int port;
  BufferedReader bufferedReaderResponseReader;
  PrintWriter printWriterToServer;
  InputStream inputStream;
  OutputStream outputStream;

  public RequestHandler() {
    host = "localhost";
    port = 8080;
  }

  @Override
  public boolean checkSet(int[] cards, int challengeId) {
    Boolean response = false;

    try {
      clientSocket = new Socket(host, port);

      inputStream = clientSocket.getInputStream();
      outputStream = clientSocket.getOutputStream();

      String clientRequest = "GET http://localhost:8080/checkSet/?cards="+cards[0]+","+cards[1]+","+cards[2]+"&challengeId="+challengeId;
      printWriterToServer = new PrintWriter(outputStream, true);
      printWriterToServer.println(clientRequest);

      bufferedReaderResponseReader = new BufferedReader(new InputStreamReader(inputStream));
      String responseFromServer = bufferedReaderResponseReader.readLine();

      if (responseFromServer.equals("true")) {
        response = true;
      }

      return response;

    } catch (UnknownHostException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }

    return response;
  }

  @Override
  public int[] fetchChallengeDeck(int challengeId) {
    int[] response = new int[12];

    try {
      clientSocket = new Socket(host, port);

      inputStream = clientSocket.getInputStream();
      outputStream = clientSocket.getOutputStream();

      String clientRequest = "GET http://localhost:8080/challengeDeck/"+challengeId;
      printWriterToServer = new PrintWriter(outputStream, true);
      printWriterToServer.println(clientRequest);

      bufferedReaderResponseReader = new BufferedReader(new InputStreamReader(inputStream));
      String responseFromServer = bufferedReaderResponseReader.readLine();

      //Turn String to int[]
      String[] split = responseFromServer.split(",");
      split[0] = split[0].substring(1);
      split[split.length-1] = split[split.length-1].substring(0, 4);

      for (int i = 0; i < split.length; i++) {
        response[i] = Integer.parseInt(split[i]);
      }

      return response;

    } catch (UnknownHostException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }

    return response;
  }

  @Override
  public int fetchSetsIncludedInChallenge(int challengeId) {
    int response = 1111;

    try {
      clientSocket = new Socket(host, port);

      inputStream = clientSocket.getInputStream();
      outputStream = clientSocket.getOutputStream();

      String clientRequest = "GET http://localhost:8080/getNumberOfSets/"+challengeId;
      printWriterToServer = new PrintWriter(outputStream, true);
      printWriterToServer.println(clientRequest);

      bufferedReaderResponseReader = new BufferedReader(new InputStreamReader(inputStream));
      String responseFromServer = bufferedReaderResponseReader.readLine();
      response = Integer.parseInt(responseFromServer);
      return response;

    } catch (UnknownHostException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return response;
  }

  @Override
  public String[] getLeagueTableForChallenge(int challengeId) {

    String[] response = new String[0];

    if (challengeId <0 || challengeId > 3) {
      throw new Error("challengeId does not exist");
    }

    try {
      clientSocket = new Socket(host, port);
      inputStream = clientSocket.getInputStream();
      outputStream = clientSocket.getOutputStream();
      String clientRequest = "GET http://localhost:8080/getLeagueTable/"+challengeId;
      printWriterToServer = new PrintWriter(outputStream, true);
      printWriterToServer.println(clientRequest);
      bufferedReaderResponseReader = new BufferedReader(new InputStreamReader(inputStream));
      String responseFromServer = bufferedReaderResponseReader.readLine();
      // remove "{ }"
      responseFromServer = responseFromServer.substring(1, responseFromServer.length()-1);
      //Turn String to int[]
      String[] split = responseFromServer.split(":|,");
      for (int i = 0; i < split.length; i++) {
        if (split[i].contains("\"")) {
          split[i] = split[i].substring(1, split[i].length()-1);
        }
      }

      return split;


    } catch (UnknownHostException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return response;
  }

  @Override
  public void updateLeagueTableForChallenge(int challengeId, String name, long time) {
    String[] nameSplit = name.split(" ");
    name = nameSplit[0];

    if (challengeId <0 || challengeId > 3) {
      throw new Error("challengeId does not exist");
    }

    try {
      clientSocket = new Socket(host, port);

      inputStream = clientSocket.getInputStream();
      outputStream = clientSocket.getOutputStream();

      String clientRequest = "POST http://localhost:8080/updateLeagueTable/"+challengeId+"/"+name+"/"+time;
      printWriterToServer = new PrintWriter(outputStream, true);
      printWriterToServer.println(clientRequest);

    } catch (UnknownHostException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }

  }


}

