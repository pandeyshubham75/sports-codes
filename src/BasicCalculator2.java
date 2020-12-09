public class BasicCalculator2 {

    public static void main(String[] args) {
        System.out.println(calculate("3+2*2"));
        System.out.println(calculate(" 3/2 "));
        System.out.println(calculate(" 3+5 / 2 "));
        System.out.println(calculate(""));
        System.out.println(calculate(" "));
        System.out.println(calculate(" 33 "));
    }

    public static int calculate(String s) {
        if (s == null || s.isEmpty()) return 0;
        Node expList = new Node();
        Node nextNode = expList;
        StringBuffer nextExpr = new StringBuffer();
        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ' ') continue;
            if (Character.isDigit(c)) nextExpr.append(c);
            else {
                nextNode.expression = nextExpr.toString();
                nextNode.next = new Node(Character.toString(c));
                nextNode.next.next = new Node();
                nextNode = nextNode.next.next;
                nextExpr.setLength(0);
            }
        }
        nextNode.expression = nextExpr.toString();
        // First iterate for multiply divide
        nextNode = expList;
        Node prevNode = null;
        while (nextNode != null) {
            if ("/".equals(nextNode.expression)) {
                prevNode.expression = Integer.toString(Integer.parseInt(prevNode.expression) / Integer.parseInt(nextNode.next.expression));
                nextNode = nextNode.next.next;
                prevNode.next = nextNode;
                continue;
            }
            if ("*".equals(nextNode.expression)) {
                prevNode.expression = Integer.toString(Integer.parseInt(prevNode.expression) * Integer.parseInt(nextNode.next.expression));
                nextNode = nextNode.next.next;
                prevNode.next = nextNode;
                continue;
            }
            prevNode = nextNode;
            nextNode = nextNode.next;
        }
        nextNode = expList;
        prevNode = null;
        while (nextNode != null) {
            if ("+".equals(nextNode.expression)) {
                prevNode.expression = Integer.toString(Integer.parseInt(prevNode.expression) + Integer.parseInt(nextNode.next.expression));
                nextNode = nextNode.next.next;
                prevNode.next = nextNode;
                continue;
            }
            if ("-".equals(nextNode.expression)) {
                prevNode.expression = Integer.toString(Integer.parseInt(prevNode.expression) - Integer.parseInt(nextNode.next.expression));
                nextNode = nextNode.next.next;
                prevNode.next = nextNode;
                continue;
            }
            prevNode = nextNode;
            nextNode = nextNode.next;
        }
        if (expList.expression == null || expList.expression.isEmpty()) return 0;
        return Integer.parseInt(expList.expression);
    }
}

class Node {
    String expression;
    Node next;

    Node() {
    }

    Node(String expression) {
        this.expression = expression;
    }
}