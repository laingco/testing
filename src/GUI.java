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
    
    ImageIcon temp1 = new ImageIcon("src/783503.png");
    Image image = temp1.getImage();
    Image newimg = image.getScaledInstance(15, 15,  java.awt.Image.SCALE_SMOOTH);
    ImageIcon resetImage = new ImageIcon(newimg); 

    ImageIcon temp2 = new ImageIcon("src/7628490.png");
    Image image2 = temp2.getImage();
    Image newimg2 = image2.getScaledInstance(30, 30,  java.awt.Image.SCALE_SMOOTH);
    ImageIcon flagImage = new ImageIcon(newimg2); 

    JButton reset = new JButton("Reset", resetImage);
    JMenuItem easy = new JMenuItem("Easy");
    JMenuItem medium = new JMenuItem("Medium");
    JMenuItem hard = new JMenuItem("Hard");
    JMenuItem whiteAndGrey = new JMenuItem("White and Grey (default)");
    JPanel playArea = new JPanel();
    int elapsedTime = 0;
    JLabel time = new JLabel("Time: " + elapsedTime + "s");
    Timer timer;
    boolean plantedMines = false;

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

    public static int[][] gridPicker(){
        switch (difficulty) {
            case 1:
                return(easyGrid);
            case 2:
                return(mediumGrid);
            case 3:
                return(hardGrid);
            default:
                return(null);
        }
    }
    
    public void guiMethod(){
        jframe = new JFrame("Minesweeper test");   //create JFrame object
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setSize(1050,900);         //set size of GUI screen
        jframe.setVisible(true);

        JPanel score = new JPanel();
        JPanel playArea = new JPanel();
        playArea.setLayout(new GridLayout(gridSize[2*difficulty-2], gridSize[2*difficulty-1]));

        JLabel flags = new JLabel("Flags left: " + flagSize[difficulty-1]);
        elapsedTime = 0;
        time.setText("Time: " + elapsedTime + "s");
        JMenuBar menu = new JMenuBar();
        JMenu difficultyDropdown = new JMenu("Difficulty");
        JMenu colours = new JMenu("Colours");
        JLabel difficultyLabel = new JLabel("Difficulty: " + difficultyPicker(0));

        //JButton tiles[][] = new JButton[gridSize[2*difficulty-2]][gridSize[2*difficulty-1]];
        for (int y = 0; y < gridSize[2*difficulty-2]; y++){
            for (int x = 0; x < gridSize[2*difficulty-1]; x++){
                //int number = x + 1 + (gridSize[2*difficulty-1] * y);
                int currentGrid[][] = new int[gridSize[2*difficulty-2]][gridSize[2*difficulty-1]];
                currentGrid = gridPicker();
                if(currentGrid[y][x] == 0){
                    tiles[y][x] = new JButton();
                } else if(currentGrid[y][x] == -3){
                    tiles[y][x] = new JButton(flagImage);
                }else{
                    tiles[y][x] = new JButton(Integer.toString(currentGrid[y][x]));
                }
                tiles[y][x].addMouseListener(this);
                if(y%2==0){
                    if(x%2==0){
                        tiles[y][x].setBackground(Color.WHITE);
                        tiles[y][x].setOpaque(true);
                        tiles[y][x].setBorderPainted(false);
                    } else{
                        tiles[y][x].setBackground(Color.LIGHT_GRAY);
                        tiles[y][x].setOpaque(true);
                        tiles[y][x].setBorderPainted(false);
                    }
                } else if (!(x%2==0)){
                    tiles[y][x].setBackground(Color.WHITE);
                    tiles[y][x].setOpaque(true);
                    tiles[y][x].setBorderPainted(false);
                } else{
                    tiles[y][x].setBackground(Color.LIGHT_GRAY);
                    tiles[y][x].setOpaque(true);
                    tiles[y][x].setBorderPainted(false);
                }
                playArea.add(tiles[y][x]);
            }
        }

        menu.add(difficultyDropdown);
        menu.add(colours);
        difficultyDropdown.add(easy);
        difficultyDropdown.add(medium);
        difficultyDropdown.add(hard);
        colours.add(whiteAndGrey);
        easy.addMouseListener(this);
        medium.addMouseListener(this);
        hard.addMouseListener(this);
        reset.addMouseListener(this);
        whiteAndGrey.addMouseListener(this);

        score.add(reset);
        score.add(flags);
        score.add(time);
        score.add(menu);
        score.add(difficultyLabel);
        

        jframe.getContentPane().add(BorderLayout.NORTH, score);
        jframe.getContentPane().add(BorderLayout.CENTER, playArea);
        jframe.setVisible(true);
        
        timer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateTimeLabel();
            }
        });
    }

    private void updateTimeLabel() {
        elapsedTime++;
        time.setText("Time: " + elapsedTime + "s");
    }

    public void plantMines(int xx, int yy){        
        int z = 0;
        while (z < flagSize[difficulty-1]+1 && !plantedMines){
            int randX = (int)Math.floor(Math.random()*gridSize[2*difficulty-1]);
            int randY = (int)Math.floor(Math.random()*gridSize[2*difficulty-2]);
            if (difficulty == 1){
                if (!(easyGrid[randY][randX] == -1) && !(randX == xx && randY == yy)){
                    easyGrid[randY][randX] = -1;
                    z++;
                }
            } else if (difficulty == 2){
                if (!(mediumGrid[randY][randX] == -1) && !(randX == xx && randY == yy)){
                    mediumGrid[randY][randX] = -1;
                    z++;
                }
            } else if (difficulty == 3){
                if (!(hardGrid[randY][randX] == -1) && !(randX == xx && randY == yy)){
                    hardGrid[randY][randX] = -1;
                    z++;
                }
            }
        }
        if (!plantedMines){
            gui sm=new gui();
            sm.guiMethod();
        }
        plantedMines = true;
    }

    public void resetGrids(){
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
    }

    public void mousePressed(MouseEvent e) {
        gui sm=new gui();
        int currentGrid[][] = gridPicker();
        for (int y = 0; y < gridSize[2*difficulty-2]; y++) {
            for (int x = 0; x < gridSize[2*difficulty-1]; x++) {
                if (tiles[y][x] == e.getSource() && e.getButton() == MouseEvent.BUTTON1) {
                    System.out.println("Clicked button at (x, y): (" + (x + 1) + ", " + (y + 1) + ")");
                    if (!(currentGrid[y][x] == -3)){
                        tiles[y][x].setText("O");
                    }
                    if (plantedMines){
                        plantMines(x, y);
                    }
                    if (!timer.isRunning()) {
                        timer.start();
                    }
                } else if (tiles[y][x] == e.getSource() && e.getButton() == MouseEvent.BUTTON3) {
                    System.out.println("Flagged button at (x, y): (" + (x + 1) + ", " + (y + 1) + ")");
                    if (currentGrid[y][x] == -3){
                        tiles[y][x].setIcon(null);
                        currentGrid[y][x] = -2;
                    } else{   
                        currentGrid[y][x] = -3;
                        tiles[y][x].setText("");
                        tiles[y][x].setIcon(flagImage);
                    }
                    if (!timer.isRunning()) {
                        timer.start();
                    }
                } else if (e.getSource() == whiteAndGrey){
                    if(y%2==0){
                        if(x%2==0){
                            tiles[y][x].setBackground(Color.WHITE);
                            tiles[y][x].setOpaque(true);
                            tiles[y][x].setBorderPainted(false);
                        } else{
                            tiles[y][x].setBackground(Color.LIGHT_GRAY);
                            tiles[y][x].setOpaque(true);
                            tiles[y][x].setBorderPainted(false);
                        }
                    } else if (!(x%2==0)){
                        tiles[y][x].setBackground(Color.WHITE);
                        tiles[y][x].setOpaque(true);
                        tiles[y][x].setBorderPainted(false);
                    } else{
                        tiles[y][x].setBackground(Color.LIGHT_GRAY);
                        tiles[y][x].setOpaque(true);
                        tiles[y][x].setBorderPainted(false);
                    }
                }
                
            }
        }

        if (e.getSource() == easy){
            difficulty = 1;
            resetGrids();
            sm.guiMethod();
        } else if (e.getSource() == medium){
            difficulty = 2;
            resetGrids();
            sm.guiMethod();
        } else if (e.getSource() == hard){
            difficulty = 3;
            resetGrids();
            sm.guiMethod();
        } else if (e.getSource() == reset){
            resetGrids();
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
        gui thing = new gui();
        thing.resetGrids();

        gui sm=new gui();
        sm.guiMethod();
    }
}