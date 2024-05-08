package view;

import java.util.ArrayList;
import java.util.Scanner;
import helper.Utils;
import java.util.Map;
import java.util.HashMap;
import java.util.InputMismatchException;

import model.Producto;

public class Mercado {

	private static Scanner teclado = new Scanner(System.in);
	private static ArrayList<Producto> productos;
	private static Map<Producto, Integer> carrinho;

	public static void main(String[] args) {
		productos = new ArrayList<Producto>();
		carrinho = new HashMap<Producto, Integer>();
		Mercado.menu();
	}

	private static void menu() {
		System.out.println("======================================================");
		System.out.println("===================Bem vindo==========================");
		System.out.println("====================MyShop============================");
		System.out.println("======================================================");
		
		System.out.println("selecione una opcao abaixo ");
		System.out.println("1 - Cadastrar producto");
		System.out.println("2 - Listar producto");
		System.out.println("3 - Comprar producto");
		System.out.println("4 - Visualizar carrinho");
		System.out.println("5 - sair do sistema");

		int opcao = 0;
		
		try {
			opcao = Integer.parseInt(Mercado.teclado.nextLine());

		} catch (InputMismatchException e) {
			Mercado.menu();
		} catch (NumberFormatException f) {
			Mercado.menu();
			}
		
		switch (opcao) {
		case 1:
			Mercado.cadastrarProducto();
			break;
		case 2:
			Mercado.listarProductos();
			break;
		case 3:
			Mercado.comprarProducto();
			break;
		case 4:
			Mercado.visualizarCarrinho();
			break;
		case 5:
			System.out.println("Volte sempre!");
			Utils.pausar(2);
			System.exit(0);// aqui saimos do programa. nao so do break
		default:
			System.out.println("opcao invalida. ");
			Utils.pausar(2);
			Mercado.menu();
			break;

		}

	}

	private static void cadastrarProducto() {
		System.out.println("Cadrastado de Producto: ");
		System.out.println("=============================");

		System.out.println("Informe o nome do producto: ");
		String nome = Mercado.teclado.nextLine();

		System.out.println("Informe o preco do producto: ");
		Double preco = Mercado.teclado.nextDouble();
		
		Producto producto = new Producto(nome, preco);
		
		Mercado.productos.add(producto);
		
		System.out.println("o producto " + producto.getNome() + " foi cadrastado con sucesso. ");
		Utils.pausar(2);
		Mercado.menu();

	}

	private static void listarProductos() {
		if (Mercado.productos.size() > 0) {// verifico si exite producto cadrastrado
			System.out.println("Listando Productos");
			System.out.println();

			for (Producto p : Mercado.productos) {
				System.out.println(p);
				System.out.println();

			}
		} else {
			System.out.println("Ainda nao exitem productos cadrastrados");

		}
		Utils.pausar(2);
		Mercado.menu();

	}

	private static void comprarProducto() {
		if(Mercado.productos.size() > 0) {
			System.out.println("Informe o codigo do producto que seja comprar: ");
			System.out.println();
			
			System.out.println("=========Porductos Disponiveis===============");
			for(Producto p: Mercado.productos) {
				System.out.println(p);
				System.out.println("-----------------------------------------");	
			}
			
			int codigo = Integer.parseInt(Mercado.teclado.nextLine());
			boolean tem = false;
			
			for(Producto p: Mercado.productos) {
				if(p.getCodigo() == codigo) {
					int quant = 0;
					try {
						quant = Mercado.carrinho.get(p);
						// já tem esse producto no carrinho actualiza a quantidade
						
						Mercado.carrinho.put(p, quant + 1);
					}catch(NullPointerException e ) {
						//Primero producto no carrinho
						
						Mercado.carrinho.put(p,  1);
					}
					
					System.out.println(" O Producto " + p.getNome() + " foi adicionado ao carrinho.");
					tem = true;
				}
			}
					if(tem) {
						System.out.println("Deseja adicionar outros productos ao carrinho? ");
						System.out.println(" Digite 1 para sim e 0 para nao ? ");
						int op = Integer.parseInt(Mercado.teclado.nextLine());
						
						if(op == 1) {
							Mercado.comprarProducto();
						}else {
							System.out.println(" Por favor aguarde encuanto fechamos seu pedido");
							Utils.pausar(2);
							Mercado.fecharPedido();
						}
							
					}else {
						System.out.println("Nao foi encontrado o producto com o codigo " + codigo);
						Utils.pausar(2);
						Mercado.menu();	
						}
				
		            }else {
                     System.out.println("Ainda nao existe producto cadastrado no mercado. ");
                     Utils.pausar(2);
                     Mercado.menu();
					}
		}
		
	private static void visualizarCarrinho() {

		if (Mercado.carrinho.size() > 0) {
			System.out.println(" Productos no carrinho: ");
			for (Producto p : Mercado.carrinho.keySet()) {
				System.out.println("Producto: " + p + "\nQuantidade: " + Mercado.carrinho.get(p));
			}

		} else {
			System.out.println("Ainda nao exitem productos no carrinho");
		}
		Utils.pausar(2);
		Mercado.menu();

	}

	private static void fecharPedido() {
		Double valorTotal = 0.0;
		System.out.println("Productos do carrinho");
		System.out.println("--------------------------------------");
		for (Producto p : Mercado.carrinho.keySet()) {
			int quant = Mercado.carrinho.get(p);
			valorTotal += p.getPreco() * quant;
			System.out.println(p);
			System.out.println("Quantidade: " + quant);
			System.out.println("----------------------------------------");

		}
		System.out.println(" Sua fatura é " + Utils.doubleParaString(valorTotal));
		Mercado.carrinho.clear();
		System.out.println(" Obrigado pela preferencia. ");
		Utils.pausar(5);
		Mercado.menu();
	}
}
