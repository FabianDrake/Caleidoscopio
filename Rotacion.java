import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Rotacion extends JFrame {

    private Grafico panel;
    private JLabel jlbGrados, jlbSesgadoX, jlbSesgadoY;
    private JSpinner jspGrados, jspSesgadoX, jspSesgadoY;
    private JButton jbnSesgado;

    int x = 350;
    int y = 250;
    int ancho = 100;
    int alto = 100;
    int giro = 0;
    boolean sesgado = false;
    double sesgadoX = 0.0;
    double sesgadoY = 0.0;

    public Rotacion() {

        setTitle("Rotacion y sesgado");
        setLayout(getLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setBounds(0, 0, 800, 600);

        panel = new Grafico();
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));
        panel.setLayout(null);

        this.jlbGrados = new JLabel("Grados:");
        this.jlbGrados.setBounds(20, 10, 100, 20);

        this.jspGrados = new JSpinner();
        this.jspGrados.setBounds(70, 10, 60, 20);

        this.jbnSesgado = new JButton("Sesgado");
        this.jbnSesgado.setBounds(150, 10, 100, 20);

        this.jlbSesgadoX = new JLabel("X:");
        this.jlbSesgadoX.setBounds(260, 10, 50, 20);
        this.jlbSesgadoX.setVisible(false);

        this.jlbSesgadoY = new JLabel("Y:");
        this.jlbSesgadoY.setBounds(260, 30, 50, 20);
        this.jlbSesgadoY.setVisible(false);

        this.jspSesgadoX = new JSpinner();
        this.jspSesgadoX.setBounds(280, 10, 60, 20);
        this.jspSesgadoX.setVisible(false);

        this.jspSesgadoY = new JSpinner();
        this.jspSesgadoY.setBounds(280, 30, 60, 20);
        this.jspSesgadoY.setVisible(false);

        jspGrados.setModel(new SpinnerNumberModel(0, 0, 360, 1));

        jspGrados.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                giro = (int) jspGrados.getValue();
                repaint();
            }
        });

        jbnSesgado.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!sesgado) {
                    jlbSesgadoX.setVisible(true);
                    jlbSesgadoY.setVisible(true);
                    jspSesgadoX.setVisible(true);
                    jspSesgadoY.setVisible(true);
                    sesgado = true;
                } else {
                    jlbSesgadoX.setVisible(false);
                    jlbSesgadoY.setVisible(false);
                    jspSesgadoX.setVisible(false);
                    jspSesgadoY.setVisible(false);
                    sesgado = false;
                }
            }
        });

        jspSesgadoX.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int valor = (int) jspSesgadoX.getValue();
                sesgadoX = (double) valor / 100;
                repaint();
            }
        });

        jspSesgadoY.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int valor = (int) jspSesgadoY.getValue();
                sesgadoY = (double) valor / 100;
                repaint();
            }
        });

        panel.add(jlbGrados);
        panel.add(jspGrados);
        panel.add(jbnSesgado);
        panel.add(jlbSesgadoX);
        panel.add(jlbSesgadoY);
        panel.add(jspSesgadoX);
        panel.add(jspSesgadoY);
        
        setContentPane(panel);
        setLocationRelativeTo(null);
        setVisible(true);

    }

    public class Grafico extends JPanel {

        @Override
        public void paint(Graphics g) {
            super.paint(g);
            Graphics2D grafico = (Graphics2D) g;
            grafico.translate(x, y);
            grafico.rotate(Math.toRadians(giro));
            if (sesgado) {
                grafico.shear(sesgadoX, sesgadoY);
            }
            grafico.setColor(Color.black);
            grafico.fillRect(0, 0, ancho, alto);
        }

    }

    public static void main(String[] args) {
        Rotacion frame = new Rotacion();
    }
}
