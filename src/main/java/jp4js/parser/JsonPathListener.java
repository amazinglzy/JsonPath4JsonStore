// Generated from jp4js/parser/JsonPath.g4 by ANTLR 4.7
package jp4js.parser;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link JsonPathParser}.
 */
public interface JsonPathListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link JsonPathParser#json_basic_path_expr}.
	 * @param ctx the parse tree
	 */
	void enterJson_basic_path_expr(JsonPathParser.Json_basic_path_exprContext ctx);
	/**
	 * Exit a parse tree produced by {@link JsonPathParser#json_basic_path_expr}.
	 * @param ctx the parse tree
	 */
	void exitJson_basic_path_expr(JsonPathParser.Json_basic_path_exprContext ctx);
	/**
	 * Enter a parse tree produced by {@link JsonPathParser#json_absolute_path_expr}.
	 * @param ctx the parse tree
	 */
	void enterJson_absolute_path_expr(JsonPathParser.Json_absolute_path_exprContext ctx);
	/**
	 * Exit a parse tree produced by {@link JsonPathParser#json_absolute_path_expr}.
	 * @param ctx the parse tree
	 */
	void exitJson_absolute_path_expr(JsonPathParser.Json_absolute_path_exprContext ctx);
	/**
	 * Enter a parse tree produced by {@link JsonPathParser#json_nonfunction_steps}.
	 * @param ctx the parse tree
	 */
	void enterJson_nonfunction_steps(JsonPathParser.Json_nonfunction_stepsContext ctx);
	/**
	 * Exit a parse tree produced by {@link JsonPathParser#json_nonfunction_steps}.
	 * @param ctx the parse tree
	 */
	void exitJson_nonfunction_steps(JsonPathParser.Json_nonfunction_stepsContext ctx);
	/**
	 * Enter a parse tree produced by {@link JsonPathParser#json_object_step}.
	 * @param ctx the parse tree
	 */
	void enterJson_object_step(JsonPathParser.Json_object_stepContext ctx);
	/**
	 * Exit a parse tree produced by {@link JsonPathParser#json_object_step}.
	 * @param ctx the parse tree
	 */
	void exitJson_object_step(JsonPathParser.Json_object_stepContext ctx);
	/**
	 * Enter a parse tree produced by {@link JsonPathParser#json_descendent_step}.
	 * @param ctx the parse tree
	 */
	void enterJson_descendent_step(JsonPathParser.Json_descendent_stepContext ctx);
	/**
	 * Exit a parse tree produced by {@link JsonPathParser#json_descendent_step}.
	 * @param ctx the parse tree
	 */
	void exitJson_descendent_step(JsonPathParser.Json_descendent_stepContext ctx);
	/**
	 * Enter a parse tree produced by {@link JsonPathParser#json_array_step}.
	 * @param ctx the parse tree
	 */
	void enterJson_array_step(JsonPathParser.Json_array_stepContext ctx);
	/**
	 * Exit a parse tree produced by {@link JsonPathParser#json_array_step}.
	 * @param ctx the parse tree
	 */
	void exitJson_array_step(JsonPathParser.Json_array_stepContext ctx);
	/**
	 * Enter a parse tree produced by {@link JsonPathParser#json_field_name}.
	 * @param ctx the parse tree
	 */
	void enterJson_field_name(JsonPathParser.Json_field_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link JsonPathParser#json_field_name}.
	 * @param ctx the parse tree
	 */
	void exitJson_field_name(JsonPathParser.Json_field_nameContext ctx);
	/**
	 * Enter a parse tree produced by {@link JsonPathParser#json_array_range}.
	 * @param ctx the parse tree
	 */
	void enterJson_array_range(JsonPathParser.Json_array_rangeContext ctx);
	/**
	 * Exit a parse tree produced by {@link JsonPathParser#json_array_range}.
	 * @param ctx the parse tree
	 */
	void exitJson_array_range(JsonPathParser.Json_array_rangeContext ctx);
}