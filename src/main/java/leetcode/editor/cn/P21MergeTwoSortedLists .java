//将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 
//
// 
//
// 示例 1： 
// 
// 
//输入：l1 = [1,2,4], l2 = [1,3,4]
//输出：[1,1,2,3,4,4]
// 
//
// 示例 2： 
//
// 
//输入：l1 = [], l2 = []
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：l1 = [], l2 = [0]
//输出：[0]
// 
//
// 
//
// 提示： 
//
// 
// 两个链表的节点数目范围是 [0, 50] 
// -100 <= Node.val <= 100 
// l1 和 l2 均按 非递减顺序 排列 
// 
//
// Related Topics递归 | 链表 
//
// 👍 3122, 👎 0bug 反馈 | 使用指南 | 更多配套插件 
//
//
//
//


package leetcode.editor.cn;

//Java：合并两个有序链表
class P21MergeTwoSortedLists {

    static class ListNode {
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
        Solution solution = new P21MergeTwoSortedLists().new Solution();
        // TO TEST
        ListNode list1 = new ListNode(1);
        list1.next = new ListNode(2);
        list1.next.next = new ListNode(4);
        ListNode list2 = new ListNode(1);
        list2.next = new ListNode(3);
        list2.next.next = new ListNode(4);
        solution.mergeTwoLists(list1, list2);
    }
    //leetcode submit region begin(Prohibit modification and deletion)



    // list1: 1 -> 2 -> 4
    // list2: 1 -> 3 -> 4
    // dummy : -1 -> ....
    class Solution {
        public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
            ListNode t1 = list1;
            ListNode t2 = list2;
            ListNode dummy = new ListNode(-1);
            ListNode t3 = dummy;
            while (t1 != null && t2 != null) {
                if (t1.val <= t2.val) {
                    t3.next = t1;
                    t1 = t1.next;
                } else {
                    t3.next = t2;
                    t2 = t2.next;
                }
                t3 = t3.next;
            }
            while (t1 != null) {
                t3.next = t1;
                t1 = t1.next;
                t3 = t3.next;
            }
            while (t2 != null) {
                t3.next = t2;
                t2 = t2.next;
                t3 = t3.next;
            }
            return dummy.next;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
