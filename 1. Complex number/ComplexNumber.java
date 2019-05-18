package savedPrevious;
import savedPrevious.interfaces.BasicMathElement;

/**
 * Math complex number
 * @autor Roman Sedov
 */
public class ComplexNumber implements BasicMathElement<ComplexNumber> {
    private double real;
    private double imaginary;

    public ComplexNumber() {
        this(0, 0);
    }

    public ComplexNumber(double real) {
        this(real, 0);
    }
    
    ComplexNumber(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    public double getReal() {
        return real;
    }

    public double getImaginary() {
        return imaginary;
    }

    public void print() {
        System.out.println(getReal() + " + " + getImaginary() + "i");
    }

    /**
     * add complex number
     * @param complexToAdd
     * @return new complex number 
     */
    public ComplexNumber add(ComplexNumber complexToAdd) {
        double calculatedReal = getReal() + complexToAdd.getReal();
        double calculatedImaginary = getImaginary() + complexToAdd.getImaginary();

        return new ComplexNumber(calculatedReal, calculatedImaginary);
    }

    /**
     * substract complex number
     * @param complexToSubstract
     * @return new complex number 
     */
    public ComplexNumber substract(ComplexNumber complexToSubstract) {
        double calculatedReal = getReal() - complexToSubstract.getReal();
        double calculatedImaginary = getImaginary() - complexToSubstract.getImaginary();

        return new ComplexNumber(calculatedReal, calculatedImaginary);
    }

    /**
     * multiply complex number
     * @param complexToMultiply
     * @return new complex number 
     */
    public ComplexNumber multiply(ComplexNumber complexToMultiply) {
        double calculatedReal = getReal() * complexToMultiply.getReal()
                + multiplyImaginery(getImaginary(), complexToMultiply.getImaginary());
        double calculatedImaginary = getImaginary() * complexToMultiply.getReal() +
                getReal() + complexToMultiply.getImaginary();

        return new ComplexNumber(calculatedReal, calculatedImaginary);
    }

    /**
     * divide complex number
     * @param complexToDivide
     * @return new complex number 
     * @throws
     */
    public ComplexNumber divide(ComplexNumber complexToDivide) throws IllegalArgumentException {
        if (complexToDivide.getReal() == 0 && complexToDivide.getImaginary() == 0) {
            throw new IllegalArgumentException("complex to divide should not be 0");
        }

        return this.calculateDividing(complexToDivide);
    }

    private static double multiplyImaginery(double first, double second) {
        double imagineryNumberSquare = -1;

        return imagineryNumberSquare * first * second;
    }

    private ComplexNumber calculateDividing(ComplexNumber complexToDivide) {
        return complexToDivide;
    }
}
