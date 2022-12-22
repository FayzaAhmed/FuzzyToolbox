import java.util.ArrayList;

public class FuzzySet
{
    String name;
    String type;

    ArrayList<Double> values = new ArrayList<>();

    double membership;
    double centroid;

    double get_line_slope(double x1, double y1, double x2, double y2)
    {
        return (y2 - y1)/(x2 - x1);
    }

    double get_y_of_crisp_value(double x1, double y1, double x2, double y2, double crispValue)
    {
        double slope = get_line_slope(x1, y1, x2, y2);
        double bias = y1 - (slope * x1);
        return (slope * crispValue) + bias;
    }

    double trapezoidal_membership (double crispValue)
    {
        if (crispValue >= values.get(0) && crispValue <= values.get(1))
        {
            membership = get_y_of_crisp_value(values.get(0), 0, values.get(1), 1, crispValue);
        }
        else if (crispValue > values.get(1) && crispValue <= values.get(2))
        {
            membership = 1;
        }
        else if (crispValue > values.get(2) && crispValue <= values.get(3))
        {
            membership = get_y_of_crisp_value(values.get(2), 1, values.get(3), 0, crispValue);
        }
        else
        {
            membership = 0;
        }
        return membership;
    }

    double triangle_membership (double crispValue)
    {
        if (crispValue >= values.get(0) && crispValue <= values.get(1))
        {
            membership = get_y_of_crisp_value(values.get(0), 0, values.get(1), 1, crispValue);
        }
        else if (crispValue > values.get(1) && crispValue <= values.get(2))
        {
            membership = get_y_of_crisp_value(values.get(1), 1, values.get(2), 0, crispValue);
        }
        else
        {
            membership = 0;
        }
        return membership;
    }

    //A (signed area) = 1/2 ∑ (Xi*(Yi+1) − (Xi+1)*Yi)
    double calcArea()
    {
        double xa = values.get(0);
        double xb = values.get(1);
        double xc = values.get(2);
        double xd = values.get(3);

        double ya = 0;
        double yb = 1;
        double yc = 1;
        double yd = 0;

        double sum =  (xa*yb - xb*ya) + (xb*yc - xc*yb) + (xc*yd - xd*yc);

        return (1.0/2.0) * sum;
    }

    // Cx = 1/6 * area ∑ (Xi + (Xi+1)) * (Xi * (Yi+1) − (Xi+1) * Yi)
    void trapezoidal_centroid()
    {
        double area = calcArea();

        double xa = values.get(0);
        double xb = values.get(1);
        double xc = values.get(2);
        double xd = values.get(3);

        double ya = 0;
        double yb = 1;
        double yc = 1;
        double yd = 0;

        centroid = (1.0 / (6.0 * area))*
                    (((xa + xb) * (xa * yb - xb * ya))+
                    ((xb + xc) * (xb * yc - xc * yb))+
                    ((xc + xd) * (xc * yd - xd * yc)));
    }

    void triangle_centroid()
    {
        double sum = 0;
        for (double value : values)
        {
            sum += value;
        }
        centroid =  sum/3;
    }

    @Override
    public String toString()
    {
        return "FuzzySet{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", values=" + values +
                '}';
    }
}
