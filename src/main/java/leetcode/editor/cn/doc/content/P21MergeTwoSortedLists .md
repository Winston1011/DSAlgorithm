<p>将两个升序链表合并为一个新的 <strong>升序</strong> 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。&nbsp;</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p> 
<img alt="" src="https://assets.leetcode.com/uploads/2020/10/03/merge_ex1.jpg" style="width: 662px; height: 302px;" /> 
<pre>
<strong>输入：</strong>l1 = [1,2,4], l2 = [1,3,4]
<strong>输出：</strong>[1,1,2,3,4,4]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>l1 = [], l2 = []
<strong>输出：</strong>[]
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>l1 = [], l2 = [0]
<strong>输出：</strong>[0]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul> 
 <li>两个链表的节点数目范围是 <code>[0, 50]</code></li> 
 <li><code>-100 &lt;= Node.val &lt;= 100</code></li> 
 <li><code>l1</code> 和 <code>l2</code> 均按 <strong>非递减顺序</strong> 排列</li> 
</ul>

<details><summary><strong>Related Topics</strong></summary>递归 | 链表</details><br>

<div>👍 3122, 👎 0<span style='float: right;'><span style='color: gray;'><a href='https://github.com/labuladong/fucking-algorithm/discussions/939' target='_blank' style='color: lightgray;text-decoration: underline;'>bug 反馈</a> | <a href='https://labuladong.gitee.io/article/fname.html?fname=jb插件简介' target='_blank' style='color: lightgray;text-decoration: underline;'>使用指南</a> | <a href='https://labuladong.github.io/algo/images/others/%E5%85%A8%E5%AE%B6%E6%A1%B6.jpg' target='_blank' style='color: lightgray;text-decoration: underline;'>更多配套插件</a></span></span></div>

<div id="labuladong"><hr>

