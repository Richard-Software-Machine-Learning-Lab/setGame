package client;

import client.gamemanagement.GameManager;
import client.uimanagement.UIManager;
import client.view.View;

/**
 * Starting the client.
 */
public class ClientMain {

  public static void main(String[] args){
    UIManager uiManager = new UIManager();
    View view = new View();
    GameManager gameManager = new GameManager();
    uiManager.setView(view);
    uiManager.setGameManager(gameManager);
    view.setUIManager(uiManager);
    view.createView();
    gameManager.setUIManager(uiManager);
  }

}

