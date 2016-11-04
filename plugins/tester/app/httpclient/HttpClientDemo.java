package httpclient;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangmeng on 16-9-28.
 */
public class HttpClientDemo {
    public static void main(String[] args) throws Exception{
        DefaultHttpClient httpClient = new DefaultHttpClient();
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
        formparams.add(new BasicNameValuePair("pager", "1"));
        formparams.add(new BasicNameValuePair("transtype", "21"));
        formparams.add(new BasicNameValuePair("pager_len", "10"));
        formparams.add(new BasicNameValuePair("queryType", "21"));
        formparams.add(new BasicNameValuePair("querywalletno", "all"));
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, "UTF-8");
        HttpPost post = new HttpPost("http://113.57.148.38:81/easweb/trade/recordlist.jhtml");
        post.setHeader(new BasicHeader("Cookie", "JSESSIONID=1042CD8CADBD49E1187DACCFD6F663F1"));
        post.setEntity(entity);
        HttpResponse resp = httpClient.execute(post);
        String result = EntityUtils.toString(resp.getEntity());
        System.out.println(result);
    }
}
