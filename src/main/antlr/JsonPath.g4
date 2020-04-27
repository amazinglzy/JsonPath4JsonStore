// Define a grammar called Hello
grammar JsonPath;

jsonBasicPathExpr: jsonAbsolutePathExpr | jsonRelativePathExpr ;

jsonAbsolutePathExpr: '$' jsonSteps ;
jsonRelativePathExpr: '@' jsonSteps ;

jsonSteps: jsonStep * ;
jsonStep: (jsonObjectStep | jsonArrayStep | jsonDescendentStep ) jsonFilterExpr ?;
jsonFilterExpr: FILTER_BEGIN jsonCond FILTER_END ;
jsonCond: jsonRelativePathExpr                                                          # JsonCondExists
        | jsonCond LOGIC_AND jsonCond                                                   # JsonCondAnd
        ;

jsonObjectStep: '.' (jsonObjectWildcardStep | jsonObjectFieldNameStep);
jsonObjectWildcardStep: WILDCARD ;
jsonObjectFieldNameStep: jsonFieldName ;
jsonDescendentStep: '..' jsonFieldName ;
jsonFieldName: IDENTIFIER ;

jsonArrayStep: ARRAY_BEGIN (jsonArrayWildcardStep | jsonArraySelectionsStep) ARRAY_END ;
jsonArrayWildcardStep: WILDCARD;
jsonArraySelectionsStep: jsonArraySelection (',' jsonArraySelection)* ;
jsonArraySelection: jsonArrayIndex | jsonArraySlice ;
jsonArrayIndex: NATRUAL_INTEGER ;
jsonArraySlice: NATRUAL_INTEGER ':' NATRUAL_INTEGER ;

JSON_STRING: '"' (~["\\])* '"';
NATRUAL_INTEGER: '0' | POSITIVEDIGIT DIGIT*;
IDENTIFIER: LETTER (LETTER | DIGIT)*;

FILTER_BEGIN: '[?(';
FILTER_END: ')]';
ARRAY_BEGIN: '[';
ARRAY_END: ']';

WILDCARD: '*';
LOGIC_AND: '&&';
DIGIT: [0-9];
POSITIVEDIGIT: [1-9];
LETTER: [A-Z] | [a-z];
WS: [ \t\r\n]+ -> skip;