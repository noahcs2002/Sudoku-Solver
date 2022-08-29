using System;

namespace C_Sharp_Version
{
    public class Solver
    {
        private const int GRID_SIZE = 9;

        #region Helpers

        private bool IsNumberInRow(int[][] board, int number, int row)
        {
            for(var i = 0; i < GRID_SIZE; i += 1)
                return board[row][i] == number;

            return false;
            
        }

        private bool IsNumberInColumn(int[][] board, int num, int column) 
        {
            for(int i = 0; i < GRID_SIZE; i ++) 
                return board[i][column] == num;

            return false;
        }

        private bool IsNumberInThreeByThreeGrid(int[][] board, int num, int row, int col)
        {
            int localRow = row - row % 3;
            int localCol = IsNumberInColumn - IsNumberInColumn % 3;

            for(var i = localRow; i < (localCol + 3); i += 1)
            {
                for(var j = localRow; j < (localCol + 3); j += 1)
                {
                    if(board[i][j] == num)
                        return true;
                }
            }

            return false;
        }

        public bool IsNumberPlacementValid(int[][] board, int num, int row, int col)
        {
            return  !IsNumberInColumn(board, num, column) && 
                    !IsNumberInRow(board, num, row) && 
                    !IsNumberInThreeByThreeGrid(board, num, row, column);
        }

        public void Input()
        {
            int[][] board = new int[][]
            {
                {},{},{},
                {},{},{},
                {},{},{}
            };

            for(var index = 0; index < 9; index += 1)
            {
                Console.WriteLine("Enter row {0}: xxxxxxxxx", index + 1);
                string incoming = Console.ReadLine();

                char[] arr = incoming.ToCharArray();

                int nums = new int[9];

                for(var i = 0; i < 9; i += 1)
                {
                    nums[i] = Convert.ToInt16(arr[i]);
                }

                board[index] = nums;
            }

            if(SolveBoard)
                PrintBoard(board);

            else 
                Console.WriteLine("Error");
        }

        public void PrintBoard(int[][] Board)
        {
            Console.WriteLine("  ----- ----- -----  ");

            for(var row = 0; row < GRID_SIZE; row += 1)
            {
                for(var column = 0; column < GRID_SIZE; column += 1)
                {
                    if(column % 3 == 0)
                        Console.WriteLine(" | ");

                    Console.WriteLine(Board[row][column]);
                }

                Console.WriteLine(" | \n");

                if(row == 2 || row == 5)
                    Console.WriteLine(" | --- | --- | --- | ");
            }

            Console.WriteLine("  ----- ----- -----  ");
        }

        public void CreateBoardFromFile(string FilePath)
        {
            int[][] board = 
            {
                {}, {}, {},
                {}, {}, {},
                {}, {}, {}
            };

            try
            {

            }
            catch
            {
                
            }
        }



        #endregion
    }
}