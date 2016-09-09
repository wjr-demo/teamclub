package plugins.ebean;

import com.avaje.ebean.Page;
import play.libs.Json;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhangmeng on 16-7-19.
 */
public class Paging<A>{
    public final List<A> data;
    public int totalRows;
    public int pageCount;
    public int currentPage;

    private static final Paging _empty = new Paging(Collections.EMPTY_LIST);
    public static <A> Paging<A> empty(){
        return _empty;
    }

    public Paging(List<A> data) {
        this.data = data;
        this.totalRows = data.size();
    }

    public List<A> getData() {
        return data;
    }

    public int getTotalRows() {
        return totalRows;
    }

    public int getPageCount() {
        return pageCount;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public String toJson(){
        Map<String, Object> rest = new HashMap<String, Object>(4);
        rest.put("recordsTotal",totalRows);
        rest.put("recordsFiltered", totalRows);
        rest.put("data", data);
        return Json.stringify(Json.toJson(rest));
    }

    public static <A> Paging<A> toPage(Page<A> page){
        return new Paging<A>(page.getList(), page.getTotalRowCount(), page.getTotalRowCount(), page.getPageIndex());
    }

    private Paging(List<A> data, int totalRows, int pageCount, int currentPage) {
        super();
        this.data = data;
        this.totalRows = totalRows;
        this.pageCount = pageCount;
        this.currentPage = currentPage;
    }
}
