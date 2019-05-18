package savedPrevious;// сложение, вычитание, перемножаться
// javaDoc
import savedPrevious.interfaces.BasicMathElement;

/**
 * Класс представляющий собой реализацию математического комплексного числа
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

    /**
     * Конструктор - создание нового объекта
     * @param real - реальная часть числа
     * @param imaginary - мнимая часть числа
     */
    ComplexNumber(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    /**
     * Получение реальной части числа
     * @return реальная часть числа
     */
    public double getReal() {
        return real;
    }

    /**
     * Получение мниной части числа
     * @return мнимая часть числа
     */
    public double getImaginary() {
        return imaginary;
    }

    /**
     * Выводит элемент в консоль
     */
    public void print() {
        System.out.println(getReal() + " + " + getImaginary() + "i");
    }

    /**
     * Сложение комплексного числа
     * @param complexToAdd - число к добавлению
     * @return новое комплексное число, полученное сложением с переданным
     */
    public ComplexNumber add(ComplexNumber complexToAdd) {
        double calculatedReal = getReal() + complexToAdd.getReal();
        double calculatedImaginary = getImaginary() + complexToAdd.getImaginary();

        return new ComplexNumber(calculatedReal, calculatedImaginary);
    }

    /**
     * Вычитание комплексного числа
     * @param complexToSubstract - число для вычитания
     * @return новое комплексное число, полученное вычитанием переданного
     */
    public ComplexNumber substract(ComplexNumber complexToSubstract) {
        double calculatedReal = getReal() - complexToSubstract.getReal();
        double calculatedImaginary = getImaginary() - complexToSubstract.getImaginary();

        return new ComplexNumber(calculatedReal, calculatedImaginary);
    }

    /**
     * Умножение комплексного числа
     * @param complexToMultiply - число для умножения
     * @return новое комплексное число, полученное умножением на переданное
     */
    public ComplexNumber multiply(ComplexNumber complexToMultiply) {
        double calculatedReal = getReal() * complexToMultiply.getReal()
                + multiplyImaginery(getImaginary(), complexToMultiply.getImaginary());
        double calculatedImaginary = getImaginary() * complexToMultiply.getReal() +
                getReal() + complexToMultiply.getImaginary();

        return new ComplexNumber(calculatedReal, calculatedImaginary);
    }

    /**
     * Деление комплексного числа
     * @param complexToDivide - число для деления
     * @return новое комплексное число, полученное делением на переданное
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
