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
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, JSON_STRING=11, NATRUAL_INTEGER=12, IDENTIFIER=13, WILDCARD=14, 
		LOGIC_AND=15, DIGIT=16, POSITIVEDIGIT=17, LETTER=18, WS=19;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
		"T__9", "JSON_STRING", "NATRUAL_INTEGER", "IDENTIFIER", "WILDCARD", "LOGIC_AND", 
		"DIGIT", "POSITIVEDIGIT", "LETTER", "WS"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'$'", "'@'", "'[?('", "')]'", "'.'", "'..'", "'['", "']'", "','", 
		"':'", null, null, null, "'*'", "'&&'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, "JSON_STRING", 
		"NATRUAL_INTEGER", "IDENTIFIER", "WILDCARD", "LOGIC_AND", "DIGIT", "POSITIVEDIGIT", 
		"LETTER", "WS"
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
		"\4\23\t\23\4\24\t\24\3\2\3\2\3\3\3\3\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\6\3"+
		"\6\3\7\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13\3\f\3\f\7\fD\n\f\f\f"+
		"\16\fG\13\f\3\f\3\f\3\r\3\r\3\r\7\rN\n\r\f\r\16\rQ\13\r\5\rS\n\r\3\16"+
		"\3\16\3\16\7\16X\n\16\f\16\16\16[\13\16\3\17\3\17\3\20\3\20\3\20\3\21"+
		"\3\21\3\22\3\22\3\23\5\23g\n\23\3\24\6\24j\n\24\r\24\16\24k\3\24\3\24"+
		"\2\2\25\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17"+
		"\35\20\37\21!\22#\23%\24\'\25\3\2\7\4\2$$^^\3\2\62;\3\2\63;\4\2C\\c|\5"+
		"\2\13\f\17\17\"\"\2t\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2"+
		"\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3"+
		"\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2"+
		"\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\3)\3\2\2\2\5+\3\2\2\2"+
		"\7-\3\2\2\2\t\61\3\2\2\2\13\64\3\2\2\2\r\66\3\2\2\2\179\3\2\2\2\21;\3"+
		"\2\2\2\23=\3\2\2\2\25?\3\2\2\2\27A\3\2\2\2\31R\3\2\2\2\33T\3\2\2\2\35"+
		"\\\3\2\2\2\37^\3\2\2\2!a\3\2\2\2#c\3\2\2\2%f\3\2\2\2\'i\3\2\2\2)*\7&\2"+
		"\2*\4\3\2\2\2+,\7B\2\2,\6\3\2\2\2-.\7]\2\2./\7A\2\2/\60\7*\2\2\60\b\3"+
		"\2\2\2\61\62\7+\2\2\62\63\7_\2\2\63\n\3\2\2\2\64\65\7\60\2\2\65\f\3\2"+
		"\2\2\66\67\7\60\2\2\678\7\60\2\28\16\3\2\2\29:\7]\2\2:\20\3\2\2\2;<\7"+
		"_\2\2<\22\3\2\2\2=>\7.\2\2>\24\3\2\2\2?@\7<\2\2@\26\3\2\2\2AE\7$\2\2B"+
		"D\n\2\2\2CB\3\2\2\2DG\3\2\2\2EC\3\2\2\2EF\3\2\2\2FH\3\2\2\2GE\3\2\2\2"+
		"HI\7$\2\2I\30\3\2\2\2JS\7\62\2\2KO\5#\22\2LN\5!\21\2ML\3\2\2\2NQ\3\2\2"+
		"\2OM\3\2\2\2OP\3\2\2\2PS\3\2\2\2QO\3\2\2\2RJ\3\2\2\2RK\3\2\2\2S\32\3\2"+
		"\2\2TY\5%\23\2UX\5%\23\2VX\5!\21\2WU\3\2\2\2WV\3\2\2\2X[\3\2\2\2YW\3\2"+
		"\2\2YZ\3\2\2\2Z\34\3\2\2\2[Y\3\2\2\2\\]\7,\2\2]\36\3\2\2\2^_\7(\2\2_`"+
		"\7(\2\2` \3\2\2\2ab\t\3\2\2b\"\3\2\2\2cd\t\4\2\2d$\3\2\2\2eg\t\5\2\2f"+
		"e\3\2\2\2g&\3\2\2\2hj\t\6\2\2ih\3\2\2\2jk\3\2\2\2ki\3\2\2\2kl\3\2\2\2"+
		"lm\3\2\2\2mn\b\24\2\2n(\3\2\2\2\n\2EORWYfk\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}