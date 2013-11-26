import java.nio.file.Paths;

import org.jsfml.audio.*;
import org.jsfml.graphics.*;
import org.jsfml.window.*;
import org.jsfml.window.event.*;
import org.jsfml.system.*;

public class main {
	public static void main(String[] args) throws Exception{
		RenderWindow Window = new RenderWindow(new VideoMode(600,500,32),"Role & Music");
		//Creacion de objetos
		Texture[] TX = new Texture[4];
		Sprite BG;
		Sprite[] Play = new Sprite[7];
		Sprite[] Stop = new Sprite[7];
		Sprite[] Label = new Sprite[7];
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
		for(int i=0;i<4;i++)
			TX[i]= new Texture();

		TX[0].loadFromFile(Paths.get("Images/Background.bmp"));
		BG = new Sprite(TX[0]);
		TX[1].loadFromFile(Paths.get("Images/Play.png"));
		for(int i=0;i<7;i++){
			Play[i] = new Sprite(TX[1]);
			Play[i].setPosition(400,150+(45*i));
		}
		TX[2].loadFromFile(Paths.get("Images/Stop.png"));
		for(int i=0;i<7;i++){
			Stop[i] = new Sprite(TX[2]);
			Stop[i].setPosition(455,150+(45*i));
		}
		TX[3].loadFromFile(Paths.get("Images/Label.png"));
		for(int i=0;i<7;i++){
			Label[i] = new Sprite(TX[3]);
			Label[i].setPosition(45,150+(45*i));
		}
		//Inicializacion de pistas
		for(int i=0;i<7;i++)
			PistasBuffer[i] = new SoundBuffer();
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
							for(int i=0;i<7;i++){
								if(Play[i].getGlobalBounds().contains(Position.x,Position.y)){
									Pistas[i].play();
								}
								if(Stop[i].getGlobalBounds().contains(Position.x,Position.y)){
									Pistas[i].stop();
								}
							}
						}
						//Click Derecho del raton
						if(event.asMouseButtonEvent().button == Mouse.Button.RIGHT){
							Vector2f Position = new Vector2f(Mouse.getPosition(Window));
							for(int i=0;i<7;i++){
								if(Play[i].getGlobalBounds().contains(Position.x,Position.y)){
									Pistas[i].setLoop(true);
									Pistas[i].play();
								}
							}
						}
					}
				}
			}
			Window.clear();
			for(int i=0;i<7;i++){
				Window.draw(Play[i]);
				Window.draw(Stop[i]);
				Window.draw(Label[i]);
			}
			Window.draw(Textos[0]);
			Window.draw(Textos[1]);
			Window.display();
		}

	}

}
