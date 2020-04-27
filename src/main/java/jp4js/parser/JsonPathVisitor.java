// Generated from JsonPath.g4 by ANTLR 4.7
package jp4js.parser;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link JsonPathParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface JsonPathVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link JsonPathParser#jsonBasicPathExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJsonBasicPathExpr(JsonPathParser.JsonBasicPathExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link JsonPathParser#jsonAbsolutePathExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJsonAbsolutePathExpr(JsonPathParser.JsonAbsolutePathExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link JsonPathParser#jsonRelativePathExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJsonRelativePathExpr(JsonPathParser.JsonRelativePathExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link JsonPathParser#jsonSteps}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJsonSteps(JsonPathParser.JsonStepsContext ctx);
	/**
	 * Visit a parse tree produced by {@link JsonPathParser#jsonStep}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJsonStep(JsonPathParser.JsonStepContext ctx);
	/**
	 * Visit a parse tree produced by {@link JsonPathParser#jsonFilterExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJsonFilterExpr(JsonPathParser.JsonFilterExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code JsonCondAnd}
	 * labeled alternative in {@link JsonPathParser#jsonCond}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJsonCondAnd(JsonPathParser.JsonCondAndContext ctx);
	/**
	 * Visit a parse tree produced by the {@code JsonCondExists}
	 * labeled alternative in {@link JsonPathParser#jsonCond}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJsonCondExists(JsonPathParser.JsonCondExistsContext ctx);
	/**
	 * Visit a parse tree produced by {@link JsonPathParser#jsonObjectStep}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJsonObjectStep(JsonPathParser.JsonObjectStepContext ctx);
	/**
	 * Visit a parse tree produced by {@link JsonPathParser#jsonObjectWildcardStep}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJsonObjectWildcardStep(JsonPathParser.JsonObjectWildcardStepContext ctx);
	/**
	 * Visit a parse tree produced by {@link JsonPathParser#jsonObjectFieldNameStep}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJsonObjectFieldNameStep(JsonPathParser.JsonObjectFieldNameStepContext ctx);
	/**
	 * Visit a parse tree produced by {@link JsonPathParser#jsonDescendentStep}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJsonDescendentStep(JsonPathParser.JsonDescendentStepContext ctx);
	/**
	 * Visit a parse tree produced by {@link JsonPathParser#jsonFieldName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJsonFieldName(JsonPathParser.JsonFieldNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link JsonPathParser#jsonArrayStep}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJsonArrayStep(JsonPathParser.JsonArrayStepContext ctx);
	/**
	 * Visit a parse tree produced by {@link JsonPathParser#jsonArrayWildcardStep}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJsonArrayWildcardStep(JsonPathParser.JsonArrayWildcardStepContext ctx);
	/**
	 * Visit a parse tree produced by {@link JsonPathParser#jsonArraySelectionsStep}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJsonArraySelectionsStep(JsonPathParser.JsonArraySelectionsStepContext ctx);
	/**
	 * Visit a parse tree produced by {@link JsonPathParser#jsonArraySelection}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJsonArraySelection(JsonPathParser.JsonArraySelectionContext ctx);
	/**
	 * Visit a parse tree produced by {@link JsonPathParser#jsonArrayIndex}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJsonArrayIndex(JsonPathParser.JsonArrayIndexContext ctx);
	/**
	 * Visit a parse tree produced by {@link JsonPathParser#jsonArraySlice}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJsonArraySlice(JsonPathParser.JsonArraySliceContext ctx);
}