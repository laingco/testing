import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class gui implements ActionListener{
    private static int difficulty = 2;
    private static int gridSize[] = {8, 10, 14, 18, 20, 24};
    private static int easyGrid[][] = new int[gridSize[0]][gridSize[1]];
    private static int mediumGrid[][] = new int[gridSize[2]][gridSize[3]];
    private static int hardGrid[][] = new int[gridSize[4]][gridSize[5]];
    private static JFrame jframe;
    private JButton tiles[][] = new JButton[gridSize[2*difficulty-2]][gridSize[2*difficulty-1]];
    
    public static String difficultyPicker(int choice){
        if (choice == 0){
            switch (difficulty) {
                case 1:
                    return("Easy");
                case 2:
                    return("Medium");
                case 3:
                    return("Hard");
                default:
                    return("N/A");
            }
        } else{
            return(null);
        }
    }
    
    public void guiMethod(){
        ImageIcon resetImage = new ImageIcon("C:/Users/coope/Documents/java/test/src/783503.png");
        Image image = resetImage.getImage();
        Image newimg = image.getScaledInstance(15, 15,  java.awt.Image.SCALE_SMOOTH);
        resetImage = new ImageIcon(newimg); 

        jframe = new JFrame("Minesweeper test");   //create JFrame object
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setSize(1050,900);         //set size of GUI screen
        jframe.setVisible(true);

        JPanel score = new JPanel();
        JPanel playArea = new JPanel();
        playArea.setLayout(new GridLayout(gridSize[2*difficulty-2], gridSize[2*difficulty-1]));

        JLabel flags = new JLabel("Flags left: 40");
        JLabel time = new JLabel("Time: 0s");
        JButton reset = new JButton("Reset", resetImage);
        JMenuBar menu = new JMenuBar();
        JMenu difficultyDropdown = new JMenu("Difficulty");
        JMenuItem easy = new JMenuItem("Easy");
        JMenuItem medium = new JMenuItem("Medium");
        JMenuItem hard = new JMenuItem("Hard");
        JLabel difficultyLabel = new JLabel("Difficulty: " + difficultyPicker(0));

        //JButton tiles[][] = new JButton[gridSize[2*difficulty-2]][gridSize[2*difficulty-1]];
        for (int y = 0; y < gridSize[2*difficulty-2]; y++){
            for (int x = 0; x < gridSize[2*difficulty-1]; x++){
                int number = x + 1 + (gridSize[2*difficulty-1] * y);
                tiles[y][x] = new JButton(Integer.toString(number));
                playArea.add(tiles[y][x]);
                tiles[y][x].addActionListener(this);
            }
        }

        menu.add(difficultyDropdown);
        difficultyDropdown.add(easy);
        difficultyDropdown.add(medium);
        difficultyDropdown.add(hard);

        score.add(reset);
        score.add(flags);
        score.add(time);
        score.add(menu);
        score.add(difficultyLabel);
        

        jframe.getContentPane().add(BorderLayout.NORTH, score);
        jframe.getContentPane().add(BorderLayout.CENTER, playArea);
        jframe.setVisible(true);
    }

    public static void main(String args[]){
        for (int y = 0; y < gridSize[0]; y++){
            for (int x = 0; x < gridSize[1]; x++){
                easyGrid[y][x] = 0;
            }
        }

        for (int y = 0; y < gridSize[2]; y++){
            for (int x = 0; x < gridSize[3]; x++){
                mediumGrid[y][x] = 0;
            }
        }

        for (int y = 0; y < gridSize[4]; y++){
            for (int x = 0; x < gridSize[5]; x++){
                hardGrid[y][x] = 0;
            }
        }
        
        gui sm=new gui();
        sm.guiMethod();
    }

    public void actionPerformed(ActionEvent e) {
        for (int x = 0; x < gridSize[2*difficulty-2]*gridSize[2*difficulty-1]; x++){
            if (Integer.toString(x+1).equals(e.getActionCommand())){
                int yy = x / gridSize[2*difficulty-1];
                int xx = x % gridSize[2*difficulty-1];
                System.out.println("Clicked button at (x, y): (" + (xx + 1) + ", " + (yy + 1) + ")");
                tiles[yy][xx].setText("O");
            }
        }
    }
}