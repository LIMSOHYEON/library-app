package com.group.libraryapp.controller.calculator;

import com.group.libraryapp.dto.calculator.request.CalculatorAddRequest;
import com.group.libraryapp.dto.calculator.request.CalculatorMultiplyRequest;
import org.springframework.web.bind.annotation.*;

@RestController
public class CalculatorController {

    @GetMapping("/add")
    public int TwoNumbers(@RequestParam int num1, @RequestParam int num2) {
        return num1 + num2;
    }

    @GetMapping("/add2")
    public int TwoNumbersObj(CalculatorAddRequest request) {
        return request.getNum1() + request.getNum2();
    }

    @PostMapping("/multiply")
    public int multiplyTwoNumbers(@RequestBody CalculatorMultiplyRequest request) {
        return request.getMul1() * request.getMul2();
    }
}
