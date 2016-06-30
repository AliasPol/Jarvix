
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.awt.image.ImageObserver.WIDTH;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;


public class ModulYanga extends JDialog implements ActionListener {
    
    private JLabel lWielkosc;
    private JTextField tWielkosc;
    private JButton bOk, bCancel;
    private boolean okData;
    public double dWielkosc;
    
    public double E,J;
    
    public ModulYanga(JFrame owner){
        
        super(owner, "Dodawanie E", true);
        setLayout(null);
        setBounds(500, 400, 300, 200);
        
        
        // \/ tworzenie Labeli
        lWielkosc = new JLabel("Wartość moduł Younga [GPa]: ", JLabel.RIGHT);
        lWielkosc.setBounds(0, 50, 170, 25);
        add(lWielkosc);        
  
        // /\ tworzenie Labeli
        
        
        // \/ tworzenie TextField
        tWielkosc = new JTextField();
        tWielkosc.setBounds(180, 50, 100, 25);
        add(tWielkosc);
        tWielkosc.setToolTipText("Podaj wielkość modułu Younga");
        
        
        // /\ tworzenie TextField
        
        
        // \/ tworzenie JButton
        bOk = new JButton("OK");
        bOk.setBounds(50, 120, 100, 30);
        add(bOk);
        bOk.addActionListener(this);
        
        bCancel = new JButton("Cancel");
        bCancel.setBounds(160, 120, 100, 30);
        add(bCancel);
        bCancel.addActionListener(this);
        
        
        // /\ tworzenie JButton
        

    }
    public ModulYanga(JFrame owner, ModulYanga E){
        super(owner, "Dodawanie J", true);
        setLayout(null);
        setBounds(500, 400, 400, 200);
        
        
        // \/ tworzenie Labeli
        lWielkosc = new JLabel("Wartość momentu bezwładności J [m^4]: ", JLabel.RIGHT);
        lWielkosc.setBounds(0, 50, 250, 25);
        add(lWielkosc);        
  
        // /\ tworzenie Labeli
        
        
        // \/ tworzenie TextField
        tWielkosc = new JTextField();
        tWielkosc.setBounds(250, 50, 100, 25);
        add(tWielkosc);
        tWielkosc.setToolTipText("Podaj wielkość J[m^4]");
        
        
        // /\ tworzenie TextField
        
        
        // \/ tworzenie JButton
        bOk = new JButton("OK");
        bOk.setBounds(80, 120, 100, 30);
        add(bOk);
        bOk.addActionListener(this);
        
        bCancel = new JButton("Cancel");
        bCancel.setBounds(200, 120, 100, 30);
        add(bCancel);
        bCancel.addActionListener(this);
        
        
        // /\ tworzenie JButton
    }
    public void setFocus(){
        
        tWielkosc.requestFocusInWindow();
    }
    
    public boolean isOk(){
        return okData;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        Object zrodlo = e.getSource();
        
        if(zrodlo == bOk){
            okData = true;

            dWielkosc = Double.parseDouble(tWielkosc.getText());
            setVisible(false);
            
        }
        else if(zrodlo == bCancel){
            okData = false;
            setVisible(false);
        }
    }
    
}
