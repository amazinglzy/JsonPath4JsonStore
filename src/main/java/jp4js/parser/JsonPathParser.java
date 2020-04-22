// Generated from jp4js/parser/JsonPath.g4 by ANTLR 4.7
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
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, JSON_STRING=8, 
		NATRUAL_INTEGER=9, IDENTIFIER=10, WILDCARD=11, DIGIT=12, POSITIVEDIGIT=13, 
		LETTER=14, WS=15;
	public static final int
		RULE_jsonBasicPathExpr = 0, RULE_jsonAbsolutePathExpr = 1, RULE_jsonNonfunctionSteps = 2, 
		RULE_jsonObjectStep = 3, RULE_jsonDescendentStep = 4, RULE_jsonArrayStep = 5, 
		RULE_jsonFieldName = 6, RULE_jsonArraySelection = 7, RULE_jsonArrayIndex = 8, 
		RULE_jsonArraySlice = 9;
	public static final String[] ruleNames = {
		"jsonBasicPathExpr", "jsonAbsolutePathExpr", "jsonNonfunctionSteps", "jsonObjectStep", 
		"jsonDescendentStep", "jsonArrayStep", "jsonFieldName", "jsonArraySelection", 
		"jsonArrayIndex", "jsonArraySlice"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'$'", "'.'", "'..'", "'['", "','", "']'", "':'", null, null, null, 
		"'*'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, "JSON_STRING", "NATRUAL_INTEGER", 
		"IDENTIFIER", "WILDCARD", "DIGIT", "POSITIVEDIGIT", "LETTER", "WS"
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
	}

	public final JsonBasicPathExprContext jsonBasicPathExpr() throws RecognitionException {
		JsonBasicPathExprContext _localctx = new JsonBasicPathExprContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_jsonBasicPathExpr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(20);
			jsonAbsolutePathExpr();
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
		public JsonNonfunctionStepsContext jsonNonfunctionSteps() {
			return getRuleContext(JsonNonfunctionStepsContext.class,0);
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
	}

	public final JsonAbsolutePathExprContext jsonAbsolutePathExpr() throws RecognitionException {
		JsonAbsolutePathExprContext _localctx = new JsonAbsolutePathExprContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_jsonAbsolutePathExpr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(22);
			match(T__0);
			setState(23);
			jsonNonfunctionSteps();
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

	public static class JsonNonfunctionStepsContext extends ParserRuleContext {
		public List<JsonObjectStepContext> jsonObjectStep() {
			return getRuleContexts(JsonObjectStepContext.class);
		}
		public JsonObjectStepContext jsonObjectStep(int i) {
			return getRuleContext(JsonObjectStepContext.class,i);
		}
		public List<JsonArrayStepContext> jsonArrayStep() {
			return getRuleContexts(JsonArrayStepContext.class);
		}
		public JsonArrayStepContext jsonArrayStep(int i) {
			return getRuleContext(JsonArrayStepContext.class,i);
		}
		public List<JsonDescendentStepContext> jsonDescendentStep() {
			return getRuleContexts(JsonDescendentStepContext.class);
		}
		public JsonDescendentStepContext jsonDescendentStep(int i) {
			return getRuleContext(JsonDescendentStepContext.class,i);
		}
		public JsonNonfunctionStepsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_jsonNonfunctionSteps; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JsonPathListener ) ((JsonPathListener)listener).enterJsonNonfunctionSteps(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JsonPathListener ) ((JsonPathListener)listener).exitJsonNonfunctionSteps(this);
		}
	}

	public final JsonNonfunctionStepsContext jsonNonfunctionSteps() throws RecognitionException {
		JsonNonfunctionStepsContext _localctx = new JsonNonfunctionStepsContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_jsonNonfunctionSteps);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(30);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << T__2) | (1L << T__3))) != 0)) {
				{
				setState(28);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__1:
					{
					setState(25);
					jsonObjectStep();
					}
					break;
				case T__3:
					{
					setState(26);
					jsonArrayStep();
					}
					break;
				case T__2:
					{
					setState(27);
					jsonDescendentStep();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(32);
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

	public static class JsonObjectStepContext extends ParserRuleContext {
		public TerminalNode WILDCARD() { return getToken(JsonPathParser.WILDCARD, 0); }
		public JsonFieldNameContext jsonFieldName() {
			return getRuleContext(JsonFieldNameContext.class,0);
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
	}

	public final JsonObjectStepContext jsonObjectStep() throws RecognitionException {
		JsonObjectStepContext _localctx = new JsonObjectStepContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_jsonObjectStep);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(33);
			match(T__1);
			setState(36);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case WILDCARD:
				{
				setState(34);
				match(WILDCARD);
				}
				break;
			case IDENTIFIER:
				{
				setState(35);
				jsonFieldName();
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
	}

	public final JsonDescendentStepContext jsonDescendentStep() throws RecognitionException {
		JsonDescendentStepContext _localctx = new JsonDescendentStepContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_jsonDescendentStep);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(38);
			match(T__2);
			setState(39);
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

	public static class JsonArrayStepContext extends ParserRuleContext {
		public TerminalNode WILDCARD() { return getToken(JsonPathParser.WILDCARD, 0); }
		public List<JsonArraySelectionContext> jsonArraySelection() {
			return getRuleContexts(JsonArraySelectionContext.class);
		}
		public JsonArraySelectionContext jsonArraySelection(int i) {
			return getRuleContext(JsonArraySelectionContext.class,i);
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
	}

	public final JsonArrayStepContext jsonArrayStep() throws RecognitionException {
		JsonArrayStepContext _localctx = new JsonArrayStepContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_jsonArrayStep);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(41);
			match(T__3);
			setState(51);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case WILDCARD:
				{
				setState(42);
				match(WILDCARD);
				}
				break;
			case NATRUAL_INTEGER:
				{
				{
				setState(43);
				jsonArraySelection();
				setState(48);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__4) {
					{
					{
					setState(44);
					match(T__4);
					setState(45);
					jsonArraySelection();
					}
					}
					setState(50);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(53);
			match(T__5);
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
	}

	public final JsonFieldNameContext jsonFieldName() throws RecognitionException {
		JsonFieldNameContext _localctx = new JsonFieldNameContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_jsonFieldName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(55);
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
	}

	public final JsonArraySelectionContext jsonArraySelection() throws RecognitionException {
		JsonArraySelectionContext _localctx = new JsonArraySelectionContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_jsonArraySelection);
		try {
			setState(59);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(57);
				jsonArrayIndex();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(58);
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
	}

	public final JsonArrayIndexContext jsonArrayIndex() throws RecognitionException {
		JsonArrayIndexContext _localctx = new JsonArrayIndexContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_jsonArrayIndex);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(61);
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
	}

	public final JsonArraySliceContext jsonArraySlice() throws RecognitionException {
		JsonArraySliceContext _localctx = new JsonArraySliceContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_jsonArraySlice);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(63);
			match(NATRUAL_INTEGER);
			setState(64);
			match(T__6);
			setState(65);
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

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\21F\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t\13\3"+
		"\2\3\2\3\3\3\3\3\3\3\4\3\4\3\4\7\4\37\n\4\f\4\16\4\"\13\4\3\5\3\5\3\5"+
		"\5\5\'\n\5\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\7\7\61\n\7\f\7\16\7\64\13\7"+
		"\5\7\66\n\7\3\7\3\7\3\b\3\b\3\t\3\t\5\t>\n\t\3\n\3\n\3\13\3\13\3\13\3"+
		"\13\3\13\2\2\f\2\4\6\b\n\f\16\20\22\24\2\2\2B\2\26\3\2\2\2\4\30\3\2\2"+
		"\2\6 \3\2\2\2\b#\3\2\2\2\n(\3\2\2\2\f+\3\2\2\2\169\3\2\2\2\20=\3\2\2\2"+
		"\22?\3\2\2\2\24A\3\2\2\2\26\27\5\4\3\2\27\3\3\2\2\2\30\31\7\3\2\2\31\32"+
		"\5\6\4\2\32\5\3\2\2\2\33\37\5\b\5\2\34\37\5\f\7\2\35\37\5\n\6\2\36\33"+
		"\3\2\2\2\36\34\3\2\2\2\36\35\3\2\2\2\37\"\3\2\2\2 \36\3\2\2\2 !\3\2\2"+
		"\2!\7\3\2\2\2\" \3\2\2\2#&\7\4\2\2$\'\7\r\2\2%\'\5\16\b\2&$\3\2\2\2&%"+
		"\3\2\2\2\'\t\3\2\2\2()\7\5\2\2)*\5\16\b\2*\13\3\2\2\2+\65\7\6\2\2,\66"+
		"\7\r\2\2-\62\5\20\t\2./\7\7\2\2/\61\5\20\t\2\60.\3\2\2\2\61\64\3\2\2\2"+
		"\62\60\3\2\2\2\62\63\3\2\2\2\63\66\3\2\2\2\64\62\3\2\2\2\65,\3\2\2\2\65"+
		"-\3\2\2\2\66\67\3\2\2\2\678\7\b\2\28\r\3\2\2\29:\7\f\2\2:\17\3\2\2\2;"+
		">\5\22\n\2<>\5\24\13\2=;\3\2\2\2=<\3\2\2\2>\21\3\2\2\2?@\7\13\2\2@\23"+
		"\3\2\2\2AB\7\13\2\2BC\7\t\2\2CD\7\13\2\2D\25\3\2\2\2\b\36 &\62\65=";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}