import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
class Rules extends JFrame 
{
String arr[]=
{
"RULES",
"This is the 1st line",
"This is the 1st line",
"This is the 1st line",
"This is the 1st line",
"This is the 1st line",
"This is the 1st line",
"This is the 1st line",
"This is the 1st line",
"This is the 1st line",
};
JLabel arr2[]=new JLabel[9];
public Rules()
{
setBackground(Color.cyan);
setLayout(new GridLayout(9,1));
setFont(new Font("Comic Sans MS",Font.BOLD,18));
for(int i=0;i<9;i++)
{
arr2[i]=new JLabel(arr[i],JLabel.CENTER);
arr2[i].setFont(new Font("Comic Sans MS",Font.BOLD+Font.ITALIC,18));
arr2[i].setForeground(Color.magenta);
add(arr2[i]);
}
pack();
}
}