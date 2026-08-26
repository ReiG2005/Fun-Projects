import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Tic_Tac_Toe {
    // Track player turns: true = X, false = O
    private static boolean xTurn = true;
    private static JButton[] buttons = new JButton[9];

    public static void main(String[] args) {
        JFrame frame = new JFrame("Tic-Tac-Toe");
        frame.setSize(800, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 1. Create a 3x3 grid layout
        frame.setLayout(new GridLayout(3, 3));
        Font gameFont = new Font("Arial", Font.BOLD, 40);

        // 2. Initialize 9 buttons using a loop
        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton("");
            buttons[i].setFont(gameFont);
            frame.add(buttons[i]); // Automatically populates the 3x3 grid row-by-row

            // 3. Add click functionality to each button
            buttons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JButton clickedButton = (JButton) e.getSource();

                    // Only allow clicking empty buttons
                    if (clickedButton.getText().equals("")) {
                        if (xTurn) {
                            clickedButton.setText("X");
                        } else {
                            clickedButton.setText("O");
                        }

                        // Switch turn
                        xTurn = !xTurn;

                        // Check if someone won
                        checkWinner();
                    }
                }
            });
        }

        frame.setVisible(true);
    }

    // 4. Game logic to check rows, columns, and diagonals
    private static void checkWinner() {
        // All 8 possible winning combinations of indices
        int[][] winConditions = {
                {0, 1, 2}, {3, 4, 5}, {6, 7, 8}, // Rows
                {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, // Columns
                {0, 4, 8}, {2, 4, 6}             // Diagonals
        };

        for (int[] condition : winConditions) {
            String b1 = buttons[condition[0]].getText();
            String b2 = buttons[condition[1]].getText();
            String b3 = buttons[condition[2]].getText();

            if (!b1.equals("") && b1.equals(b2) && b2.equals(b3)) {
                JOptionPane.showMessageDialog(null, "Player " + b1 + " wins!");
                resetGame();
                return;
            }
        }

        // Check for a tie game
        boolean tie = true;
        for (JButton b : buttons) {
            if (b.getText().equals("")) {
                tie = false;
                break;
            }
        }
        if (tie) {
            JOptionPane.showMessageDialog(null, "It's a tie!");
            resetGame();
        }
    }

    // 5. Clear the board for a new game
    private static void resetGame() {
        for (JButton b : buttons) {
            b.setText("");
        }
        xTurn = true;
    }
}