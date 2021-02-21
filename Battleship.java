import java.util.*;
import java.util.Scanner;

public class Battleship
{
    public static int numHitsPlayer;
    public static int numHitsComputer;
    public static void main(String[] args)
    {
        Player player = new Player();
        Player computer = new Player();
        for(int i = 2; i < 6; i++)
        {
            Ship ship = new Ship(i);
            do {
                int col;
                int row;
                int direction;
                do {
                    col = readInt("Enter the column (1-10) you would like to place your ship of length " + i +  " in: ");
                    if(!(col <= 10 && col > 0)) {
                        System.out.println("Must be between 1 and 10!");
                    }
                } while(!(col <= 10 && col > 0));
                do {
                    row = readInt("Enter the row (1-10) you would like to place your ship of length " + i +  " in: ");
                    if(!(row <= 10 && row > 0)) {
                        System.out.println("Must be between 1 and 10!");
                    }
                } while(!(row <= 10 && row > 0));
                do {
                    direction = readInt("Enter the desired direction (0 = horizontal, 1 = vertical): ");
                    if(!(direction == 0 || direction == 1)) {
                        System.out.println("Must be 0 or 1!");
                    }
                } while(!(direction == 0 || direction == 1));
                player.chooseShipLocation(ship, row-1, col-1, direction);
            } while(Grid.shipHere != 0);
            player.getGrid1().printShips();
            System.out.println("This is your current board of ships.");
            if(i == 3)
            {
                Ship ship1 = new Ship(i);
            do {
                int col;
                int row;
                int direction;
                do {
                    col = readInt("Enter the column (1-10) you would like to place your ship of length " + i +  " in: ");
                    if(!(col <= 10 && col > 0)) {
                        System.out.println("Must be between 1 and 10!");
                    }
                } while(!(col <= 10 && col > 0));
                do {
                    row = readInt("Enter the row (1-10) you would like to place your ship of length " + i +  " in: ");
                    if(!(row <= 10 && row > 0)) {
                        System.out.println("Must be between 1 and 10!");
                    }
                } while(!(row <= 10 && row > 0));
                do {
                    direction = readInt("Enter the desired direction (0 = horizontal, 1 = vertical): ");
                    if(!(direction == 0 || direction == 1)) {
                        System.out.println("Must be 0 or 1!");
                    }
                } while(!(direction == 0 || direction == 1));
                player.chooseShipLocation(ship1, row-1, col-1, direction);
            } while(Grid.shipHere != 0);
            player.getGrid1().printShips();
            System.out.println("This is your current board of ships.");
            }
            
        }
        for(int i = 2; i < 6; i++) {
            Ship ship = new Ship(i);
            int row;
            int col;
            int direction;
            do {
                row = (int)(Math.random() * (10)) % (10-i);
                col = (int)(Math.random() * (10)) % (10-i);
                direction = (int)(Math.random() * 10) % 2;
                computer.chooseShipLocation(ship, row, col, direction);
            } while(Grid.shipHere != 0);
            if(i == 3) {
                do {
                    row = (int)(Math.random() * (10)) % (10-i);
                    col = (int)(Math.random() * (10)) % (10-i);
                    direction = (int)(Math.random() * 10) % 2;
                    computer.chooseShipLocation(ship, row, col, direction);
                } while(Grid.shipHere != 0);
            }
        }
        while(true) {
            askForGuess(player, computer);
            player.printMyGuesses();
            System.out.println("These are your current guesses.");
            player.printOpponentGuesses();
            System.out.println("These are the computer's guesses.");
            if(numHitsPlayer == 17) {
                System.out.println("Game Over! You Win!");
                return;
            }
            String x = readLine("It is the computer's turn. Press enter to confirm.");
            int row = (int)(Math.random()*10);
            int col = (int)(Math.random()*10);
            if(player.getGrid1().get(row, col).checkHit()) {
                System.out.println("Computer guessed a hit!");
                numHitsComputer++;
                player.getGrid1().get(row, col).markHit();
            }
            else {
                System.out.println("Computer missed!");
                player.getGrid1().get(row, col).markMiss();
            }
            if(numHitsComputer == 17) {
                System.out.println("Game Over! Computer Wins!");
                return;
            }
            player.printMyGuesses();
            System.out.println("These are your current guesses.");
            player.printOpponentGuesses();
            System.out.println("These are the computer's guesses.");
            player.getGrid1().printShips();
                System.out.println("This is your current board of ships.");
            String y = readLine("It is your turn. Press enter to confirm.");
        }
    }

