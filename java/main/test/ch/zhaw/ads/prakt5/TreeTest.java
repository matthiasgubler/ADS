/**
 * @(#)TreeTest.java
 * @author
 * @version 1.00 2018/3/17
 */

package ch.zhaw.ads.prakt5;

import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TreeTest {
    Tree<String> tree;

    @Before
    public void setUp() throws Exception {
        tree = new SortedBinaryTree<String>();
        tree.add("B");
        tree.add("A");
        tree.add("C");
        tree.add("D");
    }

    @Test
    public void testInorder() {
        Visitor<String> v = new MyVisitor<String>();
        tree.traversal().inorder(v);
        assertEquals("inorder", "ABCD", v.toString());
    }

    @Test
    public void testPreorder() {
        Visitor<String> v = new MyVisitor<String>();
        tree.traversal().preorder(v);
        assertEquals("preorder", "BACD", v.toString());
    }

    @Test
    public void testPostorder() {
        Visitor<String> v = new MyVisitor<String>();
        tree.traversal().postorder(v);
        assertEquals("postorder", "ADCB", v.toString());
    }

    @Test
    public void testLevelorder() {
        Visitor<String> v = new MyVisitor<String>();
        tree.traversal().levelorder(v);
        assertEquals("levelorder", "BACD", v.toString());
    }

    @Test
    public void testMixed() {
        tree = new SortedBinaryTree<>();
        List<String> list = new LinkedList<>();
        for (int i = 0; i < 1000; i++) {
            Character c = (char) ('A' + (Math.random() * 26));
            int op = (int) (Math.random() * 2);
            switch (op) {
                case 0:
                    list.add(c.toString());
                    tree.add(c.toString());
                    break;
                case 1:
                    list.remove(c.toString());
                    tree.remove(c.toString());
                    break;
            }
        }
        assertEquals(tree.size(), list.size());
        System.out.println("" + tree.size());
        Collections.sort(list);
        StringBuilder b = new StringBuilder();
        for (String s : list) {
            b.append(s);
        }
        ;
        Visitor<String> v = new MyVisitor<String>();
        tree.traversal().inorder(v);
        assertEquals("mixed", b.toString(), v.toString());
    }

    @Test
    public void testInterval() {
        for (int i = 0; i < 200; i++) {
            Character c = (char) ('A' + (Math.random() * 26));
            tree.add(c.toString());
        }
        Visitor<String> v = new MyVisitor<>();
        char left = 'K';
        char right = 'O';
        tree.traversal().interval(((Character) left).toString(), ((Character) right).toString(), v);
        String s = v.toString();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            assertTrue(String.format("c=%c; left=%c; right=%c;", c, left, right), c >= left && c <= right);
        }
    }
}

class MyVisitor<T> implements Visitor<T> {
    StringBuilder output;

    MyVisitor() {
        output = new StringBuilder();
    }

    public void visit(T s) {
        output.append(s);
    }

    public String toString() {
        return output.toString();
    }
}
