package com.stanislav.labwork;

public class MatrixOperations {

    public static void main(String[] args) {
        try {
            execute();
        } catch (Exception e) {
            System.err.println("Виникла помилка під час виконання програми: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void execute() {
        long studentNumber = 13879022;

        int C5 = (int) (studentNumber % 5);
        int C7 = (int) (studentNumber % 7);
        int C11 = (int) (studentNumber % 11);

        System.out.println("C5 = " + C5);
        System.out.println("C7 = " + C7);
        System.out.println("C11 = " + C11);

        Class<?> elementType = getElementType(C7);
        if (elementType == null) {
            throw new IllegalArgumentException("Недійсний код C7: " + C7);
        }

        System.out.println("Тип елементів матриці: " + elementType.getSimpleName());

        int[][] A = {
                {1, 2, 3},
                {4, 5, 6}
        };

        int[][] B = {
                {7, 8, 9},
                {10, 11, 12}
        };

        int[][] C = performActionC5(C5, A, B);

        System.out.println("Матриця A:");
        printMatrix(A);

        System.out.println("Матриця B:");
        printMatrix(B);

        System.out.println("Матриця C:");
        printMatrix(C);

        int result = performActionC11(C11, C);

        System.out.println("Результат дії з матрицею C: " + result);
    }

    private static Class<?> getElementType(int C7) {
        switch (C7) {
            case 0:
                return double.class;
            case 1:
                return byte.class;
            case 2:
                return short.class;
            case 3:
                return int.class;
            case 4:
                return long.class;
            case 5:
                return char.class;
            case 6:
                return float.class;
            default:
                return null;
        }
    }

    private static int[][] performActionC5(int C5, int[][] A, int[][] B) {
        switch (C5) {
            case 0:
                int a = 2;
                return multiplyMatrixByConstant(B, a);
            case 1:
                return transposeMatrix(B);
            case 2:
                return addMatrices(A, B);
            case 3:
                return bitwiseXORMatrices(A, B);
            case 4:
                return multiplyMatrices(A, B);
            default:
                throw new IllegalArgumentException("Недійсний код C5: " + C5);
        }
    }

    private static int performActionC11(int C11, int[][] C) {
        switch (C11) {
            case 0:
                return sumOfMinElementsInColumns(C);
            case 1:
                return sumOfMinElementsInRows(C);
            case 2:
                return sumOfMaxElementsInColumns(C);
            case 3:
                return sumOfMaxElementsInRows(C);
            case 4:
                return sumMaxEvenRowsMinOddRows(C);
            case 5:
                return sumMaxOddRowsMinEvenRows(C);
            case 6:
                return sumMaxEvenColumnsMinOddColumns(C);
            case 7:
                return sumMaxOddColumnsMinEvenColumns(C);
            case 8:
                return (int) averageOfEachRow(C);
            case 9:
                return (int) averageOfEachColumn(C);
            case 10:
                return (int) averageOfAllElements(C);
            default:
                throw new IllegalArgumentException("Недійсний код C11: " + C11);
        }
    }

    private static int[][] multiplyMatrixByConstant(int[][] matrix, int a) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] result = new int[rows][cols];
        for(int i=0;i<rows;i++) {
            for(int j=0;j<cols;j++) {
                result[i][j] = a * matrix[i][j];
            }
        }
        return result;
    }

