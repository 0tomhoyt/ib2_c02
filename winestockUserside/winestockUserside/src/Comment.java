import org.json.JSONArray;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Comment extends JFrame{
    private JPanel commentPanel;
    private JButton homeButton;
    private JButton update1;
    private JButton update2;
    private JButton update3;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JLabel commentAreaNotice;

    public Comment(String title)
    {
        super(title);

        homeButton. setIcon(new ImageIcon(stock.class.getResource("/com/Home_icon.png")));
        homeButton.setText("home");
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame ui = new Homepage("homepage");
                ui.setVisible(true);
                ui.setSize(600,800);
                ui.setLocation(500,0);
                dispose();
            }
        });

        DataBase db = new DataBase();
        String response = db.makeGETRequest("https://studev.groept.be/api/a21ib2c02/getComment");
        JSONArray array = new JSONArray(response);

        textField1.setText(array.getJSONObject(0).getString("comment"));
        textField2.setText(array.getJSONObject(1).getString("comment"));
        textField3.setText(array.getJSONObject(2).getString("comment"));
        update1.setText("update");
        update2.setText("update");
        update3.setText("update");
        update1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                https://studev.groept.be/api/a21ib2c02/updateComment/n/i
                db.makeGETRequest("https://studev.groept.be/api/a21ib2c02/updateComment/"+textField1.getText()+"/1");
                JOptionPane.showMessageDialog(new JFrame(),"updated");
            }
        });
        update2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                db.makeGETRequest("https://studev.groept.be/api/a21ib2c02/updateComment/"+textField2.getText()+"/2");
                JOptionPane.showMessageDialog(new JFrame(),"updated");
            }
        });
        update3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                db.makeGETRequest("https://studev.groept.be/api/a21ib2c02/updateComment/"+textField3.getText()+"/3");
                JOptionPane.showMessageDialog(new JFrame(),"updated");
            }
        });

        commentPanel.setBackground(Color.LIGHT_GRAY);
        setContentPane(commentPanel);
    }
}
