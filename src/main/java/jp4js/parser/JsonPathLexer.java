// Generated from JsonPath.g4 by ANTLR 4.7
package jp4js.parser;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class JsonPathLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, JSON_STRING=7, NATRUAL_INTEGER=8, 
		IDENTIFIER=9, FILTER_BEGIN=10, FILTER_END=11, ARRAY_BEGIN=12, ARRAY_END=13, 
		WILDCARD=14, LOGIC_AND=15, DIGIT=16, POSITIVEDIGIT=17, LETTER=18, WS=19;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "JSON_STRING", "NATRUAL_INTEGER", 
		"IDENTIFIER", "FILTER_BEGIN", "FILTER_END", "ARRAY_BEGIN", "ARRAY_END", 
		"WILDCARD", "LOGIC_AND", "DIGIT", "POSITIVEDIGIT", "LETTER", "WS"
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


	public JsonPathLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "JsonPath.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\25o\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\5\3\6\3\6\3\7\3"+
		"\7\3\b\3\b\7\b9\n\b\f\b\16\b<\13\b\3\b\3\b\3\t\3\t\3\t\7\tC\n\t\f\t\16"+
		"\tF\13\t\5\tH\n\t\3\n\3\n\3\n\7\nM\n\n\f\n\16\nP\13\n\3\13\3\13\3\13\3"+
		"\13\3\f\3\f\3\f\3\r\3\r\3\16\3\16\3\17\3\17\3\20\3\20\3\20\3\21\3\21\3"+
		"\22\3\22\3\23\5\23g\n\23\3\24\6\24j\n\24\r\24\16\24k\3\24\3\24\2\2\25"+
		"\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20"+
		"\37\21!\22#\23%\24\'\25\3\2\7\4\2$$^^\3\2\62;\3\2\63;\4\2C\\c|\5\2\13"+
		"\f\17\17\"\"\2t\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3"+
		"\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2"+
		"\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3"+
		"\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\3)\3\2\2\2\5+\3\2\2\2\7-\3\2"+
		"\2\2\t/\3\2\2\2\13\62\3\2\2\2\r\64\3\2\2\2\17\66\3\2\2\2\21G\3\2\2\2\23"+
		"I\3\2\2\2\25Q\3\2\2\2\27U\3\2\2\2\31X\3\2\2\2\33Z\3\2\2\2\35\\\3\2\2\2"+
		"\37^\3\2\2\2!a\3\2\2\2#c\3\2\2\2%f\3\2\2\2\'i\3\2\2\2)*\7&\2\2*\4\3\2"+
		"\2\2+,\7B\2\2,\6\3\2\2\2-.\7\60\2\2.\b\3\2\2\2/\60\7\60\2\2\60\61\7\60"+
		"\2\2\61\n\3\2\2\2\62\63\7.\2\2\63\f\3\2\2\2\64\65\7<\2\2\65\16\3\2\2\2"+
		"\66:\7$\2\2\679\n\2\2\28\67\3\2\2\29<\3\2\2\2:8\3\2\2\2:;\3\2\2\2;=\3"+
		"\2\2\2<:\3\2\2\2=>\7$\2\2>\20\3\2\2\2?H\7\62\2\2@D\5#\22\2AC\5!\21\2B"+
		"A\3\2\2\2CF\3\2\2\2DB\3\2\2\2DE\3\2\2\2EH\3\2\2\2FD\3\2\2\2G?\3\2\2\2"+
		"G@\3\2\2\2H\22\3\2\2\2IN\5%\23\2JM\5%\23\2KM\5!\21\2LJ\3\2\2\2LK\3\2\2"+
		"\2MP\3\2\2\2NL\3\2\2\2NO\3\2\2\2O\24\3\2\2\2PN\3\2\2\2QR\7]\2\2RS\7A\2"+
		"\2ST\7*\2\2T\26\3\2\2\2UV\7+\2\2VW\7_\2\2W\30\3\2\2\2XY\7]\2\2Y\32\3\2"+
		"\2\2Z[\7_\2\2[\34\3\2\2\2\\]\7,\2\2]\36\3\2\2\2^_\7(\2\2_`\7(\2\2` \3"+
		"\2\2\2ab\t\3\2\2b\"\3\2\2\2cd\t\4\2\2d$\3\2\2\2eg\t\5\2\2fe\3\2\2\2g&"+
		"\3\2\2\2hj\t\6\2\2ih\3\2\2\2jk\3\2\2\2ki\3\2\2\2kl\3\2\2\2lm\3\2\2\2m"+
		"n\b\24\2\2n(\3\2\2\2\n\2:DGLNfk\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}