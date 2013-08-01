package DP;

import java.util.*;

public class PalidromePartition {

	public int minCut(String s) {
        // Start typing your Java solution below
        // DO NOT write main() function
		if (s == null || s.length() == 0)
			return 0;
		
        int len = s.length();
        int[] dp = new int[len+1];
        boolean[][] isPalid = new boolean[len][len];
        
        // Record the max cut for s[i...len-1]
        int i,j;
        for (i = 0; i <= len; i++)
        	dp[i] = len - 1 - i;
        
        for (i = len - 2; i >= 0; i--)
        	for (j = i; j < len; j++){
        		if ((s.charAt(i) == s.charAt(j)) && (j <= i+2 || isPalid[i+1][j-1])){
        			isPalid[i][j] = true;
        			dp[i] = Math.min(dp[i], 1 + dp[j+1]);
        		}
        	}
        
        return dp[0];
    }
	
	public ArrayList<ArrayList<String>> partitionHelper(String s, int start, boolean[][] isPalid){
		ArrayList<ArrayList<String>> res = new ArrayList<ArrayList<String>>();
		if (start == s.length()){
			res.add(new ArrayList<String>());
			return res;
		}
		
		for (int i = start; i < s.length(); i++){
			if (isPalid[start][i]){
				for (ArrayList<String> subRes: partitionHelper(s, i+1, isPalid)){
					subRes.add(0,s.substring(start, i+1));
					res.add(subRes);
				}
			}
		}
		
		return res;
	}
	
	public ArrayList<ArrayList<String>> partition(String s) {
        // Start typing your Java solution below
        // DO NOT write main() function
        if (s == null || s.length() == 0)
        	return new ArrayList<ArrayList<String>>();
        
        boolean[][] isPalid = new boolean[s.length()][s.length()];
        
        int i,j;
        for (i = 0; i < s.length(); i++)
        	isPalid[i][i] = true;
        
        for (i = s.length() - 2; i >= 0; i--)
        	for (j = i; j < s.length(); j++){
        		if (s.charAt(i) == s.charAt(j) && (j <= i+2 || isPalid[i+1][j-1]))
        			isPalid[i][j] = true;
        	}
        
        return partitionHelper(s, 0, isPalid);
    }
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}

}
