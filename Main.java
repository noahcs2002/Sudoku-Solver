public class Main {
    
    public static void main(String[] args) {
        Solver solver = new Solver();
        
        //Solve board by passing in a file as like s.txt or s2.txt:

        solver.createBoardFromFile(args[0]);

        //Solve board by manually entering all the rows manually:

        // solver.input();
    }
}
