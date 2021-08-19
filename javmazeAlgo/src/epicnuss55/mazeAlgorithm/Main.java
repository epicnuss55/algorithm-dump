package epicnuss55.mazeAlgorithm;

import java.util.ArrayList;
import java.util.Stack;

public class Main {
    public static final String Wall = "#";
    public static final String Visited = "_";
    public static final String NotVisited = "O";
    public static final String WallGen = "@";
    public static final String FloorGen = "!";
    public static final String ExitGen = "E";

    public enum Direction {
        up, down, left, right
    }
    public static String[][] maze(int x, int y, boolean weirdGen) {
        //creates empty
        String[][] emptyMaze = new String[1+(y*2)][1+(x*2)];
        for (int i = 0; i < emptyMaze.length; i++) {
            for (int k = 0; k < emptyMaze[i].length; k++) {
                if (i%2 == 0) {
                    emptyMaze[i][k] = Wall;
                } else if (k%2 == 0) {
                    emptyMaze[i][k] = Wall;
                } else {
                    emptyMaze[i][k] = NotVisited;
                }
            }
        }

        //creates random maze
        int currentX = 1;
        int currentY = 1;
        Stack<Integer> xStack = new Stack<Integer>();
        Stack<Integer> yStack = new Stack<Integer>();
        xStack.push(currentX);
        yStack.push(currentY);

        while (!(xStack.size() == 0 && yStack.size() == 0)) {
            emptyMaze[currentY][currentX] = Visited;
            ArrayList<Direction> availableDirections = new ArrayList<Direction>();
            if (currentX-2 > 0 && emptyMaze[currentY][currentX-2] != Visited) {
                availableDirections.add(Direction.left);
            }
            if (currentX+2 < 1+(x*2)) {
                if (emptyMaze[currentY][currentX+2] != Visited)
                availableDirections.add(Direction.right);
            }
            if (currentY-2 > 0 && emptyMaze[currentY-2][currentX] != Visited) {
                availableDirections.add(Direction.up);
            }
            if (currentY+2 < 1+(y*2)) {
                if (!emptyMaze[currentY + 2][currentX].equals(Visited))
                availableDirections.add(Direction.down);
            }

            if (availableDirections.size() > 0) {
                switch (availableDirections.get((int)(Math.random()*availableDirections.size()))) {
                    case up -> {
                        emptyMaze[currentY-1][currentX] = Visited;
                        currentY = currentY-2;
                        xStack.push(currentX);
                        yStack.push(currentY);
                        break;
                    }
                    case down -> {
                        emptyMaze[currentY+1][currentX] = Visited;
                        currentY = currentY+2;
                        xStack.push(currentX);
                        yStack.push(currentY);
                        break;
                    }
                    case left -> {
                        emptyMaze[currentY][currentX-1] = Visited;
                        currentX = currentX-2;
                        xStack.push(currentX);
                        yStack.push(currentY);
                        break;
                    }
                    case right -> {
                        emptyMaze[currentY][currentX+1] = Visited;
                        currentX = currentX+2;
                        xStack.push(currentX);
                        yStack.push(currentY);
                        break;
                    }
                }
            } else {
                xStack.pop();
                yStack.pop();
                if (xStack.size() > 0 && yStack.size() > 0) {
                    currentX = xStack.get(xStack.size() - 1);
                    currentY = yStack.get(yStack.size() - 1);
                }
            }
        }

        if (weirdGen) {
            for (int i = 1; i < emptyMaze.length - 1; i++) {
                for (int k = 1; k < emptyMaze[i].length - 1; k++) {
                    if ((int) (Math.random() * 100) > 95) {
                        switch ((int) (Math.random() * 3)) {
                            case 1 -> {
                                for (int m = 0; m < 3; m++) {
                                    for (int n = 0; n < 2; n++) {
                                        if (i + m < emptyMaze.length - 1 && k + n < emptyMaze[i + m].length - 1)
                                            emptyMaze[i + m][k + n] = Visited;
                                    }
                                }
                            }
                            case 2 -> {
                                for (int m = 0; m < 2; m++) {
                                    for (int n = 0; n < 3; n++) {
                                        if (i + m < emptyMaze.length - 1 && k + n < emptyMaze[i + m].length - 1)
                                            emptyMaze[i + m][k + n] = Visited;
                                    }
                                }
                            }
                            case 3 -> {
                                for (int m = 0; m < 2; m++) {
                                    for (int n = 0; n < 2; n++) {
                                        if (i + m < emptyMaze.length - 1 && k + n < emptyMaze[i + m].length - 1)
                                            emptyMaze[i + m][k + n] = Visited;
                                    }
                                }
                            }
                        }
                    } else if ((int) (Math.random() * 100) < 3) {
                        emptyMaze[i][k] = Visited;
                    }
                }
            }
        }

        emptyMaze[(int) (Math.random()*((y*2)-1))+1][(int) (Math.random()*((y*2)-1))+1] = ExitGen;

        for (int i = 0; i < emptyMaze.length; i++) {
            int xwhereabouts = (int) (Math.random()*((x*2)-1))+1;
            if ((int) (Math.random()*100) <= 5) {
                emptyMaze[i][xwhereabouts] = ExitGen;
            } else if ((int) (Math.random()*100) <= 30) {
                if (emptyMaze[i][xwhereabouts] == Wall)
                    emptyMaze[i][xwhereabouts] = WallGen;
                else if (emptyMaze[i][xwhereabouts] == Visited)
                    emptyMaze[i][xwhereabouts] = FloorGen;
            }
        }
        return emptyMaze;
    }

    public static void main(String[] args) {
        String[][] maze = maze(50,25, true);

        for (int i = 0; i < maze.length; i++) {
            for (int k = 0; k < maze[i].length; k++) {
                System.out.print(maze[i][k]);
            }
            System.out.println();
        }
    }
}
