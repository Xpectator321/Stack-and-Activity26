import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

class VisualStack {
    ArrayList<String> items = new ArrayList<>();
    final int MAX = 8;

    boolean push(String x){
        if(items.size() == MAX) return false;
        items.add(x);
        return true;
    }
    String pop(){ return items.isEmpty()? null : items.remove(items.size()-1); }
    String peek(){ return items.isEmpty()? null : items.get(items.size()-1); }
    boolean isEmpty(){ return items.isEmpty(); }
    int size(){ return items.size(); }
}

public class StackGUI extends JFrame {
    VisualStack stack = new VisualStack();
    DrawPanel panel = new DrawPanel(stack);

    public StackGUI(){
        setTitle("Stack Visualizer");
        setSize(400,450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel btn = new JPanel(new GridLayout(1,5));
        btn.setBackground(Color.BLACK); 

        JButton push=new JButton("Push"),
                pop=new JButton("Pop"),
                peek=new JButton("Peek"),
                size=new JButton("Size"),
                empty=new JButton("Empty");

        Color bg = Color.DARK_GRAY;
        Color fg = Color.cyan;

        push.setBackground(bg); push.setForeground(fg);
        pop.setBackground(bg); pop.setForeground(fg);
        peek.setBackground(bg); peek.setForeground(fg);
        size.setBackground(bg); size.setForeground(fg);
        empty.setBackground(bg); empty.setForeground(fg);

        btn.add(push); btn.add(pop); btn.add(peek); btn.add(size); btn.add(empty);
        add(btn, BorderLayout.NORTH);

        panel.setBackground(Color.BLACK);
        add(panel, BorderLayout.CENTER);

        push.addActionListener(e -> {
            String v = JOptionPane.showInputDialog("Enter value:");
            if(v!=null && !v.isEmpty()){
                if(!stack.push(v)) JOptionPane.showMessageDialog(this,"Stack is Full!");
                panel.repaint();
            }
        });

        pop.addActionListener(e -> {
            String r = stack.pop();
            JOptionPane.showMessageDialog(this, r==null? "Stack Empty!" : "Popped: "+r);
            panel.repaint();
        });

        peek.addActionListener(e -> JOptionPane.showMessageDialog(this,
                stack.peek()==null? "Empty" : "Top: "+stack.peek()));

        size.addActionListener(e -> JOptionPane.showMessageDialog(this,"Size: "+stack.size()));
        empty.addActionListener(e -> JOptionPane.showMessageDialog(this,
                stack.isEmpty()? "Stack Empty" : "Not Empty"));
    }

    public static void main(String[] args){ new StackGUI().setVisible(true); }
}

class DrawPanel extends JPanel{
    VisualStack stack;
    DrawPanel(VisualStack s){ this.stack=s; }

    protected void paintComponent(Graphics g){
        super.paintComponent(g);

        int w=120, h=35, x=getWidth()/2-w/2, y=getHeight()-60;

        for(int i = stack.items.size()-1; i>=0; i--){
            g.setColor(Color.darkGray);
            g.fillRect(x,y,w,h);
            g.setColor(Color.cyan);
            g.drawString(stack.items.get(i), x+45, y+20);
            y -= h + 8;
        }
    }
}
