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

	// Computes the ending balance of a loan, given the loan amount, the periodical
	// interest rate (as a percentage), the number of periods (n), and the periodical payment.
	private static double endBalance(double loan, double rate, int n, double payment) 
	{	
		double balance = loan;
        // המרת הריבית מאחוז לעשרוני (לדוגמה: 5.0 -> 0.05)
        double rateDecimal = rate / 100.0;
        double multiplier = 1.0 + rateDecimal;
        
        for (int i = 0; i < n; i++) {
            // הוספת ריבית על היתרה הקיימת
            balance = balance * multiplier;
            
            // הפחתת התשלום
            balance = balance - payment;
        }
        
        // עיגול התוצאה לדיוק של סכום כסף (אגורות)
        return Math.round(balance * 100.0) / 100.0;
	}
	
	// Uses sequential search to compute an approximation of the periodical payment
	// that will bring the ending balance of a loan close to 0.
	// Given: the sum of the loan, the periodical interest rate (as a percentage),
	// the number of periods (n), and epsilon, the approximation's accuracy
	// Side effect: modifies the class variable iterationCounter.
    public static double bruteForceSolver(double loan, double rate, int n, double epsilon)
    {
		iterationCounter = 0;
        
        // הגדרת ניחוש התחלתי g = loan/n
        // יתרת הסיום ב-g זה אמורה להיות חיובית: f(g) > 0
        double g = loan / n;
        
        // הגברת g בקפיצות של epsilon עד ש-f(g) הופך ללא-חיובי (<= 0)
        while (endBalance(loan, rate, n, g) > 0) {
            
            iterationCounter++;
            g = g + epsilon;
        }
        
        // g הוא הקירוב הנכון (הערך הראשון שבו היתרה אינה חיובית).
        // הפתרון האמיתי נמצא בטווח [g - epsilon, g].
        return g;
    }
    
    // Uses bisection search to compute an approximation of the periodical payment 
	// that will bring the ending balance of a loan close to 0.
	// Given: the sum of the loan, the periodical interest rate (as a percentage),
	// the number of periods (n), and epsilon, the approximation's accuracy
	// Side effect: modifies the class variable iterationCounter.
    public static double bisectionSolver(double loan, double rate, int n, double epsilon) 
	{  
        iterationCounter = 0;
        
        // הגדרת הגבול התחתון L (lo)
        // L: תשלום נמוך שבו היתרה חיובית (f(L) > 0). נבחר loan/n.
        double lo = loan / n;
        
        // הגדרת הגבול העליון H (hi)
        // H: תשלום גבוה שבו היתרה שלילית (f(H) < 0). נבחר את סך ההלוואה כולל ריבית מרבית.
        double rateDecimal = rate / 100.0;
        double hi = loan * Math.pow(1.0 + rateDecimal, n); 
        
        double g = (lo + hi) / 2.0; // הניחוש הראשוני
        
        // לולאת החיפוש: ממשיכים כל עוד הרווח (H - L) גדול מהדיוק הרצוי
        while ((hi - lo) > epsilon) {
            
            iterationCounter++;
            g = (lo + hi) / 2.0; // חישוב נקודת האמצע
            
            double f_g = endBalance(loan, rate, n, g);
            
            // f מונוטונית יורדת.
            // אם f(g) > 0, התשלום g נמוך מדי. הפתרון צריך להיות גבוה יותר.
            if (f_g > 0) { 
                // הפתרון חייב להיות בין g ל-H
                lo = g;
            } 
            // אם f(g) <= 0, התשלום g גבוה מדי (או שהוא הפתרון). הפתרון צריך להיות נמוך יותר או שווה.
            else { 
                // הפתרון חייב להיות בין L ל-g (או g הוא הפתרון)
                hi = g;
            }
        }
        
        // g הוא הקירוב לפתרון
        return g;
    }
}