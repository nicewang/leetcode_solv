package leetcode.leetcode.editor.en;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Interview0808 {

    public static void main(String[] args) {
        new Interview0808().permutation("qwe");
    }

    List<String> list = new ArrayList<>();
    Stack<String> stack = new Stack<>();
    // 解法一：dfs回溯 超出时间限制
    public String[] permutation(String S) {
        dfs(S);
        String[] res = new String[list.size()];
        for(int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }
    private void dfs(String s) {
        if(s.length() == 1) {
            List<String> sub_list = new ArrayList<>(stack);
            StringBuffer sb = new StringBuffer();
            for(int i = 0; i < sub_list.size(); i++) {
                sb.append(sub_list.get(i));
            }
            sb.append(s);
            if(!list.contains(sb.toString())) {
                list.add(sb.toString());
            }
            return;
        }
        for(int i = 0; i < s.length(); i++) {
            stack.push(s.substring(i, i+1));
            String str = "";
            if(i > 0) {
                str += s.substring(0,i);
            }
            if(i < s.length()-1) {
                str += s.substring(i+1, s.length());
            }
            dfs(str);
            stack.pop();
        }
    }

    // 解法二：15ms 39.7MB
    public String[] permutationTwo(String S) {
        List<String> list = new ArrayList<>();
        list.add(S);
        for (int i = 0; i < S.length() - 1; i++) {
            int size = list.size();
            for (int j = i + 1; j < S.length(); j++) {
                for (int index = 0; index < size; index++) {
                    String s = swap(list.get(index), i, j);
                    if(!list.contains(s)) {
                        list.add(s);
                    }
                }
            }
        }
        return list.toArray(new String[list.size()]);
    }
    //交换位置
    private String swap(String s, int pos1, int pos2) {
        char[] chars = s.toCharArray();
        char tmp = chars[pos1];
        chars[pos1] = chars[pos2];
        chars[pos2] = tmp;
        return new String(chars);
    }

}
