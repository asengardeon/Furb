package Lexical;

public enum Constants {

	EPSILON(0, "identificador"), //
	DOLLAR(1, "identificador"), //
	IDENTIFICADOR(2, "identificador"), //
	REAL(3, "constante real"), //
	LITERAL(4, "constante literal"), //
	MAIS(5, "simbolo especial"), //
	MENOS(6, "simbolo especial"), //
	MULT(7, "simbolo especial"), //
	BARRA(8, "simbolo especial"), //
	CIRCUNFLEXO(9, "simbolo especial"), //
	COMPARAR(10, "simbolo especial"), //
	DIFERENTE(11, "simbolo especial"), //
	MENOR_IGUAL(12, "simbolo especial"), //
	MAIOR_IGUAL(13, "simbolo especial"), //
	MENOR(14, "simbolo especial"), //
	MAIOR(15, "simbolo especial"), //
	IGUAL(16, "simbolo especial"), //
	FECHA_PARENTESE(17, "simbolo especial"), //
	ABRE_PARENTESE(18, "simbolo especial"), //
	PONTO_VIRGULA(19, "simbolo especial"), //
	PONTO(20, "simbolo especial"), //
	DOIS_PONTOS(21, "simbolo especial"), //
	ADD(22, "palavra reservada"), //
	AND(23, "palavra reservada"), //
	BOOLEAN(24, "palavra reservada"), //
	CASE(25, "palavra reservada"), //
	CHARACTER(26, "palavra reservada"), //
	CONTINUE(27, "palavra reservada"), //
	COUNT(28, "palavra reservada"), //
	DELETE(29, "palavra reservada"), //
	DO(30, "palavra reservada"), //
	ELEMENTOF(31, "palavra reservada"), //
	ELSE(32, "palavra reservada"), //
	END(33, "palavra reservada"), //
	ENDIF(34, "palavra reservada"), //
	EXIT(35, "palavra reservada"), //
	FALSE(36, "palavra reservada"), //
	IF(37, "palavra reservada"), //
	LIST(38, "palavra reservada"), //
	NOT(39, "palavra reservada"), //
	NUMBER(40, "palavra reservada"), //
	OR(41, "palavra reservada"), //
	PROGRAM(42, "palavra reservada"), //
	READ(43, "palavra reservada"), //
	SIZE(44, "palavra reservada"), //
	TRUE(45, "palavra reservada"), //
	WRITE(46, "palavra reservada");

	private int id;
	private String clazz;

	private Constants(int id, String clazz) {
		this.id = id;
		this.clazz = clazz;
	}
	
	public int getId(){
		return id;
	}
	
	public String getClazz(){
		return clazz;
	}
}
