public class Function {
    private static Function instance = new Function();

    private Function() {

    }

    public static Function getInstance() {
        return instance;
    }

    TokenUtils tokenUtils = TokenUtils.getInstance();

    public void compUnit() {
        this.funcDef();
    }

    public void funcDef() {
        System.out.print("define");
        this.funcType();

        if (!tokenUtils.getNext().equals("main")) {
            System.exit(4);
        }
        System.out.print(" @" + tokenUtils.getCurrent());

        if (!tokenUtils.getNext().equals("(")) {
            System.exit(4);
        }
        System.out.print(tokenUtils.getCurrent());

        if (!tokenUtils.getNext().equals(")")) {
            System.exit(4);
        }
        System.out.print(tokenUtils.getCurrent());

        this.block();
    }

    public void funcType() {
        if (!tokenUtils.getNext().equals("int")) {
            System.exit(5);
        }
        System.out.print(" " + tokenUtils.getPrint((String) tokenUtils.getCurrent()));
    }

    public void block() {
        if (!tokenUtils.getNext().equals("{")) {
            System.exit(6);
        }
        System.out.println(tokenUtils.getCurrent());

        this.stmt();

        if (!tokenUtils.getNext().equals("}")) {
            System.exit(6);
        }
        System.out.println(tokenUtils.getCurrent());
    }

    public void stmt() {
        if (!tokenUtils.getNext().equals("return")) {
            System.exit(7);
        }
        System.out.print(tokenUtils.getPrint((String) tokenUtils.getCurrent()));

        if (!(tokenUtils.getNext() instanceof Integer)) {
            System.exit(7);
        }
        System.out.println(" i32 " + (Integer) tokenUtils.getCurrent());

        if (!tokenUtils.getNext().equals(";")) {
            System.exit(7);
        }
    }


}
