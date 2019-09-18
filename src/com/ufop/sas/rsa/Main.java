package com.ufop.sas.rsa;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.time.LocalDateTime;


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


		LocalDateTime iCrip, fCrip, iDesCrip, fDesCrip;



		if(args.length != 1) {
			System.out.println("\n =============== Algoritmo RSA: A chave padrão é de 512 bytes ===============\n");
			args = new String[1];
			args[0]="1024";
		}

		int tamanhoChave = Integer.parseInt(args[0]);

		//		 Rsa rsa = new Rsa(tamanhoChave);
		Rsa rsa = new Rsa(tamanhoChave, 241, 13);

		System.out.println("Primos Aleatórios:");
		System.out.println("P: [" + rsa.getP().toString()+ "]");
		System.out.println("Q: [" + rsa.getQ().toString() + "]\n");
		System.out.println("Chave Pública (N,E)");
		System.out.println("N: [" + rsa.getN().toString()+ "]");
		System.out.println("E: [" + rsa.getE().toString() + "]\n");
		System.out.println("Chave Privada (N,D)");
		System.out.println("N: [" + rsa.getN().toString()+ "]");
		System.out.println("D: [" + rsa.getD().toString()+ "]\n");

		System.out.println("Mensagem: ");
		String mensagem = ( new BufferedReader(new InputStreamReader(System.in))).readLine();

		iCrip = LocalDateTime.now();
		BigInteger[] mensagemEncriptada = rsa.encripta(mensagem);
		fCrip = LocalDateTime.now();


		System.out.println("Tempo de encriptação: "+ fCrip.minusNanos(iCrip.getNano()).getNano());


		System.out.println("\nMensagem Encriptada: [");

		for(int i=0; i<mensagemEncriptada.length; i++) {
			System.out.print(mensagemEncriptada[i].toString());
			if(i != mensagemEncriptada.length-1) System.out.println("");
		}

		System.out.println("]\n");

		iDesCrip = LocalDateTime.now();
		String mensagemDecriptada = rsa.decripta(mensagemEncriptada);
		fDesCrip = LocalDateTime.now();

		System.out.println("Tempo de decriptação: "+ fDesCrip.minusNanos(iDesCrip.getNano()).getNano());
		System.out.println("\n\nMensagem Decriptada: ["+ mensagemDecriptada +"]");
	}

}
