
package determinante.gauss.y.rango;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class Ventana extends JFrame implements ActionListener {
    
    JFrame v;
    JPanel p1,p2,p3;
    JLabel lTexto,ltitulo,ldeterminante,lrango,lcontador,lformula;
    JButton bDibujar,bCalcularDeterminante, bCalcularRango, batras,batras1;
    JTextField tftamaño;
    ArrayList<JTextField> matriz;
    ArrayList<JLabel> matrixresul;
    Calculo c;
    double max[][];
    
    Ventana(){
        initcompents();
        AgregarComponentes();
    }

    private void AgregarComponentes() {
        v = new JFrame();
        v.setTitle("Determinate Gauss");
        v.setSize(300, 180);
        v.setLocationRelativeTo(null);
        v.setResizable(false);
        v.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        v.add(p1);
        v.setVisible(true);
    }

    private void initcompents() {
        p1 = new JPanel();
        p2 = new JPanel();
        p3 = new JPanel();
        p1.setLayout(null);
        p2.setLayout(null);
        p3.setLayout(null);
        c = new Calculo();
        lTexto = new JLabel("Ingrese el tamaño de la matriz:");
        ltitulo = new JLabel("Determinate y Rango");
        tftamaño =  new JTextField();
        matriz = new ArrayList();
        matrixresul = new ArrayList();
        bDibujar = new JButton("Dibujar Matriz");
        bDibujar.addActionListener(this);
        bCalcularDeterminante= new JButton("Calcular Determinante");
        bCalcularRango= new JButton("Calcular Rango");
        bCalcularDeterminante.addActionListener(this);
        bCalcularRango.addActionListener(this);
        batras= new JButton("Volver");
        batras1= new JButton("Volver");
        batras.addActionListener(this);
        batras1.addActionListener(this);
        ltitulo.setBounds(20, 10, 280, 40);
        ltitulo.setFont(new Font("Times New Roman", 3, 30));
        lTexto.setBounds(20, 60, 180, 20);
        tftamaño.setBounds(200, 60, 20, 20);
        bDibujar.setBounds(20, 90, 120, 20);
        p1.add(ltitulo);
        p1.add(lTexto);
        p1.add(tftamaño);
        p1.add(bDibujar);
    }
    
    public void CambioPaneles(JPanel viejo, JPanel nuevo,int x, int y){
        v.setVisible(false);
        v.remove(viejo);
        v.add(nuevo);
        v.setSize(x, y);
        v.setLocationRelativeTo(null);
        v.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        int tamaño = Integer.parseInt(tftamaño.getText());
        boolean size =true;
        if(tamaño>10){
            size = false;
            JOptionPane.showMessageDialog(null, "La matriz es muy grande por favor ingrese un numero más pequeño", "Warning",2);
            remover();
        }
        if(ae.getSource().equals(bDibujar) &&size){    
            Dibujarmatriz(tamaño);
            CambioPaneles(p1, p2, 50 +(tamaño*55), 120 + ((tamaño+1)*25));
        }
        if(ae.getSource().equals(bCalcularDeterminante)){
            pintarresultado(tamaño,0);
            CambioPaneles(p2, p3, 100 +(tamaño*55), 140 + ((tamaño+2)*25));
            
        }
        if(ae.getSource().equals(bCalcularRango)){
            pintarresultado(tamaño, 1);
            CambioPaneles(p2, p3, 100 +(tamaño*55), 140 + ((tamaño+2)*25));
        }
        if(ae.getSource().equals(batras)){
            remover();
            CambioPaneles(p3, p1, 300, 180);
        }
        if(ae.getSource().equals(batras1)){
            remover();
            CambioPaneles(p2, p1, 300, 180);
        }
    }

    private void Dibujarmatriz(int tamaño) {
        int index =0;
        for(int i=0; i<tamaño;i++){
            for(int j=0;j<tamaño;j++){
                matriz.add(new JTextField());
                matriz.get(index).setBounds(20 +(j*55),20+(i*25), 50, 20);
                matriz.get(index).setText("");
                p2.add(matriz.get(index));
                index++;
            }
        }
        
        bCalcularDeterminante.setBounds(20, 50 + ((tamaño-1)*25), 163, 20);
        bCalcularRango.setBounds(20, 50 + ((tamaño)*25), 163, 20);
        batras1.setBounds(20, 50 + ((tamaño+1)*25), 163, 20);
        p2.add(bCalcularRango);
        p2.add(batras1);
        p2.add(bCalcularDeterminante);
    }

    private void pintarresultado(int tamaño , int opcion) {
        
        if(opcion==1){
            max = c.CalcularDeterminante(tamaño,matriz,true);
        }else{
            max = c.CalcularDeterminante(tamaño,matriz,false);
        }
        
        int index =0;
        for(int i = 0;i<tamaño;i++){
            for(int j = 0; j<tamaño;j++){
                matrixresul.add(new JLabel(Double.toString(max[i][j])));
                matrixresul.get(index).setBounds(20 +(j*60),20+(i*25), 50, 20);
                p3.add(matrixresul.get(index));
                index++;
            }
        }
        
        
        batras.setBounds(20, 40 + ((tamaño+2)*25), 100, 20);
        lcontador = new JLabel("OE por contador: " + c.contador());
        lcontador.setBounds(20, 40 + (tamaño)*25, 150, 20);
        lformula = new JLabel("OE por formula: " +c.formula(tamaño));
        lformula.setBounds(20, 40 + (tamaño+1)*25, 150, 20);
        if(opcion==1){
            lrango = new JLabel("El rango es: " + c.rangue);
            lrango.setBounds(20, 40 + (tamaño-1)*25, 150, 20);
            p3.add(lrango);
        }else{
            ldeterminante = new JLabel(c.getDeterminante());
            ldeterminante.setBounds(20, 50 + (tamaño-1)*25, 230, 20);
            p3.add(ldeterminante);
        }
        p3.add(lcontador);
        p3.add(batras);
        p3.add(lformula);
        
       
    }

    private void remover() {
        p1.removeAll();
        p2.removeAll();
        p3.removeAll();
        matrixresul.clear();
        tftamaño.setText("");
        p1.add(ltitulo);
        p1.add(lTexto);
        p1.add(tftamaño);
        p1.add(bDibujar);
    }
  
}
