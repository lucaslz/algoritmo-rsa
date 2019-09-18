package com.ufop.sas.rsa;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

/**
 * Classe que organiza e executa o algoritmo RSA
 * 
 * @author Lucas
 * @author Philipe
 * @author Maycon
 * @author Guilherme
 *
 */
public class Main {
	
	/**
	 * Tambem e possivel rodar na linha de comando para isso os argumentos seram tratados
	 * 
	 * @param args
	 * @throws IOException
	 */
	 public static void main(String[] args) throws IOException {
		 
		 if(args.length != 1) {
			 System.out.println("\n =============== Algoritmo RSA: A chave padrão é de 512 bytes ===============\n");
			 args = new String[1];
			 args[0]="1024";
		 }
		 
		 int tamanhoChave = Integer.parseInt(args[0]);
		 
		 Rsa rsa = new Rsa(tamanhoChave);
		 
		 System.out.println("Primos Aleatórios:");
		 System.out.println("P: [" + rsa.getP().toString(16).toUpperCase() + "]");
		 System.out.println("Q: [" + rsa.getQ().toString(16).toUpperCase() + "]\n");
		 System.out.println("Chave Pública (N,E)");
		 System.out.println("N: [" + rsa.getN().toString(16).toUpperCase() + "]");
		 System.out.println("E: [" + rsa.getE().toString(16).toUpperCase() + "]\n");
		 System.out.println("Chave Privada (N,D)");
		 System.out.println("N: [" + rsa.getN().toString(16).toUpperCase() + "]");
		 System.out.println("D: [" + rsa.getD().toString(16).toUpperCase() + "]\n");
		 
		 System.out.println("Mensagem: ");
		 String mensagem = ( new BufferedReader(new InputStreamReader(System.in))).readLine();
	
		 
		 BigInteger[] mensagemEncriptada = rsa.encripta(mensagem);

		 System.out.println("\nMensagem Encriptada: [");
		 
		 for(int i=0; i<mensagemEncriptada.length; i++) {
			 System.out.print(mensagemEncriptada[i].toString(16).toUpperCase());
			 if(i != mensagemEncriptada.length-1) System.out.println("");
		 }
		 
		 System.out.println("]\n");
		 String mensagemDecriptada = rsa.decripta(mensagemEncriptada);
		 System.out.println("\n\nMensagem Decriptada: ["+ mensagemDecriptada +"]");
	 }

}
