/**
 * 
 */
package com.mouselee.merge;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zuokang.li
 *
 */
public class FuncExer {
	
	private static Map<String, String> sLocaleMap = new HashMap<String, String>();
	
	static {
		sLocaleMap.put("CHINESE_CN"  ,        "zh-rCN");    //�������� 
		sLocaleMap.put("CHINESE_OLD"  ,        "zh-rHK");    //�������� 
		sLocaleMap.put("English"    ,        "default"    );    //Ӣ�� 
		sLocaleMap.put("FRENCH"     ,        "fr"    );    //���� 
		sLocaleMap.put("DUTCH"      ,        "nl"    );    //���� 
		sLocaleMap.put("GERMAN"     ,        "de"    );    //�¹� 
		sLocaleMap.put("GREEK"      ,        "el"    );    //ϣ�� 
		sLocaleMap.put("HUNGARIAN"  ,        "hu"    );    //������ 
		sLocaleMap.put("ITALIAN"    ,        "it"    );    //����� 
		sLocaleMap.put("PORTUGUESE" ,        "pt"    );    //������ 
		sLocaleMap.put("SPANISH"    ,        "es"    );    //������ 
		sLocaleMap.put("TURKISH"    ,        "tr"    );    //������ 
		sLocaleMap.put("POLISH"     ,        "pl"    );    //���� 
		sLocaleMap.put("CZECH"      ,        "cs"    );    //�ݿ� 
		sLocaleMap.put("MALAY"      ,        "ms"    );    //������ 
		sLocaleMap.put("INDONESIAN" ,        "in"    );    //ӡ�� 
		sLocaleMap.put("SLOVAK"     ,        "sk"    );    //˹�工�� 
		sLocaleMap.put("ROMANIAN"   ,        "ro"    );    //�������� 
		sLocaleMap.put("SLOVENIAN"  ,        "sl"    );    //˹�������� 
		sLocaleMap.put("THAI"       ,        "th"    );    //̩�� 
		sLocaleMap.put("SERBIAN"    ,        "sr"    );    //���ά�� 
		sLocaleMap.put("GALICIAN"   ,        "gl"    );    //�������� 
		sLocaleMap.put("VIETNAMESE" ,        "vi"    );    //Խ�� 
		sLocaleMap.put("BRAZILIAN"  ,        "pt-rBR");    //���� 
		sLocaleMap.put("JAPANESE"   ,        "ja"    );    //���� 
		sLocaleMap.put("LATINESP"   ,        "es-rLA");    //������������ 
		sLocaleMap.put("FARSI"      ,        "fa"    );    //��˹ 
		sLocaleMap.put("CROATIAN"   ,        "hr"    );    //���޵��� 
		sLocaleMap.put("RUSSIAN"    ,        "ru"    );    //����   
		sLocaleMap.put("ARABIC"     ,        "ar"    );    //�������� 
		sLocaleMap.put("CATALAN"    ,        "ca"    );    //��̩������ 
		sLocaleMap.put("DANISH"     ,        "da"    );    //���� 
		sLocaleMap.put("FINNISH"    ,        "fi"    );    //���� 
		sLocaleMap.put("FRENCH_CA"  ,        "fr-rCA");    //����-���ô� 
		sLocaleMap.put("NORWEGIAN"  ,        "no"    );    //Ų�� 
		sLocaleMap.put("SWEDISH"    ,        "sv"    );    //��� 
		sLocaleMap.put("EUSKERA"    ,        "eu"    );    //��˹��      
		sLocaleMap.put("ALBANIAN"   ,        "sq"    );    //����������� 
		sLocaleMap.put("BENGALI"    ,        "bn-rBD");    //�ϼ��� 
		sLocaleMap.put("BULGARIAN"  ,        "bg"    );    //���������� 
		sLocaleMap.put("CAMBODIAN"  ,        "km-rKH");    //����կ 
		sLocaleMap.put("ESTONIAN"   ,        "et"    );    //��ɳ������ 
		sLocaleMap.put("HEBREW"     ,        "he"    );    //ϣ������ 
		sLocaleMap.put("KOREAN"     ,        "ko"    );    //������ 
		sLocaleMap.put("LAOTIAN"    ,        "lo-rLA");    //������ 
		sLocaleMap.put("LATVIAN"    ,        "lv"    );    //����ά���� 
		sLocaleMap.put("LITHUANIAN" ,        "lt"    );    //������ 
		sLocaleMap.put("MACEDONIAN" ,        "mk"    );    //����� 
		sLocaleMap.put("MYANMAR"    ,        "my-rMM");    //��� 
		sLocaleMap.put("UKRAINIAN"  ,        "uk"    );    //�ڿ����� 
		sLocaleMap.put("HINDI"      ,        "hi"    ); 
		sLocaleMap.put("INDONESIAN" ,        "id"    );
		sLocaleMap.put("Norwegian-Bokmol",   "nb_rNO");
	}
	
