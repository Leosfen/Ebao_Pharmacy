package com.ebaonet.pharmacy.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 验证模块（使用正则表达式技术）
 */
public class ValidateUtils {

	public static final int EMPTY = 1;
	public static final int CHINESE = 2;
	public static final int DOUBLEBYTE = 3;
	public static final int ENGLISH = 4;
	public static final int NUMBER = 5;
	public static final int INTEGER = 6;
	public static final int REAL = 7;
	public static final int DECIMAL = 8;
	public static final int EMIAL = 9;
	public static final int ACCOUT = 10;
	public static final int URL = 11;
	public static final int PHONENUMBER = 12;
	public static final int ZIP = 14;
	public static final int QQ = 16;
	public static final int HTML = 17;
	public static final int IPADDRESS = 18;
	public static final int DATE = 19;
	public static final int CARNUMBER = 20;

	/**
	 * 验证字符串是否合法
	 * 
	 * @param s
	 *            要验证的字符串
	 * @param a
	 *            验证类型
	 * @param is
	 *            附加参数
	 * @return 验证结果
	 */
	public static boolean validate(String s, int a, int... is) {
		if (s == null) {
			return false;
		}

		switch (a) {
		case 1:
			return !isEmpty(s);
		case 2:
			return isChineseChar(s);
		case 3:
			return isDoubleByte(s);
		case 4:
			return isEnglishChar(s);
		case 5:
			return isNumber(s);
		case 6:
			return isInteger(s);
		case 7:
			return isReal(s);
		case 8:
			if (is.length == 0) {
				return isDecimal(s);
			} else {
				return isDecimal(s, is[0]);
			}
		case 9:
			return isEmail(s);
		case 10:
			if (is.length > 1) {
				return isAccout(s, is[0], is[1]);
			}
		case 11:
			return isURL(s);
		case 12:
			return isPhoneNumber(s);
		case 14:
			return isZip(s);
		case 16:
			return isQQ(s);
		case 17:
			return isHTML(s);
		case 18:
			return isIpAddress(s);
		case 19:
			return isDate(s);
		case 20:
			return isCarNumber(s);
		default:
			return false;
		}
	}

	/**
	 * 是否为空
	 */
	private static boolean isEmpty(String s) {
		return s.trim().length() == 0;
	}

	/**
	 * 中文
	 */
	private static boolean isChineseChar(String s) {
		return s.matches("[\u4e00-\u9fa5]+");
	}

	/**
	 * 双字节
	 */
	private static boolean isDoubleByte(String s) {
		return s.matches("[^\\x00-\\xff]+");
	}

	/**
	 * 英文
	 */
	private static boolean isEnglishChar(String s) {
		return s.matches("^[A-Za-z]+$");
	}

	/**
	 * 数字
	 */
	public static boolean isNumber(String s) {
		return s.matches("^\\d+$");
	}

	/**
	 * 整数
	 */
	private static boolean isInteger(String s) {
		return s.matches("[+-]?\\d+$");
	}

	/**
	 * 实数
	 */
	private static boolean isReal(String s) {
		return s.matches("^([+-]?\\d+)(\\.\\d+)?$");
	}

	/**
	 * 小数，参数为保留小数点后a位
	 */
	private static boolean isDecimal(String s, int a) {
		if (a <= 0) {
			return false;
		}

		String regex = "^[+-]?\\d*\\.\\d{" + a + "}$";
		return s.matches(regex);
	}

	/**
	 * 小数
	 */
	private static boolean isDecimal(String s) {
		return s.matches("^[+-]?\\d*\\.\\d+$");
	}

	/**
	 * 帐号是否合法，参数为允许a到b个字符数
	 */
	private static boolean isAccout(String s, int a, int b) {
		String regex = "^[a-zA-Z][a-zA-Z0-9_]{" + (a - 1) + "," + (b - 1)
				+ "}$";
		return s.matches(regex);
	}

	/**
	 * 符合HTTP协议的网址
	 */
	private static boolean isURL(String s) {
		return s.matches("http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?");
	}

