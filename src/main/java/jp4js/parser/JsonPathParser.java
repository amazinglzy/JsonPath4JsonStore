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
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, JSON_STRING=7, NATRUAL_INTEGER=8, 
		IDENTIFIER=9, FILTER_BEGIN=10, FILTER_END=11, ARRAY_BEGIN=12, ARRAY_END=13, 
		WILDCARD=14, LOGIC_AND=15, DIGIT=16, POSITIVEDIGIT=17, LETTER=18, WS=19;
	public static final int
		RULE_jsonBasicPathExpr = 0, RULE_jsonAbsolutePathExpr = 1, RULE_jsonRelativePathExpr = 2, 
		RULE_jsonSteps = 3, RULE_jsonStep = 4, RULE_jsonFilterExpr = 5, RULE_jsonCond = 6, 
		RULE_jsonObjectStep = 7, RULE_jsonObjectWildcardStep = 8, RULE_jsonObjectFieldNameStep = 9, 
		RULE_jsonDescendentStep = 10, RULE_jsonFieldName = 11, RULE_jsonArrayStep = 12, 
		RULE_jsonArrayWildcardStep = 13, RULE_jsonArraySelectionsStep = 14, RULE_jsonArraySelection = 15, 
		RULE_jsonArrayIndex = 16, RULE_jsonArraySlice = 17;
	public static final String[] ruleNames = {
		"jsonBasicPathExpr", "jsonAbsolutePathExpr", "jsonRelativePathExpr", "jsonSteps", 
		"jsonStep", "jsonFilterExpr", "jsonCond", "jsonObjectStep", "jsonObjectWildcardStep", 
		"jsonObjectFieldNameStep", "jsonDescendentStep", "jsonFieldName", "jsonArrayStep", 
		"jsonArrayWildcardStep", "jsonArraySelectionsStep", "jsonArraySelection", 
		"jsonArrayIndex", "jsonArraySlice"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'$'", "'@'", "'.'", "'..'", "','", "':'", null, null, null, "'[?('", 
		"')]'", "'['", "']'", "'*'", "'&&'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, "JSON_STRING", "NATRUAL_INTEGER", 
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
			setState(38);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__0:
				enterOuterAlt(_localctx, 1);
				{
				setState(36);
				jsonAbsolutePathExpr();
				}
				break;
			case T__1:
				enterOuterAlt(_localctx, 2);
				{
				setState(37);
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
			setState(40);
			match(T__0);
			setState(41);
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
			setState(43);
			match(T__1);
			setState(44);
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
			setState(49);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(46);
					jsonStep();
					}
					} 
				}
				setState(51);
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
			setState(55);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__2:
				{
				setState(52);
				jsonObjectStep();
				}
				break;
			case ARRAY_BEGIN:
				{
				setState(53);
				jsonArrayStep();
				}
				break;
			case T__3:
				{
				setState(54);
				jsonDescendentStep();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(58);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				{
				setState(57);
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
			setState(60);
			match(FILTER_BEGIN);
			setState(61);
			jsonCond(0);
			setState(62);
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

			setState(65);
			jsonRelativePathExpr();
			}
			_ctx.stop = _input.LT(-1);
			setState(72);
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
					setState(67);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(68);
					match(LOGIC_AND);
					setState(69);
					jsonCond(2);
					}
					} 
				}
				setState(74);
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
			setState(75);
			match(T__2);
			setState(78);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case WILDCARD:
				{
				setState(76);
				jsonObjectWildcardStep();
				}
				break;
			case IDENTIFIER:
				{
				setState(77);
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
			setState(80);
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
			setState(82);
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
			setState(84);
			match(T__3);
			setState(85);
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
			setState(87);
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
			setState(89);
			match(ARRAY_BEGIN);
			setState(92);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case WILDCARD:
				{
				setState(90);
				jsonArrayWildcardStep();
				}
				break;
			case NATRUAL_INTEGER:
				{
				setState(91);
				jsonArraySelectionsStep();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(94);
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
			setState(96);
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
		public List<JsonArraySelectionContext> jsonArraySelection() {
			return getRuleContexts(JsonArraySelectionContext.class);
		}
		public JsonArraySelectionContext jsonArraySelection(int i) {
			return getRuleContext(JsonArraySelectionContext.class,i);
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
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(98);
			jsonArraySelection();
			setState(103);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__4) {
				{
				{
				setState(99);
				match(T__4);
				setState(100);
				jsonArraySelection();
				}
				}
				setState(105);
				_errHandler.sync(this);
				_la = _input.LA(1);
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

	public static class JsonArraySelectionContext extends ParserRuleContext {
		public JsonArrayIndexContext jsonArrayIndex() {
			return getRuleContext(JsonArrayIndexContext.class,0);
		}
		public JsonArraySliceContext jsonArraySlice() {
			return getRuleContext(JsonArraySliceContext.class,0);
		}
		public JsonArraySelectionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_jsonArraySelection; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JsonPathListener ) ((JsonPathListener)listener).enterJsonArraySelection(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JsonPathListener ) ((JsonPathListener)listener).exitJsonArraySelection(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JsonPathVisitor ) return ((JsonPathVisitor<? extends T>)visitor).visitJsonArraySelection(this);
			else return visitor.visitChildren(this);
		}
	}

	public final JsonArraySelectionContext jsonArraySelection() throws RecognitionException {
		JsonArraySelectionContext _localctx = new JsonArraySelectionContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_jsonArraySelection);
		try {
			setState(108);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(106);
				jsonArrayIndex();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(107);
				jsonArraySlice();
				}
				break;
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

	public static class JsonArrayIndexContext extends ParserRuleContext {
		public TerminalNode NATRUAL_INTEGER() { return getToken(JsonPathParser.NATRUAL_INTEGER, 0); }
		public JsonArrayIndexContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_jsonArrayIndex; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JsonPathListener ) ((JsonPathListener)listener).enterJsonArrayIndex(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JsonPathListener ) ((JsonPathListener)listener).exitJsonArrayIndex(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JsonPathVisitor ) return ((JsonPathVisitor<? extends T>)visitor).visitJsonArrayIndex(this);
			else return visitor.visitChildren(this);
		}
	}

	public final JsonArrayIndexContext jsonArrayIndex() throws RecognitionException {
		JsonArrayIndexContext _localctx = new JsonArrayIndexContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_jsonArrayIndex);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(110);
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
		enterRule(_localctx, 34, RULE_jsonArraySlice);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(112);
			match(NATRUAL_INTEGER);
			setState(113);
			match(T__5);
			setState(114);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\25w\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t\13\4"+
		"\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22\4\23"+
		"\t\23\3\2\3\2\5\2)\n\2\3\3\3\3\3\3\3\4\3\4\3\4\3\5\7\5\62\n\5\f\5\16\5"+
		"\65\13\5\3\6\3\6\3\6\5\6:\n\6\3\6\5\6=\n\6\3\7\3\7\3\7\3\7\3\b\3\b\3\b"+
		"\3\b\3\b\3\b\7\bI\n\b\f\b\16\bL\13\b\3\t\3\t\3\t\5\tQ\n\t\3\n\3\n\3\13"+
		"\3\13\3\f\3\f\3\f\3\r\3\r\3\16\3\16\3\16\5\16_\n\16\3\16\3\16\3\17\3\17"+
		"\3\20\3\20\3\20\7\20h\n\20\f\20\16\20k\13\20\3\21\3\21\5\21o\n\21\3\22"+
		"\3\22\3\23\3\23\3\23\3\23\3\23\2\3\16\24\2\4\6\b\n\f\16\20\22\24\26\30"+
		"\32\34\36 \"$\2\2\2n\2(\3\2\2\2\4*\3\2\2\2\6-\3\2\2\2\b\63\3\2\2\2\n9"+
		"\3\2\2\2\f>\3\2\2\2\16B\3\2\2\2\20M\3\2\2\2\22R\3\2\2\2\24T\3\2\2\2\26"+
		"V\3\2\2\2\30Y\3\2\2\2\32[\3\2\2\2\34b\3\2\2\2\36d\3\2\2\2 n\3\2\2\2\""+
		"p\3\2\2\2$r\3\2\2\2&)\5\4\3\2\')\5\6\4\2(&\3\2\2\2(\'\3\2\2\2)\3\3\2\2"+
		"\2*+\7\3\2\2+,\5\b\5\2,\5\3\2\2\2-.\7\4\2\2./\5\b\5\2/\7\3\2\2\2\60\62"+
		"\5\n\6\2\61\60\3\2\2\2\62\65\3\2\2\2\63\61\3\2\2\2\63\64\3\2\2\2\64\t"+
		"\3\2\2\2\65\63\3\2\2\2\66:\5\20\t\2\67:\5\32\16\28:\5\26\f\29\66\3\2\2"+
		"\29\67\3\2\2\298\3\2\2\2:<\3\2\2\2;=\5\f\7\2<;\3\2\2\2<=\3\2\2\2=\13\3"+
		"\2\2\2>?\7\f\2\2?@\5\16\b\2@A\7\r\2\2A\r\3\2\2\2BC\b\b\1\2CD\5\6\4\2D"+
		"J\3\2\2\2EF\f\3\2\2FG\7\21\2\2GI\5\16\b\4HE\3\2\2\2IL\3\2\2\2JH\3\2\2"+
		"\2JK\3\2\2\2K\17\3\2\2\2LJ\3\2\2\2MP\7\5\2\2NQ\5\22\n\2OQ\5\24\13\2PN"+
		"\3\2\2\2PO\3\2\2\2Q\21\3\2\2\2RS\7\20\2\2S\23\3\2\2\2TU\5\30\r\2U\25\3"+
		"\2\2\2VW\7\6\2\2WX\5\30\r\2X\27\3\2\2\2YZ\7\13\2\2Z\31\3\2\2\2[^\7\16"+
		"\2\2\\_\5\34\17\2]_\5\36\20\2^\\\3\2\2\2^]\3\2\2\2_`\3\2\2\2`a\7\17\2"+
		"\2a\33\3\2\2\2bc\7\20\2\2c\35\3\2\2\2di\5 \21\2ef\7\7\2\2fh\5 \21\2ge"+
		"\3\2\2\2hk\3\2\2\2ig\3\2\2\2ij\3\2\2\2j\37\3\2\2\2ki\3\2\2\2lo\5\"\22"+
		"\2mo\5$\23\2nl\3\2\2\2nm\3\2\2\2o!\3\2\2\2pq\7\n\2\2q#\3\2\2\2rs\7\n\2"+
		"\2st\7\b\2\2tu\7\n\2\2u%\3\2\2\2\13(\639<JP^in";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}