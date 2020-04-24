// Generated from /Users/owly/Desktop/2. PieceWorks/2. Java/JsonPath4JsonStore/src/main/antlr/JsonPath.g4 by ANTLR 4.7.1
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
	static { RuntimeMetaData.checkVersion("4.7.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, JSON_STRING=8, 
		NATRUAL_INTEGER=9, IDENTIFIER=10, WILDCARD=11, DIGIT=12, POSITIVEDIGIT=13, 
		LETTER=14, WS=15;
	public static final int
		RULE_jsonBasicPathExpr = 0, RULE_jsonAbsolutePathExpr = 1, RULE_jsonSteps = 2, 
		RULE_jsonObjectStep = 3, RULE_jsonObjectWildcardStep = 4, RULE_jsonObjectFieldNameStep = 5, 
		RULE_jsonDescendentStep = 6, RULE_jsonFieldName = 7, RULE_jsonArrayStep = 8, 
		RULE_jsonArrayWildcardStep = 9, RULE_jsonArraySelectionsStep = 10, RULE_jsonArraySelection = 11, 
		RULE_jsonArrayIndex = 12, RULE_jsonArraySlice = 13;
	public static final String[] ruleNames = {
		"jsonBasicPathExpr", "jsonAbsolutePathExpr", "jsonSteps", "jsonObjectStep", 
		"jsonObjectWildcardStep", "jsonObjectFieldNameStep", "jsonDescendentStep", 
		"jsonFieldName", "jsonArrayStep", "jsonArrayWildcardStep", "jsonArraySelectionsStep", 
		"jsonArraySelection", "jsonArrayIndex", "jsonArraySlice"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'$'", "'.'", "'..'", "'['", "']'", "','", "':'", null, null, null, 
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
	}

	public final JsonBasicPathExprContext jsonBasicPathExpr() throws RecognitionException {
		JsonBasicPathExprContext _localctx = new JsonBasicPathExprContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_jsonBasicPathExpr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(28);
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
		public JsonStepsContext jsonSteps() {
			return getRuleContext(JsonStepsContext.class,0);
		}
		public JsonAbsolutePathExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_jsonAbsolutePathExpr; }
	}

	public final JsonAbsolutePathExprContext jsonAbsolutePathExpr() throws RecognitionException {
		JsonAbsolutePathExprContext _localctx = new JsonAbsolutePathExprContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_jsonAbsolutePathExpr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(30);
			match(T__0);
			setState(31);
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
		public JsonStepsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_jsonSteps; }
	}

	public final JsonStepsContext jsonSteps() throws RecognitionException {
		JsonStepsContext _localctx = new JsonStepsContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_jsonSteps);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(38);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << T__2) | (1L << T__3))) != 0)) {
				{
				setState(36);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__1:
					{
					setState(33);
					jsonObjectStep();
					}
					break;
				case T__3:
					{
					setState(34);
					jsonArrayStep();
					}
					break;
				case T__2:
					{
					setState(35);
					jsonDescendentStep();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(40);
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
	}

	public final JsonObjectStepContext jsonObjectStep() throws RecognitionException {
		JsonObjectStepContext _localctx = new JsonObjectStepContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_jsonObjectStep);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(41);
			match(T__1);
			setState(44);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case WILDCARD:
				{
				setState(42);
				jsonObjectWildcardStep();
				}
				break;
			case IDENTIFIER:
				{
				setState(43);
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
	}

	public final JsonObjectWildcardStepContext jsonObjectWildcardStep() throws RecognitionException {
		JsonObjectWildcardStepContext _localctx = new JsonObjectWildcardStepContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_jsonObjectWildcardStep);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(46);
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
	}

	public final JsonObjectFieldNameStepContext jsonObjectFieldNameStep() throws RecognitionException {
		JsonObjectFieldNameStepContext _localctx = new JsonObjectFieldNameStepContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_jsonObjectFieldNameStep);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(48);
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
	}

	public final JsonDescendentStepContext jsonDescendentStep() throws RecognitionException {
		JsonDescendentStepContext _localctx = new JsonDescendentStepContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_jsonDescendentStep);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(50);
			match(T__2);
			setState(51);
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
	}

	public final JsonFieldNameContext jsonFieldName() throws RecognitionException {
		JsonFieldNameContext _localctx = new JsonFieldNameContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_jsonFieldName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(53);
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
	}

	public final JsonArrayStepContext jsonArrayStep() throws RecognitionException {
		JsonArrayStepContext _localctx = new JsonArrayStepContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_jsonArrayStep);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(55);
			match(T__3);
			setState(58);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case WILDCARD:
				{
				setState(56);
				jsonArrayWildcardStep();
				}
				break;
			case NATRUAL_INTEGER:
				{
				setState(57);
				jsonArraySelectionsStep();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(60);
			match(T__4);
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
	}

	public final JsonArrayWildcardStepContext jsonArrayWildcardStep() throws RecognitionException {
		JsonArrayWildcardStepContext _localctx = new JsonArrayWildcardStepContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_jsonArrayWildcardStep);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(62);
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
	}

	public final JsonArraySelectionsStepContext jsonArraySelectionsStep() throws RecognitionException {
		JsonArraySelectionsStepContext _localctx = new JsonArraySelectionsStepContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_jsonArraySelectionsStep);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(64);
			jsonArraySelection();
			setState(69);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__5) {
				{
				{
				setState(65);
				match(T__5);
				setState(66);
				jsonArraySelection();
				}
				}
				setState(71);
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
	}

	public final JsonArraySelectionContext jsonArraySelection() throws RecognitionException {
		JsonArraySelectionContext _localctx = new JsonArraySelectionContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_jsonArraySelection);
		try {
			setState(74);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(72);
				jsonArrayIndex();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(73);
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
	}

	public final JsonArrayIndexContext jsonArrayIndex() throws RecognitionException {
		JsonArrayIndexContext _localctx = new JsonArrayIndexContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_jsonArrayIndex);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(76);
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
	}

	public final JsonArraySliceContext jsonArraySlice() throws RecognitionException {
		JsonArraySliceContext _localctx = new JsonArraySliceContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_jsonArraySlice);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(78);
			match(NATRUAL_INTEGER);
			setState(79);
			match(T__6);
			setState(80);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\21U\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t\13\4"+
		"\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\3\2\3\2\3\3\3\3\3\3\3\4\3\4\3\4\7\4"+
		"\'\n\4\f\4\16\4*\13\4\3\5\3\5\3\5\5\5/\n\5\3\6\3\6\3\7\3\7\3\b\3\b\3\b"+
		"\3\t\3\t\3\n\3\n\3\n\5\n=\n\n\3\n\3\n\3\13\3\13\3\f\3\f\3\f\7\fF\n\f\f"+
		"\f\16\fI\13\f\3\r\3\r\5\rM\n\r\3\16\3\16\3\17\3\17\3\17\3\17\3\17\2\2"+
		"\20\2\4\6\b\n\f\16\20\22\24\26\30\32\34\2\2\2M\2\36\3\2\2\2\4 \3\2\2\2"+
		"\6(\3\2\2\2\b+\3\2\2\2\n\60\3\2\2\2\f\62\3\2\2\2\16\64\3\2\2\2\20\67\3"+
		"\2\2\2\229\3\2\2\2\24@\3\2\2\2\26B\3\2\2\2\30L\3\2\2\2\32N\3\2\2\2\34"+
		"P\3\2\2\2\36\37\5\4\3\2\37\3\3\2\2\2 !\7\3\2\2!\"\5\6\4\2\"\5\3\2\2\2"+
		"#\'\5\b\5\2$\'\5\22\n\2%\'\5\16\b\2&#\3\2\2\2&$\3\2\2\2&%\3\2\2\2\'*\3"+
		"\2\2\2(&\3\2\2\2()\3\2\2\2)\7\3\2\2\2*(\3\2\2\2+.\7\4\2\2,/\5\n\6\2-/"+
		"\5\f\7\2.,\3\2\2\2.-\3\2\2\2/\t\3\2\2\2\60\61\7\r\2\2\61\13\3\2\2\2\62"+
		"\63\5\20\t\2\63\r\3\2\2\2\64\65\7\5\2\2\65\66\5\20\t\2\66\17\3\2\2\2\67"+
		"8\7\f\2\28\21\3\2\2\29<\7\6\2\2:=\5\24\13\2;=\5\26\f\2<:\3\2\2\2<;\3\2"+
		"\2\2=>\3\2\2\2>?\7\7\2\2?\23\3\2\2\2@A\7\r\2\2A\25\3\2\2\2BG\5\30\r\2"+
		"CD\7\b\2\2DF\5\30\r\2EC\3\2\2\2FI\3\2\2\2GE\3\2\2\2GH\3\2\2\2H\27\3\2"+
		"\2\2IG\3\2\2\2JM\5\32\16\2KM\5\34\17\2LJ\3\2\2\2LK\3\2\2\2M\31\3\2\2\2"+
		"NO\7\13\2\2O\33\3\2\2\2PQ\7\13\2\2QR\7\t\2\2RS\7\13\2\2S\35\3\2\2\2\b"+
		"&(.<GL";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}