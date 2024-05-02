import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class gui implements MouseListener{
    private static int difficulty = 2;
    private static int gridSize[] = {8, 10, 14, 18, 20, 24};
    private static int flagSize[] = {10, 40, 99};
    private static int easyGrid[][] = new int[gridSize[0]][gridSize[1]];
    private static int mediumGrid[][] = new int[gridSize[2]][gridSize[3]];
    private static int hardGrid[][] = new int[gridSize[4]][gridSize[5]];
    private static JFrame jframe;
    private JButton tiles[][] = new JButton[20][24];
    JButton reset = new JButton("Reset");
    JMenuItem easy = new JMenuItem("Easy");
    JMenuItem medium = new JMenuItem("Medium");
    JMenuItem hard = new JMenuItem("Hard");
    
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

        JLabel flags = new JLabel("Flags left: " + flagSize[difficulty-1]);
        JLabel time = new JLabel("Time: 0s");
        JMenuBar menu = new JMenuBar();
        JMenu difficultyDropdown = new JMenu("Difficulty");
        JLabel difficultyLabel = new JLabel("Difficulty: " + difficultyPicker(0));

        //JButton tiles[][] = new JButton[gridSize[2*difficulty-2]][gridSize[2*difficulty-1]];
        for (int y = 0; y < gridSize[2*difficulty-2]; y++){
            for (int x = 0; x < gridSize[2*difficulty-1]; x++){
                int number = x + 1 + (gridSize[2*difficulty-1] * y);
                tiles[y][x] = new JButton(Integer.toString(number));
                tiles[y][x].addMouseListener(this);
                playArea.add(tiles[y][x]);
            }
        }

        menu.add(difficultyDropdown);
        difficultyDropdown.add(easy);
        difficultyDropdown.add(medium);
        difficultyDropdown.add(hard);
        easy.addMouseListener(this);
        medium.addMouseListener(this);
        hard.addMouseListener(this);

        score.add(reset);
        score.add(flags);
        score.add(time);
        score.add(menu);
        score.add(difficultyLabel);
        

        jframe.getContentPane().add(BorderLayout.NORTH, score);
        jframe.getContentPane().add(BorderLayout.CENTER, playArea);
        jframe.setVisible(true);
    }

    public void mousePressed(MouseEvent e) {
        //JButton e.getSource() = (JButton)e.getSource();
        //JMenuItem e.getSource() = (JMenuItem)e.getSource();
        for (int y = 0; y < gridSize[2*difficulty-2]; y++) {
            for (int x = 0; x < gridSize[2*difficulty-1]; x++) {
                if (tiles[y][x] == e.getSource() && e.getButton() == MouseEvent.BUTTON1) {
                    System.out.println("Clicked button at (x, y): (" + (x + 1) + ", " + (y + 1) + ")");
                    tiles[y][x].setText("O");
                } else if (tiles[y][x] == e.getSource() && e.getButton() == MouseEvent.BUTTON3) {
                    System.out.println("Clicked button at (x, y): (" + (x + 1) + ", " + (y + 1) + ")");
                    tiles[y][x].setText("X");
                }
            }
        }

        gui sm=new gui();

        if (e.getSource() == easy){
            difficulty = 1;
            sm.guiMethod();
        } else if (e.getSource() == medium){
            difficulty = 2;
            sm.guiMethod();
        } else if (e.getSource() == hard){
            difficulty = 3;
            sm.guiMethod();
        } else if (e.getSource() == reset){
            sm.guiMethod();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
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
}