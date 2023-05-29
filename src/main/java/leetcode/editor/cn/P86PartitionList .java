//给你一个链表的头节点 head 和一个特定值 x ，请你对链表进行分隔，使得所有 小于 x 的节点都出现在 大于或等于 x 的节点之前。 
//
// 你应当 保留 两个分区中每个节点的初始相对位置。 
//
// 
//
// 示例 1： 
// 
// 
//输入：head = [1,4,3,2,5,2], x = 3
//输出：[1,2,2,4,3,5]
// 
//
// 示例 2： 
//
// 
//输入：head = [2,1], x = 2
//输出：[1,2]
// 
//
// 
//
// 提示： 
//
// 
// 链表中节点的数目在范围 [0, 200] 内 
// -100 <= Node.val <= 100 
// -200 <= x <= 200 
// 
//
// Related Topics链表 | 双指针 
//
// 👍 716, 👎 0bug 反馈 | 使用指南 | 更多配套插件 
//
//
//
//


package leetcode.editor.cn;

import java.util.List;

//Java：分隔链表
class P86PartitionList {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        Solution solution = new P86PartitionList().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode() {}
     * ListNode(int val) { this.val = val; }
     * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     * }
     */
    class Solution {

        //输入：head = [1,4,3,2,5,2], x = 3
        //输出：[1,2,2,4,3,5]
        public ListNode partition(ListNode head, int x) {
            ListNode t1 = head;
            ListNode dummy1 = new ListNode(-1);
            ListNode dummy2 = new ListNode(-1);
            ListNode t2 = dummy1;
            ListNode t3 = dummy2;
            while (t1 != null) {
                if (t1.val < x) {
                    t2.next = new ListNode(t1.val);
                    t2 = t2.next;
                } else {
                    t3.next = new ListNode(t1.val);
                    t3 = t3.next;
                }
                t1 = t1.next;
            }
            t2.next = dummy2.next;
            return dummy1.next;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