	/**
	 * 电话号码
	 */
	private static boolean isPhoneNumber(String s) {
		// return s.matches("^(\\d{3,4}-)?\\d{6,8}$");//国内电话号码
		return s.matches("^(\\+)?\\d{8,16}$");// 国际电话号码
	}

	/**
	 * 邮政编码
	 */
	private static boolean isZip(String s) {
		return s.matches("[1-9]\\d{5}(?!\\d)");
	}

	/**
	 * QQ号码
	 */
	private static boolean isQQ(String s) {
		return s.matches("[1-9][0-9]{4,}");
	}

	/**
	 * HTML标记
	 */
	private static boolean isHTML(String s) {
		return s.matches("<(\\S*?)[^>]*>.*?</\1>|<.*? />");
	}

	/**
	 * Ip地址
	 */
	private static boolean isIpAddress(String s) {
		String num = "(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)";
		return s.matches("^" + num + "\\." + num + "\\." + num + "\\." + num
				+ "$");
	}

	/**
	 * 日期
	 */
	private static boolean isDate(String s) {
		return s.matches("^(?:(?:1[6-9]|[2-9]\\d)?\\d{2}[\\/\\-\\.](?:0?[1,3-9]|1[0-2])[\\/\\-\\.](?:29|30))(?: (?:0?\\d|1\\d|2[0-3])\\:(?:0?\\d|[1-5]\\d)\\:(?:0?\\d|[1-5]\\d)(?: \\d{1,3})?)?$|^(?:(?:1[6-9]|[2-9]\\d)?\\d{2}[\\/\\-\\.](?:0?[1,3,5,7,8]|1[02])[\\/\\-\\.]31)(?: (?:0?\\d|1\\d|2[0-3])\\:(?:0?\\d|[1-5]\\d)\\:(?:0?\\d|[1-5]\\d)(?: \\d{1,3})?)?$|^(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])[\\/\\-\\.]0?2[\\/\\-\\.]29)(?: (?:0?\\d|1\\d|2[0-3])\\:(?:0?\\d|[1-5]\\d)\\:(?:0?\\d|[1-5]\\d)(?: \\d{1,3})?)?$|^(?:(?:16|[2468][048]|[3579][26])00[\\/\\-\\.]0?2[\\/\\-\\.]29)(?: (?:0?\\d|1\\d|2[0-3])\\:(?:0?\\d|[1-5]\\d)\\:(?:0?\\d|[1-5]\\d)(?: \\d{1,3})?)?$|^(?:(?:1[6-9]|[2-9]\\d)?\\d{2}[\\/\\-\\.](?:0?[1-9]|1[0-2])[\\/\\-\\.](?:0?[1-9]|1\\d|2[0-8]))(?: (?:0?\\d|1\\d|2[0-3])\\:(?:0?\\d|[1-5]\\d)\\:(?:0?\\d|[1-5]\\d)(?: \\d{1,3})?)?$");
	}

	/**
	 * 车牌号
	 */
	private static boolean isCarNumber(String s) {
		return s.matches("^[\u4e00-\u9fa5][A-M]-[A-Z0-9][0-9]{4}$");
	}

	/**
	 * 电话号码验证
	 * 
	 * @param str
	 * @return 验证通过返回true
	 */
	public static boolean isPhone(String str) {
		Pattern p1 = null, p2 = null;
		Matcher m = null;
		boolean b = false;
		p1 = Pattern.compile("^[0][1-9]{2,3}-[0-9]{5,10}$"); // 验证带区号的
		p2 = Pattern.compile("^[1-9]{1}[0-9]{5,8}$"); // 验证没有区号的
		if (str.length() > 9) {
			m = p1.matcher(str);
			b = m.matches();
		} else {
			m = p2.matcher(str);
			b = m.matches();
		}
		return b;
	}

	/**
	 * 手机号验证 1开头 11位数字
	 * 
	 * @param str
	 * @return 验证通过返回true
	 */
	public static boolean isMobile(String str) {
		Pattern p = null;
		Matcher m = null;
		boolean b = false;
//		p = Pattern.compile("^[1][3,4,5,7,8][0-9]{9}$"); // 验证手机号
		p = Pattern.compile("^[1][0-9]{10}$"); // 验证手机号
		m = p.matcher(str);
		b = m.matches();
		return b;
	}

