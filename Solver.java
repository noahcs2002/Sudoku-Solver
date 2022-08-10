import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Solver
{
    private final int GRID_SIZE = 9;
  
    //#region Helpers
    private boolean isNumberInRow(int[][] board, int num, int row) 
    {

        for(int i = 0; i < GRID_SIZE; i ++) 
        {
            if(board[row][i] == num)
                return true;
        }

        return false;
    }

    private boolean isNumberInColumn(int[][] board, int num, int column) 
    {
        for(int i = 0; i < GRID_SIZE; i ++) 
        {

            if(board[i][column] == num)
                return true;

        }

        return false;
    }
    
    private boolean isNumberInThreeByThreeGrid(int[][] board, int num, int row, int column) 
    {

        int localGridRow = row - row % 3;
        int localGridColumn = column - column % 3;

        for(int i = localGridRow ; i < (localGridRow + 3) ; i ++ )
        {
            for(int j = localGridColumn ; j < (localGridColumn + 3) ; j ++)
            {
                if(board[i][j] == num)
                    return true;
            }
        }

        return false;
    }

    public boolean isNumberPlacementValid(int[][] board, int num, int row, int column)
    {
        return !isNumberInColumn(board, num, column) && 
               !isNumberInRow(board, num, row) && 
               !isNumberInThreeByThreeGrid(board, num, row, column);
    }

    public void input()
    {

        Scanner scnr = new Scanner(System.in);

        int[][] board = 
        {
            {}, {}, {}, {}, {}, {}, {}, {}, {},
        };

        for(int index = 0; index < 9; index ++)
        {
            System.out.println("Enter row " + (index + 1) + ": xxxxxxxxx");
            var in = scnr.nextLine();
            char[] arr = in.toCharArray();

            int[] nums = new int[9];

            for(int i = 0; i < 9; i ++)
            {
                nums[i] = Integer.parseInt(""+arr[i]);
            }

            board[index] = nums;
        }

        if(solveBoard(board))
            printBoard(board);
        
        else
            System.out.println("Error");

        scnr.close();
    }

    public void printBoard(int[][] board)
    {
        System.out.println("  ----- ----- -----  ");
        for(int row = 0; row < GRID_SIZE; row ++)
        {
            for(int column = 0; column < GRID_SIZE ; column ++)
            {
                if(column % 3 == 0)
                    System.out.print(" | ");

                System.out.print(board[row][column]);
            }
            System.out.print(" | ");
            System.out.println();

            if(row == 2 || row == 5)
                System.out.println(" | --- | --- | --- | ");

        }
        System.out.println("  ----- ----- -----  ");

    }

    public void createBoardFromFile(String filePath)
    {
        int[][] board = 
        {
            {}, {}, {}, {}, {}, {}, {}, {}, {},
        };

        try 
        {
            File file = new File(filePath);
            Scanner scnr = new Scanner(file);
            int index = 0;

            while(scnr.hasNextLine())
            {
                System.out.println("Enter row " + (index + 1) + ": xxxxxxxxx [BYPASSED BY FILE OVERRIDE]");
                
                char[] arr = scnr.nextLine().toCharArray();
    
                int[] nums = new int[9];
    
                for(int i = 0; i < 9; i ++)
                {
                    nums[i] = Integer.parseInt(""+arr[i]);
                }
    
                board[index] = nums;
                index += 1;
            }

            scnr.close();

            if(solveBoard(board))
                printBoard(board);
            else
                System.out.println("Err");
        } 

        catch (FileNotFoundException e) 
        {
            e.printStackTrace();
        }
    }

    //#endregion
    public boolean solveBoard(int[][] board)
    {
        for(int row = 0; row < GRID_SIZE; row ++)
        {
            for(int column = 0; column < GRID_SIZE; column ++)
            {
                if(board[row][column] == 0)
                {
                    for(int numToAttempt = 1; numToAttempt <= GRID_SIZE; numToAttempt ++)
                    {
                        if(isNumberPlacementValid(board, numToAttempt, row, column))
                        {
                            board[row][column] = numToAttempt;

                            if(solveBoard(board))
                            {
                                return true;
                            }

                            else
                            {
                                board[row][column] = 0;
                            }
                        }
                    }  
                    return false; 
                }
            }
        }
        return true;
    }
}
