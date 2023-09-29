//import org.apache.http.HttpResponse;
//import org.apache.http.client.HttpClient;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.impl.client.HttpClients;
//import org.apache.http.util.EntityUtils;
//
//public class RecipeSearchApp {
//
//    public static void main(String[] args) {
//        String apiKey = "あなたのAPIキー";
//        String apiUrl = "https://楽天APIのエンドポイントURL";
//        String keyword = "検索ワード";
//
//        try {
//            HttpClient httpClient = HttpClients.createDefault();
//            HttpGet httpGet = new HttpGet(apiUrl + "?keyword=" + keyword);
//            httpGet.addHeader("Authorization", "Bearer " + apiKey);
//
//            HttpResponse response = httpClient.execute(httpGet);
//
//            if (response.getEntity() != null) {
//                String json = EntityUtils.toString(response.getEntity());
//                // JSONをパースしてレシピ情報を取得
//                // ここでレシピ情報を処理
//                System.out.println(json); // レシピ情報を表示
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}
