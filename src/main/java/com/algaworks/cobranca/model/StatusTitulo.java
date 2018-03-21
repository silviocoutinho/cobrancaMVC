package com.algaworks.cobranca.model;

public enum StatusTitulo {
	
	PENDENTE("Pendente", 0),
	RECEBIDO("Recebido", 1),
	CANCELADO("Cancelado", 2);
	
	private String descricao;
	private int posicao;
	
		StatusTitulo(String descricao, int posicao){
			this.descricao = descricao;
			this.posicao = posicao;
		}
		
		public String getDescricao() {
			return descricao;
		}
		
		public int getPosicao() {
			return posicao;
		}

}
