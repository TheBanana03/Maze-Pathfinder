import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;



public class Maze {

    public static final String stringOfPath = "   ";
    public static final String stringOfWall = "[" + (char)(9632) + "]";
    public static final String stringOfStart = "[S]";
    public static final String stringOfGoal = "[G]";
    public static final String stringOfExplored = " . ";
    public static final String stringOfSolution = " o ";


    public String fileDirectory;

    public int mazeSize;
    public Node[][] Maze;
    public Node startNode, goalNode;


    public Maze (String fileDirectory) {

        readFile(fileDirectory);
    }


    public void readFile(String fileDirectory) {
        try {
            // Initialize file and scanner
            File mazeFile = new File(fileDirectory);
            Scanner fileInput = new Scanner(mazeFile);

            // Get maze size
            this.mazeSize = Integer.parseInt(fileInput.nextLine());
            this.Maze = new Node[mazeSize][mazeSize];

            // Store maze txt file in linked list
            for (int i = 0; i < this.mazeSize; i++) {
                String s = fileInput.nextLine();
                for (int j = 0; j < this.mazeSize; j++) {
                    if (s.charAt(j) == '.') {
                        this.Maze[i][j] = new Node(stringOfPath, j, i, 0);
                    }
                    else if (s.charAt(j) == '#') {
                        this.Maze[i][j] = new Node(stringOfWall, j, i, 1);
                    }
                    else if (s.charAt(j) == 'S') {
                        this.Maze[i][j] = new Node(stringOfStart, j, i, 0);
                        this.startNode = this.Maze[i][j];
                    }
                    else if (s.charAt(j) == 'G') {
                        this.Maze[i][j] = new Node(stringOfGoal, j, i, 0);
                        this.goalNode = this.Maze[i][j];
                    }
                }
            }

            fileInput.close();

        } catch (FileNotFoundException e) {
            System.out.println("ERROR: File error.");
        }
    }

    public void displayMaze() {

        for (int i = 0; i < this.mazeSize; i++) {
            for (int j = 0; j < this.mazeSize; j++) {
                if (this.Maze[i][j].nodeStatus == 3 &&
                        this.Maze[i][j] != goalNode &&
                        this.Maze[i][j] != startNode)
                    this.Maze[i][j].str = stringOfExplored;
                else if (this.Maze[i][j].nodeStatus == 4 &&
                        this.Maze[i][j] != goalNode &&
                        this.Maze[i][j] != startNode)
                    this.Maze[i][j].str = stringOfSolution;
                System.out.print(this.Maze[i][j].str + " ");
            }
            System.out.println();
        }
    }

    public void getNeighbors(Node currentNode, Node[] neighbors) {

        int i = 0;

        if (currentNode.x - 1 >= 0) {
            if (this.Maze[currentNode.y][currentNode.x - 1].isExplorable()) {
                neighbors[i] = this.Maze[currentNode.y][currentNode.x - 1];
                i++;
            }
        }
        if (currentNode.x + 1 < this.mazeSize) {
            if (this.Maze[currentNode.y][currentNode.x + 1].isExplorable()) {
                neighbors[i] = this.Maze[currentNode.y][currentNode.x + 1];
                i++;
            }
        }
        if (currentNode.y - 1 >= 0) {
            if (this.Maze[currentNode.y - 1][currentNode.x].isExplorable()) {
                neighbors[i] = this.Maze[currentNode.y - 1][currentNode.x];
                i++;
            }
        }
        if (currentNode.y + 1 < this.mazeSize) {
            if (this.Maze[currentNode.y + 1][currentNode.x].isExplorable()) {
                neighbors[i] = this.Maze[currentNode.y + 1][currentNode.x];
                i++;
            }
        }
    }
}