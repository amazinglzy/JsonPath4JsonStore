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
		RULE_json_basic_path_expr = 0, RULE_json_absolute_path_expr = 1, RULE_json_nonfunction_steps = 2, 
		RULE_json_object_step = 3, RULE_json_descendent_step = 4, RULE_json_array_step = 5, 
		RULE_json_field_name = 6, RULE_json_array_range = 7;
	public static final String[] ruleNames = {
		"json_basic_path_expr", "json_absolute_path_expr", "json_nonfunction_steps", 
		"json_object_step", "json_descendent_step", "json_array_step", "json_field_name", 
		"json_array_range"
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
	public static class Json_basic_path_exprContext extends ParserRuleContext {
		public Json_absolute_path_exprContext json_absolute_path_expr() {
			return getRuleContext(Json_absolute_path_exprContext.class,0);
		}
		public Json_basic_path_exprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_json_basic_path_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JsonPathListener ) ((JsonPathListener)listener).enterJson_basic_path_expr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JsonPathListener ) ((JsonPathListener)listener).exitJson_basic_path_expr(this);
		}
	}

	public final Json_basic_path_exprContext json_basic_path_expr() throws RecognitionException {
		Json_basic_path_exprContext _localctx = new Json_basic_path_exprContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_json_basic_path_expr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(16);
			json_absolute_path_expr();
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

	public static class Json_absolute_path_exprContext extends ParserRuleContext {
		public Json_nonfunction_stepsContext json_nonfunction_steps() {
			return getRuleContext(Json_nonfunction_stepsContext.class,0);
		}
		public Json_absolute_path_exprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_json_absolute_path_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JsonPathListener ) ((JsonPathListener)listener).enterJson_absolute_path_expr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JsonPathListener ) ((JsonPathListener)listener).exitJson_absolute_path_expr(this);
		}
	}

	public final Json_absolute_path_exprContext json_absolute_path_expr() throws RecognitionException {
		Json_absolute_path_exprContext _localctx = new Json_absolute_path_exprContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_json_absolute_path_expr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(18);
			match(T__0);
			setState(19);
			json_nonfunction_steps();
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

	public static class Json_nonfunction_stepsContext extends ParserRuleContext {
		public List<Json_object_stepContext> json_object_step() {
			return getRuleContexts(Json_object_stepContext.class);
		}
		public Json_object_stepContext json_object_step(int i) {
			return getRuleContext(Json_object_stepContext.class,i);
		}
		public List<Json_array_stepContext> json_array_step() {
			return getRuleContexts(Json_array_stepContext.class);
		}
		public Json_array_stepContext json_array_step(int i) {
			return getRuleContext(Json_array_stepContext.class,i);
		}
		public List<Json_descendent_stepContext> json_descendent_step() {
			return getRuleContexts(Json_descendent_stepContext.class);
		}
		public Json_descendent_stepContext json_descendent_step(int i) {
			return getRuleContext(Json_descendent_stepContext.class,i);
		}
		public Json_nonfunction_stepsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_json_nonfunction_steps; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JsonPathListener ) ((JsonPathListener)listener).enterJson_nonfunction_steps(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JsonPathListener ) ((JsonPathListener)listener).exitJson_nonfunction_steps(this);
		}
	}

	public final Json_nonfunction_stepsContext json_nonfunction_steps() throws RecognitionException {
		Json_nonfunction_stepsContext _localctx = new Json_nonfunction_stepsContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_json_nonfunction_steps);
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
					json_object_step();
					}
					break;
				case T__3:
					{
					setState(22);
					json_array_step();
					}
					break;
				case T__2:
					{
					setState(23);
					json_descendent_step();
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

	public static class Json_object_stepContext extends ParserRuleContext {
		public TerminalNode WILDCARD() { return getToken(JsonPathParser.WILDCARD, 0); }
		public Json_field_nameContext json_field_name() {
			return getRuleContext(Json_field_nameContext.class,0);
		}
		public Json_object_stepContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_json_object_step; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JsonPathListener ) ((JsonPathListener)listener).enterJson_object_step(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JsonPathListener ) ((JsonPathListener)listener).exitJson_object_step(this);
		}
	}

	public final Json_object_stepContext json_object_step() throws RecognitionException {
		Json_object_stepContext _localctx = new Json_object_stepContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_json_object_step);
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
				json_field_name();
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

	public static class Json_descendent_stepContext extends ParserRuleContext {
		public Json_field_nameContext json_field_name() {
			return getRuleContext(Json_field_nameContext.class,0);
		}
		public Json_descendent_stepContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_json_descendent_step; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JsonPathListener ) ((JsonPathListener)listener).enterJson_descendent_step(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JsonPathListener ) ((JsonPathListener)listener).exitJson_descendent_step(this);
		}
	}

	public final Json_descendent_stepContext json_descendent_step() throws RecognitionException {
		Json_descendent_stepContext _localctx = new Json_descendent_stepContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_json_descendent_step);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(34);
			match(T__2);
			setState(35);
			json_field_name();
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

	public static class Json_array_stepContext extends ParserRuleContext {
		public TerminalNode WILDCARD() { return getToken(JsonPathParser.WILDCARD, 0); }
		public List<TerminalNode> NATRUAL_INTEGER() { return getTokens(JsonPathParser.NATRUAL_INTEGER); }
		public TerminalNode NATRUAL_INTEGER(int i) {
			return getToken(JsonPathParser.NATRUAL_INTEGER, i);
		}
		public List<Json_array_rangeContext> json_array_range() {
			return getRuleContexts(Json_array_rangeContext.class);
		}
		public Json_array_rangeContext json_array_range(int i) {
			return getRuleContext(Json_array_rangeContext.class,i);
		}
		public Json_array_stepContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_json_array_step; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JsonPathListener ) ((JsonPathListener)listener).enterJson_array_step(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JsonPathListener ) ((JsonPathListener)listener).exitJson_array_step(this);
		}
	}

	public final Json_array_stepContext json_array_step() throws RecognitionException {
		Json_array_stepContext _localctx = new Json_array_stepContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_json_array_step);
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
					json_array_range();
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
						json_array_range();
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

	public static class Json_field_nameContext extends ParserRuleContext {
		public TerminalNode JSON_STRING() { return getToken(JsonPathParser.JSON_STRING, 0); }
		public TerminalNode IDENTIFIER() { return getToken(JsonPathParser.IDENTIFIER, 0); }
		public Json_field_nameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_json_field_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JsonPathListener ) ((JsonPathListener)listener).enterJson_field_name(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JsonPathListener ) ((JsonPathListener)listener).exitJson_field_name(this);
		}
	}

	public final Json_field_nameContext json_field_name() throws RecognitionException {
		Json_field_nameContext _localctx = new Json_field_nameContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_json_field_name);
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

	public static class Json_array_rangeContext extends ParserRuleContext {
		public List<TerminalNode> NATRUAL_INTEGER() { return getTokens(JsonPathParser.NATRUAL_INTEGER); }
		public TerminalNode NATRUAL_INTEGER(int i) {
			return getToken(JsonPathParser.NATRUAL_INTEGER, i);
		}
		public Json_array_rangeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_json_array_range; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JsonPathListener ) ((JsonPathListener)listener).enterJson_array_range(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JsonPathListener ) ((JsonPathListener)listener).exitJson_array_range(this);
		}
	}

	public final Json_array_rangeContext json_array_range() throws RecognitionException {
		Json_array_rangeContext _localctx = new Json_array_rangeContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_json_array_range);
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