import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI {

    public static JFrame frame;
    public static JPanel top, bottom, title;
    public static ColorGrid cg;
    public static JPanel[][] main;
    public static Border blackline;

    public static void main(String[] args){
        JFrame frame = new JFrame("Color Sorter");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600,600);
        frame.setResizable(false);

        GridLayout grid = new GridLayout(20,20);
        JPanel bigMain = new JPanel();
        BoxLayout total = new BoxLayout(bigMain, BoxLayout.Y_AXIS);

        bigMain.setLayout(total);
        JPanel title = new JPanel();
        JLabel header = new JLabel("Color Sorter");
        title.add(header);

        Border blackline = BorderFactory.createLineBorder(Color.black);
        //frame.setLayout(grid);
        JPanel top = new JPanel();
        top.setLayout(grid);
        JPanel[][] main = new JPanel[20][20];
        //later change it to be manipulated by the user
        ColorGrid cg = new ColorGrid();
        JLabel[][] labelArray = new JLabel[20][20];
        for(int i = 0; i < 20; i++){
            for(int j = 0; j < 20; j++){
                main[i][j] = new JPanel();
                labelArray[i][j] = new JLabel("");
                labelArray[i][j].setBackground(cg.getColorArray()[i][j]);
                //main[i][j].add(labelArray[i][j]);
                main[i][j].setBackground(cg.getColorArray()[i][j]);
                main[i][j].setBorder(blackline);
                top.add(main[i][j]);
            }
        }
        JPanel bottom = new JPanel();
        JButton sort = new JButton("Sort");
        String[] sortingOptions = {"Bubble Sort", "Selection Sort", "Insertion Sort", "Quick Sort", "Merge Sort"};
        JComboBox options = new JComboBox(sortingOptions);
        JButton refresh = new JButton("Refresh");
        options.setSelectedIndex(0);
        bottom.add(options);
        bottom.add(sort);
        bottom.add(refresh);
        bigMain.add(title);
        bigMain.add(top);
        bigMain.add(bottom);
        frame.add(bigMain);
        frame.pack();
        frame.setVisible(true);
        refresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cg.refresh();
                top.removeAll();
                for(int i = 0; i < 20; i++){
                    for(int j = 0; j < 20; j++){
                        main[i][j] = new JPanel();
                        labelArray[i][j] = new JLabel("");
                        labelArray[i][j].setBackground(cg.getColorArray()[i][j]);
                        //main[i][j].add(labelArray[i][j]);
                        main[i][j].setBackground(cg.getColorArray()[i][j]);
                        main[i][j].setBorder(blackline);
                        top.add(main[i][j]);
                    }
                }
                top.updateUI();
            }
        });
        sort.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String choice = (String)options.getSelectedItem();
                switch(choice){
                    case"Bubble Sort":
                        for(int i = 0; i < 20; i++){
                            for(int j = 0; j < 19; j++){
                                if(cg.compareRGB(cg.getColorArray()[i][j], cg.getColorArray()[i][j+1])){
                                    Color temp = cg.getColorArray()[i][j];
                                    cg.getColorArray()[i][j] = cg.getColorArray()[i][j+1];
                                    cg.getColorArray()[i][j+1] = temp;
                                    top.removeAll();
                                    for(int n = 0; n < 20; n++){
                                        for(int k = 0; k < 20; k++){
                                            main[n][k] = new JPanel();
                                            labelArray[n][k] = new JLabel("");
                                            labelArray[n][k].setBackground(cg.getColorArray()[n][k]);
                                            //main[i][j].add(labelArray[i][j]);
                                            main[n][k].setBackground(cg.getColorArray()[i][k]);
                                            main[n][k].setBorder(blackline);
                                            top.add(main[i][k]);
                                        }
                                    }
                                    top.updateUI();
                                    frame.pack();
                                }
                            }
                        }
                    case"Selection Sort":
                    case"Insertion Sort":
                    case"Quick Sort":
                    case"Merge Sort":

                }
            }
        });
    }

    public static void updateGUI(Color[][] colors){
        top.removeAll();
        for(int i = 0; i < 20; i++){
            for(int j = 0; j < 20; j++){
                main[i][j] = new JPanel();
                //labelArray[i][j] = new JLabel("");
                //labelArray[i][j].setBackground(cg.getColorArray()[i][j]);
                //main[i][j].add(labelArray[i][j]);
                main[i][j].setBackground(colors[i][j]);
                main[i][j].setBorder(blackline);
                top.add(main[i][j]);
            }
        }
        top.updateUI();
    }
}
