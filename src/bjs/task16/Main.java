package bjs.task16;

import java.util.Scanner;
import java.util.InputMismatchException;

import bjs.task16.hardware.*;
import bjs.task16.applications.*;

public class Main {

    public static void main(String[] args) {
        Computer computer = new Computer();
        Scanner input = new Scanner(System.in);

        try {
            System.out.println("Do you want to power on computer? Type 'Y' to power on or type 'N' to quit.");
            String userInput = input.nextLine();

            switch (userInput) {
                case "Y":
                    computer.powerOn();
                    System.out.print(computer.toString());
                    break;
                case "N":
                    System.out.println("Program aborted by user.");
                    return;
                default:
                    throw new InputMismatchException();
            }

            System.out.println("Select operation system. Type '1' to use Windows 7, x64; type '2' to use Linux.");
            //If we use input.nextInt() here further calls of the nextLine() may be returned automatically;
            userInput = input.nextLine();

            switch (userInput) {
                case "1":
                    computer.installOperationSystem("Windows 7, x64");
                    break;
                case "2":
                    computer.installOperationSystem("Linux");
                    break;
                default:
                    throw new InputMismatchException();
            }

            System.out.println("Do you want to install applications? Type 'Y' to power on or type 'N' to quit.");
            userInput = input.nextLine();

            switch (userInput) {
                case "Y":
                    computer.installApplications();
                    break;
                case "N":
                    System.out.println("Program aborted by user.");
                    return;
                default:
                    throw new InputMismatchException();
            }

            System.out.println("Which application should be run? Type '1' to use Calculator; type '2' to use File Manager");
            userInput = input.nextLine();

            switch (userInput) {
                case "1":
                    Calculator calculator = computer.getCalculator();
                    System.out.println("Enter expression:");
                    String expression = input.nextLine();
                    calculator.calcExpression(expression);

                    //System.out.println("\nPrint result array:");
                    //calculator.printCalculationResults();
                    break;
                case "2":
                    FileManager fileManager = computer.getFileManager();

                    System.out.println("Enter name of the first file:");
                    String firstFile = input.nextLine();

                    System.out.println("Enter content of the first file:");
                    String firstFileContent = input.nextLine();

                    fileManager.createOutputFile("first.txt");
                    fileManager.writeOutputFile("This the content of the first.txt");
                    fileManager.closeFileManager();

                    System.out.println("Enter name of the second file:");
                    String secondFile = input.nextLine();

                    fileManager.createInputFile(firstFile);
                    fileManager.copyInputFile(secondFile);
                    fileManager.closeFileManager();
                    break;
                default:
                    throw new InputMismatchException();
            }
        }
        catch (InputMismatchException e) {
            System.err.println("Wrong value have been typed. Program aborted.");
            e.printStackTrace();
        }
        catch (ComputerAccessException e) {
            System.err.println("Computer access exception: " + e);
            e.printStackTrace();
        }
        finally {
            computer.powerOff();
        }
    }
}

/*
Do you want to power on computer? Type 'Y' to power on or type 'N' to quit.
Y
Power is on.

Computer type: PC
Processor type: Intel Core i5-2400 CPU
Processor clock rate: 3.1
Random access memory size: 8.0
Fixed memory size: 0.0

Select operation system. Type '1' to use Windows 7, x64; type '2' to use Linux.
1
Hardware complies with the system requirements.
Operating system Windows 7, x64 successfully installed.

Do you want to install applications? Type 'Y' to power on or type 'N' to quit.
Y
Operation system: Windows 7, x64
Computer name: MyComputer

Installed applications:
	Calculator v0.1
	File Manager v0.1

Which application should be run? Type '1' to use Calculator; type '2' to use File Manager
1
Enter expression:
10.2+6.6
Expression result: 16.799999999999997

Operating system Windows 7, x64 is shut down.
Computer power is off.
 */
