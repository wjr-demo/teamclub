package xconcurrent;

import java.util.AbstractCollection;
import java.util.Iterator;

/**
 * Created by zhangmeng on 16-11-4.
 */
public class DemoColl extends AbstractCollection<String> {
    String[] ss = new String[] {"1", "3", "8", "7", "10", "12", "13"};

    int outersize = ss.length;

    public static void main(String[] args) {
        DemoColl dc = new DemoColl();
        Iterator<String> iterator = dc.iterator();
        while(iterator.hasNext()) {
            String val = iterator.next();
            if(Integer.valueOf(val) % 2 == 0) {
                iterator.remove();
            }
        }
        Iterator<String> iter2 = dc.iterator();
        while(iter2.hasNext()) {
            String val = iter2.next();
            System.out.println(val);
        }
    }

    @Override
    public Iterator<String> iterator() {
        return new Iterator<String>() {
            int pos = 0 ;
            @Override
            public boolean hasNext() {
                return pos < outersize;
            }

            @Override
            public String next() {
                return ss[pos++];
            }

            @Override
            public void remove() {
                DemoColl.this.remove(--pos);
            }
        };
    }

    public String remove(int index) {

        String old = ss[index];

        int numMoved = outersize - index - 1;
        if (numMoved > 0)
            System.arraycopy(ss, index+1, ss, index,
                    numMoved);
        ss[--outersize] = null; // Let gc do its work

        return old;
    }

    @Override
    public int size() {
        return outersize;
    }
}
