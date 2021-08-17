package epicnuss55.mazeAlgorithm;

import java.util.ArrayList;
import java.util.Stack;

public class Main {
    public static final String Wall = "#";
    public static final String Visited = " ";
    public static final String NotVisited = "O";

    public enum Direction {
        up, down, left, right
    }
    public static String[][] maze(int x, int y) {
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

        return emptyMaze;
    }

    public static void main(String[] args) {
        String[][] maze = maze(50,25);

        for (int i = 0; i < maze.length; i++) {
            for (int k = 0; k < maze[i].length; k++) {
                System.out.print(maze[i][k]);
            }
            System.out.println();
        }
    }
}
