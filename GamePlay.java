import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
class Gallows extends JPanel 
{
GamePlay temp;
int x;
public Gallows(GamePlay gp)
{
temp=gp;
x=temp.chances;
setBackground(Color.cyan);
setLayout(new BorderLayout());
}
public void what_to_draw()
{
x=temp.chances;
repaint();
}
public void draw_head(Graphics g)
{
g.drawOval(40,40,30,30);
}
public void draw_body(Graphics g)
{
g.drawLine(52,69,52,110);
}
public void draw_left_arm(Graphics g)
{
g.drawLine(37,94,52,79);
}
public void draw_right_arm(Graphics g)
{
g.drawLine(52,79,67,94);
}
public void draw_left_leg(Graphics g)
{
g.drawLine(32,130,52,110);//left leg
}
public void draw_right_leg(Graphics g)
{
g.drawLine(52,110,72,130);
}
public void draw_pole_vertical(Graphics g)
{
g.fillRect(5,5,10,200);
}
public void draw_pole_horizontal(Graphics g)
{
g.fillRect(15,5,70,10);
}
public void draw_noose(Graphics g)
{
g.drawLine(52,15,52,40);
}
public void paintComponent(Graphics g)
{
setBackground(Color.cyan);
g.setColor(Color.blue);
x=temp.chances;
if(x==7)
{
return;
}
switch(x)
{
case 6:
draw_head(g);
break;
case 5:
draw_head(g);
draw_body(g);
break;
case 4:
draw_head(g);
draw_body(g);
draw_left_arm(g);
draw_right_arm(g);
break;
case 3:
draw_head(g);
draw_body(g);
draw_left_arm(g);
draw_right_arm(g);
draw_left_leg(g);
draw_right_leg(g);
break;
case 2:
draw_head(g);
draw_body(g);
draw_left_arm(g);
draw_right_arm(g);
draw_left_leg(g);
draw_right_leg(g);
draw_pole_vertical(g);
break;
case 1:
draw_head(g);
draw_body(g);
draw_left_arm(g);
draw_right_arm(g);
draw_left_leg(g);
draw_right_leg(g);
draw_pole_vertical(g);
draw_pole_horizontal(g);
break;
case 0:
draw_head(g);
draw_body(g);
draw_left_arm(g);
draw_right_arm(g);
draw_left_leg(g);
draw_right_leg(g);
draw_pole_vertical(g);
draw_pole_horizontal(g);
draw_noose(g);
}
}
}
class MyButtons extends JPanel implements ActionListener
{
Button letters[]=new Button[26];
GamePlay temp;
public MyButtons(GamePlay gp)
{
temp=gp;
setBackground(Color.magenta);
setForeground(Color.black);
setLayout(new GridLayout(6,13));
setFont(new Font("Comic Sans MS",Font.BOLD,18));
for(int i=0;i<26;i++)
{
add (new Label(" "));
}
for(int i=0;i<26;i++)
{
add (new Label(" "));
}
for(int i=0;i<26;i++)
{
letters[i]=new Button(""+(char)(i+65));
letters[i].addActionListener(this);
add(letters[i]);
}
}
public void disableButtons()
{
for(int i=0;i<26;i++)
{
letters[i].setEnabled(false);
}
}

public void actionPerformed(ActionEvent ae)
{
String str=ae.getActionCommand();
str=str.trim();
if(str.equals(""))
{
return;
}
char ch=str.charAt(0);
int x=(int)ch;
letters[x-65].setLabel("");
temp.ch1=ch;
temp.control();
}
}
class Response extends JPanel 
{
JLabel strhint,strchances,progress;
JLabel answer;
GamePlay temp;
public Response(GamePlay gp)
{
temp=gp;
setBackground(Color.lightGray);
setForeground(Color.red);
setLayout(new GridLayout(4,1));
strchances=new JLabel(gp.chances+" chances remaining.",JLabel.CENTER);
strchances.setFont(new Font("Ariel",Font.BOLD,22));
add (strchances);
strhint=new JLabel("Hint : "+temp.hint,JLabel.CENTER);
strhint.setFont(new Font("Ariel",Font.BOLD,22));
add(strhint);
progress=new JLabel("",JLabel.CENTER);
progress.setFont(new Font("Ariel",Font.BOLD,22));
add(progress);
answer=new JLabel("",JLabel.CENTER);
answer.setFont(new Font("Ariel",Font.BOLD,22));
add(answer);

}
public void respond(String s)
{
int rnd;
rnd=(int)(5*Math.random());
if(rnd==0)
{
progress.setForeground(Color.red);
}
else if(rnd==1)
{
progress.setForeground(Color.yellow);
}
else if(rnd==2)
{
progress.setForeground(Color.blue);
}
else if(rnd==3)
{
progress.setForeground(Color.magenta);
}
else if(rnd==4)
{
progress.setForeground(Color.black);
}
strchances.setText(temp.chances+" chances remaining.");
progress.setText(s);
if(temp.chances==0)
{
answer.setText("The word was "+temp.word);
}
}
}

