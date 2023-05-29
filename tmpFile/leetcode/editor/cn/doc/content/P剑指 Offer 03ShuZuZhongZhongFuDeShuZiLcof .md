<p>找出数组中重复的数字。</p>

<p><br> 在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。请找出数组中任意一个重复的数字。</br></p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>
[2, 3, 1, 0, 2, 5, 3]
<strong>输出：</strong>2 或 3 
</pre>

<p>&nbsp;</p>

<p><strong>限制：</strong></p>

<p><code>2 &lt;= n &lt;= 100000</code></p>

<details><summary><strong>Related Topics</strong></summary>数组 | 哈希表 | 排序</details><br>

<div>👍 1174, 👎 0<span style='float: right;'><span style='color: gray;'><a href='https://github.com/labuladong/fucking-algorithm/discussions/939' target='_blank' style='color: lightgray;text-decoration: underline;'>bug 反馈</a> | <a href='https://labuladong.gitee.io/article/fname.html?fname=jb插件简介' target='_blank' style='color: lightgray;text-decoration: underline;'>使用指南</a> | <a href='https://labuladong.github.io/algo/images/others/%E5%85%A8%E5%AE%B6%E6%A1%B6.jpg' target='_blank' style='color: lightgray;text-decoration: underline;'>更多配套插件</a></span></span></div>

<div id="labuladong"><hr>

**通知：[数据结构精品课](https://aep.h5.xeknow.com/s/1XJHEO) 已更新到 V2.1，[手把手刷二叉树系列课程](https://aep.xet.tech/s/3YGcq3) 上线。**

<details><summary><strong>labuladong 思路</strong></summary>

## 基本思路

这道题和 [442. 数组中重复的数据](/problems/find-all-duplicates-in-an-array) 几乎一样，可以先去看看我写的那道题的思路。

这道题唯一的区别就是不需要做索引偏移了，而且需要把 0 拿出来单独处理，直接看解法吧。

**标签：哈希表，[数组](https://mp.weixin.qq.com/mp/appmsgalbum?__biz=MzAxODQxMDM0Mw==&action=getalbum&album_id=2120601117519675393)**

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
// 本代码还未经过力扣测试，仅供参考，如有疑惑，可以参照我写的 java 代码对比查看。

class Solution {
public:
    int findRepeatNumber(vector<int>& nums) {
        for (int num : nums) {
            if (nums[abs(num)] < 0) {
                // 之前已经把对应索引的元素变成负数了，
                // 这说明 num 重复出现了两次
                return abs(num);
            } else {
                // 把索引 num 的元素置为负数
                nums[abs(num)] *= -1;
            }
        }
        // 如果没有在 for 循环中返回，说明重复的那个元素是 0
        return 0;
    }
};
```

</div></div>

<div data-tab-item="python" class="tab-item " data-tab-group="default"><div class="highlight">

```python
# 注意：python 代码由 chatGPT🤖 根据我的 java 代码翻译，旨在帮助不同背景的读者理解算法逻辑。
# 本代码已经通过力扣的测试用例，应该可直接成功提交。

class Solution:
    def findRepeatNumber(self, nums: List[int]) -> int:
        for num in nums:
            if nums[abs(num)] < 0:
                # 之前已经把对应索引的元素变成负数了，
                # 这说明 num 重复出现了两次
                return abs(num)
            else:
                # 把索引 num 的元素置为负数
                nums[abs(num)] *= -1
        # 如果没有在 for 循环中返回，说明重复的那个元素是 0
        return 0
```

</div></div>

<div data-tab-item="java" class="tab-item active" data-tab-group="default"><div class="highlight">

```java
class Solution {
    public int findRepeatNumber(int[] nums) {
        for (int num : nums) {
            if (nums[Math.abs(num)] < 0) {
                // 之前已经把对应索引的元素变成负数了，
                // 这说明 num 重复出现了两次
                return Math.abs(num);
            } else {
                // 把索引 num 的元素置为负数
                nums[Math.abs(num)] *= -1;
            }
        }
        // 如果没有在 for 循环中返回，说明重复的那个元素是 0
        return 0;
    }
}
```

</div></div>

<div data-tab-item="go" class="tab-item " data-tab-group="default"><div class="highlight">

```go
// 注意：go 代码由 chatGPT🤖 根据我的 java 代码翻译，旨在帮助不同背景的读者理解算法逻辑。
// 本代码已经通过力扣的测试用例，应该可直接成功提交。

func findRepeatNumber(nums []int) int {
    for _, num := range nums {
        if nums[abs(num)] < 0 {
            // 之前已经把对应索引的元素变成负数了，
            // 这说明 num 重复出现了两次
            return abs(num)
        } else {
            // 把索引 num 的元素置为负数
            nums[abs(num)] *= -1
        }
    }
    // 如果没有在 for 循环中返回，说明重复的那个元素是 0
    return 0
}

func abs(num int) int {
    if num < 0 {
        return -num
    }
    return num
}
```

</div></div>

<div data-tab-item="javascript" class="tab-item " data-tab-group="default"><div class="highlight">

```javascript
// 注意：javascript 代码由 chatGPT🤖 根据我的 java 代码翻译，旨在帮助不同背景的读者理解算法逻辑。
// 本代码已经通过力扣的测试用例，应该可直接成功提交。

/**
 * @param {number[]} nums
 * @return {number}
 */
var findRepeatNumber = function(nums) {
    for (let num of nums) {
        if (nums[Math.abs(num)] < 0) {
            // 之前已经把对应索引的元素变成负数了，
            // 这说明 num 重复出现了两次
            return Math.abs(num);
        } else {
            // 把索引 num 的元素置为负数
            nums[Math.abs(num)] *= -1;
        }
    }
    // 如果没有在 for 循环中返回，说明重复的那个元素是 0
    return 0;
};
```

</div></div>
</div></div>

<details open><summary><strong>👉 算法可视化 👈</strong></summary><div class="resizable aspect-ratio-container" style="height: 70vh;">
    <iframe src="https://labuladong.github.io/algo-visualize/" width="100%"
    height="100%" scrolling="auto" frameborder="0"
    style="overflow: auto;" id="iframe_shu-zu-zhong-zhong-fu-de-shu-zi-lcof"></iframe>
</div>
<div id="data_shu-zu-zhong-zhong-fu-de-shu-zi-lcof" data="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"></div></details><hr /><br />

</details>
</div>



