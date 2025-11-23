// Implements algebraic operations and the square root function without using 
// the Java operations a + b, a - b, a * b, a / b, a % b, and without calling 
// Math.sqrt. All the functions in this class operate on int values and
// return int values.

public class Algebra {
	public static void main(String args[]) {
	    // Tests some of the operations
	    System.out.println(plus(2,3));   // 2 + 3
	    System.out.println(minus(7,2));  // 7 - 2
   		System.out.println(minus(2,7));  // 2 - 7
 		System.out.println(times(3,4));  // 3 * 4
   		System.out.println(plus(2,times(4,2)));  // 2 + 4 * 2
   		System.out.println(pow(5,3));      // 5^3
   		System.out.println(pow(3,5));      // 3^5
   		System.out.println(div(12,3));   // 12 / 3    
   		System.out.println(div(5,5));    // 5 / 5  
   		System.out.println(div(25,7));   // 25 / 7
   		System.out.println(mod(25,7));   // 25 % 7
   		System.out.println(mod(120,6));  // 120 % 6    
   		System.out.println(sqrt(36));
		System.out.println(sqrt(263169));
   		System.out.println(sqrt(76123));
	}  

	// Returns x1 + x2
	public static int plus(int x1, int x2) 
	{
		int sum = 0;
		if (x1 < 0 && x2 < 0)
		{
			sum = x1;
		    for (int i = 0; i > x2; i--)
		    {
			    sum--;
		    }
		}
        else if (x1<=x2)
		{
		    sum = x1;
		    for (int i = 0; i < x2; i++)
		    {
			    sum++;
		    }
	    }
		else 
		{
		    sum = x2;
		    for (int i = 0; i < x1; i++)
		    {
			    sum++;
		    }
	    }
		return sum;
	}

	// Returns x1 - x2
	public static int minus(int x1, int x2)
    {
		if (x2 == 0)
		{
			return x1;
		}
		int dif = x1;
		if (x1 == 0 && x2 < 0)
		{
			for (int i = 0; i > x2; i--)
		    {
		        dif++;
		    }
		}
		if (x1 != 0 && x2 < 0)
		{
			for (int i = 0; i > x2; i--)
		    {
		        dif++;
		    }
	    }
		if (x2 > 0)
		{
		    for (int i = 0; i < x2; i++)
		    {
		        dif--;
	        }
		}
			
		return dif;
	}

	// Returns x1 * x2
	public static int times(int x1, int x2) 
	{
		if (x1 == 0 || x2 == 0)
	    {
			return 0;
	    }

		int result = 0;

		if (x1 < 0 && x2 < 0)
		{
			x1 = minus(0, x1);
			x2 = minus(0, x2);
		}
		if ((x1 < 0 && x2 > 0) || (x1 > 0 && x2 < 0))
		{
			if (x1 < 0)
			{
				for (int i = 0; i < x2; i++)
				{
					result = plus(result, x1);
				}
			}
			else
			{
				for (int i = 0; i > x2; i--)
				{
					result = minus(result, x1);
				}
			}
		}
		if (x1 > 0 && x2 > 0)
		{
			for (int i = 0; i < x2; i++)
	        {
                result = plus(result, x1);
            }
		}
	    
		return result;
	}

	// Returns x^n (for n >= 0)
	public static int pow(int x, int n) 
	{
		int result = 1;
		for (int i = 0; i < n; i++)
		{
			result = times(result, x);
		}
		return result;
	}

	// Returns the integer part of x1 / x2 
	public static int div(int x1, int x2) 
	{
		if (x2 == 0) {
            System.out.println("Division by zero is not allowed.");
            return 0; 
        }
        
        int result = 0;
		int a, b;

		if (x1 < 0 && x2 < 0)
		{
			x1 = minus(0, x1);
			x2 = minus(0, x2);
		}
        
		if ((x1 < 0 && x2 > 0) || (x1 > 0 && x2 < 0))
		{
			a = x1;
			b = x2;
			if (x1 < 0)
			{
				a = minus(0, x1);
			}
			else
			{
				b = minus(0, x2);
			}
			while (a>=b)
			{
				a = minus(a, b);
				result++;
			}
			result = minus(0, result);
		}
		if (x1 > 0 && x2 > 0)
		{
			while (x1>=x2)
			{
				x1 = minus(x1, x2);
				result++;
			}
		}
		return result;    
	}

	// Returns x1 % x2
	public static int mod(int x1, int x2) 
	{
		if (x2 == 0) 
		{
            System.out.println("Modulo by zero is not allowed.");
            return 0; 
        }
        int a = x2;
        int r = x1; 
        
        while (r >= a) 
		{
            r = minus(r, a);
        }
        if (x1 < 0) 
		{	
            return minus(0, r);  
        }
        return r;
    }	

	// Returns the integer part of sqrt(x) 
	public static int sqrt(int x) 
	{
		if (x < 0) {
            System.out.println("Cannot calculate the square root of a negative number.");
            return 0; 
        }
        if (x == 0 || x == 1) 
		{
            return x;
        }
		int low = 1;
        int high = x; 
        int result = 1; 

        while (low <= high)
		{
            int mid = low + div(minus(high, low), 2); 
            if (mid <= div(x, mid)) 
		    { 
               result = mid;
               low = mid + 1;
            } 
			else 
			{
                high = mid - 1;
            }
        }
        return result;
	}	  	  
}