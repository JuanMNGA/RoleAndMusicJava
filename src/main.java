import java.nio.file.Paths;

import org.jsfml.audio.*;
import org.jsfml.graphics.*;
import org.jsfml.window.*;
import org.jsfml.window.event.*;
import org.jsfml.system.*;

public class main {
	public static void main(String[] args) throws Exception{
		RenderWindow Window = new RenderWindow(new VideoMode(600,500,32),"Role & Music");
		//Variables
		boolean Set_Rep=false;
		boolean[] Sel_Lab = new boolean[7];
		//Inicializacion de variables
		for(int i=0;i<7;i++)
			Sel_Lab[i]=false;
		//Creacion de objetos
		Texture[] TX = new Texture[7];
		Sprite BG;
		Sprite[] Play = new Sprite[7];
		Sprite[] Stop = new Sprite[7];
		Sprite[] Label = new Sprite[7];
		Sprite[] Repr = new Sprite[2];
		Sprite[] SelLab = new Sprite[7];
		Text[] Textos = new Text[2];
		Font fuente = new Font();
		SoundBuffer[] PistasBuffer = new SoundBuffer[7];
		Sound[] Pistas = new Sound[7];
		//Inicializacion de objetos
		//Fuente del texto
		fuente.loadFromFile(Paths.get("Fonts/font.ttf"));
		//Inicializacion de textos;
		Textos[0] = new Text("Role&Music v1.0.0",fuente,40);
		Textos[0].setPosition(320,450);
		Textos[0].setColor(Color.RED);
		Textos[1] = new Text(" Cliquee encima del espacio para escritura, cuando se marque el recuadro,\n escriba el nombre de la cancion deseada acabado en .wav o .ogg,\n la cancion debe estar en formato .wav o .ogg para su correcta\n reproduccion. Por ultimo, pulse enter para cargar la cancion.",fuente,20);
		Textos[1].setColor(Color.RED);
		//Inicializacion de texturas y sprites
		for(int i=0;i<7;i++)
			TX[i]= new Texture();
			//Fondo
		TX[0].loadFromFile(Paths.get("Images/Background.bmp"));
		BG = new Sprite(TX[0]);
			//Sprites botones Play
		TX[1].loadFromFile(Paths.get("Images/Play.png"));
		for(int i=0;i<7;i++){
			Play[i] = new Sprite(TX[1]);
			Play[i].setPosition(400,150+(45*i));
		}
			//Sprites botones Stop
		TX[2].loadFromFile(Paths.get("Images/Stop.png"));
		for(int i=0;i<7;i++){
			Stop[i] = new Sprite(TX[2]);
			Stop[i].setPosition(455,150+(45*i));
		}
			//Sprites botones Labels
		TX[3].loadFromFile(Paths.get("Images/Label.png"));
		for(int i=0;i<7;i++){
			Label[i] = new Sprite(TX[3]);
			Label[i].setPosition(60,150+(45*i));
		}
			//Sprites botones reproductor
		TX[4].loadFromFile(Paths.get("Images/ReproductorG.png"));
		Repr[0] = new Sprite(TX[4]);
		Repr[0].setPosition(400,90);
		TX[5].loadFromFile(Paths.get("Images/ReproductorB.png"));
		Repr[1] = new Sprite(TX[5]);
		Repr[1].setPosition(400,90);
			//Sprites seleccion label
		TX[6].loadFromFile(Paths.get("Images/Select.png"));
		for(int i=0;i<7;i++){
			SelLab[i] = new Sprite(TX[6]);
			SelLab[i].setPosition(10,150+(45*i));
		}
		//Inicializacion de pistas
		for(int i=0;i<7;i++)
			PistasBuffer[i] = new SoundBuffer();
			//Carga de archivos de audio (inclusion a pelo, hay que cambiarlo a por entrada de texto)
		PistasBuffer[0].loadFromFile(Paths.get("Samples/1.wav"));
		Pistas[0] = new Sound(PistasBuffer[0]);
		PistasBuffer[1].loadFromFile(Paths.get("Samples/2.wav"));
		Pistas[1] = new Sound(PistasBuffer[1]);
		while(Window.isOpen()){
			Event event;
			event = Window.pollEvent();
			if(event!=null){
				if(event.type == Event.Type.CLOSED){
					Window.close();
					System.gc();
				}
				if(event.type == Event.Type.MOUSE_BUTTON_PRESSED){
					if(Mouse.isButtonPressed(event.asMouseButtonEvent().button)){
						//Click Izquierdo del raton
						if(event.asMouseButtonEvent().button == Mouse.Button.LEFT){
							Vector2f Position = new Vector2f(Mouse.getPosition(Window));
							if(Position.x>=400){
								for(int i=0;i<7;i++){
									if(Play[i].getGlobalBounds().contains(Position.x,Position.y)){
										Pistas[i].play();
									}
									if(Stop[i].getGlobalBounds().contains(Position.x,Position.y)){
										Pistas[i].stop();
									}
								}
							}//Fin if posicion
							if(Repr[0].getGlobalBounds().contains(Position.x,Position.y)){
								if(Set_Rep==false){
									Set_Rep=true;
								}else{
									Set_Rep=false;
								}
							}//Fin if Modo reproductor
							if(Position.x>60 && Position.x<315){
								for(int i=0;i<7;i++){
									if(Label[i].getGlobalBounds().contains(Position.x,Position.y)){
										Sel_Lab[i] = true;
									}else{
										Sel_Lab[i] = false;
									}
								}
							}
						}
						//Click Derecho del raton
						if(event.asMouseButtonEvent().button == Mouse.Button.RIGHT){
							Vector2f Position = new Vector2f(Mouse.getPosition(Window));
							if(Position.x>=400){
								for(int i=0;i<7;i++){
									if(Play[i].getGlobalBounds().contains(Position.x,Position.y)){
										Pistas[i].setLoop(true);
										Pistas[i].play();
									}
								}
							}//Fin if posicion
						}
					}//Fin if botones
				}//Fin if evento de raton
			}
			Window.clear();
			for(int i=0;i<7;i++){
				Window.draw(Play[i]);
				Window.draw(Stop[i]);
				Window.draw(Label[i]);
			}
			if(Set_Rep==true){
				Window.draw(Repr[0]);
			}else{
				Window.draw(Repr[1]);
			}
			for(int i=0;i<7;i++){
				if(Sel_Lab[i]==true)Window.draw(SelLab[i]);
			}
			Window.draw(Textos[0]);
			Window.draw(Textos[1]);
			Window.display();
		}

	}

}
