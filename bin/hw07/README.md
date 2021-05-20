# HW07_Buy Phone

[![hackmd-github-sync-badge](https://hackmd.io/vKNw7zRnRo2J43psbRxhBQ/badge)](https://hackmd.io/vKNw7zRnRo2J43psbRxhBQ)


###### tags: `演算法` `Java`

## 題目

給你一個二維陣列((x1, y1), (x2, y2), ... , (xn, yn))，x代表手機的螢幕大小，y代表手機的效能表現，請列出無人能取代的手機。

**所謂無人能取代的手機，就是指沒有任何一台手機的x和y兩者都大於等於該手機的x和y。**

* output必須排序好
* 不會出現x和y同時一樣的測資

### example
```
Input: [[1,1],[2,4],[2,10],[5,4],[4,8],[5,5],[8,4],[10,2],[10,1]]

Output: [[2,10],[4,8],[5,5],[8,4],[10,2]]
```

## 解題想法
### 暴力解
兩個for迴圈了事，固定x，找有沒有x和y都比自己大的手機，複雜度為O(N^2^)

### Insertion Sort
先將陣列依照x排序(主)y排序(副)，不一定要用Insertion Sort，只是覺得助教的測資好像是接近排序好的測資，這樣的話Insertion Sort就會很快。

排好後，觀察排序好的陣列，從陣列尾向前掃描，讓最後一個數字的x=rx，y=ry，因為前面都是x比你小的數字，所以如果y小於ry，就是要被淘汰的了，但如果y比大於ry，則把他加入答案中，並讓他成為新的rx和ry。整個陣列掃完，答案區中的就是答案了。

時間複雜度: **Insertion Sort(best case: O(N), worst case: O((1/2)\*N^2^)) + scan array(N) = O(N ~ (1/2)\*N^2^)?)**

### Two Stack
Insertion Sort版本最花時間的部分就在於sort，所以我就開始想，可不可以不要sort呢?

**答案是可以的，解法是用兩個stack。**

概念是這樣的，stack1是用來存目前的答案，stack2則用來存暫時拿出去的手機。

因為在沒排序好的陣列中，可能會遇到新來手機的x比之前的x都還大，這時必須將新手機安插在stack1中的某個位置，此時就需要先將前面的手機暫存在stack2中，再放置新手機到指定位置，然後才將stack2的手機搬回stack1。

初始狀態

![](https://i.imgur.com/w5ymLml.jpg)

最後一個先push stack1 push

![](https://i.imgur.com/m2txNqx.jpg)

3小於9，但7大於2，所以push stack1

![](https://i.imgur.com/Pq6UQnW.jpg)

2小於3，且6小於7，所以什麼事都不做

![](https://i.imgur.com/ysIqMHt.jpg)

1小於3，但8大於7，所以push stack1

![](https://i.imgur.com/aM8CPfa.jpg)

因為8大於1，所以將(1,8) push stack2

![](https://i.imgur.com/UMKbb2G.jpg)

因為8還是大於3，所以將(3,7) push stack2

![](https://i.imgur.com/QJ7IOJj.jpg)

雖然8小於9，但1也小於2，所以(8,1)不能push stack1。  
最後將stack2所有值放回stack1。

![](https://i.imgur.com/Gv9T9j9.jpg)

stack1反過來就是答案了。

![](https://i.imgur.com/SHJa4iD.jpg)

小提示: 如果還是不懂，可以觀察答案會發現**x呈現遞增，而y呈現遞減**，從這個觀點會好想很多。

時間複雜度: **two stack(best case: O(N), worst case: O((1/2)\*N^2^)) = O(N ~ (1/2)\*N^2^)?)**

沒有sort雖然表面上是O(N)，但其實返回pop、push其實也要花時間，所以應該是取決於測資啦，測資越接近排好越有利(好像跟Insertion Sort一樣嘛😅，到底會不會比較快，明天見真章)
> 有啦，快那一點點

### Merge Sort
結果助教測資好像不是我想的接近排好的陣列，因為很多人都說用MergeSort比較快，好吧...於是就產生了MergeSort版本。

MergeSort的運作方式就不講了，我的[Algorithm_example>>sorting](https://github.com/AndyChiangSH/1092_Algorithm_example/tree/master/src/sorting)裡就有講了。時間複雜度固定是: **O(NlogN)**

### two 1D-array
[@Forcer0625](https://github.com/Forcer0625) 學長說兩個一維陣列會比一個二維陣列還更快，明天就知道是不是對的了~

### Counting table
傳承 Counting sort 的精神，可以不要sort就不要sort!

我直接建一個大到爆的array，index為x，index對應的value則y，掃第一遍時紀錄每個x對應到的y中的最大值，順便找x的最大值和最小值，以減少掃第二遍的時間。

掃第二遍時和以前一樣的找法，只是改成在counting table上掃描，並把答案存在另外一個空陣列中。

這個方法的時間複雜度為: **O(N+R)**，完全就是邪魔歪道XD，而且還不僅如此，第一次counting table還可以用執行序再加速www。

> N為陣列大小，R為最大值和最小值的差

## 版本
1. Insertion Sort: O(N ~ (1/2)*N^2)
2. two stack: O(N ~ (1/2)*N^2)
3. Merge Sort: O(NligN + N)
4. Merge Sort + two 1D-array: O(NligN + N)
5. Counting table: O(2N)

## 排名
2021/04/25 6:00

![](https://i.imgur.com/1u9Qdoq.png)
