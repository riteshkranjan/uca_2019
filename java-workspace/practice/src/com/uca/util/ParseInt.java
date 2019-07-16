package com.uca.util;

public class ParseInt {

	public static void main(String[] args) {
		ParseInt parser = new ParseInt();
		if (parser.parse("705") != 705) throw new AssertionError("Failed");
		if (parser.parse("103") != 103) throw new AssertionError("Failed");
		if (parser.parse("-705") != -705) throw new AssertionError("Failed");
		if (parser.parse("-103") != -103) throw new AssertionError("Failed");
		if (parser.parse("0") != 0) throw new AssertionError("Failed");
		if (parser.parse("000") != 0) throw new AssertionError("Failed");
		if (parser.parse("012") != 10 ) throw new AssertionError("Failed");
		if (parser.parse("0x12") != 18 ) throw new AssertionError("Failed");
		
		testNegativeCase(parser,null);
		testNegativeCase(parser,"");
		testNegativeCase(parser,"-");
		testNegativeCase(parser,"a123");
		testNegativeCase(parser,"123a");
		testNegativeCase(parser,"018");
		testNegativeCase(parser,"0x1G");

		System.out.println("all test cases passed");
	}

	private static void testNegativeCase(ParseInt parser, String s) throws AssertionError {
		try {
			parser.parse(s);
		} catch (NumberFormatException ne) {
			return;
		}
		throw new AssertionError("Failed");
	}
	
	private enum Base{
		Decimal {
			@Override
			protected int parse(String s, int startIndex) {
				if(s.length()==startIndex) throw new NumberFormatException();
				if(s.charAt(startIndex)=='0')
					return Octa.parse(s, ++startIndex);
				return process(s, startIndex, 10);
			}
		}, Octa {
			@Override
			protected int parse(String s, int startIndex) {
				if(s.length()==startIndex) return 0;
				if(s.charAt(startIndex)=='x')
					return Hexa.parse(s, ++startIndex);
				return process(s, startIndex, 8);
			}
		}, Hexa {
			@Override
			protected int parse(String s, int startIndex) {
				if(s.length()==startIndex) throw new NumberFormatException();
				return process(s, startIndex, 16);
			}

			@Override
			protected int fetchCharacter(char c, char max) {
				if((c<'A' || c>'F') && (c>'9' || c<0)) throw new NumberFormatException();
				if(c>='A'&& c<='F') return c - 'A' + 10;
				return c - '0';
			}
		};

		protected abstract int parse(String s, int startIndex) ;
		protected int fetchCharacter(char c, char max) {
			if(c>'7' || c<0) throw new NumberFormatException();
			return c - '0';
		}
		protected int process(String s, int startIndex, int factor) {
			int result = 0 ;
			for (int i = startIndex; i < s.length(); i++) {
				result  = result * factor + fetchCharacter(s.charAt(i), (char)(factor-1));
			}
			return result;
		}
	}

	public int parse(String s) throws NumberFormatException {
		if (s == null || s.equals("")) throw new NumberFormatException();
		int sign = (s.charAt(0) == '-') ? -1 : +1;
		return Base.Decimal.parse(s, -1* (sign>>1)) * sign;
	}
}
