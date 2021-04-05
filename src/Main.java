public class Main   {

    static final int SIZE = 10000000;
    static final int h = SIZE / 2;
    static float [] arr = new float[SIZE];

    public static void main(String[] args) {
one();

two();
    }

static public void one()
{

    for(int i = 0;i < SIZE;i++)
    {
        arr[i] = 1;
    }
    long a = System.currentTimeMillis();

    for(int i = 0;i < SIZE;i++)
    {
        arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
    }
long b = System.currentTimeMillis();
    System.out.println(b - a);

}

static public void two()
{

    float[] a1 = new float[h];
    float[] a2 = new float[h];
    long a = System.currentTimeMillis();
    System.arraycopy(arr,0,a1,0,h);
    System.arraycopy(arr,h,a2,0,h);


    Thread t1 = new Thread(new Runnable() {
        @Override
        public void run() {

            for (int i = 0;i < h;i++)
            {
                a1[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            }

        }
    });

    Thread t2 = new Thread(new Runnable() {
        @Override
        public void run() {

            for (int i = 0;i < h;i++)
            {
                a2[i] = (float)(arr[i] * Math.sin(0.2f + (h+i)/ 5) * Math.cos(0.2f + (h+i)/ 5) * Math.cos(0.4f + (h+i)/ 2));
            }


        }
    });

    t1.start();
    t2.start();


    try {
        t1.join();
        t2.join();
    } catch (InterruptedException e) {
        e.printStackTrace();
    }

    System.arraycopy(a1,0,arr,0,h);
    System.arraycopy(a2,0,arr,h,h);

    long b = System.currentTimeMillis();
    System.out.println(b-a);
}

}
