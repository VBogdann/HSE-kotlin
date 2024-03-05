//
//  main.swift
//  Calculator
//
//  Created by Bogdan Valuev on 29.02.2024.
//
import Foundation
// defining a class for operations
class Calculator {
    func performOperation(_ a: Double, _ b: Double, operation: String) throws -> Double {
        switch operation {
        case "+":
            return a + b
        case "-":
            return a - b
        case "*":
            return a * b
// in the division case we except the opportunity to divide by zero
        case "/":
            if b == 0 { throw CalculationError.divisionByZero }
            return a / b
        case "^":
// operation of raising to a degree comletes when the "a" is a number and "b" is degree
            return pow(a, b)
        default:
            throw CalculationError.unknownOperation
        }
    }
}

// processing of error and case when the operation is unknown
enum CalculationError: Error, CustomStringConvertible {
    case divisionByZero
    case unknownOperation

    var description: String {
        switch self {
        case .divisionByZero:
            return "Error: Division by zero is not allowed."
        case .unknownOperation:
            return "Error: Unknown operation."
        }
    }
}

// Main function to run the calculator
func main() {
    let calculator = Calculator()
    print("Enter the first number:")
    guard let firstStr = readLine(), let firstNum = Double(firstStr) else {
        print("Invalid input for the first number.")
        return
    }
// while reading the numbers, program checks if the numbers are correctly inputed
    print("Enter the second number:")
    guard let secondStr = readLine(), let secondNum = Double(secondStr) else {
        print("Invalid input for the second number.")
        return
    }
// while reading the operation definition, program checks if the operation is set correctly
    print("Enter the operation (+, -, *, /, ^):")
    guard let operation = readLine() else {
        print("Invalid input for the operation.")
        return
    }
// main part of the program that runs the calculator and checks for 0 value
    do {
        let result = try calculator.performOperation(firstNum, secondNum, operation: operation)
        print("Result: \(result)")
    } catch let error as CalculationError {
        print(error.description)
    } catch {
        print("An unexpected error occurred.")
    }
}

main()

