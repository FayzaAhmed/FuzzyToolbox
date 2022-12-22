import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class GUI
{
    FuzzySystem fuzzySystem = new FuzzySystem();

    final DecimalFormat decfor = new DecimalFormat("0.00");

    JFrame createFrame(String str)
    {
        JFrame frame = new JFrame(str);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000,1000);
        frame.setResizable(false);
        frame.setVisible(true);
        return frame;
    }

    void addVariable()
    {
        JFrame frame = createFrame("Adding Variable");
        JPanel panel = new JPanel();

        JLabel label1 = new JLabel("Variable Name");
        JLabel label2 = new JLabel("Variable Type");
        JLabel label3 = new JLabel("Lower Bound");
        JLabel label4 = new JLabel("Upper Bound");
        JLabel label5 = new JLabel("Crisp Value");
        JLabel label6 = new JLabel("Hint: If Variable Type is IN");


        JTextField textField1 = new JTextField();
        JTextField textField2 = new JTextField();
        JTextField textField3 = new JTextField();
        JTextField textField4 = new JTextField();
        JTextField textField5 = new JTextField();

        label1.setBounds(10 , 50 , 150 , 50);
        label1.setFont(new Font("Arial", Font.PLAIN, 20));
        textField1.setBounds(200 , 50 , 400 , 50);
        textField1.setFont(new Font("Arial", Font.PLAIN, 20));

        label2.setBounds(10 , 150 , 150 , 50);
        label2.setFont(new Font("Arial", Font.PLAIN, 20));
        textField2.setBounds(200 , 150 , 400 , 50);
        textField2.setFont(new Font("Arial", Font.PLAIN, 20));

        label3.setBounds(10 , 250 , 150 , 50);
        label3.setFont(new Font("Arial", Font.PLAIN, 20));
        textField3.setBounds(200 , 250 , 400 , 50);
        textField3.setFont(new Font("Arial", Font.PLAIN, 20));

        label4.setBounds(10 , 350 , 150 , 50);
        label4.setFont(new Font("Arial", Font.PLAIN, 20));
        textField4.setBounds(200 , 350 , 400 , 50);
        textField4.setFont(new Font("Arial", Font.PLAIN, 20));

        label5.setBounds(10 , 450 , 150 , 50);
        label5.setFont(new Font("Arial", Font.PLAIN, 20));
        textField5.setBounds(200 , 450 , 400 , 50);
        textField5.setFont(new Font("Arial", Font.PLAIN, 20));

        label6.setBounds(200 , 500 , 250 , 50);
        label6.setFont(new Font("Arial", Font.PLAIN, 20));
        label6.setForeground(Color.red);

        JButton button = new JButton("ADD");
        button.addActionListener( new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                Variable variable = new Variable();
                variable.name = textField1.getText();
                variable.type = textField2.getText();
                variable.lower = Double.parseDouble(textField3.getText());
                variable.upper = Double.parseDouble(textField4.getText());
                if(variable.type.equals("IN")) {
                    variable.crisp_value = Double.parseDouble(textField5.getText());
                }

                fuzzySystem.variables.add(variable);

                // To close the current window(frame)
                frame.dispose();
            }
        });

        button.setBounds(350, 650, 150, 100);
        button.setPreferredSize(new Dimension(150, 100));
        button.setFont(new Font("Arial", Font.PLAIN, 20));


        panel.setLayout(null);
        panel.add(label1);        panel.add(textField1);
        panel.add(label2);        panel.add(textField2);
        panel.add(label3);        panel.add(textField3);
        panel.add(label4);        panel.add(textField4);
        panel.add(label5);        panel.add(textField5);
        panel.add(label6);
        panel.add(button);
        frame.add(panel);
    }

    void addSet()
    {
        JFrame frame = createFrame("Adding Set");
        JPanel panel = new JPanel();

        JLabel label = new JLabel("Variable Name");
        JLabel label1 = new JLabel("Set Name");
        JLabel label2 = new JLabel("Set Type");
        JLabel label3 = new JLabel("Dimension 1");
        JLabel label4 = new JLabel("Dimension 2");
        JLabel label5 = new JLabel("Dimension 3");
        JLabel label6 = new JLabel("Dimension 4");
        JLabel label7 = new JLabel("Hint: If Set Type is TRAP");

        JTextField textField = new JTextField();
        JTextField textField1 = new JTextField();
        JTextField textField2 = new JTextField();
        JTextField textField3 = new JTextField();
        JTextField textField4 = new JTextField();
        JTextField textField5 = new JTextField();
        JTextField textField6 = new JTextField();

        label.setBounds(10 , 50 , 150 , 50);
        label.setFont(new Font("Arial", Font.PLAIN, 20));
        textField.setBounds(200 , 50 , 400 , 50);
        textField.setFont(new Font("Arial", Font.PLAIN, 20));

        label1.setBounds(10 , 150 , 150 , 50);
        label1.setFont(new Font("Arial", Font.PLAIN, 20));
        textField1.setBounds(200 , 150 , 400 , 50);
        textField1.setFont(new Font("Arial", Font.PLAIN, 20));

        label2.setBounds(10 , 250 , 150 , 50);
        label2.setFont(new Font("Arial", Font.PLAIN, 20));
        textField2.setBounds(200 , 250 , 400 , 50);
        textField2.setFont(new Font("Arial", Font.PLAIN, 20));

        label3.setBounds(10 , 350 , 150 , 50);
        label3.setFont(new Font("Arial", Font.PLAIN, 20));
        textField3.setBounds(200 , 350 , 400 , 50);
        textField3.setFont(new Font("Arial", Font.PLAIN, 20));

        label4.setBounds(10 , 450 , 150 , 50);
        label4.setFont(new Font("Arial", Font.PLAIN, 20));
        textField4.setBounds(200 , 450 , 400 , 50);
        textField4.setFont(new Font("Arial", Font.PLAIN, 20));

        label5.setBounds(10 , 550 , 150 , 50);
        label5.setFont(new Font("Arial", Font.PLAIN, 20));
        textField5.setBounds(200 , 550 , 400 , 50);
        textField5.setFont(new Font("Arial", Font.PLAIN, 20));

        label6.setBounds(10 , 650 , 150 , 50);
        label6.setFont(new Font("Arial", Font.PLAIN, 20));
        textField6.setBounds(200 , 650 , 400 , 50);
        textField6.setFont(new Font("Arial", Font.PLAIN, 20));

        label7.setBounds(200 , 700 , 250 , 50);
        label7.setFont(new Font("Arial", Font.PLAIN, 20));
        label7.setForeground(Color.red);

        JButton button = new JButton("ADD");
        button.addActionListener( new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                String variableName = textField.getText();
                boolean found = false;
                for(Variable variable :fuzzySystem.variables)
                {
                    if(variable.name.equals(variableName))
                    {
                        found = true;
                        FuzzySet set = new FuzzySet();
                        set.name = textField1.getText();
                        set.type = textField2.getText();
                        double value = Double.parseDouble(textField3.getText());
                        set.values.add(value);
                        value = Double.parseDouble(textField4.getText());
                        set.values.add(value);
                        value = Double.parseDouble(textField5.getText());
                        set.values.add(value);
                        if(set.type.equals("TRAP"))
                        {
                            value = Double.parseDouble(textField6.getText());
                            set.values.add(value);
                        }
                        variable.sets.add(set);
                        break;
                    }
                }
                if(!found)
                {
                    JOptionPane.showMessageDialog(frame, "This Variable not Found","Error", JOptionPane.ERROR_MESSAGE);
                }
                frame.dispose();
            }
        });

        button.setBounds(350, 850, 150, 100);
        button.setPreferredSize(new Dimension(150, 100));
        button.setFont(new Font("Arial", Font.PLAIN, 20));

        panel.setLayout(null);
        panel.add(label);         panel.add(textField);
        panel.add(label1);        panel.add(textField1);
        panel.add(label2);        panel.add(textField2);
        panel.add(label3);        panel.add(textField3);
        panel.add(label4);        panel.add(textField4);
        panel.add(label5);        panel.add(textField5);
        panel.add(label6);        panel.add(textField6);
        panel.add(label7);
        panel.add(button);
        frame.add(panel);
    }

    void addRule()
    {
        JFrame frame = createFrame("Adding Rule");
        JPanel panel = new JPanel();

        JLabel label = new JLabel("Add Rule");

        JTextField textField = new JTextField();

        label.setBounds(350 , 200 , 400 , 50);
        label.setFont(new Font("Arial", Font.PLAIN, 30));

        textField.setBounds(150 , 300 , 600 , 60);
        textField.setFont(new Font("Arial", Font.PLAIN, 20));

        JButton button = new JButton("ADD");
        button.addActionListener( new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                String inputRule = textField.getText();
                FuzzyRule fuzzyRule = new FuzzyRule(inputRule);
                fuzzySystem.rules.add(fuzzyRule);
                frame.dispose();
            }
        });

        button.setBounds(350, 600, 150, 100);
        button.setPreferredSize(new Dimension(150, 100));
        button.setFont(new Font("Arial", Font.PLAIN, 20));

        panel.setLayout(null);
        panel.add(label);
        panel.add(textField);
        panel.add(button);
        frame.add(panel);
    }

    String run()
    {
        fuzzySystem.fuzzification();
        fuzzySystem.inference_engine();
        fuzzySystem.defuzzification();

        String output = "";

        for (Map.Entry<String, HashMap<String, Double>> entry : fuzzySystem.final_output.entrySet())
        {
            output += "The predicted " + entry.getKey();
            for (Map.Entry<String, Double> entry2 : entry.getValue().entrySet())
            {
                output += " is " + entry2.getKey() + " (" + decfor.format(entry2.getValue()) + ")" + '\n';
            }
        }
        return output;
    }

    void beginFuzzySystem()
    {
        JFrame frame = createFrame("Fuzzy System");
        JPanel panel = new JPanel();
        JLabel label1 = new JLabel("Name");
        JLabel label2 = new JLabel("Description");
        JTextField textField1 = new JTextField();
        JTextField textField2 = new JTextField();

        panel.setLayout(null);

        label1.setBounds(10 , 50 , 150 , 50);
        label1.setFont(new Font("Arial", Font.PLAIN, 20));
        textField1.setBounds(200 , 50 , 400 , 50);
        textField1.setFont(new Font("Arial", Font.PLAIN, 20));


        label2.setBounds(10 , 150 , 150 , 50);
        label2.setFont(new Font("Arial", Font.PLAIN, 20));
        textField2.setBounds(200 , 150 , 400 , 50);
        textField2.setFont(new Font("Arial", Font.PLAIN, 20));

        JButton button1 = new JButton("Add Variable");
        button1.addActionListener( new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                addVariable();
            }
        });

        button1.setBounds(200, 400, 150, 100);
        button1.setPreferredSize(new Dimension(200, 100));
        button1.setFont(new Font("Arial", Font.PLAIN, 20));

        JButton button2 = new JButton("Add Set");
        button2.addActionListener( new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                addSet();
            }
        });

        button2.setBounds(400, 400, 150, 100);
        button2.setPreferredSize(new Dimension(200, 100));
        button2.setFont(new Font("Arial", Font.PLAIN, 20));

        JButton button3 = new JButton("Add Rule");
        button3.addActionListener( new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                addRule();
            }
        });

        button3.setBounds(200, 600, 150, 100);
        button3.setPreferredSize(new Dimension(200, 100));
        button3.setFont(new Font("Arial", Font.PLAIN, 20));

        JButton button4 = new JButton("Run System");
        button4.addActionListener( new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if(fuzzySystem.variables.size() > 0 && fuzzySystem.rules.size() > 0) {
                    boolean check = true;
                    for(Variable variable : fuzzySystem.variables)
                    {
                        if(variable.sets.size() == 0)
                        {
                            check = false;
                            JOptionPane.showMessageDialog(frame, "There is a variable without sets", "Error", JOptionPane.ERROR_MESSAGE);
                            break;
                        }
                    }
                    if(check)
                    {
                        String output = run();
                        JOptionPane.showMessageDialog(frame, output, "Output", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(frame, "Missing Variables or Rules for Fuzzy System", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        button4.setBounds(400, 600, 150, 100);
        button4.setPreferredSize(new Dimension(200, 100));
        button4.setFont(new Font("Arial", Font.PLAIN, 20));

        panel.add(label1);
        panel.add(textField1);
        panel.add(label2);
        panel.add(textField2);
        panel.add(button1);
        panel.add(button2);
        panel.add(button3);
        panel.add(button4);
        frame.add(panel);
    }

    void execute_gui()
    {
        JFrame frame = createFrame("Fuzzy Toolbox");
        JPanel panel = new JPanel();

        panel.setSize(1000, 1000);  //panel.setLocation(100, 100);
        panel.setLayout(null);

        JButton button1 = new JButton("Create Fuzzy System");
        button1.addActionListener( new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                beginFuzzySystem();
            }
        });

        button1.setBounds(250, 250, 500, 150);
        button1.setPreferredSize(new Dimension(500, 100));
        button1.setFont(new Font("Arial", Font.PLAIN, 30));

        JButton button2 = new JButton("Quit The System");
        button2.addActionListener( new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                System.exit(0);
            }
        });

        button2.setBounds(250, 500, 500, 150);
        button2.setPreferredSize(new Dimension(500, 100));
        button2.setFont(new Font("Arial", Font.PLAIN, 30));

        panel.add(button1);
        panel.add(button2);
        frame.setContentPane(panel);
    }
}
