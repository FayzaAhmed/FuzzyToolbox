import java.util.ArrayList;
import java.util.HashMap;

public class FuzzySystem
{
    ArrayList<Variable> variables = new ArrayList<>();
    ArrayList<FuzzyRule> rules = new ArrayList<>();

    HashMap<String, HashMap<String, Double>> input_memberships = new HashMap<>();
    HashMap<String, HashMap<String, Double>> output_memberships = new HashMap<>();
    HashMap<String, HashMap<String, Double>> final_output = new HashMap<>();

    String fuzzy_name;
    String description;

    void fuzzification()
    {
       for (Variable variable : variables)
       {
           if (variable.type.equals("IN"))
           {
               variable.calc_membership();
               input_memberships.put(variable.name, variable.variable_membership);
           }
       }
    }

    // calculating the membership of the right hand side of the rule
    void inference_engine()
    {
        output_memberships = new HashMap<>();
        for (Variable variable : variables)
        {
            if (variable.type.equals("OUT"))
            {
                if(variable.done == false)
                {
                    // initialization of membership to -1
                    for (FuzzySet set : variable.sets)
                    {
                        variable.variable_membership.put(set.name, -1.0);
                    }

                    output_memberships.put(variable.name, variable.variable_membership);

                    for (FuzzyRule rule : rules)
                    {
                        rule.solve_rule(input_memberships, output_memberships, variables, variable);
                    }

                    for (FuzzySet set: variable.sets)
                    {
                        double value =  output_memberships.get(variable.name).get(set.name);
                        set.membership = value;
                        variable.variable_membership.put(set.name, value);
                    }

                    input_memberships.put(variable.name , variable.variable_membership);

                    variable.done = true;
                }
            }
        }
    }

    void defuzzification()
    {
         for (Variable variable : variables)
         {
             if (variable.type.equals("OUT"))
             {
                 String output_variable = variable.name;
                 String output_setname = "";

                 variable.calc_centroids();

                 double sum = 0.0;
                 double sum_memberships = 0.0;
                 double max = Double.MIN_VALUE;

                 for(FuzzySet set : variable.sets)
                 {
                     if (max < (set.membership * set.centroid))
                     {
                         max = set.membership * set.centroid;
                         output_setname = set.name;
                     }
                     sum += (set.membership * set.centroid);
                     sum_memberships += set.membership;
                 }

                 double output_crisp_value = sum / sum_memberships;

                 HashMap<String, Double> map = new HashMap<>();

                 map.put(output_setname ,output_crisp_value );

                 final_output.put(output_variable, map);
             }
         }
    }
}
