import java.util.*;

class Solution {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> result = new ArrayList<>();
        Set<String> dict = new HashSet<>(wordList);

        if (!dict.contains(endWord)) {
            return result;
        }

        Map<String, List<String>> parents = new HashMap<>();
        Map<String, Integer> level = new HashMap<>();

        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        level.put(beginWord, 0);

        int wordLen = beginWord.length();

        while (!queue.isEmpty()) {
            String word = queue.poll();
            int currLevel = level.get(word);

            char[] arr = word.toCharArray();

            for (int i = 0; i < wordLen; i++) {
                char original = arr[i];

                for (char c = 'a'; c <= 'z'; c++) {
                    arr[i] = c;
                    String next = new String(arr);

                    if (!dict.contains(next)) {
                        continue;
                    }

                    if (!level.containsKey(next)) {
                        level.put(next, currLevel + 1);
                        queue.offer(next);
                        parents.put(next, new ArrayList<>());
                        parents.get(next).add(word);
                    } else if (level.get(next) == currLevel + 1) {
                        parents.get(next).add(word);
                    }
                }

                arr[i] = original;
            }
        }

        if (!level.containsKey(endWord)) {
            return result;
        }

        List<String> path = new ArrayList<>();
        dfs(endWord, beginWord, parents, path, result);

        return result;
    }

    private void dfs(String word, String beginWord,
                     Map<String, List<String>> parents,
                     List<String> path,
                     List<List<String>> result) {

        path.add(word);

        if (word.equals(beginWord)) {
            List<String> temp = new ArrayList<>(path);
            Collections.reverse(temp);
            result.add(temp);
        } else {
            List<String> prev = parents.get(word);
            if (prev != null) {
                for (String p : prev) {
                    dfs(p, beginWord, parents, path, result);
                }
            }
        }

        path.remove(path.size() - 1);
    }
}