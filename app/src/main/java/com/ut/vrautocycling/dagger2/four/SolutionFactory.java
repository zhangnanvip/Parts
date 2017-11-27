package com.ut.vrautocycling.dagger2.four;

import java.util.Arrays;

/**
 * @author zhangnan
 * @date 2017/11/24
 * 算法原地址： https://leetcode.com/problemset/all/
 */
class SolutionFactory {

    protected interface Solution {

        /**
         * 执行解决方案
         *
         * @return 执行结果
         */
        String handle();
    }

    private SolutionFactory() {
    }

    private static class SolutionFactoryHelper {
        private static final SolutionFactory INSTANCE = new SolutionFactory();
    }

    private static SolutionFactory getInstance() {
        return SolutionFactoryHelper.INSTANCE;
    }

    static Solution obtainSolution(int type) {
        switch (type) {
            case 1:
                return getInstance().new Solution1();
            case 2:
                return getInstance().new Solution2();
            case 3:
                return getInstance().new Solution3();
            default:
                return getInstance().new Solution1();
        }
    }

    /**
     * Given nums = [2, 7, 11, 15], target = 9,
     * Because nums[0] + nums[1] = 2 + 7 = 9,
     * return [0, 1].
     */
    private class Solution1 implements Solution {
        @Override
        public String handle() {
            return Arrays.toString(twoSum(new int[]{3, 2, 4}, 6));
        }

        int[] twoSum(int[] nums, int target) {
            int[] result = new int[2];
            for (int i = 0; i < nums.length - 1; i++) {
                for (int j = i + 1; j < nums.length; j++) {
                    if (nums[i] + nums[j] == target) {
                        result[0] = i;
                        result[1] = j;
                        return result;
                    }
                }
            }
            return result;
        }
    }

    /**
     * You are given two non-empty linked lists representing two non-negative integers.
     * The digits are stored in reverse order and each of their nodes contain a single digit.
     * Add the two numbers and return it as a linked list.
     * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
     * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
     * Output: 7 -> 0 -> 8
     */
    private class Solution2 implements Solution {
        @Override
        public String handle() {
            ListNode l1 = new ListNode(1);
            l1.next = new ListNode(9);
            ListNode l2 = new ListNode(9);
            ListNode result = addTwoNumbers(l1, l2);
            return result.val + " " + result.next.val + " " + result.next.next.val;
        }

        class ListNode {
            int val;
            ListNode next;

            ListNode(int x) {
                val = x;
            }
        }

        ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            ListNode first = new ListNode((l1.val + l2.val) % 10);
            ListNode last = first;
            int carry = (l1.val + l2.val) / 10;
            while (skipNull(l1).next != null || skipNull(l2).next != null) {
                if (l1 == null && l2 == null) {
                    break;
                }
                l1 = skipNull(l1).next;
                l2 = skipNull(l2).next;
                ListNode temp = last;
                int val1 = l1 == null ? 0 : l1.val;
                int val2 = l2 == null ? 0 : l2.val;
                ListNode newNode = new ListNode((val1 + val2 + carry) % 10);
                carry = (val1 + val2 + carry) / 10;
                last = newNode;
                temp.next = newNode;
            }
            if (carry > 0) {
                last.next = new ListNode(carry);
            }
            return first;
        }

        ListNode skipNull(ListNode listNode) {
            if (listNode == null) {
                return new ListNode(0);
            }
            return listNode;
        }
    }

    /**
     * Given a 32-bit signed integer, reverse digits of an integer.
     * <p>
     * Example 1:
     * Input: 123
     * Output:  321
     * <p>
     * Example 2:
     * Input: -123
     * Output: -321
     * <p>
     * Example 3:
     * Input: 120
     * Output: 21
     * <p>
     * Note:
     * Assume we are dealing with an environment which could only hold integers within the 32-bit signed integer range.
     * For the purpose of this problem, assume that your function returns 0 when the reversed integer overflows.
     */
    private class Solution3 implements Solution {
        @Override
        public String handle() {
            return reverse(-123) + "";
        }

        int reverse(int x) {
            String source = x + "";
            boolean hasNegative = source.contains("-");
            String handle = source.replace("-", "");
            char[] c = handle.toCharArray();
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < handle.length(); i++) {
                result.append(c[handle.length() - i - 1]);
            }
            if (hasNegative) {
                return -Integer.parseInt(result.toString());
            }
            return Integer.parseInt(result.toString());
        }
    }
}
