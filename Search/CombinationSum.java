package Search;

import java.util.*;

public class CombinationSum {

	public void combining(int[] candidates, int idx, int target,
			ArrayList<ArrayList<Integer>> res, ArrayList<Integer> sol) {

		if (target == 0) {
			res.add(sol);
			return;
		}

		if (idx == -1)
			return;

		ArrayList<Integer> tmpSol = new ArrayList<Integer>();
		tmpSol.addAll(sol);
		for (int i = 0; i * candidates[idx] <= target; i++) {
			if (i > 0)
				tmpSol.add(0, candidates[idx]);

			combining(candidates, idx - 1, target - i * candidates[idx], res,
					tmpSol);
		}
	}

	public void combining_once(int[] candidates, int idx, int target,
			ArrayList<ArrayList<Integer>> res, ArrayList<Integer> sol,
			HashMap<Integer, Integer> map) {

		if (target == 0) {
			res.add((ArrayList<Integer>) sol.clone());
			return;
		}

		if (idx == -1)
			return;

		int count = 1;
		int nextidx = idx - 1;
		if (!map.containsKey(candidates[idx])) {
			while (nextidx >= 0 && candidates[nextidx] == candidates[idx]) {
				count++;
				nextidx--;
			}
			map.put(candidates[idx], count);
		} else {
			count = map.get(candidates[idx]);
			nextidx = idx - count;
		}

		ArrayList<Integer> tmpSol = new ArrayList<Integer>();
		tmpSol.addAll(sol);
		for (int i = 0; i <= count; i++) {
			if (i > 0)
				tmpSol.add(0, candidates[idx]);
			combining_once(candidates, nextidx, target - i * candidates[idx],
					res, tmpSol, map);
		}
	}

	public ArrayList<ArrayList<Integer>> combinationSum(int[] candidates,
			int target) {
		// Start typing your Java solution below
		// DO NOT write main() function
		ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
		if (candidates == null || candidates.length == 0 || target <= 0)
			return res;

		Arrays.sort(candidates);
		ArrayList<Integer> sol = new ArrayList<Integer>();
		int idx = candidates.length - 1;
		while (idx >= 0 && target < candidates[idx])
			idx--;

		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		combining_once(candidates, idx, target, res, sol, map);
		return res;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CombinationSum cs = new CombinationSum();
		int[] candidates = new int[] { 3, 1, 3, 1, 1 };
		int target = 8;
		System.out.println(cs.combinationSum(candidates, target));
	}

}
