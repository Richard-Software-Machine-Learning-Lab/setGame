package client.view;

import client.ILeagueTableView;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

/**
 * Class for the view of the league table, implements {@link ILeagueTableView}.
 */
public class LeagueTableView extends JPanel implements ILeagueTableView {

  private JTable leagueTable;
  DefaultTableModel tableModel;

  /**
   * Constructs LeagueTableView by adding components.
   */
  public LeagueTableView() {

    setBorder(BorderFactory.createTitledBorder(
        BorderFactory.createEtchedBorder(), "Scoring Board", TitledBorder.LEFT,
        TitledBorder.TOP));


    this.leagueTable = new JTable();

    this.tableModel = new DefaultTableModel();
    String header[] = new String[] { "Player", "Time (in ms)"};
    this.tableModel.setColumnIdentifiers(header);
    this.leagueTable.setModel(this.tableModel);
    add(new JScrollPane(this.leagueTable));
  }

  @Override
  public void addLeagueTableEntries(String[] leagueTableEntries) {
    boolean col1 = true;
    String playerName = "";
    String time = "";
    for (String singleEntry : leagueTableEntries) {
      if(col1) {
        playerName = singleEntry;
        col1 = false;
      } else {
        time = singleEntry;
        this.tableModel.addRow(new Object[]{playerName, time});
        col1 = true;
      }
    }
    this.tableModel.fireTableDataChanged();
  }

  @Override
  public void removeLeagueTableEntries() {
    for (int i = 0; i < this.tableModel.getRowCount(); i++) {
      this.tableModel.removeRow(i);
    }
  }

}