	private static Map<String, String> stringKeyMap = new HashMap<String , String>();
	static {
		for (Map.Entry<String, String> enter: sLocaleMap.entrySet()) {
			stringKeyMap.put(enter.getValue(), enter.getKey());
		}
	
	
	}
	
	/* [null, null, ru, es, ar, fr, fr-rCA, pt, it, gl, de, null, sv, hu, el, sq, 
	pt-rBR, bg, ca, null, zh-rCN, zh-rHK, hr, cs, nl, et, eu, fa, es-rLA, lv, lt, mk, pl, 
	ro, sr, sk, sl, tr, uk, null, da, fi, null, th, in, null] */
	/*
	  	"en-rGB",
		"en-rUS",
		"ru",
		"es",
		"ar",
		"fr",
		"fr-rCA",
		"pt",
		"it",
		"gl",
		"de",
		"vi",
		"sv",
		"hu",
		"el",
		"sq",
		"pt-rBR",
		"bg",
		"ca",
		"zh-rHK",
		"zh-rCN",
		"zh-rTW",
		"hr",
		"cs",
		"nl",
		"et",
		"eu",
		"fa",
		"es-rLA",
		"lv",
		"lt",
		"mk",
		"pl",
		"ro",
		"sr",
		"sk",
		"sl",
		"tr",
		"uk",
		"mr-rIN",
		"da",
		"fi",
		"no",
		"th",
		"in",
		"ur"
	 * */
	
//	ar	bg	ca	cs	da-rDK	de	el	es	eu	fa	fi	fr	fr-rCA	gl	hdpi	he	hi	hr	hu	id	it	ja	mk	ms	nb-rNO	nl	pl	pt	pt-rBR	ro	ru	sk	sl	sq	sr	sv	th	tr	uk	vi	zh-rCN	zh-rHK	zh-rTW

	
	public static void main(String[] args) {
		/*String[] seq = {"ENGLISH", "ENGLISH_US", "RUSSIAN", "SPANISH", "ARABIC", "FRENCH", "FRENCH_CA", "PORTUGUESE", "ITALIAN", "GALICIAN", "GERMAN", "VIETANMESE", "SWEDISH", "HUNGARIAN", "GREEK", "ALBANIAN", "BRAZILIAN", "BULGARIAN", "CATALAN", "CHINE_HK", "CHINE_NEW", "CHINE_OLD", "CROATIAN", "CZECH", "DUTCH", "ESTONIAN", "EUSKERA", "FARSI", "LATINESP", "LATVIAN", "LITHUANIAN", "MACEDONIAN", "POLISH", "ROMANIAN", "SERBIAN", "SLOVAK", "SLOVENIAN", "TURKISH", "UKRAINIAN", "MALASIA BAHASA", "DANISH", "FINNISH", "NORWAY", "THAI", "INDONESIAN", "URDU" };
		String[] newLocaleString = new String[seq.length];
		int i = 0;
		for (String str : seq) {
			newLocaleString[i]  = sLocaleMap.get(str);
			i ++;
		}
		System.out.println(Arrays.toString(newLocaleString));*/
		String[] keyList = {
			"ar"     ,
			"bg"     ,
			"ca"     ,
			"cs"     ,
			"da" ,
			"de"     ,
			"el"     ,
			"es"     ,
			"eu"     ,
			"fa"     ,
			"fi"     ,
			"fr"     ,
			"fr-rCA" ,
			"gl"     ,
			"he"     ,
			"hi"     ,
			"hr"     ,
			"hu"     ,
			"id"     ,
			"it"     ,
			"ja"     ,
			"mk"     ,
			"ms"     ,
			"nb_rNO" ,
			"nl"     ,
			"pl"     ,
			"pt"     ,
			"pt-rBR" ,
			"ro"     ,
			"ru"     ,
			"sk"     ,
			"sl"     ,
			"sq"     ,
			"sr"     ,
			"sv"     ,
			"th"     ,
			"tr"     ,
			"uk"     ,
			"vi"     ,
			"zh-rCN" ,
			"zh-rHK" ,
			"zh-rTW"};
		
		for (String str : keyList) {
			System.out.print(stringKeyMap.get(str) + "\t");
		}
	}

}
