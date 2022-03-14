package data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author pengmf
 * @since 2022/1/21
 */
public class Solution {


    public static void main(String[] args) {
        Solution solution = new Solution();
        String s = "We are happy.";

        ListNode listNode1 = new ListNode(1);
        ListNode listNode3 = new ListNode(3);
        ListNode listNode2 = new ListNode(4);

        listNode1.next = listNode3;
        listNode3.next = listNode2;

        solution.reversePrint(listNode1);

    }

    public int[] reversePrint(ListNode head) {
        if (head == null) {
            return new int[]{};
        }
        List<Integer> list = new ArrayList<>();
        list.add(head.val);
        ListNode root = head;
        int count = 1;
        while (root.next != null) {
            root = root.next;
            list.add(root.val);
            count++;
        }

        int[] res = new int[count];

        for (int i = count - 1; i >= 0; i--) {
            res[count - i - 1] = list.get(i);
        }

        return res;
    }


    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
