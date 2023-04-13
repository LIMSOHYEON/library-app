package com.group.libraryapp.dto.calculator.request;

public class CalculatorMultiplyRequest {

    private final int mul1;
    private final int mul2;

    public CalculatorMultiplyRequest(int mul1, int mul2) {
        this.mul1 = mul1;
        this.mul2 = mul2;
    }

    public int getMul1() {
        return mul1;
    }

    public int getMul2() {
        return mul2;
    }
}
