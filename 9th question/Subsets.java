/*
 * Subsets – getSubSets
 *
 * This project implements a function getSubSets in Java to compute all subsets
 * of a given set A. The input set has at most 4 elements and is represented
 * using the Set<Element> interface.
 *
 * ------------------------------------------------------------
 * Goal
 *
 * Given a set A, return a Set<Set<Element>> containing:
 * - every possible subset of A (including the empty set and A itself)
 * - without duplicates
 * - independent of order (because we use Set)
 *
 * Example:
 * Input:  A = [1, 2, 3]
 * Output (order irrelevant):
 *   [{1, 2, 3}, {1, 2}, {1, 3}, {2, 3}, {1}, {2}, {3}, {}]
 *
 * ------------------------------------------------------------
 * Main Function
 *
 * public static Set<Set<Element>> getSubSets(Set<Element> inputSet)
 *
 * Return value:
 * - A Set<Set<Element>> where each inner Set<Element> is one subset
 *   of the original set.
 *
 * ------------------------------------------------------------
 * How the algorithm works
 *
 * 1. Copy elements to an array
 *
 *    Element[] elements = inputSet.toArray(new Element[0]);
 *    int n = elements.length;
 *
 *    Using an array makes it easy to access elements by index.
 *
 * 2. Use bitmasks to generate subsets
 *
 *    - There are 2^n possible subsets of a set with n elements.
 *    - Loop over all integers mask from 0 to (2^n - 1).
 *    - In binary, each mask indicates which elements belong to the subset:
 *         bit i = 1 → element at index i is included
 *         bit i = 0 → element at index i is not included
 *
 * 3. Build each subset
 *
 *    for (int mask = 0; mask < totalMasks; mask++) {
 *        Set<Element> subset = new HashSet<>();
 *
 *        for (int i = 0; i < n; i++) {
 *            if ((mask & (1 << i)) != 0) {
 *                subset.add(elements[i]);
 *            }
 *        }
 *
 *        allSubsets.add(subset);
 *    }
 *
 *    - mask == 0 → empty set
 *    - mask == (2^n - 1) → full set
 *
 * 4. Store all subsets in a Set of Sets
 *
 *    - allSubsets is a Set<Set<Element>>.
 *    - Using a Set guarantees:
 *         no duplicate subsets
 *         subset order does not matter
 *
 * 5. Return the result:
 *
 *    return allSubsets;
 *
 * ------------------------------------------------------------
 * Complexity
 *
 * - Number of subsets: 2^n (with n ≤ 4).
 * - Time complexity: O(n * 2^n)
 * - Space complexity: O(n * 2^n)
 *
 * ------------------------------------------------------------
 * Example usage:
 *
 *    Set<Element> a = new HashSet<>();
 *    a.add(new Element(1));
 *    a.add(new Element(2));
 *    a.add(new Element(3));
 *
 *    Set<Set<Element>> subsets = getSubSets(a);
 *
 *    for (Set<Element> subset : subsets) {
 *        System.out.println(subset);
 *    }
 *
 */

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class SubsetsExample {

    public static Set<Set<Element>> getSubSets(Set<Element> inputSet) {
        Set<Set<Element>> allSubsets = new HashSet<>();

        // Convert set to array to access elements by index
        Element[] elements = inputSet.toArray(new Element[0]);
        int n = elements.length;
        int totalMasks = 1 << n; // 2^n subsets

        // For each bitmask, build the corresponding subset
        for (int mask = 0; mask < totalMasks; mask++) {
            Set<Element> subset = new HashSet<>();

            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) {
                    subset.add(elements[i]);
                }
            }

            allSubsets.add(subset);
        }

        return allSubsets;
    }

    /**
     * The main method will:
     * 1. Build the input set A.
     * 2. Call getSubSets(A) to compute all subsets of A.
     * 3. Iterate over the returned set of subsets and print each subset.
     */
    public static void main(String[] args) {
        Set<Element> a = new HashSet<>();
        a.add(new Element(1));
        a.add(new Element(2));
        a.add(new Element(3));

        Set<Set<Element>> subsets = getSubSets(a);

        for (Set<Element> subset : subsets) {
            System.out.println(subset);
        }
    }
}

// Example Element implementation just for completeness
class Element {
    private final int value;

    public Element(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(value);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Element)) {
            return false;
        }
        Element other = (Element) obj;
        return this.value == other.value;
    }
}

