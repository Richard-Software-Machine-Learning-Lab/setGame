package client.view;

import client.IResultsView;
import client.uimanagement.UIManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Class for the view of the results of the challenge just played, implements {@link IResultsView}.
 */
public class ResultsView extends JPanel implements IResultsView {

  private static int NEXT_BUTTON_WIDTH = 200;
  private static int NEXT_BUTTON_HEIGHT = 60;

  private UIManager controller;
  private JButton nextButton;
  private JLabel congratsLabel;
  private LeagueTableView leagueTableView;

  /**
   * Constructs ResultsView by adding components.
   */
  public ResultsView(UIManager controller) {
    this.controller = controller;

    BorderLayout layout = new BorderLayout();
    setLayout(layout);

    addCongratsLabel();
    this.leagueTableView = new LeagueTableView();
    add(this.leagueTableView, BorderLayout.CENTER);
    addNextChallengeButton();
    addNextChallengeButtonActionListener();
  }

  private void addCongratsLabel() {
    this.congratsLabel = new JLabel();
    this.add(this.congratsLabel, BorderLayout.PAGE_START);
  }

  @Override
  public void setCongratsLabelText(String notification) {
    this.congratsLabel.setText(notification);
    this.congratsLabel.setForeground(new Color(34, 139, 34));
  }

  @Override
  public void setNewLeagueTable(String[] leagueTable) {
    this.leagueTableView.addLeagueTableEntries(leagueTable);
  }

  @Override
  public void emptyLeagueTable() {
    this.leagueTableView.removeLeagueTableEntries();
  }

  private void addNextChallengeButton() {
    this.nextButton = new JButton("Next Challenge");
    this.nextButton.setBackground(new Color(34, 139, 34));
    this.nextButton.setForeground(new Color(255, 255, 255));
    this.nextButton.setOpaque(true);
    this.nextButton.setBorderPainted(false);
    this.nextButton.setPreferredSize(new Dimension(NEXT_BUTTON_WIDTH, NEXT_BUTTON_HEIGHT));
    this.add(this.nextButton, BorderLayout.PAGE_END);
  }

  /**
   * Adds action listeners to the play button and
   * calls corresponding methods.
   */
  public void addNextChallengeButtonActionListener() {
    this.nextButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        controller.chooseNextChallenge();
      }
    });
  }


}