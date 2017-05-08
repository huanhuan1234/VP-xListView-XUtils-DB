package bawei.com.day421_homework_vp_xutils_db_demo;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by huanhuan on 2017/4/23.
 */

public class MyAsyncTask extends AsyncTask<Object,Void,String> {

    Handler handler;
    private String json;

    public  MyAsyncTask(Handler handler){
    this.handler=handler;
    }
    @Override
    protected String doInBackground(Object... params) {
        try {
            String url= (String) params[0];
            List<BasicNameValuePair> list= (List<BasicNameValuePair>) params[1];

            HttpClient client= new DefaultHttpClient();
            HttpPost post=new HttpPost(url);
            post.setEntity(new UrlEncodedFormEntity(list));
            HttpResponse response = client.execute(post);

            if (response.getStatusLine().getStatusCode()==200){
                HttpEntity entity = response.getEntity();
                json = EntityUtils.toString(entity, "utf-8");

                return json;
            }

			/**
			 Map map = (Map) params[0] ;
			 this.params = map ;

			 String path = "http://qhb.2dyt.com/Bwei/news";


        try {
            URL url = new URL(path);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection() ;
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setConnectTimeout(5000);
            httpURLConnection.setReadTimeout(5000);


            //拼接参数
            StringBuilder stringBuilder = new StringBuilder();
            Set<String> set =  map.keySet() ;
            for(String key : set) {
                stringBuilder.append(key);
                stringBuilder.append("=");
                stringBuilder.append(map.get(key));
                stringBuilder.append("&");
            }



//            page=1&postkey=1503d


            // 发送参数
            stringBuilder =  stringBuilder.deleteCharAt(stringBuilder.length()-1);
            OutputStream outputStream =  httpURLConnection.getOutputStream() ;
            outputStream.write(stringBuilder.toString().getBytes());
            outputStream.flush();


            //
            if(httpURLConnection.getResponseCode() == 200){

               InputStream inputStream =  httpURLConnection.getInputStream() ;

               String result =  StringUtils.inputStreamToString(inputStream);
                return result ;
            }

			*/
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        Message msg=Message.obtain();
        msg.what=1;
        msg.obj=s;
        handler.sendMessage(msg);
    }
}
