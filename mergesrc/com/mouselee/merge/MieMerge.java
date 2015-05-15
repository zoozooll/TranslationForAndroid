/**
 * 
 */
package com.mouselee.merge;

import java.util.Arrays;

import com.mouselee.merge.bo.XlsMerge;

/**
 * @author zuokang.li
 *
 */
public class MieMerge {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		if (args == null || args.length < 3) {
			return;
		}
		System.out.println(Arrays.toString(args));
		XlsMerge merge = new XlsMerge();
		merge.mergeRebackXls(args[0], args[1], args[2]);
	}

}
