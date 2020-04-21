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
		RULE_jsonFieldName = 6, RULE_jsonArrayRange = 7;
	public static final String[] ruleNames = {
		"jsonBasicPathExpr", "jsonAbsolutePathExpr", "jsonNonfunctionSteps", "jsonObjectStep", 
		"jsonDescendentStep", "jsonArrayStep", "jsonFieldName", "jsonArrayRange"
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
			setState(16);
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
			setState(18);
			match(T__0);
			setState(19);
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
			setState(26);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << T__2) | (1L << T__3))) != 0)) {
				{
				setState(24);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__1:
					{
					setState(21);
					jsonObjectStep();
					}
					break;
				case T__3:
					{
					setState(22);
					jsonArrayStep();
					}
					break;
				case T__2:
					{
					setState(23);
					jsonDescendentStep();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(28);
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
			setState(29);
			match(T__1);
			setState(32);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case WILDCARD:
				{
				setState(30);
				match(WILDCARD);
				}
				break;
			case JSON_STRING:
			case IDENTIFIER:
				{
				setState(31);
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
			setState(34);
			match(T__2);
			setState(35);
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
		public List<TerminalNode> NATRUAL_INTEGER() { return getTokens(JsonPathParser.NATRUAL_INTEGER); }
		public TerminalNode NATRUAL_INTEGER(int i) {
			return getToken(JsonPathParser.NATRUAL_INTEGER, i);
		}
		public List<JsonArrayRangeContext> jsonArrayRange() {
			return getRuleContexts(JsonArrayRangeContext.class);
		}
		public JsonArrayRangeContext jsonArrayRange(int i) {
			return getRuleContext(JsonArrayRangeContext.class,i);
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
			setState(37);
			match(T__3);
			setState(53);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case WILDCARD:
				{
				setState(38);
				match(WILDCARD);
				}
				break;
			case NATRUAL_INTEGER:
				{
				{
				setState(41);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
				case 1:
					{
					setState(39);
					match(NATRUAL_INTEGER);
					}
					break;
				case 2:
					{
					setState(40);
					jsonArrayRange();
					}
					break;
				}
				setState(50);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__4) {
					{
					{
					setState(43);
					match(T__4);
					setState(46);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
					case 1:
						{
						setState(44);
						match(NATRUAL_INTEGER);
						}
						break;
					case 2:
						{
						setState(45);
						jsonArrayRange();
						}
						break;
					}
					}
					}
					setState(52);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(55);
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
		public TerminalNode JSON_STRING() { return getToken(JsonPathParser.JSON_STRING, 0); }
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
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(57);
			_la = _input.LA(1);
			if ( !(_la==JSON_STRING || _la==IDENTIFIER) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
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

	public static class JsonArrayRangeContext extends ParserRuleContext {
		public List<TerminalNode> NATRUAL_INTEGER() { return getTokens(JsonPathParser.NATRUAL_INTEGER); }
		public TerminalNode NATRUAL_INTEGER(int i) {
			return getToken(JsonPathParser.NATRUAL_INTEGER, i);
		}
		public JsonArrayRangeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_jsonArrayRange; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JsonPathListener ) ((JsonPathListener)listener).enterJsonArrayRange(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JsonPathListener ) ((JsonPathListener)listener).exitJsonArrayRange(this);
		}
	}

	public final JsonArrayRangeContext jsonArrayRange() throws RecognitionException {
		JsonArrayRangeContext _localctx = new JsonArrayRangeContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_jsonArrayRange);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(59);
			match(NATRUAL_INTEGER);
			setState(60);
			match(T__6);
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

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\21B\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\3\2\3\2\3\3\3\3\3\3"+
		"\3\4\3\4\3\4\7\4\33\n\4\f\4\16\4\36\13\4\3\5\3\5\3\5\5\5#\n\5\3\6\3\6"+
		"\3\6\3\7\3\7\3\7\3\7\5\7,\n\7\3\7\3\7\3\7\5\7\61\n\7\7\7\63\n\7\f\7\16"+
		"\7\66\13\7\5\78\n\7\3\7\3\7\3\b\3\b\3\t\3\t\3\t\3\t\3\t\2\2\n\2\4\6\b"+
		"\n\f\16\20\2\3\4\2\n\n\f\f\2A\2\22\3\2\2\2\4\24\3\2\2\2\6\34\3\2\2\2\b"+
		"\37\3\2\2\2\n$\3\2\2\2\f\'\3\2\2\2\16;\3\2\2\2\20=\3\2\2\2\22\23\5\4\3"+
		"\2\23\3\3\2\2\2\24\25\7\3\2\2\25\26\5\6\4\2\26\5\3\2\2\2\27\33\5\b\5\2"+
		"\30\33\5\f\7\2\31\33\5\n\6\2\32\27\3\2\2\2\32\30\3\2\2\2\32\31\3\2\2\2"+
		"\33\36\3\2\2\2\34\32\3\2\2\2\34\35\3\2\2\2\35\7\3\2\2\2\36\34\3\2\2\2"+
		"\37\"\7\4\2\2 #\7\r\2\2!#\5\16\b\2\" \3\2\2\2\"!\3\2\2\2#\t\3\2\2\2$%"+
		"\7\5\2\2%&\5\16\b\2&\13\3\2\2\2\'\67\7\6\2\2(8\7\r\2\2),\7\13\2\2*,\5"+
		"\20\t\2+)\3\2\2\2+*\3\2\2\2,\64\3\2\2\2-\60\7\7\2\2.\61\7\13\2\2/\61\5"+
		"\20\t\2\60.\3\2\2\2\60/\3\2\2\2\61\63\3\2\2\2\62-\3\2\2\2\63\66\3\2\2"+
		"\2\64\62\3\2\2\2\64\65\3\2\2\2\658\3\2\2\2\66\64\3\2\2\2\67(\3\2\2\2\67"+
		"+\3\2\2\289\3\2\2\29:\7\b\2\2:\r\3\2\2\2;<\t\2\2\2<\17\3\2\2\2=>\7\13"+
		"\2\2>?\7\t\2\2?@\7\13\2\2@\21\3\2\2\2\t\32\34\"+\60\64\67";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}