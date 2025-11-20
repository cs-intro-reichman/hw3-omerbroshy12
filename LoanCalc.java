// Computes the periodical payment necessary to pay a given loan.
public class LoanCalc {
	
	static double epsilon = 0.001;  // Approximation accuracy
	static int iterationCounter;    // Number of iterations 
	
	// Gets the loan data and computes the periodical payment.
    // Expects to get three command-line arguments: loan amount (double),
    // interest rate (double, as a percentage), and number of payments (int).  
	public static void main(String[] args) {		
		// Gets the loan data
		double loan = Double.parseDouble(args[0]);
		double rate = Double.parseDouble(args[1]);
		int n = Integer.parseInt(args[2]);
		System.out.println("Loan = " + loan + ", interest rate = " + rate + "%, periods = " + n);

		// Computes the periodical payment using brute force search
		System.out.print("\nPeriodical payment, using brute force: ");
		System.out.println((int) bruteForceSolver(loan, rate, n, epsilon));
		System.out.println("number of iterations: " + iterationCounter);

		// Computes the periodical payment using bisection search
		System.out.print("\nPeriodical payment, using bi-section search: ");
		System.out.println((int) bisectionSolver(loan, rate, n, epsilon));
		System.out.println("number of iterations: " + iterationCounter);
	}

	
	private static double endBalance(double loan, double rate, int n, double payment) 
	{	
		double balance = loan;
        double rateDecimal = rate / 100.0; 
        double mult = 1.0 + rateDecimal;
    
        for (int i = 0; i < n; i++) 
        {
            balance -= payment; 
            balance *= mult;     
        }
    
        return Math.round(balance * 100.0) / 100.0;
    }
	
    public static double bruteForceSolver(double loan, double rate, int n, double epsilon)
    {
	iterationCounter = 0;
    double g = loan / n;
    
    while (endBalance(loan, rate, n, g) >= 0) 
    {
        iterationCounter++;
        g = g + epsilon;
    }
    
    return g;
    }
    
    public static double bisectionSolver(double loan, double rate, int n, double epsilon) 
	{  
        iterationCounter = 0;
        double l = loan / n;
        double h = loan;
        double g = (l + h) / 2.0; 
        
        while ((h - l) >= epsilon)
        {
            double fg = endBalance(loan, rate, n, g);
            if (fg > 0) 
            { 
                l = g;
            } 
            else 
            { 
                h = g;
            }
            iterationCounter++;
            g = (l + h) / 2.0; 
        }
        
        return g;
    }
}