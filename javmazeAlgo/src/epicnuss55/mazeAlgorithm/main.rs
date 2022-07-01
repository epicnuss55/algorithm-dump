enum Direction {
    Up,
    Down,
    Left,
    Right,
}

const WALL: &str = "#";
const VISITED: &str = " ";
const NOT_VISITED: &str = "O";

use rand::Rng;

fn gen_maze(x: usize, y: usize) {
    let mut empty_maze: Vec<Vec<&str>> = vec![vec!["#"; 1 + (x * 2)]; 1 + (y * 2)];

    for i in 0..empty_maze.len() {
        for k in 0..empty_maze[i].len() {
            if i % 2 == 0 {
                empty_maze[i][k] = WALL;
            } else if k % 2 == 0 {
                empty_maze[i][k] = WALL;
            } else {
                empty_maze[i][k] = NOT_VISITED;
            }
        }
    }

    let mut current_x: usize = 1;
    let mut current_y: usize = 1;

    let mut x_stack = Vec::from([current_x]);
    let mut y_stack = Vec::from([current_y]);

    while !(x_stack.len() == 0 && y_stack.len() == 0) {
        empty_maze[current_y][current_x] = VISITED;

        let mut avaiable_directions: Vec<Direction> = vec![];
        if (current_x as isize) - 2 > 0 {
            if empty_maze[current_y][current_x - 2] != VISITED {
                avaiable_directions.push(Direction::Left);
            }
        }
        if current_x + 2 < 1 + (x * 2) {
            if empty_maze[current_y][current_x + 2] != VISITED {
                avaiable_directions.push(Direction::Right);
            }
        }
        if (current_y as isize) - 2 > 0 {
            if empty_maze[current_y - 2][current_x] != VISITED {
                avaiable_directions.push(Direction::Up);
            }
        }
        if current_y + 2 < 1 + (y * 2) {
            if empty_maze[current_y + 2][current_x] != VISITED {
                avaiable_directions.push(Direction::Down);
            }
        }

        if avaiable_directions.len() > 0 {
            let randint = rand::thread_rng().gen_range(0..avaiable_directions.len());
            match avaiable_directions[randint] {
                Direction::Up => {
                    empty_maze[current_y - 1][current_x] = VISITED;
                    current_y = current_y - 2;
                    x_stack.push(current_x);
                    y_stack.push(current_y);
                }
                Direction::Down => {
                    empty_maze[current_y + 1][current_x] = VISITED;
                    current_y = current_y + 2;
                    x_stack.push(current_x);
                    y_stack.push(current_y);
                }
                Direction::Left => {
                    empty_maze[current_y][current_x - 1] = VISITED;
                    current_x = current_x - 2;
                    x_stack.push(current_x);
                    y_stack.push(current_y);
                }
                Direction::Right => {
                    empty_maze[current_y][current_x + 1] = VISITED;
                    current_x = current_x + 2;
                    x_stack.push(current_x);
                    y_stack.push(current_y);
                }
            }
        } else {
            x_stack.pop();
            y_stack.pop();

            if x_stack.len() != 0 && y_stack.len() != 0 {
                current_x = x_stack[x_stack.len() - 1];
                current_y = y_stack[y_stack.len() - 1];
            }
        }
    }

    // print gen
    for i in 0..empty_maze.len() {
        for k in 0..empty_maze[i].len() {
            print!("{}", empty_maze[i][k]);
        }
        println!();
    }
}

fn main() {
    gen_maze(20, 20);
}
