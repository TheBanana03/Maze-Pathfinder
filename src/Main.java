public class Main {
    public static void main(String[] args) {
        Maze maze = new Maze("src/maze.txt");
        AStar aStar = new AStar(maze);
        aStar.exploreFrontier();
        maze.displayMaze();
    }
}