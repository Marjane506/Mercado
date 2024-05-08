package view;
import model.Producto;
public class Teste {
	
	public static void main (String[]args) {
		Producto ps4 = new Producto("Playstation 4", 1789.44);
		Producto xbox = new Producto("xbox 360", 1699.0);
		
		System.out.println(ps4);
		System.out.println(xbox);
		
	}

}
