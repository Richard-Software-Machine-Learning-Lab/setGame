package client.view;

import client.IView;
import client.uimanagement.UIManager;

import javax.swing.*;
import java.awt.*;

/**
 * Manages the view. Orchestrates the creation of the JAVA swing components for the GUI.
 */
public class View extends JFrame implements IView {

  private UIManager uiManager;
  private CardLayout cardLayout;
  private JPanel mainPanel;
  private StartView startView;
  private ChallengeView challengeView;
  private ResultsView resultsView;

  private static int STD_MAIN_FRAME_WIDTH = 800;
  private static int STD_MAIN_FRAME_HEIGHT = 600;

  @Override
  public void setUIManager(UIManager uiManager) {
    this.uiManager = uiManager;
  }

  @Override
  public void createView() {
    //creating main panel
    this.cardLayout = new CardLayout();
    this.mainPanel = new JPanel(cardLayout);

    this.startView = new StartView(this.uiManager);
    this.challengeView = new ChallengeView(this.uiManager);
    this.resultsView = new ResultsView(this.uiManager);
    this.mainPanel.add(this.startView, "start");
    this.mainPanel.add(this.challengeView, "challenge");
    this.mainPanel.add(this.resultsView, "results");

    add(this.mainPanel);
    cardLayout.show(this.mainPanel, "start");
    setPreferredSize(new Dimension(STD_MAIN_FRAME_WIDTH, STD_MAIN_FRAME_HEIGHT));
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    pack();
    setLocationByPlatform(true);
    setVisible(true);
  }

  @Override
  public ChallengeView getChallengePanel() {
    return this.challengeView;
  }

  @Override
  public ResultsView getResultsPanel() {
    return this.resultsView;
  }

  @Override
  public CardView[] getAllCardPanels() {
    return this.getChallengePanel().getTwelveCardsPanel().getAllCardPanels();
  }

  @Override
  public void showChallenge() {
    cardLayout.show(this.mainPanel, "challenge");
  }

  @Override
  public void showResults() {
    cardLayout.show(this.mainPanel, "results");
  }

}
