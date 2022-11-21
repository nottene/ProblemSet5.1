/**
 * @author Charles Shi
 * @version 1.0
 */
public class Fraction {

    private int numerator;
    private int denominator;

    /**
     * Default Constructor that creates a fraction with
     * an initial numerator of 1 and an inital denominator of 1
     */
    public Fraction() {
        numerator = 1;
        denominator = 1;
    }

    /**
     * Parameterized Constructor
     * @param num the int value to set the numerator
     * @param den the int value ot set the denominator
     * Postcondition: The denominator cannot be equal to zero. If it is,
     *                 it will be changed to 1.
     */
    public Fraction(int num, int den) {
        numerator=num;
        setDenom(den);
    }

    /**
     * Precondition: The String parameter cannot be null nor empty
     * Postcondition: The contructor will set the denominator to 1 if it is 0;
     * @param frac It takes in a String formatted like this: "numerator/denominator".
     */
    public Fraction(String frac) {
        numerator = Integer.parseInt(frac.substring(0,frac.indexOf("/")));
        int tempD = Integer.parseInt(frac.substring(frac.indexOf("/")+1));
        setDenom(tempD);
    }

    /**
     * Copy Constructor
     * @param f A Fraction object to copy
     */
    public Fraction(Fraction f) {
        numerator = f.numerator;
        denominator = f.denominator;
    }

    /**
     *
     * @return A String wi
     */
    public String toString() {
        String result = "";
        result += numerator +"/"+ denominator;
        return result;
    }

    /**
     *
     * @return The numerator of the fraction object as an integer
     */
    public int getNum() {
        return(numerator);
    }

    /**
     *
     * @return The denominator of the fraction object as an integer
     */
    public int getDenom() {
        return(denominator);
    }

    /**
     *
     * @return The fraction to a decimal value
     */
    public double toDouble() {
        return(double)numerator/denominator;
    }

    /**
     *
     * @param n - value to update the numerator
     */
    public void setNum(int n) {
        numerator = n;
    }

    /**
     *Postcondition: If the denominator is 0, it will be changed to 1.
     * @param d - value to update the denominator
     */
    public void setDenom(int d) {
        if(d != 0)
            denominator = d;
        else {
            denominator = 1;
            System.out.println("undefined");
        }
    }

    /**
     *
     * @param a the first value for the GCF method
     * @param b the second value for the GCF method
     * @return The greatest common factor of the two numbers
     */
    private int GCF(int a, int b) {
        int i = Math.abs(a);
        int j = Math.abs(b);
        int output = 0;
        while(i != j) {
            if (i > j)
            i -= j;
            else j-=i;
        }
        return i;
    }

    /**
     * method that simplifies the fraction
     */
    public void reduce() {
        if (numerator == 0) {
            denominator = 1;
        } else {
            int gcfactor = GCF(numerator, denominator);
            numerator /= gcfactor;
            denominator /= gcfactor;
        }
        if(numerator < 0 && denominator < 0) {
            numerator = -numerator;
            denominator = -denominator;
        }
    }

    /**
     * Method for multiplying two Fraction objects
     * Postcondition: The answer will be in lowest terms
     * @param frac The Fraction to multiply by
     * @return A new Fraction object that is the product of this fraction and the first
     */
    public Fraction multiply(Fraction frac) {
        int n = numerator * frac.numerator;
        int d = denominator * frac.denominator;
        Fraction ans = new Fraction(n,d);
        ans.reduce();
        return ans;

    }

    /**
     * Method for multiplying two Fraction objects
     * Postcondition: The answer will be in lowest terms
     * @param a The first fraction getting multiplied
     * @param b The second fraction getting multiplied
     * @return The product of the two fractions
     */
    public static Fraction multiply(Fraction a, Fraction b) {
        Fraction frac = new Fraction((a.numerator)*(b.numerator), (a.denominator)*(b.denominator));
        frac.reduce();
        return frac;
    }

    /**
     * @param a Takes in a fraction
     * @return The reciprocal of the fraction taken
     */
    public static Fraction reciprocal(Fraction a) {
        return new Fraction(a.denominator, a.numerator);
    }

    /**
     * Method for dividing two Fraction objects
     * Postcondition: The answer will be in lowest terms
     * @param frac The Fraction object to be devided by
     * @return A new Fraction object that is the quotient of this Fraction and the first
     */
    public Fraction divide(Fraction frac) {
        if (frac.toDouble() == 0) {
            System.out.println("undefined");
            return null;
        }
        return Fraction.multiply(new Fraction(numerator, denominator),reciprocal(frac));
    }
    /**
     * Method for dividing two Fraction objects
     * Postcondition: The answer will be in lowest terms
     * @param a The first fraction to get divided by
     * @param b The second fraction to get divided by
     * @return The quotient of the two fractions
     */
    public static Fraction divide(Fraction a, Fraction b) {
        return new Fraction(multiply(a,reciprocal(b)));
    }

    /**
     * Method for adding two Fraction objects
     * Postcondition: The answer will be in lowest terms
     * @param frac The Fraction object to be added by
     * @return A new Fraction object that is the sum of this Fraction and the first
     */
    public Fraction add(Fraction frac) {
        Fraction f = new Fraction(numerator * frac.denominator + frac.numerator * denominator, denominator * frac.denominator);
        f.reduce();
        return f;
    }

    /**
     * Method for adding two Fraction objects
     * Postcondition: The answer will be in lowest terms
     * @param a The first fraction that is being added
     * @param b The second fraction that is being added
     * @return The sum of the two fractions
     */
    public static Fraction add(Fraction a, Fraction b) {
        int i = a.numerator * b.denominator + b.numerator * a.denominator;
        int j = a.denominator * b.denominator;
        Fraction frac = new Fraction(i,j);
        frac.reduce();
        return frac;
    }

    /**
     * Method for subtracting two Fraction objects
     * Postcondition: The answer will be in lowest terms
     * @param frac The Fraction object to be subtracted by
     * @return A new Fraction object that is the difference of this Fraction and the first
     */
    public Fraction subtract(Fraction frac) {
        Fraction f = new Fraction(numerator * frac.denominator - frac.numerator * denominator, denominator * frac.denominator);
        f.reduce();
        return f;
    }
    /**
     * Method for subtracting two Fraction objects
     * Postcondition: The answer will be in lowest terms
     * @param a The first fraction that is being subtracted
     * @param b The second fraction that is being subtracted
     * @return The difference of the two fractions
     */
    public static Fraction subtract(Fraction a, Fraction b) {
        int i = a.numerator * b.denominator - b.numerator * a.denominator;
        int j = a.denominator * b.denominator;
        Fraction frac = new Fraction(i,j);
        frac.reduce();
        return frac;
    }
}