import java.util.ArrayList;
import java.util.HashMap;

public class Variable {
    String name;
    String type; // IN or OUT

    double lower;
    double upper;
    double crisp_value;

    boolean done = false; // case multiple output variables

    ArrayList <FuzzySet> sets = new ArrayList<>();
    HashMap <String, Double> variable_membership = new HashMap<>();

    void calc_membership()
    {
        for (FuzzySet set :sets)
        {
            double membership;
            if(set.type.equals("TRI"))
            {
                membership = set.triangle_membership(crisp_value);
            }
            else
            {
                membership = set.trapezoidal_membership(crisp_value);
            }
            variable_membership.put(set.name, membership);
        }
    }

    void calc_centroids()
    {
        for (FuzzySet set : sets)
        {
           if (set.type.equals("TRI"))
           {
               set.triangle_centroid();
           }
           else
           {
               set.trapezoidal_centroid();
           }
        }
    }

    @Override
    public String toString()
    {
        return "Variable{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", sets=" + sets +
                ", lower=" + lower +
                ", upper=" + upper +
                ", crisp_value=" + crisp_value +
                '}';
    }
}
