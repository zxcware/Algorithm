package String;

import java.util.Arrays;

public class WildMatch {

	public boolean isMatch(String s, String p) {
		// Start typing your Java solution below
		// DO NOT write main() function
		if (p.length() == 0)
			return (s.length() == 0);

		if (p.charAt(0) != '*')
			if (s.length() > 0
					&& (p.charAt(0) == '?' || s.charAt(0) == p.charAt(0)))
				return isMatch(s.substring(1), p.substring(1));
			else
				return false;

		int count = 1;
		while (count < p.length() && p.charAt(count) == '*')
			count++;

		for (int i = 0; i <= s.length(); i++) {
			if (isMatch(s.substring(i), p.substring(count)))
				return true;
		}

		return false;
	}

	public boolean isMatch_DP(String s, String p) {
		// Start typing your Java solution below
		// DO NOT write main() function
		int i, j;

		// check valid length
		for (i = 0, j = 0; i < p.length(); i++) {
			if (p.charAt(i) == '*')
				continue;
			j++;
		}
		if (j > s.length())
			return false;

		// dp section
		boolean[] pre = new boolean[s.length() + 1];
		boolean[] cur = new boolean[s.length() + 1];
		pre[0] = true;

		for (i = 0; i < p.length(); i++) {
			if (p.charAt(i) == '*') {
				for (j = 0; j <= s.length(); j++)
					if (pre[j])
						break;
				Arrays.fill(cur, 0, j, false);
				Arrays.fill(cur, j, cur.length, true);
			} else {
				cur[0] = false;
				for (j = 0; j < s.length(); j++) {
					if (p.charAt(i) == '?' || p.charAt(i) == s.charAt(j))
						cur[j + 1] = pre[j];
					else
						cur[j + 1] = false;
				}
			}

			boolean[] tmp = cur;
			cur = pre;
			pre = tmp;
		}

		return pre[s.length()];
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WildMatch wm = new WildMatch();
		System.out.println(wm.isMatch_DP("aaaaabbbddddddddddddddddc", "a*c"));
	}

}
