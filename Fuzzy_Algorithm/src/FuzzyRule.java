import java.util.ArrayList;
import java.util.HashMap;

public class FuzzyRule
{
    String rule;

    ArrayList<Double> results = new ArrayList<>();

    double not_variable = -1;
    double and_variable = -2;
    double or_variable = -3;

    FuzzyRule(String rule){
        this.rule = rule;
    }

    void solve_rule(HashMap<String, HashMap<String, Double>> input_memberships, HashMap<String, HashMap<String, Double>> output_membership, ArrayList<Variable> variables, Variable outputVariable)
    {
        rule = rule.replace("and_not", "and not");
        rule = rule.replace("or_not", "or not");

        String [] splitter_rule = rule.split("=> ");
        String [] premise = splitter_rule[0].split(" ");
        String [] conclusion = splitter_rule[1].split(" ");

        if(outputVariable.name.equals(conclusion[0]))
        {
            if (takeRule(premise, conclusion[0], variables))
            {
                substitute(premise, input_memberships);
                evaluate_operations();

                String variable_name = conclusion[0];
                String set_name = conclusion[1];

                // if two rules infers same memberships => take the maximum one
                double old_membership = output_membership.get(variable_name).get(set_name);
                if (old_membership < results.get(0))
                {
                    output_membership.get(variable_name).replace(set_name, old_membership, results.get(0));
                }
            }
        }
    }

    boolean takeRule(String [] premise, String conclusion, ArrayList<Variable> variables)
    {
        int check_premise = 0;
        int variablesCounter = 0;

        // check if the variables in left hand side ready as input
        for (int i = 0; i < premise.length; i++)
        {
            for (Variable variable : variables)
            {
                if (premise[i].equals(variable.name))
                {
                    variablesCounter ++;
                    if(variable.type.equals("IN") || (variable.type.equals("OUT") && variable.done == true))
                    {
                        check_premise ++;
                    }
                }
            }
        }
        if(check_premise == variablesCounter)
        {
            return true;
        }

        // do not take this rule it is not ready yet
        return  false;
    }

    void substitute(String [] premise, HashMap<String, HashMap<String, Double>> input_memberships)
    {
        for (int i = 0; i < premise.length; i++)
        {
            double result = 0.0;
            if (premise[i].equals("not"))
            {
                result = not_variable;
            }
            else if (premise[i].equals("and"))
            {
                result = and_variable;
            }
            else if (premise[i].equals("or"))
            {
                result = or_variable;
            }
            else if(i+1 < premise.length)
            {
                result = input_memberships.get(premise[i]).get(premise[i+1]);
                i++;
            }
            results.add(result);
        }
    }

    void evaluate_operations()
    {
        evaluate_not();
        evaluate_and();
        evaluate_or();
    }

    void evaluate_not()
    {
        for(int i = 0; i < results.size(); i++)
        {
            if (results.get(i) == not_variable)
            {
                double result =  1 - results.get(i+1);
                results.set(i, result);
                results.remove(i+1);
            }
        }
    }

    void evaluate_and()
    {
        for(int i = 0; i < results.size(); i++)
        {
            if (results.get(i) == and_variable)
            {
                double left_operand = results.get(i-1);
                double right_operand = results.get(i+1);
                double result = Math.min(left_operand, right_operand);
                results.set(i-1, result);
                results.remove(i);
                results.remove(i);
                i--;
            }
        }
    }

    void evaluate_or()
    {
        for(int i = 0; i < results.size(); i++)
        {
            if (results.get(i) == or_variable)
            {
                double left_operand = results.get(i-1);
                double right_operand = results.get(i+1);
                double result = Math.max(left_operand, right_operand);
                results.set(i-1, result);
                results.remove(i);
                results.remove(i);
                i--;
            }
        }
    }

    @Override
    public String toString()
    {
        return "FuzzyRule{" +
                "rule='" + rule + '\'' +
                '}';
    }
}
