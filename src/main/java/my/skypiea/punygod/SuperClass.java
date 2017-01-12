package my.skypiea.punygod;

/**
 * Created by punyGod on 16/12/22.
 */
class SuperClass {


    public static void main(String args[]) {
        SubClass sc = new SubClass();
        //SubClass sc2 = new SubClass(200);
    }


    private int n;

    SuperClass() {
        System.out.println("SuperClass()");
    }

    SuperClass(int n) {
        System.out.println("SuperClass(int n)");
        this.n = n;
    }
}

class SubClass extends SuperClass {
    private int n;

    SubClass() {
        super(300);
        System.out.println("SuperClass");
    }

    public SubClass(int n) {
        System.out.println("SubClass(int n):" + n);
        this.n = n;
    }
}


