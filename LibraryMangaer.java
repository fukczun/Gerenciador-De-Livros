package projetoMikas;
import java.util.Scanner;

public class LibraryMangaer {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int opcao = 0, livros_registrados = 0;
		Livro[] colecao = new Livro[10]; // vetor e registro
		String[][] fim = {{"Programa", " encerrado👍"}, {"Volte", " sempre!"}}; // matriz
		
		espaco();
		while (opcao != 7) {
			opcao = menu_inicial(livros_registrados);
			
			espaco();
			switch (opcao) {
				case 1:
					mostrar_livros(livros_registrados, colecao);
					break;
				
				case 2:
					livros_registrados += cadastrar_livro(livros_registrados, colecao); // controle do tamanho da coleção
					System.out.println("\n(Aperte ENTER para prosseguir)"); // não duplicar instruções, devido o retorno da função
					scan.nextLine();
					break;
					
				case 3:
					escrever_resenha(livros_registrados, colecao);
					break;
					
				case 4:
					ler_resenhas(livros_registrados, colecao);
					break;
				
				case 5:
					int busca = buscar_livro(livros_registrados, colecao); // multifuncional
					
					espaco();
					if (busca != -1) {
						System.out.println("-----------------------------------------");
						imprima_livro(colecao[busca]);
					}
					else {
						System.out.println("Livro não encontrado!");
					}
					System.out.println("\n(Aperte ENTER para prosseguir)");
					scan.nextLine();
					break;
	
				case 6:
					livros_registrados -= remover_livro(livros_registrados, colecao); // mesmo caso do 2
					System.out.println("\n(Aperte ENTER para prosseguir)");
					scan.nextLine();
					break;
				
				case 7:
					break;
	
				default:
					System.out.println("Valor inválido!");
					System.out.println("\n(Aperte ENTER para prosseguir)");
					scan.nextLine();
					break;
			}
			espaco();
		}
		for (String frase[]: fim) {
			for (String palavra: frase) {
				System.out.print(palavra);
			}
			System.out.println();
		}
	}
	
	public static void espaco() { // procedimento 1
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
	}
	
	public static int menu_inicial(int livros_registrados) { // função 1
		Scanner scan = new Scanner(System.in);
		System.out.println("\r\n"
				+ "██╗░░░░░██╗██████╗░██████╗░░█████╗░██████╗░██╗░░░██╗  ███╗░░░███╗░█████╗░███╗░░██╗░█████╗░░██████╗░███████╗██████╗░\r\n"
				+ "██║░░░░░██║██╔══██╗██╔══██╗██╔══██╗██╔══██╗╚██╗░██╔╝  ████╗░████║██╔══██╗████╗░██║██╔══██╗██╔════╝░██╔════╝██╔══██╗\r\n"
				+ "██║░░░░░██║██████╦╝██████╔╝███████║██████╔╝░╚████╔╝░  ██╔████╔██║███████║██╔██╗██║███████║██║░░██╗░█████╗░░██████╔╝\r\n"
				+ "██║░░░░░██║██╔══██╗██╔══██╗██╔══██║██╔══██╗░░╚██╔╝░░  ██║╚██╔╝██║██╔══██║██║╚████║██╔══██║██║░░╚██╗██╔══╝░░██╔══██╗\r\n"
				+ "███████╗██║██████╦╝██║░░██║██║░░██║██║░░██║░░░██║░░░  ██║░╚═╝░██║██║░░██║██║░╚███║██║░░██║╚██████╔╝███████╗██║░░██║\r\n"
				+ "╚══════╝╚═╝╚═════╝░╚═╝░░╚═╝╚═╝░░╚═╝╚═╝░░╚═╝░░░╚═╝░░░  ╚═╝░░░░░╚═╝╚═╝░░╚═╝╚═╝░░╚══╝╚═╝░░╚═╝░╚═════╝░╚══════╝╚═╝░░╚═╝");
		System.out.println("\nLivros registrados: " + livros_registrados
				+ "\n\n[1] - Ver livros registrados\n"
				+ "[2] - Cadastrar livro\n"
				+ "[3] - Escrever resenha sobre o livro\n"
				+ "[4] - Ler resenhas\n"
				+ "[5] - Buscar livro\n"
				+ "[6] - Remover livro\n"
				+ "[7] - Sair do aplicativo");
		return scan.nextInt();
	}
	
	public static void imprima_livro(Livro livro) { // procedimento 2
		System.out.println("Nome: " + livro.nome + "    Autor(a): " + livro.autor);
		System.out.print("Ano: " + livro.ano + "   Resenha: ");
		if (livro.fez_resenha) {
			System.out.print("Tem");
		}
		else {
			System.out.print("Não tem");
		}
		System.out.println("\n-----------------------------------------");
	}
	
	public static void mostrar_livros(int livros_registrados, Livro[] colecao) { // procedimento 3
		Scanner scan = new Scanner(System.in);
		if (livros_registrados != 0) {
			System.out.println("\n-----------------------------------------");
			for (Livro livro: colecao) {
				if (livro == null) {
					continue;
				}
				imprima_livro(livro);
			}
		}
		else {
			System.out.print("Você não tem livros cadastrados!\n");
		}
		System.out.println("\n(Aperte ENTER para prosseguir)");
		scan.nextLine();
	}

	public static int cadastrar_livro(int livros_registrados, Livro[] colecao) { // função 2
		Scanner scan = new Scanner(System.in);
		Livro livro = new Livro();
		
		if (livros_registrados < colecao.length) {
			System.out.print("Qual é o nome do livro? ");
			livro.nome = scan.nextLine();
			System.out.print("Qual é o autor(a) do livro? ");
			livro.autor = scan.nextLine();
			System.out.print("Qual é o ano de publicação do livro? ");
			livro.ano = scan.nextInt();
			
			livro.fez_resenha = false;
			
			colecao[livros_registrados] = livro;
			
			espaco();
			
			System.out.println("\nLivro adicionado!");
			return 1;
		}
		else {
			System.out.println("Seu limite de livros estorou!");
			return 0;
		}
	}

	public static void escrever_resenha(int livros_registrados, Livro[] colecao) { // procedimento 4
		Scanner scan = new Scanner(System.in);
		
		int busca = buscar_livro(livros_registrados, colecao);
		if (busca != -1) {
			espaco();
			if (!colecao[busca].fez_resenha) {
				colecao[busca].fez_resenha = true;
				
				System.out.println("Escreva a resenha (Não aperte 'enter' até terminar): ");
				colecao[busca].resenha = scan.nextLine();
				
				espaco();
				System.out.println("\nResenhas adicionada!");
			}
			else {
				System.out.println("Livro já resenhado!");
			}
		}
		else {
			espaco();
			System.out.println("Livro não encontrado!");
		}
		System.out.println("\n(Aperte ENTER para prosseguir)");
		scan.nextLine();
	}

	public static boolean tem_resenhas(int livros_registrados, Livro[] colecao) { // função 3
		for (int i=0; i<livros_registrados; i++) {
			if (colecao[i].fez_resenha) {
				return true;
			}
		}
		return false;
	}
	
	public static void ler_resenhas(int livros_registrados, Livro[] colecao) { // procedimento 5
		Scanner scan = new Scanner(System.in);
		
		if (tem_resenhas(livros_registrados, colecao)) {
			System.out.println("-----------------------------------------");
			for (int i=0; i<livros_registrados; i++) {
				if (colecao[i].fez_resenha) {
					System.out.println("Livro: " + colecao[i].nome + "\n");
					System.out.println(colecao[i].resenha);
					System.out.println("-----------------------------------------");
				}
			}
		}
		else {
			System.out.println("Você não tem livros resenhados!");
		}
		System.out.println("\n(Aperte ENTER para prosseguir)");
		scan.nextLine();
	}

	public static int buscar_livro(int livros_registrados, Livro[] colecao) { // função 4
		Scanner scan = new Scanner(System.in);
		
		System.out.print("Digite o nome do livro: ");
		String busca = scan.nextLine();
		
		for (int i=0; i<livros_registrados; i++) {
			if (colecao[i].nome.equalsIgnoreCase(busca)) {
				return i;
			}
		}
		return -1;
	}

	public static int remover_livro(int livros_registrados, Livro[] colecao) { // função 5
		Scanner scan = new Scanner(System.in);
		
		int busca = buscar_livro(livros_registrados, colecao);
		if (busca != -1) {
			for (int i=busca; i<livros_registrados-1; i++) {
				colecao[i] = colecao[i+1];
			}
			colecao[livros_registrados-1] = null;
			
			espaco();
			System.out.println("\nLivro deletado!");
			return 1;
		}
		else {
			espaco();
			System.out.println("Livro não encontrado!");
			return 0;
		}
	}
}
	