    public void askForGuess(Player p1, Player p2)
    {
        int col = readInt("Enter the column you would like to guess: ");
        int row = readInt("Enter the row you would like to guess: ");
        if(p2.getGrid1().get(row-1, col-1).checkHit())
        {
            numHitsPlayer++;
            System.out.println("HIT!");
            p1.getGrid2().get(row-1, col-1).markHit();
            
        }
        else {
            p1.getGrid2().get(row-1, col-1).markMiss();
            System.out.println("MISS!");
        }
    }
}

public class Ship
{
    private int row;
    private int col;
    private int length;
    private int direction;
    public static final int UNSET = -1;
    public static final int HORIZONTAL = 0;
    public static final int VERTICAL = 1;
    private int numHits = 0;
    
    public Ship(int length)
    {
        this.length = length;
        this.direction = UNSET;
        row = -1;
        col = -1;
    }
    // Has the location been initialized
    public boolean isLocationSet()
    {
        boolean ans;
        if(row != -1 && col != -1)
        {
            ans = true;
        }
        else
        {
            ans = false;
        }
        return ans;
    }

    // Has the direction been initialized
    public boolean isDirectionSet()
    {
        boolean ans;
        if(this.direction != UNSET)
        {
            ans = true;
        }
        else
        {
            ans = false;
        }
        return ans;
    }

    // Set the location of the ship 
    public void setLocation(int row, int col)
    {
        this.row = row;
        this.col = col;
    }

    // Set the direction of the ship
    public void setDirection(int direction)
    {
        this.direction = direction;
    }

    // Getter for the row value
    public int getRow()
    {
        return row;
    }

    // Getter for the column value
    public int getCol()
    {
        return col;
    }

    // Getter for the length of the ship
    public int getLength()
    {
        return length;
    }

    // Getter for the direction
    public int getDirection()
    {
        return direction;
    }
    // Helper method to get a string value from the direction
    private String directionToString()
    {
        String sdirection;
        if(direction == -1)
        {
            sdirection = "unset";
        }
        if(direction == 0)
        {
            sdirection = "horizontal";
        }
        else
        {
            sdirection = "vertical";
        }
        return sdirection;
    }

    // Helper method to get a (row, col) string value from the location
    private String locationToString()
    {
        if(row == -1 && col == -1)
        {
            return "(unset location)";
        }
        return "("+Integer.toString(row) + ", " + Integer.toString(col) + ")";
    }

    // toString value for this Ship
    public String toString()
    {
        if(this.getDirection() == -1)
        {
            return "unset direction ship of length " + this.getLength() + " at " + this.locationToString();
        }
        else
        {
            return this.directionToString() + " ship of length " + this.getLength() + " at " + this.locationToString();
        }
    }
}

public class Location
{
    public static final int UNGUESSED = 0;
    public static final int HIT = 1;
    public static final int MISSED = 2;
    private boolean shipPresent;
    private int status;
    // Location constructor. 
    public Location()
    {
        status = UNGUESSED;
        shipPresent = false;
    }

    // Was this Location a hit?
    public boolean checkHit()
    {
        if(shipPresent)
        {
            this.status = HIT;
            return true;
        }
        else
        {
            this.status = MISSED;
            return false;
        }
    }

    // Was this location a miss?
    public boolean checkMiss()
    {
        boolean ans;
        if(shipPresent)
        {
            this.status = HIT;
            return false;
        }
        else
        {
            this.status = MISSED;
            return true;
        }
    }