class DisplayBlanks extends JPanel 
{
JLabel disp[];
GamePlay temp;
public DisplayBlanks(GamePlay gp)
{
temp=gp;
setLayout(new GridLayout(1,gp.word.length()));
setBackground(Color.orange);
setForeground(Color.black);
setFont(new Font("Comic Sans MS",Font.BOLD,24));
disp=new JLabel[gp.word.length()];
String ss;
for(int i=0;i<gp.word.length();i++)
{
disp[i]=new JLabel("");
disp[i].setFont(new Font("Ariel",Font.BOLD+Font.ITALIC,22));
add(disp[i]);
}
showBlanks();
}
public void showBlanks()
{
String ss;
String wd;
wd=temp.word;
for(int i=0;i<wd.length();i++)
{
if(temp.status[i]==1)
{
ss=""+wd.charAt(i);
}
else
{
ss="__";
}
disp[i].setText(ss);
}
}
}
public class GamePlay extends JFrame
{
Gallows g;
MyButtons buttons;
DisplayBlanks db;
Response r;
String word,hint;
int len,chances,status[];
char ch1;
boolean gameOver;
static boolean userWins;
String playerName;
public GamePlay(String str,String pl)
{
int pos;
playerName=pl;
setTitle("Hangman currently being played by "+playerName);  
pos=str.indexOf(",");
word=str.substring(0,pos);
hint=str.substring(pos+1);
len=word.length();
status=new int[len];
chances=7;
setLayout(new GridLayout(2,2));
//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
db=new DisplayBlanks(this);
add(db);
r=new Response(this);
add (r);
buttons=new MyButtons(this);
add(buttons);
g=new Gallows(this);
add(g);
pack();
setVisible(true);
gameOver=false;
}
public boolean checkLetter()
{
int i;
boolean found;
found=false;
for(i=0;i<len;i++)
{
if(status[i]==1)
{
continue;
} 
if(word.charAt(i)==ch1)
{
status[i]=1;
found=true;
}
}
return found;
}
public boolean success()
{
int i;
for(i=0;i<len;i++)
{
if(status[i]==0)
{
return false;
}
}
return true;
}
public void control()
{
boolean b1;
String st;
b1=checkLetter();
if(b1==false)
{
chances--;
st=" Wrong Letter !";
if(chances==0)
{
st="Oops "+playerName+"! You've been Hanged";
g.what_to_draw();
r.respond(st);
gameOver=true;
buttons.disableButtons();    
userWins=false;
//continueOrNot();
return;
}
}
else
{
st="Letter Found !";
if(success())
{
st="Bingo "+playerName+"! You've got it !";
gameOver=true;
buttons.disableButtons();    
userWins=true;
r.respond(st);
db.showBlanks();
//continueOrNot();
return;
}
}
g.what_to_draw();
r.respond(st);
db.showBlanks();
}
}   