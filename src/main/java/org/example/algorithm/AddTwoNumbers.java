package org.example.algorithm;

/*
给你两个非空的链表，表示两个非负的整数。它们每位数字都是按照逆序的方式存储的，并且每个节点只能存储一位数字。
请你将两个数相加，并以相同形式返回一个表示和的链表。你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
示例：
2—>4—>3
5—>6—>4
——————-
7—>0—>8
输入：l1 = [2,4,3], l2 = [5,6,8]
输出：[7,0,8]
解释：342 + 865 = 1207, 链表最左边表示个位数，代表 342 + 865 =1207。

时间复杂度：
O（max（m，n）），m 和 n 代表 l1 和 l2 的长度。

空间复杂度：
O（max（m，n）），m 和 n 代表 l1 和 l2 的长度。而其实新的 List 最大长度是 O（max（m，n））+ 1，因为我们的 head 没有存储值。
 */
public class AddTwoNumbers {
    public static void main(String[] args) {
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(3);

        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(8);

        ListNode head = addTwoNumbers(l1, l2);
        printListNode(head);
    }

    private static void printListNode(ListNode head) {
        ListNode current = head;
        while (current != null) {
            System.out.print(current.val + " ");
            current = current.next;
        }
    }

    private static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // 初始化一个节点的头dummy head ，但是这个头不存储数字。并且将 curr 指向它。
        ListNode dummyHead = new ListNode(0);
        ListNode current = dummyHead;

        //每一位相加可能会产生进位，我们用 carry 表示。
        int carry = 0;

        // 循环，直到 l1 和 l2 全部到达 null
        while (l1 != null || l2 != null) {
            // 获取每个链表当前节点的值，如果节点为空则视为0
            int x = l1 != null ? l1.val : 0;
            int y = l2 != null ? l2.val : 0;

            // 计算和及进位(商，0或1，进位则是1，无进位则是0)
            int sum = carry + x + y;
            carry = sum / 10;

            // 创建一个值为 sum mod 10 的节点，并将 current 的 next 指向它
            current.next = new ListNode(sum % 10);
            // 同时 current 指向变为当前的新节点, 但是dummyHead.next指向的是最终结果的第一个节点，即个位
            // 第二次循环进行后，dummyHead.next.next将指向十位；后面以此类推指向百位，千位...
            // 本算法的关键点就在：
            // 1.dummyHead和current指向相同的对象；
            // 2.不断地调整current和current.next的值，让结果链表完整；
            // 3.当current和current.next的值改变时，结果链表将跟随改变。
            current = current.next;

            // 向前移动 l1 和 l2
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }

        // 判断位数少的数字最后一位相加结果 carry 是否等于 1，如果是则在链表末尾增加一个为 1 的节点（进位了）。
        if (carry > 0) {
            current.next = new ListNode(carry);
        }

        // 返回结果链表的头节点，也就是个位数开始的地方
        return  dummyHead.next;
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
