package geekOutMasters;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.Border;


public class Header extends JLabel {
    /**
     * Constructor of the Header class
     * @param title String that contains Header text
     * @param colorBackground Color object to be assigned for the Header background
     */
    public Header(String title, Color colorBackground){
        this.setText(title);
        this.setBackground(colorBackground);
        this.setForeground(new Color(0,0,0));
        this.setFont(new Font(Font.DIALOG_INPUT,Font.BOLD,28));
        this.setHorizontalAlignment(JLabel.CENTER);
        this.setVerticalAlignment(JLabel.CENTER);
        this.setOpaque(true);

        Border border = BorderFactory.createMatteBorder(1, 0, 1, 0, Color.BLACK);
        this.setBorder(border);
    }
}
