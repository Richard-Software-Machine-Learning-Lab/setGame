package client.view;

import client.uimanagement.UIManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Class for the start view (starting page).
 */
public class StartView extends JPanel {

  private static final String DEFAULT_PLAYER_NAME = "Alex";

  /**
   * BUTTON_LABEL_PLAY is referring to the button label of the 'Play' button.
   */
  private static String BUTTON_LABEL_PLAY = "PLAY";

  /**
   * PLAY_BUTTON_WIDTH is referring to the preferred width of the play button.
   */
  private static int PLAY_BUTTON_WIDTH = 200;
  /**
   * PLAY_BUTTON_HEIGHT is referring to the preferred height of the play button.
   */
  private static int PLAY_BUTTON_HEIGHT = 60;

  private JButton playButton;
  private JTextField nameInputField;
  private UIManager controller;

  /**
   * Construct StartPanel object by adding name insertion field and "Play" button.
   */
  public StartView(UIManager controller) {
    this.controller = controller;
    addNameInput();
    addPlayButton();
    addPlayButtonActionListener();
  }

  /**
   * Creation of name input field.
   */
  private void addNameInput() {
    this.nameInputField = new JTextField(this.DEFAULT_PLAYER_NAME, 10);
    this.add(this.nameInputField);
  }

  /**
   * Creation of play button.
   */
  private void addPlayButton() {
    this.playButton = new JButton(BUTTON_LABEL_PLAY);
    this.playButton.setBackground(new Color(34, 139, 34));
    this.playButton.setForeground(new Color(255, 255, 255));
    this.playButton.setOpaque(true);
    this.playButton.setBorderPainted(false);
    this.playButton.setPreferredSize(new Dimension(PLAY_BUTTON_WIDTH, PLAY_BUTTON_HEIGHT));
    this.add(this.playButton);
  }

  /**
   * Adds action listeners to the play button and
   * calls corresponding methods.
   */
  private void addPlayButtonActionListener() {
    this.playButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String name = nameInputField.getText();
        controller.play(name);
      }
    });
  }

}
