// Generated from JsonPath.g4 by ANTLR 4.7
package jp4js.parser;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class JsonPathParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, JSON_STRING=6, NATRUAL_INTEGER=7, 
		IDENTIFIER=8, FILTER_BEGIN=9, FILTER_END=10, ARRAY_BEGIN=11, ARRAY_END=12, 
		WILDCARD=13, LOGIC_AND=14, DIGIT=15, POSITIVEDIGIT=16, LETTER=17, WS=18;
	public static final int
		RULE_jsonBasicPathExpr = 0, RULE_jsonAbsolutePathExpr = 1, RULE_jsonRelativePathExpr = 2, 
		RULE_jsonSteps = 3, RULE_jsonStep = 4, RULE_jsonFilterExpr = 5, RULE_jsonCond = 6, 
		RULE_jsonObjectStep = 7, RULE_jsonObjectWildcardStep = 8, RULE_jsonObjectFieldNameStep = 9, 
		RULE_jsonDescendentStep = 10, RULE_jsonFieldName = 11, RULE_jsonArrayStep = 12, 
		RULE_jsonArrayWildcardStep = 13, RULE_jsonArraySelectionsStep = 14, RULE_jsonArraySlice = 15;
	public static final String[] ruleNames = {
		"jsonBasicPathExpr", "jsonAbsolutePathExpr", "jsonRelativePathExpr", "jsonSteps", 
		"jsonStep", "jsonFilterExpr", "jsonCond", "jsonObjectStep", "jsonObjectWildcardStep", 
		"jsonObjectFieldNameStep", "jsonDescendentStep", "jsonFieldName", "jsonArrayStep", 
		"jsonArrayWildcardStep", "jsonArraySelectionsStep", "jsonArraySlice"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'$'", "'@'", "'.'", "'..'", "':'", null, null, null, "'[?('", "')]'", 
		"'['", "']'", "'*'", "'&&'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, "JSON_STRING", "NATRUAL_INTEGER", 
		"IDENTIFIER", "FILTER_BEGIN", "FILTER_END", "ARRAY_BEGIN", "ARRAY_END", 
		"WILDCARD", "LOGIC_AND", "DIGIT", "POSITIVEDIGIT", "LETTER", "WS"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "JsonPath.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public JsonPathParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class JsonBasicPathExprContext extends ParserRuleContext {
		public JsonAbsolutePathExprContext jsonAbsolutePathExpr() {
			return getRuleContext(JsonAbsolutePathExprContext.class,0);
		}
		public JsonRelativePathExprContext jsonRelativePathExpr() {
			return getRuleContext(JsonRelativePathExprContext.class,0);
		}
		public JsonBasicPathExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_jsonBasicPathExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JsonPathListener ) ((JsonPathListener)listener).enterJsonBasicPathExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JsonPathListener ) ((JsonPathListener)listener).exitJsonBasicPathExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JsonPathVisitor ) return ((JsonPathVisitor<? extends T>)visitor).visitJsonBasicPathExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final JsonBasicPathExprContext jsonBasicPathExpr() throws RecognitionException {
		JsonBasicPathExprContext _localctx = new JsonBasicPathExprContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_jsonBasicPathExpr);
		try {
			setState(34);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__0:
				enterOuterAlt(_localctx, 1);
				{
				setState(32);
				jsonAbsolutePathExpr();
				}
				break;
			case T__1:
				enterOuterAlt(_localctx, 2);
				{
				setState(33);
				jsonRelativePathExpr();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class JsonAbsolutePathExprContext extends ParserRuleContext {
		public JsonStepsContext jsonSteps() {
			return getRuleContext(JsonStepsContext.class,0);
		}
		public JsonAbsolutePathExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_jsonAbsolutePathExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JsonPathListener ) ((JsonPathListener)listener).enterJsonAbsolutePathExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JsonPathListener ) ((JsonPathListener)listener).exitJsonAbsolutePathExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JsonPathVisitor ) return ((JsonPathVisitor<? extends T>)visitor).visitJsonAbsolutePathExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final JsonAbsolutePathExprContext jsonAbsolutePathExpr() throws RecognitionException {
		JsonAbsolutePathExprContext _localctx = new JsonAbsolutePathExprContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_jsonAbsolutePathExpr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(36);
			match(T__0);
			setState(37);
			jsonSteps();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class JsonRelativePathExprContext extends ParserRuleContext {
		public JsonStepsContext jsonSteps() {
			return getRuleContext(JsonStepsContext.class,0);
		}
		public JsonRelativePathExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_jsonRelativePathExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JsonPathListener ) ((JsonPathListener)listener).enterJsonRelativePathExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JsonPathListener ) ((JsonPathListener)listener).exitJsonRelativePathExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JsonPathVisitor ) return ((JsonPathVisitor<? extends T>)visitor).visitJsonRelativePathExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final JsonRelativePathExprContext jsonRelativePathExpr() throws RecognitionException {
		JsonRelativePathExprContext _localctx = new JsonRelativePathExprContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_jsonRelativePathExpr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(39);
			match(T__1);
			setState(40);
			jsonSteps();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class JsonStepsContext extends ParserRuleContext {
		public List<JsonStepContext> jsonStep() {
			return getRuleContexts(JsonStepContext.class);
		}
		public JsonStepContext jsonStep(int i) {
			return getRuleContext(JsonStepContext.class,i);
		}
		public JsonStepsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_jsonSteps; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JsonPathListener ) ((JsonPathListener)listener).enterJsonSteps(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JsonPathListener ) ((JsonPathListener)listener).exitJsonSteps(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JsonPathVisitor ) return ((JsonPathVisitor<? extends T>)visitor).visitJsonSteps(this);
			else return visitor.visitChildren(this);
		}
	}

	public final JsonStepsContext jsonSteps() throws RecognitionException {
		JsonStepsContext _localctx = new JsonStepsContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_jsonSteps);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(45);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(42);
					jsonStep();
					}
					} 
				}
				setState(47);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class JsonStepContext extends ParserRuleContext {
		public JsonObjectStepContext jsonObjectStep() {
			return getRuleContext(JsonObjectStepContext.class,0);
		}
		public JsonArrayStepContext jsonArrayStep() {
			return getRuleContext(JsonArrayStepContext.class,0);
		}
		public JsonDescendentStepContext jsonDescendentStep() {
			return getRuleContext(JsonDescendentStepContext.class,0);
		}
		public JsonFilterExprContext jsonFilterExpr() {
			return getRuleContext(JsonFilterExprContext.class,0);
		}
		public JsonStepContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_jsonStep; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JsonPathListener ) ((JsonPathListener)listener).enterJsonStep(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JsonPathListener ) ((JsonPathListener)listener).exitJsonStep(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JsonPathVisitor ) return ((JsonPathVisitor<? extends T>)visitor).visitJsonStep(this);
			else return visitor.visitChildren(this);
		}
	}

	public final JsonStepContext jsonStep() throws RecognitionException {
		JsonStepContext _localctx = new JsonStepContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_jsonStep);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(51);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__2:
				{
				setState(48);
				jsonObjectStep();
				}
				break;
			case ARRAY_BEGIN:
				{
				setState(49);
				jsonArrayStep();
				}
				break;
			case T__3:
				{
				setState(50);
				jsonDescendentStep();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(54);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				{
				setState(53);
				jsonFilterExpr();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class JsonFilterExprContext extends ParserRuleContext {
		public TerminalNode FILTER_BEGIN() { return getToken(JsonPathParser.FILTER_BEGIN, 0); }
		public JsonCondContext jsonCond() {
			return getRuleContext(JsonCondContext.class,0);
		}
		public TerminalNode FILTER_END() { return getToken(JsonPathParser.FILTER_END, 0); }
		public JsonFilterExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_jsonFilterExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JsonPathListener ) ((JsonPathListener)listener).enterJsonFilterExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JsonPathListener ) ((JsonPathListener)listener).exitJsonFilterExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JsonPathVisitor ) return ((JsonPathVisitor<? extends T>)visitor).visitJsonFilterExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final JsonFilterExprContext jsonFilterExpr() throws RecognitionException {
		JsonFilterExprContext _localctx = new JsonFilterExprContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_jsonFilterExpr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(56);
			match(FILTER_BEGIN);
			setState(57);
			jsonCond(0);
			setState(58);
			match(FILTER_END);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class JsonCondContext extends ParserRuleContext {
		public JsonCondContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_jsonCond; }
	 
		public JsonCondContext() { }
		public void copyFrom(JsonCondContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class JsonCondAndContext extends JsonCondContext {
		public List<JsonCondContext> jsonCond() {
			return getRuleContexts(JsonCondContext.class);
		}
		public JsonCondContext jsonCond(int i) {
			return getRuleContext(JsonCondContext.class,i);
		}
		public TerminalNode LOGIC_AND() { return getToken(JsonPathParser.LOGIC_AND, 0); }
		public JsonCondAndContext(JsonCondContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JsonPathListener ) ((JsonPathListener)listener).enterJsonCondAnd(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JsonPathListener ) ((JsonPathListener)listener).exitJsonCondAnd(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JsonPathVisitor ) return ((JsonPathVisitor<? extends T>)visitor).visitJsonCondAnd(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class JsonCondExistsContext extends JsonCondContext {
		public JsonRelativePathExprContext jsonRelativePathExpr() {
			return getRuleContext(JsonRelativePathExprContext.class,0);
		}
		public JsonCondExistsContext(JsonCondContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JsonPathListener ) ((JsonPathListener)listener).enterJsonCondExists(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JsonPathListener ) ((JsonPathListener)listener).exitJsonCondExists(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JsonPathVisitor ) return ((JsonPathVisitor<? extends T>)visitor).visitJsonCondExists(this);
			else return visitor.visitChildren(this);
		}
	}

	public final JsonCondContext jsonCond() throws RecognitionException {
		return jsonCond(0);
	}

	private JsonCondContext jsonCond(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		JsonCondContext _localctx = new JsonCondContext(_ctx, _parentState);
		JsonCondContext _prevctx = _localctx;
		int _startState = 12;
		enterRecursionRule(_localctx, 12, RULE_jsonCond, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			_localctx = new JsonCondExistsContext(_localctx);
			_ctx = _localctx;
			_prevctx = _localctx;

			setState(61);
			jsonRelativePathExpr();
			}
			_ctx.stop = _input.LT(-1);
			setState(68);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new JsonCondAndContext(new JsonCondContext(_parentctx, _parentState));
					pushNewRecursionContext(_localctx, _startState, RULE_jsonCond);
					setState(63);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(64);
					match(LOGIC_AND);
					setState(65);
					jsonCond(2);
					}
					} 
				}
				setState(70);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class JsonObjectStepContext extends ParserRuleContext {
		public JsonObjectWildcardStepContext jsonObjectWildcardStep() {
			return getRuleContext(JsonObjectWildcardStepContext.class,0);
		}
		public JsonObjectFieldNameStepContext jsonObjectFieldNameStep() {
			return getRuleContext(JsonObjectFieldNameStepContext.class,0);
		}
		public JsonObjectStepContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_jsonObjectStep; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JsonPathListener ) ((JsonPathListener)listener).enterJsonObjectStep(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JsonPathListener ) ((JsonPathListener)listener).exitJsonObjectStep(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JsonPathVisitor ) return ((JsonPathVisitor<? extends T>)visitor).visitJsonObjectStep(this);
			else return visitor.visitChildren(this);
		}
	}

	public final JsonObjectStepContext jsonObjectStep() throws RecognitionException {
		JsonObjectStepContext _localctx = new JsonObjectStepContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_jsonObjectStep);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(71);
			match(T__2);
			setState(74);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case WILDCARD:
				{
				setState(72);
				jsonObjectWildcardStep();
				}
				break;
			case IDENTIFIER:
				{
				setState(73);
				jsonObjectFieldNameStep();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class JsonObjectWildcardStepContext extends ParserRuleContext {
		public TerminalNode WILDCARD() { return getToken(JsonPathParser.WILDCARD, 0); }
		public JsonObjectWildcardStepContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_jsonObjectWildcardStep; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JsonPathListener ) ((JsonPathListener)listener).enterJsonObjectWildcardStep(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JsonPathListener ) ((JsonPathListener)listener).exitJsonObjectWildcardStep(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JsonPathVisitor ) return ((JsonPathVisitor<? extends T>)visitor).visitJsonObjectWildcardStep(this);
			else return visitor.visitChildren(this);
		}
	}

	public final JsonObjectWildcardStepContext jsonObjectWildcardStep() throws RecognitionException {
		JsonObjectWildcardStepContext _localctx = new JsonObjectWildcardStepContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_jsonObjectWildcardStep);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(76);
			match(WILDCARD);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class JsonObjectFieldNameStepContext extends ParserRuleContext {
		public JsonFieldNameContext jsonFieldName() {
			return getRuleContext(JsonFieldNameContext.class,0);
		}
		public JsonObjectFieldNameStepContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_jsonObjectFieldNameStep; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JsonPathListener ) ((JsonPathListener)listener).enterJsonObjectFieldNameStep(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JsonPathListener ) ((JsonPathListener)listener).exitJsonObjectFieldNameStep(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JsonPathVisitor ) return ((JsonPathVisitor<? extends T>)visitor).visitJsonObjectFieldNameStep(this);
			else return visitor.visitChildren(this);
		}
	}

	public final JsonObjectFieldNameStepContext jsonObjectFieldNameStep() throws RecognitionException {
		JsonObjectFieldNameStepContext _localctx = new JsonObjectFieldNameStepContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_jsonObjectFieldNameStep);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(78);
			jsonFieldName();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class JsonDescendentStepContext extends ParserRuleContext {
		public JsonFieldNameContext jsonFieldName() {
			return getRuleContext(JsonFieldNameContext.class,0);
		}
		public JsonDescendentStepContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_jsonDescendentStep; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JsonPathListener ) ((JsonPathListener)listener).enterJsonDescendentStep(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JsonPathListener ) ((JsonPathListener)listener).exitJsonDescendentStep(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JsonPathVisitor ) return ((JsonPathVisitor<? extends T>)visitor).visitJsonDescendentStep(this);
			else return visitor.visitChildren(this);
		}
	}

	public final JsonDescendentStepContext jsonDescendentStep() throws RecognitionException {
		JsonDescendentStepContext _localctx = new JsonDescendentStepContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_jsonDescendentStep);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(80);
			match(T__3);
			setState(81);
			jsonFieldName();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class JsonFieldNameContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(JsonPathParser.IDENTIFIER, 0); }
		public JsonFieldNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_jsonFieldName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JsonPathListener ) ((JsonPathListener)listener).enterJsonFieldName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JsonPathListener ) ((JsonPathListener)listener).exitJsonFieldName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JsonPathVisitor ) return ((JsonPathVisitor<? extends T>)visitor).visitJsonFieldName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final JsonFieldNameContext jsonFieldName() throws RecognitionException {
		JsonFieldNameContext _localctx = new JsonFieldNameContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_jsonFieldName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(83);
			match(IDENTIFIER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class JsonArrayStepContext extends ParserRuleContext {
		public TerminalNode ARRAY_BEGIN() { return getToken(JsonPathParser.ARRAY_BEGIN, 0); }
		public TerminalNode ARRAY_END() { return getToken(JsonPathParser.ARRAY_END, 0); }
		public JsonArrayWildcardStepContext jsonArrayWildcardStep() {
			return getRuleContext(JsonArrayWildcardStepContext.class,0);
		}
		public JsonArraySelectionsStepContext jsonArraySelectionsStep() {
			return getRuleContext(JsonArraySelectionsStepContext.class,0);
		}
		public JsonArrayStepContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_jsonArrayStep; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JsonPathListener ) ((JsonPathListener)listener).enterJsonArrayStep(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JsonPathListener ) ((JsonPathListener)listener).exitJsonArrayStep(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JsonPathVisitor ) return ((JsonPathVisitor<? extends T>)visitor).visitJsonArrayStep(this);
			else return visitor.visitChildren(this);
		}
	}

	public final JsonArrayStepContext jsonArrayStep() throws RecognitionException {
		JsonArrayStepContext _localctx = new JsonArrayStepContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_jsonArrayStep);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(85);
			match(ARRAY_BEGIN);
			setState(88);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case WILDCARD:
				{
				setState(86);
				jsonArrayWildcardStep();
				}
				break;
			case NATRUAL_INTEGER:
				{
				setState(87);
				jsonArraySelectionsStep();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(90);
			match(ARRAY_END);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class JsonArrayWildcardStepContext extends ParserRuleContext {
		public TerminalNode WILDCARD() { return getToken(JsonPathParser.WILDCARD, 0); }
		public JsonArrayWildcardStepContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_jsonArrayWildcardStep; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JsonPathListener ) ((JsonPathListener)listener).enterJsonArrayWildcardStep(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JsonPathListener ) ((JsonPathListener)listener).exitJsonArrayWildcardStep(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JsonPathVisitor ) return ((JsonPathVisitor<? extends T>)visitor).visitJsonArrayWildcardStep(this);
			else return visitor.visitChildren(this);
		}
	}

	public final JsonArrayWildcardStepContext jsonArrayWildcardStep() throws RecognitionException {
		JsonArrayWildcardStepContext _localctx = new JsonArrayWildcardStepContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_jsonArrayWildcardStep);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(92);
			match(WILDCARD);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class JsonArraySelectionsStepContext extends ParserRuleContext {
		public JsonArraySliceContext jsonArraySlice() {
			return getRuleContext(JsonArraySliceContext.class,0);
		}
		public JsonArraySelectionsStepContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_jsonArraySelectionsStep; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JsonPathListener ) ((JsonPathListener)listener).enterJsonArraySelectionsStep(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JsonPathListener ) ((JsonPathListener)listener).exitJsonArraySelectionsStep(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JsonPathVisitor ) return ((JsonPathVisitor<? extends T>)visitor).visitJsonArraySelectionsStep(this);
			else return visitor.visitChildren(this);
		}
	}

	public final JsonArraySelectionsStepContext jsonArraySelectionsStep() throws RecognitionException {
		JsonArraySelectionsStepContext _localctx = new JsonArraySelectionsStepContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_jsonArraySelectionsStep);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(94);
			jsonArraySlice();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class JsonArraySliceContext extends ParserRuleContext {
		public List<TerminalNode> NATRUAL_INTEGER() { return getTokens(JsonPathParser.NATRUAL_INTEGER); }
		public TerminalNode NATRUAL_INTEGER(int i) {
			return getToken(JsonPathParser.NATRUAL_INTEGER, i);
		}
		public JsonArraySliceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_jsonArraySlice; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JsonPathListener ) ((JsonPathListener)listener).enterJsonArraySlice(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JsonPathListener ) ((JsonPathListener)listener).exitJsonArraySlice(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JsonPathVisitor ) return ((JsonPathVisitor<? extends T>)visitor).visitJsonArraySlice(this);
			else return visitor.visitChildren(this);
		}
	}

	public final JsonArraySliceContext jsonArraySlice() throws RecognitionException {
		JsonArraySliceContext _localctx = new JsonArraySliceContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_jsonArraySlice);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(96);
			match(NATRUAL_INTEGER);
			setState(97);
			match(T__4);
			setState(98);
			match(NATRUAL_INTEGER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 6:
			return jsonCond_sempred((JsonCondContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean jsonCond_sempred(JsonCondContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 1);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\24g\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t\13\4"+
		"\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\3\2\3\2\5\2%\n"+
		"\2\3\3\3\3\3\3\3\4\3\4\3\4\3\5\7\5.\n\5\f\5\16\5\61\13\5\3\6\3\6\3\6\5"+
		"\6\66\n\6\3\6\5\69\n\6\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\7\bE\n"+
		"\b\f\b\16\bH\13\b\3\t\3\t\3\t\5\tM\n\t\3\n\3\n\3\13\3\13\3\f\3\f\3\f\3"+
		"\r\3\r\3\16\3\16\3\16\5\16[\n\16\3\16\3\16\3\17\3\17\3\20\3\20\3\21\3"+
		"\21\3\21\3\21\3\21\2\3\16\22\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \2"+
		"\2\2^\2$\3\2\2\2\4&\3\2\2\2\6)\3\2\2\2\b/\3\2\2\2\n\65\3\2\2\2\f:\3\2"+
		"\2\2\16>\3\2\2\2\20I\3\2\2\2\22N\3\2\2\2\24P\3\2\2\2\26R\3\2\2\2\30U\3"+
		"\2\2\2\32W\3\2\2\2\34^\3\2\2\2\36`\3\2\2\2 b\3\2\2\2\"%\5\4\3\2#%\5\6"+
		"\4\2$\"\3\2\2\2$#\3\2\2\2%\3\3\2\2\2&\'\7\3\2\2\'(\5\b\5\2(\5\3\2\2\2"+
		")*\7\4\2\2*+\5\b\5\2+\7\3\2\2\2,.\5\n\6\2-,\3\2\2\2.\61\3\2\2\2/-\3\2"+
		"\2\2/\60\3\2\2\2\60\t\3\2\2\2\61/\3\2\2\2\62\66\5\20\t\2\63\66\5\32\16"+
		"\2\64\66\5\26\f\2\65\62\3\2\2\2\65\63\3\2\2\2\65\64\3\2\2\2\668\3\2\2"+
		"\2\679\5\f\7\28\67\3\2\2\289\3\2\2\29\13\3\2\2\2:;\7\13\2\2;<\5\16\b\2"+
		"<=\7\f\2\2=\r\3\2\2\2>?\b\b\1\2?@\5\6\4\2@F\3\2\2\2AB\f\3\2\2BC\7\20\2"+
		"\2CE\5\16\b\4DA\3\2\2\2EH\3\2\2\2FD\3\2\2\2FG\3\2\2\2G\17\3\2\2\2HF\3"+
		"\2\2\2IL\7\5\2\2JM\5\22\n\2KM\5\24\13\2LJ\3\2\2\2LK\3\2\2\2M\21\3\2\2"+
		"\2NO\7\17\2\2O\23\3\2\2\2PQ\5\30\r\2Q\25\3\2\2\2RS\7\6\2\2ST\5\30\r\2"+
		"T\27\3\2\2\2UV\7\n\2\2V\31\3\2\2\2WZ\7\r\2\2X[\5\34\17\2Y[\5\36\20\2Z"+
		"X\3\2\2\2ZY\3\2\2\2[\\\3\2\2\2\\]\7\16\2\2]\33\3\2\2\2^_\7\17\2\2_\35"+
		"\3\2\2\2`a\5 \21\2a\37\3\2\2\2bc\7\t\2\2cd\7\7\2\2de\7\t\2\2e!\3\2\2\2"+
		"\t$/\658FLZ";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}