package interfaz;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;

import modelo.Aula;
import modelo.Equipo;

import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JList;

@SuppressWarnings("serial")
public class VerEquipo extends JInternalFrame {

	JLabel lblPB;
	JLabel lblCPU;
	JLabel lblMON;
	JLabel lblTS;
	JList lstHDD;
	JList lstRAM;
	JList lstTG;
	JList lstTR;
	
	/**
	 * Create the frame.
	 */
	public VerEquipo() {
		setBounds(100, 100, 450, 400);
		getContentPane().setLayout(null);
		
		JLabel lblPlacaBase = new JLabel("Placa Base:");
		lblPlacaBase.setBounds(12, 25, 105, 14);
		getContentPane().add(lblPlacaBase);
		
		lblPB = new JLabel();
		lblPB.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblPB.setBounds(129, 25, 299, 14);
		getContentPane().add(lblPB);
		
		JLabel lblHdd = new JLabel("HDD:");
		lblHdd.setBounds(12, 60, 105, 14);
		getContentPane().add(lblHdd);
		
		JLabel lblCpu = new JLabel("CPU:");
		lblCpu.setBounds(12, 120, 105, 14);
		getContentPane().add(lblCpu);
		
		lblCPU = new JLabel();
		lblCPU.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblCPU.setBounds(129, 120, 299, 14);
		getContentPane().add(lblCPU);
		
		JLabel lblRam = new JLabel("RAM:");
		lblRam.setBounds(12, 155, 105, 14);
		getContentPane().add(lblRam);
		
		JLabel lblMonitor = new JLabel("Monitor:");
		lblMonitor.setBounds(12, 370, 105, 14);
		getContentPane().add(lblMonitor);
		
		lblMON = new JLabel();
		lblMON.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblMON.setBounds(129, 370, 299, 14);
		getContentPane().add(lblMON);
		
		JLabel lblTRed = new JLabel("T. Red:");
		lblTRed.setBounds(12, 310, 105, 14);
		getContentPane().add(lblTRed);
		
		JLabel lblTSonido = new JLabel("T. Sonido:");
		lblTSonido.setBounds(12, 275, 105, 14);
		getContentPane().add(lblTSonido);
		
		lblTS = new JLabel();
		lblTS.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblTS.setBounds(129, 275, 299, 14);
		getContentPane().add(lblTS);
		
		JLabel lblTGrafica = new JLabel("T. Grafica:");
		lblTGrafica.setBounds(12, 215, 105, 14);
		getContentPane().add(lblTGrafica);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(129, 60, 297, 50);
		getContentPane().add(scrollPane);
		
		lstHDD = new JList();
		scrollPane.setViewportView(lstHDD);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(129, 155, 297, 50);
		getContentPane().add(scrollPane_1);
		
		lstRAM = new JList();
		scrollPane_1.setViewportView(lstRAM);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(129, 215, 297, 50);
		getContentPane().add(scrollPane_2);
		
		lstTG = new JList();
		scrollPane_2.setViewportView(lstTG);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(129, 310, 297, 50);
		getContentPane().add(scrollPane_3);
		
		lstTR = new JList();
		scrollPane_3.setViewportView(lstTR);
	}
	public void setEquipo(Equipo e){
		this.setTitle("Aula Nº" + e.getCodAula() + " - PC" + e.getCodEquipo());
		lblPB.setText(e.getPlacaBase().toString());
		lblCPU.setText(e.getCpu().toString());
		lblMON.setText(e.getMonitor().toString());
		lblTS.setText(e.gettAudio().toString());
		lstHDD.setListData(e.getHDDs());
		lstRAM.setListData(e.getRam());
		lstTG.setListData(e.gettGraficas());
		lstTR.setListData(e.gettRed());
	}
}
