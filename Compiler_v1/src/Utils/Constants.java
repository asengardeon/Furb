package Utils;

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
	COMPARAR(9, "simbolo especial"), //
	DIFERENTE(10, "simbolo especial"), //
	MENOR_IGUAL(11, "simbolo especial"), //
	MAIOR_IGUAL(12, "simbolo especial"), //
	MENOR(13, "simbolo especial"), //
	MAIOR(14, "simbolo especial"), //
	IGUAL(15, "simbolo especial"), //
	FECHA_PARENTESE(16, "simbolo especial"), //
	ABRE_PARENTESE(17, "simbolo especial"), //
	PONTO_VIRGULA(18, "simbolo especial"), //
    ABRE_COLCHETES(19, "simbolo especial"),//
    FECHA_COLCHETES(20, "simbolo especial"),//
    VIRGULA(21, "simbolo especial"),//
	DOIS_PONTOS(22, "simbolo especial"), //
	ADD(23, "palavra reservada"), //
	AND(24, "palavra reservada"), //
	BOOLEAN(25, "palavra reservada"), //
	CASE(26, "palavra reservada"), //
	CHARACTER(27, "palavra reservada"), //
	CONTINUE(28, "palavra reservada"), //
	COUNT(29, "palavra reservada"), //
	DELETE(30, "palavra reservada"), //
	DO(31, "palavra reservada"), //
	ELEMENTOF(32, "palavra reservada"), //
	ELSE(33, "palavra reservada"), //
	END(34, "palavra reservada"), //
	ENDIF(35, "palavra reservada"), //
	EXIT(36, "palavra reservada"), //
	FALSE(37, "palavra reservada"), //
	IF(38, "palavra reservada"), //
	LIST(39, "palavra reservada"), //
	NOT(40, "palavra reservada"), //
	NUMBER(41, "palavra reservada"), //
	OR(42, "palavra reservada"), //
	PROGRAM(43, "palavra reservada"), //
	READ(44, "palavra reservada"), //
	SIZE(45, "palavra reservada"), //
	TRUE(46, "palavra reservada"), //
	WRITE(47, "palavra reservada");

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
