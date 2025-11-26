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
