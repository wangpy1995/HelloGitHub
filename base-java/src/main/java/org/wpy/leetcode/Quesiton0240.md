<div class="px-5 pt-4"><div class="_1l1MA">

编写一个高效的算法来搜索&nbsp;`_m_&nbsp;x&nbsp;_n_`&nbsp;矩阵 `matrix` 中的一个目标值 `target` 。该矩阵具有以下特性：

*   每行的元素从左到右升序排列。
*   每列的元素从上到下升序排列。

&nbsp;

**示例 1：**

![](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2020/11/25/searchgrid2.jpg)
<pre>**输入：**matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 5
**输出：**true
</pre>

**示例 2：**

![](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2020/11/25/searchgrid.jpg)
<pre>**输入：**matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 20
**输出：**false
</pre>

&nbsp;

**提示：**

*   `m == matrix.length`
*   `n == matrix[i].length`
*   `1 &lt;= n, m &lt;= 300`
*   `-10<sup>9</sup>&nbsp;&lt;= matrix[i][j] &lt;= 10<sup>9</sup>`
*   每行的所有元素从左到右升序排列
*   每列的所有元素从上到下升序排列
*   `-10<sup>9</sup>&nbsp;&lt;= target &lt;= 10<sup>9</sup>`
</div></div>