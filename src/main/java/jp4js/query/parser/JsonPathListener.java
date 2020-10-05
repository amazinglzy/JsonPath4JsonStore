// Generated from JsonPath.g4 by ANTLR 4.7
package jp4js.query.parser;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link JsonPathParser}.
 */
public interface JsonPathListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link JsonPathParser#jsonBasicPathExpr}.
	 * @param ctx the parse tree
	 */
	void enterJsonBasicPathExpr(JsonPathParser.JsonBasicPathExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link JsonPathParser#jsonBasicPathExpr}.
	 * @param ctx the parse tree
	 */
	void exitJsonBasicPathExpr(JsonPathParser.JsonBasicPathExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link JsonPathParser#jsonAbsolutePathExpr}.
	 * @param ctx the parse tree
	 */
	void enterJsonAbsolutePathExpr(JsonPathParser.JsonAbsolutePathExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link JsonPathParser#jsonAbsolutePathExpr}.
	 * @param ctx the parse tree
	 */
	void exitJsonAbsolutePathExpr(JsonPathParser.JsonAbsolutePathExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link JsonPathParser#jsonRelativePathExpr}.
	 * @param ctx the parse tree
	 */
	void enterJsonRelativePathExpr(JsonPathParser.JsonRelativePathExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link JsonPathParser#jsonRelativePathExpr}.
	 * @param ctx the parse tree
	 */
	void exitJsonRelativePathExpr(JsonPathParser.JsonRelativePathExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link JsonPathParser#jsonSteps}.
	 * @param ctx the parse tree
	 */
	void enterJsonSteps(JsonPathParser.JsonStepsContext ctx);
	/**
	 * Exit a parse tree produced by {@link JsonPathParser#jsonSteps}.
	 * @param ctx the parse tree
	 */
	void exitJsonSteps(JsonPathParser.JsonStepsContext ctx);
	/**
	 * Enter a parse tree produced by {@link JsonPathParser#jsonStep}.
	 * @param ctx the parse tree
	 */
	void enterJsonStep(JsonPathParser.JsonStepContext ctx);
	/**
	 * Exit a parse tree produced by {@link JsonPathParser#jsonStep}.
	 * @param ctx the parse tree
	 */
	void exitJsonStep(JsonPathParser.JsonStepContext ctx);
	/**
	 * Enter a parse tree produced by {@link JsonPathParser#jsonFilterExpr}.
	 * @param ctx the parse tree
	 */
	void enterJsonFilterExpr(JsonPathParser.JsonFilterExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link JsonPathParser#jsonFilterExpr}.
	 * @param ctx the parse tree
	 */
	void exitJsonFilterExpr(JsonPathParser.JsonFilterExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code JsonCondAnd}
	 * labeled alternative in {@link JsonPathParser#jsonCond}.
	 * @param ctx the parse tree
	 */
	void enterJsonCondAnd(JsonPathParser.JsonCondAndContext ctx);
	/**
	 * Exit a parse tree produced by the {@code JsonCondAnd}
	 * labeled alternative in {@link JsonPathParser#jsonCond}.
	 * @param ctx the parse tree
	 */
	void exitJsonCondAnd(JsonPathParser.JsonCondAndContext ctx);
	/**
	 * Enter a parse tree produced by the {@code JsonCondExists}
	 * labeled alternative in {@link JsonPathParser#jsonCond}.
	 * @param ctx the parse tree
	 */
	void enterJsonCondExists(JsonPathParser.JsonCondExistsContext ctx);
	/**
	 * Exit a parse tree produced by the {@code JsonCondExists}
	 * labeled alternative in {@link JsonPathParser#jsonCond}.
	 * @param ctx the parse tree
	 */
	void exitJsonCondExists(JsonPathParser.JsonCondExistsContext ctx);
	/**
	 * Enter a parse tree produced by {@link JsonPathParser#jsonObjectStep}.
	 * @param ctx the parse tree
	 */
	void enterJsonObjectStep(JsonPathParser.JsonObjectStepContext ctx);
	/**
	 * Exit a parse tree produced by {@link JsonPathParser#jsonObjectStep}.
	 * @param ctx the parse tree
	 */
	void exitJsonObjectStep(JsonPathParser.JsonObjectStepContext ctx);
	/**
	 * Enter a parse tree produced by {@link JsonPathParser#jsonObjectWildcardStep}.
	 * @param ctx the parse tree
	 */
	void enterJsonObjectWildcardStep(JsonPathParser.JsonObjectWildcardStepContext ctx);
	/**
	 * Exit a parse tree produced by {@link JsonPathParser#jsonObjectWildcardStep}.
	 * @param ctx the parse tree
	 */
	void exitJsonObjectWildcardStep(JsonPathParser.JsonObjectWildcardStepContext ctx);
	/**
	 * Enter a parse tree produced by {@link JsonPathParser#jsonObjectFieldNameStep}.
	 * @param ctx the parse tree
	 */
	void enterJsonObjectFieldNameStep(JsonPathParser.JsonObjectFieldNameStepContext ctx);
	/**
	 * Exit a parse tree produced by {@link JsonPathParser#jsonObjectFieldNameStep}.
	 * @param ctx the parse tree
	 */
	void exitJsonObjectFieldNameStep(JsonPathParser.JsonObjectFieldNameStepContext ctx);
	/**
	 * Enter a parse tree produced by {@link JsonPathParser#jsonDescendentStep}.
	 * @param ctx the parse tree
	 */
	void enterJsonDescendentStep(JsonPathParser.JsonDescendentStepContext ctx);
	/**
	 * Exit a parse tree produced by {@link JsonPathParser#jsonDescendentStep}.
	 * @param ctx the parse tree
	 */
	void exitJsonDescendentStep(JsonPathParser.JsonDescendentStepContext ctx);
	/**
	 * Enter a parse tree produced by {@link JsonPathParser#jsonFieldName}.
	 * @param ctx the parse tree
	 */
	void enterJsonFieldName(JsonPathParser.JsonFieldNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link JsonPathParser#jsonFieldName}.
	 * @param ctx the parse tree
	 */
	void exitJsonFieldName(JsonPathParser.JsonFieldNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link JsonPathParser#jsonArrayStep}.
	 * @param ctx the parse tree
	 */
	void enterJsonArrayStep(JsonPathParser.JsonArrayStepContext ctx);
	/**
	 * Exit a parse tree produced by {@link JsonPathParser#jsonArrayStep}.
	 * @param ctx the parse tree
	 */
	void exitJsonArrayStep(JsonPathParser.JsonArrayStepContext ctx);
	/**
	 * Enter a parse tree produced by {@link JsonPathParser#jsonArrayWildcardStep}.
	 * @param ctx the parse tree
	 */
	void enterJsonArrayWildcardStep(JsonPathParser.JsonArrayWildcardStepContext ctx);
	/**
	 * Exit a parse tree produced by {@link JsonPathParser#jsonArrayWildcardStep}.
	 * @param ctx the parse tree
	 */
	void exitJsonArrayWildcardStep(JsonPathParser.JsonArrayWildcardStepContext ctx);
	/**
	 * Enter a parse tree produced by {@link JsonPathParser#jsonArraySelectionsStep}.
	 * @param ctx the parse tree
	 */
	void enterJsonArraySelectionsStep(JsonPathParser.JsonArraySelectionsStepContext ctx);
	/**
	 * Exit a parse tree produced by {@link JsonPathParser#jsonArraySelectionsStep}.
	 * @param ctx the parse tree
	 */
	void exitJsonArraySelectionsStep(JsonPathParser.JsonArraySelectionsStepContext ctx);
	/**
	 * Enter a parse tree produced by {@link JsonPathParser#jsonArraySlice}.
	 * @param ctx the parse tree
	 */
	void enterJsonArraySlice(JsonPathParser.JsonArraySliceContext ctx);
	/**
	 * Exit a parse tree produced by {@link JsonPathParser#jsonArraySlice}.
	 * @param ctx the parse tree
	 */
	void exitJsonArraySlice(JsonPathParser.JsonArraySliceContext ctx);
}