<?php
/**
 * Definition for a binary tree node.
 * class TreeNode {
 *     public $val = null;
 *     public $left = null;
 *     public $right = null;
 *     function __construct($value) { $this->val = $value; }
 * }
 */
class Solution {

    /**
     * @param TreeNode $root
     * @return Integer[]
     */
    function inorderTraversal($root) {
        $res = [];
        $this->helper($root,$res);
        return $res;
    }

    function helper($root,&$res){
        if($root == null) return;
        $this->helper($root->left,$res);
        $res[] = $root->val;
        $this->helper($root->right,$res);
    }
}