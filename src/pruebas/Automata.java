package pruebas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Automata extends JPanel{
	
	public static int tam = 100; //display
	public static float[] prob = new float[4]; //proliferation
	public static int[][] mat= new int[tam][tam]; //cells
	public static float Pm = (float) 0.2 ,Pp = (float) 0.25,Pq = 1-Pm-Pp;
	Map<Integer, Color> asignaColor = new HashMap<Integer,Color>(); //states
	Random rand = new Random(System.nanoTime());
	
	public Automata(){
		for (int i = 0; i < tam; i++) 
			for (int j = 0; j < tam; j++)
				mat[i][j]= 0;

		asignaColor.put(0, Color.blue);
		asignaColor.put(1, Color.cyan);
		asignaColor.put(2, Color.yellow);
		asignaColor.put(3, Color.red);
		
		mat[(int)tam/2][(int)tam/2] = 1;
	}//constructor
	
	public void paint(Graphics g) {
		
	    Dimension d = this.getPreferredSize();
	    int w = 500/tam;
	    int h = 500/tam;
	    
	    for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat.length; j++) {
				g.setColor(Color.black);
				g.drawRect(i*w, j*h, w, h);
				g.setColor(asignaColor.get(mat[i][j]));
				g.fillRect(i*w, j*h, w, h);
				}//for interno
		}//for externo para recorrer la matriz
	    
	  }//pintado de la pantalla
	
	public void Prob(int coordX, int coordY){ 
		//bordes de la matriz
		if(coordX==0){
			if(coordY==tam-1){ //controla esquina superior derecha
				prob[0]=0;
				prob[1]=(float)((1-mat[coordX+1][coordY])/ 4-(mat[coordX][coordY-1]+mat[coordX+1][coordY]) );
				prob[2]=(float)((1-mat[coordX][coordY-1])/ 4-(mat[coordX][coordY-1]+mat[coordX+1][coordY]) );
				prob[3]=0;
			}else{ 
				if(coordY==0){ //controla esquina superior izquierda
					prob[0]=0; 
					prob[1]=(float)((1-mat[coordX+1][coordY])/ 4-(mat[coordX+1][coordY]+mat[coordX][coordY+1]) );
					prob[2]=0;
					prob[3]=(float)((1-mat[coordX][coordY+1])/ 4-(mat[coordX+1][coordY]+mat[coordX][coordY+1]) );
				}else{ //controla borde superior
				prob[0]=0; 
				prob[1]=(float)((1-mat[coordX+1][coordY])/ 4-(mat[coordX][coordY-1]+mat[coordX+1][coordY]+mat[coordX][coordY+1]) );
				prob[2]=(float)((1-mat[coordX][coordY-1])/ 4-(mat[coordX][coordY-1]+mat[coordX+1][coordY]+mat[coordX][coordY+1]) );
				prob[3]=(float)((1-mat[coordX][coordY+1])/ 4-(mat[coordX][coordY-1]+mat[coordX+1][coordY]+mat[coordX][coordY+1]) );
				}
			}
		}//if borde superior 
		if(coordX==tam-1){
			if(coordY==0){ //controla esquina inferior izquierda
				prob[0]=(float)((1-mat[coordX-1][coordY])/ 4-(mat[coordX-1][coordY]+mat[coordX][coordY+1]) );
				prob[1]=0;
				prob[2]=0;
				prob[3]=(float)((1-mat[coordX][coordY+1])/ 4-(mat[coordX-1][coordY]+mat[coordX][coordY+1]) );
			}else{ 
				if(coordY==tam-1){ //controla esquina inferior derecha
					prob[0]=(float)((1-mat[coordX-1][coordY])/ 4-(mat[coordX-1][coordY]+mat[coordX][coordY-1]) );
					prob[1]=0;
					prob[2]=(float)((1-mat[coordX][coordY-1])/ 4-(mat[coordX-1][coordY]+mat[coordX][coordY-1]) );
					prob[3]=0;
				}
				else{ //controla borde inferior
				prob[0]=(float)((1-mat[coordX-1][coordY])/ 4-(mat[coordX-1][coordY]+mat[coordX][coordY-1]+mat[coordX][coordY+1]) );
				prob[1]=0;
				prob[2]=(float)((1-mat[coordX][coordY-1])/ 4-(mat[coordX-1][coordY]+mat[coordX][coordY-1]+mat[coordX][coordY+1]) );
				prob[3]=(float)((1-mat[coordX][coordY+1])/ 4-(mat[coordX-1][coordY]+mat[coordX][coordY-1]+mat[coordX][coordY+1]) );
				}
			}
		}//if borde inferior
		if(coordY==0){
			if(coordX==0){ //control esquina superior izquierda
				prob[0]=0;
				prob[1]=(float)((1-mat[coordX+1][coordY])/ 4-(mat[coordX+1][coordY]+mat[coordX][coordY+1]) );
				prob[2]=0;
				prob[3]=(float)((1-mat[coordX][coordY+1])/ 4-(mat[coordX+1][coordY]+mat[coordX][coordY+1]) );
			}
			else{
				if(coordX==tam-1){ //control escuina inferior izquierda
					prob[0]=(float)((1-mat[coordX-1][coordY])/ 4-(mat[coordX][coordY+1]+mat[coordX-1][coordY]) );
					prob[1]=0;
					prob[2]=0;
					prob[3]=(float)((1-mat[coordX][coordY+1])/ 4-(mat[coordX][coordY+1]+mat[coordX-1][coordY]) );
				}
				else{ //control borde izquierdo
					prob[0]=(float)((1-mat[coordX-1][coordY])/ 4-(mat[coordX][coordY+1]+mat[coordX+1][coordY]+mat[coordX-1][coordY]) );
					prob[1]=(float)((1-mat[coordX+1][coordY])/ 4-(mat[coordX][coordY+1]+mat[coordX+1][coordY]+mat[coordX-1][coordY]) );
					prob[2]=0;
					prob[3]=(float)((1-mat[coordX][coordY+1])/ 4-(mat[coordX][coordY+1]+mat[coordX+1][coordY]+mat[coordX-1][coordY]) );
				}
			}
		}//if borde izquierdo
		if(coordY==tam-1){
			if(coordX==tam-1){//control esquina inferior derecha
				prob[0]=(float)((1-mat[coordX-1][coordY])/ 4-(mat[coordX-1][coordY]+mat[coordX][coordY-1]) );
				prob[1]=0;
				prob[2]=(float)((1-mat[coordX][coordY-1])/ 4-(mat[coordX-1][coordY]+mat[coordX][coordY-1]) );
				prob[3]=0;
			}else{
				if(coordX==0){ //control esquina superior derecha
					prob[0]=0;
					prob[1]=(float)((1-mat[coordX+1][coordY])/ 4-(mat[coordX][coordY-1]+mat[coordX+1][coordY]) );
					prob[2]=(float)((1-mat[coordX][coordY-1])/ 4-(mat[coordX][coordY-1]+mat[coordX+1][coordY]) );
					prob[3]=0;
				}else{		//control borde derecho 
					prob[0]=(float)((1-mat[coordX-1][coordY])/ 4-(mat[coordX-1][coordY]+mat[coordX][coordY-1]+mat[coordX+1][coordY]) );
					prob[1]=(float)((1-mat[coordX+1][coordY-1])/ 4-(mat[coordX-1][coordY]+mat[coordX][coordY-1]+mat[coordX+1][coordY]) );
					prob[2]=(float)((1-mat[coordX][coordY-1])/ 4-(mat[coordX-1][coordY]+mat[coordX][coordY-1]+mat[coordX+1][coordY]) );
					prob[3]=0;
				}
			}
		}//if borde derecho
		if(coordX!=0 && coordX!=tam-1 && coordY!=0 && coordY!=tam-1){ //si no es ninguno de los bordes, que calcule la probabilidad normal
			float val = 4-(mat[coordX-1][coordY]+mat[coordX][coordY-1]+mat[coordX+1][coordY]+mat[coordX][coordY+1]);
			prob[0]=(float)((1-mat[coordX-1][coordY])/ val );
			prob[1]=(float)((1-mat[coordX+1][coordY])/ val );
			prob[2]=(float)((1-mat[coordX][coordY-1])/ val );
			prob[3]=(float)((1-mat[coordX][coordY+1])/ val );
		}//else
	}
	
	public void FourthPh(){ //quiescent
		
	}
	
	public void ThirdPh(int CX, int CY){ //migration
		float rrm = rand.nextFloat();
		float posicion = rand.nextFloat();
		boolean permiso = true;
		
		if(rrm<Pm){
				Prob(CX,CY);
			if(prob[0]==0 && prob[1]==0 && prob[2]==0 && prob[3]==0){
				permiso=false;
			}
			if(posicion>=0 && posicion<=prob[0] && permiso){
				mat[CX-1][CY]=1;
				mat[CX][CY]=0;
				permiso = false;
			}//movimiento hacia arriba
			if(posicion>prob[0] && posicion<=(prob[0]+prob[1]) && permiso){
				mat[CX+1][CY]=1;
				mat[CX][CY]=0;
				permiso = false;
			}//movimiento hacia abajo
			if(posicion>(prob[0]+prob[1]) && posicion<=(prob[0]+prob[1]+prob[2]) && permiso){
				mat[CX][CY-1]=1;
				mat[CX][CY]=0;
				permiso = false;
			}//movimiento a la izquierda
			if(posicion>(prob[0]+prob[1]+prob[2]) && posicion<=1 && permiso){
				mat[CX][CY+1]=1;
				mat[CX][CY]=0;
				permiso = false;
			}//movimiento a la derecha
		}
		else{
			FourthPh();
		}//call Quiescent
		
	}//migration method
	
	public void SecondPh(int CX, int CY,float rr){
		float rrp;
		int PH=0, NP=1;
		boolean prol=false;
		
		rrp=rand.nextFloat();
		if (rrp<Pp){ 
			PH++;
			if (PH >= NP) {
				Prob(CX,CY);
				PH=0;
				if(!ProlCel(CX,CY,rr)){
					ThirdPh(CX,CY);
				}
			}
			else{
				ThirdPh(CX,CY);
			}
		}
		else{
			ThirdPh(CX,CY);
		}
	}//proliferation method
	
	public void FirstPh(int CX, int CY){
		float Ps = 1,Pd = 1-Ps, rr;
		rr=rand.nextFloat();
		if(rr<Ps)
			SecondPh(CX, CY, rr);
	}//metodo de inicio que evalua si la celula muere o vive
	
	public boolean ProlCel (int cX, int cY , float rr){ //hacia donde se divide
		
		if(!(prob[0]==0 && prob[1]==0 && prob[2]==0 && prob[3]==0)){
			if(rr>=0 && rr<=prob[0]){
				mat[cX-1][cY]=1;
			}//multiplica hacia ariba
			if(rr>prob[0] && rr<=(prob[0]+prob[1])){
				mat[cX+1][cY]=1;
			}//multiplica hacia abajo
			if(rr>(prob[0]+prob[1]) && rr<=(prob[0]+prob[1]+prob[2])){
				mat[cX][cY-1]=1;
			}//multiplica a la izquierda
			if(rr>(prob[0]+prob[1]+prob[2]) && rr<=1){
				mat[cX][cY+1]=1;
			}//multiplica a la derecha
			return true;
		}//si estan todas ocupadas no puede multiplicarse
		else
			return false;
	}
	
	public void Comportamiento(){
		//valores de ejemplo
		
		for (int i = 1; i < mat.length; i++) {
			for (int j = 1; j < mat.length; j++) {
				if(mat[i][j]!=0){
					FirstPh(i,j);
					}
			}//for interno
		}//for que recorre la matriz
		
	}//m�todo de evoluci�n de las celulas
	
	public void NuevoEstado(){
		for (int i = 0; i < tam; i++) 
			for (int j = 0; j < tam; j++)
				if(mat[i][j]!=0)
					FirstPh(i,j);
				//mat[i][j]= rand.nextInt(4);
	}
	
	public static void main(String [] args){
		JFrame frame = new JFrame("Automat");
		frame.setSize(550,550);
		Automata auto = new Automata();
	    frame.getContentPane().add(auto);

	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    frame.setVisible(true);
	    String input = JOptionPane.showInputDialog("Numero de estados:");
	    
	    for (int i = 0; i < Integer.parseInt(input); i++) {
	    	/*for (int j = 0; j < mat.length; j++) {
				for (int j2 = 0; j2 < mat.length; j2++) {
					System.out.print(mat[j][j2]+" ");
				}
				System.out.println();
			}
	    	System.out.println();*/
	    	auto.NuevoEstado();
	    	frame.getContentPane().validate();
	    	frame.getContentPane().update(frame.getContentPane().getGraphics());
	    }//for que repinta tantas veces como estados quieras
	}
}