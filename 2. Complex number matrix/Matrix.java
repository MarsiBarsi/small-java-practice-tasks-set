package savedPrevious;// Сложение, умножение, транспонирвоание, определитель

public class Matrix {
    private int rowsNumber;
    private int columsNumber;
    private ComplexNumber determinant;
    private ComplexNumber[][] table;

    public Matrix() {
        ComplexNumber[][] emptyMatrix = {};

        this.initMatrix(emptyMatrix);
    }

    public Matrix(ComplexNumber[][] table) {
        this.initMatrix(table);
    }

    public ComplexNumber getElement(int row, int column) {
        if (table[row][column] == null) {
            return null;
        }

        return this.table[row][column];
    }

    public ComplexNumber getDeterminant() {
        return this.determinant;
    }

    public ComplexNumber[][] getTable() {
        return this.table;
    }

    public int getRowsNumber() {
        return this.rowsNumber;
    }

    public int getColumnsNumber() {
        return this.columsNumber;
    }

    public Matrix getTransposed() {
        ComplexNumber[][] transposedTable = this.transposeTable(this.table);

        return new Matrix(transposedTable);
    }

    public Matrix add(Matrix matrixToAdd) throws IllegalArgumentException {
        if (this.rowsNumber != matrixToAdd.rowsNumber || this.columsNumber != matrixToAdd.columsNumber) {
            throw new IllegalArgumentException("savedPrevious.Matrix should have the similar size to be added");
        }

        ComplexNumber[][] tableToAdd = matrixToAdd.getTable();
        ComplexNumber[][] newTable = this.calculateAdding(this.getTable(), tableToAdd);

        return new Matrix(newTable);
    }

    private void initMatrix(ComplexNumber[][] table) {
        this.table = table;
        this.rowsNumber = table.length;
        this.columsNumber = table[0].length;
        this.determinant = this.calculateDeterminant(table);
    }

    private ComplexNumber[][] calculateAdding(ComplexNumber[][] first, ComplexNumber[][] second) {
        ComplexNumber[][] newMatrix = new ComplexNumber[this.rowsNumber][this.columsNumber];

        for (int i = 0; i < this.rowsNumber; i++) {
            for (int j = 0; j < this.columsNumber; j++) {
                newMatrix[i][j] = first[i][j].add(second[i][j]);
            }
        }

        return newMatrix;
    }

    private ComplexNumber calculateDeterminant(ComplexNumber[][] table) {
        ComplexNumber determinant = new ComplexNumber(0,0);

        if (columsNumber == 1) {
            return determinant;
        }

        for (int i = 0; i < rowsNumber; i++) {
            ComplexNumber[][] tempTable = new ComplexNumber[columsNumber - 1][rowsNumber - 1];

            for (int j = 1; j < columsNumber; j++) {
                for (int k = 0; k < rowsNumber; k++) {
                    if (k < i) {
                        tempTable[j - 1][k] = table[j][k];
                    } else if (k > i) {
                        tempTable[j - 1][k - 1] = table[j][k];
                    }
                }

                ComplexNumber multipliedChild = calculateDeterminant(tempTable);
                ComplexNumber multipliedItemOnChildMatrix = table[0][i].multiply(multipliedChild);
                determinant = determinant.add(multipliedItemOnChildMatrix);
            }
        }

        return determinant;
    }

    public ComplexNumber[][] transposeTable(ComplexNumber[][] table) {
        ComplexNumber[][] newTable = new ComplexNumber[columsNumber][rowsNumber];

        for (int i = 0; i < rowsNumber; i++) {
            for (int j = 0; j < columsNumber; j++) {
                newTable[j][i] = table[i][j];
            }
        }

        return newTable;
    }
}