**通知：[数据结构精品课](https://aep.h5.xeknow.com/s/1XJHEO) 已更新到 V2.1，[手把手刷二叉树系列课程](https://aep.xet.tech/s/3YGcq3) 上线。**



<p><strong><a href="https://labuladong.gitee.io/article/slug.html?slug=merge-two-sorted-lists" target="_blank">⭐️labuladong 题解</a></strong></p>
<details><summary><strong>labuladong 思路</strong></summary>

## 基本思路

> 本文有视频版：[链表双指针技巧全面汇总](https://www.bilibili.com/video/BV1q94y1X7vy)

经典算法题了，[双指针技巧](https://labuladong.github.io/article/fname.html?fname=链表技巧) 用起来。

![](https://labuladong.github.io/pictures/链表技巧/1.gif)

这个算法的逻辑类似于「拉拉链」，`l1, l2` 类似于拉链两侧的锯齿，指针 `p` 就好像拉链的拉索，将两个有序链表合并。

**代码中还用到一个链表的算法题中是很常见的「虚拟头结点」技巧，也就是 `dummy` 节点**，它相当于是个占位符，可以避免处理空指针的情况，降低代码的复杂性。

**详细题解：[双指针技巧秒杀七道链表题目](https://labuladong.github.io/article/fname.html?fname=链表技巧)**

**标签：[数据结构](https://mp.weixin.qq.com/mp/appmsgalbum?__biz=MzAxODQxMDM0Mw==&action=getalbum&album_id=1318892385270808576)，[链表](https://mp.weixin.qq.com/mp/appmsgalbum?__biz=MzAxODQxMDM0Mw==&action=getalbum&album_id=2120596033251475465)，[链表双指针](https://mp.weixin.qq.com/mp/appmsgalbum?__biz=MzAxODQxMDM0Mw==&action=getalbum&album_id=2120596033251475465)**

## 解法代码

提示：🟢 标记的是我写的解法代码，🤖 标记的是 chatGPT 翻译的多语言解法代码。如有错误，可以 [点这里](https://github.com/labuladong/fucking-algorithm/issues/1113) 反馈和修正。

<div class="tab-panel"><div class="tab-nav">
<button data-tab-item="cpp" class="tab-nav-button btn " data-tab-group="default" onclick="switchTab(this)">cpp🤖</button>

<button data-tab-item="python" class="tab-nav-button btn " data-tab-group="default" onclick="switchTab(this)">python🤖</button>

<button data-tab-item="java" class="tab-nav-button btn active" data-tab-group="default" onclick="switchTab(this)">java🟢</button>

<button data-tab-item="go" class="tab-nav-button btn " data-tab-group="default" onclick="switchTab(this)">go🤖</button>

<button data-tab-item="javascript" class="tab-nav-button btn " data-tab-group="default" onclick="switchTab(this)">javascript🤖</button>
</div><div class="tab-content">
<div data-tab-item="cpp" class="tab-item " data-tab-group="default"><div class="highlight">

```cpp
// 注意：cpp 代码由 chatGPT🤖 根据我的 java 代码翻译，旨在帮助不同背景的读者理解算法逻辑。
// 本代码已经通过力扣的测试用例，应该可直接成功提交。

class Solution {
public:
    ListNode* mergeTwoLists(ListNode* l1, ListNode* l2) {
        // 虚拟头结点
        ListNode* dummy = new ListNode(-1), *p = dummy;
        ListNode* p1 = l1, *p2 = l2;

        while (p1 != nullptr && p2 != nullptr) {/**<extend down -200>![](https://labuladong.github.io/pictures/链表技巧/1.gif) */
            // 比较 p1 和 p2 两个指针
            // 将值较小的的节点接到 p 指针
            if (p1->val > p2->val) {
                p->next = p2;
                p2 = p2->next;
            } else {
                p->next = p1;
                p1 = p1->next;
            }
            // p 指针不断前进
            p = p->next;
        }

        if (p1 != nullptr) {
            p->next = p1;
        }

        if (p2 != nullptr) {
            p->next = p2;
        }

        return dummy->next;
    }
};
```

</div></div>

<div data-tab-item="python" class="tab-item " data-tab-group="default"><div class="highlight">

```python
# 注意：python 代码由 chatGPT🤖 根据我的 java 代码翻译，旨在帮助不同背景的读者理解算法逻辑。
# 本代码已经通过力扣的测试用例，应该可直接成功提交。

class Solution:
    def mergeTwoLists(self, l1: ListNode, l2: ListNode) -> ListNode:
        # 虚拟头结点
        dummy = ListNode(-1)
        p = dummy
        p1 = l1
        p2 = l2

        while p1 and p2: # <extend down -200>![](https://labuladong.github.io/pictures/链表技巧/1.gif) #
            # 比较 p1 和 p2 两个指针
            # 将值较小的的节点接到 p 指针
            if p1.val > p2.val:
                p.next = p2
                p2 = p2.next
            else:
                p.next = p1
                p1 = p1.next
            # p 指针不断前进
            p = p.next

        if p1:
            p.next = p1

        if p2:
            p.next = p2

        return dummy.next
```

</div></div>

<div data-tab-item="java" class="tab-item active" data-tab-group="default"><div class="highlight">

```java
class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        // 虚拟头结点
        ListNode dummy = new ListNode(-1), p = dummy;
        ListNode p1 = l1, p2 = l2;

        while (p1 != null && p2 != null) {/**<extend down -200>![](https://labuladong.github.io/pictures/链表技巧/1.gif) */
            // 比较 p1 和 p2 两个指针
            // 将值较小的的节点接到 p 指针
            if (p1.val > p2.val) {
                p.next = p2;
                p2 = p2.next;
            } else {
                p.next = p1;
                p1 = p1.next;
            }
            // p 指针不断前进
            p = p.next;
        }

        if (p1 != null) {
            p.next = p1;
        }

        if (p2 != null) {
            p.next = p2;
        }

        return dummy.next;
    }
}
```

</div></div>

<div data-tab-item="go" class="tab-item " data-tab-group="default"><div class="highlight">

```go
// 注意：go 代码由 chatGPT🤖 根据我的 java 代码翻译，旨在帮助不同背景的读者理解算法逻辑。
// 本代码已经通过力扣的测试用例，应该可直接成功提交。

/**
 * Definition for singly-linked list.
 * type ListNode struct {
 *     Val int
 *     Next *ListNode
 * }
 */
func mergeTwoLists(l1 *ListNode, l2 *ListNode) *ListNode {
    // 虚拟头结点
    dummy := &ListNode{-1, nil}
    p := dummy
    p1 := l1
    p2 := l2

    for p1 != nil && p2 != nil {/**<extend down -200>![](https://labuladong.github.io/pictures/链表技巧/1.gif) */
        // 比较 p1 和 p2 两个指针
        // 将值较小的的节点接到 p 指针
        if p1.Val > p2.Val {
            p.Next = p2
            p2 = p2.Next
        } else {
            p.Next = p1
            p1 = p1.Next
        }
        // p 指针不断前进
        p = p.Next
    }

    if p1 != nil {
        p.Next = p1
    }

    if p2 != nil {
        p.Next = p2
    }

    return dummy.Next
}
```

</div></div>

<div data-tab-item="javascript" class="tab-item " data-tab-group="default"><div class="highlight">

```javascript
// 注意：javascript 代码由 chatGPT🤖 根据我的 java 代码翻译，旨在帮助不同背景的读者理解算法逻辑。
// 本代码已经通过力扣的测试用例，应该可直接成功提交。

/**
 * @param {ListNode} l1
 * @param {ListNode} l2
 * @return {ListNode}
 */
var mergeTwoLists = function(l1, l2) {
    // 虚拟头结点
    var dummy = new ListNode(-1), p = dummy;
    var p1 = l1, p2 = l2;

    while (p1 !== null && p2 !== null) {/**<extend down -200>![](https://labuladong.github.io/pictures/链表技巧/1.gif) */
        // 比较 p1 和 p2 两个指针
        // 将值较小的的节点接到 p 指针
        if (p1.val > p2.val) {
            p.next = p2;
            p2 = p2.next;
        } else {
            p.next = p1;
            p1 = p1.next;
        }
        // p 指针不断前进
        p = p.next;
    }

    if (p1 !== null) {
        p.next = p1;
    }

    if (p2 !== null) {
        p.next = p2;
    }

    return dummy.next;
};
```

</div></div>
</div></div>

<details open><summary><strong>👉 算法可视化 👈</strong></summary><div class="resizable aspect-ratio-container" style="height: 70vh;">
    <iframe src="https://labuladong.github.io/algo-visualize/" width="100%"
    height="100%" scrolling="auto" frameborder="0"
    style="overflow: auto;" id="iframe_merge-two-sorted-lists"></iframe>
</div>
<div id="data_merge-two-sorted-lists" data="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"></div></details><hr /><br />

**类似题目**：
  - [1305. 两棵二叉搜索树中的所有元素 🟠](/problems/all-elements-in-two-binary-search-trees)
  - [141. 环形链表 🟢](/problems/linked-list-cycle)
  - [142. 环形链表 II 🟠](/problems/linked-list-cycle-ii)
  - [160. 相交链表 🟢](/problems/intersection-of-two-linked-lists)
  - [19. 删除链表的倒数第 N 个结点 🟠](/problems/remove-nth-node-from-end-of-list)
  - [23. 合并K个升序链表 🔴](/problems/merge-k-sorted-lists)
  - [264. 丑数 II 🟠](/problems/ugly-number-ii)
  - [313. 超级丑数 🟠](/problems/super-ugly-number)
  - [86. 分隔链表 🟠](/problems/partition-list)
  - [876. 链表的中间结点 🟢](/problems/middle-of-the-linked-list)
  - [88. 合并两个有序数组 🟢](/problems/merge-sorted-array)
  - [97. 交错字符串 🟠](/problems/interleaving-string)
  - [977. 有序数组的平方 🟢](/problems/squares-of-a-sorted-array)
  - [剑指 Offer 22. 链表中倒数第k个节点 🟢](/problems/lian-biao-zhong-dao-shu-di-kge-jie-dian-lcof)
  - [剑指 Offer 25. 合并两个排序的链表 🟢](/problems/he-bing-liang-ge-pai-xu-de-lian-biao-lcof)
  - [剑指 Offer 49. 丑数 🟠](/problems/chou-shu-lcof)
  - [剑指 Offer 52. 两个链表的第一个公共节点 🟢](/problems/liang-ge-lian-biao-de-di-yi-ge-gong-gong-jie-dian-lcof)
  - [剑指 Offer II 021. 删除链表的倒数第 n 个结点 🟠](/problems/SLwz0R)
  - [剑指 Offer II 022. 链表中环的入口节点 🟠](/problems/c32eOV)
  - [剑指 Offer II 023. 两个链表的第一个重合节点 🟢](/problems/3u1WK4)
  - [剑指 Offer II 078. 合并排序链表 🔴](/problems/vvXgSW)

</details>
</div>



