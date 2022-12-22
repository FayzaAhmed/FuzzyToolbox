import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    static final DecimalFormat decfor = new DecimalFormat("0.00");

    static FuzzySystem fillData()
    {
        FuzzySystem fuzzySystem = new FuzzySystem();

        Variable variable1 = new Variable();
        variable1.name = "dirt";
        variable1.type = "IN";
        variable1.lower = 0;
        variable1.upper = 100;
        FuzzySet set = new FuzzySet();
        set.name = "small";
        set.type = "TRAP";
        set.values.add(0.0);
        set.values.add(0.0);
        set.values.add(20.0);
        set.values.add(40.0);
        variable1.sets.add(set);
        set = new FuzzySet();
        set.name = "medium";
        set.type = "TRAP";
        set.values.add(20.0);
        set.values.add(40.0);
        set.values.add(60.0);
        set.values.add(80.0);
        variable1.sets.add(set);
        set = new FuzzySet();
        set.name = "large";
        set.type = "TRAP";
        set.values.add(60.0);
        set.values.add(80.0);
        set.values.add(100.0);
        set.values.add(100.0);
        variable1.sets.add(set);
        variable1.crisp_value = 60;
        //////////////////////////////////////////////////
        Variable variable2 = new Variable();
        variable2.name = "fabric";
        variable2.type = "IN";
        variable2.lower = 0;
        variable2.upper = 100;
        set = new FuzzySet();
        set.name = "soft";
        set.type = "TRAP";
        set.values.add(0.0);
        set.values.add(0.0);
        set.values.add(20.0);
        set.values.add(40.0);
        variable2.sets.add(set);
        set = new FuzzySet();
        set.name = "ordinary";
        set.type = "TRAP";
        set.values.add(20.0);
        set.values.add(40.0);
        set.values.add(60.0);
        set.values.add(80.0);
        variable2.sets.add(set);
        set = new FuzzySet();
        set.name = "stiff";
        set.type = "TRAP";
        set.values.add(60.0);
        set.values.add(80.0);
        set.values.add(100.0);
        set.values.add(100.0);
        variable2.sets.add(set);
        variable2.crisp_value = 25;
        //////////////////////////////////
        Variable variable3 = new Variable();
        variable3.name = "wash_time";
        variable3.type = "OUT";
        variable3.lower = 0;
        variable3.upper = 60;
        set = new FuzzySet();
        set.name = "very_small";
        set.type = "TRI";
        set.values.add(0.0);
        set.values.add(0.0);
        set.values.add(15.0);
        variable3.sets.add(set);
        set = new FuzzySet();
        set.name = "small";
        set.type = "TRI";
        set.values.add(0.0);
        set.values.add(15.0);
        set.values.add(30.0);
        variable3.sets.add(set);
        set = new FuzzySet();
        set.name = "standard";
        set.type = "TRI";
        set.values.add(15.0);
        set.values.add(30.0);
        set.values.add(45.0);
        variable3.sets.add(set);
        set = new FuzzySet();
        set.name = "large";
        set.type = "TRI";
        set.values.add(30.0);
        set.values.add(45.0);
        set.values.add(60.0);
        variable3.sets.add(set);
        set = new FuzzySet();
        set.name = "very_large";
        set.type = "TRI";
        set.values.add(45.0);
        set.values.add(60.0);
        set.values.add(60.0);
        variable3.sets.add(set);
        fuzzySystem.variables.add(variable1);
        fuzzySystem.variables.add(variable2);
        fuzzySystem.variables.add(variable3);
        String rule1 = "dirt small and fabric soft => wash_time very_small";
        String rule2 = "dirt medium and fabric ordinary => wash_time standard";
        String rule3 = "dirt small and_not fabric soft or dirt medium and fabric soft => wash_time small";
        String rule4 =  "dirt medium and fabric stiff => wash_time large";
        String rule5 = "dirt large and_not fabric soft => wash_time very_large";
        String rule6 = "dirt large and fabric soft => wash_time standard";
        FuzzyRule rule = new FuzzyRule(rule1);
        fuzzySystem.rules.add(rule);

        rule = new FuzzyRule(rule2);
        fuzzySystem.rules.add(rule);

        rule = new FuzzyRule(rule3);
        fuzzySystem.rules.add(rule);

        rule = new FuzzyRule(rule4);
        fuzzySystem.rules.add(rule);

        rule = new FuzzyRule(rule5);
        fuzzySystem.rules.add(rule);

        rule = new FuzzyRule(rule6);
        fuzzySystem.rules.add(rule);

        return fuzzySystem;
    }

    static void menu()
    {
        System.out.println("Fuzzy Logic Toolbox\n===================");
        System.out.println("1- Create a new fuzzy system\n2- Quit");
        Scanner input = new Scanner(System.in);
        int choice = input.nextInt();

        if (choice == 1){
            System.out.println("Enter the system’s name and a brief description:\n------------------------------------------------");
            FuzzySystem fuzzySystem = new FuzzySystem();
            fuzzySystem.fuzzy_name = input.next();
            fuzzySystem.description= input.next();
            boolean first = false;
            boolean second = false;
            boolean third = false;
            while (true){
                System.out.println("Main Menu:\n==========");
                System.out.println("1- Add variables.\n2- Add fuzzy sets to an existing variable.");
                System.out.println("3- Add rules.\n4- Run the simulation on crisp values.");
                String innerChoice = input.next();
                // add variable
                if(innerChoice.equals("1")){
                    System.out.println("Enter the variable’s name, type (IN/OUT) and range ([lower, upper]):\n(Press x to finish)\n--------------------------------------------------------------------");
                    while (true) {
                        Variable variable = new Variable();
                        String name = input.next();
                        if (name.equalsIgnoreCase("x"))
                            break;
                        variable.name = name;
                        variable.type = input.next();
                        String range = input.nextLine();
                        range = range.replaceAll("\\s", "");
                        int indexOfComma = range.indexOf(',');
                        variable.lower = Double.parseDouble(range.substring(1, indexOfComma));
                        variable.upper = Double.parseDouble(range.substring(indexOfComma+1, range.length()-1));
                        fuzzySystem.variables.add(variable);
                    }
                  first = true;
                }
                // add sets to the variable
                else if(innerChoice.equals("2")){
                    System.out.println("Enter the variable’s name:\n--------------------------");
                    String variableName = input.next();
                    boolean found = false;
                    for(Variable variable :fuzzySystem.variables)
                    {
                        if(variable.name.equals(variableName))
                        {
                            System.out.println("Enter the fuzzy set name, type (TRI/TRAP) and values:\n(Press x to finish)\n-----------------------------------------------------");
                            while (true)
                            {
                                FuzzySet set = new FuzzySet();
                                String name = input.next();
                                if (name.equalsIgnoreCase("x"))
                                    break;
                                set.name = name;
                                set.type = input.next();
                                double value = input.nextDouble();
                                set.values.add(value);
                                value = input.nextDouble();
                                set.values.add(value);
                                value = input.nextDouble();
                                set.values.add(value);
                                if(set.type.equals("TRAP"))
                                {
                                    value = input.nextDouble();
                                    set.values.add(value);
                                }
                                variable.sets.add(set);
                            }//end of while
                            found = true;
                            break;
                        }//end of if
                    }//end of for
                    if (!found) System.out.println("Error: Variable not found in the system");
                    second = true;
                }
                // add rules
                else if (innerChoice.equals("3")){
                    System.out.println("Enter the rules in this format:\n(Press x to finish)");
                    System.out.println("IN_variable set operator IN_variable set => OUT_variable set\n------------------------------------------------------------");
                    while (true){
                        String inputRule = input.nextLine();
                        if(inputRule.equalsIgnoreCase("x"))break;
                        if (inputRule.isEmpty()){
                            continue;
                        }
                        FuzzyRule fuzzyRule = new FuzzyRule(inputRule);
                        fuzzySystem.rules.add(fuzzyRule);
                    }
                    third = true;
                }

                else if (innerChoice.equals("4")){
                    if (first & second & third) {
                        System.out.println("Enter the crisp values:\n-----------------------");
                        for (Variable variable : fuzzySystem.variables) {
                            if (variable.type.equals("IN")) {
                                System.out.print(variable.name + ": ");
                                variable.crisp_value = input.nextInt();
                            }
                        }
                        System.out.println("Running the simulation…");
                        fuzzySystem.fuzzification();
                        System.out.println("Fuzzification => done");
                        fuzzySystem.inference_engine();
                        System.out.println("Inference => done");
                        fuzzySystem.defuzzification();
                        System.out.println("Defuzzification => done");

                        for (Map.Entry<String, HashMap<String, Double>> entry : fuzzySystem.final_output.entrySet())
                        {
                            System.out.print("The predicted " + entry.getKey());
                            for (Map.Entry<String, Double> entry2 : entry.getValue().entrySet())
                            {
                                System.out.println(" is " + entry2.getKey()+ " (" + decfor.format(entry2.getValue()) + ")");
                            }
                        }
                        System.out.println();
                    }
                    else if (!first)
                    {
                        System.out.println("CAN’T START THE SIMULATION! Please add the fuzzy variables.");
                    }
                    else if (!second){
                        System.out.println("CAN’T START THE SIMULATION! Please add the fuzzy sets.");
                    }
                    else {
                        System.out.println("CAN’T START THE SIMULATION! Please add rules.");
                    }
                }
                else if (innerChoice.equalsIgnoreCase("close")){
                    break;
                }
            }
        }
        else
        {
           System.exit(0);
        }
    }

    static void fileHandling() throws IOException
    {
        File file = new File("input.txt");
        Scanner myReader = new Scanner(file);

        FuzzySystem fuzzySystem = new FuzzySystem();

        fuzzySystem.fuzzy_name = myReader.nextLine();
        fuzzySystem.description = myReader.nextLine();

        int numOfVariables = myReader.nextInt();

        for(int i = 0; i < numOfVariables; i++)
        {
            Variable variable = new Variable();
            variable.name = myReader.next();
            variable.type = myReader.next();
            variable.lower = myReader.nextDouble();
            variable.upper = myReader.nextDouble();
            int numOfSets = myReader.nextInt();
            for (int j = 0; j < numOfSets; j++)
            {
                FuzzySet set = new FuzzySet();
                set.name = myReader.next();
                set.type = myReader.next();
                set.values.add(myReader.nextDouble());
                set.values.add(myReader.nextDouble());
                set.values.add(myReader.nextDouble());
                if(set.type.equals("TRAP")){
                    set.values.add(myReader.nextDouble());
                }
                variable.sets.add(set);
            }

            fuzzySystem.variables.add(variable);
        }

        int numOfRules = myReader.nextInt();
        for(int i = 0; i < numOfRules; i++)
        {
            String ruleStr = myReader.nextLine();
            if(ruleStr.isEmpty())
                ruleStr = myReader.nextLine();
            FuzzyRule rule = new FuzzyRule(ruleStr);
            fuzzySystem.rules.add(rule);
        }

        int numOfCrispValues = myReader.nextInt();
        for(int i = 0; i < numOfCrispValues; i++)
        {
            double inputCrisp = myReader.nextDouble();
            fuzzySystem.variables.get(i).crisp_value = inputCrisp;
        }

        myReader.close();

        FileWriter fileWriter = new FileWriter("output.txt");

        fileWriter.write("Running the simulation…" + '\n');
        fuzzySystem.fuzzification();
        fileWriter.write("Fuzzification => done" + '\n');

        fuzzySystem.inference_engine();
        fileWriter.write("Inference => done" + '\n');

        fuzzySystem.defuzzification();
        fileWriter.write("Defuzzification => done" + '\n');

        for (Map.Entry<String, HashMap<String, Double>> entry : fuzzySystem.final_output.entrySet())
        {
            fileWriter.write("The predicted " + entry.getKey());
            for (Map.Entry<String, Double> entry2 : entry.getValue().entrySet())
            {
                fileWriter.write(" is " + entry2.getKey()+ " (" + decfor.format(entry2.getValue()) + ")" + '\n');
            }
        }
        fileWriter.write('\n');
        fileWriter.close();
    }

    public static void main(String[] args) throws IOException
    {
        System.out.println("1- Terminal\n2- File\n3- GUI\n4- Quit");
        Scanner input = new Scanner(System.in);
        int choice = input.nextInt();
        switch (choice)
        {
            case 1:
            {
                while (true)
                {
                    menu();
                }
            }
            case 2:
            {
                fileHandling();
                break;
            }
            case 3:
            {
                GUI gui = new GUI();
                gui.execute_gui();
                break;
            }
            case 4:
            {
                System.exit(0);
                break;
            }
            default:
            {
                System.out.println("Invalid Input, PLease Choose a number from 1 to 4 ");
            }
        }
    }
}