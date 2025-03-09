package client.view;

import client.IChallengeView;
import client.uimanagement.UIManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Class for the view of the current challenge, implements {@link IChallengeView}.
 */
public class ChallengeView extends JPanel implements IChallengeView {

  private static int SUBMIT_BUTTON_WIDTH = 200;
  private static int SUBMIT_BUTTON_HEIGHT = 60;

  private UIManager uiManager;
  private TwelveCardsView twelveCardsView;
  private JLabel messageLabel;
  private JPanel messageLabelPanel;
  private JPanel statusLabelPanel;
  private JLabel setStatusLabel;
  private JPanel notificationPanel;
  private JButton submitButton;

  /**
   * Constructs challenge View
   * @param uiManager
   */
  public ChallengeView(UIManager uiManager) {
    this.uiManager = uiManager;

    BorderLayout layout = new BorderLayout();
    setLayout(layout);

    this.twelveCardsView = new TwelveCardsView(this.uiManager);
    add(this.twelveCardsView, BorderLayout.CENTER);

    addNotificationPanel();
    addSubmitButton();
    addSubmitButtonActionListener();
  }

  private void addNotificationPanel() {
    this.notificationPanel = new JPanel();
    add(this.notificationPanel, BorderLayout.PAGE_START);
    this.notificationPanel.setLayout(new BorderLayout());
    addMessageLabel();
    addSetStatusLabel();
  }

  private void addMessageLabel() {
    this.messageLabelPanel = new JPanel();
    this.notificationPanel.add(this.messageLabelPanel, BorderLayout.WEST);
    this.messageLabel = new JLabel("Select 3 Cards:");
    this.messageLabelPanel.add(this.messageLabel);
  }

  @Override
  public void showSelectionWarning() {
    this.messageLabel.setText("Select exactly 3 Cards:");
    this.messageLabel.setForeground(Color.RED);
  }

  @Override
  public void removeSelectionWarning() {
    this.messageLabel.setText("Select exactly 3 Cards:");
    this.messageLabel.setForeground(Color.BLACK);
  }

  private void addSetStatusLabel() {
    this.statusLabelPanel = new JPanel();
    this.notificationPanel.add(this.statusLabelPanel, BorderLayout.EAST);
    this.setStatusLabel = new JLabel();
    this.statusLabelPanel.add(this.setStatusLabel);
  }

  @Override
  public void setStatusLabelText(String notification) {
    this.setStatusLabel.setText(notification);
    this.setStatusLabel.setForeground(Color.BLACK);
  }

  private void addSubmitButton() {
    this.submitButton = new JButton("Submit");
    this.submitButton.setBackground(new Color(34, 139, 34));
    this.submitButton.setForeground(new Color(255, 255, 255));
    this.submitButton.setOpaque(true);
    this.submitButton.setBorderPainted(false);
    this.submitButton.setPreferredSize(new Dimension(SUBMIT_BUTTON_WIDTH, SUBMIT_BUTTON_HEIGHT));
    this.add(this.submitButton, BorderLayout.PAGE_END);
  }

  /**
   * Adds action listeners to the play button and
   * calls corresponding methods.
   */
  public void addSubmitButtonActionListener() {
    this.submitButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        uiManager.submitSetSelection();
      }
    });
  }

  @Override
  public TwelveCardsView getTwelveCardsPanel() {
    return this.twelveCardsView;
  }


}