    private static int[][] transposeMatrix(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] transposed = new int[cols][rows];
        for(int i=0;i<rows;i++) {
            for(int j=0;j<cols;j++) {
                transposed[j][i] = matrix[i][j];
            }
        }
        return transposed;
    }

    private static int[][] addMatrices(int[][] A, int[][] B) {
        int rows = A.length;
        int cols = A[0].length;
        if (B.length != rows || B[0].length != cols) {
            throw new IllegalArgumentException("Матриці повинні мати однакові розміри для додавання.");
        }
        int[][] sum = new int[rows][cols];
        for(int i=0;i<rows;i++) {
            for(int j=0;j<cols;j++) {
                sum[i][j] = A[i][j] + B[i][j];
            }
        }
        return sum;
    }

    private static int[][] bitwiseXORMatrices(int[][] A, int[][] B) {
        int rows = A.length;
        int cols = A[0].length;
        if (B.length != rows || B[0].length != cols) {
            throw new IllegalArgumentException("Матриці повинні мати однакові розміри для побітового XOR.");
        }
        int[][] result = new int[rows][cols];
        for(int i=0;i<rows;i++) {
            for(int j=0;j<cols;j++) {
                result[i][j] = A[i][j] ^ B[i][j];
            }
        }
        return result;
    }

    private static int[][] multiplyMatrices(int[][] A, int[][] B) {
        int rowsA = A.length;
        int colsA = A[0].length;
        int rowsB = B.length;
        int colsB = B[0].length;
        if (colsA != rowsB) {
            throw new IllegalArgumentException("Кількість стовпців матриці A повинна дорівнювати кількості рядків матриці B.");
        }
        int[][] product = new int[rowsA][colsB];
        for(int i=0;i<rowsA;i++) {
            for(int j=0;j<colsB;j++) {
                for(int k=0;k<colsA;k++) {
                    product[i][j] += A[i][k] * B[k][j];
                }
            }
        }
        return product;
    }

    private static int sumOfMinElementsInColumns(int[][] matrix) {
        int cols = matrix[0].length;
        int sum = 0;
        for(int j=0;j<cols;j++) {
            int min = matrix[0][j];
            for(int i=1;i<matrix.length;i++) {
                if(matrix[i][j] < min) {
                    min = matrix[i][j];
                }
            }
            sum += min;
        }
        return sum;
    }

    private static int sumOfMinElementsInRows(int[][] matrix) {
        int sum = 0;
        for(int[] row : matrix) {
            int min = row[0];
            for(int val : row) {
                if(val < min) {
                    min = val;
                }
            }
            sum += min;
        }
        return sum;
    }

    private static int sumOfMaxElementsInColumns(int[][] matrix) {
        int cols = matrix[0].length;
        int sum = 0;
        for(int j=0;j<cols;j++) {
            int max = matrix[0][j];
            for(int i=1;i<matrix.length;i++) {
                if(matrix[i][j] > max) {
                    max = matrix[i][j];
                }
            }
            sum += max;
        }
        return sum;
    }

    private static int sumOfMaxElementsInRows(int[][] matrix) {
        int sum = 0;
        for(int[] row : matrix) {
            int max = row[0];
            for(int val : row) {
                if(val > max) {
                    max = val;
                }
            }
            sum += max;
        }
        return sum;
    }

    private static int sumMaxEvenRowsMinOddRows(int[][] matrix) {
        int sum = 0;
        for(int i=0;i<matrix.length;i++) {
            if((i+1) % 2 == 0) {
                int max = matrix[i][0];
                for(int val : matrix[i]) {
                    if(val > max) {
                        max = val;
                    }
                }
                sum += max;
            } else {
                int min = matrix[i][0];
                for(int val : matrix[i]) {
                    if(val < min) {
                        min = val;
                    }
                }
                sum += min;
            }
        }
        return sum;
    }

    private static int sumMaxOddRowsMinEvenRows(int[][] matrix) {
        int sum = 0;
        for(int i=0;i<matrix.length;i++) {
            if((i+1) % 2 != 0) {
                int max = matrix[i][0];
                for(int val : matrix[i]) {
                    if(val > max) {
                        max = val;
                    }
                }
                sum += max;
            } else {
                int min = matrix[i][0];
                for(int val : matrix[i]) {
                    if(val < min) {
                        min = val;
                    }
                }
                sum += min;
            }
        }
        return sum;
    }

    private static int sumMaxEvenColumnsMinOddColumns(int[][] matrix) {
        int cols = matrix[0].length;
        int sum = 0;
        for(int j=0;j<cols;j++) {
            if((j+1) % 2 == 0) {
                int max = matrix[0][j];
                for(int i=1;i<matrix.length;i++) {
                    if(matrix[i][j] > max) {
                        max = matrix[i][j];
                    }
                }
                sum += max;
            } else {
                int min = matrix[0][j];
                for(int i=1;i<matrix.length;i++) {
                    if(matrix[i][j] < min) {
                        min = matrix[i][j];
                    }
                }
                sum += min;
            }
        }
        return sum;
    }

    private static int sumMaxOddColumnsMinEvenColumns(int[][] matrix) {
        int cols = matrix[0].length;
        int sum = 0;
        for(int j=0;j<cols;j++) {
            if((j+1) % 2 != 0) {
                int max = matrix[0][j];
                for(int i=1;i<matrix.length;i++) {
                    if(matrix[i][j] > max) {
                        max = matrix[i][j];
                    }
                }
                sum += max;
            } else {
                int min = matrix[0][j];
                for(int i=1;i<matrix.length;i++) {
                    if(matrix[i][j] < min) {
                        min = matrix[i][j];
                    }
                }
                sum += min;
            }
        }
        return sum;
    }

    private static double averageOfEachRow(int[][] matrix) {
        double sum = 0.0;
        int count = 0;
        for(int[] row : matrix) {
            for(int val : row) {
                sum += val;
                count++;
            }
        }
        return sum / count;
    }

    private static double averageOfEachColumn(int[][] matrix) {
        double sum = 0.0;
        int count = 0;
        for(int[] row : matrix) {
            for(int val : row) {
                sum += val;
                count++;
            }
        }
        return sum / count;
    }

    private static double averageOfAllElements(int[][] matrix) {
        return averageOfEachRow(matrix);
    }

    private static void printMatrix(int[][] matrix) {
        for(int[] row : matrix) {
            for(int val : row) {
                System.out.print(val + "\t");
            }
            System.out.println();
        }
    }
}