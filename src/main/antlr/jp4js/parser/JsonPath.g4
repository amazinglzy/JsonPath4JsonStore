// Define a grammar called Hello
grammar JsonPath;
// r  : 'hello' ID ;         // match keyword hello followed by an identifier
// ID : [a-z]+ ;             // match lower-case identifiers
// WS : [ \t\r\n]+ -> skip ; // skip spaces, tabs, newlines

jsonBasicPathExpr: jsonAbsolutePathExpr
  ;

jsonAbsolutePathExpr: '$' jsonNonfunctionSteps
  ;

jsonNonfunctionSteps: (jsonObjectStep | jsonArrayStep | jsonDescendentStep) *
  ;

jsonObjectStep: '.' ( WILDCARD | jsonFieldName)
  ;

jsonDescendentStep: '..' jsonFieldName
  ;

jsonArrayStep: '[' (WILDCARD | ((NATRUAL_INTEGER | jsonArrayRange) (',' (NATRUAL_INTEGER| jsonArrayRange))* ) ) ']'
  ;

jsonFieldName: JSON_STRING | IDENTIFIER
  ;

jsonArrayRange: NATRUAL_INTEGER ':' NATRUAL_INTEGER
  ;

JSON_STRING: '"' (~["\\])* '"';
NATRUAL_INTEGER: '0' | POSITIVEDIGIT DIGIT*;
IDENTIFIER: LETTER (LETTER | DIGIT)*;

WILDCARD: '*';
DIGIT: [0-9];
POSITIVEDIGIT: [1-9];
LETTER: [A-Z] | [a-z];
WS: [ \t\r\n]+ -> skip;