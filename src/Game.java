import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Game extends JFrame {
    private TTTBoard gameBoard;
    private TTTTileButton[][] buttons;
    private JLabel statusLabel;

    public Game() {
        setTitle("Lab07B - OOP Tic Tac Toe");
        setSize(400, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        gameBoard = new TTTBoard();
        buttons = new TTTTileButton[3][3];

        // Top Status Panel
        statusLabel = new JLabel("Player X's turn", SwingConstants.CENTER);
        statusLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(statusLabel, BorderLayout.NORTH);

        // Center Grid Panel
        JPanel gridPanel = new JPanel(new GridLayout(3, 3));
        ActionListener tileListener = new TileClickListener();

        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                buttons[r][c] = new TTTTileButton(r, c);
                buttons[r][c].setFont(new Font("Arial", Font.BOLD, 40));
                buttons[r][c].addActionListener(tileListener);
                gridPanel.add(buttons[r][c]);
            }
        }
        add(gridPanel, BorderLayout.CENTER);
        setLocationRelativeTo(null);
    }

    private class TileClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            TTTTileButton clickedButton = (TTTTileButton) e.getSource();
            int r = clickedButton.getRow();
            int c = clickedButton.getCol();

            char activePlayer = gameBoard.getCurrentPlayer();
            if (gameBoard.makeMove(r, c)) {
                clickedButton.setText(String.valueOf(activePlayer));

                if (gameBoard.checkWin()) {
                    statusLabel.setText("Player " + activePlayer + " Wins!");
                    disableAllButtons();
                } else if (gameBoard.checkDraw()) {
                    statusLabel.setText("It's a Draw!");
                } else {
                    statusLabel.setText("Player " + gameBoard.getCurrentPlayer() + "'s turn");
                }
            }
        }
    }

    private void disableAllButtons() {
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                buttons[r][c].setEnabled(false);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Game().setVisible(true);
        });
    }
}