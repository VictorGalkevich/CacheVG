package cachevg.types;

import java.time.OffsetDateTime;
import java.util.Iterator;
import java.util.LinkedList;

public class ConnectedList extends AbstractValue<LinkedList<String>> {
    public ConnectedList(LinkedList<String> value, OffsetDateTime ttl, OffsetDateTime createdAt) {
        super(value, ttl, createdAt);
    }

    /**
     * Appends the specified element to the beginning of this list.
     * @param value - the element to be pushed to the list
     * @return 1 - if the element was added successfully, 2 - when value wasn't added to the list
     */
    public int leftPush(String value) {
        boolean b = this.value.offerFirst(value);
        return b ? 1 : 0;
    }

    /**
     * Appends the specified element to the end of this list.
     * @param value - the element to be pushed to the list
     * @return 1 - if the element was added successfully, 2 - when value wasn't added to the list
     */
    public int rightPush(String value) {
        boolean b = this.value.offerLast(value);
        return b ? 1 : 0;
    }

    /**
     * Removes the specified element from the end of this list.
     * @return *Element* if it was removed successfully, *nil* when list was empty
     */
    public String rightPop() {
        if (this.value.isEmpty()) {
            return "nil";
        }
        return this.value.pollLast();
    }

    /**
     * Removes the specified element from the beginning of this list.
     * @return *Element* if it was removed successfully, *nil* when list was empty
     */
    public String leftPop() {
        if (this.value.isEmpty()) {
            return "nil";
        }
        return this.value.pollFirst();
    }

    /**
     * Returns the list of all elements in this list
     * @param from first element index to output
     * @param to last element index to output
     * @return list of all the elements in the specified range
     */
    public String list(int from, int to) {
        if (from >= this.value.size()) {
            return "from > list size";
        }
        StringBuilder builder = new StringBuilder();
        String template = "%d) %s";
        int counter = 0;
        LinkedList<String> list = this.value;
        Iterator<String> iterator = list.iterator();
        if (to != -1) {
            while (iterator.hasNext() && counter <= to) {
                String next = iterator.next();
                if (counter >= from) {
                    counter++;
                    builder.append(String.format(template, counter - from, next)).append("\n");
                }
            }
        } else {
            while (iterator.hasNext()) {
                String next = iterator.next();
                counter++;
                builder.append(String.format(template, counter, next)).append("\n");
            }
        }
        return builder.toString();
    }

    /**
     * Returns the number of elements in this list.
     *
     * @return the number of elements in this list
     */
    public int length() {
        return this.value.size();
    }

    /**
     * Returns the element at the specified position in this list.
     *
     * @param index index of the element to return
     * @return the element at the specified position in this list
     */
    public String get(int index) {
        return this.value.get(index);
    }
}
