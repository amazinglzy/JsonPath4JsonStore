// Define a grammar called Hello
grammar JsonPath;
// r  : 'hello' ID ;         // match keyword hello followed by an identifier
// ID : [a-z]+ ;             // match lower-case identifiers
// WS : [ \t\r\n]+ -> skip ; // skip spaces, tabs, newlines

json_basic_path_expr: json_absolute_path_expr
  ;

json_absolute_path_expr: '$' json_nonfunction_steps
  ;

json_nonfunction_steps: (json_object_step | json_array_step | json_descendent_step) *
  ;

json_object_step: '.' ( WILDCARD | json_field_name)
  ;

json_descendent_step: '..' json_field_name
  ;

json_array_step: '[' (WILDCARD | ((NATRUAL_INTEGER | json_array_range) (',' (NATRUAL_INTEGER| json_array_range))* ) ) ']'
  ;

json_field_name: JSON_STRING | IDENTIFIER
  ;

json_array_range: NATRUAL_INTEGER ':' NATRUAL_INTEGER
  ;

JSON_STRING: '"' (~["\\])* '"';
NATRUAL_INTEGER: '0' | POSITIVEDIGIT DIGIT*;
IDENTIFIER: LETTER (LETTER | DIGIT)*;

WILDCARD: '*';
DIGIT: [0-9];
POSITIVEDIGIT: [1-9];
LETTER: [A-Z] | [a-z];
WS: [ \t\r\n]+ -> skip;