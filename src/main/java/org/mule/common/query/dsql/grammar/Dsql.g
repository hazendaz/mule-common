grammar Dsql;

options {
  language = Java;
  output=AST;
  ASTLabelType=CommonTree;
}

@header {
  package org.mule.common.query.dsql.grammar;
}

@lexer::header {
  package org.mule.common.query.dsql.grammar;
}

@rulecatch {
    catch (RecognitionException e) {
        throw e;
    }
}

@parser::members {
	protected Object recoverFromMismatchedToken(IntStream input,int ttype, BitSet follow) throws RecognitionException {
	    //System.out.println("recoverFromMismatechedToken");
		throw new org.mule.common.query.dsql.parser.exception.DsqlParsingException(input.toString());
	}
	
	protected void mismatch(IntStream input, int ttype, BitSet follow) throws RecognitionException {
		//System.out.println("mismatch");
        throw new MismatchedTokenException(ttype, input);
    }

    public Object recoverFromMismatchedSet(IntStream input, RecognitionException e, BitSet follow) throws RecognitionException {
    	//System.out.println("recoverFromMismatechedSet");
        throw e;
    }

    public void reportError(RecognitionException e) {
    	//System.out.println("reportError");
        throw new org.mule.common.query.dsql.parser.exception.DsqlParsingException(e);
    }

    public void recover(RecognitionException e) {
    	//System.out.println("recover");
        throw new org.mule.common.query.dsql.parser.exception.DsqlParsingException(e);
    }
}

select:
      SELECT^
      ((IDENT | STRING_LITERAL)(','! (IDENT| STRING_LITERAL))*| ASTERIX)
      from 
      where?
      orderBy?
      limit?
      offset?;

from:
      FROM^
      IDENT(','! IDENT)*;

where:
      WHERE^
      expression;
    
orderBy:
    ORDER^ BY! 
    (IDENT(','! IDENT)*)
    direction?;

direction:
	(ASC|DESC);

limit:
    LIMIT^
    number;
    
offset:
    OFFSET^
    number;
    
string:
    STRING_LITERAL;

bool:
    BOOLEAN_LITERAL;

date:
    DATE_LITERAL;

number:
  NUMBER_LITERAL | MULE_EXPRESSION;

null_type:
  NULL_LITERAL;

identifier:
  IDENT;
    
term:
    identifier
    | OPENING_PARENTHESIS^expression CLOSING_PARENTHESIS!
    | string
    | number
    | bool
    | date
    | null_type;
    
negation:
      NOT^* term;

searchBy: SEARCH^ string;

comparison: negation ((OPERATOR^|COMPARATOR^) negation)*;

relation: (comparison | searchBy);


expression:
      relation ((AND^|OR^) relation)*;

ASC : (A_ S_ C_ | A_ S_ C_ E_ N_ D_ I_ N_ G_);
DESC : (D_ E_ S_ C_ | D_ E_ S_ C_ E_ N_ D_ I_ N_ G_);
SELECT  : S_ E_ L_ E_ C_ T_ ; 
FROM  : F_ R_ O_ M_ ;
WHERE  : W_ H_ E_ R_ E_;
ORDER: O_ R_ D_ E_ R_;
BY: B_ Y_;
LIMIT: L_ I_ M_ I_ T_;
OFFSET: O_ F_ F_ S_ E_ T_;
AND: A_ N_ D_;
OR: O_ R_;
NOT: N_ O_ T_;
OPENING_PARENTHESIS: '(';
CLOSING_PARENTHESIS: ')';

COMPARATOR: L_ I_ K_ E_;
SEARCH: S_ E_ A_ R_ C_ H_ B_ Y_;

DATE_LITERAL: TWO_DIGIT TWO_DIGIT'-'TWO_DIGIT'-'TWO_DIGIT'T'TWO_DIGIT':'TWO_DIGIT':'TWO_DIGIT TIME_ZONE;
fragment
TIME_ZONE: (('+'|'-') TWO_DIGIT':'TWO_DIGIT | 'Z');
NULL_LITERAL: N_ U_ L_ L_;

fragment
TWO_DIGIT: ('0'..'9') ('0'..'9');

MULE_EXPRESSION
	:	'#'NESTED_MULE_EXPRESSION;

fragment
NESTED_MULE_EXPRESSION :
	'['
	(	options {greedy=false; k=2;}
	: NESTED_MULE_EXPRESSION
	| STRING_LITERAL
	|	.
	)*
	']';

STRING_LITERAL:  
	'\'' ( ESCAPE_SEQUENCE | ~('\\'|'\'') )* '\'';

BOOLEAN_LITERAL: (T_ R_ U_ E_ | F_ A_ L_ S_ E_);
    
fragment
ESCAPE_SEQUENCE:   
	'\\' ('b'|'t'|'n'|'f'|'r'|'\"'|'\''|'\\')
    |   UNICODE_ESCAPE
    |   OCTAL_ESCAPE;

fragment
OCTAL_ESCAPE:   
	'\\' ('0'..'3') ('0'..'7') ('0'..'7')
    |   '\\' ('0'..'7') ('0'..'7')
    |   '\\' ('0'..'7');

fragment
UNICODE_ESCAPE:   
	'\\' 'u' HEX_DIGIT HEX_DIGIT HEX_DIGIT HEX_DIGIT;

fragment
HEX_DIGIT: 
	('0'..'9'|'a'..'f'|'A'..'F');

NUMBER_LITERAL:
	('0'..'9'|'.')*; 
 
IDENT : ('a'..'z' | 'A'..'Z' | '0'..'9'| '-' | '_' | '.')+;
ASTERIX : '*';
OPERATOR : '='|'>'|'<'|'<='|'<>'|'>=';

COMMENT  : ( ('--') ~('\n'|'\r')* '\r'? '\n' ) {$channel=HIDDEN;};
WS : ( ' ' | '\t' | '\n' | '\r' | '\f' )+ {$channel=HIDDEN;};

fragment A_:('a'|'A');
fragment B_:('b'|'B');
fragment C_:('c'|'C');
fragment D_:('d'|'D');
fragment E_:('e'|'E');
fragment F_:('f'|'F');
fragment G_:('g'|'G');
fragment H_:('h'|'H');
fragment I_:('i'|'I');
fragment J_:('j'|'J');
fragment K_:('k'|'K');
fragment L_:('l'|'L');
fragment M_:('m'|'M');
fragment N_:('n'|'N');
fragment O_:('o'|'O');
fragment P_:('p'|'P');
fragment Q_:('q'|'Q');
fragment R_:('r'|'R');
fragment S_:('s'|'S');
fragment T_:('t'|'T');
fragment U_:('u'|'U');
fragment V_:('v'|'V');
fragment W_:('w'|'W');
fragment X_:('x'|'X');
fragment Y_:('y'|'Y');
fragment Z_:('z'|'Z');