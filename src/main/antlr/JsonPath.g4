// Define a grammar called Hello
grammar JsonPath;

jsonBasicPathExpr: jsonAbsolutePathExpr ;

jsonAbsolutePathExpr: '$' jsonSteps ;

jsonSteps: (jsonObjectStep | jsonArrayStep | jsonDescendentStep) * ;

jsonObjectStep: '.' (jsonObjectWildcardStep | jsonObjectFieldNameStep);
jsonObjectWildcardStep: WILDCARD ;
jsonObjectFieldNameStep: jsonFieldName ;
jsonDescendentStep: '..' jsonFieldName ;
jsonFieldName: IDENTIFIER ;

jsonArrayStep: '[' (jsonArrayWildcardStep | jsonArraySelectionsStep) ']' ;
jsonArrayWildcardStep: WILDCARD;
jsonArraySelectionsStep: jsonArraySelection (',' jsonArraySelection)* ;
jsonArraySelection: jsonArrayIndex | jsonArraySlice ;
jsonArrayIndex: NATRUAL_INTEGER ;
jsonArraySlice: NATRUAL_INTEGER ':' NATRUAL_INTEGER ;

JSON_STRING: '"' (~["\\])* '"';
NATRUAL_INTEGER: '0' | POSITIVEDIGIT DIGIT*;
IDENTIFIER: LETTER (LETTER | DIGIT)*;

WILDCARD: '*';
DIGIT: [0-9];
POSITIVEDIGIT: [1-9];
LETTER: [A-Z] | [a-z];
WS: [ \t\r\n]+ -> skip;