    // Was this location unguessed?
    public boolean isUnguessed()
    {
        if(this.status != 0)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    // Mark this location a hit.
    public void markHit()
    {
        this.status = HIT;
    }

    // Mark this location a miss.
    public void markMiss()
    {
        this.status = MISSED;
    }

    // Return whether or not this location has a ship.
    public boolean hasShip()
    {
        if(shipPresent)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    // Set the value of whether this location has a ship.
    public void setShip(boolean val)
    {
        this.shipPresent = val;
    }

    // Set the status of this Location.
    public void setStatus(int status)
    {
        this.status = status;
    }

    // Get the status of this Location.
    public int getStatus()
    {
        return status;
    }
}

public class Grid
{
    private Location[][] grid;

    // Constants for number of rows and columns.
    public static final int NUM_ROWS = 10;
    public static final int NUM_COLS = 10;
    public static int shipHere = 0;
    
    public Grid()
    {
        grid = new Location[NUM_ROWS][NUM_COLS];
        for(int i = 0; i < grid.length; i++)
        {
            for(int t = 0; t < grid.length; t++)
            {
                grid[i][t] = new Location();
            }
        }
    }
    public void markHit(int row, int col)
    {
        this.grid[row][col].markHit(); 
    }
    public void markMiss(int row, int col)
    {
        this.grid[row][col].markMiss(); 
    }
    public int getStatus(int row, int col)
    {
        return this.grid[row][col].getStatus();
    }
    public void setStatus(int row, int col, int status)
    {
        this.grid[row][col].setStatus(status);
    }
    public boolean alreadyGuessed(int row, int col)
    {
        if(this.grid[row][col].getStatus() == 0)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    public void setShip(int row, int col, boolean val)
    {
        this.grid[row][col].setShip(val);
    }
    public boolean hasShip(int row, int col)
    {
        return this.grid[row][col].hasShip();
    }
    public Location get(int row, int col)
    {
        return this.grid[row][col];
    }
    public int numRows()
    {
        return NUM_ROWS;
    }
    public int numCols()
    {
        return NUM_COLS;
    }
    public void printStatus()
    {
        System.out.println("  1 2 3 4 5 6 7 8 9 10");
        for(int i = 0; i < NUM_ROWS; i++)
        {
            int letter = (i+1);
            if(letter == 10) {
                System.out.print(letter);
            }
            else {
                System.out.print(letter + " ");
            }
            for(int t = 0; t < NUM_COLS; t++)
            {
                if(this.grid[i][t].getStatus() == 0)
                {
                    System.out.print("- ");
                }
                if(this.grid[i][t].getStatus() == 1)
                {
                    System.out.print("X ");
                }
                if(this.grid[i][t].getStatus() == 2)
                {
                    System.out.print("O ");
                }
            }
            System.out.println("");
        }
    }
    public void printShips()
    {
        System.out.println("  1 2 3 4 5 6 7 8 9 10");
        for(int i = 0; i < NUM_ROWS; i++)
        {
            int letter = (i+1);
            if(letter == 10) {
                System.out.print(letter);
            }
            else {
                System.out.print(letter + " ");
            }
            for(int t = 0; t < NUM_COLS; t++)
            {
                if(this.grid[i][t].hasShip())
                {
                    System.out.print("X ");
                }
                else
                {
                    System.out.print("- ");
                }
            }
            System.out.println("");
        }
    }
    public void addShip(Ship s)
    {
        if(s.getDirection() == 0)
        {
            for(int i = s.getCol(); i < (s.getCol() + s.getLength()); i++)
            {
                if(i > 9 || this.grid[s.getRow()][i].hasShip())
                {
                    shipHere = 1;
                    System.out.println("There is already a ship in this spot or it is off the board. Please choose another.");
                    return;
                }
            }
            shipHere = 0;
            for(int i = s.getCol(); i < (s.getCol() + s.getLength()); i++)
            {
                this.grid[s.getRow()][i].setShip(true);
            }
            
        }
        if(s.getDirection() == 1)
        {
            for(int i = s.getRow(); i < (s.getRow() + s.getLength()); i++)
            {
                if(i > 9 || this.grid[i][s.getCol()].hasShip())
                {
                    shipHere = 1;
                    System.out.println("There is already a ship in this spot or it is off the board. Please choose another.");
                    return;
                }
            }
            shipHere = 0;
            for(int i = s.getRow(); i < (s.getRow() + s.getLength()); i++)
            {
                this.grid[i][s.getCol()].setShip(true);
            }
        }
    }
}

public class Player
{
    private static final int[] SHIP_LENGTHS = {2, 3, 3, 4, 5};
    private Grid grid1;
    private Grid grid2;
    public Player()
    {
        grid1 = new Grid();
        grid2 = new Grid();
    }
    
    public void chooseShipLocation(Ship s, int row, int col, int direction)
    {
            s.setLocation(row, col);
            s.setDirection(direction);
            this.grid1.addShip(s);
    }
    public void printMyShips()
    {
        this.grid1.printShips();
    }

    // Print opponent guesses
    public void printOpponentGuesses()
    {
        this.grid1.printStatus();
    }

    // Print your guesses
    public void printMyGuesses()
    {
        this.grid2.printStatus();
    }

    // Record a guess from the opponent
    public void recordOpponentGuess(int row, int col)
    {
        if(this.grid1.get(row, col).hasShip())
        {
            this.grid1.get(row, col).markHit();
            this.grid1.get(row, col).setStatus(1);
        }
        else
        {
            this.grid1.get(row, col).markMiss();
            this.grid1.get(row, col).setStatus(2);
        }
    }
    
    public Grid getGrid1()
    {
        return grid1;
    }
    public Grid getGrid2()
    {
        return grid2;
    }
}

public class Randomizer{

	public static Random theInstance = null;
	
	public Randomizer(){
		
	}
	
	public static Random getInstance(){
		if(theInstance == null){
			theInstance = new Random();
		}
		return theInstance;
	}
	
	/**
	 * Return a random boolean value.
	 * @return True or false value simulating a coin flip.
	 */
	public static boolean nextBoolean(){
		return Randomizer.getInstance().nextBoolean();
	}

	/**
	 * This method simulates a weighted coin flip which will return
	 * true with the probability passed as a parameter.
	 * 
	 * @param	probability	The probability that the method returns true, a value between 0 to 1 inclusive. 
	 * @return True or false value simulating a weighted coin flip.
	 */
	public static boolean nextBoolean(double probability){
		return Randomizer.nextDouble() < probability;
	}
	
	/**
	 * This method returns a random integer.
	 * @return A random integer.
	 */
	public static int nextInt(){
		return Randomizer.getInstance().nextInt();
	}

	/**
	 * This method returns a random integer between 0 and n, exclusive.
	 * @param n	The maximum value for the range.
	 * @return A random integer between 0 and n, exclusive.
	 */
	public static int nextInt(int n){
		return Randomizer.getInstance().nextInt(n);
	}

	/**
	 * Return a number between min and max, inclusive.
	 * @param min	The minimum integer value of the range, inclusive.
	 * @param max	The maximum integer value in the range, inclusive.
	 * @return A random integer between min and max.
	 */
	public static int nextInt(int min, int max){
		return min + Randomizer.nextInt(max - min + 1);
	}

	/**
	 * Return a random double between 0 and 1.
	 * @return A random double between 0 and 1.
	 */
	public static double nextDouble(){
		return Randomizer.getInstance().nextDouble();
	}

	/**
	 * Return a random double between min and max.
	 * @param min The minimum double value in the range.
	 * @param max The maximum double value in the rang.
	 * @return A random double between min and max.
	 */
	public static double nextDouble(double min, double max){
		return min + (max - min) * Randomizer.nextDouble();
	}
}