	/**
	 * 验证身份证号码
	 * 
	 * @param idCard
	 *            居民身份证号码15位或18位，最后一位可能是数字或字母
	 * @return 验证成功返回true，验证失败返回false
	 */
	public static boolean checkIdCard(String idCard) {
		// String regex = "[1-9]\\d{13,16}[a-zA-Z0-9]{1}";
		// String regex = "(\\d{14}[0-9a-zA-Z])|(\\d{17}[0-9a-zA-Z])";
		String regex = "\\d{17}[0-9xX]";// 只能18位最后一位为0-9x|X
		return Pattern.matches(regex, idCard);
	}

	/**
	 * 由数字和字母组成，并且要同时含有数字和字母，且长度要在6-16位
	 * @param pwd
	 * @return
	 */
	public static boolean isPwd(String pwd) {
		String regex = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,16}$";
		return Pattern.matches(regex, pwd);
	}

	/**
	 * 验证邮箱
	 * 
	 * @param 待验证的字符串
	 * @return 如果是符合的字符串,返回 <b>true </b>,否则为 <b>false </b>
	 */
	public static boolean isEmail(String str) {
		String regex = "^([\\w-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([\\w-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
		return match(regex, str);
	}

	/**
	 * 验证IP地址
	 * 
	 * @param 待验证的字符串
	 * @return 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
	 */
	public static boolean isIP(String str) {
		String num = "(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)";
		String regex = "^" + num + "\\." + num + "\\." + num + "\\." + num
				+ "$";
		return match(regex, str);
	}

	/**
	 * 验证网址Url
	 * 
	 * @param 待验证的字符串
	 * @return 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
	 */
	public static boolean IsUrl(String str) {
		String regex = "http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?";
		return match(regex, str);
	}

	/**
	 * 验证电话号码
	 * 
	 * @param 待验证的字符串
	 * @return 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
	 */
	public static boolean IsTelephone(String str) {
		String regex = "^(\\d{3,4}-)?\\d{6,8}$";
		return match(regex, str);
	}

	/**
	 * 验证输入密码条件(字符与数据同时出现)
	 * 
	 * @param 待验证的字符串
	 * @return 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
	 */
	public static boolean IsPassword(String str) {
		String regex = "[A-Za-z]+[0-9]";
		return match(regex, str);
	}

	/**
	 * 验证输入密码长度 (6-18位)
	 * 
	 * @param 待验证的字符串
	 * @return 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
	 */
	public static boolean IsPasswLength(String str) {
		String regex = "^\\d{6,18}$";
		return match(regex, str);
	}

	/**
	 * 验证输入邮政编号
	 * 
	 * @param 待验证的字符串
	 * @return 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
	 */
	public static boolean IsPostalcode(String str) {
		String regex = "^\\d{6}$";
		return match(regex, str);
	}

	/**
	 * 验证输入手机号码
	 * 
	 * @param 待验证的字符串
	 * @return 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
	 */
	public static boolean IsHandset(String str) {
		String regex = "^[1]+[3,5]+\\d{9}$";
		return match(regex, str);
	}

	/**
	 * 验证输入身份证号
	 * 
	 * @param 待验证的字符串
	 * @return 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
	 */
	public static boolean IsIDcard(String str) {
		String regex = "(^\\d{18}$)|(^\\d{15}$)";
		return match(regex, str);
	}

	/**
	 * 验证输入两位小数
	 * 
	 * @param 待验证的字符串
	 * @return 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
	 */
	public static boolean IsDecimal(String str) {
		String regex = "^[0-9]+(.[0-9]{2})?$";
		return match(regex, str);
	}

	/**
	 * 验证输入一年的12个月
	 * 
	 * @param 待验证的字符串
	 * @return 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
	 */
	public static boolean IsMonth(String str) {
		String regex = "^(0?[[1-9]|1[0-2])$";
		return match(regex, str);
	}

	/**
	 * 验证输入一个月的31天
	 * 
	 * @param 待验证的字符串
	 * @return 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
	 */
	public static boolean IsDay(String str) {
		String regex = "^((0?[1-9])|((1|2)[0-9])|30|31)$";
		return match(regex, str);
	}

	/**
	 * 验证数字输入
	 * 
	 * @param 待验证的字符串
	 * @return 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
	 */
	public static boolean IsNumber(String str) {
		String regex = "^[0-9]*$";
		return match(regex, str);
	}

	/**
	 * 验证非零的正整数
	 * 
	 * @param 待验证的字符串
	 * @return 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
	 */
	public static boolean IsIntNumber(String str) {
		String regex = "^\\+?[1-9][0-9]*$";
		return match(regex, str);
	}

	/**
	 * 验证大写字母
	 * 
	 * @param 待验证的字符串
	 * @return 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
	 */
	public static boolean IsUpChar(String str) {
		String regex = "^[A-Z]+$";
		return match(regex, str);
	}

	/**
	 * 验证小写字母
	 * 
	 * @param 待验证的字符串
	 * @return 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
	 */
	public static boolean IsLowChar(String str) {
		String regex = "^[a-z]+$";
		return match(regex, str);
	}

	/**
	 * 验证验证输入字母
	 * 
	 * @param 待验证的字符串
	 * @return 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
	 */
	public static boolean IsLetter(String str) {
		String regex = "^[A-Za-z]+$";
		return match(regex, str);
	}

	/**
	 * 验证验证输入汉字
	 * 
	 * @param 待验证的字符串
	 * @return 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
	 */
	public static boolean IsChinese(String str) {
		String regex = "^[\u4e00-\u9fa5],{0,}$";
		return match(regex, str);
	}

	/**
	 * 验证验证输入字符串
	 * 
	 * @param 待验证的字符串
	 * @return 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
	 */
	public static boolean IsLength(String str) {
		String regex = "^.{8,}$";
		return match(regex, str);
	}

	/**
	 * @param regex
	 *            正则表达式字符串
	 * @param str
	 *            要匹配的字符串
	 * @return 如果str 符合 regex的正则表达式格式,返回true, 否则返回 false;
	 */
	private static boolean match(String regex, String str) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(str);
		return matcher.matches();
	}
	
	public static boolean isNick(String nickName){
		return match("^[0-9a-zA-Z\\u4e00-\\u9fa5]+$", nickName);
	}

	// 3. 检查字符串重复出现的词
	//
	// private void btnWord_Click(object sender, EventArgs e)
	// {
	// System.Text.RegularExpressions.MatchCollection matches =
	// System.Text.RegularExpressions.Regex.Matches(label1.Text,
	//
	// @"\b(?<word>\w+)\s+(\k<word>)\b",
	// System.Text.RegularExpressions.RegexOptions.Compiled |
	// System.Text.RegularExpressions.RegexOptions.IgnoreCase);
	// if (matches.Count != 0)
	// {
	// foreach (System.Text.RegularExpressions.Match match in matches)
	// {
	// string word = match.Groups["word"].Value;
	// MessageBox.Show(word.ToString(),"英文单词");
	// }
	// }
	// else { MessageBox.Show("没有重复的单词"); }
	//
	//
	// }
	//
	// 4. 替换字符串
	//
	// private void button1_Click(object sender, EventArgs e)
	// {
	//
	// string strResult =
	// System.Text.RegularExpressions.Regex.Replace(textBox1.Text,
	// @"[A-Za-z]\*?", textBox2.Text);
	// MessageBox.Show("替换前字符:" + "\n" + textBox1.Text + "\n" + "替换的字符:" + "\n"
	// + textBox2.Text + "\n" +
	//
	// "替换后的字符:" + "\n" + strResult,"替换");
	//
	// }
	//
	// 5. 拆分字符串
	//
	// private void button1_Click(object sender, EventArgs e)
	// {
	// //实例: 甲025-8343243乙0755-2228382丙029-32983298389289328932893289丁
	// foreach (string s in
	// System.Text.RegularExpressions.Regex.Split(textBox1.Text,@"\d{3,4}-\d*"))
	// {
	// textBox2.Text+=s; //依次输出 "甲乙丙丁"
	// }
	//
	// }
}
