package com.ufop.sas.rsa;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Random;

/**
 * Algoritmo de encriptacao RSA
 * 
 * @author Lucas
 * @author Philipe
 * @author Maycon
 * @author Guilherme
 *
 */
public class Rsa {

	public int tamanhoChave;
	public BigInteger n;
	public BigInteger q;
	public BigInteger p;
	public BigInteger totient;
	public BigInteger e;
	public BigInteger d;
	public LocalDateTime iniDataEncriptacao;
	public LocalDateTime fimDataEncriptacao;
	public LocalDateTime iniDataDecriptacao;
	public LocalDateTime fimDataDecriptacao;

	public Rsa (int tamanhoChave) {
		this.tamanhoChave = tamanhoChave;
		this.generarPrimos();
		this.gerarChaves();
	}


	public Rsa (int tamanhoChave, int p, int q) {
		this.tamanhoChave = tamanhoChave;
		this.e = new BigInteger("1");
		this.p = BigInteger.valueOf(p);
		this.q = BigInteger.valueOf(q);

		this.gerarChaves_v1();
	}


	/**
	 * Gera os primos considerando que pq sao divisiveis entre si
	 * 
	 * @return void
	 */
	public void generarPrimos() {
		this.p = new BigInteger(tamanhoChave, 10, new Random());
		do this.q = new BigInteger(tamanhoChave, 10, new Random());
		while(this.q.compareTo(this.p)==0);
	}

	/**
	 * Gera as chaves para encriptaar e decriptar os dados
	 * 
	 * @return void
	 */
	public void gerarChaves() {
		this.n = this.p.multiply(q);

		// Calculando a funcao toltient
		this.totient = this.p.subtract(BigInteger.valueOf(1));
		this.totient = this.totient.multiply(this.q.subtract(BigInteger.valueOf(1)));

		// Selecionando um numero primo x menos que n para gerar a chave publica
		do this.e = new BigInteger(2 * tamanhoChave, new Random());
		while((this.e.compareTo(this.totient) != -1) ||
				(this.e.gcd(this.totient).compareTo(BigInteger.valueOf(1)) != 0));

		//Calculando a chave privada
		this.d = this.e.modInverse(this.totient);
	}

	public void gerarChaves_v1() {
		this.n = this.p.multiply(q);

		// Calculando a funcao toltient
		this.totient = this.p.subtract(BigInteger.valueOf(1));
		this.totient = this.totient.multiply(this.q.subtract(BigInteger.valueOf(1)));

		do this.e = this.e.add(new BigInteger("2"));
		while((this.e.compareTo(this.totient) != -1) ||
				(this.e.gcd(this.totient).compareTo(BigInteger.valueOf(1)) != 0));

		//Calculando a chave privada
		this.d = this.e.modInverse(this.totient);
	}

	/**
	 * Encripta o texto utilizando a chave publica
	 *
	 * @param mensaje
	 * @return BigInteger
	 */
	public BigInteger[] encripta(String mensagem) {

		this.setIniDataEncriptacao(LocalDateTime.now());

		int i;

		//Gerando a matriz de bytes
		byte[] matrizTemporaria = new byte[1];

		//Codificando a cadeia em uma sequencia de bytes
		byte[] bytes = mensagem.getBytes();
		BigInteger[] bigDigitos = new BigInteger[bytes.length];

		for(i=0; i<bigDigitos.length;i++){
			matrizTemporaria[0] = bytes[i];
			bigDigitos[i] = new BigInteger(matrizTemporaria);
		}

		BigInteger[] encriptado = new BigInteger[bigDigitos.length];

		for(i=0; i < bigDigitos.length; i++) encriptado[i] = bigDigitos[i].modPow(e,n);

		this.setFimDataEncriptacao(LocalDateTime.now());
		return encriptado;
	}

	/**
	 * Decripta o texto utilizando a chave privada
	 * 
	 * @param encriptado
	 * @return String
	 */
	public String decripta(BigInteger[] mensagemEncriptada) {

		this.setIniDataDecriptacao(LocalDateTime.now());

		BigInteger[] mensagemDesencriptada = new BigInteger[mensagemEncriptada.length];

		for(int i=0; i<mensagemDesencriptada.length; i++)
			mensagemDesencriptada[i] = mensagemEncriptada[i].modPow(d,n);

		char[] charArray = new char[mensagemDesencriptada.length];

		for(int i=0; i<charArray.length; i++)
			charArray[i] = (char) (mensagemDesencriptada[i].intValue());

		this.setFimDataDecriptacao(LocalDateTime.now());
		return new String(charArray);
	}

	public int getTamanhoChave() {
		return this.tamanhoChave;
	}

	public BigInteger getN() {
		return this.n;
	}

	public BigInteger getQ() {
		return this.q;
	}

	public BigInteger getP() {
		return this.p;
	}

	public BigInteger getTotient() {
		return this.totient;
	}

	public BigInteger getE() {
		return this.e;
	}

	public BigInteger getD() {
		return this.d;
	}

	public LocalDateTime getIniDataEncriptacao() {
		return iniDataEncriptacao;
	}

	public void setIniDataEncriptacao(LocalDateTime iniDataEncriptacao) {
		this.iniDataEncriptacao = iniDataEncriptacao;
	}

	public LocalDateTime getFimDataEncriptacao() {
		return fimDataEncriptacao;
	}

	public void setFimDataEncriptacao(LocalDateTime fimDataEncriptacao) {
		this.fimDataEncriptacao = fimDataEncriptacao;
	}

	public LocalDateTime getIniDataDecriptacao() {
		return iniDataDecriptacao;
	}

	public void setIniDataDecriptacao(LocalDateTime iniDataDecriptacao) {
		this.iniDataDecriptacao = iniDataDecriptacao;
	}

	public LocalDateTime getFimDataDecriptacao() {
		return fimDataDecriptacao;
	}

	public void setFimDataDecriptacao(LocalDateTime fimDataDecriptacao) {
		this.fimDataDecriptacao = fimDataDecriptacao;
	}


}
